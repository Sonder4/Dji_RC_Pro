package com.example.dji_rc_pro.protocol

import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * Control Packet Structure
 * Header: 0xAA (1 byte)
 * Payload: LX, LY, RX, RY (4 bytes, 0-255)
 * Buttons: Bitmask (2 bytes)
 * CRC: 2 bytes
 * Total: 9 bytes
 */
data class ControlPacket(
    val leftStickX: Int = CENTER_VALUE,   // 0-255
    val leftStickY: Int = CENTER_VALUE,   // 0-255
    val rightStickX: Int = CENTER_VALUE,  // 0-255
    val rightStickY: Int = CENTER_VALUE,  // 0-255
    val buttonMask: Int = 0               // 16-bit mask
) {
    fun toByteArray(): ByteArray = ByteBuffer.allocate(PACKET_SIZE).apply {
        order(ByteOrder.LITTLE_ENDIAN)
        put(HEADER)
        put(leftStickX.toByte())
        put(leftStickY.toByte())
        put(rightStickX.toByte())
        put(rightStickY.toByte())
        putShort(buttonMask.toShort())
        putShort(Crc16.compute(array().copyOfRange(0, HEADER_SIZE + PAYLOAD_SIZE)).toShort())
    }.array()

    companion object {
        const val HEADER: Byte = 0xAA.toByte()
        const val PACKET_SIZE = 9
        const val HEADER_SIZE = 1
        const val PAYLOAD_SIZE = 6  // 4 sticks + 2 buttons
        const val CRC_SIZE = 2
        const val CENTER_VALUE = 127

        /**
         * Parse byte array to ControlPacket
         * Returns null if data is invalid or CRC check fails
         */
        fun fromByteArray(data: ByteArray): ControlPacket? {
            if (data.size != PACKET_SIZE || data[0] != HEADER) return null

            val payload = data.copyOfRange(0, HEADER_SIZE + PAYLOAD_SIZE)
            val receivedCrc = ((data[7].toInt() and 0xFF) shl 8) or (data[8].toInt() and 0xFF)

            if (Crc16.compute(payload) != receivedCrc) return null

            return ControlPacket(
                leftStickX = data[1].toInt() and 0xFF,
                leftStickY = data[2].toInt() and 0xFF,
                rightStickX = data[3].toInt() and 0xFF,
                rightStickY = data[4].toInt() and 0xFF,
                buttonMask = ((data[5].toInt() and 0xFF) shl 8) or (data[6].toInt() and 0xFF)
            )
        }

        /**
         * Create packet from current controller state
         * Convenience method for services
         */
        fun fromControllerState(
            leftStickX: Int,
            leftStickY: Int,
            rightStickX: Int,
            rightStickY: Int,
            buttonMask: Int
        ): ByteArray = ControlPacket(
            leftStickX = leftStickX,
            leftStickY = leftStickY,
            rightStickX = rightStickX,
            rightStickY = rightStickY,
            buttonMask = buttonMask
        ).toByteArray()
    }
}
