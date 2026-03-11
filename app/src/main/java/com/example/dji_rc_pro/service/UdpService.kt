package com.example.dji_rc_pro.service

import android.util.Log
import com.example.dji_rc_pro.domain.config.ConfigRepository
import com.example.dji_rc_pro.manager.ConnectionManager
import com.example.dji_rc_pro.manager.FrequencyManager
import com.example.dji_rc_pro.manager.HeartbeatManager
import com.example.dji_rc_pro.manager.ReconnectManager
import com.example.dji_rc_pro.manager.DataLogManager
import com.example.dji_rc_pro.manager.UdpCustomDataManager
import com.example.dji_rc_pro.manager.UdpDataLogManager
import com.example.dji_rc_pro.util.ErrorCode
import com.example.dji_rc_pro.util.LogUtil
import com.example.dji_rc_pro.util.UdpEndpointConfig
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.SocketException

/**
 * UDP transmission service for sending control packets via network
 */
class UdpService : BaseTransmissionService() {

    override val startAction: String = ACTION_START
    override val stopAction: String = ACTION_STOP
    override val transmissionFrequencyHz: Int = DEFAULT_FREQUENCY_HZ
    override val serviceTag: String = TAG

    private var socket: DatagramSocket? = null
    private var frequencyManager: FrequencyManager? = null
    private var connectionManager: ConnectionManager? = null
    private var heartbeatManager: HeartbeatManager? = null
    private var reconnectManager: ReconnectManager? = null
    private var dataLogManager: DataLogManager? = null
    @Deprecated("Use dataLogManager instead")
    private var udpDataLogManager: UdpDataLogManager? = null
    private var targetIp: String = ""
    private var targetPort: Int = 0
    private var resolvedTargetAddress: InetAddress? = null

    private val isRunning: Boolean
        get() = socket != null && serviceJob.isActive

    private var receiveJob: Job? = null
    

