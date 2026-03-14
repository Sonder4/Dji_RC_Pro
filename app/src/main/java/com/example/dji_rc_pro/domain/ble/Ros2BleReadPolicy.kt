package com.example.dji_rc_pro.domain.ble

import android.bluetooth.BluetoothGatt
import java.util.UUID

object Ros2BleReadPolicy {
    private const val GATT_ERROR_WITH_PAYLOAD = 133

    private val tolerantCharacteristics: Set<UUID> = setOf(
        Ros2BleProfile.PAIR_CONTROL_UUID,
        Ros2BleProfile.NETWORK_INFO_UUID,
        Ros2BleProfile.STATUS_UUID
    )

    fun shouldProcessPayload(
        characteristicUuid: UUID?,
        status: Int,
        payload: ByteArray?
    ): Boolean {
        if (payload == null || payload.isEmpty()) {
            return status == BluetoothGatt.GATT_SUCCESS
        }

        if (status == BluetoothGatt.GATT_SUCCESS) {
            return true
        }

        return status == GATT_ERROR_WITH_PAYLOAD && characteristicUuid in tolerantCharacteristics
    }
}
