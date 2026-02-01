package com.example.dji_rc_pro.manager

import android.util.Log
import com.example.dji_rc_pro.service.UdpService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.net.DatagramPacket
import java.net.InetAddress

/**
 * UDP自定义数据管理器
 * 提供简单的API供用户发送和接收自定义UDP数据
 */
class UdpCustomDataManager private constructor() {

    companion object {
        private const val TAG = "UdpCustomDataManager"
        
        @Volatile
        private var instance: UdpCustomDataManager? = null
        
        fun getInstance(): UdpCustomDataManager {
            return instance ?: synchronized(this) {
                instance ?: UdpCustomDataManager().also { instance = it }
            }
        }
    }

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    
    // 接收到的自定义数据流
    private val _receivedData = MutableSharedFlow<CustomDataPacket>(extraBufferCapacity = 100)
    val receivedData: SharedFlow<CustomDataPacket> = _receivedData.asSharedFlow()

    /**
     * 自定义数据包
     */
    data class CustomDataPacket(
        val data: ByteArray,
        val sourceIp: String,
        val sourcePort: Int,
        val timestamp: Long = System.currentTimeMillis()
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false
            other as CustomDataPacket
            return timestamp == other.timestamp &&
                   sourcePort == other.sourcePort &&
                   sourceIp == other.sourceIp &&
                   data.contentEquals(other.data)
        }

        override fun hashCode(): Int {
            var result = data.contentHashCode()
            result = 31 * result + sourceIp.hashCode()
            result = 31 * result + sourcePort
            result = 31 * result + timestamp.hashCode()
            return result
        }
    }

    /**
     * 发送自定义数据到指定目标
     * 
     * @param data 要发送的字节数据
     * @param targetIp 目标IP地址
     * @param targetPort 目标端口
     * @return 是否发送成功
     */
    fun sendData(data: ByteArray, targetIp: String, targetPort: Int): Boolean {
        return try {
            scope.launch {
                try {
                    val udpService = UdpService.getInstanceOrNull()
                    if (udpService != null) {
                        udpService.sendCustomData(data, targetIp, targetPort)
                        Log.d(TAG, "Sent ${data.size} bytes to $targetIp:$targetPort")
                    } else {
                        Log.w(TAG, "UdpService not available, cannot send data")
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to send data", e)
                }
            }
            true
        } catch (e: Exception) {
            Log.e(TAG, "Failed to send data", e)
            false
        }
    }

    /**
     * 发送字符串数据到指定目标
     * 
     * @param data 要发送的字符串
     * @param targetIp 目标IP地址
     * @param targetPort 目标端口
     * @return 是否发送成功
     */
    fun sendString(data: String, targetIp: String, targetPort: Int): Boolean {
        return sendData(data.toByteArray(Charsets.UTF_8), targetIp, targetPort)
    }

    /**
     * 发送十六进制字符串数据到指定目标
     * 
     * @param hexString 十六进制字符串，如 "52 43 A2 02" 或 "5243A202"
     * @param targetIp 目标IP地址
     * @param targetPort 目标端口
     * @return 是否发送成功
     */
    fun sendHexString(hexString: String, targetIp: String, targetPort: Int): Boolean {
        val cleanedHex = hexString.replace(" ", "").replace("0x", "").replace(",", "")
        return try {
            val data = cleanedHex.chunked(2)
                .map { it.toInt(16).toByte() }
                .toByteArray()
            sendData(data, targetIp, targetPort)
        } catch (e: Exception) {
            Log.e(TAG, "Invalid hex string: $hexString", e)
            false
        }
    }

    /**
     * 处理接收到的数据（由UdpService调用）
     */
    internal fun onDataReceived(data: ByteArray, sourceIp: String, sourcePort: Int) {
        val packet = CustomDataPacket(data, sourceIp, sourcePort)
        _receivedData.tryEmit(packet)
    }

    /**
     * 清理资源
     */
    fun cleanup() {
        scope.cancel()
    }
}
