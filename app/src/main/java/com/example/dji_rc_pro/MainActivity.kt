package com.example.dji_rc_pro

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.dji_rc_pro.domain.config.ConnectionMode
import com.example.dji_rc_pro.domain.config.DebugLaunchOverrideStore
import com.example.dji_rc_pro.domain.config.TransportIsolationMode
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
        DebugLaunchOverrideStore.capture(intent)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        enableEdgeToEdge()
        requestPermissions()
        applyDebugIntent(intent)

        setContent {
            Dji_RC_ProTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        DebugLaunchOverrideStore.capture(intent)
        applyDebugIntent(intent)
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

    private fun applyDebugIntent(intent: Intent?) {
        intent ?: return

        intent.getStringExtra(EXTRA_TRANSPORT_MODE)?.let { rawMode ->
            val mode = TransportIsolationMode.fromStorageValue(rawMode)
            viewModel.applyDebugTransportIsolationMode(mode)
            LogUtil.i("Applied debug transport mode=$rawMode", TAG)
        }

        intent.getStringExtra(EXTRA_CONNECTION_MODE)?.let { rawMode ->
            val mode = ConnectionMode.fromStorageValue(rawMode)
            viewModel.applyDebugConnectionMode(mode)
            LogUtil.i("Applied debug connection mode=$rawMode", TAG)
        }

        intent.getStringExtra(EXTRA_PAIR_CODE)?.let { pairCode ->
            viewModel.applyDebugPairCode(pairCode)
            LogUtil.i("Applied debug pair code", TAG)
        }

        intent.getStringExtra(EXTRA_TARGET_IP)?.takeIf { it.isNotBlank() }?.let { targetIp ->
            viewModel.applyDebugTargetIp(targetIp)
            LogUtil.i("Applied debug target ip=$targetIp", TAG)
        }

        if (intent.hasExtra(EXTRA_TARGET_PORT)) {
            val port = intent.getIntExtra(EXTRA_TARGET_PORT, -1)
            if (port > 0) {
                viewModel.applyDebugTargetPort(port.toString())
                LogUtil.i("Applied debug target port=$port", TAG)
            }
        }

        if (intent.getBooleanExtra(EXTRA_FORGET_PAIRING, false)) {
            viewModel.forgetPairing()
            LogUtil.i("Applied debug forget pairing", TAG)
        }

        if (intent.getBooleanExtra(EXTRA_START_PRIMARY, false)) {
            viewModel.toggleUdp()
            LogUtil.i("Applied debug start primary", TAG)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        const val EXTRA_TRANSPORT_MODE = "transport_mode"
        const val EXTRA_CONNECTION_MODE = "connection_mode"
        const val EXTRA_PAIR_CODE = "pair_code"
        const val EXTRA_TARGET_IP = "target_ip"
        const val EXTRA_TARGET_PORT = "target_port"
        const val EXTRA_START_PRIMARY = "start_primary"
        const val EXTRA_FORGET_PAIRING = "forget_pairing"
    }
}
