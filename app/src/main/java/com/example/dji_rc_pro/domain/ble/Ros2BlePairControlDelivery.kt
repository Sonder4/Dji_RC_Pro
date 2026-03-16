package com.example.dji_rc_pro.domain.ble

import com.example.dji_rc_pro.domain.config.TransportIsolationMode

enum class Ros2BlePairControlDelivery {
    NOTIFY_WITH_POLL_FALLBACK,
    POLL_ONLY
}

object Ros2BlePairControlDeliveryResolver {
    fun resolve(
        transportMode: TransportIsolationMode,
        supportsNotify: Boolean
    ): Ros2BlePairControlDelivery {
        if (supportsNotify && transportMode == TransportIsolationMode.BLE_ONLY) {
            return Ros2BlePairControlDelivery.NOTIFY_WITH_POLL_FALLBACK
        }
        return Ros2BlePairControlDelivery.POLL_ONLY
    }
}
