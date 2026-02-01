package com.example.dji_rc_pro.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.dji_rc_pro.ui.components.*
import com.example.dji_rc_pro.viewmodel.MainViewModel
import android.annotation.SuppressLint
import android.bluetooth.BluetoothProfile

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
    val controllerState by viewModel.controllerState.collectAsState()
    val targetIp by viewModel.targetIp.collectAsState()
    val targetPort by viewModel.targetPort.collectAsState()
    val localIpAddress by viewModel.localIpAddress.collectAsState()
    val bleScanResults by viewModel.bleScanResults.collectAsState()
    val bleConnectionState by viewModel.bleConnectionState.collectAsState()
    val isBleScanning by viewModel.isBleScanning.collectAsState()
    val toastMessage by viewModel.toastMessage.collectAsState()
    val showSettings by viewModel.showSettingsDialog.collectAsState()
    val showBleDialog by viewModel.showBleDialog.collectAsState()
    val showErrorDialog by viewModel.showErrorDialog.collectAsState()
    val showFrequencyDialog by viewModel.showFrequencyDialog.collectAsState()
    val showUdpDataDialog by viewModel.showUdpDataDialog.collectAsState()

    var showAdvancedScreen by remember { mutableStateOf(false) }

    showErrorDialog?.let { errorCode ->
        ErrorDialog(
            errorCode = errorCode,
            onRetry = { viewModel.retryConnection() },
            onDismiss = { viewModel.dismissErrorDialog() }
        )
    }

    if (showFrequencyDialog) {
        FrequencyDialog(
            frequencyManager = viewModel.frequencyManager,
            onDismiss = { viewModel.dismissFrequencyDialog() }
        )
    }

    if (showBleDialog) {
        BleScanDialog(
            viewModel = viewModel,
            onDismiss = { viewModel.dismissBleDialog() }
        )
    }

    if (showSettings) {
        SettingsDialog(
            viewModel = viewModel,
            onDismiss = { viewModel.dismissSettingsDialog() }
        )
    }

    if (showUdpDataDialog) {
        UdpDataDialog(
            viewModel = viewModel,
            onDismiss = { viewModel.dismissUdpDataDialog() }
        )
    }

    if (showAdvancedScreen) {
        AdvancedScreen(
            viewModel = viewModel,
            onBack = { showAdvancedScreen = false }
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "RC Mode: USB HID",
                        color = Color.Green,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = "Local IP: $localIpAddress",
                        color = Color.Cyan,
                        style = MaterialTheme.typography.labelSmall
                    )
                    
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Button(
                            onClick = { viewModel.showSettingsDialog() },
                            modifier = Modifier.height(32.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            Text("SETTINGS", style = MaterialTheme.typography.labelSmall)
                        }

                        Button(
                            onClick = { viewModel.showBleDialog() },
                            modifier = Modifier.height(32.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (bleConnectionState == BluetoothProfile.STATE_CONNECTED) Color.Blue else Color.Gray
                            ),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            Text(
                                if (bleConnectionState == BluetoothProfile.STATE_CONNECTED) "BLE: ON" else "BLE: OFF",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }

                        Button(
                            onClick = { viewModel.showFrequencyDialog() },
                            modifier = Modifier.height(32.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            Text(
                                text = "FREQ",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Black
                            )
                        }

                        Button(
                            onClick = { viewModel.toggleVideo() },
                            modifier = Modifier.height(32.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isVideoEnabled) Color.Blue else Color.Gray
                            ),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            Text(
                                if (isVideoEnabled) "VIDEO ON" else "VIDEO OFF",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }

                        Button(
                            onClick = { viewModel.toggleUdp() },
                            modifier = Modifier.height(32.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isUdpRunning) Color.Red else Color.Green
                            ),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            Text(
                                if (isUdpRunning) "STOP UDP" else "START UDP",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }

                        Button(
                            onClick = { viewModel.showUdpDataDialog() },
                            modifier = Modifier.height(32.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800)),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            Text("DATA", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
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

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(start = 16.dp, bottom = 16.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    VirtualStick(
                        xValue = controllerState.leftStickX,
                        yValue = controllerState.leftStickY,
                        size = 120.dp,
                        onStickMoved = { x, y -> viewModel.updateVirtualLeftStick(x, y) }
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1.5f)
                        .fillMaxHeight()
                        .background(Color.DarkGray)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (isVideoEnabled) {
                        Text("Video Feed Placeholder", color = Color.White)
                    }
                    
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                            VirtualButton("L1", 4, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                            VirtualButton("R1", 5, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                        }
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                VirtualButton("Up", 10, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    VirtualButton("Left", 12, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                                    Spacer(modifier = Modifier.size(48.dp))
                                    VirtualButton("Right", 13, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                                }
                                VirtualButton("Down", 11, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                            }
                            
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                VirtualButton("Y", 3, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                    VirtualButton("X", 2, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                                    Spacer(modifier = Modifier.size(36.dp))
                                    VirtualButton("B", 1, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                                }
                                VirtualButton("A", 0, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                            }
                        }
                        
                        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                            VirtualButton("C1", 6, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                            VirtualButton("C2", 7, controllerState.buttonMask, { i, p -> viewModel.updateButtonState(i, p) })
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(end = 16.dp, bottom = 16.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    VirtualStick(
                        xValue = controllerState.rightStickX,
                        yValue = controllerState.rightStickY,
                        size = 120.dp,
                        onStickMoved = { x, y -> viewModel.updateVirtualRightStick(x, y) }
                    )
                }
            }
        }

        toastMessage?.let { message ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 48.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .background(Color(0xCC000000), shape = MaterialTheme.shapes.medium)
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                ) {
                    Text(message, color = Color.White, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
fun BleScanDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val bleScanResults by viewModel.bleScanResults.collectAsState()
    val bleConnectionState by viewModel.bleConnectionState.collectAsState()
    val isBleScanning by viewModel.isBleScanning.collectAsState()

    AlertDialog(
        onDismissRequest = {
            viewModel.stopBleScan()
            onDismiss()
        },
        confirmButton = {},
        dismissButton = {},
        text = {
            Column(
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
                        onDismiss()
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
                                Text(text = bleDevice.device.address, color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
                                if (bleDevice.rssi != 0) {
                                    Text(text = "RSSI: ${bleDevice.rssi}", color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun SettingsDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val targetIp by viewModel.targetIp.collectAsState()
    val targetPort by viewModel.targetPort.collectAsState()
    val localPort by viewModel.localPort.collectAsState()
    val pingResult by viewModel.pingResult.collectAsState()
    val isPinging by viewModel.isPinging.collectAsState()

    var ipInput by remember { mutableStateOf(targetIp) }
    var portInput by remember { mutableStateOf(targetPort.toString()) }
    var localPortInput by remember { mutableStateOf(localPort.toString()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("UDP Settings") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // IP输入框和Ping按钮
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = ipInput,
                        onValueChange = { ipInput = it },
                        label = { Text("Target IP") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    Button(
                        onClick = { viewModel.performPing(ipInput) },
                        enabled = !isPinging && ipInput.isNotBlank(),
                        modifier = Modifier.height(56.dp)
                    ) {
                        if (isPinging) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = MaterialTheme.colorScheme.onPrimary,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text("Ping")
                        }
                    }
                }

                // Ping结果显示
                if (pingResult != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = if (pingResult!!.isSuccess) 
                                Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp)
                        ) {
                            Text(
                                text = if (pingResult!!.isSuccess) "✓ Ping成功" else "✗ Ping失败",
                                style = MaterialTheme.typography.titleSmall,
                                color = if (pingResult!!.isSuccess) 
                                    Color(0xFF2E7D32) else Color(0xFFC62828)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "目标: ${pingResult!!.ip}",
                                style = MaterialTheme.typography.bodySmall
                            )
                            if (pingResult!!.isSuccess) {
                                Text(
                                    text = "数据包: ${pingResult!!.packetsReceived}/${pingResult!!.packetsSent} " +
                                           "(${pingResult!!.packetLossPercent}% 丢失)",
                                    style = MaterialTheme.typography.bodySmall
                                )
                                if (pingResult!!.packetsReceived > 0) {
                                    Text(
                                        text = "延迟: 最短=${pingResult!!.minTimeMs}ms, " +
                                               "最长=${pingResult!!.maxTimeMs}ms, " +
                                               "平均=${pingResult!!.avgTimeMs}ms",
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            } else if (pingResult!!.errorMessage.isNotEmpty()) {
                                Text(
                                    text = "错误: ${pingResult!!.errorMessage}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFFC62828)
                                )
                            }
                        }
                    }
                }

                OutlinedTextField(
                    value = portInput,
                    onValueChange = { portInput = it },
                    label = { Text("Target Port") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = localPortInput,
                    onValueChange = { localPortInput = it },
                    label = { Text("Local Port") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.updateTargetIp(ipInput)
                    viewModel.updateTargetPort(portInput)
                    viewModel.updateLocalPort(localPortInput)
                    viewModel.clearPingResult()
                    onDismiss()
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    viewModel.clearPingResult()
                    onDismiss()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun UdpDataDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val dataLogs by viewModel.udpDataLogManager.dataLogs.collectAsState()
    val listState = androidx.compose.foundation.lazy.rememberLazyListState()

    LaunchedEffect(dataLogs.size) {
        if (dataLogs.isNotEmpty()) {
            listState.animateScrollToItem(dataLogs.size - 1)
        }
    }

    // 使用自定义Dialog以实现更宽的显示区域
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Surface(
            modifier = Modifier
                .width(800.dp)  // 横向扩大宽度
                .height(500.dp) // 增加高度
                .padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // 标题栏
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "UDP Data Log",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "${dataLogs.size} entries",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 图例说明
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        text = "TX = Send (Green)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF4CAF50)
                    )
                    Text(
                        text = "RX = Receive (Yellow)",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFFFC107)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 数据列表
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(dataLogs) { log ->
                        Text(
                            text = log.formatLogLine(),
                            style = MaterialTheme.typography.bodySmall,
                            color = if (log.isSent) Color(0xFF4CAF50) else Color(0xFFFFC107),
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 按钮栏
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { viewModel.clearUdpDataLogs() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Clear")
                    }
                    Button(onClick = onDismiss) {
                        Text("Close")
                    }
                }
            }
        }
    }
}
