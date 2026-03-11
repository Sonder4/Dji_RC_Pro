package com.example.dji_rc_pro.domain.discovery

import android.content.Context
import android.net.DhcpInfo
import android.net.wifi.WifiManager
import com.example.dji_rc_pro.manager.DataLogManager
import com.example.dji_rc_pro.util.LogUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.Inet4Address
import java.net.Inet6Address
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.MulticastSocket
import java.net.NetworkInterface
import java.net.SocketException
import java.net.SocketTimeoutException

sealed class DiscoveryEvent {
    data class PairAcknowledged(val ack: DiscoveryProtocol.PairAck) : DiscoveryEvent()
    data class PairRejected(val busy: DiscoveryProtocol.PairBusy) : DiscoveryEvent()
    data class Error(val message: String) : DiscoveryEvent()
}

class WifiDiscoveryManager(
    context: Context,
    private val dataLogManager: DataLogManager = DataLogManager.getInstance()
) {
    companion object {
        private const val TAG = "WifiDiscoveryManager"
        private const val SOCKET_TIMEOUT_MS = 1000
        private const val HOST_STALE_TIMEOUT_MS = 3500L
        private const val PROBE_INTERVAL_MS = 1000L
    }

    private val applicationContext = context.applicationContext
    private val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as? WifiManager
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val discoveredHostMap = linkedMapOf<String, DiscoveryProtocol.DiscoveredHost>()

    private val _discoveredHosts = MutableStateFlow<List<DiscoveryProtocol.DiscoveredHost>>(emptyList())
    val discoveredHosts: StateFlow<List<DiscoveryProtocol.DiscoveredHost>> = _discoveredHosts.asStateFlow()

    private val _events = MutableSharedFlow<DiscoveryEvent>(extraBufferCapacity = 16)
    val events: SharedFlow<DiscoveryEvent> = _events.asSharedFlow()

    private var multicastLock: WifiManager.MulticastLock? = null
    private var ipv6Socket: MulticastSocket? = null
    private var ipv4Socket: DatagramSocket? = null
    private var discoveryInterface: NetworkInterface? = null

    private var ipv6ListenJob: Job? = null
    private var ipv4ListenJob: Job? = null
    private var cleanupJob: Job? = null
    private var probeJob: Job? = null

    private var activeSession: ActiveSession? = null
    private var probeState: DiscoveryProtocol.ProbeState? = null
    private var pendingPairContext: DiscoveryProtocol.PendingPairContext? = null
    private var startedClientId: String? = null
    private var startedPairCode: String? = null
    private var bootstrapTargets: Set<String> = emptySet()
    private var startedBootstrapTargets: Set<String> = emptySet()

    private data class ActiveSession(
        val hostId: String,
        val hostAddress: String,
        val discoveryPort: Int,
        val sessionId: String,
        val family: DiscoveryProtocol.AddressFamily
    )

    fun start(clientId: String, pairCode: String, bootstrapTargets: Set<String> = emptySet()) {
        if (clientId.isBlank()) {
            emitError("客户端 ID 为空，无法启动自动发现")
            return
        }

        val normalizedPairCode = pairCode.ifBlank { DiscoveryProtocol.DEFAULT_PAIR_CODE }
        val normalizedBootstrapTargets = bootstrapTargets
            .map(::normalizeTarget)
            .filter { it.isNotBlank() }
            .toSet()
        if (
            startedClientId == clientId &&
            startedPairCode == normalizedPairCode &&
            startedBootstrapTargets == normalizedBootstrapTargets &&
            (ipv4ListenJob?.isActive == true || ipv6ListenJob?.isActive == true)
        ) {
            return
        }

        stop()

        val iface = findWifiInterface()
        if (iface == null) {
            emitError("未找到可用于自动发现的 Wi‑Fi 接口")
            return
        }

        probeState = DiscoveryProtocol.ProbeState(
            clientId = clientId,
            clientNonce = generateNonce(),
            pairCode = normalizedPairCode
        )
        this.bootstrapTargets = normalizedBootstrapTargets
        startedClientId = clientId
        startedPairCode = normalizedPairCode
        startedBootstrapTargets = normalizedBootstrapTargets

        try {
            acquireMulticastLock()
            discoveryInterface = iface
            ipv4Socket = openIpv4Socket()
            ipv6Socket = openIpv6Socket(iface)

            if (ipv4Socket == null && ipv6Socket == null) {
                throw IllegalStateException("IPv4/IPv6 发现套接字都不可用")
            }

            ipv4ListenJob = ipv4Socket?.let { socket ->
                serviceScope.launch { listenLoop(socket) }
            }
            ipv6ListenJob = ipv6Socket?.let { socket ->
                serviceScope.launch { listenLoop(socket) }
            }
            cleanupJob = serviceScope.launch { cleanupLoop() }
            probeJob = serviceScope.launch { probeLoop() }

            logDebug(
                "Discovery started on interface ${iface.name} " +
                    "(ipv4=${ipv4Socket != null}, ipv6=${ipv6Socket != null})"
            )
        } catch (error: Exception) {
            stop()
            emitError("启动自动发现失败: ${error.message}")
        }
    }

    fun stop() {
        ipv4ListenJob?.cancel()
        ipv6ListenJob?.cancel()
        cleanupJob?.cancel()
        probeJob?.cancel()
        ipv4ListenJob = null
        ipv6ListenJob = null
        cleanupJob = null
        probeJob = null

        runCatching {
            ipv6Socket?.let { socket ->
                discoveryInterface?.let { iface ->
                    runCatching {
                        socket.leaveGroup(
                            InetSocketAddress(
                                InetAddress.getByName(DiscoveryProtocol.DEFAULT_DISCOVERY_GROUP),
                                DiscoveryProtocol.DEFAULT_DISCOVERY_PORT
                            ),
                            iface
                        )
                    }
                }
                socket.close()
            }
        }
        runCatching { ipv4Socket?.close() }

        ipv4Socket = null
        ipv6Socket = null
        discoveryInterface = null
        releaseMulticastLock()
        activeSession = null
        pendingPairContext = null
        probeState = null
        startedClientId = null
        startedPairCode = null
        bootstrapTargets = emptySet()
        startedBootstrapTargets = emptySet()
        synchronized(discoveredHostMap) {
            discoveredHostMap.clear()
            _discoveredHosts.value = emptyList()
        }
        logDebug("Discovery stopped")
    }

    fun shutdown() {
        stop()
    }

    fun requestPairing(host: DiscoveryProtocol.DiscoveredHost) {
        val activeProbeState = probeState
        if (activeProbeState == null) {
            emitError("发现未启动，无法发起配对")
            return
        }

        val socket = socketForFamily(host.selectedFamily) ?: run {
            emitError("${host.selectedFamily.wireValue} 套接字不可用，无法发起配对")
            return
        }

        pendingPairContext = DiscoveryProtocol.PendingPairContext(
            hostId = host.hostId,
            hostNonce = host.hostNonce,
            clientId = activeProbeState.clientId,
            clientNonce = activeProbeState.clientNonce,
            pairCode = activeProbeState.pairCode
        )

        serviceScope.launch {
            try {
                val payload = DiscoveryProtocol.buildPairRequest(host, activeProbeState)
                val packet = DatagramPacket(
                    payload,
                    payload.size,
                    InetAddress.getByName(host.address),
                    host.discoveryPort
                )
                socket.send(packet)
                logDebug("Sent pair request to ${host.label}")
            } catch (error: Exception) {
                pendingPairContext = null
                emitError("发送配对请求失败: ${error::class.java.simpleName}${error.message?.let { ": $it" } ?: ""}")
            }
        }
    }

    fun sendUnpair(clientId: String) {
        val session = activeSession ?: return
        val socket = socketForFamily(session.family) ?: return
        serviceScope.launch {
            try {
                val payload = DiscoveryProtocol.buildUnpair(clientId, session.sessionId)
                val packet = DatagramPacket(
                    payload,
                    payload.size,
                    InetAddress.getByName(session.hostAddress),
                    session.discoveryPort
                )
                socket.send(packet)
                logDebug("Sent unpair to ${session.hostId}@${session.hostAddress}")
            } catch (error: Exception) {
                emitError("发送解绑请求失败: ${error::class.java.simpleName}${error.message?.let { ": $it" } ?: ""}")
            } finally {
                activeSession = null
            }
        }
    }

    private suspend fun probeLoop() {
        while (serviceScope.isActive) {
            val state = probeState
            if (state != null) {
                sendIpv4Probe(state)
                sendIpv6Probe(state)
                sendDirectProbes(state)
            }
            delay(PROBE_INTERVAL_MS)
        }
    }

    private fun sendIpv4Probe(state: DiscoveryProtocol.ProbeState) {
        val socket = ipv4Socket ?: return
        val payload = DiscoveryProtocol.buildProbe(
            clientId = state.clientId,
            clientNonce = state.clientNonce,
            pairCode = state.pairCode,
            supportIpv4 = true,
            supportIpv6 = ipv6Socket != null
        )
        val addresses = linkedSetOf<String>()
        addresses += "255.255.255.255"
        computeDirectedBroadcasts().forEach { addresses += it }

        for (address in addresses) {
            runCatching {
                val packet = DatagramPacket(
                    payload,
                    payload.size,
                    InetAddress.getByName(address),
                    DiscoveryProtocol.DEFAULT_DISCOVERY_PORT
                )
                socket.send(packet)
            }.onFailure { error ->
                logDebug("IPv4 probe send failed to $address: ${error.message}")
            }
        }
    }

    private fun sendIpv6Probe(state: DiscoveryProtocol.ProbeState) {
        val socket = ipv6Socket ?: return
        runCatching {
            val payload = DiscoveryProtocol.buildProbe(
                clientId = state.clientId,
                clientNonce = state.clientNonce,
                pairCode = state.pairCode,
                supportIpv4 = ipv4Socket != null,
                supportIpv6 = true
            )
            val packet = DatagramPacket(
                payload,
                payload.size,
                InetAddress.getByName(DiscoveryProtocol.DEFAULT_DISCOVERY_GROUP),
                DiscoveryProtocol.DEFAULT_DISCOVERY_PORT
            )
            socket.send(packet)
        }.onFailure { error ->
            logDebug("IPv6 probe send failed: ${error.message}")
        }
    }

    private fun sendDirectProbes(state: DiscoveryProtocol.ProbeState) {
        if (bootstrapTargets.isEmpty()) {
            return
        }

        val payload = DiscoveryProtocol.buildProbe(
            clientId = state.clientId,
            clientNonce = state.clientNonce,
            pairCode = state.pairCode,
            supportIpv4 = ipv4Socket != null,
            supportIpv6 = ipv6Socket != null
        )

        bootstrapTargets.forEach { address ->
            val family = if (':' in address) {
                DiscoveryProtocol.AddressFamily.IPV6
            } else {
                DiscoveryProtocol.AddressFamily.IPV4
            }
            val socket = socketForFamily(family) ?: return@forEach
            runCatching {
                val packet = DatagramPacket(
                    payload,
                    payload.size,
                    InetAddress.getByName(address),
                    DiscoveryProtocol.DEFAULT_DISCOVERY_PORT
                )
                socket.send(packet)
                logDebug("Sent direct probe to $address via ${family.wireValue}")
            }.onFailure { error ->
                logDebug("Direct probe send failed to $address: ${error.message}")
            }
        }
    }

    private suspend fun listenLoop(socket: DatagramSocket) {
        val buffer = ByteArray(4096)
        while (serviceScope.isActive) {
            try {
                val packet = DatagramPacket(buffer, buffer.size)
                socket.receive(packet)
                val payload = packet.data.decodeToString(packet.offset, packet.offset + packet.length)
                val sourceAddress = normalizeAddress(packet.address)
                handleDiscoveryPacket(payload, sourceAddress)
            } catch (_: SocketTimeoutException) {
                continue
            } catch (_: SocketException) {
                break
            } catch (error: Exception) {
                if (serviceScope.isActive) {
                    emitError("自动发现监听异常: ${error.message}")
                }
                break
            }
        }
    }

    private suspend fun handleDiscoveryPacket(payload: String, sourceAddress: String) {
        val state = probeState ?: return
        val offer = DiscoveryProtocol.parseOffer(payload, sourceAddress, state)
        if (offer != null) {
            synchronized(discoveredHostMap) {
                val merged = DiscoveryProtocol.mergeHost(discoveredHostMap[offer.hostId], offer)
                discoveredHostMap[offer.hostId] = merged
                _discoveredHosts.value = discoveredHostMap.values.sortedWith(
                    compareBy<DiscoveryProtocol.DiscoveredHost> { it.hostName }.thenBy { it.address }
                )
            }
            logDebug(
                "Discovered host ${offer.hostName} " +
                    "(ipv4=${offer.ipv4Address ?: "-"}, ipv6=${offer.ipv6Address ?: "-"}, family=${offer.selectedFamily.wireValue})"
            )
            return
        }

        val context = pendingPairContext ?: return
        val pairAck = DiscoveryProtocol.parsePairAck(payload, sourceAddress, context)
        if (pairAck != null) {
            activeSession = ActiveSession(
                hostId = pairAck.hostId,
                hostAddress = pairAck.address,
                discoveryPort = DiscoveryProtocol.DEFAULT_DISCOVERY_PORT,
                sessionId = pairAck.sessionId,
                family = pairAck.selectedFamily
            )
            pendingPairContext = null
            _events.emit(DiscoveryEvent.PairAcknowledged(pairAck))
            logDebug("Pair ack from ${pairAck.hostId}@${pairAck.address}:${pairAck.controlPort}")
            return
        }

        val pairBusy = DiscoveryProtocol.parsePairBusy(payload, sourceAddress, context)
        if (pairBusy != null) {
            pendingPairContext = null
            _events.emit(DiscoveryEvent.PairRejected(pairBusy))
            logDebug("Pair busy from ${pairBusy.hostId}@${pairBusy.address}")
        }
    }

    private suspend fun cleanupLoop() {
        while (serviceScope.isActive) {
            delay(1000)
            val cutoff = System.currentTimeMillis() - HOST_STALE_TIMEOUT_MS
            synchronized(discoveredHostMap) {
                val iterator = discoveredHostMap.entries.iterator()
                var changed = false
                while (iterator.hasNext()) {
                    if (iterator.next().value.lastSeenAtMs < cutoff) {
                        iterator.remove()
                        changed = true
                    }
                }
                if (changed) {
                    _discoveredHosts.value = discoveredHostMap.values.sortedWith(
                        compareBy<DiscoveryProtocol.DiscoveredHost> { it.hostName }.thenBy { it.address }
                    )
                }
            }
        }
    }

    private fun openIpv4Socket(): DatagramSocket? {
        return runCatching {
            DatagramSocket(null).apply {
                reuseAddress = true
                broadcast = true
                soTimeout = SOCKET_TIMEOUT_MS
                bind(InetSocketAddress(DiscoveryProtocol.DEFAULT_DISCOVERY_PORT))
            }
        }.getOrElse {
            logDebug("IPv4 discovery socket unavailable: ${it.message}")
            null
        }
    }

    private fun openIpv6Socket(iface: NetworkInterface): MulticastSocket? {
        return runCatching {
            MulticastSocket(null).apply {
                reuseAddress = true
                soTimeout = SOCKET_TIMEOUT_MS
                bind(InetSocketAddress(DiscoveryProtocol.DEFAULT_DISCOVERY_PORT))
                networkInterface = iface
                joinGroup(
                    InetSocketAddress(
                        InetAddress.getByName(DiscoveryProtocol.DEFAULT_DISCOVERY_GROUP),
                        DiscoveryProtocol.DEFAULT_DISCOVERY_PORT
                    ),
                    iface
                )
            }
        }.getOrElse {
            logDebug("IPv6 discovery socket unavailable: ${it.message}")
            null
        }
    }

    private fun socketForFamily(family: DiscoveryProtocol.AddressFamily): DatagramSocket? {
        return when (family) {
            DiscoveryProtocol.AddressFamily.IPV4 -> ipv4Socket
            DiscoveryProtocol.AddressFamily.IPV6 -> ipv6Socket
        }
    }

    private fun acquireMulticastLock() {
        if (multicastLock == null) {
            multicastLock = wifiManager?.createMulticastLock(TAG)?.apply {
                setReferenceCounted(false)
                acquire()
            }
        }
    }

    private fun releaseMulticastLock() {
        multicastLock?.let { lock ->
            if (lock.isHeld) {
                lock.release()
            }
        }
        multicastLock = null
    }

    private fun findWifiInterface(): NetworkInterface? {
        return try {
            NetworkInterface.getNetworkInterfaces().toList()
                .filter { iface -> iface.isUp && !iface.isLoopback }
                .sortedByDescending { iface -> if (iface.name.startsWith("wlan", ignoreCase = true)) 1 else 0 }
                .firstOrNull { iface ->
                    iface.inetAddresses.toList().any { address ->
                        (address is Inet4Address && !address.isLoopbackAddress) ||
                            (address is Inet6Address && !address.isLoopbackAddress && !address.isLinkLocalAddress)
                    }
                }
        } catch (error: Exception) {
            emitError("扫描网络接口失败: ${error.message}")
            null
        }
    }

    private fun computeDirectedBroadcasts(): Set<String> {
        val addresses = linkedSetOf<String>()
        val dhcpInfo = wifiManager?.dhcpInfo
        if (dhcpInfo != null) {
            broadcastAddressFromDhcp(dhcpInfo)?.let { addresses += it }
        }
        discoveryInterface?.interfaceAddresses
            ?.mapNotNull { it.broadcast?.hostAddress }
            ?.map { it.substringBefore('%') }
            ?.forEach { addresses += it }
        return addresses
    }

    private fun broadcastAddressFromDhcp(dhcpInfo: DhcpInfo): String? {
        val ip = dhcpInfo.ipAddress
        val netmask = dhcpInfo.netmask
        if (ip == 0 || netmask == 0) {
            return null
        }
        val broadcast = (ip and netmask) or netmask.inv()
        val bytes = byteArrayOf(
            (broadcast and 0xff).toByte(),
            (broadcast shr 8 and 0xff).toByte(),
            (broadcast shr 16 and 0xff).toByte(),
            (broadcast shr 24 and 0xff).toByte()
        )
        return InetAddress.getByAddress(bytes).hostAddress
    }

    private fun normalizeTarget(address: String): String {
        return address.trim()
            .removePrefix("[")
            .removeSuffix("]")
            .substringBefore('%')
    }

    private fun normalizeAddress(address: InetAddress): String {
        return address.hostAddress?.substringBefore('%') ?: address.hostAddress ?: "unknown"
    }

    private fun generateNonce(): String {
        return java.util.UUID.randomUUID().toString().replace("-", "")
    }

    private fun logDebug(message: String) {
        dataLogManager.addUdpDebugLog(message)
        LogUtil.d(message, TAG)
    }

    private fun emitError(message: String) {
        dataLogManager.addUdpDebugLog(message)
        LogUtil.e(message, TAG)
        _events.tryEmit(DiscoveryEvent.Error(message))
    }
}
