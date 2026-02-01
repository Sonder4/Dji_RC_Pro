package com.example.dji_rc_pro.domain.ble

import android.annotation.SuppressLint
import android.bluetooth.*
import android.bluetooth.BluetoothGatt
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.util.Log
import com.example.dji_rc_pro.manager.ConnectionManager
import com.example.dji_rc_pro.manager.FrequencyManager
import com.example.dji_rc_pro.manager.HeartbeatManager
import com.example.dji_rc_pro.manager.ReconnectManager
import com.example.dji_rc_pro.util.ErrorCode
import com.example.dji_rc_pro.util.LogUtil
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue

@SuppressLint("MissingPermission")
class BleManager private constructor(context: Context) {

    companion object {
        private const val TAG = "BleManager"
        private const val WRITE_QUEUE_MAX_SIZE = 1000
        private const val MAX_WRITE_ATTEMPTS = 3

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
    }

    private val context: Context = context.applicationContext
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val bluetoothAdapter: BluetoothAdapter? by lazy {
        val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private var bluetoothGatt: BluetoothGatt? = null
    private var writeCharacteristic: BluetoothGattCharacteristic? = null

    private var connectionManager: ConnectionManager? = null
    private var heartbeatManager: HeartbeatManager? = null
    private var reconnectManager: ReconnectManager? = null
    private var frequencyManager: FrequencyManager? = null

    private val writeQueue = NonBlockingQueue<ByteArray>(WRITE_QUEUE_MAX_SIZE)
    private var writeJob: Job? = null
    private var isWriting = false

    private val _connectionState = MutableStateFlow(BluetoothProfile.STATE_DISCONNECTED)
    val connectionState: StateFlow<Int> = _connectionState.asStateFlow()

    private val _isScanning = MutableStateFlow(false)
    val isScanning: StateFlow<Boolean> = _isScanning.asStateFlow()

    private val _lastWriteTime = MutableStateFlow(0L)
    val lastWriteTime: StateFlow<Long> = _lastWriteTime.asStateFlow()

    data class BleDevice(
        val device: BluetoothDevice,
        val rssi: Int = 0,
        val isPaired: Boolean = false
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
                val currentList = _scanResults.value.toMutableList()
                val existingIndex = currentList.indexOfFirst { it.device.address == newResult.device.address }

                if (existingIndex >= 0) {
                    val existing = currentList[existingIndex]
                    currentList[existingIndex] = existing.copy(
                        device = newResult.device,
                        rssi = newResult.rssi
                    )
                } else {
                    val isBonded = try {
                        newResult.device.bondState == BluetoothDevice.BOND_BONDED
                    } catch (e: SecurityException) {
                        false
                    }
                    currentList.add(BleDevice(newResult.device, newResult.rssi, isBonded))
                }

                currentList.sortWith(compareByDescending<BleDevice> { it.isPaired }.thenByDescending { it.rssi })
                _scanResults.value = currentList
            }
        }

        override fun onScanFailed(errorCode: Int) {
            LogUtil.e("BleManager scan failed: $errorCode")
            _isScanning.value = false
            connectionManager?.onBleError(ErrorCode.BLE_SCAN_FAILED)
        }
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            LogUtil.d("BleManager connection state change: $status -> $newState")
            _connectionState.value = newState

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                connectionManager?.onBleConnected()
                heartbeatManager?.startBleHeartbeat()
                startWriteProcessor()
                gatt?.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                heartbeatManager?.stopBleHeartbeat()
                stopWriteProcessor()

                val errorCode = when (status) {
                    14 -> ErrorCode.BLE_CONNECTION_REFUSED
                    133 -> ErrorCode.BLE_CONNECTION_FAILED
                    257 -> ErrorCode.BLE_CONNECTION_FAILED
                    else -> ErrorCode.BLE_CONNECTION_FAILED
                }

                connectionManager?.onBleDisconnected(errorCode)

                reconnectManager?.let { rc ->
                    if (!rc.isReconnecting()) {
                        gatt?.device?.let { device ->
                            rc.startReconnect("BLE", errorCode)
                        }
                    }
                }

