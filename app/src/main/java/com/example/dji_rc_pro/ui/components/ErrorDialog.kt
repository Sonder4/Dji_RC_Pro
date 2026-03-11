package com.example.dji_rc_pro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.dji_rc_pro.util.ErrorCategory
import com.example.dji_rc_pro.util.ErrorCode
import com.example.dji_rc_pro.util.ErrorSeverity

@Composable
fun ErrorDialog(
    errorCode: ErrorCode,
    onRetry: () -> Unit,
    onDismiss: () -> Unit,
    onReport: (() -> Unit)? = null
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1A1A1A)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                getSeverityColor(errorCode.severity).copy(alpha = 0.2f),
                                RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = errorCode.code.toString().take(3),
                            color = getSeverityColor(errorCode.severity),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column {
                        Text(
                            text = "Error Occurred",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = errorCode.category.name.replace("_", " "),
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }
                }

                HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f))

                // Error Details
                ErrorDetailRow(
                    label = "Code",
                    value = "E${errorCode.code}"
                )

                ErrorDetailRow(
                    label = "Severity",
                    value = errorCode.severity.name,
                    valueColor = getSeverityColor(errorCode.severity)
                )

                ErrorDetailRow(
                    label = "Category",
                    value = errorCode.category.name.replace("_", " ")
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = errorCode.message,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }

                HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f))

                // Transport info
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ErrorTransportChip(
                        label = "UDP",
                        isActive = isUdpError(errorCode)
                    )
                    ErrorTransportChip(
                        label = "BLE",
                        isActive = isBleError(errorCode)
                    )
                }

                // Actions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (errorCode.severity == ErrorSeverity.HIGH ||
                        errorCode.severity == ErrorSeverity.CRITICAL) {
                        Button(
                            onClick = onRetry,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2196F3)
                            )
                        ) {
                            Text("Retry", color = Color.White)
                        }
                    }

                    onReport?.let { reportAction ->
                        OutlinedButton(
                            onClick = reportAction,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color(0xFFFF9800)
                            )
                        ) {
                            Text("Report", color = Color(0xFFFF9800))
                        }
                    }

                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        )
                    ) {
                        Text("Dismiss", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
private fun ErrorDetailRow(
    label: String,
    value: String,
    valueColor: Color = Color.White
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = Color.LightGray,
            fontSize = 12.sp
        )
        Text(
            text = value,
            color = valueColor,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun ErrorTransportChip(
    label: String,
    isActive: Boolean
) {
    Box(
        modifier = Modifier
            .background(
                if (isActive) Color.Red.copy(alpha = 0.2f) else Color.Gray.copy(alpha = 0.2f),
                RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(
                        if (isActive) Color.Red else Color.Gray,
                        RoundedCornerShape(3.dp)
                    )
            )
            Text(
                text = label,
                color = if (isActive) Color.Red else Color.Gray,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
fun MultipleErrorDialog(
    errors: List<ErrorCode>,
    onRetry: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1A1A1A)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                Color.Red.copy(alpha = 0.2f),
                                RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${errors.size}",
                            color = Color.Red,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column {
                        Text(
                            text = "Multiple Errors",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Multiple issues detected",
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }
                }

                HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f))

                // Error List
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 200.dp)
                        .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    errors.take(5).forEach { error ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(8.dp)
                                        .background(
                                            getSeverityColor(error.severity),
                                            RoundedCornerShape(4.dp)
                                        )
                                )
                                Text(
                                    text = error.message,
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    maxLines = 1
                                )
                            }
                            Text(
                                text = "E${error.code}",
                                color = Color.LightGray,
                                fontSize = 10.sp
                            )
                        }
                    }

                    if (errors.size > 5) {
                        Text(
                            text = "... and ${errors.size - 5} more errors",
                            color = Color.Gray,
                            fontSize = 11.sp
                        )
                    }
                }

                HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f))

                // Actions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = onRetry,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2196F3)
                        )
                    ) {
                        Text("Retry All", color = Color.White)
                    }

                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        )
                    ) {
                        Text("Dismiss", color = Color.White)
                    }
                }
            }
        }
    }
}

private fun getSeverityColor(severity: ErrorSeverity): Color {
    return when (severity) {
        ErrorSeverity.LOW -> Color(0xFF4CAF50)
        ErrorSeverity.MEDIUM -> Color(0xFFFFC107)
        ErrorSeverity.HIGH -> Color(0xFFFF9800)
        ErrorSeverity.CRITICAL -> Color(0xFFF44336)
    }
}

private fun isUdpError(errorCode: ErrorCode): Boolean {
    return errorCode.category == ErrorCategory.CONNECTION &&
            errorCode.name.startsWith("UDP")
}

private fun isBleError(errorCode: ErrorCode): Boolean {
    return errorCode.category == ErrorCategory.CONNECTION &&
            errorCode.name.startsWith("BLE")
}
