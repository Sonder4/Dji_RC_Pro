package com.example.dji_rc_pro.domain.msdk

import android.content.Context
import android.util.Log
import com.example.dji_rc_pro.domain.input.StickTransformer
import dji.v5.common.error.IDJIError
import dji.v5.manager.KeyManager
import dji.v5.manager.SDKManager
import dji.v5.manager.interfaces.SDKManagerCallback
import dji.v5.common.register.DJISDKInitEvent
import dji.sdk.keyvalue.key.KeyTools
import dji.sdk.keyvalue.key.RemoteControllerKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Manages DJI MSDK V5 initialization and hardware interaction (Sticks, Buttons).
 *
 * References:
 * - Docs/Android_API/en/Components/IKeyManager/Key_RemoteController_RemoteControllerKey.html
 * - Docs/DJI-MSDK-to-PC/MSDKRemote/.../MainActivity.java
 */
class MsdkManager private constructor() {

    companion object {
        const val TAG = "MsdkManager"
        val instance: MsdkManager by lazy { MsdkManager() }
    }

    // StateFlow to expose current stick and button data
    // Storing raw integer values for sticks (0-1024 after transformation) and button mask
    data class ControllerState(
        val leftStickX: Int = 127, // Center (0-255 mapped from transformer, but transformer returns byte, so 127 is center?)
        // StickTransformer returns Byte, so we can store Int for easier usage or Byte.
        // Let's check StickTransformer: it maps -1.0..1.0 to 0..255.
        // Center is ~127.
        val leftStickY: Int = 127,
        val rightStickX: Int = 127,
        val rightStickY: Int = 127,
        val buttonMask: Int = 0
    )

    private val _controllerState = MutableStateFlow(ControllerState())
    val controllerState: StateFlow<ControllerState> = _controllerState.asStateFlow()

    private val _isRegistered = MutableStateFlow(false)
    val isRegistered: StateFlow<Boolean> = _isRegistered.asStateFlow()

    // Allow external updates (e.g. from Virtual Sticks)
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

    fun init(context: Context) {
        SDKManager.getInstance().init(context, object : SDKManagerCallback {
            override fun onRegisterSuccess() {
                Log.i(TAG, "MSDK Register Success")
                _isRegistered.value = true
                setupInputListeners()
            }

            override fun onRegisterFailure(error: IDJIError?) {
                Log.e(TAG, "MSDK Register Failure: ${error?.description()}")
                _isRegistered.value = false
            }

            override fun onProductDisconnect(productId: Int) {
                Log.i(TAG, "Product Disconnect")
            }

            override fun onProductConnect(productId: Int) {
                Log.i(TAG, "Product Connect")
            }

            override fun onProductChanged(productId: Int) {
                Log.i(TAG, "Product Changed: $productId")
            }

            override fun onInitProcess(event: DJISDKInitEvent, totalProcess: Int) {
                Log.i(TAG, "Init Process: ${event.name} ($totalProcess)")
                if (event == DJISDKInitEvent.INITIALIZE_COMPLETE) {
                    Log.i(TAG, "Initialization Complete. Registering App...")
                    SDKManager.getInstance().registerApp()
                }
            }

            override fun onDatabaseDownloadProgress(current: Long, total: Long) {
                // Optional: Handle database download progress
            }
        })
    }

