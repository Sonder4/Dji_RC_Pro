package com.example.dji_rc_pro

import com.example.dji_rc_pro.domain.config.TransportIsolationMode
import com.example.dji_rc_pro.domain.diagnostics.AddressSyncPolicy
import com.example.dji_rc_pro.domain.diagnostics.BleLinkEvent
import com.example.dji_rc_pro.domain.diagnostics.BleLinkPhase
import com.example.dji_rc_pro.domain.diagnostics.BleLinkStateMachine
import com.example.dji_rc_pro.domain.diagnostics.BleLinkStatusResolver
import com.example.dji_rc_pro.domain.discovery.DiscoveryProtocol
import com.example.dji_rc_pro.domain.diagnostics.UdpLinkState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BleDiagnosticsUnitTest {

    @Test
    fun `ble udp enters udp verification after address sync`() {
        val connected = BleLinkStateMachine.transition(
            current = BleLinkPhase.CONNECTING,
            event = BleLinkEvent.GATT_CONNECTED,
            transportMode = TransportIsolationMode.BLE_UDP
        )

        val synced = BleLinkStateMachine.transition(
            current = connected,
            event = BleLinkEvent.ADDRESSES_SYNCED,
            transportMode = TransportIsolationMode.BLE_UDP
        )

        assertEquals(BleLinkPhase.VERIFYING_UDP, synced)
    }

    @Test
    fun `udp failure falls back to ble when ble session is still alive`() {
        val fallback = BleLinkStateMachine.transition(
            current = BleLinkPhase.VERIFYING_UDP,
            event = BleLinkEvent.UDP_FAILED,
            transportMode = TransportIsolationMode.BLE_UDP
        )

        assertEquals(BleLinkPhase.BLE_FALLBACK_ACTIVE, fallback)
        assertEquals("已回退到蓝牙", fallback.label)
    }

    @Test
    fun `ble only becomes communicating after address sync`() {
        val connected = BleLinkStateMachine.transition(
            current = BleLinkPhase.CONNECTING,
            event = BleLinkEvent.GATT_CONNECTED,
            transportMode = TransportIsolationMode.BLE_ONLY
        )

        val synced = BleLinkStateMachine.transition(
            current = connected,
            event = BleLinkEvent.ADDRESSES_SYNCED,
            transportMode = TransportIsolationMode.BLE_ONLY
        )

        assertEquals(BleLinkPhase.COMMUNICATING, synced)
        assertEquals("通信正常", synced.label)
    }

    @Test
    fun `udp ready promotes ble udp verification to communicating for display`() {
        val normalized = BleLinkStatusResolver.normalize(
            current = BleLinkPhase.VERIFYING_UDP,
            udpState = UdpLinkState.READY,
            bleConnected = true
        )

        assertEquals(BleLinkPhase.COMMUNICATING, normalized)
    }

    @Test
    fun `stale pairing clears only after failed probe with ble connected`() {
        assertTrue(
            AddressSyncPolicy.shouldClearStalePairing(
                staleAddress = "240c:c901:2:3806::1",
                peerIpv4 = "10.202.168.216",
                peerIpv6 = "240c:c901:2:2a5c::1",
                probeSucceeded = false,
                bleConnected = true
            )
        )

        assertFalse(
            AddressSyncPolicy.shouldClearStalePairing(
                staleAddress = "240c:c901:2:3806::1",
                peerIpv4 = "10.202.168.216",
                peerIpv6 = "240c:c901:2:2a5c::1",
                probeSucceeded = true,
                bleConnected = true
            )
        )

        assertFalse(
            AddressSyncPolicy.shouldClearStalePairing(
                staleAddress = "240c:c901:2:3806::1",
                peerIpv4 = "10.202.168.216",
                peerIpv6 = "240c:c901:2:2a5c::1",
                probeSucceeded = false,
                bleConnected = false
            )
        )
    }

    @Test
    fun `target address selection honors preferred family`() {
        assertEquals(
            "240c:c901:2:2a5c::1",
            AddressSyncPolicy.chooseTargetAddress(
                peerIpv4 = "10.202.168.216",
                peerIpv6 = "240c:c901:2:2a5c::1",
                preferredFamily = DiscoveryProtocol.AddressFamily.IPV6
            )
        )

        assertEquals(
            "10.202.168.216",
            AddressSyncPolicy.chooseTargetAddress(
                peerIpv4 = "10.202.168.216",
                peerIpv6 = null,
                preferredFamily = DiscoveryProtocol.AddressFamily.IPV6
            )
        )
    }
}
