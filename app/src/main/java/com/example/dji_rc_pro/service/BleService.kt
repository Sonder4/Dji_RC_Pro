package com.example.dji_rc_pro.service

import android.app.Service
import android.bluetooth.BluetoothProfile
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.dji_rc_pro.domain.ble.BleManager
import com.example.dji_rc_pro.domain.msdk.MsdkManager
import com.example.dji_rc_pro.protocol.ControlPacket
import kotlinx.coroutines.*

class BleService : Service() {
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)
    
    companion object {
        private const val TAG = "BleService"
        const val ACTION_START = "START_BLE"
        const val ACTION_STOP = "STOP_BLE"
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startTransmission()
            ACTION_STOP -> stopTransmission()
        }
        return START_STICKY
    }

    private fun startTransmission() {
        Log.i(TAG, "Starting BLE Transmission Service")
        
        // Ensure scope is active (if restarted)
        serviceScope.launch {
            val bleManager = BleManager.getInstance(applicationContext)
            
            while (isActive) {
                if (bleManager.connectionState.value == BluetoothProfile.STATE_CONNECTED) {
                    val packetData = createPacket()
                    bleManager.write(packetData)
                }
                
                // 50Hz = 20ms
                delay(20)
            }
        }
    }

    private fun createPacket(): ByteArray {
        val state = MsdkManager.instance.controllerState.value
        return ControlPacket(
            leftStickX = state.leftStickX,
            leftStickY = state.leftStickY,
            rightStickX = state.rightStickX,
            rightStickY = state.rightStickY,
            buttonMask = state.buttonMask
        ).toByteArray()
    }

    private fun stopTransmission() {
        Log.i(TAG, "Stopping BLE Transmission Service")
        serviceJob.cancelChildren()
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
    }
}
