package com.example.dji_rc_pro.domain.input

import kotlin.math.roundToInt

/**
 * Transforms float inputs (-1.0 to 1.0) into Byte range (0 to 255).
 */
object StickTransformer {
    private const val MIN_OUT = 0
    private const val MAX_OUT = 255
    private const val CENTER_OUT = 127
    
    // Deadzone to prevent drift
    private const val DEADZONE = 0.05f 

    fun transform(value: Float): Int {
        if (kotlin.math.abs(value) < DEADZONE) {
            return CENTER_OUT
        }

        // Clamp input
        val clamped = value.coerceIn(-1.0f, 1.0f)
        
        // Map -1..1 to 0..255
        // (-1 + 1) * 127.5 = 0
        // (0 + 1) * 127.5 = 127.5
        // (1 + 1) * 127.5 = 255
        return ((clamped + 1.0f) * 127.5f).roundToInt().coerceIn(MIN_OUT, MAX_OUT)
    }
}
