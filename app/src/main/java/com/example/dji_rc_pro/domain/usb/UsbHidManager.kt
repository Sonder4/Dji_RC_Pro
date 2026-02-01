package com.example.dji_rc_pro.domain.usb

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.*
import android.util.Log
import kotlinx.coroutines.*

private const val TAG = "UsbHidManager"

class UsbHidManager(private val context: Context) {

    companion object {
        const val ACTION_USB_PERMISSION = "com.example.dji_rc_pro.USB_PERMISSION"
        // DJI Virtual Joystick / RC Plus 2 HID
        const val DJI_VID = 0x2CA3
        const val DJI_PID = 0x1501
    }

    private val usbManager: UsbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
    private var usbDevice: UsbDevice? = null
    private var connection: UsbDeviceConnection? = null
    private var usbInterface: UsbInterface? = null
    private var endpointIn: UsbEndpoint? = null

    private var readJob: Job? = null
    private var isReading = false

    // Callbacks
    var onDataReceived: ((data: ByteArray, length: Int) -> Unit)? = null
    var onStatusChanged: ((status: String, isConnected: Boolean) -> Unit)? = null
    var onError: ((error: String) -> Unit)? = null

    private val usbReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == ACTION_USB_PERMISSION) {
                synchronized(this) {
                    val device: UsbDevice? = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                        intent.getParcelableExtra(UsbManager.EXTRA_DEVICE, UsbDevice::class.java)
                    } else {
                        @Suppress("DEPRECATION")
                        intent.getParcelableExtra(UsbManager.EXTRA_DEVICE)
                    }
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        device?.let {
                            Log.i(TAG, "USB permission granted for ${it.deviceName}")
                            openDevice(it)
                        }
                    } else {
                        val errorMsg = "USB permission denied"
                        Log.e(TAG, errorMsg)
                        onStatusChanged?.invoke(errorMsg, false)
                        onError?.invoke(errorMsg)
                    }
                }
            }
        }
    }

    init {
        val filter = IntentFilter(ACTION_USB_PERMISSION)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            context.registerReceiver(usbReceiver, filter, Context.RECEIVER_NOT_EXPORTED)
        } else {
            context.registerReceiver(usbReceiver, filter)
        }
        Log.i(TAG, "UsbHidManager initialized")
    }

    fun findDevice(): Boolean {
        Log.d(TAG, "Searching for DJI RC (VID: ${DJI_VID.toString(16)}, PID: ${DJI_PID.toString(16)})...")
        val deviceList = usbManager.deviceList
        for (device in deviceList.values) {
            if (device.vendorId == DJI_VID && device.productId == DJI_PID) {
                usbDevice = device
                Log.i(TAG, "DJI RC found: ${device.deviceName}")
                onStatusChanged?.invoke("Device Found: ${device.deviceName}", false)
                return true
            }
        }
        val errorMsg = "DJI RC Not Found"
        Log.e(TAG, errorMsg)
        onStatusChanged?.invoke(errorMsg, false)
        return false
    }

    fun requestPermission() {
        val device = usbDevice ?: run {
            if (!findDevice()) return
            usbDevice
        }
        
        if (device == null) return

        if (usbManager.hasPermission(device)) {
            openDevice(device)
        } else {
            val flags = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                PendingIntent.FLAG_MUTABLE
            } else {
                0
            }
            val intent = PendingIntent.getBroadcast(context, 0, Intent(ACTION_USB_PERMISSION), flags)
            usbManager.requestPermission(device, intent)
            onStatusChanged?.invoke("Requesting USB Permission...", false)
        }
    }

    private fun openDevice(device: UsbDevice): Boolean {
        connection = usbManager.openDevice(device)
        if (connection == null) {
            onError?.invoke("Failed to open USB device")
            return false
        }

        usbInterface = device.getInterface(0)
        if (!connection!!.claimInterface(usbInterface, true)) {
            connection?.close()
            connection = null
            onError?.invoke("Failed to claim USB interface")
            return false
        }

        for (i in 0 until usbInterface!!.endpointCount) {
            val ep = usbInterface!!.getEndpoint(i)
            if (ep.direction == UsbConstants.USB_DIR_IN) {
                endpointIn = ep
                break
            }
        }

        if (endpointIn == null) {
            connection?.releaseInterface(usbInterface)
            connection?.close()
            connection = null
            onError?.invoke("No IN endpoint found")
            return false
        }

        Log.i(TAG, "USB device opened successfully")
        onStatusChanged?.invoke("Connected", true)
        return true
    }

    fun startReading() {
        if (connection == null || endpointIn == null) {
            requestPermission()
            return
        }

        if (isReading) return
        isReading = true

        readJob = CoroutineScope(Dispatchers.IO).launch {
            val readBuffer = ByteArray(endpointIn!!.maxPacketSize)
            while (isActive && isReading) {
                val len = connection!!.bulkTransfer(endpointIn!!, readBuffer, readBuffer.size, 100)
                if (len > 0) {
                    onDataReceived?.invoke(readBuffer.copyOf(len), len)
                }
                delay(10)
            }
        }
    }

    fun stopReading() {
        isReading = false
        readJob?.cancel()
        readJob = null
    }

    fun close() {
        stopReading()
        connection?.let {
            usbInterface?.let { iface -> it.releaseInterface(iface) }
            it.close()
        }
        connection = null
        usbInterface = null
        endpointIn = null
        usbDevice = null
        onStatusChanged?.invoke("Disconnected", false)
    }

    fun release() {
        close()
        try {
            context.unregisterReceiver(usbReceiver)
        } catch (e: Exception) {}
    }
}
