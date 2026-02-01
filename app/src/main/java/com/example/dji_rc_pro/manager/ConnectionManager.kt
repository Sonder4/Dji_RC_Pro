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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * Unified connection manager for UDP and BLE transports
 * Eliminates code duplication through generic transport handling
 */
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
        if (listener !in connectionListeners) {
            connectionListeners.add(listener)
        }
    }

    fun removeConnectionListener(listener: ConnectionListener) {
        connectionListeners.remove(listener)
    }

    // UDP Operations
    fun connectUdp() = connectTransport("UDP", _udpState)
    fun onUdpConnected() = onTransportConnected("UDP", _udpState)
    fun onUdpDisconnected(errorCode: ErrorCode? = null) = onTransportDisconnected("UDP", _udpState, errorCode)
    fun onUdpError(errorCode: ErrorCode) = onTransportError("UDP", _udpState, errorCode)
    fun onUdpActivity(bytesSent: Long = 0, bytesReceived: Long = 0) = onTransportActivity(_udpState, bytesSent, bytesReceived)

    // BLE Operations
    fun connectBle() = connectTransport("BLE", _bleState)
    fun onBleConnected() = onTransportConnected("BLE", _bleState)
    fun onBleDisconnected(errorCode: ErrorCode? = null) = onTransportDisconnected("BLE", _bleState, errorCode)
    fun onBleError(errorCode: ErrorCode) = onTransportError("BLE", _bleState, errorCode)
    fun onBleActivity(bytesSent: Long = 0, bytesReceived: Long = 0) = onTransportActivity(_bleState, bytesSent, bytesReceived)

    fun disconnectAll() {
        LogUtil.d("Disconnecting all transports")
        isManualDisconnect = true
        _udpState.update { it.copy(isConnected = false, isConnecting = false) }
        _bleState.update { it.copy(isConnected = false, isConnecting = false) }
        updateOverallState()
        emitEvent(ConnectionEvent.Disconnected("All", null))
    }

    fun isConnected(): Boolean = _connectionState.value.isConnected()
    fun isAnyConnected(): Boolean = _connectionState.value.isAnyConnected()
    fun isUdpConnected(): Boolean = _udpState.value.isConnected
    fun isBleConnected(): Boolean = _bleState.value.isConnected
    fun getUptimeMillis(): Long = _connectionState.value.getUptimeMillis()
    fun clearLastError() = _connectionState.update { it.copy(errorCode = null) }

    private fun connectTransport(name: String, stateFlow: MutableStateFlow<TransportConnectionState>) {
        LogUtil.d("$name connection requested")
        isManualDisconnect = false
        stateFlow.update { it.copy(isConnecting = true, isConnected = false) }
        updateOverallState()
        emitEvent(ConnectionEvent.Connecting(name))
    }

    private fun onTransportConnected(name: String, stateFlow: MutableStateFlow<TransportConnectionState>) {
        LogUtil.d("$name connected")
        val now = System.currentTimeMillis()
        stateFlow.value = TransportConnectionState(
            isConnected = true,
            isConnecting = false,
            connectedSince = now,
            lastActivityTime = now
        )
        updateOverallState()
        emitEvent(ConnectionEvent.Connected(name))
        notifyListeners { it.onConnected(name) }
    }

    private fun onTransportDisconnected(name: String, stateFlow: MutableStateFlow<TransportConnectionState>, errorCode: ErrorCode?) {
        LogUtil.d("$name disconnected: $errorCode")
        stateFlow.update {
            it.copy(
                isConnected = false,
                isConnecting = false,
                lastActivityTime = System.currentTimeMillis(),
                lastError = errorCode,
                errorCount = it.errorCount + 1
            )
        }
        updateOverallState()
        emitEvent(ConnectionEvent.Disconnected(name, errorCode))
        notifyListeners { it.onDisconnected(name, errorCode) }
    }

    private fun onTransportError(name: String, stateFlow: MutableStateFlow<TransportConnectionState>, errorCode: ErrorCode) {
        LogUtil.e("$name error: $errorCode")
        stateFlow.update {
            it.copy(
                lastError = errorCode,
                errorCount = it.errorCount + 1,
                lastActivityTime = System.currentTimeMillis()
            )
        }
        emitEvent(ConnectionEvent.Error(name, errorCode))
        notifyListeners { it.onError(name, errorCode) }
    }

    private fun onTransportActivity(stateFlow: MutableStateFlow<TransportConnectionState>, bytesSent: Long, bytesReceived: Long) {
        stateFlow.update {
            it.copy(
                lastActivityTime = System.currentTimeMillis(),
                bytesSent = it.bytesSent + bytesSent,
                bytesReceived = it.bytesReceived + bytesReceived,
                packetsSent = it.packetsSent + if (bytesSent > 0) 1 else 0,
                packetsReceived = it.packetsReceived + if (bytesReceived > 0) 1 else 0
            )
        }
    }

    private fun updateOverallState() {
        val udp = _udpState.value
        val ble = _bleState.value
        val newState = when {
            isManualDisconnect -> OverallConnectionState.DISCONNECTED
            udp.isConnecting || ble.isConnecting -> OverallConnectionState.CONNECTING
            udp.isConnected && ble.isConnected -> OverallConnectionState.CONNECTED
            udp.isConnected || ble.isConnected -> OverallConnectionState.PARTIAL
            udp.lastError != null || ble.lastError != null -> OverallConnectionState.ERROR
            else -> OverallConnectionState.DISCONNECTED
        }
        _connectionState.update {
            it.copy(overallState = newState, lastActivityTime = System.currentTimeMillis())
        }
    }

    private fun emitEvent(event: ConnectionEvent) {
        LogUtil.d("Emitting event: $event")
        scope.launch { _connectionEvents.emit(event) }
    }

    private fun notifyListeners(notify: (ConnectionListener) -> Unit) {
        connectionListeners.forEach { listener ->
            runCatching { notify(listener) }.onFailure {
                LogUtil.e(it, "Error notifying connection listener")
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
