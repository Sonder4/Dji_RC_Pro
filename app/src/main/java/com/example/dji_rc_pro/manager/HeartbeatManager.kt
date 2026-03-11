package com.example.dji_rc_pro.manager

import com.example.dji_rc_pro.util.ErrorCode
import com.example.dji_rc_pro.util.HeartbeatState
import com.example.dji_rc_pro.util.LogUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class HeartbeatManager {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _udpHeartbeat = MutableStateFlow(HeartbeatState())
    val udpHeartbeat: StateFlow<HeartbeatState> = _udpHeartbeat.asStateFlow()

    private val _bleHeartbeat = MutableStateFlow(HeartbeatState())
    val bleHeartbeat: StateFlow<HeartbeatState> = _bleHeartbeat.asStateFlow()

    private val _heartbeatEvents = MutableSharedFlow<HeartbeatEvent>()
    val heartbeatEvents: SharedFlow<HeartbeatEvent> = _heartbeatEvents.asSharedFlow()

    private var udpHeartbeatJob: Job? = null
    private var bleHeartbeatJob: Job? = null
    private var heartbeatMonitorJob: Job? = null

    var onHeartbeatSend: ((String, Long) -> Unit)? = null
    var onHeartbeatReceived: ((String) -> Unit)? = null
    var onHeartbeatTimeout: ((String, HeartbeatState) -> Unit)? = null

    var defaultIntervalMs: Long = DEFAULT_INTERVAL_MS
    var defaultMaxMissed: Int = DEFAULT_MAX_MISSED

    fun startUdpHeartbeat(intervalMs: Long = defaultIntervalMs, maxMissed: Int = defaultMaxMissed) {
        LogUtil.d("HeartbeatManager: Starting UDP heartbeat (interval: ${intervalMs}ms, maxMissed: $maxMissed)")
        stopUdpHeartbeat()

        _udpHeartbeat.value = HeartbeatState(
            isActive = true,
            intervalMs = intervalMs,
            maxMissed = maxMissed,
            lastSentTime = 0L,
            lastReceivedTime = 0L,
            missedCount = 0,
            timeoutCount = 0
        )

        udpHeartbeatJob = scope.launch {
            while (isActive) {
                try {
                    sendHeartbeat("UDP")
                    _udpHeartbeat.value = _udpHeartbeat.value.markHeartbeatSent()
                    delay(intervalMs)
                } catch (e: Exception) {
                    LogUtil.e(e, "Error in UDP heartbeat")
                    break
                }
            }
        }

        startHeartbeatMonitor("UDP", _udpHeartbeat)
    }

    fun stopUdpHeartbeat() {
        udpHeartbeatJob?.cancel()
        udpHeartbeatJob = null
        _udpHeartbeat.value = _udpHeartbeat.value.reset()
        LogUtil.d("HeartbeatManager: UDP heartbeat stopped")
    }

    fun startBleHeartbeat(intervalMs: Long = defaultIntervalMs, maxMissed: Int = defaultMaxMissed) {
        LogUtil.d("HeartbeatManager: Starting BLE heartbeat (interval: ${intervalMs}ms, maxMissed: $maxMissed)")
        stopBleHeartbeat()

        _bleHeartbeat.value = HeartbeatState(
            isActive = true,
            intervalMs = intervalMs,
            maxMissed = maxMissed,
            lastSentTime = 0L,
            lastReceivedTime = 0L,
            missedCount = 0,
            timeoutCount = 0
        )

        bleHeartbeatJob = scope.launch {
            while (isActive) {
                try {
                    sendHeartbeat("BLE")
                    _bleHeartbeat.value = _bleHeartbeat.value.markHeartbeatSent()
                    delay(intervalMs)
                } catch (e: Exception) {
                    LogUtil.e(e, "Error in BLE heartbeat")
                    break
                }
            }
        }

        startHeartbeatMonitor("BLE", _bleHeartbeat)
    }

    fun stopBleHeartbeat() {
        bleHeartbeatJob?.cancel()
        bleHeartbeatJob = null
        _bleHeartbeat.value = _bleHeartbeat.value.reset()
        LogUtil.d("HeartbeatManager: BLE heartbeat stopped")
    }

    fun startAllHeartbeats(intervalMs: Long = defaultIntervalMs, maxMissed: Int = defaultMaxMissed) {
        startUdpHeartbeat(intervalMs, maxMissed)
        startBleHeartbeat(intervalMs, maxMissed)
    }

    fun stopAllHeartbeats() {
        stopUdpHeartbeat()
        stopBleHeartbeat()
        stopHeartbeatMonitor()
    }

    fun onHeartbeatReceived(transport: String) {
        LogUtil.d("HeartbeatManager: Heartbeat received from $transport")
        val stateFlow = when (transport.uppercase()) {
            "UDP" -> _udpHeartbeat
            "BLE" -> _bleHeartbeat
            else -> return
        }

        stateFlow.value = stateFlow.value.markHeartbeatReceived()
        onHeartbeatReceived?.invoke(transport)

        scope.launch {
            _heartbeatEvents.emit(HeartbeatEvent.Received(transport))
        }
    }

    fun forceHeartbeat(transport: String) {
        LogUtil.d("HeartbeatManager: Force sending heartbeat to $transport")
        sendHeartbeat(transport)

        val stateFlow = when (transport.uppercase()) {
            "UDP" -> _udpHeartbeat
            "BLE" -> _bleHeartbeat
            else -> return
        }
        stateFlow.value = stateFlow.value.markHeartbeatSent()
    }

    fun isHeartbeatHealthy(transport: String): Boolean {
        return when (transport.uppercase()) {
            "UDP" -> _udpHeartbeat.value.isHealthy
            "BLE" -> _bleHeartbeat.value.isHealthy
            else -> false
        }
    }

    fun isHeartbeatTimedOut(transport: String): Boolean {
        return when (transport.uppercase()) {
            "UDP" -> _udpHeartbeat.value.isTimedOut
            "BLE" -> _bleHeartbeat.value.isTimedOut
            else -> false
        }
    }

    fun getMissedCount(transport: String): Int {
        return when (transport.uppercase()) {
            "UDP" -> _udpHeartbeat.value.missedCount
            "BLE" -> _bleHeartbeat.value.missedCount
            else -> 0
        }
    }

    fun setInterval(transport: String, intervalMs: Long) {
        val stateFlow = when (transport.uppercase()) {
            "UDP" -> _udpHeartbeat
            "BLE" -> _bleHeartbeat
            else -> return
        }

        stateFlow.value = stateFlow.value.copy(intervalMs = intervalMs)
        LogUtil.d("HeartbeatManager: Set $transport heartbeat interval to $intervalMs ms")

        if (stateFlow.value.isActive) {
            when (transport.uppercase()) {
                "UDP" -> startUdpHeartbeat(intervalMs, stateFlow.value.maxMissed)
                "BLE" -> startBleHeartbeat(intervalMs, stateFlow.value.maxMissed)
            }
        }
    }

    fun setMaxMissed(transport: String, maxMissed: Int) {
        val stateFlow = when (transport.uppercase()) {
            "UDP" -> _udpHeartbeat
            "BLE" -> _bleHeartbeat
            else -> return
        }

        stateFlow.value = stateFlow.value.copy(maxMissed = maxMissed)
        LogUtil.d("HeartbeatManager: Set $transport max missed heartbeats to $maxMissed")
    }

    fun resetHeartbeat(transport: String) {
        val stateFlow = when (transport.uppercase()) {
            "UDP" -> _udpHeartbeat
            "BLE" -> _bleHeartbeat
            else -> return
        }
        stateFlow.value = stateFlow.value.reset()
        LogUtil.d("HeartbeatManager: Reset $transport heartbeat")
    }

    fun resetAllHeartbeats() {
        resetHeartbeat("UDP")
        resetHeartbeat("BLE")
    }

    fun getCombinedStatus(): HeartbeatCombinedStatus {
        return HeartbeatCombinedStatus(
            udpHealthy = _udpHeartbeat.value.isHealthy,
            bleHealthy = _bleHeartbeat.value.isHealthy,
            udpMissedCount = _udpHeartbeat.value.missedCount,
            bleMissedCount = _bleHeartbeat.value.missedCount,
            isAnyActive = _udpHeartbeat.value.isActive || _bleHeartbeat.value.isActive
        )
    }

    private fun startHeartbeatMonitor(transport: String, stateFlow: MutableStateFlow<HeartbeatState>) {
        heartbeatMonitorJob?.cancel()
        heartbeatMonitorJob = scope.launch {
            while (isActive) {
                delay(MONITOR_INTERVAL_MS)

                val state = stateFlow.value
                if (!state.isActive) continue

                val timeSinceLastReceived = System.currentTimeMillis() - state.lastReceivedTime
                val timeoutThreshold = state.intervalMs * state.maxMissed

                if (timeSinceLastReceived > timeoutThreshold && state.lastReceivedTime > 0) {
                    LogUtil.w("HeartbeatManager: $transport heartbeat timeout (missed: ${state.missedCount})")
                    stateFlow.value = stateFlow.value.incrementTimeout()
                    onHeartbeatTimeout?.invoke(transport, stateFlow.value)

                    scope.launch {
                        _heartbeatEvents.emit(HeartbeatEvent.Timeout(transport, stateFlow.value))
                    }
                } else if (state.missedCount > 0 && timeSinceLastReceived > state.intervalMs) {
                    stateFlow.value = stateFlow.value.incrementMissed()
                    LogUtil.d("HeartbeatManager: $transport missed heartbeat count: ${state.missedCount}")

                    scope.launch {
                        _heartbeatEvents.emit(HeartbeatEvent.Missed(transport, state.missedCount))
                    }
                }
            }
        }
    }

    private fun stopHeartbeatMonitor() {
        heartbeatMonitorJob?.cancel()
        heartbeatMonitorJob = null
    }

    private fun sendHeartbeat(transport: String) {
        val timestamp = System.currentTimeMillis()
        LogUtil.d("HeartbeatManager: Sending heartbeat to $transport at $timestamp")
        onHeartbeatSend?.invoke(transport, timestamp)
    }

    fun shutdown() {
        stopAllHeartbeats()
        LogUtil.d("HeartbeatManager shutdown")
    }

    data class HeartbeatCombinedStatus(
        val udpHealthy: Boolean,
        val bleHealthy: Boolean,
        val udpMissedCount: Int,
        val bleMissedCount: Int,
        val isAnyActive: Boolean
    ) {
        val isHealthy: Boolean
            get() = !isAnyActive || (udpHealthy && bleHealthy)

        val overallHealth: HealthStatus
            get() = when {
                !isAnyActive -> HealthStatus.INACTIVE
                udpHealthy && bleHealthy -> HealthStatus.HEALTHY
                udpHealthy || bleHealthy -> HealthStatus.DEGRADED
                else -> HealthStatus.UNHEALTHY
            }
    }

    enum class HealthStatus {
        INACTIVE,
        HEALTHY,
        DEGRADED,
        UNHEALTHY
    }

    sealed class HeartbeatEvent {
        data class Sent(val transport: String, val timestamp: Long) : HeartbeatEvent()
        data class Received(val transport: String) : HeartbeatEvent()
        data class Missed(val transport: String, val count: Int) : HeartbeatEvent()
        data class Timeout(val transport: String, val state: HeartbeatState) : HeartbeatEvent()
    }

    companion object {
        const val DEFAULT_INTERVAL_MS = 1000L
        const val DEFAULT_MAX_MISSED = 3
        const val MONITOR_INTERVAL_MS = 100L
    }
}
