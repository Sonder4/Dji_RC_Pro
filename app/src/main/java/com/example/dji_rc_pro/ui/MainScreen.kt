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
import com.example.dji_rc_pro.domain.discovery.DiscoveryProtocol
import com.example.dji_rc_pro.domain.model.TransportType
import com.example.dji_rc_pro.ui.components.ErrorDialog
import com.example.dji_rc_pro.ui.components.FrequencyDialog
import com.example.dji_rc_pro.ui.components.VirtualStick
import com.example.dji_rc_pro.viewmodel.MainViewModel

private val DeckPanelShape = RoundedCornerShape(28.dp)
private val DeckButtonShape = RoundedCornerShape(22.dp)
private val DeckChipShape = RoundedCornerShape(18.dp)

private enum class CockpitDrawerPanel(val label: String) {
    CONNECTION("Connection"),
    BLE("BLE"),
    LOGS("Data Logs")
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
    val targetIp by viewModel.targetIp.collectAsState()
    val targetPort by viewModel.targetPort.collectAsState()
    val connectionMode by viewModel.connectionMode.collectAsState()
    val transportIsolationMode by viewModel.transportIsolationMode.collectAsState()
    val autoPairStatus by viewModel.autoPairStatus.collectAsState()
    val bleConnectionState by viewModel.bleConnectionState.collectAsState()
    val isBleScanning by viewModel.isBleScanning.collectAsState()
    val toastMessage by viewModel.toastMessage.collectAsState()
    val showErrorDialog by viewModel.showErrorDialog.collectAsState()
    val showFrequencyDialog by viewModel.showFrequencyDialog.collectAsState()
    val showHostSelectionDialog by viewModel.showHostSelectionDialog.collectAsState()
    val pairedHostName by viewModel.pairedHostName.collectAsState()
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

    val linkSummary = when (transportIsolationMode) {
        TransportIsolationMode.UDP_ONLY -> if (isUdpRunning) "UDP live" else "UDP standby"
        TransportIsolationMode.BLE_ONLY -> when {
            bleConnectionState == BluetoothProfile.STATE_CONNECTED -> "BLE linked"
            bleConnectionState == BluetoothProfile.STATE_CONNECTING -> "BLE connecting"
            isBleScanning -> "BLE scanning"
            else -> "BLE idle"
        }
        TransportIsolationMode.BLE_UDP -> when {
            isUdpRunning -> "UDP primary"
            bleConnectionState == BluetoothProfile.STATE_CONNECTED -> "BLE bootstrap"
            isBleScanning -> "BLE scanning"
            else -> "Hybrid standby"
        }
    }

    val targetSummary = if (connectionMode == ConnectionMode.AUTO_PAIR) {
        pairedHostName ?: when {
            transportIsolationMode == TransportIsolationMode.BLE_ONLY -> "BLE Auto Pair"
            autoPairStatus.isNotBlank() -> autoPairStatus
            else -> "Auto Pair"
        }
    } else {
        "$targetIp:$targetPort"
    }

