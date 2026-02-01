package com.example.dji_rc_pro.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.dji_rc_pro.helper.UsbSerialHelper

@Composable
fun UsbTestScreen() {
    // Use applicationContext to avoid Activity memory leaks
    val context = LocalContext.current.applicationContext
    val usbHelper = remember { UsbSerialHelper(context) }
    
    var logText by remember { mutableStateOf("Ready to connect...\n") }
    var inputText by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    // Handle callbacks
    DisposableEffect(Unit) {
        usbHelper.onDeviceStateChanged = { state ->
            logText += "STATUS: $state\n"
        }
        usbHelper.onDataReceived = { data ->
            val msg = String(data) // Assume ASCII/UTF-8 for test
            logText += "RX: $msg\n"
        }
        
        onDispose {
            usbHelper.disconnect()
        }
    }

    // Auto-scroll to bottom when log changes
    LaunchedEffect(logText) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    Column(modifier = Modifier.padding(16.dp).fillMaxSize()) {
        Text(text = "STM32 USB Serial Test", style = MaterialTheme.typography.titleLarge)
        
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Button(onClick = { usbHelper.connectDevice() }) {
                Text("Connect")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { usbHelper.disconnect() }) {
                Text("Disconnect")
            }
        }

        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                label = { Text("Command") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { 
                if (inputText.isNotEmpty()) {
                    usbHelper.sendData(inputText)
                    logText += "TX: $inputText\n"
                    inputText = ""
                }
            }) {
                Text("Send")
            }
        }

        // Log Area
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.LightGray.copy(alpha = 0.3f))
                .padding(4.dp)
        ) {
            Text(
                text = logText,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
