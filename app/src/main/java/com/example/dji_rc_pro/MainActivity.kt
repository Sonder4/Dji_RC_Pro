package com.example.dji_rc_pro

import android.os.Bundle
import android.Manifest
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.dji_rc_pro.ui.MainScreen
import com.example.dji_rc_pro.ui.theme.Dji_RC_ProTheme
import com.example.dji_rc_pro.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.entries.forEach {
            android.util.Log.i("MainActivity", "Permission ${it.key}: ${it.value}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        requestPermissions()

        setContent {
            Dji_RC_ProTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }

    private fun requestPermissions() {
        val permissions = mutableListOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_PHONE_STATE
        )
        
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            permissions.add(Manifest.permission.BLUETOOTH_SCAN)
            permissions.add(Manifest.permission.BLUETOOTH_CONNECT)
        }

        permissionLauncher.launch(permissions.toTypedArray())
    }

    override fun onKeyDown(keyCode: Int, event: android.view.KeyEvent?): Boolean {
        handleKeyEvent(keyCode, true)
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: android.view.KeyEvent?): Boolean {
        handleKeyEvent(keyCode, false)
        return super.onKeyUp(keyCode, event)
    }

    private fun handleKeyEvent(keyCode: Int, isDown: Boolean) {
        val bitIndex = when (keyCode) {
            android.view.KeyEvent.KEYCODE_BUTTON_A -> 0
            android.view.KeyEvent.KEYCODE_BUTTON_B -> 1
            android.view.KeyEvent.KEYCODE_BUTTON_X -> 2
            android.view.KeyEvent.KEYCODE_BUTTON_Y -> 3
            android.view.KeyEvent.KEYCODE_BUTTON_L1 -> 4
            android.view.KeyEvent.KEYCODE_BUTTON_R1 -> 5
            android.view.KeyEvent.KEYCODE_DPAD_UP -> 10
            android.view.KeyEvent.KEYCODE_DPAD_DOWN -> 11
            android.view.KeyEvent.KEYCODE_DPAD_LEFT -> 12
            android.view.KeyEvent.KEYCODE_DPAD_RIGHT -> 13
            else -> return
        }
        com.example.dji_rc_pro.domain.msdk.MsdkManager.instance.updateButtonMask(bitIndex, isDown)
    }

    override fun onGenericMotionEvent(event: android.view.MotionEvent): Boolean {
        // Broaden check to include Gamepad and others, or just log everything for debug
        // if ((event.source and android.view.InputDevice.SOURCE_CLASS_JOYSTICK) != 0) {
        
        // Always scan axes for debug purposes
        val debugBuilder = StringBuilder()
        debugBuilder.append("Src: ${event.source} | ")
        
        val axes = mapOf(
            "X" to android.view.MotionEvent.AXIS_X,
            "Y" to android.view.MotionEvent.AXIS_Y,
            "Z" to android.view.MotionEvent.AXIS_Z,
            "RX" to android.view.MotionEvent.AXIS_RX,
            "RY" to android.view.MotionEvent.AXIS_RY,
            "RZ" to android.view.MotionEvent.AXIS_RZ,
            "HAT_X" to android.view.MotionEvent.AXIS_HAT_X,
            "HAT_Y" to android.view.MotionEvent.AXIS_HAT_Y,
            "LTRIGGER" to android.view.MotionEvent.AXIS_LTRIGGER,
            "RTRIGGER" to android.view.MotionEvent.AXIS_RTRIGGER,
            "THROTTLE" to android.view.MotionEvent.AXIS_THROTTLE,
            "RUDDER" to android.view.MotionEvent.AXIS_RUDDER,
            "WHEEL" to android.view.MotionEvent.AXIS_WHEEL,
            "GAS" to android.view.MotionEvent.AXIS_GAS,
            "BRAKE" to android.view.MotionEvent.AXIS_BRAKE,
            "VSCROLL" to android.view.MotionEvent.AXIS_VSCROLL,
            "HSCROLL" to android.view.MotionEvent.AXIS_HSCROLL,
            "GENERIC_1" to android.view.MotionEvent.AXIS_GENERIC_1
        )

        var hasMovement = false
        axes.forEach { (name, axis) ->
            val value = event.getAxisValue(axis)
            if (kotlin.math.abs(value) > 0.01f) {
                debugBuilder.append("$name: ${String.format("%.2f", value)}  ")
                hasMovement = true
            }
        }
        
        if (hasMovement) {
             com.example.dji_rc_pro.domain.msdk.MsdkManager.instance.updateAndroidDebug(debugBuilder.toString())
        }

        // Try to capture wheels from likely axes (Z/RZ are common for joystick throttles/rudders, but wheels might be different)
        // If it is a joystick/gamepad:
        if ((event.source and android.view.InputDevice.SOURCE_CLASS_JOYSTICK) != 0 || 
            (event.source and android.view.InputDevice.SOURCE_GAMEPAD) != 0) {
            
            val axisZ = event.getAxisValue(android.view.MotionEvent.AXIS_Z)
            val axisRz = event.getAxisValue(android.view.MotionEvent.AXIS_RZ)
            
            // Only update if these look like they are active (non-zero or changed)
            // But we need to be careful not to overwrite if they are 0 and stick is used?
            // Usually Z/RZ are separate from X/Y.
            
            val leftWheelVal = com.example.dji_rc_pro.domain.input.StickTransformer.transform(axisZ)
            val rightWheelVal = com.example.dji_rc_pro.domain.input.StickTransformer.transform(axisRz)
            
            com.example.dji_rc_pro.domain.msdk.MsdkManager.instance.updateLeftWheel(leftWheelVal)
            com.example.dji_rc_pro.domain.msdk.MsdkManager.instance.updateRightWheel(rightWheelVal)
            
            return true
        }
        return super.onGenericMotionEvent(event)
    }
}