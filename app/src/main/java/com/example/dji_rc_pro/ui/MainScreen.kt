package com.example.dji_rc_pro.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dji_rc_pro.ui.components.VideoFeedView
import com.example.dji_rc_pro.ui.components.VirtualStick
import com.example.dji_rc_pro.viewmodel.MainViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import android.annotation.SuppressLint
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.foundation.gestures.detectTapGestures
import android.bluetooth.BluetoothProfile

import androidx.compose.foundation.border

@Composable
fun VirtualButton(
    text: String, 
    bitIndex: Int, 
    buttonMask: Int, 
    onPress: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val isPressed = (buttonMask and (1 shl bitIndex)) != 0
    
    Box(
        modifier = modifier
            .size(45.dp)
            .border(2.dp, Color.Black, MaterialTheme.shapes.small)
            .background(if (isPressed) Color.LightGray else Color.White, shape = MaterialTheme.shapes.small)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        onPress(bitIndex, true)
                        tryAwaitRelease()
                        onPress(bitIndex, false)
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = Color.Black)
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val isUdpRunning by viewModel.isUdpRunning.collectAsState()
    val isVideoEnabled by viewModel.isVideoEnabled.collectAsState()
    val msdkRegistered by viewModel.msdkRegistered.collectAsState()
    
    val controllerState by viewModel.controllerState.collectAsState()
    
    val targetIp by viewModel.targetIp.collectAsState()
    val targetPort by viewModel.targetPort.collectAsState()
    
    // BLE State
    val bleScanResults by viewModel.bleScanResults.collectAsState()
    val bleConnectionState by viewModel.bleConnectionState.collectAsState()
    val isBleScanning by viewModel.isBleScanning.collectAsState()
    val toastMessage by viewModel.toastMessage.collectAsState()
    
    var showSettings by remember { mutableStateOf(false) }
    var showBleDialog by remember { mutableStateOf(false) }

    // Settings Dialog
    if (showSettings) {
        Dialog(onDismissRequest = { showSettings = false }) {
            Card(
                modifier = Modifier.padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("UDP Settings", color = Color.White)
                    
                    OutlinedTextField(
                        value = targetIp,
                        onValueChange = { viewModel.updateTargetIp(it) },
                        label = { Text("Target IP") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )
                    
                    OutlinedTextField(
                        value = targetPort.toString(),
                        onValueChange = { viewModel.updateTargetPort(it) },
                        label = { Text("Target Port") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White
                        )
                    )
                    
                    Button(onClick = { showSettings = false }) {
                        Text("Close")
                    }
                }
            }
        }
    }

    // BLE Dialog
    if (showBleDialog) {
        Dialog(onDismissRequest = { 
            viewModel.stopBleScan()
            showBleDialog = false 
        }) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight(0.8f),
                colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "Bluetooth Devices", 
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (bleConnectionState == BluetoothProfile.STATE_CONNECTED) {
                            Button(
                                onClick = { viewModel.disconnectBle() },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                            ) {
                                Text("Disconnect")
                            }
                        } else {
                            Button(
                                onClick = { 
                                    if (isBleScanning) viewModel.stopBleScan() else viewModel.startBleScan() 
                                }
                            ) {
                                Text(if (isBleScanning) "Stop Scan" else "Start Scan")
                            }
                        }
                        
                        Button(onClick = { 
                            viewModel.stopBleScan()
                            showBleDialog = false 
                        }) {
                            Text("Close")
                        }
                    }

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        items(bleScanResults) { bleDevice ->
                            @SuppressLint("MissingPermission")
                            val name = bleDevice.device.name ?: bleDevice.device.address
                            val address = bleDevice.device.address
                            val isPaired = bleDevice.isPaired
                            
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .clickable { viewModel.connectBle(bleDevice) },
                                colors = CardDefaults.cardColors(
                                    containerColor = if (isPaired) Color(0xFF555555) else Color.Gray
                                )
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = name, 
                                            color = Color.White, 
                                            style = MaterialTheme.typography.bodyLarge,
                                            modifier = Modifier.weight(1f)
                                        )
                                        if (isPaired) {
                                            Text(
                                                text = "PAIRED", 
                                                color = Color.Green, 
                                                style = MaterialTheme.typography.labelSmall,
                                                modifier = Modifier.padding(start = 8.dp)
                                            )
                                        }
                                    }
                                    Text(text = address, color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
                                    if (bleDevice.rssi != 0) {
                                        Text(text = "RSSI: ${bleDevice.rssi}", color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(16.dp)
        ) {
        // Wheel Indicators
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Left Wheel
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Left Wheel: ${controllerState.leftWheel}",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall
                )
                LinearProgressIndicator(
                    progress = { controllerState.leftWheel / 255f },
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Cyan,
                    trackColor = Color.DarkGray
                )
            }
            
            // Right Wheel
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Right Wheel: ${controllerState.rightWheel}",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall
                )
                LinearProgressIndicator(
                    progress = { controllerState.rightWheel / 255f },
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Cyan,
                    trackColor = Color.DarkGray
                )
            }
        }

        // Debug Info
        if (controllerState.debugInfo.isNotEmpty()) {
            Text(
                text = "Debug Axes: ${controllerState.debugInfo}",
                color = Color.Yellow,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Status Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "MSDK: ${if (msdkRegistered) "Registered" else "Not Registered"}",
                color = if (msdkRegistered) Color.Green else Color.Red
            )
            
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { showSettings = true }) {
                    Text("UDP SETTINGS")
                }

                Button(
                    onClick = { showBleDialog = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (bleConnectionState == BluetoothProfile.STATE_CONNECTED) Color.Blue else Color.Gray
                    )
                ) {
                    Text(if (bleConnectionState == BluetoothProfile.STATE_CONNECTED) "BLE: ON" else "BLE: OFF")
                }
                
                // Video Toggle
                Button(
                    onClick = { viewModel.toggleVideo() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isVideoEnabled) Color.Blue else Color.Gray
                    )
                ) {
                    Text(if (isVideoEnabled) "VIDEO ON" else "VIDEO OFF")
                }

                // UDP Toggle
                Button(
                    onClick = { viewModel.toggleUdp() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isUdpRunning) Color.Red else Color.Green
                    )
                ) {
                    Text(if (isUdpRunning) "STOP UDP" else "START UDP")
                }
            }
            
            Text(
                text = "UDP: ${if (isUdpRunning) "Running" else "Stopped"}",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Main Controls
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Stick
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(start = 24.dp, bottom = 24.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                VirtualStick(
                    xValue = controllerState.leftStickX,
                    yValue = controllerState.leftStickY,
                    size = 150.dp,
                    onStickMoved = { x, y -> viewModel.updateVirtualLeftStick(x, y) }
                )
            }

            // Center Area (Video and Buttons)
            Box(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight()
                    .background(Color.DarkGray)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                if (isVideoEnabled) {
                    Text("Video Feed Placeholder", color = Color.White)
                    // In real app, VideoFeedView() here
                }
                
                // Virtual Buttons Overlay
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Shoulder Buttons
                    Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                        VirtualButton("L1", 4, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                        VirtualButton("R1", 5, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                    }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // D-Pad
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            VirtualButton("Up", 10, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                VirtualButton("Left", 12, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                                Spacer(modifier = Modifier.size(60.dp)) // Center of Dpad
                                VirtualButton("Right", 13, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                            }
                            VirtualButton("Down", 11, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                        }
                        
                        // ABXY
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            VirtualButton("Y", 3, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                VirtualButton("X", 2, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                                Spacer(modifier = Modifier.size(45.dp)) 
                                VirtualButton("B", 1, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                            }
                            VirtualButton("A", 0, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                        }
                    }
                    
                    // Custom Buttons
                    Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                        VirtualButton("C1", 6, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                        VirtualButton("C2", 7, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                    }
                }
            }

            // Right Stick
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(end = 24.dp, bottom = 24.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                VirtualStick(
                    xValue = controllerState.rightStickX,
                    yValue = controllerState.rightStickY,
                    size = 150.dp,
                    onStickMoved = { x, y -> viewModel.updateVirtualRightStick(x, y) }
                )
            }
        }
    }

    toastMessage?.let { message ->
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp)
                .background(Color(0xCC000000), shape = MaterialTheme.shapes.medium)
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(message, color = Color.White)
        }
    }
}
}
