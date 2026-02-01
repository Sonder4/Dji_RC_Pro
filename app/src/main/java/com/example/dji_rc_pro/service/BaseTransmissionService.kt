package com.example.dji_rc_pro.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.dji_rc_pro.domain.input.ControllerManager
import com.example.dji_rc_pro.protocol.ControlPacket
import kotlinx.coroutines.*

/**
 * Base class for transmission services (UDP/BLE)
 * Provides common functionality for control packet transmission
 */
abstract class BaseTransmissionService : Service() {

    protected val serviceJob = SupervisorJob()
    protected val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

    /**
     * Action to start transmission, must be defined by subclasses
     */
    protected abstract val startAction: String

    /**
     * Action to stop transmission, must be defined by subclasses
     */
    protected abstract val stopAction: String

    /**
     * Transmission frequency in Hz
     */
    protected abstract val transmissionFrequencyHz: Int

    /**
     * Service tag for logging
     */
    protected abstract val serviceTag: String

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            startAction -> startTransmission()
            stopAction -> stopTransmission()
        }
        return START_STICKY
    }

    /**
     * Start the transmission loop
     */
    protected open fun startTransmission() {
        logInfo("Starting transmission service")

        serviceScope.launch {
            val intervalMs = 1000L / transmissionFrequencyHz

            while (isActive) {
                transmitPacket()
                delay(intervalMs)
            }
        }
    }

    /**
     * Transmit a single control packet
     * Subclasses can override to customize transmission behavior
     */
    protected abstract suspend fun transmitPacket()

    /**
     * Stop the transmission
     */
    protected open fun stopTransmission() {
        logInfo("Stopping transmission service")
        serviceJob.cancelChildren()
        stopSelf()
    }

    /**
     * Create control packet from current controller state
     */
    protected fun createControlPacket(): ByteArray {
        val state = ControllerManager.instance.controllerState.value
        return ControlPacket(
            leftStickX = state.leftStickX,
            leftStickY = state.leftStickY,
            rightStickX = state.rightStickX,
            rightStickY = state.rightStickY,
            buttonMask = state.buttonMask
        ).toByteArray()
    }

    /**
     * Log info message
     */
    protected abstract fun logInfo(message: String)

    /**
     * Log debug message
     */
    protected abstract fun logDebug(message: String)

    /**
     * Log warning message
     */
    protected abstract fun logWarning(message: String)

    /**
     * Log error message
     */
    protected abstract fun logError(message: String, throwable: Throwable? = null)

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
    }
}
