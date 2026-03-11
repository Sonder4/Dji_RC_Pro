package com.example.dji_rc_pro.domain.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Log level for data logs
 */
enum class LogLevel {
    VERBOSE,
    DEBUG,
    INFO,
    WARN,
    ERROR
}

/**
 * Transport type for data transmission
 */
enum class TransportType {
    UDP,
    BLE
}

/**
 * Operation type for BLE debugging
 */
enum class BleOperation {
    SCAN,
    CONNECT,
    DISCONNECT,
    SERVICE_DISCOVER,
    CHARACTERISTIC_READ,
    CHARACTERISTIC_WRITE,
    CHARACTERISTIC_CHANGED,
    RSSI_READ,
    DATA_SEND,
    DATA_RECEIVE,
    HEARTBEAT,
    ERROR,
    STATE_CHANGE
}

/**
 * Represents a single data log entry for UDP or BLE transmission
 * Supports both data transmission logs and debug message logs
 */
data class DataLog(
    val timestamp: Long = System.currentTimeMillis(),
    val transport: TransportType,
    val data: ByteArray? = null,
    val message: String? = null,
    val logLevel: LogLevel = LogLevel.INFO,
    val isSent: Boolean? = null,
    val bleOperation: BleOperation? = null,
    val macAddress: String? = null,
    val rssi: Int? = null,
    val status: Int? = null,
    val errorCode: Int? = null
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
        return data?.joinToString(" ") { byte ->
            String.format("%02X", byte.toInt() and 0xFF)
        } ?: ""
    }

    /**
     * Get formatted log line for display
     */
    fun formatLogLine(): String {
        val sb = StringBuilder()
        sb.append("[${formatTimestamp()}]")
        sb.append(" [${transport.name}]")
        sb.append(" [${logLevel.name}]")

        bleOperation?.let {
            sb.append(" [${it.name}]")
        }

        macAddress?.let {
            sb.append(" [MAC:$it]")
        }

        rssi?.let {
            sb.append(" [RSSI:${it}dBm]")
        }

        status?.let {
            sb.append(" [Status:$it]")
        }

        errorCode?.let {
            sb.append(" [Error:$it]")
        }

        isSent?.let {
            sb.append(if (it) " [TX]" else " [RX]")
        }

        message?.let {
            sb.append(" $it")
        }

        data?.let {
            if (it.isNotEmpty()) {
                sb.append(" Data: ${toHexString()}")
            }
        }

        return sb.toString()
    }

    /**
     * Get short display text for UI list
     */
    fun getDisplayText(): String {
        val direction = when {
            isSent == true -> "TX"
            isSent == false -> "RX"
            else -> logLevel.name.take(1)
        }
        val dataStr = data?.let { " (${it.size} bytes)" } ?: ""
        val msgStr = message?.let { ": $it" } ?: ""
        return "[$direction]${dataStr}${msgStr}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DataLog

        if (timestamp != other.timestamp) return false
        if (transport != other.transport) return false
        if (logLevel != other.logLevel) return false
        if (isSent != other.isSent) return false
        if (bleOperation != other.bleOperation) return false
        if (macAddress != other.macAddress) return false
        if (rssi != other.rssi) return false
        if (status != other.status) return false
        if (errorCode != other.errorCode) return false
        if (data != null) {
            if (other.data == null) return false
            if (!data.contentEquals(other.data)) return false
        } else if (other.data != null) return false
        if (message != other.message) return false

        return true
    }

    override fun hashCode(): Int {
        var result = timestamp.hashCode()
        result = 31 * result + transport.hashCode()
        result = 31 * result + (data?.contentHashCode() ?: 0)
        result = 31 * result + (message?.hashCode() ?: 0)
        result = 31 * result + logLevel.hashCode()
        result = 31 * result + (isSent?.hashCode() ?: 0)
        result = 31 * result + (bleOperation?.hashCode() ?: 0)
        result = 31 * result + (macAddress?.hashCode() ?: 0)
        result = 31 * result + (rssi ?: 0)
        result = 31 * result + (status ?: 0)
        result = 31 * result + (errorCode ?: 0)
        return result
    }

    companion object {
        /**
         * Create a data transmission log
         */
        fun createDataLog(
            transport: TransportType,
            data: ByteArray,
            isSent: Boolean,
            macAddress: String? = null
        ): DataLog {
            return DataLog(
                transport = transport,
                data = data.copyOf(),
                isSent = isSent,
                logLevel = LogLevel.INFO,
                macAddress = macAddress
            )
        }

        /**
         * Create a BLE debug log
         */
        fun createBleDebugLog(
            operation: BleOperation,
            message: String,
            level: LogLevel = LogLevel.DEBUG,
            macAddress: String? = null,
            rssi: Int? = null,
            status: Int? = null,
            errorCode: Int? = null,
            data: ByteArray? = null
        ): DataLog {
            return DataLog(
                transport = TransportType.BLE,
                bleOperation = operation,
                message = message,
                logLevel = level,
                macAddress = macAddress,
                rssi = rssi,
                status = status,
                errorCode = errorCode,
                data = data?.copyOf()
            )
        }

        /**
         * Create a UDP debug log
         */
        fun createUdpDebugLog(
            message: String,
            level: LogLevel = LogLevel.DEBUG,
            data: ByteArray? = null
        ): DataLog {
            return DataLog(
                transport = TransportType.UDP,
                message = message,
                logLevel = level,
                data = data?.copyOf()
            )
        }
    }
}
