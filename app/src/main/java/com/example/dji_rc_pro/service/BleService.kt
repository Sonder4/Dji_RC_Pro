package com.example.dji_rc_pro.service

import android.bluetooth.BluetoothProfile
import com.example.dji_rc_pro.domain.ble.BleManager
import timber.log.Timber

/**
 * BLE transmission service for sending control packets via Bluetooth Low Energy
 */
class BleService : BaseTransmissionService() {

    override val startAction: String = ACTION_START
    override val stopAction: String = ACTION_STOP
    override val transmissionFrequencyHz: Int = DEFAULT_FREQUENCY_HZ
    override val serviceTag: String = TAG

    companion object {
        private const val TAG = "BleService"
        const val ACTION_START = "START_BLE"
        const val ACTION_STOP = "STOP_BLE"
        private const val DEFAULT_FREQUENCY_HZ = 50
    }

    override suspend fun transmitPacket() {
        val bleManager = BleManager.getInstance(applicationContext)

        if (bleManager.connectionState.value == BluetoothProfile.STATE_CONNECTED) {
            val packetData = createControlPacket()
            bleManager.write(packetData)
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
