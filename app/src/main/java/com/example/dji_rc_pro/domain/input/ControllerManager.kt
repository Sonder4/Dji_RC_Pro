package com.example.dji_rc_pro.domain.input

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Manages the controller state.
 * This state is updated by USB HID or virtual sticks.
 */
class ControllerManager private constructor() {

    companion object {
        val instance: ControllerManager by lazy { ControllerManager() }
    }

    data class ControllerState(
        val leftStickX: Int = 127,
        val leftStickY: Int = 127,
        val rightStickX: Int = 127,
        val rightStickY: Int = 127,
        val buttonMask: Int = 0,
        val leftWheel: Int = 127,
        val rightWheel: Int = 127,
        val androidDebug: String = "",
        val usbDebug: String = ""
    ) {
        val debugInfo: String
            get() = "$androidDebug | $usbDebug"
    }

    private val _controllerState = MutableStateFlow(ControllerState())
    val controllerState: StateFlow<ControllerState> = _controllerState.asStateFlow()

    fun updateAndroidDebug(info: String) {
        _controllerState.update { state -> state.copy(androidDebug = info) }
    }

    fun updateUsbDebug(info: String) {
        _controllerState.update { state -> state.copy(usbDebug = info) }
    }

    fun updateLeftStick(x: Int, y: Int) {
        _controllerState.update { state ->
            state.copy(leftStickX = x, leftStickY = y)
        }
    }

    fun updateRightStick(x: Int, y: Int) {
        _controllerState.update { state ->
            state.copy(rightStickX = x, rightStickY = y)
        }
    }

    fun updateLeftWheel(value: Int) {
        _controllerState.update { state -> state.copy(leftWheel = value) }
    }

    fun updateRightWheel(value: Int) {
        _controllerState.update { state -> state.copy(rightWheel = value) }
    }

    fun updateButtonMask(bitIndex: Int, isPressed: Boolean) {
        _controllerState.update { state ->
            val mask = if (isPressed) {
                state.buttonMask or (1 shl bitIndex)
            } else {
                state.buttonMask and (1 shl bitIndex).inv()
            }
            state.copy(buttonMask = mask)
        }
    }
}
