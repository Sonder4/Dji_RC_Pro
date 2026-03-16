package com.example.dji_rc_pro

import com.example.dji_rc_pro.domain.ble.Ros2BlePairControlDelivery
import com.example.dji_rc_pro.domain.ble.Ros2BlePairControlDeliveryResolver
import com.example.dji_rc_pro.domain.config.TransportIsolationMode
import org.junit.Assert.assertEquals
import org.junit.Test

class Ros2BlePairControlDeliveryUnitTest {

    @Test
    fun `BLE UDP stays on polling to avoid stalled CCCD writes on RC Pro`() {
        val delivery = Ros2BlePairControlDeliveryResolver.resolve(
            transportMode = TransportIsolationMode.BLE_UDP,
            supportsNotify = true
        )

        assertEquals(Ros2BlePairControlDelivery.POLL_ONLY, delivery)
    }

    @Test
    fun `BLE UDP falls back to polling when pair control cannot notify`() {
        val delivery = Ros2BlePairControlDeliveryResolver.resolve(
            transportMode = TransportIsolationMode.BLE_UDP,
            supportsNotify = false
        )

        assertEquals(Ros2BlePairControlDelivery.POLL_ONLY, delivery)
    }

    @Test
    fun `BLE only may use notifications when characteristic supports it`() {
        val delivery = Ros2BlePairControlDeliveryResolver.resolve(
            transportMode = TransportIsolationMode.BLE_ONLY,
            supportsNotify = true
        )

        assertEquals(Ros2BlePairControlDelivery.NOTIFY_WITH_POLL_FALLBACK, delivery)
    }
}
