package com.example.dji_rc_pro.util

import java.net.Inet4Address
import java.net.NetworkInterface

/**
 * Network utility functions
 */
object NetworkUtil {

    /**
     * Get the device's IPv4 address
     * Returns null if no suitable address found
     */
    fun getLocalIpAddress(): String? {
        return try {
            NetworkInterface.getNetworkInterfaces().toList()
                .flatMap { it.inetAddresses.toList() }
                .filterIsInstance<Inet4Address>()
                .filter { !it.isLoopbackAddress }
                .map { it.hostAddress }
                .firstOrNull { it != null && !it.startsWith("169.254") } // Filter out link-local addresses
        } catch (e: Exception) {
            LogUtil.e("Failed to get local IP address", e)
            null
        }
    }

    /**
     * Get all network interface information for debugging
     */
    fun getNetworkInfo(): String {
        return try {
            val sb = StringBuilder()
            NetworkInterface.getNetworkInterfaces().toList().forEach { iface ->
                val addresses = iface.inetAddresses.toList()
                    .filterIsInstance<Inet4Address>()
                    .filter { !it.isLoopbackAddress }
                    .map { it.hostAddress }
                if (addresses.isNotEmpty()) {
                    sb.append("${iface.name}: ${addresses.joinToString(", ")}\n")
                }
            }
            sb.toString().trim()
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}
