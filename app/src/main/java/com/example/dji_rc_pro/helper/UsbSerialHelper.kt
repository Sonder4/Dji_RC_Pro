package com.example.dji_rc_pro.helper

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbManager
import android.os.Build
import android.util.Log
import com.hoho.android.usbserial.driver.UsbSerialPort
import com.hoho.android.usbserial.driver.UsbSerialProber
import com.hoho.android.usbserial.util.SerialInputOutputManager
import java.io.IOException

class UsbSerialHelper(private val context: Context) : SerialInputOutputManager.Listener {

    private val usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
    private var usbSerialPort: UsbSerialPort? = null
    private var ioManager: SerialInputOutputManager? = null
    
    // Callbacks
    var onDataReceived: ((ByteArray) -> Unit)? = null
    var onDeviceStateChanged: ((String) -> Unit)? = null

    companion object {
        private const val ACTION_USB_PERMISSION = "com.example.dji_rc_pro.USB_PERMISSION"
        private const val BAUD_RATE = 115200 // Default for STM32
        private const val TAG = "UsbSerialHelper"
    }

    /**
     * Finds and connects to the first available USB Serial device (CDC/ACM).
     */
    fun connectDevice() {
        val availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(usbManager)
        if (availableDrivers.isEmpty()) {
            onDeviceStateChanged?.invoke("未发现 USB 串口设备")
            return
        }

        // Default to first device
        val driver = availableDrivers[0]
        val connection = usbManager.openDevice(driver.device)

        if (connection == null) {
            onDeviceStateChanged?.invoke("请求 USB 权限中...")
            val permissionIntent = PendingIntent.getBroadcast(
                context, 0, Intent(ACTION_USB_PERMISSION),
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_MUTABLE else 0
            )
            usbManager.requestPermission(driver.device, permissionIntent)
            return
        }

        try {
            usbSerialPort = driver.ports[0] // Most devices have 1 port
            usbSerialPort?.open(connection)
            
            // Set parameters: 115200, 8, 1, None
            usbSerialPort?.setParameters(BAUD_RATE, 8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE)
            
            // Start IO
            ioManager = SerialInputOutputManager(usbSerialPort, this)
            ioManager?.start()
            
            onDeviceStateChanged?.invoke("已连接到: ${driver.device.productName ?: "Unknown Device"}")
            Log.d(TAG, "Connected to ${driver.device.deviceName}")
            
        } catch (e: IOException) {
            Log.e(TAG, "Connection failed", e)
            onDeviceStateChanged?.invoke("连接失败: ${e.message}")
            disconnect()
        }
    }

    fun sendData(data: String) {
        try {
            if (usbSerialPort != null) {
                val bytes = data.toByteArray(Charsets.UTF_8)
                usbSerialPort?.write(bytes, 1000) // 1000ms timeout
            } else {
                onDeviceStateChanged?.invoke("设备未连接")
            }
        } catch (e: IOException) {
            onDeviceStateChanged?.invoke("发送失败: ${e.message}")
        }
    }
    
    fun sendBytes(bytes: ByteArray) {
        try {
            if (usbSerialPort != null) {
                usbSerialPort?.write(bytes, 1000)
            } else {
                onDeviceStateChanged?.invoke("设备未连接")
            }
        } catch (e: IOException) {
            onDeviceStateChanged?.invoke("发送失败: ${e.message}")
        }
    }

    fun disconnect() {
        ioManager?.listener = null
        ioManager?.stop()
        ioManager = null
        try {
            usbSerialPort?.close()
        } catch (e: IOException) {
            // ignore
        }
        usbSerialPort = null
        onDeviceStateChanged?.invoke("已断开连接")
    }

    override fun onNewData(data: ByteArray) {
        onDataReceived?.invoke(data)
    }

    override fun onRunError(e: Exception) {
        Log.e(TAG, "IO Error", e)
        onDeviceStateChanged?.invoke("连接中断")
        disconnect()
    }
}
