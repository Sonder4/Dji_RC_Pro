package com.example.dji_rc_pro.ui

import android.annotation.SuppressLint
import android.bluetooth.BluetoothProfile
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.dji_rc_pro.domain.config.ConnectionMode
import com.example.dji_rc_pro.domain.config.TransportIsolationMode
import com.example.dji_rc_pro.domain.diagnostics.PingSlot
import com.example.dji_rc_pro.domain.discovery.DiscoveryProtocol
import com.example.dji_rc_pro.domain.model.TransportType
import com.example.dji_rc_pro.ui.components.ErrorDialog
import com.example.dji_rc_pro.ui.components.FrequencyDialog
import com.example.dji_rc_pro.ui.components.VirtualStick
import com.example.dji_rc_pro.util.PingUtil
import com.example.dji_rc_pro.viewmodel.MainViewModel

private val DeckPanelShape = RoundedCornerShape(28.dp)
private val DeckButtonShape = RoundedCornerShape(22.dp)
private val DeckChipShape = RoundedCornerShape(18.dp)

private enum class CockpitDrawerPanel(val label: String) {
    CONNECTION("连接"),
    BLE("BLE"),
    LOGS("日志")
}

private fun transportModeLabel(mode: TransportIsolationMode): String = when (mode) {
    TransportIsolationMode.UDP_ONLY -> "UDP"
    TransportIsolationMode.BLE_ONLY -> "BLE"
    TransportIsolationMode.BLE_UDP -> "BLE + UDP"
}

private fun transportModeSupportLabel(mode: TransportIsolationMode): String = when (mode) {
    TransportIsolationMode.UDP_ONLY -> "仅 UDP"
    TransportIsolationMode.BLE_ONLY -> "仅 BLE"
    TransportIsolationMode.BLE_UDP -> "自动回退"
}

private fun transportModeDescription(mode: TransportIsolationMode): String = when (mode) {
    TransportIsolationMode.UDP_ONLY -> "关闭 BLE，仅通过 UDP 发现与控制。"
    TransportIsolationMode.BLE_ONLY -> "仅通过 BLE 建链与控制，禁用手动 UDP 目标。"
    TransportIsolationMode.BLE_UDP -> "先通过 BLE 对齐地址，再切换 UDP 为主通道，失败时自动回退到 BLE。"
}

private fun connectionModeLabel(mode: ConnectionMode): String = when (mode) {
    ConnectionMode.AUTO_PAIR -> "自动配对"
    ConnectionMode.MANUAL -> "手动输入"
}

private fun pingResultSummary(result: PingUtil.PingResult?): String {
    if (result == null) {
        return "未测试"
    }
    if (!result.isSuccess) {
        return result.errorMessage.ifBlank { "失败" }
    }
    return if (result.packetsReceived > 0) {
        "成功，平均 ${result.avgTimeMs}ms"
    } else {
        "成功，0 响应"
    }
}

