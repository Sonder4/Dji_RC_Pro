package com.example.dji_rc_pro.domain.diagnostics

import android.bluetooth.BluetoothProfile
import com.example.dji_rc_pro.domain.ble.Ros2BleProfile
import com.example.dji_rc_pro.domain.config.TransportIsolationMode
import com.example.dji_rc_pro.domain.discovery.DiscoveryProtocol

enum class BleLinkPhase(val label: String) {
    DISCONNECTED("未连接"),
    SCANNING("正在扫描"),
    CONNECTING("正在连接"),
    CONNECTED("连接成功"),
    SYNCING_ADDRESSES("正在对齐地址"),
    VERIFYING_UDP("正在验证UDP"),
    COMMUNICATING("通信正常"),
    COMMUNICATION_FAILED("通信失败"),
    CONNECTION_FAILED("连接失败"),
    BLE_FALLBACK_ACTIVE("已回退到蓝牙"),
}

enum class BleLinkEvent {
    SCAN_STARTED,
    CONNECTION_REQUESTED,
    GATT_CONNECTED,
    ADDRESSES_SYNCED,
    UDP_READY,
    UDP_FAILED,
    BLE_ACTIVITY,
    CONNECTION_FAILED,
    CONNECTION_LOST,
    COMMUNICATION_ERROR,
}

object BleLinkStateMachine {
    fun transition(
        current: BleLinkPhase,
        event: BleLinkEvent,
        transportMode: TransportIsolationMode
    ): BleLinkPhase {
        return when (event) {
            BleLinkEvent.SCAN_STARTED -> BleLinkPhase.SCANNING
            BleLinkEvent.CONNECTION_REQUESTED -> BleLinkPhase.CONNECTING
            BleLinkEvent.GATT_CONNECTED -> BleLinkPhase.CONNECTED
            BleLinkEvent.ADDRESSES_SYNCED -> when (transportMode) {
                TransportIsolationMode.BLE_ONLY -> BleLinkPhase.COMMUNICATING
                TransportIsolationMode.BLE_UDP -> BleLinkPhase.VERIFYING_UDP
                TransportIsolationMode.UDP_ONLY -> BleLinkPhase.CONNECTED
            }
            BleLinkEvent.UDP_READY -> BleLinkPhase.COMMUNICATING
            BleLinkEvent.UDP_FAILED -> if (transportMode == TransportIsolationMode.BLE_UDP) {
                BleLinkPhase.BLE_FALLBACK_ACTIVE
            } else {
                BleLinkPhase.COMMUNICATION_FAILED
            }
            BleLinkEvent.BLE_ACTIVITY -> when (current) {
                BleLinkPhase.CONNECTION_FAILED,
                BleLinkPhase.COMMUNICATION_FAILED,
                BleLinkPhase.DISCONNECTED,
                BleLinkPhase.SCANNING,
                BleLinkPhase.CONNECTING -> current
                else -> BleLinkPhase.COMMUNICATING
            }
            BleLinkEvent.CONNECTION_FAILED -> BleLinkPhase.CONNECTION_FAILED
            BleLinkEvent.CONNECTION_LOST -> BleLinkPhase.DISCONNECTED
            BleLinkEvent.COMMUNICATION_ERROR -> if (current == BleLinkPhase.VERIFYING_UDP &&
                transportMode == TransportIsolationMode.BLE_UDP
            ) {
                BleLinkPhase.BLE_FALLBACK_ACTIVE
            } else {
                BleLinkPhase.COMMUNICATION_FAILED
            }
        }
    }
}

object BleLinkStatusResolver {
    fun normalize(
        current: BleLinkPhase,
        udpState: UdpLinkState,
        bleConnected: Boolean
    ): BleLinkPhase {
        if (bleConnected && udpState == UdpLinkState.READY && current == BleLinkPhase.VERIFYING_UDP) {
            return BleLinkPhase.COMMUNICATING
        }
        return current
    }
}

object BlePairingStatusResolver {
    fun resolveOverride(
        allowsBle: Boolean,
        session: Ros2BleProfile.GatewaySession,
        udpReady: Boolean,
        connectionState: Int,
        isScanning: Boolean,
        pairedHostName: String?
    ): String? {
        if (!allowsBle) {
            return null
        }

        return when {
            session.paired -> {
                val hostName = session.hostName.ifBlank { pairedHostName ?: "ROS2 BLE 网关" }
                "蓝牙已配对 $hostName"
            }
            udpReady -> {
                val hostName = session.hostName.ifBlank { pairedHostName.orEmpty() }.trim()
                if (hostName.isNotEmpty()) {
                    "已连接 $hostName"
                } else {
                    "已连接"
                }
            }
            connectionState == BluetoothProfile.STATE_CONNECTING -> "蓝牙正在连接 ROS2 网关"
            connectionState == BluetoothProfile.STATE_CONNECTED -> "蓝牙已连接，等待短码配对"
            isScanning -> "正在搜索 ROS2 BLE 网关"
            else -> null
        }
    }
}

enum class UdpLinkState(val label: String) {
    IDLE("未启动"),
    VERIFYING("正在验证"),
    READY("通信正常"),
    FAILED("通信失败"),
    BLE_FALLBACK("已回退到蓝牙"),
}

enum class PeerAddressSource(val label: String) {
    MANUAL("手动输入"),
    BLE_SYNC("蓝牙同步"),
    CACHED("缓存地址"),
}

enum class PingSlot(val label: String) {
    LOCAL_IPV4("本机 IPv4"),
    LOCAL_IPV6("本机 IPv6"),
    PEER_IPV4("对端 IPv4"),
    PEER_IPV6("对端 IPv6"),
}

data class NetworkAddressSnapshot(
    val ipv4: String? = null,
    val ipv6: String? = null,
)

data class LinkDiagnosticsState(
    val blePhase: BleLinkPhase = BleLinkPhase.DISCONNECTED,
    val udpState: UdpLinkState = UdpLinkState.IDLE,
    val localAddresses: NetworkAddressSnapshot = NetworkAddressSnapshot(),
    val peerAddresses: NetworkAddressSnapshot = NetworkAddressSnapshot(),
    val currentPrimaryTransport: String = "未建立",
    val peerAddressSource: PeerAddressSource = PeerAddressSource.CACHED,
    val stalePairingCleared: Boolean = false,
) {
    val bleStatusLabel: String
        get() = blePhase.label
}

object AddressSyncPolicy {
    fun shouldClearStalePairing(
        staleAddress: String?,
        peerIpv4: String?,
        peerIpv6: String?,
        probeSucceeded: Boolean,
        bleConnected: Boolean
    ): Boolean {
        if (!bleConnected || probeSucceeded || staleAddress.isNullOrBlank()) {
            return false
        }
        return staleAddress != peerIpv4 && staleAddress != peerIpv6
    }

    fun chooseTargetAddress(
        peerIpv4: String?,
        peerIpv6: String?,
        preferredFamily: DiscoveryProtocol.AddressFamily?
    ): String? {
        return when (preferredFamily) {
            DiscoveryProtocol.AddressFamily.IPV4 -> peerIpv4 ?: peerIpv6
            DiscoveryProtocol.AddressFamily.IPV6 -> peerIpv6 ?: peerIpv4
            null -> peerIpv6 ?: peerIpv4
        }
    }
}
