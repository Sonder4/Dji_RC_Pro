package com.example.dji_rc_pro.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.example.dji_rc_pro.manager.FrequencyManager

@Composable
fun FrequencyDialog(
    frequencyManager: FrequencyManager,
    onDismiss: () -> Unit
) {
    val frequencyHz by frequencyManager.frequencyHz.collectAsState()
    val intervalMs by frequencyManager.intervalMs.collectAsState()

    var sliderValue by remember { mutableFloatStateOf(frequencyHz.toFloat()) }
    var selectedPreset by remember { mutableIntStateOf(frequencyHz) }

    LaunchedEffect(frequencyHz) {
        sliderValue = frequencyHz.toFloat()
        selectedPreset = frequencyHz
    }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1A1A1A)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp)
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
                                Color.Cyan.copy(alpha = 0.2f),
                                RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${frequencyHz}",
                            color = Color.Cyan,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column {
                        Text(
                            text = "Frequency Settings",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Data transmission rate",
                            color = Color.LightGray,
                            fontSize = 12.sp
                        )
                    }
                }

                HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f))

                // Current Value Display
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${sliderValue.toInt()} Hz",
                        color = Color.Cyan,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Interval: ${intervalMs}ms",
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                }

                // Slider
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "10 Hz",
                            color = Color.Gray,
                            fontSize = 11.sp
                        )
                        Text(
                            text = "200 Hz",
                            color = Color.Gray,
                            fontSize = 11.sp
                        )
                    }

                    Slider(
                        value = sliderValue,
                        onValueChange = {
                            sliderValue = it
                            selectedPreset = 0
                        },
                        valueRange = 10f..200f,
                        steps = 18,
                        colors = SliderDefaults.colors(
                            thumbColor = Color.Cyan,
                            activeTrackColor = Color.Cyan,
                            inactiveTrackColor = Color.Gray.copy(alpha = 0.3f)
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Preset Buttons
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Quick Presets",
                        color = Color.LightGray,
                        fontSize = 12.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        FrequencyPresetButton(
                            preset = 10,
                            isSelected = selectedPreset == 10,
                            onClick = {
                                selectedPreset = 10
                                sliderValue = 10f
                            }
                        )
                        FrequencyPresetButton(
                            preset = 50,
                            isSelected = selectedPreset == 50,
                            onClick = {
                                selectedPreset = 50
                                sliderValue = 50f
                            }
                        )
                        FrequencyPresetButton(
                            preset = 100,
                            isSelected = selectedPreset == 100,
                            onClick = {
                                selectedPreset = 100
                                sliderValue = 100f
                            }
                        )
                        FrequencyPresetButton(
                            preset = 150,
                            isSelected = selectedPreset == 150,
                            onClick = {
                                selectedPreset = 150
                                sliderValue = 150f
                            }
                        )
                        FrequencyPresetButton(
                            preset = 200,
                            isSelected = selectedPreset == 200,
                            onClick = {
                                selectedPreset = 200
                                sliderValue = 200f
                            }
                        )
                    }
                }

                // Bandwidth Info
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black.copy(alpha = 0.3f)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Bandwidth Estimate",
                                color = Color.LightGray,
                                fontSize = 11.sp
                            )
                            Text(
                                text = "${frequencyManager.getBandwidthEstimate(20)} kbps",
                                color = Color.Cyan,
                                fontSize = 11.sp
                            )
                        }
                        Text(
                            text = "Based on 20-byte packets",
                            color = Color.Gray,
                            fontSize = 10.sp
                        )
                    }
                }

                HorizontalDivider(color = Color.Gray.copy(alpha = 0.3f))

                // Actions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            frequencyManager.resetToDefault()
                            sliderValue = frequencyManager.defaultFrequency.toFloat()
                            selectedPreset = frequencyManager.defaultFrequency
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.LightGray
                        )
                    ) {
                        Text("Reset", color = Color.LightGray)
                    }

                    Button(
                        onClick = {
                            frequencyManager.setFrequency(sliderValue.toInt(), persist = true)
                            onDismiss()
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Cyan
                        )
                    ) {
                        Text("Apply", color = Color.Black)
                    }

                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        )
                    ) {
                        Text("Cancel", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
private fun FrequencyPresetButton(
    preset: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Color.Cyan else Color.Gray.copy(alpha = 0.2f),
        label = "backgroundColor"
    )

    val textColor by animateColorAsState(
        targetValue = if (isSelected) Color.Black else Color.LightGray,
        label = "textColor"
    )

    Box(
        modifier = Modifier
            .size(48.dp)
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) Color.Cyan else Color.Gray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "${preset}",
            color = textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CompactFrequencySelector(
    frequencyManager: FrequencyManager,
    onOpenDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    val frequencyHz by frequencyManager.frequencyHz.collectAsState()

    Card(
        modifier = modifier
            .clickable(onClick = onOpenDialog),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.6f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color.Cyan, RoundedCornerShape(4.dp))
            )
            Text(
                text = "Freq",
                color = Color.LightGray,
                fontSize = 11.sp
            )
            Text(
                text = "$frequencyHz Hz",
                color = Color.Cyan,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
