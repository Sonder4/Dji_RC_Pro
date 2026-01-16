package com.example.dji_rc_pro.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
fun VirtualStick(
    modifier: Modifier = Modifier,
    xValue: Int, // 0..255
    yValue: Int, // 0..255
    size: androidx.compose.ui.unit.Dp = 200.dp,
    onStickMoved: (Float, Float) -> Unit
) {
    // Convert 0..255 to -1..1 for display
    // 0 -> -1.0, 127 -> 0.0, 255 -> 1.0
    // (val - 127.5) / 127.5
    
    // We maintain local state for smooth dragging, but also sync with props
    // If user is NOT dragging, use props. If dragging, use local.
    
    var isDragging by remember { mutableStateOf(false) }
    var dragPosition by remember { mutableStateOf(Offset.Zero) }
    
    val sizePx = with(LocalDensity.current) { size.toPx() }
    val radius = sizePx / 2
    val knobRadius = sizePx / 6
    
    // If not dragging, calculate position from props
    val knobPosition = if (!isDragging) {
        val normX = (xValue - 127.5f) / 127.5f
        val normY = (yValue - 127.5f) / 127.5f
        // Invert Y because screen coordinates: down is positive, but joystick up is positive (usually)
        // Wait, StickTransformer: 
        // 1.0 (Up) -> 255. 
        // Screen Y: Up is negative.
        // So we need to invert Y for display.
        // 255 (Up) -> 1.0 -> Display Y should be -radius
        Offset(normX * radius, -normY * radius)
    } else {
        dragPosition
    }

    Box(
        modifier = modifier
            .size(size)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { isDragging = true },
                    onDragEnd = { 
                        isDragging = false 
                        onStickMoved(0f, 0f) // Center on release
                    },
                    onDragCancel = { 
                        isDragging = false 
                        onStickMoved(0f, 0f)
                    }
                ) { change, dragAmount ->
                    change.consume()
                    
                    var newPos = dragPosition + dragAmount
                    val distance = newPos.getDistance()
                    
                    // Clamp to circle
                    if (distance > radius) {
                        val angle = atan2(newPos.y, newPos.x)
                        newPos = Offset(
                            cos(angle) * radius,
                            sin(angle) * radius
                        )
                    }
                    
                    dragPosition = newPos
                    
                    // Normalize to -1..1
                    val normX = newPos.x / radius
                    val normY = -(newPos.y / radius) // Invert Y back for logic
                    
                    onStickMoved(normX, normY)
                }
            }
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            // Draw Background
            drawCircle(
                color = Color.Gray.copy(alpha = 0.3f),
                radius = radius
            )
            
            // Draw Knob
            drawCircle(
                color = Color.Cyan,
                radius = knobRadius,
                center = center + knobPosition // center is standard Compose center
            )
        }
    }
}
