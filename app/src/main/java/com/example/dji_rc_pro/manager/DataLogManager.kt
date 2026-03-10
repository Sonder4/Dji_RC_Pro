package com.example.dji_rc_pro.manager

import com.example.dji_rc_pro.domain.model.BleOperation
import com.example.dji_rc_pro.domain.model.DataLog
import com.example.dji_rc_pro.domain.model.LogLevel
import com.example.dji_rc_pro.domain.model.TransportType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Unified manager for UDP and BLE data transmission logs
 * Replaces UdpDataLogManager to support both transport types
 */
class DataLogManager private constructor() {

    private val _dataLogs = MutableStateFlow<List<DataLog>>(emptyList())
    val dataLogs: StateFlow<List<DataLog>> = _dataLogs.asStateFlow()

    private val logsBuffer = ArrayDeque<DataLog>(MAX_LOGS)

    /**
     * Add a UDP sent data log entry
     */
    fun addUdpSentLog(data: ByteArray) {
        addLog(DataLog.createDataLog(
            transport = TransportType.UDP,
            data = data,
            isSent = true
        ))
    }

    /**
     * Add a UDP received data log entry
     */
    fun addUdpReceivedLog(data: ByteArray) {
        addLog(DataLog.createDataLog(
            transport = TransportType.UDP,
            data = data,
            isSent = false
        ))
    }

    /**
     * Add a BLE sent data log entry
     */
    fun addBleSentLog(data: ByteArray, macAddress: String? = null) {
        addLog(DataLog.createDataLog(
            transport = TransportType.BLE,
            data = data,
            isSent = true,
            macAddress = macAddress
        ))
    }

    /**
     * Add a BLE received data log entry
     */
    fun addBleReceivedLog(data: ByteArray, macAddress: String? = null) {
        addLog(DataLog.createDataLog(
            transport = TransportType.BLE,
            data = data,
            isSent = false,
            macAddress = macAddress
        ))
    }

    /**
     * Add a BLE debug log
     */
    fun addBleDebugLog(
        operation: BleOperation,
        message: String,
        level: LogLevel = LogLevel.DEBUG,
        macAddress: String? = null,
        rssi: Int? = null,
        status: Int? = null,
        errorCode: Int? = null,
        data: ByteArray? = null
    ) {
        addLog(DataLog.createBleDebugLog(
            operation = operation,
            message = message,
            level = level,
            macAddress = macAddress,
            rssi = rssi,
            status = status,
            errorCode = errorCode,
            data = data
        ))
    }

    /**
     * Add a UDP debug log
     */
    fun addUdpDebugLog(
        message: String,
        level: LogLevel = LogLevel.DEBUG,
        data: ByteArray? = null
    ) {
        addLog(DataLog.createUdpDebugLog(
            message = message,
            level = level,
            data = data
        ))
    }

    /**
     * Add a generic log entry
     */
    fun addLog(log: DataLog) {
        synchronized(logsBuffer) {
            if (logsBuffer.size >= MAX_LOGS) {
                logsBuffer.removeFirst()
            }
            logsBuffer.addLast(log)
            _dataLogs.value = logsBuffer.toList()
        }
    }

    /**
     * Clear all logs
     */
    fun clearLogs() {
        synchronized(logsBuffer) {
            logsBuffer.clear()
            _dataLogs.value = emptyList()
        }
    }

    /**
     * Get current log count
     */
    fun getLogCount(): Int = logsBuffer.size

    /**
     * Get logs filtered by transport type
     */
    fun getLogsByTransport(transport: TransportType): List<DataLog> {
        return logsBuffer.filter { it.transport == transport }
    }

    /**
     * Get logs filtered by log level
     */
    fun getLogsByLevel(level: LogLevel): List<DataLog> {
        return logsBuffer.filter { it.logLevel.ordinal >= level.ordinal }
    }

    /**
     * Get BLE operation logs
     */
    fun getBleOperationLogs(operation: BleOperation): List<DataLog> {
        return logsBuffer.filter { 
            it.transport == TransportType.BLE && it.bleOperation == operation 
        }
    }

    /**
     * Get logs for specific MAC address
     */
    fun getLogsByMacAddress(macAddress: String): List<DataLog> {
        return logsBuffer.filter { it.macAddress == macAddress }
    }

    /**
     * Get the most recent logs (limited count)
     */
    fun getRecentLogs(count: Int): List<DataLog> {
        return logsBuffer.takeLast(count.coerceAtMost(logsBuffer.size))
    }

    companion object {
        private const val MAX_LOGS = 1000

        @Volatile
        private var instance: DataLogManager? = null

        fun getInstance(): DataLogManager {
            return instance ?: synchronized(this) {
                instance ?: DataLogManager().also { instance = it }
            }
        }
    }
}

/**
 * Extension functions for convenient logging
 */
fun DataLogManager.logBleScan(message: String, macAddress: String? = null, level: LogLevel = LogLevel.DEBUG) {
    addBleDebugLog(BleOperation.SCAN, message, level, macAddress)
}

fun DataLogManager.logBleConnect(message: String, macAddress: String? = null, level: LogLevel = LogLevel.INFO) {
    addBleDebugLog(BleOperation.CONNECT, message, level, macAddress)
}

fun DataLogManager.logBleDisconnect(message: String, macAddress: String? = null, level: LogLevel = LogLevel.INFO) {
    addBleDebugLog(BleOperation.DISCONNECT, message, level, macAddress)
}

fun DataLogManager.logBleServiceDiscover(message: String, macAddress: String? = null, level: LogLevel = LogLevel.DEBUG) {
    addBleDebugLog(BleOperation.SERVICE_DISCOVER, message, level, macAddress)
}

fun DataLogManager.logBleRssi(rssi: Int, macAddress: String? = null) {
    addBleDebugLog(BleOperation.RSSI_READ, "RSSI: ${rssi}dBm", LogLevel.DEBUG, macAddress, rssi)
}

fun DataLogManager.logBleError(message: String, macAddress: String? = null, errorCode: Int? = null) {
    addBleDebugLog(BleOperation.ERROR, message, LogLevel.ERROR, macAddress, errorCode = errorCode)
}

fun DataLogManager.logBleStateChange(message: String, macAddress: String? = null, status: Int? = null) {
    addBleDebugLog(BleOperation.STATE_CHANGE, message, LogLevel.INFO, macAddress, status = status)
}
