package com.example.dji_rc_pro.viewmodel

import android.app.Application
import android.content.Intent
import android.bluetooth.le.ScanResult
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

import com.example.dji_rc_pro.domain.ble.BleManager
import com.example.dji_rc_pro.domain.config.ConfigRepository
import com.example.dji_rc_pro.domain.input.StickTransformer
import com.example.dji_rc_pro.domain.input.ControllerManager
import com.example.dji_rc_pro.domain.usb.UsbHidManager
import com.example.dji_rc_pro.domain.usb.UsbHidProtocolParser
import com.example.dji_rc_pro.manager.ConnectionManager
import com.example.dji_rc_pro.manager.FrequencyManager
import com.example.dji_rc_pro.manager.HeartbeatManager
import com.example.dji_rc_pro.manager.ReconnectManager
import com.example.dji_rc_pro.manager.UdpDataLogManager
import com.example.dji_rc_pro.service.BleService
import com.example.dji_rc_pro.service.UdpService
import com.example.dji_rc_pro.util.ErrorCode
import com.example.dji_rc_pro.util.LogExportUtil
import com.example.dji_rc_pro.util.NetworkUtil
import com.example.dji_rc_pro.util.PingUtil
import android.util.Log
import timber.log.Timber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
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

    // New Managers
    val frequencyManager = FrequencyManager(application)
    val connectionManager = ConnectionManager()
    val heartbeatManager = HeartbeatManager()
    val reconnectManager = ReconnectManager()

    // USB HID
    private val usbHidManager = UsbHidManager(application)

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage.asStateFlow()

    // Dialog States
    private val _showErrorDialog = MutableStateFlow<ErrorCode?>(null)
    val showErrorDialog: StateFlow<ErrorCode?> = _showErrorDialog.asStateFlow()

    private val _showFrequencyDialog = MutableStateFlow(false)
    val showFrequencyDialog: StateFlow<Boolean> = _showFrequencyDialog.asStateFlow()

    private val _showSettingsDialog = MutableStateFlow(false)
    val showSettingsDialog: StateFlow<Boolean> = _showSettingsDialog.asStateFlow()

    private val _showBleDialog = MutableStateFlow(false)
    val showBleDialog: StateFlow<Boolean> = _showBleDialog.asStateFlow()

    private val _showUdpDataDialog = MutableStateFlow(false)
    val showUdpDataDialog: StateFlow<Boolean> = _showUdpDataDialog.asStateFlow()

    private val _exportStatus = MutableStateFlow<ExportStatus>(ExportStatus.Idle)
    val exportStatus: StateFlow<ExportStatus> = _exportStatus.asStateFlow()

    // UDP Data Log Manager
    val udpDataLogManager = UdpDataLogManager.getInstance()

    // Ping状态
    private val _pingResult = MutableStateFlow<PingUtil.PingResult?>(null)
    val pingResult: StateFlow<PingUtil.PingResult?> = _pingResult.asStateFlow()

    private val _isPinging = MutableStateFlow(false)
    val isPinging: StateFlow<Boolean> = _isPinging.asStateFlow()

    init {
        setupUsbHid()
        setupConnectionManager()
        setupFrequencyManager()
    }

    private fun setupConnectionManager() {
        viewModelScope.launch {
            connectionManager.connectionEvents.collect { event ->
                when (event) {
                    is ConnectionManager.ConnectionEvent.Error -> {
                        _showErrorDialog.value = event.errorCode
                    }
                    is ConnectionManager.ConnectionEvent.Connecting -> {
                        // Handle connecting event
                    }
                    is ConnectionManager.ConnectionEvent.Connected -> {
                        showToast("${event.transport} Connected", 1000)
                    }
                    is ConnectionManager.ConnectionEvent.Disconnected -> {
                        showToast("${event.transport} Disconnected", 1000)
                    }
                }
            }
        }

        viewModelScope.launch {
            connectionManager.connectionState.collect { state ->
                _isUdpRunning.value = state.udpState.isConnected
            }
        }
    }

    private fun setupFrequencyManager() {
        viewModelScope.launch {
            frequencyManager.frequencyHz.collect { hz ->
                Timber.d("Frequency changed to: $hz Hz")
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

    // Config
    val targetIp: StateFlow<String> = ConfigRepository.get().targetIp
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "192.168.1.83")

    val targetPort: StateFlow<Int> = ConfigRepository.get().targetPort
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 1387)

    val localPort: StateFlow<Int> = ConfigRepository.get().localPort
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 1346)

    // Local IP Address
    private val _localIpAddress = MutableStateFlow<String>(NetworkUtil.getLocalIpAddress() ?: "Unknown")
    val localIpAddress: StateFlow<String> = _localIpAddress.asStateFlow()

    // Expose controller state for UI (Virtual Sticks feedback)
    val controllerState = ControllerManager.instance.controllerState

    // Logs for UI display - using ArrayDeque for better performance
    private val _logs = MutableStateFlow<ArrayDeque<String>>(ArrayDeque(MAX_LOGS))
    val logs: StateFlow<List<String>> = _logs.map { it.toList() }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    private fun addLog(message: String) {
        val timestamp = java.text.SimpleDateFormat("HH:mm:ss.SSS", java.util.Locale.getDefault()).format(java.util.Date())
        val logMessage = "[$timestamp] $message"

        _logs.value.let { deque ->
            if (deque.size >= MAX_LOGS) {
                deque.removeFirst()
            }
            deque.addLast(logMessage)
            // Trigger StateFlow update by creating new reference
            _logs.value = ArrayDeque(deque)
        }
    }

    fun clearLogs() {
        _logs.value = ArrayDeque(MAX_LOGS)
    }

    companion object {
        private const val MAX_LOGS = 500
    }

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

    fun updateLocalPort(portStr: String) {
        val port = portStr.toIntOrNull() ?: return
        viewModelScope.launch {
            ConfigRepository.get().setLocalPort(port)
        }
    }

    /**
     * 执行Ping测试
     * @param ip 目标IP地址
     */
    fun performPing(ip: String) {
        viewModelScope.launch {
            _isPinging.value = true
            _pingResult.value = null
            try {
                val result = PingUtil.ping(ip)
                _pingResult.value = result
                Log.d("MainViewModel", "Ping result: ${result.formatResult()}")
            } catch (e: Exception) {
                Log.e("MainViewModel", "Ping failed", e)
                _pingResult.value = PingUtil.PingResult(
                    ip = ip,
                    isSuccess = false,
                    errorMessage = e.message ?: "Ping失败"
                )
            } finally {
                _isPinging.value = false
            }
        }
    }

    /**
     * 清除Ping结果
     */
    fun clearPingResult() {
        _pingResult.value = null
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
        bleManager.connect(device.device)
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
        
        // Wait for service to be created and then initialize it
        viewModelScope.launch {
            var retryCount = 0
            Log.d("MainViewModel", "Waiting for UdpService to be created...")
            while (retryCount < 10) {
                delay(100)
                val service = UdpService.getInstanceOrNull()
                Log.d("MainViewModel", "UdpService instance: $service (retry $retryCount)")
                if (service != null) {
                    Log.d("MainViewModel", "UdpService found, initializing...")
                    service.initialize(
                        freqManager = frequencyManager,
                        connManager = connectionManager,
                        hbManager = heartbeatManager,
                        rcManager = reconnectManager,
                        dataLogManager = udpDataLogManager
                    )
                    Log.d("MainViewModel", "UdpService initialized successfully")
                    break
                }
                retryCount++
            }
            if (retryCount >= 10) {
                Log.e("MainViewModel", "Failed to get UdpService instance after 10 retries")
            }
        }
        
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
        val byteX = StickTransformer.transform(x).toInt() and 0xFF
        val byteY = StickTransformer.transform(y).toInt() and 0xFF
        ControllerManager.instance.updateLeftStick(byteX, byteY)
    }

    fun updateVirtualRightStick(x: Float, y: Float) {
        val byteX = StickTransformer.transform(x).toInt() and 0xFF
        val byteY = StickTransformer.transform(y).toInt() and 0xFF
        ControllerManager.instance.updateRightStick(byteX, byteY)
    }
    
    fun updateButtonState(bitIndex: Int, isPressed: Boolean) {
        ControllerManager.instance.updateButtonMask(bitIndex, isPressed)
    }

    fun dismissErrorDialog() {
        _showErrorDialog.value = null
    }

    fun retryConnection() {
        _showErrorDialog.value?.let { errorCode ->
            try {
                val reconnectManager = this@MainViewModel.reconnectManager
                reconnectManager.startReconnect("UDP", errorCode)
            } catch (e: Exception) {
                Timber.e(e, "Retry connection failed")
            }
        }
        _showErrorDialog.value = null
    }

    fun showFrequencyDialog() {
        _showFrequencyDialog.value = true
    }

    fun dismissFrequencyDialog() {
        _showFrequencyDialog.value = false
    }

    fun showSettingsDialog() {
        _showSettingsDialog.value = true
    }

    fun dismissSettingsDialog() {
        _showSettingsDialog.value = false
    }

    fun showBleDialog() {
        _showBleDialog.value = true
    }

    fun dismissBleDialog() {
        _showBleDialog.value = false
    }

    fun showUdpDataDialog() {
        _showUdpDataDialog.value = true
    }

    fun dismissUdpDataDialog() {
        _showUdpDataDialog.value = false
    }

    fun clearUdpDataLogs() {
        udpDataLogManager.clearLogs()
    }

    fun exportLogs() {
        viewModelScope.launch {
            _exportStatus.value = ExportStatus.Exporting
            val result = LogExportUtil.exportCurrentLogs(getApplication())
            result.fold(
                onSuccess = { file ->
                    _exportStatus.value = ExportStatus.Success(file.name)
                    LogExportUtil.shareFile(getApplication(), file, "Share Logs")
                    showToast("Logs exported successfully", 2000)
                },
                onFailure = { error ->
                    _exportStatus.value = ExportStatus.Error(error.message ?: "Unknown error")
                    showToast("Failed to export logs: ${error.message}", 2000)
                }
            )
        }
    }

    fun clearExportStatus() {
        _exportStatus.value = ExportStatus.Idle
    }

    fun getLogFiles(): List<LogExportUtil.FileInfo> {
        return LogExportUtil.getLogFiles(getApplication())
    }

    fun getCrashFiles(): List<LogExportUtil.FileInfo> {
        return LogExportUtil.getCrashFiles(getApplication())
    }

    fun shareLogFile(fileInfo: LogExportUtil.FileInfo) {
        val file = java.io.File(fileInfo.path)
        if (file.exists()) {
            LogExportUtil.shareFile(getApplication(), file, "Share Log")
        } else {
            showToast("Log file not found", 2000)
        }
    }

    fun shareCrashFile(fileInfo: LogExportUtil.FileInfo) {
        val file = java.io.File(fileInfo.path)
        if (file.exists()) {
            LogExportUtil.shareFile(getApplication(), file, "Share Crash Report")
        } else {
            showToast("Crash file not found", 2000)
        }
    }

    fun deleteLogFile(fileInfo: LogExportUtil.FileInfo) {
        viewModelScope.launch {
            val success = LogExportUtil.deleteLogFile(getApplication(), fileInfo.name)
            if (success) {
                showToast("Log deleted", 1000)
            } else {
                showToast("Failed to delete log", 1000)
            }
        }
    }

    private fun setupUsbHid() {
        usbHidManager.onDataReceived = { data, len ->
            // 解析USB HID数据
            UsbHidProtocolParser.parse(data, len)
            
            // 将USB HID原始数据通过UDP发送
            if (_isUdpRunning.value) {
                val udpService = UdpService.getInstanceOrNull()
                if (udpService != null) {
                    udpService.addUsbHidData(data.copyOf(len))
                }
            }
        }
        usbHidManager.onStatusChanged = { status, isConnected ->
            showToast(status, 1000)
            if (isConnected) {
                usbHidManager.startReading()
            }
        }
        usbHidManager.onError = { error ->
            showToast("USB Error: $error", 2000)
        }

        viewModelScope.launch {
            if (usbHidManager.findDevice()) {
                usbHidManager.requestPermission()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        usbHidManager.release()
        frequencyManager.shutdown()
        heartbeatManager.shutdown()
        reconnectManager.shutdown()
    }
}

sealed class ExportStatus {
    data object Idle : ExportStatus()
    data object Exporting : ExportStatus()
    data class Success(val fileName: String) : ExportStatus()
    data class Error(val message: String) : ExportStatus()
}
