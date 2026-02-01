package com.example.dji_rc_pro.domain.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Represents a single UDP data log entry
 */
data class UdpDataLog(
    val timestamp: Long = System.currentTimeMillis(),
    val data: ByteArray,
    val isSent: Boolean
) {
    /**
     * Format timestamp as HH:mm:ss.SSS
     */
    fun formatTimestamp(): String {
        val sdf = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }

    /**
     * Convert data bytes to HEX string format
     */
    fun toHexString(): String {
        return data.joinToString(" ") { byte ->
            String.format("%02X", byte.toInt() and 0xFF)
        }
    }

    /**
     * Get formatted log line: [HH:mm:ss.SSS] TX/RX: HH HH HH...
     */
    fun formatLogLine(): String {
        val direction = if (isSent) "TX" else "RX"
        return "[${formatTimestamp()}] $direction: ${toHexString()}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UdpDataLog

        if (timestamp != other.timestamp) return false
        if (isSent != other.isSent) return false
        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + data.contentHashCode()
        result = 31 * result + isSent.hashCode()
        return result
    }
}