    val stickSize = if (activeDrawerPanel == null) 132.dp else 120.dp
    val modeButtonValue = when (transportIsolationMode) {
        TransportIsolationMode.UDP_ONLY -> "UDP"
        TransportIsolationMode.BLE_ONLY -> "BLE"
        TransportIsolationMode.BLE_UDP -> "Hybrid"
    }

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
                modeValue = transportIsolationMode.displayName,
                linkValue = linkSummary,
                targetValue = targetSummary,
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
                    title = "Left Stick",
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
                    title = "Right Stick",
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
                label = "Mode",
                value = modeValue,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.weight(0.92f)
            )
            CockpitStatusChip(
                label = "Link",
                value = linkValue,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.weight(0.96f)
            )
            CockpitStatusChip(
                label = "Target",
                value = targetValue,
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier.weight(1.12f)
            )
            CockpitToolButton(
                title = "Mode",
                value = modeButtonValue,
                active = isModeActive,
                accentColor = MaterialTheme.colorScheme.secondary,
                onClick = onModeClick
            )
            CockpitToolButton(
                title = "Settings",
                value = "Panel",
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
                text = "Transport Mode",
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
                            TransportIsolationMode.UDP_ONLY -> "Primary"
                            TransportIsolationMode.BLE_UDP -> "Handoff"
                            TransportIsolationMode.BLE_ONLY -> "Bridge"
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
                    Text("Close")
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
                title = "Left Wheel",
                value = leftWheel,
                modifier = Modifier.weight(1f)
            )
            WheelMeter(
                title = "Right Wheel",
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
        VirtualButton(text = "UP", bitIndex = 10, buttonMask = buttonMask, onPress = onPress)
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            VirtualButton(text = "LT", bitIndex = 12, buttonMask = buttonMask, onPress = onPress)
            Spacer(modifier = Modifier.size(38.dp))
            VirtualButton(text = "RT", bitIndex = 13, buttonMask = buttonMask, onPress = onPress)
        }
        VirtualButton(text = "DN", bitIndex = 11, buttonMask = buttonMask, onPress = onPress)
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
                        TransportIsolationMode.UDP_ONLY -> if (isUdpRunning) "UDP Primary" else "UDP Idle"
                        TransportIsolationMode.BLE_ONLY -> if (bleConnectionState == BluetoothProfile.STATE_CONNECTED) "BLE Primary" else "BLE Waiting"
                        TransportIsolationMode.BLE_UDP -> if (isUdpRunning) "UDP Primary" else "BLE Bootstrap"
                    },
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Wheels $leftWheel / $rightWheel",
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
                    text = if (isVideoEnabled) "Video" else "Core",
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
                    text = "BLE Device Bridge",
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
                    !transportMode.allowsBle -> "DISABLED"
                    bleConnectionState == BluetoothProfile.STATE_CONNECTED -> "CONNECTED"
                    isBleScanning -> "SCANNING"
                    else -> "READY"
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
                title = if (isBleScanning) "Stop Scan" else "Start Scan",
                subtitle = "Search for ROS2 gateway",
                active = isBleScanning,
                enabled = transportMode.allowsBle,
                accentColor = MaterialTheme.colorScheme.secondary,
                onClick = {
                    if (isBleScanning) viewModel.stopBleScan() else viewModel.startBleScan()
                },
                modifier = Modifier.weight(1f)
            )
            ActionButton(
                title = "Disconnect",
                subtitle = "Drop current BLE session",
                active = bleConnectionState == BluetoothProfile.STATE_CONNECTED,
                enabled = bleConnectionState == BluetoothProfile.STATE_CONNECTED,
                accentColor = MaterialTheme.colorScheme.error,
                onClick = { viewModel.disconnectBle() },
                modifier = Modifier.weight(1f)
            )
            ActionButton(
                title = "Close",
                subtitle = "Return to controller deck",
                onClick = onDismiss,
                modifier = Modifier.weight(1f)
            )
        }

        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))

        if (!transportMode.allowsBle) {
            EmptyStateCard(
                title = "BLE is disabled in UDP Only mode",
                supporting = "Switch transport mode to BLE or BLE + UDP to search and connect."
            )
        } else if (bleScanResults.isEmpty()) {
            EmptyStateCard(
                title = if (isBleScanning) "Searching for ROS2 BLE gateway" else "No BLE devices yet",
                supporting = if (isBleScanning) {
                    "The scan is running. Nearby ROS2 gateways will appear here."
                } else {
                    "Start a BLE scan to discover RCBridge-ROS2 devices."
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
                            text = "PAIRED",
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
                text = if (isConnected) "Tap to reconnect another device" else "Tap to connect",
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
    val isVideoEnabled by viewModel.isVideoEnabled.collectAsState()
    val frequencyHz by viewModel.frequencyManager.frequencyHz.collectAsState()

    var ipInput by remember(targetIp) { mutableStateOf(targetIp) }
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
                    text = "Transport Settings",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Landscape drawer for pairing, routing, and runtime controls.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            StatusBadge(
                text = transportIsolationMode.displayName,
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        }

        SectionCard(title = "Connection") {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                SegmentButton(
                    label = "Auto Pair",
                    supporting = "Recommended",
                    selected = connectionMode == ConnectionMode.AUTO_PAIR,
                    onClick = { viewModel.setConnectionMode(ConnectionMode.AUTO_PAIR) },
                    modifier = Modifier.weight(1f)
                )
                SegmentButton(
                    label = "Manual",
                    supporting = "Direct target",
                    selected = connectionMode == ConnectionMode.MANUAL,
                    enabled = transportIsolationMode != TransportIsolationMode.BLE_ONLY,
                    onClick = { viewModel.setConnectionMode(ConnectionMode.MANUAL) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        SectionCard(title = "Transport") {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                TransportIsolationMode.entries.forEach { mode ->
                    SegmentButton(
                        label = when (mode) {
                            TransportIsolationMode.UDP_ONLY -> "UDP"
                            TransportIsolationMode.BLE_UDP -> "BLE + UDP"
                            TransportIsolationMode.BLE_ONLY -> "BLE"
                        },
                        supporting = when (mode) {
                            TransportIsolationMode.UDP_ONLY -> "Primary"
                            TransportIsolationMode.BLE_UDP -> "Hybrid"
                            TransportIsolationMode.BLE_ONLY -> "Bridge"
                        },
                        selected = transportIsolationMode == mode,
                        onClick = { viewModel.setTransportIsolationMode(mode) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = when (transportIsolationMode) {
                    TransportIsolationMode.UDP_ONLY -> "BLE is disabled. Discovery and control use UDP only."
                    TransportIsolationMode.BLE_ONLY -> "BLE discovery and control are active. Manual target entry is disabled."
                    TransportIsolationMode.BLE_UDP -> "BLE bootstraps the session, then UDP becomes the primary path."
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        SectionCard(title = "Runtime") {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ActionButton(
                    title = "Frequency",
                    subtitle = "$frequencyHz Hz transmission",
                    active = false,
                    accentColor = MaterialTheme.colorScheme.secondary,
                    onClick = { viewModel.showFrequencyDialog() },
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    title = if (isVideoEnabled) "Video ON" else "Video OFF",
                    subtitle = "Center panel preview",
                    active = isVideoEnabled,
                    accentColor = MaterialTheme.colorScheme.primary,
                    onClick = { viewModel.toggleVideo() },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        SectionCard(title = "Current Status") {
            Text(
                text = "Link status: $autoPairStatus",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Current host: ${pairedHostName ?: "Not paired"}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            pairedHostAddress?.let { address ->
                Text(
                    text = "Endpoint: $address:${pairedControlPort ?: DiscoveryProtocol.DEFAULT_CONTROL_PORT}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        if (connectionMode == ConnectionMode.AUTO_PAIR) {
            SectionCard(title = "Pairing") {
                if (transportIsolationMode != TransportIsolationMode.BLE_ONLY) {
                    DeckTextField(
                        label = "Bootstrap Address",
                        supporting = "Use the host IPv6 or IPv4 only when the Wi-Fi cannot auto-discover.",
                        value = ipInput,
                        onValueChange = {
                            ipInput = it
                            viewModel.clearUdpValidationError()
                        },
                        placeholderText = "Host IPv6 or IPv4",
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Uri,
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                } else {
                    Text(
                        text = "BLE Only uses BLE advertisements to discover the ROS2 gateway automatically.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                DeckTextField(
                    label = "Pair Code",
                    supporting = "Short code used for BLE and UDP pairing.",
                    value = pairCodeInput,
                    onValueChange = { pairCodeInput = it },
                    placeholderText = DiscoveryProtocol.DEFAULT_PAIR_CODE
                )

                if (transportIsolationMode != TransportIsolationMode.BLE_ONLY) {
                    Spacer(modifier = Modifier.height(12.dp))
                    DeckTextField(
                        label = "Local Port",
                        supporting = "Local UDP port for the RC Pro app.",
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

                if (discoveredHosts.isNotEmpty() && transportIsolationMode != TransportIsolationMode.BLE_ONLY) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Discovered Hosts",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    discoveredHosts.forEach { host ->
                        val tag = when {
                            host.busy -> "Busy"
                            host.ready -> "Ready"
                            else -> "Waiting"
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
                                    Text("Use")
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
                TextButton(onClick = { viewModel.forgetPairing() }) {
                    Text("Forget Pairing")
                }
            }
        } else {
            SectionCard(title = "Manual Target") {
                DeckTextField(
                    label = "Target Address",
                    supporting = "IPv6 and IPv4 are both supported. Give the address its own row to avoid clipping.",
                    value = ipInput,
                    onValueChange = {
                        ipInput = it
                        viewModel.clearUdpValidationError()
                    },
                    placeholderText = "Target IPv6 or IPv4",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Uri,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { viewModel.performPing(ipInput) },
                        enabled = !isPinging && ipInput.isNotBlank(),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        if (isPinging) {
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

                Spacer(modifier = Modifier.height(12.dp))

                DeckTextField(
                    label = "Target Port",
                    supporting = "Remote UDP port exposed by the ROS2 gateway.",
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
                    label = "Local Port",
                    supporting = "Source UDP port used by the RC Pro app.",
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

        udpValidationError?.let { error ->
            SectionCard(title = "Validation") {
                Text(
                    text = error,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        pingResult?.let { result ->
            SectionCard(title = "Ping Result") {
                Text(
                    text = if (result.isSuccess) "Ping success" else "Ping failed",
                    style = MaterialTheme.typography.titleSmall,
                    color = if (result.isSuccess) {
                        MaterialTheme.colorScheme.tertiary
                    } else {
                        MaterialTheme.colorScheme.error
                    }
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Target: ${result.ip}",
                    style = MaterialTheme.typography.bodySmall
                )
                if (result.isSuccess) {
                    Text(
                        text = "Packets: ${result.packetsReceived}/${result.packetsSent} (${result.packetLossPercent}% loss)",
                        style = MaterialTheme.typography.bodySmall
                    )
                    if (result.packetsReceived > 0) {
                        Text(
                            text = "Latency: min ${result.minTimeMs}ms / max ${result.maxTimeMs}ms / avg ${result.avgTimeMs}ms",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                } else if (result.errorMessage.isNotEmpty()) {
                    Text(
                        text = "Error: ${result.errorMessage}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
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
                Text("Cancel")
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
        }
    }
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

    var ipInput by remember(targetIp) { mutableStateOf(targetIp) }
    var portInput by remember(targetPort) { mutableStateOf(targetPort.toString()) }
    var localPortInput by remember(localPort) { mutableStateOf(localPort.toString()) }
    var pairCodeInput by remember(pairCode) { mutableStateOf(pairCode) }

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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Transport Settings",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "Bright control layout with explicit mode and pairing sections.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    StatusBadge(
                        text = transportIsolationMode.displayName,
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                }

                SectionCard(title = "Connection") {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        SegmentButton(
                            label = "Auto Pair",
                            supporting = "Recommended",
                            selected = connectionMode == ConnectionMode.AUTO_PAIR,
                            onClick = { viewModel.setConnectionMode(ConnectionMode.AUTO_PAIR) },
                            modifier = Modifier.weight(1f)
                        )
                        SegmentButton(
                            label = "Manual",
                            supporting = "Direct target",
                            selected = connectionMode == ConnectionMode.MANUAL,
                            enabled = transportIsolationMode != TransportIsolationMode.BLE_ONLY,
                            onClick = { viewModel.setConnectionMode(ConnectionMode.MANUAL) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                SectionCard(title = "Transport") {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        TransportIsolationMode.entries.forEach { mode ->
                            SegmentButton(
                                label = when (mode) {
                                    TransportIsolationMode.UDP_ONLY -> "UDP"
                                    TransportIsolationMode.BLE_UDP -> "BLE + UDP"
                                    TransportIsolationMode.BLE_ONLY -> "BLE"
                                },
                                supporting = when (mode) {
                                    TransportIsolationMode.UDP_ONLY -> "Primary"
                                    TransportIsolationMode.BLE_UDP -> "Hybrid"
                                    TransportIsolationMode.BLE_ONLY -> "Bridge"
                                },
                                selected = transportIsolationMode == mode,
                                onClick = { viewModel.setTransportIsolationMode(mode) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = when (transportIsolationMode) {
                            TransportIsolationMode.UDP_ONLY -> "BLE is disabled. Discovery and control use UDP only."
                            TransportIsolationMode.BLE_ONLY -> "BLE discovery and control are active. Manual target entry is disabled."
                            TransportIsolationMode.BLE_UDP -> "BLE bootstraps the session, then UDP becomes the primary path."
                        },
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                SectionCard(title = "Current Status") {
                    Text(
                        text = "Link status: $autoPairStatus",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Current host: ${pairedHostName ?: "Not paired"}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    pairedHostAddress?.let { address ->
                        Text(
                            text = "Endpoint: $address:${pairedControlPort ?: DiscoveryProtocol.DEFAULT_CONTROL_PORT}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                if (connectionMode == ConnectionMode.AUTO_PAIR) {
                    SectionCard(title = "Pairing") {
                        if (transportIsolationMode != TransportIsolationMode.BLE_ONLY) {
                            DeckTextField(
                                label = "Bootstrap Address",
                                supporting = "Use the host IPv6 or IPv4 only when the Wi-Fi cannot auto-discover.",
                                value = ipInput,
                                onValueChange = {
                                    ipInput = it
                                    viewModel.clearUdpValidationError()
                                },
                                placeholderText = "Host IPv6 or IPv4",
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Uri,
                                    imeAction = ImeAction.Next
                                )
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        } else {
                            Text(
                                text = "BLE Only uses BLE advertisements to discover the ROS2 gateway automatically.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                        }

                        DeckTextField(
                            label = "Pair Code",
                            supporting = "Short code used for BLE and UDP pairing.",
                            value = pairCodeInput,
                            onValueChange = { pairCodeInput = it },
                            placeholderText = DiscoveryProtocol.DEFAULT_PAIR_CODE
                        )

                        if (transportIsolationMode != TransportIsolationMode.BLE_ONLY) {
                            Spacer(modifier = Modifier.height(12.dp))
                            DeckTextField(
                                label = "Local Port",
                                supporting = "Local UDP port for the RC Pro app.",
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

                        if (discoveredHosts.isNotEmpty() && transportIsolationMode != TransportIsolationMode.BLE_ONLY) {
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(
                                text = "Discovered Hosts",
                                style = MaterialTheme.typography.titleSmall
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            discoveredHosts.forEach { host ->
                                val tag = when {
                                    host.busy -> "Busy"
                                    host.ready -> "Ready"
                                    else -> "Waiting"
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
                                            Text("Use")
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        TextButton(onClick = { viewModel.forgetPairing() }) {
                            Text("Forget Pairing")
                        }
                    }
                } else {
                    SectionCard(title = "Manual Target") {
                        DeckTextField(
                            label = "Target Address",
                            supporting = "IPv6 and IPv4 are both supported. Give the address its own row to avoid clipping.",
                            value = ipInput,
                            onValueChange = {
                                ipInput = it
                                viewModel.clearUdpValidationError()
                            },
                            placeholderText = "Target IPv6 or IPv4",
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Uri,
                                imeAction = ImeAction.Next
                            )
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Button(
                                onClick = { viewModel.performPing(ipInput) },
                                enabled = !isPinging && ipInput.isNotBlank(),
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                            ) {
                                if (isPinging) {
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

                        Spacer(modifier = Modifier.height(12.dp))

                        DeckTextField(
                            label = "Target Port",
                            supporting = "Remote UDP port exposed by the ROS2 gateway.",
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
                            label = "Local Port",
                            supporting = "Source UDP port used by the RC Pro app.",
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

                udpValidationError?.let { error ->
                    SectionCard(title = "Validation") {
                        Text(
                            text = error,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                pingResult?.let { result ->
                    SectionCard(title = "Ping Result") {
                        Text(
                            text = if (result.isSuccess) "Ping success" else "Ping failed",
                            style = MaterialTheme.typography.titleSmall,
                            color = if (result.isSuccess) {
                                MaterialTheme.colorScheme.tertiary
                            } else {
                                MaterialTheme.colorScheme.error
                            }
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Target: ${result.ip}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        if (result.isSuccess) {
                            Text(
                                text = "Packets: ${result.packetsReceived}/${result.packetsSent} (${result.packetLossPercent}% loss)",
                                style = MaterialTheme.typography.bodySmall
                            )
                            if (result.packetsReceived > 0) {
                                Text(
                                    text = "Latency: min ${result.minTimeMs}ms / max ${result.maxTimeMs}ms / avg ${result.avgTimeMs}ms",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        } else if (result.errorMessage.isNotEmpty()) {
                            Text(
                                text = "Error: ${result.errorMessage}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
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
                        Text("Cancel")
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
                }
            }
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
private fun DeckTextField(
    label: String,
    supporting: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
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
                    text = "Select Host",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Multiple gateways are ready. Choose the default control host.",
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
                                Text("Use")
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
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
                text = "Data Log",
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
                label = "Debug",
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
                Text("Clear")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = onDismiss) {
                Text("Close")
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
