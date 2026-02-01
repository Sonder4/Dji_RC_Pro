package com.example.dji_rc_pro.manager

import com.example.dji_rc_pro.util.ErrorCode
import com.example.dji_rc_pro.util.LogUtil
import com.example.dji_rc_pro.util.ReconnectState
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

class ReconnectManager {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _reconnectState = MutableStateFlow(ReconnectState())
    val reconnectState: StateFlow<ReconnectState> = _reconnectState.asStateFlow()

    private val _reconnectEvents = MutableSharedFlow<ReconnectEvent>()
    val reconnectEvents: SharedFlow<ReconnectEvent> = _reconnectEvents.asSharedFlow()

    private var reconnectJob: Job? = null

    var onReconnectAttempt: ((String, Int) -> Unit)? = null
    var onReconnectSuccess: ((String) -> Unit)? = null
    var onReconnectFailure: ((String, Int, ErrorCode?) -> Unit)? = null
    var onGiveUp: ((String, Int) -> Unit)? = null

    var defaultBaseDelayMs: Long = DEFAULT_BASE_DELAY_MS
    var defaultMaxDelayMs: Long = DEFAULT_MAX_DELAY_MS
    var defaultMaxAttempts: Int = DEFAULT_MAX_ATTEMPTS
    var defaultExponentialBase: Double = DEFAULT_EXPONENTIAL_BASE

    fun startReconnect(transport: String, errorCode: ErrorCode? = null) {
        LogUtil.d("ReconnectManager: Starting reconnection for $transport (error: $errorCode)")

        if (_reconnectState.value.isReconnecting) {
            LogUtil.w("ReconnectManager: Reconnection already in progress")
            return
        }

        _reconnectState.value.startReconnect()

        scope.launch {
            _reconnectEvents.emit(ReconnectEvent.Started(transport, errorCode))
        }

        reconnectJob = scope.launch {
            var attempt = 0

            while (isActive) {
                if (!_reconnectState.value.isReconnecting) {
                    LogUtil.d("ReconnectManager: Reconnection cancelled")
                    break
                }

                attempt++
                _reconnectState.value.recordAttempt()

                val delayMs = _reconnectState.value.currentDelayMs
                LogUtil.d("ReconnectManager: Attempt $attempt for $transport (delay: ${delayMs}ms)")

                onReconnectAttempt?.invoke(transport, attempt)

                scope.launch {
                    _reconnectEvents.emit(ReconnectEvent.Attempt(transport, attempt, delayMs))
                }

                delay(delayMs)

                if (!_reconnectState.value.isReconnecting) {
                    LogUtil.d("ReconnectManager: Reconnection cancelled during delay")
                    break
                }

                val success = performReconnect(transport)

                if (success) {
                    LogUtil.d("ReconnectManager: Reconnection successful for $transport on attempt $attempt")
                    _reconnectState.value.recordSuccess()

                    onReconnectSuccess?.invoke(transport)

                    scope.launch {
                        _reconnectEvents.emit(ReconnectEvent.Success(transport, attempt))
                    }

                    break
                } else {
                    LogUtil.w("ReconnectManager: Reconnection failed for $transport on attempt $attempt")

                    if (_reconnectState.value.shouldGiveUp()) {
                        LogUtil.e("ReconnectManager: Giving up reconnection for $transport after $attempt attempts")

                        onGiveUp?.invoke(transport, attempt)

                        scope.launch {
                            _reconnectEvents.emit(ReconnectEvent.GiveUp(transport, attempt))
                        }

                        stopReconnect()
                        break
                    }
                }
            }
        }
    }

    fun stopReconnect() {
        reconnectJob?.cancel()
        reconnectJob = null
        _reconnectState.value.stopReconnect()
        LogUtil.d("ReconnectManager: Reconnection stopped")
    }

    fun cancelReconnect() {
        LogUtil.d("ReconnectManager: Reconnection cancelled by user")
        stopReconnect()

        scope.launch {
            _reconnectEvents.emit(ReconnectEvent.Cancelled)
        }
    }

    fun getRetryDelay(): Long {
        return _reconnectState.value.nextRetryTime - System.currentTimeMillis()
    }

    fun getTimeUntilRetry(): Long {
        val timeUntil = _reconnectState.value.nextRetryTime - System.currentTimeMillis()
        return timeUntil.coerceAtLeast(0)
    }

    fun getAttemptCount(): Int = _reconnectState.value.attemptCount

    fun isReconnecting(): Boolean = _reconnectState.value.isReconnecting

    fun isReconnectingFor(transport: String): Boolean {
        return _reconnectState.value.isReconnecting
    }

    fun getStatistics(): ReconnectStatistics {
        return ReconnectStatistics(
            totalAttempts = _reconnectState.value.attemptCount,
            successCount = _reconnectState.value.successCount,
            failureCount = _reconnectState.value.failureCount,
            isReconnecting = _reconnectState.value.isReconnecting,
            currentDelayMs = _reconnectState.value.currentDelayMs
        )
    }

    fun setBaseDelay(delayMs: Long) {
        _reconnectState.value = _reconnectState.value.copy(baseDelayMs = delayMs)
        LogUtil.d("ReconnectManager: Base delay set to $delayMs ms")
    }

    fun setMaxDelay(delayMs: Long) {
        _reconnectState.value = _reconnectState.value.copy(maxDelayMs = delayMs)
        LogUtil.d("ReconnectManager: Max delay set to $delayMs ms")
    }

    fun setMaxAttempts(maxAttempts: Int) {
        _reconnectState.value = _reconnectState.value.copy(maxAttempts = maxAttempts)
        LogUtil.d("ReconnectManager: Max attempts set to $maxAttempts")
    }

    fun setExponentialBase(base: Double) {
        _reconnectState.value = _reconnectState.value.copy(exponentialBase = base)
        LogUtil.d("ReconnectManager: Exponential base set to $base")
    }

    fun resetStatistics() {
        _reconnectState.value = ReconnectState()
        LogUtil.d("ReconnectManager: Statistics reset")
    }

    private suspend fun performReconnect(transport: String): Boolean {
        return try {
            LogUtil.d("ReconnectManager: Performing reconnection for $transport")
            true
        } catch (e: Exception) {
            LogUtil.e(e, "ReconnectManager: Reconnection error for $transport")
            false
        }
    }

    fun shutdown() {
        stopReconnect()
        LogUtil.d("ReconnectManager shutdown")
    }

    data class ReconnectStatistics(
        val totalAttempts: Int,
        val successCount: Int,
        val failureCount: Int,
        val isReconnecting: Boolean,
        val currentDelayMs: Long
    ) {
        val successRate: Float
            get() = if (totalAttempts > 0) successCount.toFloat() / totalAttempts else 0f
    }

    sealed class ReconnectEvent {
        data class Started(val transport: String, val errorCode: ErrorCode?) : ReconnectEvent()
        data class Attempt(val transport: String, val attempt: Int, val delayMs: Long) : ReconnectEvent()
        data class Success(val transport: String, val attempts: Int) : ReconnectEvent()
        data class GiveUp(val transport: String, val attempts: Int) : ReconnectEvent()
        data class Failed(val transport: String, val attempt: Int, val errorCode: ErrorCode?) : ReconnectEvent()
        object Cancelled : ReconnectEvent()
    }

    companion object {
        const val DEFAULT_BASE_DELAY_MS = 1000L
        const val DEFAULT_MAX_DELAY_MS = 30000L
        const val DEFAULT_MAX_ATTEMPTS = 10
        const val DEFAULT_EXPONENTIAL_BASE = 2.0
    }
}
