package com.example.dji_rc_pro.viewmodel

import android.app.Application
import android.bluetooth.BluetoothProfile
import android.content.Intent
import android.os.Build
import android.bluetooth.le.ScanResult
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

import com.example.dji_rc_pro.domain.ble.BleManager
import com.example.dji_rc_pro.domain.ble.Ros2BleProfile
import com.example.dji_rc_pro.domain.config.ConnectionMode
import com.example.dji_rc_pro.domain.config.ConfigRepository
import com.example.dji_rc_pro.domain.config.DebugLaunchOverrideStore
import com.example.dji_rc_pro.domain.config.TransportIsolationMode
import com.example.dji_rc_pro.domain.discovery.DiscoveryEvent
import com.example.dji_rc_pro.domain.discovery.DiscoveryProtocol
import com.example.dji_rc_pro.domain.discovery.HostSelectionDecision
import com.example.dji_rc_pro.domain.discovery.WifiDiscoveryManager
import com.example.dji_rc_pro.domain.discovery.WifiPairingSelector
import com.example.dji_rc_pro.domain.input.StickTransformer
import com.example.dji_rc_pro.domain.input.ControllerManager
import com.example.dji_rc_pro.domain.usb.UsbHidManager
import com.example.dji_rc_pro.domain.usb.UsbHidProtocolParser
import com.example.dji_rc_pro.manager.ConnectionManager
import com.example.dji_rc_pro.manager.DataLogManager
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
import com.example.dji_rc_pro.util.UdpEndpointConfig
import android.util.Log
import timber.log.Timber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val configRepository = ConfigRepository.get()
    private val wifiDiscoveryManager = WifiDiscoveryManager(application)
    private var launchOverrides = DebugLaunchOverrideStore.current()
    
    // UI State
    private val _isUdpRunning = MutableStateFlow(false)
    val isUdpRunning: StateFlow<Boolean> = _isUdpRunning.asStateFlow()
    
    // BLE State
    private val bleManager = BleManager.getInstance(application)
    val bleScanResults: StateFlow<List<BleManager.BleDevice>> = bleManager.scanResults
    val bleConnectionState: StateFlow<Int> = bleManager.connectionState
    val isBleScanning: StateFlow<Boolean> = bleManager.isScanning
    val bleCurrentRssi: StateFlow<Int?> = bleManager.currentRssi
    val bleConnectedDeviceAddress: StateFlow<String?> = bleManager.connectedDeviceAddress
    val bleRos2Session: StateFlow<Ros2BleProfile.GatewaySession> = bleManager.ros2GatewaySession

    // New Managers
    val frequencyManager = FrequencyManager(application)
    val connectionManager = ConnectionManager()
    val heartbeatManager = HeartbeatManager()
    val reconnectManager = ReconnectManager()

    // Unified Data Log Manager (replaces UdpDataLogManager)
    val dataLogManager = DataLogManager.getInstance()

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

    private val _showDataLogDialog = MutableStateFlow(false)
    val showDataLogDialog: StateFlow<Boolean> = _showDataLogDialog.asStateFlow()

    private val _exportStatus = MutableStateFlow<ExportStatus>(ExportStatus.Idle)
    val exportStatus: StateFlow<ExportStatus> = _exportStatus.asStateFlow()

    // Legacy UDP Data Log Manager (for backward compatibility)
    @Deprecated("Use dataLogManager instead")
    val udpDataLogManager = UdpDataLogManager.getInstance()

    // Ping状态
    private val _pingResult = MutableStateFlow<PingUtil.PingResult?>(null)
    val pingResult: StateFlow<PingUtil.PingResult?> = _pingResult.asStateFlow()

    private val _isPinging = MutableStateFlow(false)
    val isPinging: StateFlow<Boolean> = _isPinging.asStateFlow()

    private val _autoPairStatus = MutableStateFlow("正在发现主机")
    val autoPairStatus: StateFlow<String> = _autoPairStatus.asStateFlow()

    private val _bleGatewayStatus = MutableStateFlow("BLE 网关未连接")
    val bleGatewayStatus: StateFlow<String> = _bleGatewayStatus.asStateFlow()

    private val _showHostSelectionDialog = MutableStateFlow(false)
    val showHostSelectionDialog: StateFlow<Boolean> = _showHostSelectionDialog.asStateFlow()

    private val _hostSelectionCandidates = MutableStateFlow<List<DiscoveryProtocol.DiscoveredHost>>(emptyList())
    val hostSelectionCandidates: StateFlow<List<DiscoveryProtocol.DiscoveredHost>> = _hostSelectionCandidates.asStateFlow()

    private var autoPairPaused = false
    private var pendingPairHostId: String? = null
    private var pendingPairTimeoutJob: Job? = null
    private var udpStartInProgress = false
    private var autoBleConnectAddress: String? = null
    private var lastAutoBleConnectAtMs: Long = 0L
    private var lastAppliedBleTargetKey: String? = null
    private var bleServiceRetryJob: Job? = null
    private var bleServiceRetryCount = 0

    private fun shouldUseWifiAutoPair(mode: ConnectionMode, transportMode: TransportIsolationMode): Boolean {
        return effectiveConnectionMode(mode) == ConnectionMode.AUTO_PAIR &&
            effectiveTransportMode(transportMode).allowsWifiDiscovery
    }

    private fun shouldUseBleAutoPair(mode: ConnectionMode, transportMode: TransportIsolationMode): Boolean {
        val resolvedTransportMode = effectiveTransportMode(transportMode)
        return resolvedTransportMode.allowsBle &&
            (resolvedTransportMode == TransportIsolationMode.BLE_ONLY ||
                effectiveConnectionMode(mode) == ConnectionMode.AUTO_PAIR)
    }

    private fun effectiveTransportMode(mode: TransportIsolationMode = transportIsolationMode.value): TransportIsolationMode {
        return launchOverrides?.transportMode ?: mode
    }

    private fun effectiveConnectionMode(mode: ConnectionMode = connectionMode.value): ConnectionMode {
        return launchOverrides?.connectionMode ?: mode
    }

    private fun effectivePairCode(value: String = pairCode.value): String {
        return launchOverrides?.pairCode ?: value
    }

    private fun effectiveTargetIp(value: String = targetIp.value): String {
        return launchOverrides?.targetIp ?: value
    }

    private fun effectiveTargetPort(value: Int = targetPort.value): Int {
        return launchOverrides?.targetPort ?: value
    }

    private fun clearLaunchOverrides() {
        launchOverrides = null
        DebugLaunchOverrideStore.clear()
    }

    private fun shouldManageBleAutoScan(
        mode: ConnectionMode = connectionMode.value,
        transportMode: TransportIsolationMode = transportIsolationMode.value
    ): Boolean {
        return shouldUseBleAutoPair(mode, transportMode)
    }

    private fun canStartBleScan(
        connectionState: Int = bleConnectionState.value,
        scanning: Boolean = bleManager.isScanning.value
    ): Boolean {
        return !scanning && connectionState == BluetoothProfile.STATE_DISCONNECTED
    }

    private fun selectBestRos2Gateway(scanResults: List<BleManager.BleDevice>): BleManager.BleDevice? {
        return scanResults
            .filter { it.isRos2Gateway }
            .maxByOrNull { it.rssi }
    }

    private fun shouldAutoConnectBleGateway(deviceAddress: String, nowMs: Long): Boolean {
        return autoBleConnectAddress != deviceAddress ||
            nowMs - lastAutoBleConnectAtMs > 5000L
    }

    private fun showSearchingBleGatewayStatus() {
        _bleGatewayStatus.value = "正在搜索 ROS2 BLE 网关"
    }

    private fun handleMissingBleGateway(state: BleAutoState) {
        if (canStartBleScan(state.connectionState, state.isScanning)) {
            bleManager.startScan()
        }
        showSearchingBleGatewayStatus()
    }

    private fun connectToBleGateway(device: BleManager.BleDevice, nowMs: Long) {
        autoBleConnectAddress = device.device.address
        lastAutoBleConnectAtMs = nowMs
        _bleGatewayStatus.value = "正在连接 ${device.device.name ?: device.device.address}"
        connectBle(device)
    }

    private fun canStartUdp(mode: TransportIsolationMode = transportIsolationMode.value): Boolean {
        return effectiveTransportMode(mode).allowsUdp && !_isUdpRunning.value && !udpStartInProgress
    }

    private fun startServiceSafely(intent: Intent, label: String, useForegroundStart: Boolean = false): Boolean {
        return try {
            val application = getApplication<Application>()
            if (useForegroundStart && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                application.startForegroundService(intent)
            } else {
                application.startService(intent)
            }
            true
        } catch (error: IllegalStateException) {
            Log.w("MainViewModel", "Skipping $label while app is backgrounded", error)
            false
        }
    }

    private fun ensureBleAllowedForUserAction(): Boolean {
        if (effectiveTransportMode().allowsBle) {
            return true
        }
        showToast("当前模式已禁用 BLE", 1500)
        return false
    }

    private fun bleDisabledStatusMessage(): String = "BLE 已禁用（UDP Only）"

    private fun wifiAutoPairDisabledStatusMessage(): String = "BLE Only 模式，Wi‑Fi 自动配对已停用"

    private fun manualModeStatusMessage(): String = "手动模式"

    private fun connectedAutoPairStatus(): String {
        return pairedHostName.value?.let { "已连接 $it" } ?: "已连接"
    }

    private fun pairingInProgressStatus(hostName: String): String = "正在配对 $hostName"

    private fun pauseAutoPairingStatus(): String = "自动配对已暂停"

    private fun unavailableHostStatus(reason: String?): String {
        return if (reason == "not_ready") "主机未就绪" else "主机忙碌中"
    }

    private fun discoveredHostsStatus(hosts: List<DiscoveryProtocol.DiscoveredHost>): String {
        val readyHosts = hosts.count { it.ready && !it.busy }
        return when {
            readyHosts == 0 && hosts.any { it.busy } -> "主机忙碌中"
            readyHosts == 0 && hosts.any { !it.ready } -> "主机未就绪"
            readyHosts == 0 -> "正在发现主机"
            readyHosts == 1 -> "发现 1 台可用主机"
            else -> "发现 ${readyHosts} 台可用主机"
        }
    }

    private fun isBleConnectedOrConnecting(connectionState: Int = bleConnectionState.value): Boolean {
        return connectionState == BluetoothProfile.STATE_CONNECTED ||
            connectionState == BluetoothProfile.STATE_CONNECTING
    }

    private fun pauseAutoPairing(status: String = pauseAutoPairingStatus()) {
        autoPairPaused = true
        _autoPairStatus.value = status
    }

    private fun resumeWifiAutoPairing() {
        autoPairPaused = false
        viewModelScope.launch {
            startAutoDiscovery()
            evaluateAutoPairing(discoveredHosts.value)
        }
    }

    private fun primaryTransportRunning(mode: TransportIsolationMode): Boolean {
        return when (effectiveTransportMode(mode)) {
            TransportIsolationMode.BLE_ONLY -> bleConnectionState.value == BluetoothProfile.STATE_CONNECTED
            TransportIsolationMode.UDP_ONLY,
            TransportIsolationMode.BLE_UDP -> _isUdpRunning.value
        }
    }

    private fun updateTransportModeDependentState(mode: TransportIsolationMode) {
        when (effectiveTransportMode(mode)) {
            TransportIsolationMode.UDP_ONLY -> {
                bleManager.stopScan()
                stopBleService()
                bleManager.disconnect()
                lastAppliedBleTargetKey = null
                _bleGatewayStatus.value = bleDisabledStatusMessage()
            }

            TransportIsolationMode.BLE_ONLY -> {
                wifiDiscoveryManager.stop()
                pendingPairTimeoutJob?.cancel()
                pendingPairHostId = null
                autoPairPaused = false
                if (_isUdpRunning.value) {
                    stopUdp()
                }
                _autoPairStatus.value = wifiAutoPairDisabledStatusMessage()
                _bleGatewayStatus.value = if (isBleConnectedOrConnecting()) {
                    "BLE 已连接，等待短码配对"
                } else {
                    "正在搜索 ROS2 BLE 网关"
                }
                if (!isBleConnectedOrConnecting() && !bleManager.isScanning.value) {
                    bleManager.startScan()
                }
            }

            TransportIsolationMode.BLE_UDP -> {
                if (shouldManageBleAutoScan() && canStartBleScan()) {
                    bleManager.startScan()
                }
                if (bleConnectionState.value == BluetoothProfile.STATE_DISCONNECTED && bleManager.isScanning.value) {
                    _bleGatewayStatus.value = "正在搜索 ROS2 BLE 网关"
                }
            }
        }
    }

    private fun initializeBleManager() {
        bleManager.initialize(
            connManager = connectionManager,
            hbManager = heartbeatManager,
            rcManager = reconnectManager,
            freqManager = frequencyManager,
            dataLogMgr = dataLogManager
        )
    }

    private fun discoveryBootstrapTargets(): Set<String> {
        val targets = linkedSetOf<String>()
        pairedHostAddress.value?.takeIf { it.isNotBlank() }?.let { targets += it }
        effectiveTargetIp()
            .takeIf { it.isNotBlank() && it != "198.51.100.83" }
            ?.let { targets += it }
        return targets
    }

    private suspend fun getStableClientId(): String {
        return clientId.value.ifBlank { configRepository.getOrCreateClientId() }
    }

    private suspend fun startAutoDiscovery() {
        if (!effectiveTransportMode().allowsWifiDiscovery) {
            return
        }
        val stableClientId = getStableClientId()
        wifiDiscoveryManager.start(stableClientId, effectivePairCode(), discoveryBootstrapTargets())
    }

    private fun setupAutoPairing() {
        viewModelScope.launch {
            configRepository.getOrCreateClientId()
        }

        viewModelScope.launch {
            combine(connectionMode, pairCode, clientId, transportIsolationMode) { mode, code, currentClientId, transportMode ->
                AutoPairConfig(mode, code, currentClientId, transportMode)
            }.collect { config ->
                pendingPairTimeoutJob?.cancel()
                pendingPairHostId = null

                if (shouldUseWifiAutoPair(config.mode, config.transportMode)) {
                    autoPairPaused = false
                    val stableClientId = config.clientId.ifBlank { getStableClientId() }
                    wifiDiscoveryManager.start(stableClientId, config.pairCode, discoveryBootstrapTargets())
                    updateAutoPairStatus(discoveredHosts.value)
                    evaluateAutoPairing(discoveredHosts.value)
                } else {
                    wifiDiscoveryManager.stop()
                    _showHostSelectionDialog.value = false
                    _hostSelectionCandidates.value = emptyList()
                    _autoPairStatus.value = if (config.transportMode == TransportIsolationMode.BLE_ONLY) {
                        wifiAutoPairDisabledStatusMessage()
                    } else {
                        manualModeStatusMessage()
                    }
                }
            }
        }

        viewModelScope.launch {
            discoveredHosts.collect { hosts ->
                updateAutoPairStatus(hosts)
                evaluateAutoPairing(hosts)
            }
        }

        viewModelScope.launch {
            wifiDiscoveryManager.events.collect { event ->
                when (event) {
                    is DiscoveryEvent.PairAcknowledged -> handlePairAcknowledged(event.ack)
                    is DiscoveryEvent.PairRejected -> handlePairRejected(event.busy)
                    is DiscoveryEvent.Error -> showToast(event.message, 2000)
                }
            }
        }
    }

    private fun evaluateAutoPairing(hosts: List<DiscoveryProtocol.DiscoveredHost>) {
        val currentTransportMode = effectiveTransportMode()
        if (!currentTransportMode.allowsWifiDiscovery ||
            effectiveConnectionMode() != ConnectionMode.AUTO_PAIR ||
            autoPairPaused ||
            _isUdpRunning.value ||
            udpStartInProgress) {
            return
        }
        if (pendingPairHostId != null) {
            return
        }

        when (val decision = WifiPairingSelector.selectHost(pairedHostId.value, hosts)) {
            is HostSelectionDecision.AutoSelected -> requestPairing(decision.host)
            is HostSelectionDecision.RequiresUserChoice -> {
                _hostSelectionCandidates.value = decision.hosts
                _showHostSelectionDialog.value = true
                _autoPairStatus.value = "发现多台主机，等待选择"
            }
            HostSelectionDecision.WaitForDiscovery -> {
                if (!_showHostSelectionDialog.value) {
                    _hostSelectionCandidates.value = emptyList()
                }
            }
        }
    }

    private fun updateAutoPairStatus(hosts: List<DiscoveryProtocol.DiscoveredHost>) {
        if (!effectiveTransportMode().allowsWifiDiscovery) {
            _autoPairStatus.value = wifiAutoPairDisabledStatusMessage()
            return
        }

        if (effectiveConnectionMode() == ConnectionMode.MANUAL) {
            _autoPairStatus.value = manualModeStatusMessage()
            return
        }

        if (_isUdpRunning.value) {
            _autoPairStatus.value = connectedAutoPairStatus()
            return
        }

        if (autoPairPaused) {
            _autoPairStatus.value = pauseAutoPairingStatus()
            return
        }

        pendingPairHostId?.let { hostId ->
            val hostName = hosts.firstOrNull { it.hostId == hostId }?.hostName ?: pairedHostName.value ?: hostId
            _autoPairStatus.value = pairingInProgressStatus(hostName)
            return
        }

        _autoPairStatus.value = discoveredHostsStatus(hosts)
    }

    private fun requestPairing(host: DiscoveryProtocol.DiscoveredHost) {
        viewModelScope.launch {
            pendingPairHostId = host.hostId
            _showHostSelectionDialog.value = false
            _hostSelectionCandidates.value = emptyList()
            _autoPairStatus.value = "正在配对 ${host.hostName}"

            wifiDiscoveryManager.requestPairing(host)

            pendingPairTimeoutJob?.cancel()
            pendingPairTimeoutJob = launch {
                delay(2500)
                if (pendingPairHostId == host.hostId && !_isUdpRunning.value) {
                    pendingPairHostId = null
                    updateAutoPairStatus(discoveredHosts.value)
                    evaluateAutoPairing(discoveredHosts.value)
                }
            }
        }
    }

    private fun handlePairAcknowledged(ack: DiscoveryProtocol.PairAck) {
        viewModelScope.launch {
            pendingPairTimeoutJob?.cancel()
            pendingPairHostId = null

            val hostInfo = discoveredHosts.value.firstOrNull { it.hostId == ack.hostId }
            val hostName = hostInfo?.hostName ?: pairedHostName.value ?: ack.hostId
            val ipv4Address = hostInfo?.ipv4Address
                ?: ack.address.takeIf { ack.selectedFamily == DiscoveryProtocol.AddressFamily.IPV4 }
            val ipv6Address = hostInfo?.ipv6Address
                ?: ack.address.takeIf { ack.selectedFamily == DiscoveryProtocol.AddressFamily.IPV6 }

            configRepository.savePairedHost(
                hostId = ack.hostId,
                hostName = hostName,
                ipv4Address = ipv4Address,
                ipv6Address = ipv6Address,
                preferredFamily = ack.selectedFamily,
                controlPort = ack.controlPort
            )
            configRepository.setTargetIp(ack.address)
            configRepository.setTargetPort(ack.controlPort)

            _autoPairStatus.value = "已配对 $hostName"
            if (canStartUdp()) {
                startUdp()
            }
        }
    }

    private fun handlePairRejected(busy: DiscoveryProtocol.PairBusy) {
        pendingPairTimeoutJob?.cancel()
        pendingPairHostId = null
        _autoPairStatus.value = unavailableHostStatus(busy.reason)
        showToast("${busy.hostId} 当前不可配对", 2000)
    }

    private fun setupBleRos2Bridge() {
        viewModelScope.launch {
            bleRos2Session.collect { session ->
                updateBleGatewayStatus(session)
                applyBleGatewayTarget(session)
            }
        }

        viewModelScope.launch {
            combine(connectionMode, transportIsolationMode, bleScanResults, bleConnectionState, isBleScanning) { mode, transportMode, results, state, scanning ->
                BleAutoState(mode, transportMode, results, state, scanning)
            }.collect { state ->
                if (!shouldManageBleAutoScan(state.mode, state.transportMode)) {
                    if (state.transportMode == TransportIsolationMode.UDP_ONLY) {
                        bleManager.stopScan()
                    }
                    return@collect
                }

                if (state.connectionState == BluetoothProfile.STATE_CONNECTED) {
                    startBleService()
                    updateBleGatewayStatus(bleRos2Session.value)
                    return@collect
                }

                if (state.connectionState == BluetoothProfile.STATE_CONNECTING) {
                    updateBleGatewayStatus(bleRos2Session.value)
                    return@collect
                }

                val ros2Gateway = selectBestRos2Gateway(state.scanResults)

                if (ros2Gateway == null) {
                    handleMissingBleGateway(state)
                    return@collect
                }

                val now = System.currentTimeMillis()
                if (shouldAutoConnectBleGateway(ros2Gateway.device.address, now)) {
                    connectToBleGateway(ros2Gateway, now)
                }
            }
        }
    }

    private suspend fun applyBleGatewayTarget(session: Ros2BleProfile.GatewaySession) {
        val transportMode = effectiveTransportMode()
        if (!transportMode.allowsBle || !session.paired) {
            return
        }

        val selectedAddress = session.effectiveAddress ?: return
        val selectedFamily = session.selectedFamily
            ?: session.preferredFamily
            ?: DiscoveryProtocol.choosePreferredFamily(session.ipv4Address, session.ipv6Address)
        val hostId = session.hostId.ifBlank { "ble_gateway" }
        val hostName = session.hostName.ifBlank { "ROS2 BLE Gateway" }
        val targetKey = listOf(hostId, session.sessionId.orEmpty(), selectedAddress, session.controlPort).joinToString("|")
        if (targetKey == lastAppliedBleTargetKey) {
            return
        }

        configRepository.savePairedHost(
            hostId = hostId,
            hostName = hostName,
            ipv4Address = session.ipv4Address,
            ipv6Address = session.ipv6Address,
            preferredFamily = selectedFamily,
            controlPort = session.controlPort
        )
        configRepository.setTargetIp(selectedAddress)
        configRepository.setTargetPort(session.controlPort)
        if (transportMode.allowsUdp) {
            UdpService.getInstanceOrNull()?.updateTarget(selectedAddress, session.controlPort)
        }
        lastAppliedBleTargetKey = targetKey

        if (transportMode.shouldAutoStartUdpFromBle && canStartUdp(transportMode)) {
            startUdp()
        }
    }

    private fun updateBleGatewayStatus(session: Ros2BleProfile.GatewaySession) {
        _bleGatewayStatus.value = when {
            !effectiveTransportMode().allowsBle -> bleDisabledStatusMessage()
            bleConnectionState.value == BluetoothProfile.STATE_CONNECTING -> "BLE 正在连接 ROS2 网关"
            effectiveTransportMode() == TransportIsolationMode.BLE_ONLY && session.bleOnlyControlReady ->
                "BLE 已授权，纯 BLE 控制中"
            session.paired && session.udpReady -> {
                val address = session.effectiveAddress ?: "-"
                "BLE 已配对，UDP主通道: $address:${session.controlPort}"
            }
            session.paired -> {
                val address = session.effectiveAddress ?: "-"
                "BLE 已配对，BLE兜底中: $address:${session.controlPort}"
            }
            bleConnectionState.value == BluetoothProfile.STATE_CONNECTED -> "BLE 已连接，等待短码配对"
            isBleScanning.value -> "正在搜索 ROS2 BLE 网关"
            else -> "BLE 网关未连接"
        }
    }

    private data class BleAutoState(
        val mode: ConnectionMode,
        val transportMode: TransportIsolationMode,
        val scanResults: List<BleManager.BleDevice>,
        val connectionState: Int,
        val isScanning: Boolean
    )

    private data class AutoPairConfig(
        val mode: ConnectionMode,
        val pairCode: String,
        val clientId: String,
        val transportMode: TransportIsolationMode
    )

    private fun setupConnectionManager() {
        viewModelScope.launch {
            connectionManager.connectionEvents.collect { event ->
                when (event) {
                    is ConnectionManager.ConnectionEvent.Error -> {
                        udpStartInProgress = false
                        _showErrorDialog.value = event.errorCode
                    }
                    is ConnectionManager.ConnectionEvent.Connecting -> {
                        // Handle connecting event
                    }
                    is ConnectionManager.ConnectionEvent.Connected -> {
                        showToast("${event.transport} Connected", 1000)
                    }
                    is ConnectionManager.ConnectionEvent.Disconnected -> {
                        udpStartInProgress = false
                        showToast("${event.transport} Disconnected", 1000)
                    }
                }
            }
        }

        viewModelScope.launch {
            connectionManager.connectionState.collect { state ->
                _isUdpRunning.value = state.udpState.isConnected
                if (state.udpState.isConnected) {
                    udpStartInProgress = false
                }
                if (!state.udpState.isConnected &&
                    shouldUseWifiAutoPair(connectionMode.value, transportIsolationMode.value) &&
                    !autoPairPaused) {
                    startAutoDiscovery()
                    evaluateAutoPairing(discoveredHosts.value)
                }
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
    val targetIp: StateFlow<String> = configRepository.targetIp
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "198.51.100.83")

    val targetPort: StateFlow<Int> = configRepository.targetPort
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 1387)

    val localPort: StateFlow<Int> = configRepository.localPort
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 1346)

    val connectionMode: StateFlow<ConnectionMode> = configRepository.connectionMode
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ConnectionMode.AUTO_PAIR)

    val transportIsolationMode: StateFlow<TransportIsolationMode> = configRepository.transportIsolationMode
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TransportIsolationMode.BLE_UDP)

    val clientId: StateFlow<String> = configRepository.clientId
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")

    val pairCode: StateFlow<String> = configRepository.pairCode
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DiscoveryProtocol.DEFAULT_PAIR_CODE)

    val pairedHostId: StateFlow<String?> = configRepository.pairedHostId
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val pairedHostName: StateFlow<String?> = configRepository.pairedHostName
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val pairedHostAddress: StateFlow<String?> = configRepository.pairedHostAddress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val pairedControlPort: StateFlow<Int?> = configRepository.pairedControlPort
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val discoveredHosts: StateFlow<List<DiscoveryProtocol.DiscoveredHost>> = wifiDiscoveryManager.discoveredHosts

    init {
        setupUsbHid()
        setupConnectionManager()
        setupFrequencyManager()
        initializeBleManager()
        viewModelScope.launch {
            transportIsolationMode.collect { mode ->
                updateTransportModeDependentState(mode)
            }
        }
        setupAutoPairing()
        setupBleRos2Bridge()
    }

    // Local IP Address
    private val _localIpAddress = MutableStateFlow<String>(NetworkUtil.getLocalIpAddress() ?: "Unknown")
    val localIpAddress: StateFlow<String> = _localIpAddress.asStateFlow()

    private val _udpValidationError = MutableStateFlow<String?>(null)
    val udpValidationError: StateFlow<String?> = _udpValidationError.asStateFlow()

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
        private const val BLE_SERVICE_START_MAX_RETRIES = 6
    }

    fun updateTargetIp(ip: String) {
        clearLaunchOverrides()
        viewModelScope.launch {
            configRepository.setTargetIp(UdpEndpointConfig.normalizeTargetAddress(ip))
        }
    }

    fun updateTargetPort(portStr: String): Boolean {
        val port = UdpEndpointConfig.parsePort(portStr) ?: return false
        clearLaunchOverrides()
        viewModelScope.launch {
            configRepository.setTargetPort(port)
        }
        return true
    }

    fun updateLocalPort(portStr: String): Boolean {
        val port = UdpEndpointConfig.parsePort(portStr) ?: return false
        viewModelScope.launch {
            configRepository.setLocalPort(port)
        }
        return true
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
                val normalizedAddress = UdpEndpointConfig.normalizeTargetAddress(ip)
                if (normalizedAddress.isBlank()) {
                    _pingResult.value = PingUtil.PingResult(
                        ip = ip,
                        isSuccess = false,
                        errorMessage = "目标地址不能为空"
                    )
                    return@launch
                }
                val result = PingUtil.ping(normalizedAddress)
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

    fun clearUdpValidationError() {
        _udpValidationError.value = null
    }

    fun setConnectionMode(mode: ConnectionMode) {
        if (effectiveTransportMode() == TransportIsolationMode.BLE_ONLY && mode == ConnectionMode.MANUAL) {
            showToast("BLE Only 模式固定使用 Auto Pair", 1800)
            return
        }
        clearLaunchOverrides()
        viewModelScope.launch {
            configRepository.setConnectionMode(mode)
        }
    }

    fun setTransportIsolationMode(mode: TransportIsolationMode) {
        clearLaunchOverrides()
        viewModelScope.launch {
            configRepository.setTransportIsolationMode(mode)
            if (mode == TransportIsolationMode.BLE_ONLY) {
                configRepository.setConnectionMode(ConnectionMode.AUTO_PAIR)
            }
        }
    }

    fun setPairCode(value: String) {
        clearLaunchOverrides()
        viewModelScope.launch {
            configRepository.setPairCode(value.trim())
        }
    }

    fun selectDiscoveredHost(host: DiscoveryProtocol.DiscoveredHost) {
        autoPairPaused = false
        requestPairing(host)
    }

    fun dismissHostSelectionDialog() {
        _showHostSelectionDialog.value = false
        _hostSelectionCandidates.value = emptyList()
        pauseAutoPairing()
    }

    fun forgetPairing() {
        viewModelScope.launch {
            configRepository.clearPairing()
            pendingPairHostId = null
            pendingPairTimeoutJob?.cancel()
            if (!primaryTransportRunning(transportIsolationMode.value)) {
                updateAutoPairStatus(discoveredHosts.value)
            }
        }
    }

    fun toggleUdp() {
        when (effectiveTransportMode()) {
            TransportIsolationMode.BLE_ONLY -> {
                if (isBleConnectedOrConnecting()) {
                    pauseAutoPairing()
                    disconnectBle()
                    _bleGatewayStatus.value = "BLE 自动连接已暂停"
                } else {
                    autoPairPaused = false
                    if (effectiveConnectionMode() == ConnectionMode.AUTO_PAIR) {
                        bleManager.startScan()
                    }
                }
                return
            }

            TransportIsolationMode.UDP_ONLY,
            TransportIsolationMode.BLE_UDP -> Unit
        }

        if (effectiveConnectionMode() == ConnectionMode.AUTO_PAIR) {
            if (_isUdpRunning.value) {
                pauseAutoPairing()
                viewModelScope.launch {
                    val stableClientId = getStableClientId()
                    wifiDiscoveryManager.sendUnpair(stableClientId)
                }
                stopUdp()
            } else {
                resumeWifiAutoPairing()
            }
            return
        }

        if (_isUdpRunning.value) {
            stopUdp()
        } else {
            startUdp()
        }
    }
    
    // BLE Actions
    fun startBleScan() {
        if (!ensureBleAllowedForUserAction()) {
            return
        }
        _bleGatewayStatus.value = "正在搜索 ROS2 BLE 网关"
        bleManager.startScan()
    }

    fun stopBleScan() {
        bleManager.stopScan()
    }

    fun connectBle(device: BleManager.BleDevice) {
        if (!ensureBleAllowedForUserAction()) {
            return
        }
        _bleGatewayStatus.value = "正在连接 ${device.device.name ?: device.device.address}"
        bleManager.connect(device.device)
    }
    
    fun disconnectBle() {
        bleManager.disconnect()
        stopBleService()
    }
    
    private fun startBleService() {
        if (!effectiveTransportMode().allowsBle) {
            return
        }
        val intent = Intent(getApplication(), BleService::class.java).apply {
            action = BleService.ACTION_START
        }
        if (startServiceSafely(intent, "BleService start", useForegroundStart = true)) {
            bleServiceRetryJob?.cancel()
            bleServiceRetryJob = null
            bleServiceRetryCount = 0
        } else {
            scheduleBleServiceRetry()
        }
    }

    private fun stopBleService() {
        bleServiceRetryJob?.cancel()
        bleServiceRetryJob = null
        bleServiceRetryCount = 0
        val intent = Intent(getApplication(), BleService::class.java).apply {
            action = BleService.ACTION_STOP
        }
        startServiceSafely(intent, "BleService stop")
    }

    private fun scheduleBleServiceRetry() {
        if (bleServiceRetryCount >= BLE_SERVICE_START_MAX_RETRIES) {
            return
        }
        bleServiceRetryJob?.cancel()
        bleServiceRetryJob = viewModelScope.launch {
            bleServiceRetryCount += 1
            delay(500)
            if (effectiveTransportMode().allowsBle &&
                bleConnectionState.value == BluetoothProfile.STATE_CONNECTED) {
                startBleService()
            }
        }
    }
    
    fun toggleVideo() {
        _isVideoEnabled.value = !_isVideoEnabled.value
    }

    private fun startUdp() {
        if (!effectiveTransportMode().allowsUdp) {
            udpStartInProgress = false
            _udpValidationError.value = "当前模式已禁用 UDP"
            return
        }
        udpStartInProgress = true
        viewModelScope.launch(Dispatchers.IO) {
            val validationResult = UdpEndpointConfig.validate(
                targetAddress = effectiveTargetIp(),
                targetPort = effectiveTargetPort().toString(),
                localPort = localPort.value.toString()
            )

            validationResult.fold(
                onSuccess = { validated ->
                    _udpValidationError.value = null
                    updateTargetIp(validated.targetAddress)

                    val intent = Intent(getApplication(), UdpService::class.java).apply {
                        action = UdpService.ACTION_START
                    }
                    if (!startServiceSafely(intent, "UdpService start", useForegroundStart = true)) {
                        udpStartInProgress = false
                    } else {
                        // Wait for service to be created and then initialize it
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
                                    dataLogManager = dataLogManager
                                )
                                Log.d("MainViewModel", "UdpService initialized successfully")
                                break
                            }
                            retryCount++
                        }
                        if (retryCount >= 10) {
                            udpStartInProgress = false
                            Log.e("MainViewModel", "Failed to get UdpService instance after 10 retries")
                        }

                        _isUdpRunning.value = true
                    }
                },
                onFailure = { error ->
                    udpStartInProgress = false
                    val message = error.message ?: "UDP 参数无效"
                    _udpValidationError.value = message
                    showToast(message, 2000)
                }
            )
        }
    }

    private fun stopUdp() {
        val intent = Intent(getApplication(), UdpService::class.java).apply {
            action = UdpService.ACTION_STOP
        }
        startServiceSafely(intent, "UdpService stop")
        udpStartInProgress = false
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
        if (!ensureBleAllowedForUserAction()) {
            return
        }
        if (!isBleConnectedOrConnecting() && !bleManager.isScanning.value) {
            bleManager.startScan()
            _bleGatewayStatus.value = "正在搜索 ROS2 BLE 网关"
        }
        _showBleDialog.value = true
    }

    fun applyDebugConnectionMode(mode: ConnectionMode) {
        viewModelScope.launch {
            configRepository.setConnectionMode(
                if (effectiveTransportMode() == TransportIsolationMode.BLE_ONLY && mode == ConnectionMode.MANUAL) {
                    ConnectionMode.AUTO_PAIR
                } else {
                    mode
                }
            )
        }
    }

    fun applyDebugTransportIsolationMode(mode: TransportIsolationMode) {
        viewModelScope.launch {
            configRepository.setTransportIsolationMode(mode)
            if (mode == TransportIsolationMode.BLE_ONLY) {
                configRepository.setConnectionMode(ConnectionMode.AUTO_PAIR)
            }
        }
    }

    fun applyDebugPairCode(value: String) {
        viewModelScope.launch {
            configRepository.setPairCode(value.trim())
        }
    }

    fun applyDebugTargetIp(ip: String) {
        viewModelScope.launch {
            configRepository.setTargetIp(UdpEndpointConfig.normalizeTargetAddress(ip))
        }
    }

    fun applyDebugTargetPort(portStr: String): Boolean {
        val port = UdpEndpointConfig.parsePort(portStr) ?: return false
        viewModelScope.launch {
            configRepository.setTargetPort(port)
        }
        return true
    }

    fun dismissBleDialog() {
        _showBleDialog.value = false
    }

    /**
     * Show unified data log dialog (replaces UDP data dialog)
     */
    fun showDataLogDialog() {
        _showDataLogDialog.value = true
    }

    fun dismissDataLogDialog() {
        _showDataLogDialog.value = false
    }

    /**
     * Legacy method for backward compatibility
     */
    @Deprecated("Use showDataLogDialog() instead")
    fun showUdpDataDialog() {
        showDataLogDialog()
    }

    @Deprecated("Use dismissDataLogDialog() instead")
    fun dismissUdpDataDialog() {
        dismissDataLogDialog()
    }

    /**
     * Clear all data logs (UDP and BLE)
     */
    fun clearDataLogs() {
        dataLogManager.clearLogs()
    }

    @Deprecated("Use clearDataLogs() instead")
    fun clearUdpDataLogs() {
        clearDataLogs()
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
        runCatching {
            val stableClientId = clientId.value.ifBlank { "" }
            if (effectiveTransportMode().allowsWifiDiscovery && stableClientId.isNotBlank()) {
                wifiDiscoveryManager.sendUnpair(stableClientId)
            }
        }
        wifiDiscoveryManager.shutdown()
        usbHidManager.release()
        frequencyManager.shutdown()
        heartbeatManager.shutdown()
        reconnectManager.shutdown()
        bleServiceRetryJob?.cancel()
        bleManager.shutdown()
    }
}

sealed class ExportStatus {
    data object Idle : ExportStatus()
    data object Exporting : ExportStatus()
    data class Success(val fileName: String) : ExportStatus()
    data class Error(val message: String) : ExportStatus()
}
