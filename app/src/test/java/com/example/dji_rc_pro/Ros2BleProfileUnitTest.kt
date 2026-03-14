package com.example.dji_rc_pro

import com.example.dji_rc_pro.domain.ble.Ros2BleProfile
import com.example.dji_rc_pro.domain.discovery.DiscoveryProtocol
import org.junit.Assert.assertEquals
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
}
