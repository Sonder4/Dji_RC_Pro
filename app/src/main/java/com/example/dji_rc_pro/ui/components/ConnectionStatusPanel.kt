package com.example.dji_rc_pro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dji_rc_pro.manager.ConnectionManager
import com.example.dji_rc_pro.manager.FrequencyManager
import com.example.dji_rc_pro.manager.HeartbeatManager
import com.example.dji_rc_pro.manager.ReconnectManager
import com.example.dji_rc_pro.util.ConnectionState
import com.example.dji_rc_pro.util.OverallConnectionState
import com.example.dji_rc_pro.manager.HeartbeatManager.HeartbeatCombinedStatus
import com.example.dji_rc_pro.manager.HeartbeatManager.HealthStatus

@Composable
fun ConnectionStatusPanel(
    connectionManager: ConnectionManager,
    frequencyManager: FrequencyManager,
    heartbeatManager: HeartbeatManager,
    reconnectManager: ReconnectManager,
    modifier: Modifier = Modifier
) {
    val connectionState by connectionManager.connectionState.collectAsState()
    val frequencyHz by frequencyManager.frequencyHz.collectAsState()
    val heartbeatStatus = heartbeatManager.getCombinedStatus()
    val reconnectState by reconnectManager.reconnectState.collectAsState()

    Card(
        modifier = modifier
            .width(280.dp)
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.7f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Title
            Text(
                text = "Connection Status",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            HorizontalDivider(color = Color.Gray.copy(alpha = 0.5f))

            // Overall Status
            OverallStatusRow(
                state = connectionState.overallState,
                isReconnecting = connectionState.reconnecting
            )

            // Transport Status
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TransportStatusItem(
                    name = "UDP",
                    isConnected = connectionState.udpState.isConnected,
                    isConnecting = connectionState.udpState.isConnecting
                )
                TransportStatusItem(
                    name = "BLE",
                    isConnected = connectionState.bleState.isConnected,
                    isConnecting = connectionState.bleState.isConnecting
                )
            }

            HorizontalDivider(color = Color.Gray.copy(alpha = 0.5f))

            // Frequency
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Frequency",
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
                Text(
                    text = "$frequencyHz Hz",
                    color = Color.Cyan,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Heartbeat Status
            HeartbeatStatusRow(
                status = heartbeatStatus.overallHealth,
                udpMissed = heartbeatManager.getMissedCount("UDP"),
                bleMissed = heartbeatManager.getMissedCount("BLE")
            )

            // Reconnect Status
            if (reconnectState.isReconnecting) {
                ReconnectStatusRow(
                    attempt = reconnectState.attemptCount,
                    maxAttempts = reconnectState.maxAttempts,
                    delayMs = reconnectState.currentDelayMs
                )
            }

            HorizontalDivider(color = Color.Gray.copy(alpha = 0.5f))

            // Statistics
            StatisticsRow(
                packetsSent = connectionState.udpState.packetsSent + connectionState.bleState.packetsSent,
                packetsReceived = connectionState.udpState.packetsReceived + connectionState.bleState.packetsReceived,
                errors = connectionState.udpState.errorCount + connectionState.bleState.errorCount,
                uptime = connectionState.getUptimeMillis()
            )
        }
    }
}

@Composable
private fun OverallStatusRow(
    state: OverallConnectionState,
    isReconnecting: Boolean
) {
    val (color, text) = when {
        isReconnecting -> Color.Yellow to "Reconnecting..."
        else -> when (state) {
            OverallConnectionState.CONNECTED -> Color.Green to "Connected"
            OverallConnectionState.PARTIAL -> Color(0xFF00BFFF) to "Partial"
            OverallConnectionState.CONNECTING -> Color.Yellow to "Connecting..."
            OverallConnectionState.ERROR -> Color.Red to "Error"
            OverallConnectionState.DISCONNECTED -> Color.Gray to "Disconnected"
            OverallConnectionState.RECONNECTING -> Color.Yellow to "Reconnecting..."
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Status",
            color = Color.LightGray,
            fontSize = 12.sp
        )
        Box(
            modifier = Modifier
                .background(color.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 2.dp)
        ) {
            Text(
                text = text,
                color = color,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun TransportStatusItem(
    name: String,
    isConnected: Boolean,
    isConnecting: Boolean
) {
    val color = when {
        isConnecting -> Color.Yellow
        isConnected -> Color.Green
        else -> Color.Gray
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            color = Color.LightGray,
            fontSize = 11.sp
        )
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(color, RoundedCornerShape(4.dp))
        )
        Text(
            text = if (isConnected) "ON" else if (isConnecting) "..." else "OFF",
            color = color,
            fontSize = 10.sp
        )
    }
}

@Composable
private fun HeartbeatStatusRow(
    status: HealthStatus,
    udpMissed: Int,
    bleMissed: Int
) {
    val statusColor = when (status) {
        HealthStatus.HEALTHY -> Color.Green
        HealthStatus.DEGRADED -> Color.Yellow
        HealthStatus.UNHEALTHY -> Color.Red
        HealthStatus.INACTIVE -> Color.Gray
    }

    val statusText = when (status) {
        HealthStatus.HEALTHY -> "Healthy"
        HealthStatus.DEGRADED -> "Degraded"
        HealthStatus.UNHEALTHY -> "Unhealthy"
        HealthStatus.INACTIVE -> "Inactive"
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Heartbeat",
            color = Color.LightGray,
            fontSize = 12.sp
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(statusColor, RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = statusText,
                color = statusColor,
                fontSize = 11.sp
            )
            if (udpMissed > 0 || bleMissed > 0) {
                Text(
                    text = " (U:$udpMissed B:$bleMissed)",
                    color = if (status == HealthStatus.UNHEALTHY) Color.Red else Color.Yellow,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Composable
private fun ReconnectStatusRow(
    attempt: Int,
    maxAttempts: Int,
    delayMs: Long
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Yellow.copy(alpha = 0.1f), RoundedCornerShape(4.dp))
            .padding(6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Reconnecting",
            color = Color.Yellow,
            fontSize = 11.sp
        )
        Text(
            text = "$attempt/$maxAttempts (${delayMs}ms)",
            color = Color.Yellow,
            fontSize = 10.sp
        )
    }
}

@Composable
private fun StatisticsRow(
    packetsSent: Long,
    packetsReceived: Long,
    errors: Int,
    uptime: Long
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Sent: ${formatNumber(packetsSent)}",
                color = Color.LightGray,
                fontSize = 10.sp
            )
            Text(
                text = "Recv: ${formatNumber(packetsReceived)}",
                color = Color.LightGray,
                fontSize = 10.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Errors: $errors",
                color = if (errors > 0) Color.Red else Color.LightGray,
                fontSize = 10.sp
            )
            Text(
                text = "Uptime: ${formatDuration(uptime)}",
                color = Color.LightGray,
                fontSize = 10.sp
            )
        }
    }
}

private fun formatNumber(num: Long): String {
    return when {
        num >= 1000000 -> "${num / 1000000}M"
        num >= 1000 -> "${num / 1000}K"
        else -> num.toString()
    }
}

private fun formatDuration(ms: Long): String {
    val seconds = ms / 1000
    val minutes = seconds / 60
    val hours = minutes / 60
    return when {
        hours > 0 -> "${hours}h ${minutes % 60}m"
        minutes > 0 -> "${minutes}m ${seconds % 60}s"
        seconds > 0 -> "${seconds}s"
        else -> "0s"
    }
}
