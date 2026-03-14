package com.example.dji_rc_pro

import android.bluetooth.BluetoothProfile
import com.example.dji_rc_pro.domain.ble.Ros2BleProfile
import com.example.dji_rc_pro.domain.diagnostics.BlePairingStatusResolver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BlePairingStatusResolverUnitTest {

    @Test
    fun `paired session should show paired host status`() {
        val status = BlePairingStatusResolver.resolveOverride(
            allowsBle = true,
            session = Ros2BleProfile.GatewaySession(
                hostName = "主机 A",
                paired = true
            ),
            udpReady = false,
            connectionState = BluetoothProfile.STATE_CONNECTED,
            isScanning = false,
            pairedHostName = null
        )

        assertEquals("蓝牙已配对 主机 A", status)
    }

    @Test
    fun `connected but not paired session should wait for short code pairing`() {
        val status = BlePairingStatusResolver.resolveOverride(
            allowsBle = true,
            session = Ros2BleProfile.GatewaySession(),
            udpReady = false,
            connectionState = BluetoothProfile.STATE_CONNECTED,
            isScanning = false,
            pairedHostName = null
        )

        assertEquals("蓝牙已连接，等待短码配对", status)
    }

    @Test
    fun `ble disabled should not provide override`() {
        val status = BlePairingStatusResolver.resolveOverride(
            allowsBle = false,
            session = Ros2BleProfile.GatewaySession(paired = true),
            udpReady = true,
            connectionState = BluetoothProfile.STATE_CONNECTED,
            isScanning = false,
            pairedHostName = "主机 B"
        )

        assertNull(status)
    }

    @Test
    fun `udp ready should show connected host instead of waiting for short code`() {
        val status = BlePairingStatusResolver.resolveOverride(
            allowsBle = true,
            session = Ros2BleProfile.GatewaySession(
                hostName = "xuan-QNLYS",
                udpReady = true
            ),
            udpReady = true,
            connectionState = BluetoothProfile.STATE_CONNECTED,
            isScanning = false,
            pairedHostName = "xuan-QNLYS"
        )

        assertEquals("已连接 xuan-QNLYS", status)
    }
}
