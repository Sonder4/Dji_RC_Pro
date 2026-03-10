package com.example.dji_rc_pro.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.dji_rc_pro.R
import com.example.dji_rc_pro.domain.input.ControllerManager
import com.example.dji_rc_pro.protocol.Ros2ChassisControlPacket
import kotlinx.coroutines.*

/**
 * Base class for transmission services (UDP/BLE)
 * Provides common functionality for control packet transmission
 */
abstract class BaseTransmissionService : Service() {

    protected val serviceJob = SupervisorJob()
    protected val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)
    private var isForegroundStarted = false

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

    protected open val notificationChannelId: String
        get() = "transmission_services"

    protected open val notificationTitle: String
        get() = serviceTag

    protected open val notificationText: String
        get() = "Transmission active"

    protected open val notificationId: Int
        get() = serviceTag.hashCode()

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            startAction -> {
                ensureForegroundStarted()
                startTransmission()
            }
            stopAction -> stopTransmission()
        }
        return START_STICKY
    }

    private fun ensureForegroundStarted() {
        if (isForegroundStarted) {
            return
        }

        val notificationManager = getSystemService(NotificationManager::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
            notificationManager.getNotificationChannel(notificationChannelId) == null) {
            val channel = NotificationChannel(
                notificationChannelId,
                "Transmission Services",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Foreground services for BLE and UDP control transport"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, notificationChannelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setOngoing(true)
            .setOnlyAlertOnce(true)
            .build()

        startForeground(notificationId, notification)
        isForegroundStarted = true
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
        if (isForegroundStarted) {
            stopForeground(STOP_FOREGROUND_REMOVE)
            isForegroundStarted = false
        }
        stopSelf()
    }

    /**
     * Create control packet from current controller state
     */
    protected fun createControlPacket(): ByteArray {
        val state = ControllerManager.instance.controllerState.value
        return Ros2ChassisControlPacket.fromControllerState(state)
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
