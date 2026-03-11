package com.example.dji_rc_pro.protocol

/**
 * CRC16 Utility (Poly: 0x1021, Initial: 0xFFFF)
 * Common in RC protocols.
 */
object Crc16 {
    fun compute(data: ByteArray): Int {
        var crc = 0xFFFF
        for (b in data) {
            crc = ((crc shl 8) and 0xFFFF) xor lut[((crc ushr 8) xor (b.toInt() and 0xFF))]
        }
        return crc
    }

    private val lut: IntArray = IntArray(256).apply {
        for (i in 0..255) {
            var crc = i shl 8
            for (j in 0..7) {
                crc = if ((crc and 0x8000) != 0) {
                    (crc shl 1) xor 0x1021
                } else {
                    crc shl 1
                }
            }
            this[i] = crc and 0xFFFF
        }
    }
}
