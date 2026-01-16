package com.example.dji_rc_pro.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.dji_rc_pro.domain.config.ConfigRepository
import com.example.dji_rc_pro.domain.msdk.MsdkManager
import com.example.dji_rc_pro.protocol.ControlPacket
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class UdpService : Service() {
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)
    private var socket: DatagramSocket? = null
    
    companion object {
        private const val TAG = "UdpService"
        const val ACTION_START = "START_UDP"
        const val ACTION_STOP = "STOP_UDP"
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
        if (socket != null) return // Already running

        Log.i(TAG, "Starting UDP Service")
        
        serviceScope.launch {
            try {
                val repo = ConfigRepository.get()
                val targetIp = repo.targetIp.first()
                val targetPort = repo.targetPort.first()

                Log.i(TAG, "Target: $targetIp:$targetPort")

                socket = DatagramSocket()
                val address = InetAddress.getByName(targetIp)
                
                while (isActive) {
                    val packetData = createPacket()
                    val packet = DatagramPacket(packetData, packetData.size, address, targetPort)
                    
                    socket?.send(packet)
                    
                    // 50Hz = 20ms
                    delay(20)
                }
            } catch (e: Exception) {
                Log.e(TAG, "UDP Error", e)
            } finally {
                closeSocket()
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
        Log.i(TAG, "Stopping UDP Service")
        serviceJob.cancelChildren()
        closeSocket()
        stopSelf()
    }

    private fun closeSocket() {
        socket?.close()
        socket = null
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        closeSocket()
    }
}
