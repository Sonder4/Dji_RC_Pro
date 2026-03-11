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
        assertEquals("198.51.100.10", UdpEndpointConfig.normalizeTargetAddress(" 198.51.100.10 "))
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
        val ipv4Bind = UdpEndpointConfig.getWildcardBindAddress(UdpEndpointConfig.resolveAddress("198.51.100.10"))
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
    fun `build frame packs ros2 chassis payload and crc`() {
        val payload = Ros2ChassisControlPacket.ChassisControlPayload(
            vMpsMilli = 1000,
            dRadMilli = 0,
            wRadPsMilli = 250,
            headingAngleMilli = 0
        )

        val frame = Ros2ChassisControlPacket.buildFrame(payload)

        assertEquals(18, frame.size)
        assertEquals(0x52.toByte(), frame[0])
        assertEquals(0x43.toByte(), frame[1])
        assertEquals(0x00.toByte(), frame[2])
        assertEquals(0x01.toByte(), frame[3])
        assertEquals(0x0A.toByte(), frame[4])
        assertEquals(0x01.toByte(), frame[5])
        assertEquals(0x08.toByte(), frame[6])
        assertEquals(0xE8.toByte(), frame[7])
        assertEquals(0x03.toByte(), frame[8])
        assertEquals(0xFA.toByte(), frame[11])
        assertEquals(0x00.toByte(), frame[12])
        assertEquals(0x00.toByte(), frame[13])
        assertEquals(0x00.toByte(), frame[14])

        val expectedCrc = Crc16.compute(frame.copyOfRange(5, 15))
        val actualCrc = ((frame[15].toInt() and 0xFF) shl 8) or (frame[16].toInt() and 0xFF)
        assertEquals(expectedCrc, actualCrc)
        assertEquals(0xED.toByte(), frame[17])
    }

    @Test
    fun `controller state maps to ros2 velocity vector semantics`() {
        val neutral = Ros2ChassisControlPacket.createPayload(ControllerManager.ControllerState())
        assertEquals(0, neutral.vMpsMilli)
        assertEquals(0, neutral.dRadMilli)
        assertEquals(0, neutral.wRadPsMilli)
        assertEquals(0, neutral.headingAngleMilli)

        val forward = Ros2ChassisControlPacket.createPayload(
            ControllerManager.ControllerState(leftStickX = 255)
        )
        assertEquals(4000, forward.vMpsMilli)
        assertEquals(0, forward.dRadMilli)

        val left = Ros2ChassisControlPacket.createPayload(
            ControllerManager.ControllerState(leftStickY = 255)
        )
        assertEquals(4000, left.vMpsMilli)
        assertEquals(1571, left.dRadMilli)

        val reverse = Ros2ChassisControlPacket.createPayload(
            ControllerManager.ControllerState(leftStickX = 0)
        )
        assertEquals(4000, reverse.vMpsMilli)
        assertEquals(3142, reverse.dRadMilli)

        val diagonal = Ros2ChassisControlPacket.createPayload(
            ControllerManager.ControllerState(leftStickX = 255, leftStickY = 255)
        )
        assertEquals(4000, diagonal.vMpsMilli)
        assertEquals(785, diagonal.dRadMilli)

        val rightTurn = Ros2ChassisControlPacket.createPayload(
            ControllerManager.ControllerState(rightStickX = 255)
        )
        assertEquals(6283, rightTurn.wRadPsMilli)

        val leftTurn = Ros2ChassisControlPacket.createPayload(
            ControllerManager.ControllerState(rightStickX = 0)
        )
        assertEquals(-6283, leftTurn.wRadPsMilli)
    }

    @Test
    fun `frame payload uses little endian int16 ordering`() {
        val payload = Ros2ChassisControlPacket.ChassisControlPayload(
            vMpsMilli = 0x1234,
            dRadMilli = 0x2345,
            wRadPsMilli = -2,
            headingAngleMilli = 0x3456
        )

        val frame = Ros2ChassisControlPacket.buildFrame(payload)
        assertArrayEquals(
            byteArrayOf(
                0x34, 0x12,
                0x45, 0x23,
                0xFE.toByte(), 0xFF.toByte(),
                0x56, 0x34
            ),
            frame.copyOfRange(7, 15)
        )
    }
}
