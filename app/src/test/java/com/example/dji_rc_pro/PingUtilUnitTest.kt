package com.example.dji_rc_pro

import com.example.dji_rc_pro.util.PingUtil
import org.junit.Assert.assertEquals
import org.junit.Test

class PingUtilUnitTest {

    @Test
    fun `ipv4 ping uses ping binary with ipv4 flag`() {
        val command = PingUtil.buildCommand(
            ip = "10.202.168.216",
            family = PingUtil.IpFamily.IPV4,
            packetCount = 2,
            timeoutSeconds = 3
        )

        assertEquals(
            listOf("/system/bin/ping", "-4", "-c", "2", "-W", "3", "10.202.168.216"),
            command
        )
    }

    @Test
    fun `ipv6 ping uses ping6 binary`() {
        val command = PingUtil.buildCommand(
            ip = "240c:c901:2:2a5c:9dea:871b:31ad:230",
            family = PingUtil.IpFamily.IPV6,
            packetCount = 2,
            timeoutSeconds = 3
        )

        assertEquals(
            listOf("/system/bin/ping6", "-c", "2", "-W", "3", "240c:c901:2:2a5c:9dea:871b:31ad:230"),
            command
        )
    }
}
