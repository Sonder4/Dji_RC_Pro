package com.example.dji_rc_pro.domain.usb

import android.util.Log
import com.example.dji_rc_pro.domain.input.ControllerManager
import com.example.dji_rc_pro.domain.input.StickTransformer
import java.nio.ByteBuffer
import java.nio.ByteOrder

private const val TAG = "UsbHidParser"

/**
 * Parses DJI RC HID Protocol as defined in USB_HID_Communication_Format_Analysis.md
 */
object UsbHidProtocolParser {
    private const val HEADER_1 = 0x02.toByte()
    private const val HEADER_2 = 0x0E.toByte()
    private const val NEUTRAL_VALUE = 1024f
    private const val MAX_DELTA = 1024f // Range is roughly 0 to 2048

    fun parse(data: ByteArray, length: Int) {
        if (length < 18) return
        if (data[0] != HEADER_1 || data[1] != HEADER_2) return

        // Log.d(TAG, "HID Raw Data: ${bytesToHex(data.copyOf(length))}")

        val buffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN)
        
        // Skip header (2 bytes)
        buffer.position(2)
        
        // Counter (2 bytes)
        val counter = buffer.short.toInt() and 0xFFFF
        
        // Sticks (INT16, 2 bytes each)
        val leftStickXRaw = buffer.short.toInt()
        val leftStickYRaw = buffer.short.toInt()
        val rightStickXRaw = buffer.short.toInt()
        val rightStickYRaw = buffer.short.toInt()
        val leftWheelRaw = buffer.short.toInt()
        val rightWheelRaw = buffer.short.toInt()
        
        // Buttons (1 byte each)
        val funcButtons = buffer.get().toInt() and 0xFF
        val fiveWayButton = buffer.get().toInt() and 0xFF

        // Update Controller State
        val controller = ControllerManager.instance
        
        // Transform sticks to 0-255
        controller.updateLeftStick(
            StickTransformer.transform(normalize(leftStickXRaw)),
            StickTransformer.transform(normalize(leftStickYRaw))
        )
        controller.updateRightStick(
            StickTransformer.transform(normalize(rightStickXRaw)),
            StickTransformer.transform(normalize(rightStickYRaw))
        )
        controller.updateLeftWheel(StickTransformer.transform(normalize(leftWheelRaw)))
        controller.updateRightWheel(StickTransformer.transform(normalize(rightWheelRaw)))

        // Map Buttons
        // Function Buttons (Byte 17)
        // 0x02: Back -> Bit 0 (A)
        // 0x04: Record -> Bit 4 (L1)
        // 0x08: Photo -> Bit 5 (R1)
        controller.updateButtonMask(0, (funcButtons and 0x02) != 0)
        controller.updateButtonMask(4, (funcButtons and 0x04) != 0)
        controller.updateButtonMask(5, (funcButtons and 0x08) != 0)
        
        // Five-way Button (Byte 18)
        // 0x01: Up -> Bit 10
        // 0x02: Down -> Bit 11
        // 0x04: Left -> Bit 12
        // 0x08: Right -> Bit 13
        // 0x10: Center -> Bit 3 (Y)
        controller.updateButtonMask(10, (fiveWayButton and 0x01) != 0)
        controller.updateButtonMask(11, (fiveWayButton and 0x02) != 0)
        controller.updateButtonMask(12, (fiveWayButton and 0x04) != 0)
        controller.updateButtonMask(13, (fiveWayButton and 0x08) != 0)
        controller.updateButtonMask(3, (fiveWayButton and 0x10) != 0)

        // Debug info
        controller.updateUsbDebug("USB HID: Cnt=$counter, Func=$funcButtons, 5D=$fiveWayButton")
    }

    private fun normalize(raw: Int): Float {
        return (raw - NEUTRAL_VALUE) / MAX_DELTA
    }

    private fun bytesToHex(bytes: ByteArray): String {
        return bytes.joinToString(" ") { String.format("%02X", it) }
    }
}
