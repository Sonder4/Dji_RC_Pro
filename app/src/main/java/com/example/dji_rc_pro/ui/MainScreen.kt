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
import com.example.dji_rc_pro.domain.config.ConnectionMode
import com.example.dji_rc_pro.domain.config.TransportIsolationMode
import com.example.dji_rc_pro.domain.discovery.DiscoveryProtocol
import com.example.dji_rc_pro.ui.components.*
import com.example.dji_rc_pro.viewmodel.MainViewModel
import com.example.dji_rc_pro.domain.model.TransportType
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
    val connectionMode by viewModel.connectionMode.collectAsState()
    val transportIsolationMode by viewModel.transportIsolationMode.collectAsState()
    val autoPairStatus by viewModel.autoPairStatus.collectAsState()
    val bleGatewayStatus by viewModel.bleGatewayStatus.collectAsState()
    val bleScanResults by viewModel.bleScanResults.collectAsState()
    val bleConnectionState by viewModel.bleConnectionState.collectAsState()
    val isBleScanning by viewModel.isBleScanning.collectAsState()
    val toastMessage by viewModel.toastMessage.collectAsState()
    val showSettings by viewModel.showSettingsDialog.collectAsState()
    val showBleDialog by viewModel.showBleDialog.collectAsState()
    val showErrorDialog by viewModel.showErrorDialog.collectAsState()
    val showFrequencyDialog by viewModel.showFrequencyDialog.collectAsState()
    val showUdpDataDialog by viewModel.showDataLogDialog.collectAsState()
    val showHostSelectionDialog by viewModel.showHostSelectionDialog.collectAsState()

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

    if (showHostSelectionDialog) {
        HostSelectionDialog(
            viewModel = viewModel,
            onDismiss = { viewModel.dismissHostSelectionDialog() }
        )
    }

    if (showUdpDataDialog) {
        DataLogDialog(
            viewModel = viewModel,
            onDismiss = { viewModel.dismissDataLogDialog() }
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
                        text = "Local Address: $localIpAddress",
                        color = Color.Cyan,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = if (connectionMode == ConnectionMode.AUTO_PAIR) {
                            "Auto Pair: $autoPairStatus"
                        } else {
                            "Manual Target: $targetIp:$targetPort"
                        },
                        color = if (connectionMode == ConnectionMode.AUTO_PAIR) Color.Yellow else Color.LightGray,
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = "Transport Mode: ${transportIsolationMode.displayName}",
                        color = Color(0xFFFFCC80),
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        text = "BLE Bridge: $bleGatewayStatus",
                        color = Color(0xFF80CBC4),
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
                            enabled = transportIsolationMode.allowsBle,
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
                                containerColor = when (transportIsolationMode) {
                                    TransportIsolationMode.BLE_ONLY -> {
                                        if (bleConnectionState == BluetoothProfile.STATE_CONNECTED ||
                                            bleConnectionState == BluetoothProfile.STATE_CONNECTING) Color.Red else Color.Green
                                    }
                                    TransportIsolationMode.UDP_ONLY,
                                    TransportIsolationMode.BLE_UDP -> if (isUdpRunning) Color.Red else Color.Green
                                }
                            ),
                            contentPadding = PaddingValues(horizontal = 8.dp)
                        ) {
                            Text(
                                when (transportIsolationMode) {
                                    TransportIsolationMode.BLE_ONLY -> {
                                        if (bleConnectionState == BluetoothProfile.STATE_CONNECTED ||
                                            bleConnectionState == BluetoothProfile.STATE_CONNECTING) "STOP LINK" else "START LINK"
                                    }
                                    TransportIsolationMode.UDP_ONLY,
                                    TransportIsolationMode.BLE_UDP -> if (isUdpRunning) "STOP UDP" else "START UDP"
                                },
                                style = MaterialTheme.typography.labelSmall
                            )
                        }

                        Button(
                            onClick = { viewModel.showDataLogDialog() },
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
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        if (bleDevice.isRos2Gateway) {
                                            Text(
                                                text = "ROS2",
                                                color = Color(0xFF80CBC4),
                                                style = MaterialTheme.typography.labelSmall,
                                                modifier = Modifier.padding(start = 8.dp)
                                            )
                                        }
                                        if (isPaired) {
                                            Text(
                                                text = "PAIRED",
                                                color = Color.Green,
                                                style = MaterialTheme.typography.labelSmall,
                                                modifier = Modifier.padding(start = 8.dp)
                                            )
                                        }
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
    val udpValidationError by viewModel.udpValidationError.collectAsState()
    val connectionMode by viewModel.connectionMode.collectAsState()
    val transportIsolationMode by viewModel.transportIsolationMode.collectAsState()
    val autoPairStatus by viewModel.autoPairStatus.collectAsState()
    val pairCode by viewModel.pairCode.collectAsState()
    val pairedHostName by viewModel.pairedHostName.collectAsState()
    val pairedHostAddress by viewModel.pairedHostAddress.collectAsState()
    val pairedControlPort by viewModel.pairedControlPort.collectAsState()
    val discoveredHosts by viewModel.discoveredHosts.collectAsState()

    var ipInput by remember { mutableStateOf(targetIp) }
    var portInput by remember { mutableStateOf(targetPort.toString()) }
    var localPortInput by remember { mutableStateOf(localPort.toString()) }
    var pairCodeInput by remember(pairCode) { mutableStateOf(pairCode) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Transport Settings") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = buildString {
                        append(
                            if (connectionMode == ConnectionMode.AUTO_PAIR) {
                                "Connection Mode: Auto Pair"
                            } else {
                                "Connection Mode: Manual"
                            }
                        )
                        append(" | Transport: ${transportIsolationMode.displayName}")
                    },
                    style = MaterialTheme.typography.titleSmall
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(
                        selected = connectionMode == ConnectionMode.AUTO_PAIR,
                        onClick = { viewModel.setConnectionMode(ConnectionMode.AUTO_PAIR) },
                        label = { Text("Auto Pair") }
                    )
                    FilterChip(
                        selected = connectionMode == ConnectionMode.MANUAL,
                        onClick = { viewModel.setConnectionMode(ConnectionMode.MANUAL) },
                        label = { Text("Manual") }
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(
                        selected = transportIsolationMode == TransportIsolationMode.UDP_ONLY,
                        onClick = { viewModel.setTransportIsolationMode(TransportIsolationMode.UDP_ONLY) },
                        label = { Text("UDP Only") }
                    )
                    FilterChip(
                        selected = transportIsolationMode == TransportIsolationMode.BLE_ONLY,
                        onClick = { viewModel.setTransportIsolationMode(TransportIsolationMode.BLE_ONLY) },
                        label = { Text("BLE Only") }
                    )
                    FilterChip(
                        selected = transportIsolationMode == TransportIsolationMode.BLE_UDP,
                        onClick = { viewModel.setTransportIsolationMode(TransportIsolationMode.BLE_UDP) },
                        label = { Text("BLE + UDP") }
                    )
                }

                if (connectionMode == ConnectionMode.AUTO_PAIR) {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text("状态: $autoPairStatus")
                            Text("当前主机: ${pairedHostName ?: "未配对"}")
                            pairedHostAddress?.let { address ->
                                Text("地址: $address:${pairedControlPort ?: DiscoveryProtocol.DEFAULT_CONTROL_PORT}")
                            }
                            Text("已发现主机: ${discoveredHosts.size}")

                            if (transportIsolationMode != TransportIsolationMode.BLE_ONLY) {
                                Text(
                                    text = "受限网络首配：地址填电脑 IPv6/IPv4，Pair Code 填短码",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )

                                OutlinedTextField(
                                    value = ipInput,
                                    onValueChange = {
                                        ipInput = it
                                        viewModel.clearUdpValidationError()
                                    },
                                    label = { Text("Bootstrap Address") },
                                    placeholder = { Text("电脑的 IPv6 或 IPv4") },
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Uri,
                                        imeAction = ImeAction.Next
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            } else {
                                Text(
                                    text = "BLE Only：通过 BLE 广播自动发现电脑，Pair Code 仅用于 BLE 短码配对。",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                            }

                            OutlinedTextField(
                                value = pairCodeInput,
                                onValueChange = { pairCodeInput = it },
                                label = { Text("Pair Code / Short Code") },
                                placeholder = { Text(DiscoveryProtocol.DEFAULT_PAIR_CODE) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth()
                            )

                            if (transportIsolationMode != TransportIsolationMode.BLE_ONLY && discoveredHosts.isNotEmpty()) {
                                discoveredHosts.forEach { host ->
                                    val tag = when {
                                        host.busy -> "忙碌"
                                        host.ready -> "可用"
                                        else -> "未就绪"
                                    }
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text("${host.hostName} [$tag]")
                                            Text(
                                                text = host.label,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = Color.Gray
                                            )
                                        }
                                        TextButton(onClick = { viewModel.selectDiscoveredHost(host) }) {
                                            Text("选择")
                                        }
                                    }
                                }
                            }

                            if (transportIsolationMode != TransportIsolationMode.BLE_ONLY) {
                                OutlinedTextField(
                                    value = localPortInput,
                                    onValueChange = {
                                        localPortInput = it
                                        viewModel.clearUdpValidationError()
                                    },
                                    label = { Text("Local Port") },
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Done
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }

                            TextButton(onClick = { viewModel.forgetPairing() }) {
                                Text("Forget Pairing")
                            }
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = ipInput,
                            onValueChange = {
                                ipInput = it
                                viewModel.clearUdpValidationError()
                            },
                            label = { Text("Target Address") },
                            placeholder = { Text("IPv4 或 IPv6") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Uri,
                                imeAction = ImeAction.Next
                            ),
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick = { viewModel.performPing(ipInput) },
                            enabled = !isPinging && ipInput.isNotBlank()
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

                    OutlinedTextField(
                        value = portInput,
                        onValueChange = {
                            portInput = it
                            viewModel.clearUdpValidationError()
                        },
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
                        onValueChange = {
                            localPortInput = it
                            viewModel.clearUdpValidationError()
                        },
                        label = { Text("Local Port") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (udpValidationError != null) {
                    Text(
                        text = udpValidationError!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                if (pingResult != null) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = if (pingResult!!.isSuccess) {
                                Color(0xFFE8F5E9)
                            } else {
                                Color(0xFFFFEBEE)
                            }
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
                                color = if (pingResult!!.isSuccess) {
                                    Color(0xFF2E7D32)
                                } else {
                                    Color(0xFFC62828)
                                }
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
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val savedLocalPort = if (transportIsolationMode.allowsUdp) {
                        viewModel.updateLocalPort(localPortInput)
                    } else {
                        true
                    }
                    val savedTargetPort = if (connectionMode == ConnectionMode.MANUAL) {
                        viewModel.updateTargetPort(portInput)
                    } else {
                        viewModel.setPairCode(pairCodeInput)
                        if (transportIsolationMode != TransportIsolationMode.BLE_ONLY) {
                            viewModel.updateTargetIp(ipInput)
                        }
                        true
                    }

                    if (connectionMode == ConnectionMode.MANUAL && transportIsolationMode.allowsUdp) {
                        viewModel.updateTargetIp(ipInput)
                    }

                    if (savedLocalPort && savedTargetPort) {
                        viewModel.clearPingResult()
                        viewModel.clearUdpValidationError()
                        onDismiss()
                    }
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    viewModel.clearPingResult()
                    viewModel.clearUdpValidationError()
                    onDismiss()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun HostSelectionDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val candidates by viewModel.hostSelectionCandidates.collectAsState()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Host") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("发现多台可用主机，请选择一台作为默认控制主机")
                candidates.forEach { host ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(host.hostName)
                                Text(host.address, style = MaterialTheme.typography.bodySmall)
                            }
                            Button(onClick = { viewModel.selectDiscoveredHost(host) }) {
                                Text("Pair")
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun DataLogDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val dataLogs by viewModel.dataLogManager.dataLogs.collectAsState()
    val listState = androidx.compose.foundation.lazy.rememberLazyListState()

    // Filter states
    var showUdp by remember { mutableStateOf(true) }
    var showBle by remember { mutableStateOf(true) }
    var showDebug by remember { mutableStateOf(true) }

    val filteredLogs = dataLogs.filter { log ->
        when (log.transport) {
            com.example.dji_rc_pro.domain.model.TransportType.UDP -> showUdp
            com.example.dji_rc_pro.domain.model.TransportType.BLE -> showBle
        } && (showDebug || log.isSent != null)
    }

    LaunchedEffect(filteredLogs.size) {
        if (filteredLogs.isNotEmpty()) {
            listState.animateScrollToItem(filteredLogs.size - 1)
        }
    }

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
                .width(900.dp)
                .height(600.dp)
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
                // Title bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Data Log (UDP & BLE)",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        text = "${filteredLogs.size}/${dataLogs.size} entries",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Filter controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = showUdp,
                            onCheckedChange = { showUdp = it }
                        )
                        Text("UDP", color = Color(0xFF2196F3))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = showBle,
                            onCheckedChange = { showBle = it }
                        )
                        Text("BLE", color = Color(0xFF9C27B0))
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = showDebug,
                            onCheckedChange = { showDebug = it }
                        )
                        Text("Debug Logs")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Legend
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "TX = Send",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF4CAF50)
                    )
                    Text(
                        text = "RX = Receive",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFFFC107)
                    )
                    Text(
                        text = "DEBUG = Debug",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Data list
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(filteredLogs) { log ->
                        val textColor = when {
                            log.isSent == true -> Color(0xFF4CAF50)
                            log.isSent == false -> Color(0xFFFFC107)
                            else -> Color.Gray
                        }
                        val bgColor = when (log.transport) {
                            com.example.dji_rc_pro.domain.model.TransportType.UDP ->
                                Color(0xFFE3F2FD)
                            com.example.dji_rc_pro.domain.model.TransportType.BLE ->
                                Color(0xFFF3E5F5)
                        }

                        Text(
                            text = log.formatLogLine(),
                            style = MaterialTheme.typography.bodySmall,
                            color = textColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(bgColor.copy(alpha = 0.3f))
                                .padding(vertical = 2.dp, horizontal = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Button bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { viewModel.clearDataLogs() },
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

@Deprecated("Use DataLogDialog instead")
@Composable
fun UdpDataDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    DataLogDialog(viewModel = viewModel, onDismiss = onDismiss)
}
