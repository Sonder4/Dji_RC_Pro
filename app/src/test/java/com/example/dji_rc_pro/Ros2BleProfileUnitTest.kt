package com.example.dji_rc_pro

import com.example.dji_rc_pro.domain.ble.Ros2BleProfile
import com.example.dji_rc_pro.domain.discovery.DiscoveryProtocol
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class Ros2BleProfileUnitTest {

    @Test
    fun `compact status payload updates host and client ip addresses`() {
        val payload = Ros2BleProfile.buildKeyValuePayload(
            linkedMapOf(
                "v" to "1",
                "t" to "s",
                "h" to "rc26-host",
                "s" to "session-1",
                "f" to "6",
                "a" to "240c:c901:2:36d::200",
                "p" to "1387",
                "u" to "1",
                "b" to "1",
                "k" to "1",
                "d" to "1",
                "host_ipv4" to "10.202.168.216",
                "host_ipv6" to "240c:c901:2:36d::200",
                "c4" to "10.202.200.141",
                "c6" to "240c:c901:2:36d::100"
            )
        )

        val session = Ros2BleProfile.parseStatus(payload)

        requireNotNull(session)
        assertEquals("10.202.168.216", session.ipv4Address)
        assertEquals("240c:c901:2:36d::200", session.ipv6Address)
        assertEquals("10.202.200.141", session.clientIpv4Address)
        assertEquals("240c:c901:2:36d::100", session.clientIpv6Address)
        assertEquals(DiscoveryProtocol.AddressFamily.IPV6, session.selectedFamily)
        assertEquals("240c:c901:2:36d::200", session.selectedAddress)
        assertTrue(session.paired)
        assertTrue(session.udpReady)
    }

    @Test
    fun `compact probe echo is recognized for polling retry`() {
        val probeState = Ros2BleProfile.PairProbeState(
            clientId = "client-1",
            clientNonce = "nonce-1",
            pairCode = "NCURC2026",
            clientIpv4 = "10.202.200.141",
            clientIpv6 = "240c:c901:2:36d::100"
        )

        val payload = Ros2BleProfile.buildKeyValuePayload(
            linkedMapOf(
                "v" to "1",
                "t" to "p",
                "c" to probeState.clientId,
                "n" to probeState.clientNonce,
                "x" to "3",
                "4" to probeState.clientIpv4,
                "6" to probeState.clientIpv6,
                "m" to "proof"
            )
        )

        assertTrue(Ros2BleProfile.isPairProbeEcho(payload, probeState))
        assertFalse(
            Ros2BleProfile.isPairProbeEcho(
                payload,
                probeState.copy(clientNonce = "nonce-2")
            )
        )
    }

    @Test
    fun `compact pair request echo is recognized for polling retry`() {
        val context = Ros2BleProfile.PendingPairContext(
            hostId = "rc26-host",
            hostNonce = "host-nonce",
            clientId = "client-1",
            clientNonce = "nonce-1",
            pairCode = "NCURC2026"
        )
        val host = DiscoveryProtocol.DiscoveredHost(
            hostId = context.hostId,
            hostName = "rc26-host",
            controlPort = 1387,
            discoveryPort = DiscoveryProtocol.DEFAULT_DISCOVERY_PORT,
            ready = true,
            busy = false,
            leaseMs = 5000L,
            hostNonce = context.hostNonce,
            ipv4Address = "10.202.168.216",
            ipv6Address = "240c:c901:2:36d::200",
            selectedFamily = DiscoveryProtocol.AddressFamily.IPV6,
            lastSeenAtMs = 0L
        )
        val probeState = Ros2BleProfile.PairProbeState(
            clientId = context.clientId,
            clientNonce = context.clientNonce,
            pairCode = context.pairCode
        )

        val payload = Ros2BleProfile.buildKeyValuePayload(
            linkedMapOf(
                "v" to "1",
                "t" to "r",
                "h" to host.hostId,
                "c" to probeState.clientId,
                "n" to probeState.clientNonce,
                "o" to host.hostNonce,
                "p" to host.controlPort.toString(),
                "m" to "proof"
            )
        )

        assertTrue(Ros2BleProfile.isPairRequestEcho(payload, context))
        assertFalse(
            Ros2BleProfile.isPairRequestEcho(
                payload,
                context.copy(hostNonce = "other-host-nonce")
            )
        )
    }
}
