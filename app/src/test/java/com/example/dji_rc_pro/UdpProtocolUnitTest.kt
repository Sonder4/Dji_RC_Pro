package com.example.dji_rc_pro

import com.example.dji_rc_pro.domain.input.ControllerManager
import com.example.dji_rc_pro.protocol.Crc16
import com.example.dji_rc_pro.protocol.Ros2ChassisControlPacket
import com.example.dji_rc_pro.util.UdpEndpointConfig
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UdpProtocolUnitTest {

    @Before
    fun setUp() {
        Ros2ChassisControlPacket.resetHeartCounterForTest()
    }

    @Test
    fun `normalize target address trims ipv6 brackets`() {
        assertEquals("2001:db8::1", UdpEndpointConfig.normalizeTargetAddress(" [2001:db8::1] "))
        assertEquals("192.168.1.10", UdpEndpointConfig.normalizeTargetAddress(" 192.168.1.10 "))
    }

    @Test
    fun `parse port rejects out of range`() {
        assertEquals(1387, UdpEndpointConfig.parsePort("1387"))
        assertNull(UdpEndpointConfig.parsePort("0"))
        assertNull(UdpEndpointConfig.parsePort("65536"))
        assertNull(UdpEndpointConfig.parsePort("abc"))
    }

    @Test
    fun `validate accepts ipv6 target and ports`() {
        val result = UdpEndpointConfig.validate("2001:db8::1", "1387", "1346")
        assertTrue(result.isSuccess)
        val config = result.getOrThrow()
        assertEquals("2001:db8::1", config.targetAddress)
        assertEquals(1387, config.targetPort)
        assertEquals(1346, config.localPort)
        assertTrue(config.resolvedAddress.hostAddress?.contains("2001:db8") == true)
    }

    @Test
    fun `wildcard bind address matches family`() {
        val ipv4Bind = UdpEndpointConfig.getWildcardBindAddress(UdpEndpointConfig.resolveAddress("192.168.1.10"))
        val ipv6Bind = UdpEndpointConfig.getWildcardBindAddress(UdpEndpointConfig.resolveAddress("2001:db8::1"))

        assertEquals("0.0.0.0", ipv4Bind.hostAddress)
        assertTrue(ipv6Bind.hostAddress == "0:0:0:0:0:0:0:0" || ipv6Bind.hostAddress == "::")
    }

    @Test
    fun `crc16 matches protocol sample bytes`() {
        val payload = byteArrayOf(0x01, 0x08, 0x00, 0x00, 0xE8.toByte(), 0x03, 0x00, 0x00, 0x00, 0x00)
        assertEquals(0xCF2B, Crc16.compute(payload))
    }

    @Test
    fun `build frame packs raw controller payload and crc`() {
        val state = ControllerManager.ControllerState(
            leftStickX = 0x12,
            leftStickY = 0x34,
            rightStickX = 0x56,
            rightStickY = 0x78,
            buttonMask = 0x89ABCDEF.toInt(),
            leftWheel = 0x9A,
            rightWheel = 0xBC
        )

        val frame = Ros2ChassisControlPacket.fromControllerState(state)

        assertEquals(20, frame.size)
        assertEquals(0x52.toByte(), frame[0])
        assertEquals(0x43.toByte(), frame[1])
        assertEquals(0x00.toByte(), frame[2])
        assertEquals(0x01.toByte(), frame[3])
        assertEquals(0x0C.toByte(), frame[4])
        assertEquals(0x09.toByte(), frame[5])
        assertEquals(0x0A.toByte(), frame[6])
        assertArrayEquals(
            byteArrayOf(
                0x12, 0x34, 0x56, 0x78,
                0xEF.toByte(), 0xCD.toByte(), 0xAB.toByte(), 0x89.toByte(),
                0x9A.toByte(), 0xBC.toByte()
            ),
            frame.copyOfRange(7, 17)
        )

        val expectedCrc = Crc16.compute(frame.copyOfRange(5, 17))
        val actualCrc = ((frame[17].toInt() and 0xFF) shl 8) or (frame[18].toInt() and 0xFF)
        assertEquals(expectedCrc, actualCrc)
        assertEquals(0xED.toByte(), frame[19])
    }

    @Test
    fun `controller state raw values are forwarded without semantic mapping`() {
        val frame = Ros2ChassisControlPacket.fromControllerState(
            ControllerManager.ControllerState(
                leftStickX = 0,
                leftStickY = 255,
                rightStickX = 1,
                rightStickY = 254,
                buttonMask = 0x01020304,
                leftWheel = 5,
                rightWheel = 250
            )
        )

        assertArrayEquals(
            byteArrayOf(
                0x00, 0xFF.toByte(), 0x01, 0xFE.toByte(),
                0x04, 0x03, 0x02, 0x01,
                0x05, 0xFA.toByte()
            ),
            frame.copyOfRange(7, 17)
        )
    }

    @Test
    fun `frame payload uses little endian uint32 button mask ordering`() {
        val frame = Ros2ChassisControlPacket.fromControllerState(
            ControllerManager.ControllerState(
                buttonMask = 0x12345678,
                leftWheel = 0x11,
                rightWheel = 0x22
            )
        )

        assertArrayEquals(
            byteArrayOf(
                0x78, 0x56, 0x34, 0x12,
                0x11, 0x22
            ),
            frame.copyOfRange(11, 17)
        )
    }
}
