package com.example.dji_rc_pro.ui

import android.annotation.SuppressLint
import android.bluetooth.BluetoothProfile
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dji_rc_pro.manager.ConnectionManager
import com.example.dji_rc_pro.manager.FrequencyManager
import com.example.dji_rc_pro.manager.HeartbeatManager
import com.example.dji_rc_pro.manager.ReconnectManager
import com.example.dji_rc_pro.ui.components.ConnectionStatusPanel
import com.example.dji_rc_pro.ui.components.ErrorDialog
import com.example.dji_rc_pro.viewmodel.MainViewModel

private fun getBleStateString(state: Int): String {
    return when (state) {
        0 -> "未连接"
        1 -> "扫描中"
        2 -> "连接中"
        3 -> "已连接"
        4 -> "断开中"
        else -> "未知($state)"
    }
}

@Composable
fun AdvancedScreen(
    viewModel: MainViewModel,
    onBack: () -> Unit
) {
    val connectionState by viewModel.connectionManager.connectionState.collectAsState()
    val frequencyHz by viewModel.frequencyManager.frequencyHz.collectAsState()
    val heartbeatStatus = viewModel.heartbeatManager.getCombinedStatus()
    val reconnectState by viewModel.reconnectManager.reconnectState.collectAsState()
    val bleConnectionState by viewModel.bleConnectionState.collectAsState()
    val isBleScanning by viewModel.isBleScanning.collectAsState()
    val bleScanResults by viewModel.bleScanResults.collectAsState()
    val showErrorDialog by viewModel.showErrorDialog.collectAsState()
    val showBleDialog by viewModel.showBleDialog.collectAsState()
    val linkDiagnostics by viewModel.linkDiagnostics.collectAsState()

    var showLogsDialog by remember { mutableStateOf(false) }
    var showSettings by remember { mutableStateOf(false) }

    if (showErrorDialog != null) {
        ErrorDialog(
            errorCode = showErrorDialog!!,
            onRetry = { viewModel.retryConnection() },
            onDismiss = { viewModel.dismissErrorDialog() }
        )
    }

    if (showBleDialog) {
        AdvancedBleDialog(
            scanResults = bleScanResults,
            isScanning = isBleScanning,
            connectionState = bleConnectionState,
            onScan = { viewModel.startBleScan() },
            onStopScan = { viewModel.stopBleScan() },
            onConnect = { device -> viewModel.connectBle(bleScanResults.find { it.device == device }!!) },
            onDisconnect = { viewModel.disconnectBle() },
            onDismiss = { viewModel.dismissBleDialog() }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "返回",
                    tint = Color.White
                )
            }
            Text(
                text = "高级设置",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.width(48.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        ConnectionStatusPanel(
            connectionManager = viewModel.connectionManager,
            frequencyManager = viewModel.frequencyManager,
            heartbeatManager = viewModel.heartbeatManager,
            reconnectManager = viewModel.reconnectManager
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "连接信息",
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                ConnectionInfoRow("整体状态", connectionState.overallState.name)
                ConnectionInfoRow("BLE 状态", linkDiagnostics.bleStatusLabel)
                ConnectionInfoRow("UDP 状态", linkDiagnostics.udpState.label)
                ConnectionInfoRow("主通道", linkDiagnostics.currentPrimaryTransport)
                ConnectionInfoRow("频率", "$frequencyHz Hz")
                ConnectionInfoRow("心跳", heartbeatStatus.overallHealth.name)
                ConnectionInfoRow("重连中", if (reconnectState.isReconnecting) "是" else "否")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "双端地址",
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                ConnectionInfoRow("本机 IPv4", linkDiagnostics.localAddresses.ipv4 ?: "-")
                ConnectionInfoRow("本机 IPv6", linkDiagnostics.localAddresses.ipv6 ?: "-")
                ConnectionInfoRow("对端 IPv4", linkDiagnostics.peerAddresses.ipv4 ?: "-")
                ConnectionInfoRow("对端 IPv6", linkDiagnostics.peerAddresses.ipv6 ?: "-")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { showSettings = true },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Text("设置")
            }
            Button(
                onClick = { showLogsDialog = true },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("日志")
            }
            Button(
                onClick = { viewModel.showBleDialog() },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (bleConnectionState == BluetoothProfile.STATE_CONNECTED) Color.Blue else Color.Gray
                )
            ) {
                Text(if (bleConnectionState == BluetoothProfile.STATE_CONNECTED) "BLE 已连接" else "BLE 未连接")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "统计信息",
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.height(8.dp))
                StatsRow("UDP 数据", connectionState.toString())
                StatsRow("BLE 状态码", bleConnectionState.toString())
                StatsRow("心跳丢失", "${heartbeatStatus.udpMissedCount}")
                StatsRow("重连次数", "${reconnectState.attemptCount}")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.showFrequencyDialog() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
        ) {
            Text("频率设置", color = Color.Black)
        }
    }

    if (showSettings) {
        SettingsLogDialog(
            viewModel = viewModel,
            onDismiss = { showSettings = false }
        )
    }

    if (showLogsDialog) {
        LogsDialog(
            viewModel = viewModel,
            onDismiss = { showLogsDialog = false }
        )
    }
}

@Composable
private fun ConnectionInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
        Text(text = value, color = Color.White, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun StatsRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.LightGray, style = MaterialTheme.typography.bodySmall)
        Text(text = value, color = Color.Cyan, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun AdvancedBleDialog(
    scanResults: List<com.example.dji_rc_pro.domain.ble.BleManager.BleDevice>,
    isScanning: Boolean,
    connectionState: Int,
    onScan: () -> Unit,
    onStopScan: () -> Unit,
    onConnect: (android.bluetooth.BluetoothDevice) -> Unit,
    onDisconnect: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("BLE 设备扫描") },
        text = {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (isScanning) {
                        Text("扫描中...", color = Color.Blue)
                        TextButton(onClick = onStopScan) {
                            Text("停止")
                        }
                    } else {
                        Text("未扫描")
                        Button(onClick = onScan) {
                            Text("开始")
                        }
                    }
                }

                if (connectionState == BluetoothProfile.STATE_CONNECTED) {
                    Button(
                        onClick = onDisconnect,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("断开连接")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn {
                    items(scanResults) { device ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = device.device.name ?: "未知设备",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = device.device.address,
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                                if (connectionState == BluetoothProfile.STATE_CONNECTED) {
                                    Text("已连接", color = Color.Green)
                                } else {
                                    Button(
                                        onClick = { onConnect(device.device) },
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Text("连接")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("关闭")
            }
        }
    )
}

@Composable
fun SettingsLogDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val logs by viewModel.logs.collectAsState()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("设置与日志") },
        text = {
            Column {
                Text("最近日志：", style = MaterialTheme.typography.titleSmall)
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    items(logs.takeLast(50).reversed()) { log ->
                        Text(
                            text = log,
                            style = MaterialTheme.typography.bodySmall,
                            color = if (log.contains("ERROR")) Color.Red else Color.Black
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("关闭")
            }
        }
    )
}

@Composable
fun LogsDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val logs by viewModel.logs.collectAsState()
    val reversedLogs = remember(logs) { logs.reversed() }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("应用日志") },
        text = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                items(reversedLogs) { log: String ->
                    Text(
                        text = log,
                        style = MaterialTheme.typography.bodySmall,
                        color = when {
                            log.contains("ERROR") -> Color.Red
                            log.contains("WARN") -> Color.Yellow
                            log.contains("DEBUG") -> Color.Blue
                            else -> Color.Black
                        }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("关闭")
            }
        }
    )
}