@Composable
fun VirtualButton(
    text: String,
    bitIndex: Int,
    buttonMask: Int,
    onPress: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier,
    accentColor: Color = MaterialTheme.colorScheme.primary
) {
    val isPressed = (buttonMask and (1 shl bitIndex)) != 0
    val containerColor = if (isPressed) accentColor else MaterialTheme.colorScheme.surface
    val contentColor = if (isPressed) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(containerColor)
            .border(
                width = 2.dp,
                color = if (isPressed) accentColor else MaterialTheme.colorScheme.outline,
                shape = CircleShape
            )
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
        Text(
            text = text,
            color = contentColor,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val isUdpRunning by viewModel.isUdpRunning.collectAsState()
    val isVideoEnabled by viewModel.isVideoEnabled.collectAsState()
    val controllerState by viewModel.controllerState.collectAsState()
    val bleConnectionState by viewModel.bleConnectionState.collectAsState()
    val connectionMode by viewModel.connectionMode.collectAsState()
    val transportIsolationMode by viewModel.transportIsolationMode.collectAsState()
    val toastMessage by viewModel.toastMessage.collectAsState()
    val showErrorDialog by viewModel.showErrorDialog.collectAsState()
    val showFrequencyDialog by viewModel.showFrequencyDialog.collectAsState()
    val showHostSelectionDialog by viewModel.showHostSelectionDialog.collectAsState()
    val linkDiagnostics by viewModel.linkDiagnostics.collectAsState()
    var activeDrawerPanel by remember { mutableStateOf<CockpitDrawerPanel?>(null) }

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

    if (showHostSelectionDialog) {
        HostSelectionDialog(
            viewModel = viewModel,
            onDismiss = { viewModel.dismissHostSelectionDialog() }
        )
    }

    val stickSize = if (activeDrawerPanel == null) 132.dp else 120.dp
    val modeButtonValue = connectionModeLabel(connectionMode)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CockpitStatusStrip(
                modeValue = transportModeLabel(transportIsolationMode),
                linkValue = linkDiagnostics.bleStatusLabel,
                targetValue = "${linkDiagnostics.udpState.label} / ${linkDiagnostics.currentPrimaryTransport}",
                modeButtonValue = modeButtonValue,
                isModeActive = activeDrawerPanel == CockpitDrawerPanel.CONNECTION,
                isSettingsActive = activeDrawerPanel != null,
                onModeClick = { activeDrawerPanel = CockpitDrawerPanel.CONNECTION },
                onSettingsClick = { activeDrawerPanel = CockpitDrawerPanel.CONNECTION }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StickPanel(
                    title = "左摇杆",
                    valueLabel = "${controllerState.leftStickX}, ${controllerState.leftStickY}",
                    titleAlignment = Alignment.Start,
                    stickAlignment = Alignment.BottomStart,
                    modifier = Modifier.weight(0.78f)
                ) {
                    VirtualStick(
                        xValue = controllerState.leftStickX,
                        yValue = controllerState.leftStickY,
                        size = stickSize,
                        onStickMoved = { x, y -> viewModel.updateVirtualLeftStick(x, y) }
                    )
                }

                ControllerDeck(
                    controllerState = controllerState.buttonMask,
                    isVideoEnabled = isVideoEnabled,
                    transportMode = transportIsolationMode,
                    isUdpRunning = isUdpRunning,
                    bleConnectionState = bleConnectionState,
                    leftWheel = controllerState.leftWheel,
                    rightWheel = controllerState.rightWheel,
                    onButtonStateChanged = viewModel::updateButtonState,
                    modifier = Modifier.weight(1.34f)
                )

                StickPanel(
                    title = "右摇杆",
                    valueLabel = "${controllerState.rightStickX}, ${controllerState.rightStickY}",
                    titleAlignment = Alignment.End,
                    stickAlignment = Alignment.BottomEnd,
                    modifier = Modifier.weight(0.78f)
                ) {
                    VirtualStick(
                        xValue = controllerState.rightStickX,
                        yValue = controllerState.rightStickY,
                        size = stickSize,
                        onStickMoved = { x, y -> viewModel.updateVirtualRightStick(x, y) }
                    )
                }
            }
        }

        activeDrawerPanel?.let { panel ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.scrim.copy(alpha = 0.16f))
                    .clickable { activeDrawerPanel = null }
            )
            CockpitDrawer(
                activePanel = panel,
                onPanelSelected = { activeDrawerPanel = it },
                onClose = { activeDrawerPanel = null },
                viewModel = viewModel,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight()
                    .padding(end = 12.dp, top = 14.dp, bottom = 14.dp)
            )
        }

        toastMessage?.let { message ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 28.dp)
            ) {
                Surface(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    shape = DeckChipShape,
                    color = MaterialTheme.colorScheme.onSurface,
                    tonalElevation = 6.dp
                ) {
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CockpitStatusStrip(
    modeValue: String,
    linkValue: String,
    targetValue: String,
    modeButtonValue: String,
    isModeActive: Boolean,
    isSettingsActive: Boolean,
    onModeClick: () -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = DeckPanelShape,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CockpitStatusChip(
                label = "模式",
                value = modeValue,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.weight(0.92f)
            )
            CockpitStatusChip(
                label = "BLE",
                value = linkValue,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.weight(0.96f)
            )
            CockpitStatusChip(
                label = "UDP",
                value = targetValue,
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier.weight(1.12f)
            )
            CockpitToolButton(
                title = "模式",
                value = modeButtonValue,
                active = isModeActive,
                accentColor = MaterialTheme.colorScheme.secondary,
                onClick = onModeClick
            )
            CockpitToolButton(
                title = "设置",
                value = "抽屉",
                active = isSettingsActive,
                accentColor = MaterialTheme.colorScheme.primary,
                onClick = onSettingsClick
            )
        }
    }
}

@Composable
private fun CockpitStatusChip(
    label: String,
    value: String,
    containerColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = DeckButtonShape,
        color = containerColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun CockpitToolButton(
    title: String,
    value: String,
    active: Boolean,
    accentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = if (active) accentColor else MaterialTheme.colorScheme.surfaceVariant
    val contentColor = if (active) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Surface(
        modifier = modifier
            .width(96.dp)
            .height(66.dp)
            .clip(DeckButtonShape)
            .clickable(onClick = onClick),
        color = containerColor,
        shape = DeckButtonShape,
        border = BorderStroke(
            width = 1.dp,
            color = if (active) accentColor else MaterialTheme.colorScheme.outline
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = contentColor
            )
            Text(
                text = value,
                style = MaterialTheme.typography.labelSmall,
                color = contentColor.copy(alpha = 0.82f)
            )
        }
    }
}

@Composable
private fun InfoCard(
    title: String,
    value: String,
    supporting: String,
    badgeText: String,
    badgeColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.heightIn(min = 180.dp, max = 210.dp),
        shape = DeckPanelShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                StatusBadge(text = badgeText, containerColor = badgeColor)
            }
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = supporting,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun StatusBadge(
    text: String,
    containerColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = DeckChipShape,
        color = containerColor
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
        )
    }
}

