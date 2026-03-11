package com.example.dji_rc_pro.domain.ble

import android.annotation.SuppressLint
import android.bluetooth.*
import android.bluetooth.BluetoothGatt
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.os.Build
import android.util.Log
import com.example.dji_rc_pro.domain.config.ConfigRepository
import com.example.dji_rc_pro.domain.config.TransportIsolationMode
import com.example.dji_rc_pro.domain.discovery.DiscoveryProtocol
import com.example.dji_rc_pro.domain.model.BleOperation
import com.example.dji_rc_pro.domain.model.LogLevel
import com.example.dji_rc_pro.manager.ConnectionManager
import com.example.dji_rc_pro.manager.DataLogManager
import com.example.dji_rc_pro.manager.FrequencyManager
import com.example.dji_rc_pro.manager.HeartbeatManager
import com.example.dji_rc_pro.manager.ReconnectManager
import com.example.dji_rc_pro.manager.logBleConnect
import com.example.dji_rc_pro.manager.logBleDisconnect
import com.example.dji_rc_pro.manager.logBleError
import com.example.dji_rc_pro.manager.logBleRssi
import com.example.dji_rc_pro.manager.logBleScan
import com.example.dji_rc_pro.manager.logBleServiceDiscover
import com.example.dji_rc_pro.manager.logBleStateChange
import com.example.dji_rc_pro.util.ErrorCode
import com.example.dji_rc_pro.util.LogUtil
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue

@SuppressLint("MissingPermission")
class BleManager private constructor(context: Context) {

