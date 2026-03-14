package com.example.dji_rc_pro

import com.example.dji_rc_pro.domain.ble.Ros2BleProfile
import com.example.dji_rc_pro.domain.ble.Ros2BleReadPolicy
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.UUID

class Ros2BleReadPolicyUnitTest {

    @Test
    fun `pair control read with status 133 and payload should still be processed`() {
        val shouldProcess = Ros2BleReadPolicy.shouldProcessPayload(
            characteristicUuid = Ros2BleProfile.PAIR_CONTROL_UUID,
            status = 133,
            payload = "v=1\nt=o\n".toByteArray()
        )

        assertTrue(shouldProcess)
    }

    @Test
    fun `ros2 read without payload should not be processed when status is 133`() {
        val shouldProcess = Ros2BleReadPolicy.shouldProcessPayload(
            characteristicUuid = Ros2BleProfile.STATUS_UUID,
            status = 133,
            payload = ByteArray(0)
        )

        assertFalse(shouldProcess)
    }

    @Test
    fun `non ros2 characteristic should not bypass read error`() {
        val shouldProcess = Ros2BleReadPolicy.shouldProcessPayload(
            characteristicUuid = UUID.fromString("00002a19-0000-1000-8000-00805f9b34fb"),
            status = 133,
            payload = byteArrayOf(1)
        )

        assertFalse(shouldProcess)
    }
}