@Composable
private fun TransportModeSelector(
    selectedMode: TransportIsolationMode,
    onModeSelected: (TransportIsolationMode) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = DeckPanelShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "传输模式",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                TransportIsolationMode.entries.forEach { mode ->
                    SegmentButton(
                        label = when (mode) {
                            TransportIsolationMode.UDP_ONLY -> "UDP"
                            TransportIsolationMode.BLE_UDP -> "BLE + UDP"
                            TransportIsolationMode.BLE_ONLY -> "BLE"
                        },
                        supporting = when (mode) {
                            TransportIsolationMode.UDP_ONLY -> "仅 UDP"
                            TransportIsolationMode.BLE_UDP -> "自动回退"
                            TransportIsolationMode.BLE_ONLY -> "仅 BLE"
                        },
                        selected = selectedMode == mode,
                        onClick = { onModeSelected(mode) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun SegmentButton(
    label: String,
    supporting: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val containerColor = when {
        !enabled -> MaterialTheme.colorScheme.surfaceVariant
        selected -> MaterialTheme.colorScheme.primary
        else -> MaterialTheme.colorScheme.primaryContainer
    }
    val contentColor = if (selected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Surface(
        modifier = modifier
            .height(72.dp)
            .clip(DeckButtonShape)
            .clickable(enabled = enabled, onClick = onClick),
        color = containerColor,
        shape = DeckButtonShape,
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) containerColor else MaterialTheme.colorScheme.outline
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleSmall,
                color = contentColor,
                textAlign = TextAlign.Center
            )
            Text(
                text = supporting,
                style = MaterialTheme.typography.labelSmall,
                color = contentColor.copy(alpha = 0.8f),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ActionButton(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    active: Boolean = false,
    enabled: Boolean = true,
    accentColor: Color = MaterialTheme.colorScheme.primary
) {
    val containerColor = when {
        !enabled -> MaterialTheme.colorScheme.surfaceVariant
        active -> accentColor
        else -> MaterialTheme.colorScheme.surface
    }
    val contentColor = if (active) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Surface(
            modifier = modifier
            .height(80.dp)
            .clip(DeckButtonShape)
            .clickable(enabled = enabled, onClick = onClick),
        shape = DeckButtonShape,
        color = containerColor,
        border = BorderStroke(
            width = 1.dp,
            color = if (active) accentColor else MaterialTheme.colorScheme.outline
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = contentColor
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = contentColor.copy(alpha = 0.82f)
            )
        }
    }
}

@Composable
private fun CockpitDrawer(
    activePanel: CockpitDrawerPanel,
    onPanelSelected: (CockpitDrawerPanel) -> Unit,
    onClose: () -> Unit,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.widthIn(min = 388.dp, max = 456.dp),
        shape = DeckPanelShape,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 10.dp,
        shadowElevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CockpitDrawerPanel.entries.forEach { panel ->
                    DrawerTabButton(
                        label = panel.label,
                        selected = activePanel == panel,
                        onClick = { onPanelSelected(panel) },
                        modifier = Modifier.weight(1f)
                    )
                }
                TextButton(onClick = onClose) {
                    Text("关闭")
                }
            }

            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.45f))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                when (activePanel) {
                    CockpitDrawerPanel.CONNECTION -> SettingsPanelContent(
                        viewModel = viewModel,
                        onDismiss = onClose,
                        modifier = Modifier.fillMaxSize()
                    )
                    CockpitDrawerPanel.BLE -> BleDevicesPanelContent(
                        viewModel = viewModel,
                        onDismiss = onClose,
                        modifier = Modifier.fillMaxSize()
                    )
                    CockpitDrawerPanel.LOGS -> DataLogPanelContent(
                        viewModel = viewModel,
                        onDismiss = onClose,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
private fun DrawerTabButton(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.primaryContainer
    }
    val contentColor = if (selected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Surface(
        modifier = modifier
            .height(48.dp)
            .clip(DeckButtonShape)
            .clickable(onClick = onClick),
        shape = DeckButtonShape,
        color = containerColor,
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) containerColor else MaterialTheme.colorScheme.outline
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = contentColor
            )
        }
    }
}

@Composable
private fun WheelTelemetry(leftWheel: Int, rightWheel: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = DeckPanelShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WheelMeter(
                title = "左拨轮",
                value = leftWheel,
                modifier = Modifier.weight(1f)
            )
            WheelMeter(
                title = "右拨轮",
                value = rightWheel,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun WheelMeter(title: String, value: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        LinearProgressIndicator(
            progress = { value / 255f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(CircleShape),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.primaryContainer
        )
    }
}

@Composable
private fun StickPanel(
    title: String,
    valueLabel: String,
    titleAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    stickAlignment: Alignment = Alignment.BottomCenter,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxHeight(),
        shape = DeckPanelShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = titleAlignment
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = valueLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = stickAlignment
            ) {
                content()
            }
        }
    }
}

@Composable
private fun ControllerDeck(
    controllerState: Int,
    isVideoEnabled: Boolean,
    transportMode: TransportIsolationMode,
    isUdpRunning: Boolean,
    bleConnectionState: Int,
    leftWheel: Int,
    rightWheel: Int,
    onButtonStateChanged: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxHeight(),
        shape = DeckPanelShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                VirtualButton(
                    text = "L1",
                    bitIndex = 4,
                    buttonMask = controllerState,
                    onPress = onButtonStateChanged
                )
                VirtualButton(
                    text = "R1",
                    bitIndex = 5,
                    buttonMask = controllerState,
                    onPress = onButtonStateChanged,
                    accentColor = MaterialTheme.colorScheme.secondary
                )
            }

            CenterBridgePanel(
                modifier = Modifier.fillMaxWidth(),
                isVideoEnabled = isVideoEnabled,
                transportMode = transportMode,
                isUdpRunning = isUdpRunning,
                bleConnectionState = bleConnectionState,
                leftWheel = leftWheel,
                rightWheel = rightWheel
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DirectionCluster(
                    buttonMask = controllerState,
                    onPress = onButtonStateChanged
                )
                ActionCluster(
                    buttonMask = controllerState,
                    onPress = onButtonStateChanged
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                VirtualButton(
                    text = "C1",
                    bitIndex = 6,
                    buttonMask = controllerState,
                    onPress = onButtonStateChanged
                )
                VirtualButton(
                    text = "C2",
                    bitIndex = 7,
                    buttonMask = controllerState,
                    onPress = onButtonStateChanged,
                    accentColor = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
private fun DirectionCluster(buttonMask: Int, onPress: (Int, Boolean) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        VirtualButton(text = "上", bitIndex = 10, buttonMask = buttonMask, onPress = onPress)
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            VirtualButton(text = "LT", bitIndex = 12, buttonMask = buttonMask, onPress = onPress)
            Spacer(modifier = Modifier.size(38.dp))
            VirtualButton(text = "RT", bitIndex = 13, buttonMask = buttonMask, onPress = onPress)
        }
        VirtualButton(text = "下", bitIndex = 11, buttonMask = buttonMask, onPress = onPress)
    }
}

@Composable
private fun ActionCluster(buttonMask: Int, onPress: (Int, Boolean) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        VirtualButton(
            text = "Y",
            bitIndex = 3,
            buttonMask = buttonMask,
            onPress = onPress,
            accentColor = MaterialTheme.colorScheme.secondary
        )
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            VirtualButton(
                text = "X",
                bitIndex = 2,
                buttonMask = buttonMask,
                onPress = onPress,
                accentColor = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.size(38.dp))
            VirtualButton(
                text = "B",
                bitIndex = 1,
                buttonMask = buttonMask,
                onPress = onPress,
                accentColor = MaterialTheme.colorScheme.secondary
            )
        }
        VirtualButton(
            text = "A",
            bitIndex = 0,
            buttonMask = buttonMask,
            onPress = onPress,
            accentColor = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun CenterBridgePanel(
    modifier: Modifier = Modifier,
    isVideoEnabled: Boolean,
    transportMode: TransportIsolationMode,
    isUdpRunning: Boolean,
    bleConnectionState: Int,
    leftWheel: Int,
    rightWheel: Int
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = when (transportMode) {
                        TransportIsolationMode.UDP_ONLY -> if (isUdpRunning) "UDP 主通道" else "UDP 待机"
                        TransportIsolationMode.BLE_ONLY -> if (bleConnectionState == BluetoothProfile.STATE_CONNECTED) "BLE 主通道" else "BLE 等待中"
                        TransportIsolationMode.BLE_UDP -> if (isUdpRunning) "UDP 主通道" else "BLE 引导中"
                    },
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "拨轮 $leftWheel / $rightWheel",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = if (isVideoEnabled) {
                    MaterialTheme.colorScheme.primaryContainer
                } else {
                    MaterialTheme.colorScheme.surface
                }
            ) {
                Text(
                    text = if (isVideoEnabled) "视频" else "核心",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun BleScanDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .heightIn(max = 760.dp)
                .padding(16.dp),
            shape = DeckPanelShape,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            BleDevicesPanelContent(
                viewModel = viewModel,
                onDismiss = onDismiss,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun BleDevicesPanelContent(
    viewModel: MainViewModel,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bleScanResults by viewModel.bleScanResults.collectAsState()
    val bleConnectionState by viewModel.bleConnectionState.collectAsState()
    val isBleScanning by viewModel.isBleScanning.collectAsState()
    val transportMode by viewModel.transportIsolationMode.collectAsState()
    val bleGatewayStatus by viewModel.bleGatewayStatus.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
            ) {
                Text(
                    text = "BLE 设备桥",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = bleGatewayStatus,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            StatusBadge(
                text = when {
                    !transportMode.allowsBle -> "已禁用"
                    bleConnectionState == BluetoothProfile.STATE_CONNECTED -> "已连接"
                    isBleScanning -> "扫描中"
                    else -> "就绪"
                },
                containerColor = when {
                    !transportMode.allowsBle -> MaterialTheme.colorScheme.surfaceVariant
                    bleConnectionState == BluetoothProfile.STATE_CONNECTED -> MaterialTheme.colorScheme.tertiaryContainer
                    isBleScanning -> MaterialTheme.colorScheme.secondaryContainer
                    else -> MaterialTheme.colorScheme.primaryContainer
                }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ActionButton(
                title = if (isBleScanning) "停止扫描" else "开始扫描",
                subtitle = "搜索 ROS2 网关",
                active = isBleScanning,
                enabled = transportMode.allowsBle,
                accentColor = MaterialTheme.colorScheme.secondary,
                onClick = {
                    if (isBleScanning) viewModel.stopBleScan() else viewModel.startBleScan()
                },
                modifier = Modifier.weight(1f)
            )
            ActionButton(
                title = "断开连接",
                subtitle = "断开当前 BLE 会话",
                active = bleConnectionState == BluetoothProfile.STATE_CONNECTED,
                enabled = bleConnectionState == BluetoothProfile.STATE_CONNECTED,
                accentColor = MaterialTheme.colorScheme.error,
                onClick = { viewModel.disconnectBle() },
                modifier = Modifier.weight(1f)
            )
            ActionButton(
                title = "关闭",
                subtitle = "返回控制主界面",
                onClick = onDismiss,
                modifier = Modifier.weight(1f)
            )
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))

        if (!transportMode.allowsBle) {
            EmptyStateCard(
                title = "当前为 UDP 模式，BLE 已禁用",
                supporting = "切换到 BLE 或 BLE + UDP 后才能搜索并连接。"
            )
        } else if (bleScanResults.isEmpty()) {
            EmptyStateCard(
                title = if (isBleScanning) "正在搜索 ROS2 BLE 网关" else "尚未发现 BLE 设备",
                supporting = if (isBleScanning) {
                    "扫描已启动，附近可用的 ROS2 网关会显示在这里。"
                } else {
                    "点击开始扫描，发现附近的 RC26-ROS2 设备。"
                }
            )
        } else {
            val sortedDevices = bleScanResults.sortedWith(
                compareByDescending<com.example.dji_rc_pro.domain.ble.BleManager.BleDevice> { it.isRos2Gateway }
                    .thenByDescending { it.rssi }
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false)
            ) {
                items(
                    items = sortedDevices,
                    key = { it.device.address }
                ) { bleDevice ->
                    BleDeviceCard(
                        bleDevice = bleDevice,
                        isConnected = bleConnectionState == BluetoothProfile.STATE_CONNECTED,
                        onConnect = { viewModel.connectBle(bleDevice) }
                    )
                }
            }
        }
    }
}

@Composable
private fun EmptyStateCard(title: String, supporting: String) {
    Card(
        shape = DeckPanelShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = supporting,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun BleDeviceCard(
    bleDevice: com.example.dji_rc_pro.domain.ble.BleManager.BleDevice,
    isConnected: Boolean,
    onConnect: () -> Unit
) {
    @SuppressLint("MissingPermission")
    val deviceName = bleDevice.device.name ?: bleDevice.device.address
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onConnect),
        shape = DeckButtonShape,
        colors = CardDefaults.cardColors(
            containerColor = if (bleDevice.isRos2Gateway) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = deviceName,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    if (bleDevice.isRos2Gateway) {
                        StatusBadge(
                            text = "ROS2",
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    }
                    if (bleDevice.isPaired) {
                        StatusBadge(
                            text = "已配对",
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer
                        )
                    }
                }
            }
            Text(
                text = bleDevice.device.address,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "RSSI ${bleDevice.rssi}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = if (isConnected) "点击切换到其他设备" else "点击连接",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun SettingsPanelContent(
    viewModel: MainViewModel,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val targetIpv4 by viewModel.targetIpv4.collectAsState()
    val targetIpv6 by viewModel.targetIpv6.collectAsState()
    val targetPort by viewModel.targetPort.collectAsState()
    val localPort by viewModel.localPort.collectAsState()
    val pingResults by viewModel.pingResults.collectAsState()
    val pingInFlight by viewModel.pingInFlight.collectAsState()
    val udpValidationError by viewModel.udpValidationError.collectAsState()
    val connectionMode by viewModel.connectionMode.collectAsState()
    val transportIsolationMode by viewModel.transportIsolationMode.collectAsState()
    val autoPairStatus by viewModel.autoPairStatus.collectAsState()
    val pairCode by viewModel.pairCode.collectAsState()
    val pairedHostName by viewModel.pairedHostName.collectAsState()
    val pairedHostAddress by viewModel.pairedHostAddress.collectAsState()
    val pairedControlPort by viewModel.pairedControlPort.collectAsState()
    val discoveredHosts by viewModel.discoveredHosts.collectAsState()
    val isVideoEnabled by viewModel.isVideoEnabled.collectAsState()
    val frequencyHz by viewModel.frequencyManager.frequencyHz.collectAsState()
    val linkDiagnostics by viewModel.linkDiagnostics.collectAsState()

    var ipv4Input by remember(targetIpv4) { mutableStateOf(targetIpv4.orEmpty()) }
    var ipv6Input by remember(targetIpv6) { mutableStateOf(targetIpv6.orEmpty()) }
    var portInput by remember(targetPort) { mutableStateOf(targetPort.toString()) }
    var localPortInput by remember(localPort) { mutableStateOf(localPort.toString()) }
    var pairCodeInput by remember(pairCode) { mutableStateOf(pairCode) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "连接设置",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "主界面保持简洁，详细链路诊断集中在这里。",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            StatusBadge(
                text = transportModeLabel(transportIsolationMode),
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        }

        SectionCard(title = "连接模式") {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SegmentButton(
                    label = "自动配对",
                    supporting = "推荐",
                    selected = connectionMode == ConnectionMode.AUTO_PAIR,
                    onClick = { viewModel.setConnectionMode(ConnectionMode.AUTO_PAIR) },
                    modifier = Modifier.weight(1f)
                )
                SegmentButton(
                    label = "手动输入",
                    supporting = "直接指定",
                    selected = connectionMode == ConnectionMode.MANUAL,
                    enabled = transportIsolationMode != TransportIsolationMode.BLE_ONLY,
                    onClick = { viewModel.setConnectionMode(ConnectionMode.MANUAL) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        SectionCard(title = "传输模式") {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                TransportIsolationMode.entries.forEach { mode ->
                    SegmentButton(
                        label = transportModeLabel(mode),
                        supporting = transportModeSupportLabel(mode),
                        selected = transportIsolationMode == mode,
                        onClick = { viewModel.setTransportIsolationMode(mode) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = transportModeDescription(transportIsolationMode),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        SectionCard(title = "运行控制") {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ActionButton(
                    title = "频率",
                    subtitle = "$frequencyHz Hz 发送",
                    active = false,
                    accentColor = MaterialTheme.colorScheme.secondary,
                    onClick = { viewModel.showFrequencyDialog() },
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    title = if (isVideoEnabled) "视频已开启" else "视频已关闭",
                    subtitle = "中部预览面板",
                    active = isVideoEnabled,
                    accentColor = MaterialTheme.colorScheme.primary,
                    onClick = { viewModel.toggleVideo() },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        SectionCard(title = "链路状态") {
            DiagnosticStatusLine("BLE", linkDiagnostics.bleStatusLabel)
            DiagnosticStatusLine("UDP", linkDiagnostics.udpState.label)
            DiagnosticStatusLine("主通道", linkDiagnostics.currentPrimaryTransport)
            DiagnosticStatusLine("地址来源", linkDiagnostics.peerAddressSource.label)
            DiagnosticStatusLine("配对状态", autoPairStatus)
            DiagnosticStatusLine("当前主机", pairedHostName ?: "未配对")
            DiagnosticStatusLine("对端 IPv4", linkDiagnostics.peerAddresses.ipv4 ?: "-")
            DiagnosticStatusLine("对端 IPv6", linkDiagnostics.peerAddresses.ipv6 ?: "-")
            pairedHostAddress?.let { address ->
                DiagnosticStatusLine(
                    "已配对端口",
                    "$address:${pairedControlPort ?: DiscoveryProtocol.DEFAULT_CONTROL_PORT}"
                )
            }
        }

        SectionCard(title = "本机地址") {
            DeckTextField(
                label = "本机 IPv4",
                supporting = "当前 RC Pro 局域网 IPv4。",
                value = linkDiagnostics.localAddresses.ipv4.orEmpty(),
                onValueChange = {},
                placeholderText = "未获取",
                readOnly = true
            )
            Spacer(modifier = Modifier.height(12.dp))
            DeckTextField(
                label = "本机 IPv6",
                supporting = "当前 RC Pro 局域网 IPv6。",
                value = linkDiagnostics.localAddresses.ipv6.orEmpty(),
                onValueChange = {},
                placeholderText = "未获取",
                readOnly = true
            )
        }

        if (connectionMode == ConnectionMode.AUTO_PAIR) {
            SectionCard(title = "配对参数") {
                if (transportIsolationMode.allowsUdp) {
                    DeckTextField(
                        label = "引导 IPv4",
                        supporting = "自动发现失败时，可手动填写主机 IPv4。",
                        value = ipv4Input,
                        onValueChange = {
                            ipv4Input = it
                            viewModel.clearUdpValidationError()
                        },
                        placeholderText = "例如 192.168.1.10",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Uri,
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    DeckTextField(
                        label = "引导 IPv6",
                        supporting = "BLE 已连接时也可通过 BLE 自动同步主机 IPv6。",
                        value = ipv6Input,
                        onValueChange = {
                            ipv6Input = it
                            viewModel.clearUdpValidationError()
                        },
                        placeholderText = "例如 fd00::1234",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Uri,
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                } else {
                    Text(
                        text = "当前为 BLE 模式，地址会优先通过 BLE 自动同步。",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                DeckTextField(
                    label = "配对短码",
                    supporting = "BLE 与 UDP 共用的认证短码。",
                    value = pairCodeInput,
                    onValueChange = { pairCodeInput = it },
                    placeholderText = DiscoveryProtocol.DEFAULT_PAIR_CODE
                )

                if (transportIsolationMode.allowsUdp) {
                    Spacer(modifier = Modifier.height(12.dp))
                    DeckTextField(
                        label = "本机 UDP 端口",
                        supporting = "RC Pro 本地发送端口。",
                        value = localPortInput,
                        onValueChange = {
                            localPortInput = it
                            viewModel.clearUdpValidationError()
                        },
                        placeholderText = "1346",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        )
                    )
                }

                if (discoveredHosts.isNotEmpty() && transportIsolationMode.allowsUdp) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "已发现主机",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    discoveredHosts.forEach { host ->
                        val tag = when {
                            host.busy -> "占用中"
                            host.ready -> "就绪"
                            else -> "等待中"
                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            shape = DeckButtonShape,
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(host.hostName, style = MaterialTheme.typography.titleSmall)
                                    Text(
                                        text = host.label,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                                StatusBadge(
                                    text = tag,
                                    containerColor = when {
                                        host.busy -> MaterialTheme.colorScheme.errorContainer
                                        host.ready -> MaterialTheme.colorScheme.tertiaryContainer
                                        else -> MaterialTheme.colorScheme.secondaryContainer
                                    }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                TextButton(onClick = { viewModel.selectDiscoveredHost(host) }) {
                                    Text("使用")
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                TextButton(onClick = { viewModel.forgetPairing() }) {
                    Text("清除配对")
                }
            }
        } else {
            SectionCard(title = "手动目标") {
                DeckTextField(
                    label = "目标 IPv4",
                    supporting = "用于局域网 IPv4 Ping 与 UDP 通信。",
                    value = ipv4Input,
                    onValueChange = {
                        ipv4Input = it
                        viewModel.clearUdpValidationError()
                    },
                    placeholderText = "例如 192.168.1.10",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Uri,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))
                DeckTextField(
                    label = "目标 IPv6",
                    supporting = "用于局域网 IPv6 Ping 与 UDP 通信。",
                    value = ipv6Input,
                    onValueChange = {
                        ipv6Input = it
                        viewModel.clearUdpValidationError()
                    },
                    placeholderText = "例如 fd00::1234",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Uri,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                DeckTextField(
                    label = "目标 UDP 端口",
                    supporting = "ROS2 网关暴露的远端 UDP 控制端口。",
                    value = portInput,
                    onValueChange = {
                        portInput = it
                        viewModel.clearUdpValidationError()
                    },
                    placeholderText = "1387",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                DeckTextField(
                    label = "本机 UDP 端口",
                    supporting = "RC Pro 本地发送端口。",
                    value = localPortInput,
                    onValueChange = {
                        localPortInput = it
                        viewModel.clearUdpValidationError()
                    },
                    placeholderText = "1346",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                )
            }
        }

        SectionCard(title = "Ping 测试") {
            PingActionRow(
                title = PingSlot.LOCAL_IPV4.label,
                address = linkDiagnostics.localAddresses.ipv4.orEmpty(),
                summary = pingResultSummary(pingResults[PingSlot.LOCAL_IPV4]),
                inFlight = PingSlot.LOCAL_IPV4 in pingInFlight,
                enabled = linkDiagnostics.localAddresses.ipv4?.isNotBlank() == true,
                onPing = { viewModel.performPing(PingSlot.LOCAL_IPV4, linkDiagnostics.localAddresses.ipv4.orEmpty()) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            PingActionRow(
                title = PingSlot.LOCAL_IPV6.label,
                address = linkDiagnostics.localAddresses.ipv6.orEmpty(),
                summary = pingResultSummary(pingResults[PingSlot.LOCAL_IPV6]),
                inFlight = PingSlot.LOCAL_IPV6 in pingInFlight,
                enabled = linkDiagnostics.localAddresses.ipv6?.isNotBlank() == true,
                onPing = { viewModel.performPing(PingSlot.LOCAL_IPV6, linkDiagnostics.localAddresses.ipv6.orEmpty()) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            PingActionRow(
                title = PingSlot.PEER_IPV4.label,
                address = ipv4Input,
                summary = pingResultSummary(pingResults[PingSlot.PEER_IPV4]),
                inFlight = PingSlot.PEER_IPV4 in pingInFlight,
                enabled = ipv4Input.isNotBlank(),
                onPing = { viewModel.performPing(PingSlot.PEER_IPV4, ipv4Input) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            PingActionRow(
                title = PingSlot.PEER_IPV6.label,
                address = ipv6Input,
                summary = pingResultSummary(pingResults[PingSlot.PEER_IPV6]),
                inFlight = PingSlot.PEER_IPV6 in pingInFlight,
                enabled = ipv6Input.isNotBlank(),
                onPing = { viewModel.performPing(PingSlot.PEER_IPV6, ipv6Input) }
            )
        }

        udpValidationError?.let { error ->
            SectionCard(title = "参数校验") {
                Text(
                    text = error,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = {
                    viewModel.clearPingResult()
                    viewModel.clearUdpValidationError()
                    onDismiss()
                }
            ) {
                Text("取消")
            }
            Spacer(modifier = Modifier.width(10.dp))
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
                        if (transportIsolationMode.allowsUdp) {
                            viewModel.updateTargetIpv4(ipv4Input)
                            viewModel.updateTargetIpv6(ipv6Input)
                        }
                        true
                    }

                    if (connectionMode == ConnectionMode.MANUAL && transportIsolationMode.allowsUdp) {
                        viewModel.updateTargetIpv4(ipv4Input)
                        viewModel.updateTargetIpv6(ipv6Input)
                    }

                    if (savedLocalPort && savedTargetPort) {
                        viewModel.clearPingResult()
                        viewModel.clearUdpValidationError()
                        onDismiss()
                    }
                }
            ) {
                Text("保存")
            }
        }
    }
}

@Composable
fun SettingsDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .heightIn(max = 840.dp)
                .padding(16.dp),
            shape = DeckPanelShape,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            SettingsPanelContent(
                viewModel = viewModel,
                onDismiss = onDismiss,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )
        }
    }
}

@Composable
private fun SectionCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = DeckPanelShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
private fun DiagnosticStatusLine(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun PingActionRow(
    title: String,
    address: String,
    summary: String,
    inFlight: Boolean,
    enabled: Boolean,
    onPing: () -> Unit
) {
    Card(
        shape = DeckButtonShape,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = address.ifBlank { "未填写地址" },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = summary,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = onPing,
                    enabled = enabled && !inFlight,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    if (inFlight) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            color = MaterialTheme.colorScheme.onSecondary,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("Ping")
                    }
                }
            }
        }
    }
}

@Composable
private fun DeckTextField(
    label: String,
    supporting: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    readOnly: Boolean = false
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = supporting,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.bodyMedium.copy(textDecoration = TextDecoration.None),
            placeholder = {
                Text(
                    text = placeholderText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            singleLine = true,
            keyboardOptions = keyboardOptions,
            readOnly = readOnly,
            shape = DeckButtonShape
        )
    }
}

@Composable
fun HostSelectionDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    val candidates by viewModel.hostSelectionCandidates.collectAsState()

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            shape = DeckPanelShape,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "选择主机",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "当前有多个网关可用，请选择默认控制主机。",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                candidates.forEach { host ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = DeckButtonShape,
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(host.hostName, style = MaterialTheme.typography.titleSmall)
                                Text(host.address, style = MaterialTheme.typography.bodySmall)
                            }
                            Button(onClick = { viewModel.selectDiscoveredHost(host) }) {
                                Text("使用")
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("取消")
                    }
                }
            }
        }
    }
}

@Composable
fun DataLogDialog(
    viewModel: MainViewModel,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.94f)
                .heightIn(max = 760.dp)
                .padding(16.dp),
            shape = DeckPanelShape,
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            DataLogPanelContent(
                viewModel = viewModel,
                onDismiss = onDismiss,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun DataLogPanelContent(
    viewModel: MainViewModel,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dataLogs by viewModel.dataLogManager.dataLogs.collectAsState()
    val listState = rememberLazyListState()

    var showUdp by remember { mutableStateOf(true) }
    var showBle by remember { mutableStateOf(true) }
    var showDebug by remember { mutableStateOf(true) }

    val filteredLogs = dataLogs.filter { log ->
        when (log.transport) {
            TransportType.UDP -> showUdp
            TransportType.BLE -> showBle
        } && (showDebug || log.isSent != null)
    }

    LaunchedEffect(filteredLogs.size) {
        if (filteredLogs.isNotEmpty()) {
            listState.animateScrollToItem(filteredLogs.size - 1)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "数据日志",
                style = MaterialTheme.typography.titleLarge
            )
            StatusBadge(
                text = "${filteredLogs.size}/${dataLogs.size}",
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            FilterToggle(
                label = "UDP",
                checked = showUdp,
                onCheckedChange = { showUdp = it }
            )
            FilterToggle(
                label = "BLE",
                checked = showBle,
                onCheckedChange = { showBle = it }
            )
            FilterToggle(
                label = "调试",
                checked = showDebug,
                onCheckedChange = { showDebug = it }
            )
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))

        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(filteredLogs) { log ->
                val textColor = when {
                    log.isSent == true -> MaterialTheme.colorScheme.tertiary
                    log.isSent == false -> MaterialTheme.colorScheme.secondary
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                }
                val bgColor = when (log.transport) {
                    TransportType.UDP -> MaterialTheme.colorScheme.primaryContainer
                    TransportType.BLE -> MaterialTheme.colorScheme.secondaryContainer
                }

                Text(
                    text = log.formatLogLine(),
                    style = MaterialTheme.typography.bodySmall,
                    color = textColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(bgColor.copy(alpha = 0.45f), shape = RoundedCornerShape(14.dp))
                        .padding(horizontal = 10.dp, vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { viewModel.clearDataLogs() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("清空")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = onDismiss) {
                Text("关闭")
            }
        }
    }
}

@Composable
private fun FilterToggle(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
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
