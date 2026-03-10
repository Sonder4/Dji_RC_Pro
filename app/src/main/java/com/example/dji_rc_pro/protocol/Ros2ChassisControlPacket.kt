package com.example.dji_rc_pro.protocol

import com.example.dji_rc_pro.domain.input.ControllerManager
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * ROS2 chassis control frame builder for PID 0x01.
 */
object Ros2ChassisControlPacket {
    private const val HEADER_1: Byte = 0x52
    private const val HEADER_2: Byte = 0x43
    private const val TAIL: Byte = 0xED.toByte()
    private const val MID_PC_MASTER: Byte = 0x01
    private const val PID_CHASSIS_CTRL: Byte = 0x01
    private const val PAYLOAD_LENGTH: Byte = 0x08
    private const val PAYLOAD_SECTION_LENGTH: Byte = 0x0A
    private const val SCALE = 1000.0f
    private const val MAX_INT16 = 32767
    private const val MIN_INT16 = -32768
    private const val FRAME_PREFIX_SIZE = 15
    private const val CENTER = 127.0f
    private const val RANGE = 127.0f
    private const val MAX_LINEAR_SPEED_MPS = 4.0f
    private const val MAX_ANGULAR_SPEED_RADPS = (2.0 * PI).toFloat()

    @Volatile
    private var heartCounter: Int = 0

    data class ChassisControlPayload(
        val vMpsMilli: Int,
        val dRadMilli: Int,
        val wRadPsMilli: Int,
        val headingAngleMilli: Int
    )

    fun fromControllerState(state: ControllerManager.ControllerState): ByteArray {
        return buildFrame(createPayload(state))
    }

    fun createPayload(state: ControllerManager.ControllerState): ChassisControlPayload {
        val linearX = normalizeAxis(state.leftStickX) * MAX_LINEAR_SPEED_MPS
        val linearY = normalizeAxis(state.leftStickY) * MAX_LINEAR_SPEED_MPS
        val angularVelocity = normalizeAxis(state.rightStickX) * MAX_ANGULAR_SPEED_RADPS
        val speedMagnitude = sqrt(linearX * linearX + linearY * linearY)
        val direction = if (speedMagnitude < 0.05f) 0f else atan2(linearY, linearX)

        return ChassisControlPayload(
            vMpsMilli = scaleToInt16(speedMagnitude.coerceIn(0f, MAX_LINEAR_SPEED_MPS)),
            dRadMilli = scaleToInt16(direction.coerceIn(-PI.toFloat(), PI.toFloat())),
            wRadPsMilli = scaleToInt16(angularVelocity.coerceIn(-MAX_ANGULAR_SPEED_RADPS, MAX_ANGULAR_SPEED_RADPS)),
            headingAngleMilli = 0
        )
    }

    fun buildFrame(payload: ChassisControlPayload): ByteArray {
        val frame = ByteBuffer.allocate(FRAME_PREFIX_SIZE).order(ByteOrder.LITTLE_ENDIAN).apply {
            put(HEADER_1)
            put(HEADER_2)
            put(nextHeart())
            put(MID_PC_MASTER)
            put(PAYLOAD_SECTION_LENGTH)
            put(PID_CHASSIS_CTRL)
            put(PAYLOAD_LENGTH)
            putShort(payload.vMpsMilli.toShort())
            putShort(payload.dRadMilli.toShort())
            putShort(payload.wRadPsMilli.toShort())
            putShort(payload.headingAngleMilli.toShort())
        }.array()

        val crc = Crc16.compute(frame.copyOfRange(5, 15))
        return frame + byteArrayOf(((crc ushr 8) and 0xFF).toByte(), (crc and 0xFF).toByte(), TAIL)
    }

    internal fun resetHeartCounterForTest() {
        heartCounter = 0
    }

    private fun normalizeAxis(value: Int): Float {
        return ((value - CENTER) / RANGE).coerceIn(-1.0f, 1.0f)
    }

    private fun scaleToInt16(value: Float): Int {
        val scaled = (value * SCALE).roundToInt()
        return scaled.coerceIn(MIN_INT16, MAX_INT16)
    }

    private fun nextHeart(): Byte {
        val current = heartCounter and 0xFF
        heartCounter = (heartCounter + 1) and 0xFF
        return current.toByte()
    }
}
