package com.example.dji_rc_pro.manager

import com.example.dji_rc_pro.domain.model.UdpDataLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Manager for UDP data transmission logs
 * Stores recent send/receive data in HEX format
 */
class UdpDataLogManager {

    private val _dataLogs = MutableStateFlow<List<UdpDataLog>>(emptyList())
    val dataLogs: StateFlow<List<UdpDataLog>> = _dataLogs.asStateFlow()

    private val logsBuffer = ArrayDeque<UdpDataLog>(MAX_LOGS)

    /**
     * Add a sent data log entry
     */
    fun addSentLog(data: ByteArray) {
        addLog(UdpDataLog(data = data.copyOf(), isSent = true))
    }

    /**
     * Add a received data log entry
     */
    fun addReceivedLog(data: ByteArray) {
        addLog(UdpDataLog(data = data.copyOf(), isSent = false))
    }

    private fun addLog(log: UdpDataLog) {
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

    companion object {
        private const val MAX_LOGS = 1000

        @Volatile
        private var instance: UdpDataLogManager? = null

        fun getInstance(): UdpDataLogManager {
            return instance ?: synchronized(this) {
                instance ?: UdpDataLogManager().also { instance = it }
            }
        }
    }
}
