package com.example.dji_rc_pro.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.ConcurrentHashMap

private val Context.frequencyDataStore: DataStore<Preferences> by preferencesDataStore(name = "frequency_settings")

class FrequencyManager(private val context: Context) {

    private val dataStore = context.frequencyDataStore
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _frequencyHz = MutableStateFlow(DEFAULT_FREQUENCY)
    val frequencyHz: StateFlow<Int> = _frequencyHz.asStateFlow()

    private val _intervalMs = MutableStateFlow(calculateInterval(DEFAULT_FREQUENCY))
    val intervalMs: StateFlow<Long> = _intervalMs.asStateFlow()

    private val jobs = ConcurrentHashMap<String, Job>()

    val minFrequency: Int = MIN_FREQUENCY
    val maxFrequency: Int = MAX_FREQUENCY
    val defaultFrequency: Int = DEFAULT_FREQUENCY

    init {
        loadFrequency()
        observeFrequencyChanges()
    }

    private fun loadFrequency() {
        scope.launch {
            try {
                val savedFrequency = dataStore.data.first()[FREQUENCY_KEY] ?: DEFAULT_FREQUENCY
                val clampedFrequency = savedFrequency.coerceIn(MIN_FREQUENCY, MAX_FREQUENCY)
                updateFrequencyInternal(clampedFrequency)
                Timber.d("Frequency loaded: $clampedFrequency Hz")
            } catch (e: Exception) {
                Timber.e(e, "Failed to load frequency")
                updateFrequencyInternal(DEFAULT_FREQUENCY)
            }
        }
    }

    private fun observeFrequencyChanges() {
        dataStore.data
            .onEach { preferences ->
                val savedFrequency = preferences[FREQUENCY_KEY] ?: DEFAULT_FREQUENCY
                val clampedFrequency = savedFrequency.coerceIn(MIN_FREQUENCY, MAX_FREQUENCY)
                if (clampedFrequency != _frequencyHz.value) {
                    updateFrequencyInternal(clampedFrequency)
                }
            }
            .launchIn(scope)
    }

    fun setFrequency(hz: Int, persist: Boolean = true): Boolean {
        val clampedHz = hz.coerceIn(MIN_FREQUENCY, MAX_FREQUENCY)

        if (clampedHz == _frequencyHz.value) {
            Timber.d("Frequency unchanged: $clampedHz Hz")
            return false
        }

        Timber.d("Setting frequency: $clampedHz Hz")
        updateFrequencyInternal(clampedHz)

        if (persist) {
            persistFrequency(clampedHz)
        }

        return true
    }

    private fun updateFrequencyInternal(hz: Int) {
        _frequencyHz.value = hz
        _intervalMs.value = calculateInterval(hz)
        Timber.d("Frequency updated: $hz Hz, interval: ${_intervalMs.value}ms")
    }

    private fun persistFrequency(hz: Int) {
        scope.launch {
            try {
                dataStore.edit { preferences ->
                    preferences[FREQUENCY_KEY] = hz
                }
                Timber.d("Frequency persisted: $hz Hz")
            } catch (e: Exception) {
                Timber.e(e, "Failed to persist frequency")
            }
        }
    }

    fun resetToDefault() {
        setFrequency(DEFAULT_FREQUENCY, persist = true)
        Timber.d("Frequency reset to default: $DEFAULT_FREQUENCY Hz")
    }

    fun startPeriodicTask(
        taskId: String,
        task: suspend (Long) -> Unit,
        initialDelayMs: Long = 0
    ) {
        stopPeriodicTask(taskId)

        val job = scope.launch {
            delay(initialDelayMs)
            while (isActive) {
                val startTime = System.currentTimeMillis()
                try {
                    task(startTime)
                } catch (e: Exception) {
                    Timber.e(e, "Error in periodic task: $taskId")
                }
                val elapsed = System.currentTimeMillis() - startTime
                val delayTime = (_intervalMs.value - elapsed).coerceAtLeast(1)
                delay(delayTime)
            }
        }

        jobs[taskId] = job
        Timber.d("Started periodic task: $taskId with interval ${_intervalMs.value}ms")
    }

    fun startPrecisePeriodicTask(
        taskId: String,
        task: suspend (Long) -> Unit
    ) {
        stopPeriodicTask(taskId)

        val job = scope.launch {
            var lastTickTime = System.currentTimeMillis()
            while (isActive) {
                val currentTime = System.currentTimeMillis()
                val targetTick = lastTickTime + _intervalMs.value

                if (currentTime < targetTick) {
                    delay(targetTick - currentTime)
                }

                val adjustedTime = System.currentTimeMillis()
                lastTickTime = adjustedTime

                try {
                    task(adjustedTime)
                } catch (e: Exception) {
                    Timber.e(e, "Error in precise periodic task: $taskId")
                }
            }
        }

        jobs[taskId] = job
        Timber.d("Started precise periodic task: $taskId")
    }

    fun stopPeriodicTask(taskId: String) {
        jobs[taskId]?.cancel()
        jobs.remove(taskId)
        Timber.d("Stopped periodic task: $taskId")
    }

    fun stopAllPeriodicTasks() {
        jobs.values.forEach { it.cancel() }
        jobs.clear()
        Timber.d("Stopped all periodic tasks")
    }

    fun getFrequencyForInterval(intervalMs: Long): Int {
        return (1000 / intervalMs).toInt().coerceIn(MIN_FREQUENCY, MAX_FREQUENCY)
    }

    fun getIntervalForFrequency(hz: Int): Long {
        return calculateInterval(hz.coerceIn(MIN_FREQUENCY, MAX_FREQUENCY))
    }

    fun calculateTheoreticalInterval(hz: Int): Long {
        return calculateInterval(hz.coerceIn(MIN_FREQUENCY, MAX_FREQUENCY))
    }

    fun calculateActualFrequency(observedIntervals: List<Long>): Double {
        if (observedIntervals.isEmpty()) return 0.0
        val avgInterval = observedIntervals.average()
        return if (avgInterval > 0) 1000.0 / avgInterval else 0.0
    }

    fun getFrequencyRange(): IntRange = MIN_FREQUENCY..MAX_FREQUENCY

    fun isFrequencyValid(hz: Int): Boolean = hz in MIN_FREQUENCY..MAX_FREQUENCY

    fun getBandwidthEstimate(packetSizeBytes: Int): Long {
        return packetSizeBytes.toLong() * _frequencyHz.value * 8 / 1000
    }

    fun shutdown() {
        stopAllPeriodicTasks()
        Timber.d("FrequencyManager shutdown")
    }

    private fun calculateInterval(hz: Int): Long {
        return (1000.0 / hz).toLong().coerceAtLeast(1)
    }

    companion object {
        const val MIN_FREQUENCY = 10
        const val MAX_FREQUENCY = 200
        const val DEFAULT_FREQUENCY = 100

        private val FREQUENCY_KEY = intPreferencesKey("data_frequency_hz")
    }
}