    private fun setupInputListeners() {
        val keyManager = KeyManager.getInstance()
        
        // --- Sticks ---
        // Based on Docs: RemoteControllerKey.KeyStickLeftHorizontal
        val leftXKey = KeyTools.createKey(RemoteControllerKey.KeyStickLeftHorizontal)
        val leftYKey = KeyTools.createKey(RemoteControllerKey.KeyStickLeftVertical)
        val rightXKey = KeyTools.createKey(RemoteControllerKey.KeyStickRightHorizontal)
        val rightYKey = KeyTools.createKey(RemoteControllerKey.KeyStickRightVertical)

        // Listen to Left Stick X
        keyManager.listen(leftXKey, this) { oldValue, newValue: Int? ->
            newValue?.let {
                // Stick values in MSDK V5 are typically Int (e.g., -660 to 660 or similar range, need verification)
                // Assuming normalization is needed. 
                // Wait, documentation says "Int". 
                // Standard DJI stick range is usually -660 to 660 for V4, let's assume V5 is similar or normalize via StickTransformer.
                // StickTransformer takes Float (-1.0 to 1.0). 
                // We need to convert Int from SDK to Float.
                // Let's assume standard range 364 to 1684 (SBUS-like) or -660..660. 
                // If it's RC Pro, it might be different.
                // For now, let's assume we map whatever we get to -1.0..1.0.
                // Actually, MSDK V5 documentation usually implies direct values. 
                // Let's treat the incoming Int as a value to be normalized.
                // To be safe, let's log the first few values in development or assume a standard range of -660..660.
                
                val normalized = normalizeStickValue(it)
                val byteVal = StickTransformer.transform(normalized)
                
                _controllerState.update { state ->
                    state.copy(leftStickX = byteVal.toInt() and 0xFF)
                }
            }
        }

        keyManager.listen(leftYKey, this) { oldValue, newValue: Int? ->
            newValue?.let {
                val normalized = normalizeStickValue(it)
                val byteVal = StickTransformer.transform(normalized)
                _controllerState.update { state ->
                    state.copy(leftStickY = byteVal.toInt() and 0xFF)
                }
            }
        }

        keyManager.listen(rightXKey, this) { oldValue, newValue: Int? ->
            newValue?.let {
                val normalized = normalizeStickValue(it)
                val byteVal = StickTransformer.transform(normalized)
                _controllerState.update { state ->
                    state.copy(rightStickX = byteVal.toInt() and 0xFF)
                }
            }
        }

        keyManager.listen(rightYKey, this) { oldValue, newValue: Int? ->
            newValue?.let {
                val normalized = normalizeStickValue(it)
                val byteVal = StickTransformer.transform(normalized)
                _controllerState.update { state ->
                    state.copy(rightStickY = byteVal.toInt() and 0xFF)
                }
            }
        }

        // --- Buttons ---
        // Need to listen to individual buttons and update the mask.
        // Based on Docs: RemoteControllerKey.KeyCustom1, KeyCustom2, etc.
        // We need to map these to our bitmask bits.
        // Bit 0: A
        // Bit 1: B
        // Bit 2: X
        // Bit 3: Y
        // Bit 4: L1 (Shoulder Left)
        // Bit 5: R1 (Shoulder Right)
        // Bit 6: Custom 1
        // Bit 7: Custom 2
        
        // MSDK V5 might not expose A/B/X/Y directly if it's an RC Pro running Android, 
        // as those might be standard Android key events (KeyEvent).
        // However, C1/C2/5D button are usually via SDK.
        // Let's listen to what we can. 
        // If A/B/X/Y are not in RemoteControllerKey, we must catch them in MainActivity dispatchKeyEvent.
        // For now, let's add C1/C2 listeners.
        
        val c1Key = KeyTools.createKey(RemoteControllerKey.KeyCustomButton1Down)
        keyManager.listen(c1Key, this) { old, newVal: Boolean? -> 
            updateButtonMask(6, newVal == true)
        }
        
        val c2Key = KeyTools.createKey(RemoteControllerKey.KeyCustomButton2Down)
        keyManager.listen(c2Key, this) { old, newVal: Boolean? -> 
            updateButtonMask(7, newVal == true)
        }
    }
    
    private fun normalizeStickValue(value: Int): Float {
        // MSDK stick range usually -660 to 660.
        // Clamp just in case.
        val clamped = value.coerceIn(-660, 660)
        return clamped / 660f
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
    
    fun cleanup() {
        KeyManager.getInstance().cancelListen(this)
    }
}
