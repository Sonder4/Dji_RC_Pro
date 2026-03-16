package com.example.dji_rc_pro.protocol

import com.example.dji_rc_pro.domain.input.ControllerManager
import java.nio.ByteBuffer
import java.nio.ByteOrder

/**
 * ROS2 chassis control frame builder for raw controller PID 0x09.
 */
object Ros2ChassisControlPacket {
    private const val HEADER_1: Byte = 0x52
    private const val HEADER_2: Byte = 0x43
    private const val TAIL: Byte = 0xED.toByte()
    private const val MID_PC_MASTER: Byte = 0x01
    private const val PID_CHASSIS_CTRL: Byte = 0x09
    private const val PAYLOAD_LENGTH: Byte = 0x0A
    private const val PAYLOAD_SECTION_LENGTH: Byte = 0x0C
    private const val FRAME_PREFIX_SIZE = 17

    @Volatile
    private var heartCounter: Int = 0

    data class RawInputPayload(
        val leftStickX: Int,
        val leftStickY: Int,
        val rightStickX: Int,
        val rightStickY: Int,
        val buttonMask: Int,
        val leftWheel: Int,
        val rightWheel: Int
    )

    fun fromControllerState(state: ControllerManager.ControllerState): ByteArray {
        return buildFrame(createPayload(state))
    }

    fun createPayload(state: ControllerManager.ControllerState): RawInputPayload {
        return RawInputPayload(
            leftStickX = state.leftStickX,
            leftStickY = state.leftStickY,
            rightStickX = state.rightStickX,
            rightStickY = state.rightStickY,
            buttonMask = state.buttonMask,
            leftWheel = state.leftWheel,
            rightWheel = state.rightWheel
        )
    }

    fun buildFrame(payload: RawInputPayload): ByteArray {
        val frame = ByteBuffer.allocate(FRAME_PREFIX_SIZE).order(ByteOrder.LITTLE_ENDIAN).apply {
            put(HEADER_1)
            put(HEADER_2)
            put(nextHeart())
            put(MID_PC_MASTER)
            put(PAYLOAD_SECTION_LENGTH)
            put(PID_CHASSIS_CTRL)
            put(PAYLOAD_LENGTH)
            put(payload.leftStickX.toByte())
            put(payload.leftStickY.toByte())
            put(payload.rightStickX.toByte())
            put(payload.rightStickY.toByte())
            putInt(payload.buttonMask)
            put(payload.leftWheel.toByte())
            put(payload.rightWheel.toByte())
        }.array()

        val crc = Crc16.compute(frame.copyOfRange(5, 17))
        return frame + byteArrayOf(((crc ushr 8) and 0xFF).toByte(), (crc and 0xFF).toByte(), TAIL)
    }

    internal fun resetHeartCounterForTest() {
        heartCounter = 0
    }

    private fun nextHeart(): Byte {
        val current = heartCounter and 0xFF
        heartCounter = (heartCounter + 1) and 0xFF
        return current.toByte()
    }
}
