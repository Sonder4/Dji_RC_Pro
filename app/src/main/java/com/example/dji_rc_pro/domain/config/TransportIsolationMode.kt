package com.example.dji_rc_pro.domain.config

enum class TransportIsolationMode(
    val storageValue: String,
    val displayName: String
) {
    UDP_ONLY("udp_only", "UDP Only"),
    BLE_ONLY("ble_only", "BLE Only"),
    BLE_UDP("ble_udp", "BLE + UDP");

    val allowsUdp: Boolean
        get() = this != BLE_ONLY

    val allowsBle: Boolean
        get() = this != UDP_ONLY

    val allowsWifiDiscovery: Boolean
        get() = this != BLE_ONLY

    val shouldAutoStartUdpFromBle: Boolean
        get() = this == BLE_UDP

    companion object {
        fun fromStorageValue(value: String?): TransportIsolationMode {
            return entries.firstOrNull { it.storageValue == value } ?: BLE_UDP
        }
    }
}
