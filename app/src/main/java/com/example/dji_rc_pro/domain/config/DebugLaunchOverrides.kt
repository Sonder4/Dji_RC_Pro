package com.example.dji_rc_pro.domain.config

import android.content.Intent
import com.example.dji_rc_pro.MainActivity

data class DebugLaunchOverrides(
    val transportMode: TransportIsolationMode? = null,
    val connectionMode: ConnectionMode? = null,
    val pairCode: String? = null,
    val targetIp: String? = null,
    val targetPort: Int? = null
)

object DebugLaunchOverrideStore {
    @Volatile
    private var current: DebugLaunchOverrides? = null

    fun capture(intent: Intent?) {
        intent ?: run {
            current = null
            return
        }

        val transportMode = intent.getStringExtra(MainActivity.EXTRA_TRANSPORT_MODE)
            ?.let(TransportIsolationMode::fromStorageValue)
        val connectionMode = intent.getStringExtra(MainActivity.EXTRA_CONNECTION_MODE)
            ?.let(ConnectionMode::fromStorageValue)
        val pairCode = intent.getStringExtra(MainActivity.EXTRA_PAIR_CODE)
            ?.takeIf { it.isNotBlank() }
        val targetIp = intent.getStringExtra(MainActivity.EXTRA_TARGET_IP)
            ?.takeIf { it.isNotBlank() }
        val targetPort = intent.getIntExtra(MainActivity.EXTRA_TARGET_PORT, -1)
            .takeIf { it > 0 }

        current = if (
            transportMode == null &&
            connectionMode == null &&
            pairCode == null &&
            targetIp == null &&
            targetPort == null
        ) {
            null
        } else {
            DebugLaunchOverrides(
                transportMode = transportMode,
                connectionMode = connectionMode,
                pairCode = pairCode,
                targetIp = targetIp,
                targetPort = targetPort
            )
        }
    }

    fun current(): DebugLaunchOverrides? = current
}