                close()
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                LogUtil.d("BleManager services discovered")
                val service = gatt?.getService(UART_SERVICE_UUID)
                if (service != null) {
                    writeCharacteristic = service.getCharacteristic(UART_RX_CHAR_UUID)
                    if (writeCharacteristic != null) {
                        LogUtil.d("BleManager write characteristic found")
                    } else {
                        LogUtil.w("BleManager write characteristic not found")
                        connectionManager?.onBleError(ErrorCode.BLE_CHARACTERISTIC_NOT_FOUND)
                    }
                } else {
                    LogUtil.w("BleManager UART service not found")
                    connectionManager?.onBleError(ErrorCode.BLE_SERVICE_NOT_FOUND)
                }
            } else {
                LogUtil.e("BleManager service discovery failed: $status")
            }
        }

        override fun onCharacteristicWrite(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                isWriting = false
                processWriteQueue()
            } else {
                LogUtil.w("BleManager characteristic write failed: $status")
                isWriting = false
            }
        }

        override fun onCharacteristicChanged(gatt: BluetoothGatt?, characteristic: BluetoothGattCharacteristic?) {
            characteristic?.value?.let { data ->
                heartbeatManager?.onHeartbeatReceived("BLE")
                connectionManager?.onBleActivity(bytesReceived = data.size.toLong())
            }
        }
    }

    fun initialize(
        connManager: ConnectionManager,
        hbManager: HeartbeatManager,
        rcManager: ReconnectManager,
        freqManager: FrequencyManager
    ) {
        this.connectionManager = connManager
        this.heartbeatManager = hbManager
        this.reconnectManager = rcManager
        this.frequencyManager = freqManager
        LogUtil.d("BleManager initialized with managers")
    }

    fun startScan() {
        if (bluetoothAdapter?.isEnabled == true && !_isScanning.value) {
            val bondedDevices = try {
                bluetoothAdapter?.bondedDevices?.map {
                    BleDevice(it, 0, true)
                } ?: emptyList()
            } catch (e: SecurityException) {
                emptyList()
            }

            _scanResults.value = bondedDevices

            bluetoothAdapter?.bluetoothLeScanner?.startScan(
                null,
                ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build(),
                scanCallback
            )
            _isScanning.value = true
            LogUtil.d("BleManager started scan")
        }
    }

    fun stopScan() {
        if (_isScanning.value) {
            bluetoothAdapter?.bluetoothLeScanner?.stopScan(scanCallback)
            _isScanning.value = false
            LogUtil.d("BleManager stopped scan")
        }
    }

    fun connect(device: BluetoothDevice) {
        stopScan()
        LogUtil.d("BleManager connecting to ${device.address}")

        connectionManager?.connectBle()

        try {
            bluetoothGatt = device.connectGatt(context, false, gattCallback)
        } catch (e: Exception) {
            LogUtil.e(e, "BleManager connection failed")
            connectionManager?.onBleError(ErrorCode.BLE_CONNECTION_FAILED)
        }
    }

    fun disconnect() {
        reconnectManager?.cancelReconnect()
        bluetoothGatt?.disconnect()
        LogUtil.d("BleManager disconnect requested")
    }

    private fun close() {
        bluetoothGatt?.close()
        bluetoothGatt = null
        writeCharacteristic = null
        LogUtil.d("BleManager closed")
    }

    fun write(data: ByteArray): Boolean {
        if (_connectionState.value != BluetoothProfile.STATE_CONNECTED) {
            LogUtil.w("BleManager write ignored: not connected")
            return false
        }

        val queued = writeQueue.offer(data)
        if (!queued) {
            LogUtil.w("BleManager write queue overflow, dropping oldest")
            writeQueue.poll()
            writeQueue.offer(data)
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

    private fun stopWriteProcessor() {
        writeJob?.cancel()
        writeJob = null
        writeQueue.clear()
        isWriting = false
    }

    private fun processWriteQueue() {
        if (isWriting || writeQueue.isEmpty()) return

        val char = writeCharacteristic ?: return
        if (_connectionState.value != BluetoothProfile.STATE_CONNECTED) return

        val data = writeQueue.poll() ?: return

        isWriting = true
        char.value = data
        char.writeType = BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE

        try {
            val success = bluetoothGatt?.writeCharacteristic(char) == true
            if (success) {
                _lastWriteTime.value = System.currentTimeMillis()
                connectionManager?.onBleActivity(bytesSent = data.size.toLong())
            } else {
                LogUtil.w("BleManager writeCharacteristic failed")
                isWriting = false
            }
        } catch (e: SecurityException) {
            LogUtil.e(e, "BleManager write failed")
            isWriting = false
        }
    }

    fun isConnected(): Boolean = _connectionState.value == BluetoothProfile.STATE_CONNECTED

    fun getQueueSize(): Int = writeQueue.size()

    fun clearWriteQueue() {
        writeQueue.clear()
        LogUtil.d("BleManager write queue cleared")
    }

    fun shutdown() {
        stopWriteProcessor()
        stopScan()
        disconnect()
        close()
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
