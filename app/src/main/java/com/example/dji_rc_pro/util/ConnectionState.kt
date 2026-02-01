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

class HeartbeatState(
    var isActive: Boolean = false,
    var lastSentTime: Long = 0L,
    var lastReceivedTime: Long = 0L,
    var missedCount: Int = 0,
    var timeoutCount: Int = 0,
    val intervalMs: Long = 1000L,
    val maxMissed: Int = 3
) {
    val isHealthy: Boolean
        get() = missedCount < maxMissed && !isTimedOut

    val isTimedOut: Boolean
        get() = System.currentTimeMillis() - lastReceivedTime > intervalMs * maxMissed

    fun markHeartbeatSent() {
        lastSentTime = System.currentTimeMillis()
    }

    fun markHeartbeatReceived() {
        lastReceivedTime = System.currentTimeMillis()
        missedCount = 0
    }

    fun incrementMissed() {
        missedCount++
    }

    fun incrementTimeout() {
        timeoutCount++
    }

    fun reset() {
        isActive = false
        missedCount = 0
        timeoutCount = 0
        lastSentTime = 0L
        lastReceivedTime = 0L
    }

    fun copy(
        isActive: Boolean = this.isActive,
        lastSentTime: Long = this.lastSentTime,
        lastReceivedTime: Long = this.lastReceivedTime,
        missedCount: Int = this.missedCount,
        timeoutCount: Int = this.timeoutCount,
        intervalMs: Long = this.intervalMs,
        maxMissed: Int = this.maxMissed
    ): HeartbeatState {
        return HeartbeatState(
            isActive = isActive,
            lastSentTime = lastSentTime,
            lastReceivedTime = lastReceivedTime,
            missedCount = missedCount,
            timeoutCount = timeoutCount,
            intervalMs = intervalMs,
            maxMissed = maxMissed
        )
    }
}

class ReconnectState(
    var isReconnecting: Boolean = false,
    var attemptCount: Int = 0,
    val maxAttempts: Int = 10,
    val baseDelayMs: Long = 1000L,
    val maxDelayMs: Long = 30000L,
    val exponentialBase: Double = 2.0,
    var lastAttemptTime: Long = 0L,
    var nextRetryTime: Long = 0L,
    var successCount: Int = 0,
    var failureCount: Int = 0
) {
    val shouldRetry: Boolean
        get() = isReconnecting && attemptCount < maxAttempts && System.currentTimeMillis() >= nextRetryTime

    val currentDelayMs: Long
        get() {
            val delay = (baseDelayMs * Math.pow(exponentialBase, (attemptCount - 1).toDouble())).toLong()
            return delay.coerceIn(baseDelayMs, maxDelayMs)
        }

    fun startReconnect() {
        isReconnecting = true
        attemptCount = 0
        lastAttemptTime = 0L
        nextRetryTime = System.currentTimeMillis()
    }

    fun recordAttempt(): Long {
        attemptCount++
        lastAttemptTime = System.currentTimeMillis()
        nextRetryTime = lastAttemptTime + currentDelayMs
        return currentDelayMs
    }

    fun recordSuccess() {
        successCount++
        isReconnecting = false
        attemptCount = 0
    }

    fun recordFailure() {
        failureCount++
    }

    fun stopReconnect() {
        isReconnecting = false
        attemptCount = 0
    }

    fun shouldGiveUp(): Boolean {
        return attemptCount >= maxAttempts
    }

    fun copy(
        isReconnecting: Boolean = this.isReconnecting,
        attemptCount: Int = this.attemptCount,
        lastAttemptTime: Long = this.lastAttemptTime,
        nextRetryTime: Long = this.nextRetryTime,
        successCount: Int = this.successCount,
        failureCount: Int = this.failureCount,
        maxAttempts: Int = this.maxAttempts,
        baseDelayMs: Long = this.baseDelayMs,
        maxDelayMs: Long = this.maxDelayMs,
        exponentialBase: Double = this.exponentialBase
    ): ReconnectState {
        return ReconnectState(
            isReconnecting = isReconnecting,
            attemptCount = attemptCount,
            maxAttempts = maxAttempts,
            baseDelayMs = baseDelayMs,
            maxDelayMs = maxDelayMs,
            exponentialBase = exponentialBase,
            lastAttemptTime = lastAttemptTime,
            nextRetryTime = nextRetryTime,
            successCount = successCount,
            failureCount = failureCount
        )
    }
}

class ProtocolStats(
    var totalPackets: Long = 0L,
    var validPackets: Long = 0L,
    var invalidPackets: Long = 0L,
    var corruptedPackets: Long = 0L,
    var lastPacketTime: Long = 0L,
    var avgLatencyMs: Double = 0.0,
    var maxLatencyMs: Long = 0L,
    var minLatencyMs: Long = Long.MAX_VALUE
) {
    val validRate: Float
        get() = if (totalPackets > 0) validPackets.toFloat() / totalPackets else 0f

    fun recordPacket(isValid: Boolean, latencyMs: Long) {
        totalPackets++
        lastPacketTime = System.currentTimeMillis()

        if (isValid) {
            validPackets++
            avgLatencyMs = (avgLatencyMs * (validPackets - 1) + latencyMs) / validPackets
            maxLatencyMs = maxOf(maxLatencyMs, latencyMs)
            minLatencyMs = minOf(minLatencyMs, latencyMs)
        } else {
            invalidPackets++
        }
    }

    fun recordCorrupted() {
        corruptedPackets++
    }

    fun reset() {
        totalPackets = 0
        validPackets = 0
        invalidPackets = 0
        corruptedPackets = 0
        avgLatencyMs = 0.0
        maxLatencyMs = 0
        minLatencyMs = Long.MAX_VALUE
    }
}
