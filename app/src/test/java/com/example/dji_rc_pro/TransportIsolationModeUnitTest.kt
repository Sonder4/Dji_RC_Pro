package com.example.dji_rc_pro

import com.example.dji_rc_pro.domain.config.TransportIsolationMode
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TransportIsolationModeUnitTest {

    @Test
    fun `udp only disables ble and keeps wifi discovery`() {
        val mode = TransportIsolationMode.UDP_ONLY

        assertTrue(mode.allowsUdp)
        assertFalse(mode.allowsBle)
        assertTrue(mode.allowsWifiDiscovery)
        assertFalse(mode.shouldAutoStartUdpFromBle)
    }

    @Test
    fun `ble only disables udp and wifi discovery`() {
        val mode = TransportIsolationMode.BLE_ONLY

        assertFalse(mode.allowsUdp)
        assertTrue(mode.allowsBle)
        assertFalse(mode.allowsWifiDiscovery)
        assertFalse(mode.shouldAutoStartUdpFromBle)
    }

    @Test
    fun `ble udp keeps both transports and auto starts udp from ble`() {
        val mode = TransportIsolationMode.BLE_UDP

        assertTrue(mode.allowsUdp)
        assertTrue(mode.allowsBle)
        assertTrue(mode.allowsWifiDiscovery)
        assertTrue(mode.shouldAutoStartUdpFromBle)
        assertEquals(mode, TransportIsolationMode.fromStorageValue(mode.storageValue))
    }
}
