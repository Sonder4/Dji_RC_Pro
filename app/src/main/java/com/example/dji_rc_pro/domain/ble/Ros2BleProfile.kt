package com.example.dji_rc_pro.domain.ble

import android.util.Base64
import com.example.dji_rc_pro.domain.discovery.DiscoveryProtocol
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.UUID
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object Ros2BleProfile {
    const val DEVICE_NAME_PREFIX = "RCBridge-ROS2"

    private const val COMPACT_VERSION = "1"
    private const val PROOF_LENGTH_BYTES = 16

    val SERVICE_UUID: UUID = UUID.fromString("8f231100-6e52-4f7c-9b16-1b20c1a50001")
    val PAIR_CONTROL_UUID: UUID = UUID.fromString("8f231100-6e52-4f7c-9b16-1b20c1a50002")
    val NETWORK_INFO_UUID: UUID = UUID.fromString("8f231100-6e52-4f7c-9b16-1b20c1a50003")
    val CONTROL_STREAM_UUID: UUID = UUID.fromString("8f231100-6e52-4f7c-9b16-1b20c1a50004")
    val STATUS_UUID: UUID = UUID.fromString("8f231100-6e52-4f7c-9b16-1b20c1a50005")

    data class GatewaySession(
        val hostId: String = "",
        val hostName: String = "",
        val sessionId: String? = null,
        val ipv4Address: String? = null,
        val ipv6Address: String? = null,
        val preferredFamily: DiscoveryProtocol.AddressFamily? = null,
        val selectedFamily: DiscoveryProtocol.AddressFamily? = null,
        val selectedAddress: String? = null,
        val controlPort: Int = DiscoveryProtocol.DEFAULT_CONTROL_PORT,
        val leaseMs: Long = 0L,
        val udpReady: Boolean = false,
        val bleReady: Boolean = false,
        val mcuReady: Boolean = false,
        val paired: Boolean = false,
        val bleOnlyControlReady: Boolean = false
    ) {
        val effectiveAddress: String?
            get() = selectedAddress
                ?: when (selectedFamily ?: preferredFamily) {
                    DiscoveryProtocol.AddressFamily.IPV6 -> ipv6Address ?: ipv4Address
                    DiscoveryProtocol.AddressFamily.IPV4 -> ipv4Address ?: ipv6Address
                    null -> ipv6Address ?: ipv4Address
                }

        val shouldTransmitControlOverBle: Boolean
            get() = (bleOnlyControlReady || (paired && bleReady)) && !udpReady
    }

    data class PairProbeState(
        val clientId: String,
        val clientNonce: String,
        val pairCode: String,
        val supportIpv4: Boolean = true,
        val supportIpv6: Boolean = true
    )

    data class PendingPairContext(
        val hostId: String,
        val hostNonce: String,
        val clientId: String,
        val clientNonce: String,
        val pairCode: String
    )

    fun matchesGatewayName(name: String?): Boolean {
        val normalized = name?.trim().orEmpty()
        return normalized.startsWith(DEVICE_NAME_PREFIX, ignoreCase = true)
    }

    fun createProbeState(
        clientSeed: String,
        pairCode: String,
        supportIpv4: Boolean = true,
        supportIpv6: Boolean = true
    ): PairProbeState {
        return PairProbeState(
            clientId = stableWireId(clientSeed),
            clientNonce = randomWireToken(),
            pairCode = pairCode,
            supportIpv4 = supportIpv4,
            supportIpv6 = supportIpv6
        )
    }

    fun buildPairProbe(state: PairProbeState): ByteArray {
        val supportMask = supportMask(state.supportIpv4, state.supportIpv6)
        val proof = compactProof(
            state.pairCode,
            listOf("p", COMPACT_VERSION, state.clientId, state.clientNonce, supportMask)
        )
        return buildKeyValuePayload(
            linkedMapOf(
                "v" to COMPACT_VERSION,
                "t" to "p",
                "c" to state.clientId,
                "n" to state.clientNonce,
                "x" to supportMask,
                "m" to proof
            )
        )
    }

    fun parsePairOffer(
        payload: ByteArray,
        probeState: PairProbeState,
        nowMs: Long = System.currentTimeMillis()
    ): DiscoveryProtocol.DiscoveredHost? {
        val fields = parseKeyValuePayload(payload)
        if (fields["v"] != COMPACT_VERSION || fields["t"] != "o") {
            return null
        }

        val hostId = fields["h"] ?: return null
        val clientId = fields["c"] ?: return null
        val clientNonce = fields["n"] ?: return null
        val hostNonce = fields["o"] ?: return null
        val controlPort = fields["p"]?.toIntOrNull() ?: DiscoveryProtocol.DEFAULT_CONTROL_PORT
        val leaseMs = fields["l"]?.toLongOrNull() ?: 0L
        val ready = fields["r"] == "1"
        val busy = fields["b"] == "1"
        val selectedFamily = compactFamilyFromWire(fields["f"]) ?: return null
        val selectedAddress = fields["a"]?.takeIf { it.isNotBlank() } ?: return null
        val proof = fields["m"] ?: return null

        if (clientId != probeState.clientId || clientNonce != probeState.clientNonce) {
            return null
        }

        val expectedProof = compactProof(
            probeState.pairCode,
            listOf(
                "o",
                COMPACT_VERSION,
                hostId,
                clientId,
                clientNonce,
                hostNonce,
                controlPort.toString(),
                leaseMs.toString(),
                compactFamilyToWire(selectedFamily),
                selectedAddress,
                if (ready) "1" else "0",
                if (busy) "1" else "0"
            )
        )
        if (proof != expectedProof) {
            return null
        }

        return DiscoveryProtocol.DiscoveredHost(
            hostId = hostId,
            hostName = hostId,
            controlPort = controlPort,
            discoveryPort = DiscoveryProtocol.DEFAULT_DISCOVERY_PORT,
            ready = ready,
            busy = busy,
            leaseMs = leaseMs,
            hostNonce = hostNonce,
            ipv4Address = selectedAddress.takeIf { selectedFamily == DiscoveryProtocol.AddressFamily.IPV4 },
            ipv6Address = selectedAddress.takeIf { selectedFamily == DiscoveryProtocol.AddressFamily.IPV6 },
            selectedFamily = selectedFamily,
            lastSeenAtMs = nowMs
        )
    }

    fun createPendingPairContext(
        host: DiscoveryProtocol.DiscoveredHost,
        probeState: PairProbeState
    ): PendingPairContext {
        return PendingPairContext(
            hostId = host.hostId,
            hostNonce = host.hostNonce,
            clientId = probeState.clientId,
            clientNonce = probeState.clientNonce,
            pairCode = probeState.pairCode
        )
    }

    fun buildPairRequest(host: DiscoveryProtocol.DiscoveredHost, probeState: PairProbeState): ByteArray {
        val proof = compactProof(
            probeState.pairCode,
            listOf(
                "r",
                COMPACT_VERSION,
                host.hostId,
                probeState.clientId,
                probeState.clientNonce,
                host.hostNonce,
                host.controlPort.toString()
            )
        )
        return buildKeyValuePayload(
            linkedMapOf(
                "v" to COMPACT_VERSION,
                "t" to "r",
                "h" to host.hostId,
                "c" to probeState.clientId,
                "n" to probeState.clientNonce,
                "o" to host.hostNonce,
                "p" to host.controlPort.toString(),
                "m" to proof
            )
        )
    }

    fun parsePairAck(payload: ByteArray, context: PendingPairContext): DiscoveryProtocol.PairAck? {
        val fields = parseKeyValuePayload(payload)
        if (fields["v"] != COMPACT_VERSION || fields["t"] != "a") {
            return null
        }

        val hostId = fields["h"] ?: return null
        val sessionId = fields["s"] ?: return null
        val leaseMs = fields["l"]?.toLongOrNull() ?: return null
        val controlPort = fields["p"]?.toIntOrNull() ?: return null
        val selectedFamily = compactFamilyFromWire(fields["f"]) ?: return null
        val address = fields["a"]?.takeIf { it.isNotBlank() } ?: return null
        val proof = fields["m"] ?: return null

        if (hostId != context.hostId) {
            return null
        }

        val expectedProof = compactProof(
            context.pairCode,
            listOf(
                "a",
                COMPACT_VERSION,
                hostId,
                context.clientId,
                context.clientNonce,
                context.hostNonce,
                sessionId,
                controlPort.toString(),
                leaseMs.toString(),
                compactFamilyToWire(selectedFamily),
                address
            )
        )
        if (proof != expectedProof) {
            return null
        }

        return DiscoveryProtocol.PairAck(
            hostId = hostId,
            address = address,
            sessionId = sessionId,
            leaseMs = leaseMs,
            controlPort = controlPort,
            selectedFamily = selectedFamily
        )
    }

    fun parsePairBusy(payload: ByteArray, context: PendingPairContext): DiscoveryProtocol.PairBusy? {
        val fields = parseKeyValuePayload(payload)
        if (fields["v"] != COMPACT_VERSION || fields["t"] != "b") {
            return null
        }

        val hostId = fields["h"] ?: return null
        val leaseMs = fields["l"]?.toLongOrNull() ?: 0L
        val reason = fields["r"]
        val proof = fields["m"] ?: return null
        if (hostId != context.hostId) {
            return null
        }

        val expectedProof = compactProof(
            context.pairCode,
            listOf(
                "b",
                COMPACT_VERSION,
                hostId,
                context.clientId,
                context.clientNonce,
                context.hostNonce,
                reason.orEmpty(),
                leaseMs.toString()
            )
        )
        if (proof != expectedProof) {
            return null
        }

        return DiscoveryProtocol.PairBusy(
            hostId = hostId,
            address = "",
            leaseMs = leaseMs,
            reason = reason
        )
    }

    fun parseKeyValuePayload(payload: ByteArray): Map<String, String> =
        parseKeyValuePayload(payload.toString(StandardCharsets.UTF_8))

    fun parseKeyValuePayload(payload: String): Map<String, String> {
        return payload
            .lineSequence()
            .map { it.trim() }
            .filter { it.isNotEmpty() }
            .mapNotNull { line ->
                val separator = line.indexOf('=')
                if (separator <= 0) {
                    null
                } else {
                    line.substring(0, separator) to line.substring(separator + 1)
                }
            }
            .toMap()
    }

    fun buildKeyValuePayload(fields: Map<String, String?>): ByteArray {
        val payload = buildString {
            fields.forEach { (key, value) ->
                value?.let {
                    append(key)
                    append('=')
                    append(it)
                    append('\n')
                }
            }
            append('\n')
        }
        return payload.toByteArray(StandardCharsets.UTF_8)
    }

    fun parseNetworkInfo(payload: ByteArray, current: GatewaySession = GatewaySession()): GatewaySession? {
        val fields = parseKeyValuePayload(payload)
        if (fields["v"] == COMPACT_VERSION && fields["t"] == "n") {
            val selectedFamily = compactFamilyFromWire(fields["f"])
            val preferredFamily = compactFamilyFromWire(fields["pf"]) ?: selectedFamily
            val ipv4 = fields["4"]?.takeIf { it.isNotBlank() }
            val ipv6 = fields["6"]?.takeIf { it.isNotBlank() }
            val selectedAddress = fields["a"]?.takeIf { it.isNotBlank() }
                ?: when (selectedFamily ?: preferredFamily) {
                    DiscoveryProtocol.AddressFamily.IPV6 -> ipv6 ?: ipv4
                    DiscoveryProtocol.AddressFamily.IPV4 -> ipv4 ?: ipv6
                    null -> ipv6 ?: ipv4
                }
            val hostId = fields["h"] ?: current.hostId
            return current.copy(
                hostId = hostId,
                hostName = current.hostName.ifBlank { hostId },
                sessionId = fields["s"] ?: current.sessionId,
                ipv4Address = ipv4 ?: current.ipv4Address,
                ipv6Address = ipv6 ?: current.ipv6Address,
                preferredFamily = preferredFamily ?: current.preferredFamily,
                selectedFamily = selectedFamily ?: current.selectedFamily ?: preferredFamily,
                selectedAddress = selectedAddress ?: current.selectedAddress,
                controlPort = fields["p"]?.toIntOrNull() ?: current.controlPort,
                leaseMs = fields["l"]?.toLongOrNull() ?: current.leaseMs,
                bleReady = fields["b"] == "1" || current.bleReady,
                paired = fields["s"]?.isNotBlank() == true || current.paired
            )
        }

        if (fields["type"] != "network_info") {
            return null
        }

        val preferredFamily = DiscoveryProtocol.AddressFamily.fromWireValue(fields["preferred_family"])
        val selectedFamily = DiscoveryProtocol.AddressFamily.fromWireValue(fields["selected_family"])
        val ipv4 = fields["ipv4"]?.takeIf { it.isNotBlank() }
        val ipv6 = fields["ipv6"]?.takeIf { it.isNotBlank() }
        val selectedAddress = fields["selected_address"]?.takeIf { it.isNotBlank() }
            ?: when (selectedFamily ?: preferredFamily) {
                DiscoveryProtocol.AddressFamily.IPV6 -> ipv6 ?: ipv4
                DiscoveryProtocol.AddressFamily.IPV4 -> ipv4 ?: ipv6
                null -> ipv6 ?: ipv4
            }

        return current.copy(
            hostId = fields["host_id"] ?: current.hostId,
            hostName = fields["host_name"] ?: current.hostName,
            sessionId = fields["session_id"] ?: current.sessionId,
            ipv4Address = ipv4 ?: current.ipv4Address,
            ipv6Address = ipv6 ?: current.ipv6Address,
            preferredFamily = preferredFamily ?: current.preferredFamily,
            selectedFamily = selectedFamily ?: current.selectedFamily ?: preferredFamily,
            selectedAddress = selectedAddress ?: current.selectedAddress,
            controlPort = fields["control_port"]?.toIntOrNull() ?: current.controlPort,
            leaseMs = fields["lease_ms"]?.toLongOrNull() ?: current.leaseMs,
            bleReady = fields["ble_ready"] == "1" || current.bleReady,
            paired = fields["session_id"]?.isNotBlank() == true || current.paired
        )
    }

    fun parseStatus(payload: ByteArray, current: GatewaySession = GatewaySession()): GatewaySession? {
        val fields = parseKeyValuePayload(payload)
        if (fields["v"] == COMPACT_VERSION && fields["t"] == "s") {
            val selectedFamily = compactFamilyFromWire(fields["f"])
            val hostId = fields["h"] ?: current.hostId
            return current.copy(
                hostId = hostId,
                hostName = current.hostName.ifBlank { hostId },
                sessionId = fields["s"] ?: current.sessionId,
                selectedFamily = selectedFamily ?: current.selectedFamily,
                selectedAddress = fields["a"]?.takeIf { it.isNotBlank() } ?: current.selectedAddress,
                controlPort = fields["p"]?.toIntOrNull() ?: current.controlPort,
                udpReady = fields["u"] == "1",
                bleReady = fields["b"] == "1",
                mcuReady = fields["k"] == "1",
                paired = fields["d"] == "1" || current.paired
            )
        }

        if (fields["type"] != "status") {
            return null
        }

        val selectedFamily = DiscoveryProtocol.AddressFamily.fromWireValue(fields["selected_family"])
        return current.copy(
            hostId = fields["host_id"] ?: current.hostId,
            hostName = fields["host_name"] ?: current.hostName,
            sessionId = fields["session_id"] ?: current.sessionId,
            selectedFamily = selectedFamily ?: current.selectedFamily,
            selectedAddress = fields["selected_address"]?.takeIf { it.isNotBlank() } ?: current.selectedAddress,
            controlPort = fields["control_port"]?.toIntOrNull() ?: current.controlPort,
            udpReady = fields["udp_ready"] == "1",
            bleReady = fields["ble_ready"] == "1",
            mcuReady = fields["mcu_ready"] == "1",
            paired = fields["paired"] == "1" || current.paired
        )
    }

    private fun supportMask(ipv4: Boolean, ipv6: Boolean): String {
        val mask = (if (ipv4) 0x1 else 0) or (if (ipv6) 0x2 else 0)
        return mask.toString()
    }

    private fun compactProof(pairCode: String, parts: List<String>): String {
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(pairCode.toByteArray(StandardCharsets.UTF_8), "HmacSHA256"))
        val digest = mac.doFinal(parts.joinToString("|").toByteArray(StandardCharsets.UTF_8))
        return Base64.encodeToString(
            digest.copyOf(PROOF_LENGTH_BYTES),
            Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING
        )
    }

    private fun stableWireId(seed: String): String {
        val digest = MessageDigest.getInstance("SHA-256").digest(seed.toByteArray(StandardCharsets.UTF_8))
        return digest.copyOf(8).joinToString("") { "%02x".format(it) }
    }

    private fun randomWireToken(): String {
        return UUID.randomUUID().toString().replace("-", "").take(16)
    }

    private fun compactFamilyToWire(family: DiscoveryProtocol.AddressFamily): String {
        return when (family) {
            DiscoveryProtocol.AddressFamily.IPV4 -> "4"
            DiscoveryProtocol.AddressFamily.IPV6 -> "6"
        }
    }

    private fun compactFamilyFromWire(value: String?): DiscoveryProtocol.AddressFamily? {
        return when (value) {
            "4", "ipv4" -> DiscoveryProtocol.AddressFamily.IPV4
            "6", "ipv6" -> DiscoveryProtocol.AddressFamily.IPV6
            else -> null
        }
    }
}
