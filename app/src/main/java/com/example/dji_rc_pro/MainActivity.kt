package com.example.dji_rc_pro

import android.Manifest
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.dji_rc_pro.ui.MainScreen
import com.example.dji_rc_pro.ui.theme.Dji_RC_ProTheme
import com.example.dji_rc_pro.util.LogUtil
import com.example.dji_rc_pro.viewmodel.MainViewModel

/**
 * Main entry point for DJI RC Pro application
 * Handles permissions and UI setup
 */
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        permissions.forEach { (permission, granted) ->
            LogUtil.i("Permission $permission: $granted", TAG)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
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
            permissions += listOf(
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT
            )
        }

        permissionLauncher.launch(permissions.toTypedArray())
    }

    override fun onKeyDown(keyCode: Int, event: android.view.KeyEvent?): Boolean {
        LogUtil.i("KeyDown: code=$keyCode, device=${event?.device?.name}", TAG)
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: android.view.KeyEvent?): Boolean {
        LogUtil.i("KeyUp: code=$keyCode, device=${event?.device?.name}", TAG)
        return super.onKeyUp(keyCode, event)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
