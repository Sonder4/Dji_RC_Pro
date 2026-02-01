package com.example.dji_rc_pro.util

import java.time.Instant

data class ConnectionState(
    val udpState: TransportConnectionState = TransportConnectionState(),
    val bleState: TransportConnectionState = TransportConnectionState(),
    val overallState: OverallConnectionState = OverallConnectionState.DISCONNECTED,
    val lastActivityTime: Long = System.currentTimeMillis(),
    val errorCode: ErrorCode? = null,
    val reconnecting: Boolean = false,
    val reconnectAttempt: Int = 0
) {
    fun isConnected(): Boolean {
        return overallState == OverallConnectionState.CONNECTED
    }

    fun isAnyConnected(): Boolean {
        return udpState.isConnected || bleState.isConnected
    }

    fun getConnectedTransports(): List<String> {
        val transports = mutableListOf<String>()
        if (udpState.isConnected) transports.add("UDP")
        if (bleState.isConnected) transports.add("BLE")
        return transports
    }

    fun getUptimeMillis(): Long {
        val connectedTransport = when {
            udpState.isConnected -> udpState
            bleState.isConnected -> bleState
            else -> return 0L
        }
        return System.currentTimeMillis() - connectedTransport.connectedSince
    }
}

data class TransportConnectionState(
    val isConnected: Boolean = false,
    val isConnecting: Boolean = false,
    val connectedSince: Long = 0L,
    val lastActivityTime: Long = System.currentTimeMillis(),
    val bytesSent: Long = 0L,
    val bytesReceived: Long = 0L,
    val packetsSent: Long = 0L,
    val packetsReceived: Long = 0L,
    val errorCount: Int = 0,
    val lastError: ErrorCode? = null
) {
    companion object {
        const val DISCONNECTED = 0
        const val CONNECTING = 1
        const val CONNECTED = 2
        const val ERROR = 3
    }
}

enum class OverallConnectionState {
    DISCONNECTED,
    CONNECTING,
    CONNECTED,
    PARTIAL,  // 部分连接
    ERROR,
    RECONNECTING
}

data class HeartbeatState(
    val isActive: Boolean = false,
    val lastSentTime: Long = 0L,
    val lastReceivedTime: Long = 0L,
    val missedCount: Int = 0,
    val timeoutCount: Int = 0,
    val intervalMs: Long = 1000L,
    val maxMissed: Int = 3
) {
    val isHealthy: Boolean
        get() = missedCount < maxMissed && !isTimedOut

    val isTimedOut: Boolean
        get() = System.currentTimeMillis() - lastReceivedTime > intervalMs * maxMissed

    fun markHeartbeatSent(): HeartbeatState =
        copy(lastSentTime = System.currentTimeMillis())

    fun markHeartbeatReceived(): HeartbeatState =
        copy(lastReceivedTime = System.currentTimeMillis(), missedCount = 0)

    fun incrementMissed(): HeartbeatState =
        copy(missedCount = missedCount + 1)

    fun incrementTimeout(): HeartbeatState =
        copy(timeoutCount = timeoutCount + 1)

    fun reset(): HeartbeatState =
        copy(
            isActive = false,
            missedCount = 0,
            timeoutCount = 0,
            lastSentTime = 0L,
            lastReceivedTime = 0L
        )
}

data class ReconnectState(
    val isReconnecting: Boolean = false,
    val attemptCount: Int = 0,
    val maxAttempts: Int = 10,
    val baseDelayMs: Long = 1000L,
    val maxDelayMs: Long = 30000L,
    val exponentialBase: Double = 2.0,
    val lastAttemptTime: Long = 0L,
    val nextRetryTime: Long = 0L,
    val successCount: Int = 0,
    val failureCount: Int = 0
) {
    val shouldRetry: Boolean
        get() = isReconnecting && attemptCount < maxAttempts && System.currentTimeMillis() >= nextRetryTime

    val currentDelayMs: Long
        get() {
            val delay = (baseDelayMs * Math.pow(exponentialBase, (attemptCount - 1).toDouble())).toLong()
            return delay.coerceIn(baseDelayMs, maxDelayMs)
        }

    fun startReconnect(): ReconnectState =
        copy(
            isReconnecting = true,
            attemptCount = 0,
            lastAttemptTime = 0L,
            nextRetryTime = System.currentTimeMillis()
        )

    fun recordAttempt(): ReconnectState {
        val newAttemptCount = attemptCount + 1
        val newLastAttemptTime = System.currentTimeMillis()
        val newNextRetryTime = newLastAttemptTime + currentDelayMs
        return copy(
            attemptCount = newAttemptCount,
            lastAttemptTime = newLastAttemptTime,
            nextRetryTime = newNextRetryTime
        )
    }

    fun recordSuccess(): ReconnectState =
        copy(
            successCount = successCount + 1,
            isReconnecting = false,
            attemptCount = 0
        )

    fun recordFailure(): ReconnectState =
        copy(failureCount = failureCount + 1)

    fun stopReconnect(): ReconnectState =
        copy(
            isReconnecting = false,
            attemptCount = 0
        )

    fun shouldGiveUp(): Boolean = attemptCount >= maxAttempts
}

data class ProtocolStats(
    val totalPackets: Long = 0L,
    val validPackets: Long = 0L,
    val invalidPackets: Long = 0L,
    val corruptedPackets: Long = 0L,
    val lastPacketTime: Long = 0L,
    val avgLatencyMs: Double = 0.0,
    val maxLatencyMs: Long = 0L,
    val minLatencyMs: Long = Long.MAX_VALUE
) {
    val validRate: Float
        get() = if (totalPackets > 0) validPackets.toFloat() / totalPackets else 0f

    fun recordPacket(isValid: Boolean, latencyMs: Long): ProtocolStats {
        val newTotalPackets = totalPackets + 1
        val newLastPacketTime = System.currentTimeMillis()

        return if (isValid) {
            val newValidPackets = validPackets + 1
            val newAvgLatencyMs = (avgLatencyMs * validPackets + latencyMs) / newValidPackets
            copy(
                totalPackets = newTotalPackets,
                validPackets = newValidPackets,
                lastPacketTime = newLastPacketTime,
                avgLatencyMs = newAvgLatencyMs,
                maxLatencyMs = maxOf(maxLatencyMs, latencyMs),
                minLatencyMs = minOf(minLatencyMs, latencyMs)
            )
        } else {
            copy(
                totalPackets = newTotalPackets,
                invalidPackets = invalidPackets + 1,
                lastPacketTime = newLastPacketTime
            )
        }
    }

    fun recordCorrupted(): ProtocolStats =
        copy(corruptedPackets = corruptedPackets + 1)

    fun reset(): ProtocolStats =
        ProtocolStats()
}
