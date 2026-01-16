package com.example.dji_rc_pro.protocol

import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Control Packet Structure
 * Header: 0xAA
 * Payload: LX, LY, RX, RY (0-255)
 * Buttons: Bitmask (2 bytes? or 1 byte?) - Assuming 2 bytes for Xbox style compatibility
 * CRC: 2 bytes
 * Total: 1 + 4 + 2 + 2 = 9 bytes
 */
data class ControlPacket(
    val leftStickX: Int, // 0-255
    val leftStickY: Int, // 0-255
    val rightStickX: Int, // 0-255
    val rightStickY: Int, // 0-255
    val buttonMask: Int // 16-bit mask
) {
    fun toByteArray(): ByteArray {
        val buffer = ByteBuffer.allocate(9)
        buffer.order(ByteOrder.LITTLE_ENDIAN) // Xbox usually LE, but Network often BE. Let's use LE for now.

        buffer.put(0xAA.toByte())
        buffer.put(leftStickX.toByte())
        buffer.put(leftStickY.toByte())
        buffer.put(rightStickX.toByte())
        buffer.put(rightStickY.toByte())
        buffer.putShort(buttonMask.toShort())

        // Calculate CRC on first 7 bytes
        val payload = buffer.array().copyOfRange(0, 7)
        val crc = Crc16.compute(payload)
        
        buffer.putShort(crc.toShort())

        return buffer.array()
    }
}
