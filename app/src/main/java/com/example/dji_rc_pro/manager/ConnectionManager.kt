package com.example.dji_rc_pro.manager

import com.example.dji_rc_pro.util.ConnectionState
import com.example.dji_rc_pro.util.ErrorCode
import com.example.dji_rc_pro.util.LogUtil
import com.example.dji_rc_pro.util.OverallConnectionState
import com.example.dji_rc_pro.util.TransportConnectionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ConnectionManager {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _connectionState = MutableStateFlow(ConnectionState())
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    private val _udpState = MutableStateFlow(TransportConnectionState())
    val udpState: StateFlow<TransportConnectionState> = _udpState.asStateFlow()

    private val _bleState = MutableStateFlow(TransportConnectionState())
    val bleState: StateFlow<TransportConnectionState> = _bleState.asStateFlow()

    private val _connectionEvents = MutableSharedFlow<ConnectionEvent>()
    val connectionEvents: SharedFlow<ConnectionEvent> = _connectionEvents.asSharedFlow()

    private val connectionListeners = mutableListOf<ConnectionListener>()

    private var isManualDisconnect = false

    fun addConnectionListener(listener: ConnectionListener) {
        if (!connectionListeners.contains(listener)) {
            connectionListeners.add(listener)
        }
    }

    fun removeConnectionListener(listener: ConnectionListener) {
        connectionListeners.remove(listener)
    }

    fun connectUdp() {
        LogUtil.d("ConnectionManager: UDP connection requested")
        isManualDisconnect = false
        updateUdpState { it.copy(isConnecting = true, isConnected = false) }
        emitEvent(ConnectionEvent.Connecting("UDP"))
        scope.launch {
            _connectionEvents.emit(ConnectionEvent.Connecting("UDP"))
        }
    }

    fun onUdpConnected() {
        LogUtil.d("ConnectionManager: UDP connected")
        updateUdpState {
            TransportConnectionState(
                isConnected = true,
                isConnecting = false,
                connectedSince = System.currentTimeMillis(),
                lastActivityTime = System.currentTimeMillis()
            )
        }
        updateOverallState()
        emitEvent(ConnectionEvent.Connected("UDP"))
        notifyListeners { it.onConnected("UDP") }
        scope.launch {
            _connectionEvents.emit(ConnectionEvent.Connected("UDP"))
        }
    }

    fun onUdpDisconnected(errorCode: ErrorCode? = null) {
        LogUtil.d("ConnectionManager: UDP disconnected: $errorCode")
        updateUdpState {
            TransportConnectionState(
                isConnected = false,
                isConnecting = false,
                lastActivityTime = System.currentTimeMillis(),
                lastError = errorCode,
                errorCount = it.errorCount + 1
            )
        }
        updateOverallState()
        emitEvent(ConnectionEvent.Disconnected("UDP", errorCode))
        notifyListeners { it.onDisconnected("UDP", errorCode) }
        scope.launch {
            _connectionEvents.emit(ConnectionEvent.Disconnected("UDP", errorCode))
        }
    }

    fun onUdpError(errorCode: ErrorCode) {
        LogUtil.e("ConnectionManager: UDP error: $errorCode")
        updateUdpState {
            it.copy(
                lastError = errorCode,
                errorCount = it.errorCount + 1,
                lastActivityTime = System.currentTimeMillis()
            )
        }
        emitEvent(ConnectionEvent.Error("UDP", errorCode))
        notifyListeners { it.onError("UDP", errorCode) }
        scope.launch {
            _connectionEvents.emit(ConnectionEvent.Error("UDP", errorCode))
        }
    }

    fun onUdpActivity(bytesSent: Long = 0, bytesReceived: Long = 0) {
        updateUdpState {
            it.copy(
                lastActivityTime = System.currentTimeMillis(),
                bytesSent = it.bytesSent + bytesSent,
                bytesReceived = it.bytesReceived + bytesReceived,
                packetsSent = if (bytesSent > 0) it.packetsSent + 1 else it.packetsSent,
                packetsReceived = if (bytesReceived > 0) it.packetsReceived + 1 else it.packetsReceived
            )
        }
    }

    fun connectBle() {
        LogUtil.d("ConnectionManager: BLE connection requested")
        isManualDisconnect = false
        updateBleState { it.copy(isConnecting = true, isConnected = false) }
        emitEvent(ConnectionEvent.Connecting("BLE"))
        scope.launch {
            _connectionEvents.emit(ConnectionEvent.Connecting("BLE"))
        }
    }

    fun onBleConnected() {
        LogUtil.d("ConnectionManager: BLE connected")
        updateBleState {
            TransportConnectionState(
                isConnected = true,
                isConnecting = false,
                connectedSince = System.currentTimeMillis(),
                lastActivityTime = System.currentTimeMillis()
            )
        }
        updateOverallState()
        emitEvent(ConnectionEvent.Connected("BLE"))
        notifyListeners { it.onConnected("BLE") }
        scope.launch {
            _connectionEvents.emit(ConnectionEvent.Connected("BLE"))
        }
    }

    fun onBleDisconnected(errorCode: ErrorCode? = null) {
        LogUtil.d("ConnectionManager: BLE disconnected: $errorCode")
        updateBleState {
            TransportConnectionState(
                isConnected = false,
                isConnecting = false,
                lastActivityTime = System.currentTimeMillis(),
                lastError = errorCode,
                errorCount = it.errorCount + 1
            )
        }
        updateOverallState()
        emitEvent(ConnectionEvent.Disconnected("BLE", errorCode))
        notifyListeners { it.onDisconnected("BLE", errorCode) }
        scope.launch {
            _connectionEvents.emit(ConnectionEvent.Disconnected("BLE", errorCode))
        }
    }

    fun onBleError(errorCode: ErrorCode) {
        LogUtil.e("ConnectionManager: BLE error: $errorCode")
        updateBleState {
            it.copy(
                lastError = errorCode,
                errorCount = it.errorCount + 1,
                lastActivityTime = System.currentTimeMillis()
            )
        }
        emitEvent(ConnectionEvent.Error("BLE", errorCode))
        notifyListeners { it.onError("BLE", errorCode) }
        scope.launch {
            _connectionEvents.emit(ConnectionEvent.Error("BLE", errorCode))
        }
    }

    fun onBleActivity(bytesSent: Long = 0, bytesReceived: Long = 0) {
        updateBleState {
            it.copy(
                lastActivityTime = System.currentTimeMillis(),
                bytesSent = it.bytesSent + bytesSent,
                bytesReceived = it.bytesReceived + bytesReceived,
                packetsSent = if (bytesSent > 0) it.packetsSent + 1 else it.packetsSent,
                packetsReceived = if (bytesReceived > 0) it.packetsReceived + 1 else it.packetsReceived
            )
        }
    }

    fun disconnectAll() {
        LogUtil.d("ConnectionManager: Disconnecting all transports")
        isManualDisconnect = true
        updateUdpState { it.copy(isConnected = false, isConnecting = false) }
        updateBleState { it.copy(isConnected = false, isConnecting = false) }
        updateOverallState()
        emitEvent(ConnectionEvent.Disconnected("All", null))
        scope.launch {
            _connectionEvents.emit(ConnectionEvent.Disconnected("All", null))
        }
    }

    fun isConnected(): Boolean = _connectionState.value.isConnected()

    fun isAnyConnected(): Boolean = _connectionState.value.isAnyConnected()

    fun isUdpConnected(): Boolean = _udpState.value.isConnected

    fun isBleConnected(): Boolean = _bleState.value.isConnected

    fun isReconnecting(): Boolean = _connectionState.value.reconnecting

    fun getConnectedTransports(): List<String> = _connectionState.value.getConnectedTransports()

    fun getLastError(): ErrorCode? = _connectionState.value.errorCode

    fun clearLastError() {
        updateConnectionState { it.copy(errorCode = null) }
    }

    fun getUptimeMillis(): Long = _connectionState.value.getUptimeMillis()

    private fun updateUdpState(update: (TransportConnectionState) -> TransportConnectionState) {
        val newState = update(_udpState.value)
        _udpState.value = newState
        updateConnectionState { it.copy(udpState = newState) }
    }

    private fun updateBleState(update: (TransportConnectionState) -> TransportConnectionState) {
        val newState = update(_bleState.value)
        _bleState.value = newState
        updateConnectionState { it.copy(bleState = newState) }
    }

    private fun updateConnectionState(update: (ConnectionState) -> ConnectionState) {
        _connectionState.value = update(_connectionState.value)
    }

    private fun updateOverallState() {
        val newState = when {
            isManualDisconnect -> OverallConnectionState.DISCONNECTED
            _udpState.value.isConnecting || _bleState.value.isConnecting -> OverallConnectionState.CONNECTING
            _udpState.value.isConnected && _bleState.value.isConnected -> OverallConnectionState.CONNECTED
            _udpState.value.isConnected || _bleState.value.isConnected -> OverallConnectionState.PARTIAL
            _udpState.value.lastError != null || _bleState.value.lastError != null -> OverallConnectionState.ERROR
            else -> OverallConnectionState.DISCONNECTED
        }
        updateConnectionState {
            it.copy(
                overallState = newState,
                lastActivityTime = System.currentTimeMillis()
            )
        }
    }

    private fun emitEvent(event: ConnectionEvent) {
        LogUtil.d("ConnectionManager: Emitting event: $event")
    }

    private fun notifyListeners(notify: (ConnectionListener) -> Unit) {
        connectionListeners.forEach { listener ->
            try {
                notify(listener)
            } catch (e: Exception) {
                LogUtil.e(e, "Error notifying connection listener")
            }
        }
    }

    fun shutdown() {
        connectionListeners.clear()
        LogUtil.d("ConnectionManager shutdown")
    }

    interface ConnectionListener {
        fun onConnected(transport: String)
        fun onDisconnected(transport: String, errorCode: ErrorCode?)
        fun onError(transport: String, errorCode: ErrorCode)
    }

    sealed class ConnectionEvent {
        data class Connecting(val transport: String) : ConnectionEvent()
        data class Connected(val transport: String) : ConnectionEvent()
        data class Disconnected(val transport: String, val errorCode: ErrorCode?) : ConnectionEvent()
        data class Error(val transport: String, val errorCode: ErrorCode) : ConnectionEvent()
    }
}