    companion object {
        private const val TAG = "UdpService"
        const val ACTION_START = "START_UDP"
        const val ACTION_STOP = "STOP_UDP"
        private const val DEFAULT_FREQUENCY_HZ = 50
        private const val SOCKET_TIMEOUT_MS = 1000

        @Volatile
        private var instance: UdpService? = null

        fun getInstance(): UdpService {
            return instance ?: throw IllegalStateException("UdpService not initialized")
        }
        
        fun getInstanceOrNull(): UdpService? {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        logDebug("UdpService created")
    }

    fun initialize(
        freqManager: FrequencyManager,
        connManager: ConnectionManager,
        hbManager: HeartbeatManager,
        rcManager: ReconnectManager,
        dataLogManager: DataLogManager
    ) {
        this.frequencyManager = freqManager
        this.connectionManager = connManager
        this.heartbeatManager = hbManager
        this.reconnectManager = rcManager
        this.dataLogManager = dataLogManager
        this.udpDataLogManager = null // Deprecated
        logDebug("UdpService initialized with managers")
    }

    @Deprecated("Use the new initialize method with DataLogManager")
    fun initialize(
        freqManager: FrequencyManager,
        connManager: ConnectionManager,
        hbManager: HeartbeatManager,
        rcManager: ReconnectManager,
        udpDataLogManager: UdpDataLogManager
    ) {
        this.frequencyManager = freqManager
        this.connectionManager = connManager
        this.heartbeatManager = hbManager
        this.reconnectManager = rcManager
        this.udpDataLogManager = udpDataLogManager
        this.dataLogManager = DataLogManager.getInstance()
        logDebug("UdpService initialized with managers (legacy)")
    }

    override fun startTransmission() {
        if (isRunning) {
            logWarning("UdpService already running")
            return
        }

        logInfo("Starting UDP transmission")

        serviceScope.launch {
            try {
                val repo = ConfigRepository.get()
                targetIp = UdpEndpointConfig.normalizeTargetAddress(repo.targetIp.first())
                targetPort = repo.targetPort.first()
                val localPort = repo.localPort.first()
                resolvedTargetAddress = UdpEndpointConfig.resolveAddress(targetIp)

                logInfo("Target: $targetIp:$targetPort, Local Port: $localPort")
                Log.d(TAG, "UDP Connecting to $targetIp:$targetPort (local port: $localPort)")
                
                // 等待初始化完成（最多等待2秒）
                var waitCount = 0
                while (frequencyManager == null && waitCount < 20) {
                    Log.d(TAG, "Waiting for initialization... ($waitCount)")
                    delay(100)
                    waitCount++
                }
                
                if (frequencyManager == null) {
                    Log.e(TAG, "Timeout waiting for initialization")
                    logError("Timeout waiting for initialization")
                    return@launch
                }
                
                Log.d(TAG, "Initialization complete, starting UDP service")
                connectionManager?.connectUdp()
                openSocket(localPort)
                startReceiving()
                connectionManager?.onUdpConnected()
                heartbeatManager?.startUdpHeartbeat()
                transmissionLoop()
            } catch (e: Exception) {
                logError("Error during startup", e)
                handleError(e)
            } finally {
                cleanup()
            }
        }
    }

    private suspend fun openSocket(localPort: Int) {
        Log.d(TAG, "Opening socket on port $localPort")
        try {
            val remoteAddress = resolvedTargetAddress
                ?: throw IllegalStateException("Target address has not been resolved")
            val bindAddress = UdpEndpointConfig.getWildcardBindAddress(remoteAddress)

            socket = DatagramSocket(null).apply {
                reuseAddress = true
                broadcast = false
                soTimeout = SOCKET_TIMEOUT_MS
                bind(InetSocketAddress(bindAddress, localPort))
                Log.d(TAG, "Socket successfully bound to $bindAddress:$localPort for $remoteAddress")
                logDebug("Socket opened on $bindAddress:$localPort")
            }
        } catch (e: SocketException) {
            Log.e(TAG, "Failed to bind socket on port $localPort", e)
            logError("Failed to configure socket on port $localPort", e)
            throw e
        }
    }

    private fun startReceiving() {
        receiveJob?.cancel()
        receiveJob = serviceScope.launch {
            val buffer = ByteArray(2048)
            while (isActive && socket != null) {
                try {
                    val packet = DatagramPacket(buffer, buffer.size)
                    socket?.receive(packet)
                    val receivedData = packet.data.copyOfRange(packet.offset, packet.offset + packet.length)
                    val sourceIp = packet.address.hostAddress ?: "unknown"
                    val sourcePort = packet.port
                    
                    // 记录到数据日志
                    dataLogManager?.addUdpReceivedLog(receivedData)
                    udpDataLogManager?.addReceivedLog(receivedData)
                    Log.d(TAG, "UDP RX [${receivedData.size}]: ${bytesToHex(receivedData)}")
                    logDebug("Received ${receivedData.size} bytes from $sourceIp:$sourcePort")
                    
                    // 传递给自定义数据管理器
                    UdpCustomDataManager.getInstance().onDataReceived(receivedData, sourceIp, sourcePort)
                } catch (e: Exception) {
                    if (e is SocketException && e.message?.contains("Socket closed") == true) {
                        break
                    }
                    if (e is java.net.SocketTimeoutException) {
                        continue
                    }
                    logError("Receive error", e)
                }
            }
        }
    }

    private suspend fun transmissionLoop() {
        val freqManager = frequencyManager
        if (freqManager == null) {
            Log.e(TAG, "transmissionLoop: frequencyManager is null, cannot start transmission")
            logError("frequencyManager is null, cannot start transmission")
            return
        }
        logDebug("Starting transmission loop at ${freqManager.frequencyHz.value}Hz")

        while (serviceScope.isActive && socket != null) {
            val startTime = System.currentTimeMillis()

            try {
                transmitPacket()
                heartbeatManager?.onHeartbeatReceived("UDP")
            } catch (e: Exception) {
                logError("Transmission error", e)
                handleTransmissionError(e)
                break
            }

            val elapsed = System.currentTimeMillis() - startTime
            val delayTime = (freqManager.intervalMs.value - elapsed).coerceAtLeast(1)
            delay(delayTime)
        }
    }

    override suspend fun transmitPacket() {
        val packetData = createControlPacket()
        val address = resolvedTargetAddress ?: InetAddress.getByName(targetIp)
        val packet = DatagramPacket(packetData, packetData.size, address, targetPort)

        socket?.send(packet)
        dataLogManager?.addUdpSentLog(packetData)
        udpDataLogManager?.addSentLog(packetData)
        Log.d(TAG, "UDP TX [${packetData.size}]: ${bytesToHex(packetData)}")

        connectionManager?.onUdpActivity(
            bytesSent = packetData.size.toLong(),
            bytesReceived = 0
        )
    }
    

    /**
     * 发送自定义数据到指定目标
     * 供用户在其他地方调用发送自定义数据
     * 
     * @param data 要发送的字节数据
     * @param targetIp 目标IP地址
     * @param targetPort 目标端口
     */
    suspend fun sendCustomData(data: ByteArray, targetIp: String, targetPort: Int) {
        val normalizedTargetIp = UdpEndpointConfig.normalizeTargetAddress(targetIp)
        val address = if (normalizedTargetIp == this.targetIp) {
            resolvedTargetAddress ?: InetAddress.getByName(normalizedTargetIp)
        } else {
            InetAddress.getByName(normalizedTargetIp)
        }
        val packet = DatagramPacket(data, data.size, address, targetPort)
        
        socket?.send(packet)
        dataLogManager?.addUdpSentLog(data)
        udpDataLogManager?.addSentLog(data)
        Log.d(TAG, "UDP Custom TX [${data.size}]: ${bytesToHex(data)}")
        
        connectionManager?.onUdpActivity(
            bytesSent = data.size.toLong(),
            bytesReceived = 0
        )
    }

    private fun bytesToHex(bytes: ByteArray): String {
        return bytes.joinToString(" ") { String.format("%02X", it) }
    }

    private fun handleError(e: Exception) {
        val errorCode = when (e) {
            is SocketException -> ErrorCode.UDP_BIND_FAILED
            is IllegalArgumentException -> ErrorCode.UDP_CONNECTION_FAILED
            else -> ErrorCode.UDP_CONNECTION_FAILED
        }
        connectionManager?.onUdpError(errorCode)
        reconnectManager?.let { rc ->
            if (!rc.isReconnecting()) {
                rc.startReconnect("UDP", errorCode)
            }
        }
    }

    private fun handleTransmissionError(e: Exception) {
        val errorCode = when (e) {
            is SocketException -> ErrorCode.UDP_SEND_FAILED
            else -> ErrorCode.UDP_CONNECTION_FAILED
        }
        connectionManager?.onUdpDisconnected(errorCode)
        reconnectManager?.let { rc ->
            if (!rc.isReconnecting()) {
                rc.startReconnect("UDP", errorCode)
            }
        }
    }

    private fun cleanup() {
        logDebug("Cleaning up")
        heartbeatManager?.stopUdpHeartbeat()
        receiveJob?.cancel()
        receiveJob = null
        closeSocket()
        if (socket != null) {
            connectionManager?.onUdpDisconnected()
        }
        socket = null
        resolvedTargetAddress = null
    }

    override fun stopTransmission() {
        logInfo("Stopping UDP transmission")
        reconnectManager?.cancelReconnect()
        serviceScope.launch {
            serviceJob.cancelChildren()
            cleanup()
            stopSelf()
        }
    }

    private fun closeSocket() {
        try {
            socket?.close()
            logDebug("Socket closed")
        } catch (e: Exception) {
            logError("Error closing socket", e)
        }
    }

    fun updateTarget(ip: String, port: Int) {
        if (isRunning) {
            val normalizedIp = UdpEndpointConfig.normalizeTargetAddress(ip)
            logDebug("Updating target: $normalizedIp:$port")
            targetIp = normalizedIp
            targetPort = port
            resolvedTargetAddress = InetAddress.getByName(normalizedIp)
        }
    }

    fun isConnected(): Boolean = socket != null && isRunning

    override fun onDestroy() {
        super.onDestroy()
        logDebug("UdpService destroying")
        instance = null
        serviceScope.launch {
            serviceJob.cancel()
            cleanup()
        }
    }

    override fun logInfo(message: String) {
        LogUtil.i("[$TAG] $message")
    }

    override fun logDebug(message: String) {
        LogUtil.d("[$TAG] $message")
    }

    override fun logWarning(message: String) {
        LogUtil.w("[$TAG] $message")
    }

    override fun logError(message: String, throwable: Throwable?) {
        if (throwable != null) {
            LogUtil.e(throwable, "[$TAG] $message")
        } else {
            LogUtil.e("[$TAG] $message")
        }
    }
}