    companion object {
        private const val TAG = "BleManager"
        private const val WRITE_QUEUE_MAX_SIZE = 1000
        private const val MAX_WRITE_ATTEMPTS = 3

        // Connection parameters for stability
        private const val CONNECTION_PRIORITY_HIGH = 1
        private const val MTU_SIZE = 185
        private const val RSSI_UPDATE_INTERVAL = 1000L // 1 second
        private const val ROS2_SESSION_READ_MAX_RETRIES = 6

        // RSSI signal strength thresholds
        private const val RSSI_EXCELLENT = -50
        private const val RSSI_GOOD = -60
        private const val RSSI_FAIR = -70
        private const val RSSI_POOR = -80

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: BleManager? = null

        fun getInstance(context: Context): BleManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: BleManager(context.applicationContext).also { INSTANCE = it }
            }
        }

        val UART_SERVICE_UUID: UUID = UUID.fromString("6E400001-B5A3-F393-E0A9-E50E24DCCA9E")
        val UART_RX_CHAR_UUID: UUID = UUID.fromString("6E400002-B5A3-F393-E0A9-E50E24DCCA9E")
        val UART_TX_CHAR_UUID: UUID = UUID.fromString("6E400003-B5A3-F393-E0A9-E50E24DCCA9E")

        /**
         * Analyze disconnect reason from status code
         */
        fun analyzeDisconnectReason(status: Int): String {
            return when (status) {
                BluetoothGatt.GATT_SUCCESS -> "Success"
                8 -> "Connection Timeout"
                19 -> "Remote User Terminated"
                22 -> "Local Host Terminated"
                34 -> "Connection Fail"
                62 -> "Failed to Connect"
                133 -> "GATT Error / Connection Fail"
                257 -> "Connection Cancelled"
                else -> "Unknown Reason (Status: $status)"
            }
        }

        /**
         * Get signal strength description
         */
        fun getRssiDescription(rssi: Int): String {
            return when {
                rssi >= RSSI_EXCELLENT -> "Excellent"
                rssi >= RSSI_GOOD -> "Good"
                rssi >= RSSI_FAIR -> "Fair"
                rssi >= RSSI_POOR -> "Poor"
                else -> "Very Poor"
            }
        }
    }

    private val context: Context = context.applicationContext
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val bluetoothAdapter: BluetoothAdapter? by lazy {
        val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    // Bond state change receiver
    private val bondStateReceiver = object : android.content.BroadcastReceiver() {
        override fun onReceive(context: android.content.Context?, intent: android.content.Intent?) {
            val action = intent?.action
            if (action == BluetoothDevice.ACTION_BOND_STATE_CHANGED) {
                val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE)
                val prevState = intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, BluetoothDevice.BOND_NONE)
                val newState = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.BOND_NONE)

                dataLogManager?.addBleDebugLog(
                    operation = BleOperation.CONNECT,
                    message = "Bond state changed: ${bondStateToString(prevState)} -> ${bondStateToString(newState)}",
                    level = LogLevel.INFO,
                    macAddress = device?.address
                )

                // If bonding completed successfully, try to connect
                if (newState == BluetoothDevice.BOND_BONDED && device != null) {
                    dataLogManager?.addBleDebugLog(
                        operation = BleOperation.CONNECT,
                        message = "Bonding complete, initiating connection...",
                        level = LogLevel.INFO,
                        macAddress = device.address
                    )
                    scope.launch {
                        delay(500) // Small delay after bonding
                        performConnect(device)
                    }
                }
            }
        }
    }

    private enum class ActiveProfile {
        NONE,
        UART,
        ROS2
    }

    private var bluetoothGatt: BluetoothGatt? = null
    private var writeCharacteristic: BluetoothGattCharacteristic? = null
    private var notifyCharacteristic: BluetoothGattCharacteristic? = null
    private var pairControlCharacteristic: BluetoothGattCharacteristic? = null
    private var networkInfoCharacteristic: BluetoothGattCharacteristic? = null
    private var statusCharacteristic: BluetoothGattCharacteristic? = null
    private var activeProfile: ActiveProfile = ActiveProfile.NONE
    private var probeState: Ros2BleProfile.PairProbeState? = null
    private var pendingPairContext: Ros2BleProfile.PendingPairContext? = null
    private var pendingDiscoveredHost: DiscoveryProtocol.DiscoveredHost? = null
    private var pendingProbeAfterNotify = false
    private var ros2PairControlNotifyReady = false
    private var ros2PairControlPollingMode = false
    private var currentMtu = 23
    @Volatile
    private var currentTransportMode: TransportIsolationMode = TransportIsolationMode.BLE_UDP

    private var connectionManager: ConnectionManager? = null
    private var heartbeatManager: HeartbeatManager? = null
    private var reconnectManager: ReconnectManager? = null
    private var frequencyManager: FrequencyManager? = null
    private var dataLogManager: DataLogManager? = null

    private val writeQueue = NonBlockingQueue<ByteArray>(WRITE_QUEUE_MAX_SIZE)
    private var writeJob: Job? = null
    private var isWriting = false
    private var rssiJob: Job? = null
    private var statusPollJob: Job? = null
    private var pairNotifyFallbackJob: Job? = null
    private var pairProbeDispatchJob: Job? = null
    private var pairReadAfterWriteJob: Job? = null
    private var bleOnlyReadyFallbackJob: Job? = null
    private var transportModeObserverJob: Job? = null
    private var ros2SessionReadRetryCount = 0

    private val _connectionState = MutableStateFlow(BluetoothProfile.STATE_DISCONNECTED)
    val connectionState: StateFlow<Int> = _connectionState.asStateFlow()

    private val _isScanning = MutableStateFlow(false)
    val isScanning: StateFlow<Boolean> = _isScanning.asStateFlow()

    private val _lastWriteTime = MutableStateFlow(0L)
    val lastWriteTime: StateFlow<Long> = _lastWriteTime.asStateFlow()

    private val _currentRssi = MutableStateFlow<Int?>(null)
    val currentRssi: StateFlow<Int?> = _currentRssi.asStateFlow()

    private val _connectedDeviceAddress = MutableStateFlow<String?>(null)
    val connectedDeviceAddress: StateFlow<String?> = _connectedDeviceAddress.asStateFlow()

    private val _ros2GatewaySession = MutableStateFlow(Ros2BleProfile.GatewaySession())
    val ros2GatewaySession: StateFlow<Ros2BleProfile.GatewaySession> = _ros2GatewaySession.asStateFlow()

    data class BleDevice(
        val device: BluetoothDevice,
        val rssi: Int = 0,
        val isPaired: Boolean = false,
        val isRos2Gateway: Boolean = false
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is BleDevice) return false
            return device.address == other.device.address
        }
        override fun hashCode(): Int {
            return device.address.hashCode()
        }
    }

    private val _scanResults = MutableStateFlow<List<BleDevice>>(emptyList())
    val scanResults: StateFlow<List<BleDevice>> = _scanResults.asStateFlow()

    private val scanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            result?.let { newResult ->
                val device = newResult.device
                val address = device.address
                val rssi = newResult.rssi
                val name = try { device.name } catch (e: SecurityException) { null } ?: "Unknown"

                val advertisedServices = newResult.scanRecord?.serviceUuids.orEmpty()
                val isRos2Gateway = advertisedServices.any { it.uuid == Ros2BleProfile.SERVICE_UUID } ||
                    Ros2BleProfile.matchesGatewayName(name)

                val logMessage = when {
                    isRos2Gateway -> "ROS2 gateway found - Name: $name, RSSI: ${rssi}dBm (${getRssiDescription(rssi)})"
                    else -> "Device found - Name: $name, RSSI: ${rssi}dBm"
                }
                dataLogManager?.logBleScan(
                    message = logMessage,
                    macAddress = address,
                    level = if (isRos2Gateway) LogLevel.INFO else LogLevel.DEBUG
                )

                val currentList = _scanResults.value.toMutableList()
                val existingIndex = currentList.indexOfFirst { it.device.address == address }

                if (existingIndex >= 0) {
                    val existing = currentList[existingIndex]
                    currentList[existingIndex] = existing.copy(
                        device = device,
                        rssi = rssi,
                        isRos2Gateway = isRos2Gateway
                    )
                } else {
                    val isBonded = try {
                        device.bondState == BluetoothDevice.BOND_BONDED
                    } catch (e: SecurityException) {
                        false
                    }
                    currentList.add(BleDevice(device, rssi, isBonded, isRos2Gateway))
                }

                currentList.sortWith(
                    compareByDescending<BleDevice> { it.isRos2Gateway }
                        .thenByDescending { it.isPaired }
                        .thenByDescending { it.rssi }
                )
                _scanResults.value = currentList
            }
        }

        override fun onScanFailed(errorCode: Int) {
            val errorMessage = when (errorCode) {
                SCAN_FAILED_ALREADY_STARTED -> "Scan already started"
                SCAN_FAILED_APPLICATION_REGISTRATION_FAILED -> "App registration failed"
                SCAN_FAILED_INTERNAL_ERROR -> "Internal error"
                SCAN_FAILED_FEATURE_UNSUPPORTED -> "Feature unsupported"
                SCAN_FAILED_OUT_OF_HARDWARE_RESOURCES -> "Out of hardware resources"
                SCAN_FAILED_SCANNING_TOO_FREQUENTLY -> "Scanning too frequently"
                else -> "Unknown error ($errorCode)"
            }

            LogUtil.e("BleManager scan failed: $errorCode - $errorMessage")
            dataLogManager?.addBleDebugLog(
                operation = BleOperation.SCAN,
                message = "Scan failed: $errorMessage",
                level = LogLevel.ERROR,
                errorCode = errorCode
            )
            _isScanning.value = false
            connectionManager?.onBleError(ErrorCode.BLE_SCAN_FAILED)
        }
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            val deviceAddress = gatt?.device?.address
            val stateStr = when (newState) {
                BluetoothProfile.STATE_CONNECTED -> "CONNECTED"
                BluetoothProfile.STATE_DISCONNECTED -> "DISCONNECTED"
                BluetoothProfile.STATE_CONNECTING -> "CONNECTING"
                BluetoothProfile.STATE_DISCONNECTING -> "DISCONNECTING"
                else -> "UNKNOWN($newState)"
            }

            LogUtil.d("BleManager connection state change: status=$status, newState=$newState ($stateStr)")
            dataLogManager?.logBleStateChange(
                message = "Connection state changed: $stateStr (Status: $status)",
                macAddress = deviceAddress,
                status = status
            )

            _connectionState.value = newState

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    currentMtu = 23
                    _connectedDeviceAddress.value = deviceAddress
                    dataLogManager?.logBleConnect(
                        message = "GATT connected successfully to $deviceAddress",
                        macAddress = deviceAddress,
                        level = LogLevel.INFO
                    )

                    connectionManager?.onBleConnected()
                    heartbeatManager?.startBleHeartbeat()
                    startWriteProcessor()
                    startRssiMonitoring()

                    // Request high priority connection for better performance
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        gatt?.requestConnectionPriority(CONNECTION_PRIORITY_HIGH)
                        dataLogManager?.addBleDebugLog(
                            operation = BleOperation.CONNECT,
                            message = "Requested high priority connection",
                            level = LogLevel.DEBUG,
                            macAddress = deviceAddress
                        )
                    }

                    scope.launch {
                        delay(150)
                        val refreshed = if (usesRos2BleTransport()) {
                            refreshGattCache(gatt)
                        } else {
                            false
                        }
                        if (refreshed) {
                            LogUtil.i("ROS2 BLE refreshed GATT cache before service discovery", TAG)
                            delay(350)
                        }
                        val discoveryStarted = gatt?.discoverServices() ?: false
                        LogUtil.d(
                            "ROS2 BLE discoverServices started=$discoveryStarted refreshed=$refreshed",
                            TAG
                        )
                    }
                } else {
                    dataLogManager?.logBleError(
                        message = "Connected but status is not success: $status",
                        macAddress = deviceAddress,
                        errorCode = status
                    )
                }
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                val disconnectReason = analyzeDisconnectReason(status)
                dataLogManager?.logBleDisconnect(
                    message = "Disconnected - Reason: $disconnectReason",
                    macAddress = deviceAddress,
                    level = if (status == BluetoothGatt.GATT_SUCCESS) LogLevel.INFO else LogLevel.WARN
                )

                heartbeatManager?.stopBleHeartbeat()
                stopWriteProcessor()
                stopRssiMonitoring()
                stopStatusPolling()
                _currentRssi.value = null
                _connectedDeviceAddress.value = null
                _ros2GatewaySession.value = Ros2BleProfile.GatewaySession()

                val errorCode = when (status) {
                    14 -> ErrorCode.BLE_CONNECTION_REFUSED
                    133 -> ErrorCode.BLE_CONNECTION_FAILED
                    257 -> ErrorCode.BLE_CONNECTION_FAILED
                    else -> ErrorCode.BLE_CONNECTION_FAILED
                }

                // Retry connection if failed and retry count not exceeded
                if (status != BluetoothGatt.GATT_SUCCESS && connectRetryCount < MAX_CONNECT_RETRY) {
                    dataLogManager?.addBleDebugLog(
                        operation = BleOperation.CONNECT,
                        message = "Connection failed with status $status, will retry ($connectRetryCount/$MAX_CONNECT_RETRY)",
                        level = LogLevel.WARN,
                        macAddress = deviceAddress,
                        status = status
                    )
                    gatt?.device?.let { device ->
                        scope.launch {
                            delay(500) // Wait before retry
                            performConnect(device)
                        }
                    }
                } else {
                    connectionManager?.onBleDisconnected(errorCode)

                    reconnectManager?.let { rc ->
                        if (!rc.isReconnecting()) {
                            gatt?.device?.let { device ->
                                rc.startReconnect("BLE", errorCode)
                            }
                        }
                    }
                }

                close()
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            val deviceAddress = gatt?.device?.address

            if (status != BluetoothGatt.GATT_SUCCESS) {
                dataLogManager?.logBleError(
                    message = "Service discovery failed with status: $status",
                    macAddress = deviceAddress,
                    errorCode = status
                )
                return
            }

            dataLogManager?.logBleServiceDiscover(
                message = "Services discovered successfully",
                macAddress = deviceAddress,
                level = LogLevel.INFO
            )

            val ros2Service = gatt?.getService(Ros2BleProfile.SERVICE_UUID)
            if (ros2Service != null) {
                val bleOnlyMode = isBleOnlyTransportMode()
                activeProfile = ActiveProfile.ROS2
                pairControlCharacteristic = ros2Service.getCharacteristic(Ros2BleProfile.PAIR_CONTROL_UUID)
                networkInfoCharacteristic = ros2Service.getCharacteristic(Ros2BleProfile.NETWORK_INFO_UUID)
                statusCharacteristic = ros2Service.getCharacteristic(Ros2BleProfile.STATUS_UUID)
                writeCharacteristic = ros2Service.getCharacteristic(Ros2BleProfile.CONTROL_STREAM_UUID)
                notifyCharacteristic = pairControlCharacteristic
                LogUtil.d(
                    "ROS2 BLE service discovered: pair=${pairControlCharacteristic != null} network=${networkInfoCharacteristic != null} status=${statusCharacteristic != null} control=${writeCharacteristic != null}",
                    TAG
                )
                LogUtil.d(
                    "ROS2 BLE characteristic properties: pair=${describeGattProperties(pairControlCharacteristic)} network=${describeGattProperties(networkInfoCharacteristic)} status=${describeGattProperties(statusCharacteristic)} control=${describeGattProperties(writeCharacteristic)}",
                    TAG
                )
                if (bleOnlyMode && (networkInfoCharacteristic != null || statusCharacteristic != null)) {
                    LogUtil.w(
                        "ROS2 BLE-only discovered legacy network/status characteristics; GATT cache is likely stale",
                        TAG
                    )
                }

                dataLogManager?.logBleServiceDiscover(
                    message = "ROS2 gateway service found: ${Ros2BleProfile.SERVICE_UUID}",
                    macAddress = deviceAddress,
                    level = LogLevel.INFO
                )

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val mtuRequested = runCatching { gatt.requestMtu(MTU_SIZE) }.getOrDefault(false)
                    LogUtil.d("ROS2 BLE requestMtu started=$mtuRequested", TAG)
                }

                val missingRequiredCharacteristic = pairControlCharacteristic == null ||
                    writeCharacteristic == null ||
                    (!bleOnlyMode && (networkInfoCharacteristic == null || statusCharacteristic == null))
                if (missingRequiredCharacteristic) {
                    dataLogManager?.logBleError(
                        message = "ROS2 gateway characteristics incomplete",
                        macAddress = deviceAddress
                    )
                    connectionManager?.onBleError(ErrorCode.BLE_CHARACTERISTIC_NOT_FOUND)
                    return
                }

                pendingProbeAfterNotify = true
                ros2PairControlNotifyReady = false
                ros2PairControlPollingMode = !bleOnlyMode
                if (bleOnlyMode) {
                    maybeDispatchRos2PairProbe("services_discovered_ble_only")
                } else {
                    LogUtil.i("ROS2 BLE using pair_control polling instead of CCCD notifications", TAG)
                    maybeDispatchRos2PairProbe("services_discovered_ble_udp")
                }
                return
            }

            activeProfile = ActiveProfile.UART
            val service = gatt?.getService(UART_SERVICE_UUID)
            if (service != null) {
                dataLogManager?.logBleServiceDiscover(
                    message = "UART service found: $UART_SERVICE_UUID",
                    macAddress = deviceAddress,
                    level = LogLevel.INFO
                )

                writeCharacteristic = service.getCharacteristic(UART_RX_CHAR_UUID)
                notifyCharacteristic = service.getCharacteristic(UART_TX_CHAR_UUID)
                pairControlCharacteristic = null
                networkInfoCharacteristic = null
                statusCharacteristic = null

                if (writeCharacteristic != null) {
                    dataLogManager?.logBleServiceDiscover(
                        message = "Write characteristic found: $UART_RX_CHAR_UUID",
                        macAddress = deviceAddress,
                        level = LogLevel.INFO
                    )
                } else {
                    dataLogManager?.logBleError(
                        message = "Write characteristic not found: $UART_RX_CHAR_UUID",
                        macAddress = deviceAddress
                    )
                    connectionManager?.onBleError(ErrorCode.BLE_CHARACTERISTIC_NOT_FOUND)
                }

                if (notifyCharacteristic != null) {
                    dataLogManager?.logBleServiceDiscover(
                        message = "Notify characteristic found: $UART_TX_CHAR_UUID",
                        macAddress = deviceAddress,
                        level = LogLevel.INFO
                    )
                    enableNotifications(gatt, notifyCharacteristic!!)
                }
            } else {
                dataLogManager?.logBleError(
                    message = "No supported BLE service found",
                    macAddress = deviceAddress
                )
                connectionManager?.onBleError(ErrorCode.BLE_SERVICE_NOT_FOUND)
            }
        }

        override fun onCharacteristicWrite(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?, status: Int) {
            val deviceAddress = gatt?.device?.address
            val isControlStream = characteristic?.uuid == writeCharacteristic?.uuid
            LogUtil.d(
                "ROS2 BLE onCharacteristicWrite uuid=${characteristic?.uuid} status=$status controlStream=$isControlStream notifyReady=$ros2PairControlNotifyReady",
                TAG
            )

            if (status == BluetoothGatt.GATT_SUCCESS) {
                if (isControlStream) {
                    isWriting = false
                    processWriteQueue()
                }
                if (activeProfile == ActiveProfile.ROS2 &&
                    characteristic?.uuid == Ros2BleProfile.PAIR_CONTROL_UUID) {
                    if (isBleOnlyTransportMode()) {
                        bleOnlyReadyFallbackJob?.cancel()
                        markBleOnlyControlReady(deviceAddress)
                    } else if (!ros2PairControlNotifyReady) {
                        scheduleRos2SessionReadRetry("pair_control_write", resetAttemptCounter = true)
                    }
                }
                dataLogManager?.addBleDebugLog(
                    operation = BleOperation.CHARACTERISTIC_WRITE,
                    message = "Characteristic write successful: ${characteristic?.uuid}",
                    level = LogLevel.DEBUG,
                    macAddress = deviceAddress
                )
            } else {
                dataLogManager?.logBleError(
                    message = "Characteristic write failed with status: $status",
                    macAddress = deviceAddress,
                    errorCode = status
                )
                if (isControlStream) {
                    isWriting = false
                }
            }
        }

        override fun onDescriptorWrite(gatt: BluetoothGatt?, descriptor: BluetoothGattDescriptor?, status: Int) {
            super.onDescriptorWrite(gatt, descriptor, status)
            LogUtil.d(
                "ROS2 BLE onDescriptorWrite uuid=${descriptor?.characteristic?.uuid} status=$status pendingProbe=$pendingProbeAfterNotify",
                TAG
            )
            if (status != BluetoothGatt.GATT_SUCCESS) {
                return
            }
            val characteristicUuid = descriptor?.characteristic?.uuid
            if (activeProfile == ActiveProfile.ROS2 &&
                characteristicUuid == Ros2BleProfile.PAIR_CONTROL_UUID &&
                pendingProbeAfterNotify) {
                pairNotifyFallbackJob?.cancel()
                ros2PairControlNotifyReady = true
                pendingProbeAfterNotify = false
                dispatchRos2PairingProbe()
            }
        }

        override fun onCharacteristicRead(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?, status: Int) {
            LogUtil.d(
                "ROS2 BLE onCharacteristicRead uuid=${characteristic?.uuid} status=$status bytes=${characteristic?.value?.size ?: 0}",
                TAG
            )
            if (status != BluetoothGatt.GATT_SUCCESS) {
                when (characteristic?.uuid) {
                    Ros2BleProfile.PAIR_CONTROL_UUID -> scheduleRos2SessionReadRetry("pair_control_status_$status")
                    Ros2BleProfile.NETWORK_INFO_UUID -> scheduleRos2SessionReadRetry("network_info_status_$status")
                    Ros2BleProfile.STATUS_UUID -> scheduleRos2SessionReadRetry("status_status_$status")
                }
                return
            }
            val deviceAddress = gatt?.device?.address
            val data = characteristic?.value ?: return
            dataLogManager?.addBleDebugLog(
                operation = BleOperation.CHARACTERISTIC_READ,
                message = "Characteristic read ${characteristic.uuid} (${data.size} bytes)",
                level = LogLevel.DEBUG,
                macAddress = deviceAddress,
                data = data
            )
            heartbeatManager?.onHeartbeatReceived("BLE")
            connectionManager?.onBleActivity(bytesReceived = data.size.toLong())

            when (characteristic.uuid) {
                Ros2BleProfile.PAIR_CONTROL_UUID -> handleRos2PairControlMessage(data, deviceAddress)
                Ros2BleProfile.NETWORK_INFO_UUID -> {
                    ros2SessionReadRetryCount = 0
                    applyRos2NetworkInfo(data)
                    readRos2StatusCharacteristic()
                }
                Ros2BleProfile.STATUS_UUID -> {
                    ros2SessionReadRetryCount = 0
                    applyRos2Status(data)
                    if (_ros2GatewaySession.value.paired && statusPollJob?.isActive != true) {
                        startStatusPolling()
                    }
                }
            }
        }

        override fun onCharacteristicChanged(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?) {
            val deviceAddress = gatt?.device?.address
            characteristic?.value?.let { data ->
                LogUtil.d(
                    "ROS2 BLE onCharacteristicChanged uuid=${characteristic.uuid} bytes=${data.size}",
                    TAG
                )
                dataLogManager?.addBleReceivedLog(data, deviceAddress)
                dataLogManager?.addBleDebugLog(
                    operation = BleOperation.CHARACTERISTIC_CHANGED,
                    message = "Received ${data.size} bytes from ${characteristic.uuid}",
                    level = LogLevel.DEBUG,
                    macAddress = deviceAddress,
                    data = data
                )
                heartbeatManager?.onHeartbeatReceived("BLE")
                connectionManager?.onBleActivity(bytesReceived = data.size.toLong())

                when (characteristic.uuid) {
                    Ros2BleProfile.PAIR_CONTROL_UUID -> handleRos2PairControlMessage(data, deviceAddress)
                    else -> Unit
                }
            }
        }

        override fun onReadRemoteRssi(gatt: BluetoothGatt?, rssi: Int, status: Int) {
            val deviceAddress = gatt?.device?.address
            if (status == BluetoothGatt.GATT_SUCCESS) {
                _currentRssi.value = rssi
                dataLogManager?.logBleRssi(rssi, deviceAddress)
            }
        }

        override fun onMtuChanged(gatt: BluetoothGatt?, mtu: Int, status: Int) {
            val deviceAddress = gatt?.device?.address
            if (status == BluetoothGatt.GATT_SUCCESS) {
                currentMtu = mtu
                maybeDispatchRos2PairProbe("mtu_changed")
                dataLogManager?.addBleDebugLog(
                    operation = BleOperation.CONNECT,
                    message = "MTU changed to $mtu bytes",
                    level = LogLevel.INFO,
                    macAddress = deviceAddress
                )
            }
        }
    }

    private fun dispatchRos2PairingProbe() {
        scope.launch {
            runCatching {
                val repo = ConfigRepository.get()
                val pairCode = repo.pairCode.first()
                val nextProbeState = Ros2BleProfile.createProbeState(
                    clientSeed = repo.getOrCreateClientId(),
                    pairCode = pairCode
                )
                probeState = nextProbeState
                pendingPairContext = null
                pendingDiscoveredHost = null
                pendingProbeAfterNotify = false
                pairProbeDispatchJob?.cancel()
                pairProbeDispatchJob = null
                LogUtil.d(
                    "ROS2 BLE dispatch pair probe: clientId=${nextProbeState.clientId} nonce=${nextProbeState.clientNonce} mtu=$currentMtu",
                    TAG
                )
                val writeStarted = writeCharacteristicData(
                    characteristic = pairControlCharacteristic,
                    data = Ros2BleProfile.buildPairProbe(nextProbeState),
                    writeType = ros2ControlWriteType(pairControlCharacteristic)
                )
                LogUtil.d("ROS2 BLE pair probe writeStarted=$writeStarted", TAG)
                if (writeStarted && isBleOnlyTransportMode()) {
                    scheduleBleOnlyReadyFallback(_connectedDeviceAddress.value)
                } else if (writeStarted && !ros2PairControlNotifyReady) {
                    scheduleRos2SessionReadAfterWrite("probe")
                }
            }.onFailure { error ->
                dataLogManager?.logBleError(
                    message = "Failed to send ROS2 pair probe: ${error.message}",
                    macAddress = _connectedDeviceAddress.value
                )
                LogUtil.e(error, "BleManager failed to send ROS2 pair probe")
            }
        }
    }

    private fun handleRos2PairControlMessage(data: ByteArray, deviceAddress: String?) {
        val payload = data.toString(Charsets.UTF_8)
        val sourceAddress = deviceAddress.orEmpty()
        LogUtil.d(
            "ROS2 BLE pair_control message bytes=${data.size} payload=${payload.replace('\n', '|')}",
            TAG
        )

        probeState?.let { currentProbe ->
            Ros2BleProfile.parsePairOffer(data, currentProbe)?.let { host ->
                pendingDiscoveredHost = host
                pendingPairContext = Ros2BleProfile.createPendingPairContext(host, currentProbe)
                _ros2GatewaySession.value = _ros2GatewaySession.value.copy(
                    hostId = host.hostId,
                    hostName = host.hostName,
                    ipv4Address = host.ipv4Address,
                    ipv6Address = host.ipv6Address,
                    preferredFamily = host.selectedFamily,
                    selectedFamily = host.selectedFamily,
                    selectedAddress = host.address,
                    controlPort = host.controlPort,
                    leaseMs = host.leaseMs,
                    bleReady = true
                )
                val writeStarted = writeCharacteristicData(
                    characteristic = pairControlCharacteristic,
                    data = Ros2BleProfile.buildPairRequest(host, currentProbe),
                    writeType = ros2ControlWriteType(pairControlCharacteristic)
                )
                LogUtil.d(
                    "ROS2 BLE sending pair request to host=${host.hostId} address=${host.address} writeStarted=$writeStarted",
                    TAG
                )
                if (writeStarted && !ros2PairControlNotifyReady) {
                    scheduleRos2SessionReadAfterWrite("pair_request")
                }
                return
            }
        }

        pendingPairContext?.let { context ->
            Ros2BleProfile.parsePairAck(data, context)?.let { ack ->
                LogUtil.d(
                    "ROS2 BLE pair ack: session=${ack.sessionId} family=${ack.selectedFamily} address=${ack.address}",
                    TAG
                )
                val discoveredHost = pendingDiscoveredHost
                _ros2GatewaySession.value = _ros2GatewaySession.value.copy(
                    hostId = ack.hostId,
                    hostName = discoveredHost?.hostName ?: _ros2GatewaySession.value.hostName,
                    sessionId = ack.sessionId,
                    ipv4Address = discoveredHost?.ipv4Address ?: _ros2GatewaySession.value.ipv4Address,
                    ipv6Address = discoveredHost?.ipv6Address ?: _ros2GatewaySession.value.ipv6Address,
                    preferredFamily = discoveredHost?.selectedFamily ?: _ros2GatewaySession.value.preferredFamily,
                    selectedFamily = ack.selectedFamily,
                    selectedAddress = ack.address,
                    controlPort = ack.controlPort,
                    leaseMs = ack.leaseMs,
                    bleReady = true,
                    paired = true
                )
                readRos2NetworkInfoCharacteristic()
                readRos2StatusCharacteristic()
                startStatusPolling()
                return
            }

            Ros2BleProfile.parsePairBusy(data, context)?.let { busy ->
                _ros2GatewaySession.value = _ros2GatewaySession.value.copy(
                    bleReady = false,
                    paired = false,
                    selectedAddress = busy.address.takeIf { it.isNotBlank() } ?: _ros2GatewaySession.value.selectedAddress
                )
                LogUtil.w(
                    "ROS2 BLE gateway busy: reason=${busy.reason} address=${busy.address}",
                    TAG
                )
                dataLogManager?.addBleDebugLog(
                    operation = BleOperation.CONNECT,
                    message = "ROS2 gateway busy: ${busy.reason}",
                    level = LogLevel.WARN,
                    macAddress = deviceAddress
                )
            }
        }
    }

    private fun applyRos2NetworkInfo(data: ByteArray) {
        Ros2BleProfile.parseNetworkInfo(data, _ros2GatewaySession.value)?.let { parsed ->
            _ros2GatewaySession.value = parsed.copy(
                bleReady = true,
                paired = parsed.paired || _ros2GatewaySession.value.paired
            )
        }
    }

    private fun applyRos2Status(data: ByteArray) {
        Ros2BleProfile.parseStatus(data, _ros2GatewaySession.value)?.let { parsed ->
            _ros2GatewaySession.value = parsed
        }
    }

    private fun readRos2NetworkInfoCharacteristic() {
        val gatt = bluetoothGatt ?: return
        val characteristic = networkInfoCharacteristic ?: return
        try {
            val started = gatt.readCharacteristic(characteristic)
            LogUtil.d("ROS2 BLE read network_info started=$started", TAG)
        } catch (error: Exception) {
            LogUtil.e(error, "BleManager failed to read ROS2 network info")
        }
    }

    private fun readRos2StatusCharacteristic() {
        val gatt = bluetoothGatt ?: return
        val characteristic = statusCharacteristic ?: return
        try {
            val started = gatt.readCharacteristic(characteristic)
            LogUtil.d("ROS2 BLE read status started=$started", TAG)
        } catch (error: Exception) {
            LogUtil.e(error, "BleManager failed to read ROS2 status")
        }
    }

    private fun startStatusPolling() {
        if (activeProfile != ActiveProfile.ROS2) {
            return
        }
        if (statusPollJob?.isActive == true) {
            return
        }
        statusPollJob?.cancel()
        statusPollJob = scope.launch {
            while (isActive && isConnected() && activeProfile == ActiveProfile.ROS2) {
                readRos2StatusCharacteristic()
                delay(1000)
            }
        }
    }

    private fun stopStatusPolling() {
        statusPollJob?.cancel()
        statusPollJob = null
    }

    private fun writeCharacteristicData(
        characteristic: BluetoothGattCharacteristic?,
        data: ByteArray,
        writeType: Int
    ): Boolean {
        val gatt = bluetoothGatt ?: return false
        val targetCharacteristic = characteristic ?: return false
        return try {
            targetCharacteristic.value = data
            targetCharacteristic.writeType = writeType
            val started = gatt.writeCharacteristic(targetCharacteristic)
            LogUtil.d(
                "ROS2 BLE write uuid=${targetCharacteristic.uuid} bytes=${data.size} type=$writeType started=$started",
                TAG
            )
            started
        } catch (error: SecurityException) {
            dataLogManager?.logBleError(
                message = "BLE write failed: ${error.message}",
                macAddress = _connectedDeviceAddress.value
            )
            LogUtil.e(error, "BleManager writeCharacteristicData failed")
            false
        }
    }

    private fun isBleOnlyTransportMode(): Boolean = currentTransportMode == TransportIsolationMode.BLE_ONLY

    private fun usesRos2BleTransport(): Boolean = currentTransportMode != TransportIsolationMode.UDP_ONLY

    private fun markBleOnlyControlReady(deviceAddress: String?) {
        if (!isBleOnlyTransportMode()) {
            return
        }
        val currentSession = _ros2GatewaySession.value
        if (currentSession.bleOnlyControlReady) {
            return
        }
        val hostId = currentSession.hostId.ifBlank {
            deviceAddress?.replace(":", "") ?: "ble_only_gateway"
        }
        _ros2GatewaySession.value = currentSession.copy(
            hostId = hostId,
            hostName = currentSession.hostName.ifBlank { "ROS2 BLE Gateway" },
            bleReady = true,
            udpReady = false,
            bleOnlyControlReady = true
        )
        dataLogManager?.addBleDebugLog(
            operation = BleOperation.CONNECT,
            message = "BLE-only auth accepted locally, control stream enabled",
            level = LogLevel.INFO,
            macAddress = deviceAddress
        )
        LogUtil.i("ROS2 BLE-only auth marked ready locally", TAG)
    }

    private fun scheduleBleOnlyReadyFallback(deviceAddress: String?) {
        if (!isBleOnlyTransportMode()) {
            return
        }
        bleOnlyReadyFallbackJob?.cancel()
        bleOnlyReadyFallbackJob = scope.launch {
            delay(150)
            if (activeProfile == ActiveProfile.ROS2 &&
                isConnected() &&
                !_ros2GatewaySession.value.bleOnlyControlReady) {
                LogUtil.w("ROS2 BLE-only auth callback missing, using local write-start fallback", TAG)
                markBleOnlyControlReady(deviceAddress)
            }
        }
    }

    private fun ros2ControlWriteType(characteristic: BluetoothGattCharacteristic?): Int {
        val properties = characteristic?.properties ?: 0
        return when {
            properties and BluetoothGattCharacteristic.PROPERTY_WRITE != 0 ->
                BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
            properties and BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE != 0 ->
                BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE
            else -> BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
        }
    }

    fun shouldTransmitControlFrames(): Boolean {
        return when (activeProfile) {
            ActiveProfile.ROS2 -> isConnected() && ros2GatewaySession.value.shouldTransmitControlOverBle
            ActiveProfile.UART -> isConnected()
            ActiveProfile.NONE -> false
        }
    }

    fun controlFrameBlockReason(): String {
        return when (activeProfile) {
            ActiveProfile.ROS2 -> {
                if (!isConnected()) {
                    "BLE not connected"
                } else {
                    val session = ros2GatewaySession.value
                    when {
                        session.bleOnlyControlReady -> "ROS2 BLE-only control ready"
                        !session.paired -> "ROS2 BLE session not paired yet"
                        session.udpReady -> "UDP remains primary"
                        !session.bleReady -> "ROS2 BLE session not ready"
                        else -> "ROS2 BLE control temporarily unavailable"
                    }
                }
            }
            ActiveProfile.UART -> if (isConnected()) "BLE UART ready" else "BLE not connected"
            ActiveProfile.NONE -> "No active BLE profile"
        }
    }

    fun initialize(
        connManager: ConnectionManager,
        hbManager: HeartbeatManager,
        rcManager: ReconnectManager,
        freqManager: FrequencyManager,
        dataLogMgr: DataLogManager = DataLogManager.getInstance()
    ) {
        this.connectionManager = connManager
        this.heartbeatManager = hbManager
        this.reconnectManager = rcManager
        this.frequencyManager = freqManager
        this.dataLogManager = dataLogMgr

        transportModeObserverJob?.cancel()
        transportModeObserverJob = scope.launch {
            ConfigRepository.get().transportIsolationMode.collect { mode ->
                currentTransportMode = mode
                LogUtil.d("BleManager transport mode updated: $mode", TAG)
            }
        }

        // Register bond state receiver
        try {
            val filter = android.content.IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
            context.registerReceiver(bondStateReceiver, filter)
            LogUtil.d("BleManager: Bond state receiver registered")
        } catch (e: Exception) {
            LogUtil.e(e, "BleManager: Failed to register bond state receiver")
        }

        LogUtil.d("BleManager initialized with managers")
        dataLogManager?.addBleDebugLog(
            operation = BleOperation.CONNECT,
            message = "BleManager initialized",
            level = LogLevel.INFO
        )
    }

    fun startScan() {
        if (bluetoothAdapter?.isEnabled == true && !_isScanning.value) {
            dataLogManager?.logBleScan(
                message = "Starting BLE scan...",
                level = LogLevel.INFO
            )

            val bondedDevices = try {
                bluetoothAdapter?.bondedDevices?.map {
                    val name = try { it.name } catch (e: SecurityException) { null }
                    BleDevice(it, 0, true, Ros2BleProfile.matchesGatewayName(name))
                } ?: emptyList()
            } catch (e: SecurityException) {
                dataLogManager?.logBleError(
                    message = "Security exception getting bonded devices: ${e.message}"
                )
                emptyList()
            }

            _scanResults.value = bondedDevices

            if (bondedDevices.isNotEmpty()) {
                dataLogManager?.logBleScan(
                    message = "Found ${bondedDevices.size} bonded devices",
                    level = LogLevel.INFO
                )
                bondedDevices.forEach {
                    dataLogManager?.logBleScan(
                        message = "Bonded device: ${it.device.name ?: "Unknown"}",
                        macAddress = it.device.address,
                        level = LogLevel.DEBUG
                    )
                }
            }

            bluetoothAdapter?.bluetoothLeScanner?.startScan(
                null,
                ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build(),
                scanCallback
            )
            _isScanning.value = true
            LogUtil.d("BleManager started scan")
        } else {
            dataLogManager?.logBleScan(
                message = "Cannot start scan - Bluetooth not enabled or already scanning",
                level = LogLevel.WARN
            )
        }
    }

    fun stopScan() {
        if (_isScanning.value) {
            bluetoothAdapter?.bluetoothLeScanner?.stopScan(scanCallback)
            _isScanning.value = false
            dataLogManager?.logBleScan(
                message = "BLE scan stopped",
                level = LogLevel.INFO
            )
            LogUtil.d("BleManager stopped scan")
        }
    }

    private var connectRetryCount = 0
    private val MAX_CONNECT_RETRY = 3

    fun connect(device: BluetoothDevice) {
        val address = device.address
        val name = try { device.name } catch (e: SecurityException) { null } ?: "Unknown"
        val bondState = try { device.bondState } catch (e: SecurityException) { BluetoothDevice.BOND_NONE }
        val isRos2Gateway = _scanResults.value.firstOrNull { it.device.address == address }?.isRos2Gateway == true ||
            Ros2BleProfile.matchesGatewayName(name)

        stopScan()
        connectRetryCount = 0

        dataLogManager?.logBleConnect(
            message = "Initiating connection to $name ($address), BondState: ${bondStateToString(bondState)}",
            macAddress = address,
            level = LogLevel.INFO
        )

        // ROS2 PC gateway uses app-layer short-code authentication, no system bond required.
        if (!isRos2Gateway && bondState == BluetoothDevice.BOND_NONE) {
            dataLogManager?.addBleDebugLog(
                operation = BleOperation.CONNECT,
                message = "Device not bonded, attempting to create bond...",
                level = LogLevel.INFO,
                macAddress = address
            )
            try {
                val bondResult = device.createBond()
                dataLogManager?.addBleDebugLog(
                    operation = BleOperation.CONNECT,
                    message = "createBond() returned: $bondResult",
                    level = LogLevel.DEBUG,
                    macAddress = address
                )
            } catch (e: SecurityException) {
                dataLogManager?.logBleError(
                    message = "SecurityException creating bond: ${e.message}",
                    macAddress = address
                )
            }
        }

        connectionManager?.connectBle()
        performConnect(device)
    }

    private fun performConnect(device: BluetoothDevice) {
        val address = device.address
        connectRetryCount++

        dataLogManager?.addBleDebugLog(
            operation = BleOperation.CONNECT,
            message = "Connection attempt $connectRetryCount/$MAX_CONNECT_RETRY",
            level = LogLevel.INFO,
            macAddress = address
        )

        try {
            // Use autoConnect=false for direct connection, true for background auto-connect
            // For PC BLE devices, we typically want direct connection
            bluetoothGatt = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                device.connectGatt(context, false, gattCallback, BluetoothDevice.TRANSPORT_LE)
            } else {
                device.connectGatt(context, false, gattCallback)
            }
            LogUtil.d("BleManager connecting to $address (attempt $connectRetryCount)")
        } catch (e: Exception) {
            dataLogManager?.logBleError(
                message = "Connection failed (attempt $connectRetryCount): ${e.message}",
                macAddress = address
            )
            LogUtil.e(e, "BleManager connection failed")

            if (connectRetryCount < MAX_CONNECT_RETRY) {
                scope.launch {
                    delay(1000)
                    performConnect(device)
                }
            } else {
                connectionManager?.onBleError(ErrorCode.BLE_CONNECTION_FAILED)
            }
        }
    }

    private fun bondStateToString(state: Int): String {
        return when (state) {
            BluetoothDevice.BOND_NONE -> "NONE"
            BluetoothDevice.BOND_BONDING -> "BONDING"
            BluetoothDevice.BOND_BONDED -> "BONDED"
            else -> "UNKNOWN($state)"
        }
    }

    fun disconnect() {
        val address = _connectedDeviceAddress.value
        dataLogManager?.logBleDisconnect(
            message = "Disconnect requested by user",
            macAddress = address,
            level = LogLevel.INFO
        )
        reconnectManager?.cancelReconnect()
        bluetoothGatt?.disconnect()
        LogUtil.d("BleManager disconnect requested")
    }

    private fun close() {
        bluetoothGatt?.close()
        bluetoothGatt = null
        writeCharacteristic = null
        notifyCharacteristic = null
        pairControlCharacteristic = null
        networkInfoCharacteristic = null
        statusCharacteristic = null
        currentMtu = 23
        pendingProbeAfterNotify = false
        ros2PairControlNotifyReady = false
        ros2PairControlPollingMode = false
        pairNotifyFallbackJob?.cancel()
        pairNotifyFallbackJob = null
        pairProbeDispatchJob?.cancel()
        pairProbeDispatchJob = null
        pairReadAfterWriteJob?.cancel()
        pairReadAfterWriteJob = null
        bleOnlyReadyFallbackJob?.cancel()
        bleOnlyReadyFallbackJob = null
        ros2SessionReadRetryCount = 0
        probeState = null
        pendingPairContext = null
        pendingDiscoveredHost = null
        activeProfile = ActiveProfile.NONE
        stopStatusPolling()
        LogUtil.d("BleManager closed")
    }

    private fun enableNotifications(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic) {
        val deviceAddress = gatt.device?.address
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val localEnabled = gatt.setCharacteristicNotification(characteristic, true)
            LogUtil.d(
                "ROS2 BLE enableNotifications uuid=${characteristic.uuid} localEnabled=$localEnabled",
                TAG
            )
            val descriptor = characteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"))
            descriptor?.let {
                it.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                val writeStarted = gatt.writeDescriptor(it)
                LogUtil.d(
                    "ROS2 BLE CCCD write uuid=${characteristic.uuid} writeStarted=$writeStarted",
                    TAG
                )
                dataLogManager?.addBleDebugLog(
                    operation = BleOperation.CHARACTERISTIC_READ,
                    message = "Enabled notifications for ${characteristic.uuid} (writeStarted=$writeStarted)",
                    level = LogLevel.DEBUG,
                    macAddress = deviceAddress
                )
                if (activeProfile == ActiveProfile.ROS2 &&
                    characteristic.uuid == Ros2BleProfile.PAIR_CONTROL_UUID) {
                    if (writeStarted) {
                        scheduleRos2ProbeFallback()
                    } else if (pendingProbeAfterNotify) {
                        LogUtil.w(
                            "ROS2 BLE CCCD write did not start, dispatching probe immediately",
                            TAG
                        )
                        pendingProbeAfterNotify = false
                        ros2PairControlNotifyReady = false
                        dispatchRos2PairingProbe()
                    }
                }
            } ?: run {
                dataLogManager?.addBleDebugLog(
                    operation = BleOperation.CHARACTERISTIC_READ,
                    message = "CCCD missing for ${characteristic.uuid}, using read-after-write fallback",
                    level = LogLevel.WARN,
                    macAddress = deviceAddress
                )
                if (activeProfile == ActiveProfile.ROS2 &&
                    characteristic.uuid == Ros2BleProfile.PAIR_CONTROL_UUID &&
                    pendingProbeAfterNotify) {
                    LogUtil.w(
                        "ROS2 BLE CCCD missing, using direct probe fallback",
                        TAG
                    )
                    pendingProbeAfterNotify = false
                    ros2PairControlNotifyReady = false
                    dispatchRos2PairingProbe()
                }
            }
        }
    }

    private fun scheduleRos2ProbeFallback() {
        pairNotifyFallbackJob?.cancel()
        LogUtil.d("ROS2 BLE schedule pair probe fallback in 600ms", TAG)
        pairNotifyFallbackJob = scope.launch {
            delay(600)
            if (activeProfile == ActiveProfile.ROS2 && pendingProbeAfterNotify) {
                LogUtil.w("ROS2 BLE pair probe fallback fired before CCCD callback", TAG)
                pendingProbeAfterNotify = false
                ros2PairControlNotifyReady = false
                dispatchRos2PairingProbe()
            }
        }
    }

    private fun readPairControlCharacteristic() {
        val gatt = bluetoothGatt ?: return
        val characteristic = pairControlCharacteristic ?: return
        try {
            val started = gatt.readCharacteristic(characteristic)
            LogUtil.d("ROS2 BLE read pair_control started=$started", TAG)
        } catch (error: Exception) {
            LogUtil.e(error, "BleManager failed to read ROS2 pair control")
        }
    }

    private fun scheduleRos2SessionReadAfterWrite(reason: String) {
        scheduleRos2SessionReadRetry(reason, resetAttemptCounter = true)
    }

    private fun scheduleRos2SessionReadRetry(
        reason: String,
        resetAttemptCounter: Boolean = false
    ) {
        if (resetAttemptCounter) {
            ros2SessionReadRetryCount = 0
        }
        if (ros2SessionReadRetryCount >= ROS2_SESSION_READ_MAX_RETRIES) {
            LogUtil.w("ROS2 BLE session read retries exhausted reason=$reason", TAG)
            return
        }

        pairReadAfterWriteJob?.cancel()
        pairReadAfterWriteJob = scope.launch {
            val attempt = ros2SessionReadRetryCount + 1
            ros2SessionReadRetryCount = attempt
            val delayMs = if (attempt == 1) 350L else 500L
            delay(delayMs)
            if (activeProfile == ActiveProfile.ROS2 &&
                !ros2PairControlNotifyReady &&
                !_ros2GatewaySession.value.paired) {
                LogUtil.d("ROS2 BLE delayed pair_control read reason=$reason attempt=$attempt", TAG)
                readPairControlCharacteristic()
            }
        }
    }

    private fun maybeDispatchRos2PairProbe(reason: String) {
        if (activeProfile != ActiveProfile.ROS2 || !pendingProbeAfterNotify) {
            return
        }
        if (!isBleOnlyTransportMode() &&
            !ros2PairControlNotifyReady &&
            !ros2PairControlPollingMode) {
            LogUtil.d(
                "ROS2 BLE waiting for CCCD before probe reason=$reason mtu=$currentMtu",
                TAG
            )
            return
        }
        if (currentMtu > 23) {
            LogUtil.d("ROS2 BLE pair probe dispatch reason=$reason mtu=$currentMtu", TAG)
            dispatchRos2PairingProbe()
            return
        }
        LogUtil.w("ROS2 BLE waiting for MTU before probe reason=$reason mtu=$currentMtu", TAG)
        pairProbeDispatchJob?.cancel()
        pairProbeDispatchJob = scope.launch {
            delay(300)
            if (activeProfile == ActiveProfile.ROS2 && pendingProbeAfterNotify) {
                LogUtil.w("ROS2 BLE MTU wait timeout, dispatching probe with mtu=$currentMtu", TAG)
                dispatchRos2PairingProbe()
            }
        }
    }

    private fun startRssiMonitoring() {
        rssiJob?.cancel()
        rssiJob = scope.launch {
            while (isActive && isConnected()) {
                try {
                    bluetoothGatt?.readRemoteRssi()
                } catch (e: Exception) {
                    LogUtil.e(e, "Failed to read RSSI")
                }
                delay(RSSI_UPDATE_INTERVAL)
            }
        }
        dataLogManager?.addBleDebugLog(
            operation = BleOperation.RSSI_READ,
            message = "Started RSSI monitoring (interval: ${RSSI_UPDATE_INTERVAL}ms)",
            level = LogLevel.DEBUG,
            macAddress = _connectedDeviceAddress.value
        )
    }

    private fun stopRssiMonitoring() {
        rssiJob?.cancel()
        rssiJob = null
    }

    fun write(data: ByteArray): Boolean {
        val address = _connectedDeviceAddress.value

        if (_connectionState.value != BluetoothProfile.STATE_CONNECTED) {
            dataLogManager?.addBleDebugLog(
                operation = BleOperation.DATA_SEND,
                message = "Write ignored - not connected",
                level = LogLevel.WARN,
                macAddress = address
            )
            LogUtil.w("BleManager write ignored: not connected")
            return false
        }

        val queued = writeQueue.offer(data)
        if (!queued) {
            dataLogManager?.addBleDebugLog(
                operation = BleOperation.DATA_SEND,
                message = "Write queue overflow, dropping oldest data",
                level = LogLevel.WARN,
                macAddress = address
            )
            LogUtil.w("BleManager write queue overflow, dropping oldest")
            writeQueue.poll()
            writeQueue.offer(data)
        } else {
            dataLogManager?.addBleDebugLog(
                operation = BleOperation.DATA_SEND,
                message = "Data queued for sending (${data.size} bytes), queue size: ${writeQueue.size()}",
                level = LogLevel.DEBUG,
                macAddress = address,
                data = data
            )
        }

        processWriteQueue()
        return true
    }

    private fun startWriteProcessor() {
        writeJob?.cancel()
        writeJob = scope.launch {
            while (isActive) {
                if (!writeQueue.isEmpty() && !isWriting) {
                    processWriteQueue()
                }
                delay(5)
            }
        }
    }

    private fun refreshGattCache(gatt: BluetoothGatt?): Boolean {
        val targetGatt = gatt ?: return false
        return runCatching {
            val method = BluetoothGatt::class.java.getMethod("refresh")
            (method.invoke(targetGatt) as? Boolean) == true
        }.getOrDefault(false)
    }

    private fun describeGattProperties(characteristic: BluetoothGattCharacteristic?): String {
        val properties = characteristic?.properties ?: return "-"
        val labels = mutableListOf<String>()
        if (properties and BluetoothGattCharacteristic.PROPERTY_READ != 0) {
            labels += "read"
        }
        if (properties and BluetoothGattCharacteristic.PROPERTY_WRITE != 0) {
            labels += "write"
        }
        if (properties and BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE != 0) {
            labels += "write_no_response"
        }
        if (properties and BluetoothGattCharacteristic.PROPERTY_NOTIFY != 0) {
            labels += "notify"
        }
        if (properties and BluetoothGattCharacteristic.PROPERTY_INDICATE != 0) {
            labels += "indicate"
        }
        if (labels.isEmpty()) {
            return "0x${properties.toString(16)}"
        }
        return labels.joinToString("|")
    }

    private fun stopWriteProcessor() {
        writeJob?.cancel()
        writeJob = null
        writeQueue.clear()
        isWriting = false
    }

    private fun processWriteQueue() {
        if (isWriting || writeQueue.isEmpty()) return
        if (_connectionState.value != BluetoothProfile.STATE_CONNECTED) return

        val data = writeQueue.poll() ?: return
        val address = _connectedDeviceAddress.value

        isWriting = true
        val success = writeCharacteristicData(
            characteristic = writeCharacteristic,
            data = data,
            writeType = if (activeProfile == ActiveProfile.ROS2) {
                ros2ControlWriteType(writeCharacteristic)
            } else {
                BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE
            }
        )
        if (success) {
            _lastWriteTime.value = System.currentTimeMillis()
            dataLogManager?.addBleSentLog(data, address)
            dataLogManager?.addBleDebugLog(
                operation = BleOperation.DATA_SEND,
                message = "Data sent successfully (${data.size} bytes)",
                level = LogLevel.DEBUG,
                macAddress = address,
                data = data
            )
            connectionManager?.onBleActivity(bytesSent = data.size.toLong())
        } else {
            dataLogManager?.addBleDebugLog(
                operation = BleOperation.DATA_SEND,
                message = "writeCharacteristic failed",
                level = LogLevel.WARN,
                macAddress = address
            )
            LogUtil.w("BleManager writeCharacteristic failed")
            isWriting = false
        }
    }

    fun isConnected(): Boolean = _connectionState.value == BluetoothProfile.STATE_CONNECTED

    fun getQueueSize(): Int = writeQueue.size()

    fun clearWriteQueue() {
        writeQueue.clear()
        dataLogManager?.addBleDebugLog(
            operation = BleOperation.DATA_SEND,
            message = "Write queue cleared",
            level = LogLevel.DEBUG,
            macAddress = _connectedDeviceAddress.value
        )
        LogUtil.d("BleManager write queue cleared")
    }

    fun shutdown() {
        dataLogManager?.addBleDebugLog(
            operation = BleOperation.CONNECT,
            message = "BleManager shutdown initiated",
            level = LogLevel.INFO,
            macAddress = _connectedDeviceAddress.value
        )
        stopWriteProcessor()
        stopScan()
        stopRssiMonitoring()
        disconnect()
        close()

        // Unregister bond state receiver
        try {
            context.unregisterReceiver(bondStateReceiver)
            LogUtil.d("BleManager: Bond state receiver unregistered")
        } catch (e: Exception) {
            // Receiver might not be registered
        }

        scope.cancel()
        LogUtil.d("BleManager shutdown")
    }

    class NonBlockingQueue<T>(private val maxSize: Int = 1000) {
        private val queue = ConcurrentLinkedQueue<T>()

        fun offer(item: T): Boolean {
            if (queue.size >= maxSize) {
                queue.poll()
            }
            return queue.offer(item)
        }

        fun poll(): T? = queue.poll()

        fun peek(): T? = queue.peek()

        fun isEmpty(): Boolean = queue.isEmpty()

        fun size(): Int = queue.size

        fun clear() = queue.clear()

        fun toList(): List<T> = queue.toList()
    }
}
