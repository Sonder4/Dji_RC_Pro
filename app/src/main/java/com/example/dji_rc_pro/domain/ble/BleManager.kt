package com.example.dji_rc_pro.domain.ble

import android.annotation.SuppressLint
import android.bluetooth.*
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

@SuppressLint("MissingPermission") // Permissions are handled in UI/Activity
class BleManager private constructor(context: Context) {

    companion object {
        private const val TAG = "BleManager"
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: BleManager? = null

        fun getInstance(context: Context): BleManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: BleManager(context.applicationContext).also { INSTANCE = it }
            }
        }

        // Standard Nordic UART Service
        val UART_SERVICE_UUID: UUID = UUID.fromString("6E400001-B5A3-F393-E0A9-E50E24DCCA9E")
        val UART_RX_CHAR_UUID: UUID = UUID.fromString("6E400002-B5A3-F393-E0A9-E50E24DCCA9E") // Write to this
        val UART_TX_CHAR_UUID: UUID = UUID.fromString("6E400003-B5A3-F393-E0A9-E50E24DCCA9E") // Read from this
    }

    private val bluetoothAdapter: BluetoothAdapter? by lazy {
        val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothManager.adapter
    }

    private var bluetoothGatt: BluetoothGatt? = null
    private var writeCharacteristic: BluetoothGattCharacteristic? = null

    // State
    private val _connectionState = MutableStateFlow(BluetoothProfile.STATE_DISCONNECTED)
    val connectionState: StateFlow<Int> = _connectionState.asStateFlow()

    private val _isScanning = MutableStateFlow(false)
    val isScanning: StateFlow<Boolean> = _isScanning.asStateFlow()

    private val _scanResults = MutableStateFlow<List<ScanResult>>(emptyList())
    val scanResults: StateFlow<List<ScanResult>> = _scanResults.asStateFlow()

    private val scanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            result?.let { newResult ->
                val currentList = _scanResults.value.toMutableList()
                val existingIndex = currentList.indexOfFirst { it.device.address == newResult.device.address }
                
                if (existingIndex >= 0) {
                    currentList[existingIndex] = newResult
                } else {
                    currentList.add(newResult)
                }
                _scanResults.value = currentList
            }
        }

        override fun onScanFailed(errorCode: Int) {
            Log.e(TAG, "Scan failed: $errorCode")
            _isScanning.value = false
        }
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            Log.i(TAG, "Connection State Change: $status -> $newState")
            _connectionState.value = newState
            
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                gatt?.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                close()
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.i(TAG, "Services Discovered")
                val service = gatt?.getService(UART_SERVICE_UUID)
                if (service != null) {
                    writeCharacteristic = service.getCharacteristic(UART_RX_CHAR_UUID)
                    Log.i(TAG, "Write Characteristic Found: $writeCharacteristic")
                } else {
                    Log.w(TAG, "UART Service not found!")
                    // Fallback: Try to find ANY writable characteristic if standard not found?
                    // For now, strict check.
                }
            }
        }
    }

    fun startScan() {
        if (bluetoothAdapter?.isEnabled == true && !_isScanning.value) {
            _scanResults.value = emptyList()
            bluetoothAdapter?.bluetoothLeScanner?.startScan(
                null, // Filter can be added later
                ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build(),
                scanCallback
            )
            _isScanning.value = true
            Log.i(TAG, "Started Scan")
        }
    }

    fun stopScan() {
        if (_isScanning.value) {
            bluetoothAdapter?.bluetoothLeScanner?.stopScan(scanCallback)
            _isScanning.value = false
            Log.i(TAG, "Stopped Scan")
        }
    }

    fun connect(device: BluetoothDevice, context: Context) {
        stopScan()
        Log.i(TAG, "Connecting to ${device.address}")
        bluetoothGatt = device.connectGatt(context, false, gattCallback)
    }

    fun disconnect() {
        bluetoothGatt?.disconnect()
    }

    private fun close() {
        bluetoothGatt?.close()
        bluetoothGatt = null
        writeCharacteristic = null
    }

    fun write(data: ByteArray) {
        val gatt = bluetoothGatt ?: return
        val char = writeCharacteristic ?: return

        if (_connectionState.value != BluetoothProfile.STATE_CONNECTED) return

        // Legacy: char.value = data; gatt.writeCharacteristic(char)
        // Android 13 (API 33)+ usage is different, but for minSdk 29 this works.
        // Ideally should queue writes, but for 50Hz control stream, we just fire and forget mostly,
        // or drop if busy.
        
        char.value = data
        char.writeType = BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE // Faster for RC
        
        try {
            gatt.writeCharacteristic(char)
        } catch (e: SecurityException) {
            Log.e(TAG, "Write failed", e)
        }
    }
}
