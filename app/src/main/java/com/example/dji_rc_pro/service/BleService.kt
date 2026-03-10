package com.example.dji_rc_pro.service

import android.bluetooth.BluetoothProfile
import com.example.dji_rc_pro.domain.ble.BleManager
import com.example.dji_rc_pro.manager.DataLogManager
import timber.log.Timber

/**
 * BLE transmission service for sending control packets via Bluetooth Low Energy
 * Enhanced with detailed data logging for debugging
 */
class BleService : BaseTransmissionService() {

    override val startAction: String = ACTION_START
    override val stopAction: String = ACTION_STOP
    override val transmissionFrequencyHz: Int = DEFAULT_FREQUENCY_HZ
    override val serviceTag: String = TAG

    private lateinit var bleManager: BleManager
    private lateinit var dataLogManager: DataLogManager
    private var lastSkipReason: String? = null
    private var lastSkipLogAtMs: Long = 0L
    private var lastDisconnectedState: String? = null
    private var lastDisconnectedLogAtMs: Long = 0L

    companion object {
        private const val TAG = "BleService"
        const val ACTION_START = "START_BLE"
        const val ACTION_STOP = "STOP_BLE"
        private const val DEFAULT_FREQUENCY_HZ = 50
        private const val SKIP_LOG_THROTTLE_MS = 1500L
    }

    override fun onCreate() {
        super.onCreate()
        bleManager = BleManager.getInstance(applicationContext)
        dataLogManager = DataLogManager.getInstance()
        logInfo("BleService created, DataLogManager initialized")
    }

    override suspend fun transmitPacket() {
        if (bleManager.connectionState.value != BluetoothProfile.STATE_CONNECTED) {
            logDisconnectedState(connectionStateLabel())
            return
        }

        if (!bleManager.shouldTransmitControlFrames()) {
            logSkipReason(bleManager.controlFrameBlockReason())
            return
        }

        val packetData = createControlPacket()
        val success = bleManager.write(packetData)

        if (success) {
            resetSkipLogState()
            logDebug("Transmitted ${packetData.size} bytes via BLE")
        } else {
            logWarning("Failed to transmit packet - BLE not connected or queue full")
        }
    }

    private fun connectionStateLabel(): String {
        return when (bleManager.connectionState.value) {
            BluetoothProfile.STATE_DISCONNECTED -> "DISCONNECTED"
            BluetoothProfile.STATE_CONNECTING -> "CONNECTING"
            BluetoothProfile.STATE_DISCONNECTING -> "DISCONNECTING"
            else -> "UNKNOWN"
        }
    }

    private fun logSkipReason(reason: String) {
        val nowMs = System.currentTimeMillis()
        if (reason != lastSkipReason || nowMs - lastSkipLogAtMs >= SKIP_LOG_THROTTLE_MS) {
            logDebug("Skipping BLE control frame - $reason")
            lastSkipReason = reason
            lastSkipLogAtMs = nowMs
        }
    }

    private fun logDisconnectedState(state: String) {
        val nowMs = System.currentTimeMillis()
        if (state != lastDisconnectedState || nowMs - lastDisconnectedLogAtMs >= SKIP_LOG_THROTTLE_MS) {
            logDebug("Skipping transmission - BLE state: $state")
            lastDisconnectedState = state
            lastDisconnectedLogAtMs = nowMs
        }
    }

    private fun resetSkipLogState() {
        lastSkipReason = null
        lastSkipLogAtMs = 0L
        lastDisconnectedState = null
        lastDisconnectedLogAtMs = 0L
    }

    /**
     * Get current connection status for debugging
     */
    fun getConnectionStatus(): String {
        return if (::bleManager.isInitialized) {
            val state = when (bleManager.connectionState.value) {
                BluetoothProfile.STATE_CONNECTED -> "CONNECTED"
                BluetoothProfile.STATE_DISCONNECTED -> "DISCONNECTED"
                BluetoothProfile.STATE_CONNECTING -> "CONNECTING"
                BluetoothProfile.STATE_DISCONNECTING -> "DISCONNECTING"
                else -> "UNKNOWN"
            }
            val rssi = bleManager.currentRssi.value?.let { "RSSI: ${it}dBm" } ?: "RSSI: N/A"
            val address = bleManager.connectedDeviceAddress.value ?: "No device"
            "$state | $address | $rssi | Queue: ${bleManager.getQueueSize()}"
        } else {
            "Not initialized"
        }
    }

    override fun logInfo(message: String) {
        Timber.i("[$TAG] $message")
    }

    override fun logDebug(message: String) {
        Timber.d("[$TAG] $message")
    }

    override fun logWarning(message: String) {
        Timber.w("[$TAG] $message")
    }

    override fun logError(message: String, throwable: Throwable?) {
        if (throwable != null) {
            Timber.e(throwable, "[$TAG] $message")
        } else {
            Timber.e("[$TAG] $message")
        }
    }
}
