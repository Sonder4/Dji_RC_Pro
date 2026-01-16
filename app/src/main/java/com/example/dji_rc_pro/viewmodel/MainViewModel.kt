package com.example.dji_rc_pro.viewmodel

import android.app.Application
import android.content.Intent
import android.bluetooth.le.ScanResult
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

import com.example.dji_rc_pro.domain.ble.BleManager
import com.example.dji_rc_pro.domain.config.ConfigRepository
import com.example.dji_rc_pro.domain.input.StickTransformer
import com.example.dji_rc_pro.domain.msdk.MsdkManager
import com.example.dji_rc_pro.service.BleService
import com.example.dji_rc_pro.service.UdpService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    
    // UI State
    private val _isUdpRunning = MutableStateFlow(false)
    val isUdpRunning: StateFlow<Boolean> = _isUdpRunning.asStateFlow()
    
    // BLE State
    private val bleManager = BleManager.getInstance(application)
    val bleScanResults: StateFlow<List<BleManager.BleDevice>> = bleManager.scanResults
    val bleConnectionState: StateFlow<Int> = bleManager.connectionState
    val isBleScanning: StateFlow<Boolean> = bleManager.isScanning

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

    init {
        viewModelScope.launch {
            bleConnectionState.collect { state ->
                if (state == android.bluetooth.BluetoothProfile.STATE_CONNECTED) {
                    showToast("Bluetooth Connected", 500)
                } else if (state == android.bluetooth.BluetoothProfile.STATE_DISCONNECTED) {
                    // Optional: showToast("Disconnected", 500)
                }
            }
        }
    }

    private fun showToast(message: String, durationMs: Long) {
        viewModelScope.launch {
            _toastMessage.value = message
            kotlinx.coroutines.delay(durationMs)
            _toastMessage.value = null
        }
    }

    private val _isVideoEnabled = MutableStateFlow(false)
    val isVideoEnabled: StateFlow<Boolean> = _isVideoEnabled.asStateFlow()

    val msdkRegistered: StateFlow<Boolean> = MsdkManager.instance.isRegistered

    // Config
    val targetIp: StateFlow<String> = ConfigRepository.get().targetIp
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "192.168.1.100")

    val targetPort: StateFlow<Int> = ConfigRepository.get().targetPort
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 8888)

    // Expose controller state for UI (Virtual Sticks feedback)
    val controllerState = MsdkManager.instance.controllerState

    fun updateTargetIp(ip: String) {
        viewModelScope.launch {
            ConfigRepository.get().setTargetIp(ip)
        }
    }

    fun updateTargetPort(portStr: String) {
        val port = portStr.toIntOrNull() ?: return
        viewModelScope.launch {
            ConfigRepository.get().setTargetPort(port)
        }
    }

    fun toggleUdp() {
        if (_isUdpRunning.value) {
            stopUdp()
        } else {
            startUdp()
        }
    }
    
    // BLE Actions
    fun startBleScan() {
        bleManager.startScan()
    }

    fun stopBleScan() {
        bleManager.stopScan()
    }

    fun connectBle(device: BleManager.BleDevice) {
        bleManager.connect(device.device, getApplication())
        // Auto-start BLE service logic could be here, but let's keep it separate or auto-start on connect?
        // Let's start the service immediately so it's ready to pump data once connected.
        startBleService()
    }
    
    fun disconnectBle() {
        bleManager.disconnect()
        stopBleService()
    }
    
    private fun startBleService() {
        val intent = Intent(getApplication(), BleService::class.java).apply {
            action = BleService.ACTION_START
        }
        getApplication<Application>().startService(intent)
    }

    private fun stopBleService() {
        val intent = Intent(getApplication(), BleService::class.java).apply {
            action = BleService.ACTION_STOP
        }
        getApplication<Application>().startService(intent)
    }
    
    fun toggleVideo() {
        _isVideoEnabled.value = !_isVideoEnabled.value
    }

    private fun startUdp() {
        val intent = Intent(getApplication(), UdpService::class.java).apply {
            action = UdpService.ACTION_START
        }
        getApplication<Application>().startService(intent)
        _isUdpRunning.value = true
    }

    private fun stopUdp() {
        val intent = Intent(getApplication(), UdpService::class.java).apply {
            action = UdpService.ACTION_STOP
        }
        getApplication<Application>().startService(intent)
        _isUdpRunning.value = false
    }
    
    fun updateVirtualLeftStick(x: Float, y: Float) {
        // x, y are -1.0 to 1.0 from VirtualStick
        val byteX = StickTransformer.transform(x).toInt() and 0xFF
        val byteY = StickTransformer.transform(y).toInt() and 0xFF
        MsdkManager.instance.updateLeftStick(byteX, byteY)
    }

    fun updateVirtualRightStick(x: Float, y: Float) {
        val byteX = StickTransformer.transform(x).toInt() and 0xFF
        val byteY = StickTransformer.transform(y).toInt() and 0xFF
        MsdkManager.instance.updateRightStick(byteX, byteY)
    }
    
    fun updateButtonState(bitIndex: Int, isPressed: Boolean) {
        MsdkManager.instance.updateButtonMask(bitIndex, isPressed)
    }
}
