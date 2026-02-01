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
                   data