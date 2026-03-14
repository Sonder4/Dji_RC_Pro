package com.example.dji_rc_pro.util

import java.net.Inet4Address
import java.net.Inet6Address
import java.net.NetworkInterface

/**
 * Network utility functions
 */
object NetworkUtil {

    data class AddressSnapshot(
        val ipv4: String? = null,
        val ipv6: String? = null
    )

    /**
     * Get the device's preferred local address for display.
     * Prefers non-link-local IPv4 first, then global IPv6.
     */
    fun getLocalIpAddress(): String? {
        return getAllLocalAddresses().firstOrNull()
    }

    fun getAllLocalAddresses(): List<String> {
        return try {
            NetworkInterface.getNetworkInterfaces().toList()
                .flatMap { iface ->
                    iface.inetAddresses.toList()
                        .filter { !it.isLoopbackAddress }
                        .mapNotNull { address ->
                            when (address) {
                                is Inet4Address -> address.hostAddress?.takeIf { !it.startsWith("169.254") }
                                is Inet6Address -> address.hostAddress
                                    ?.substringBefore('%')
                                    ?.takeIf { !address.isLinkLocalAddress }
                                else -> null
                            }
                        }
                }
        } catch (e: Exception) {
            LogUtil.e("Failed to get local IP address", e)
            emptyList()
        }
    }

    /**
     * Get all network interface information for debugging.
     */
    fun getNetworkInfo(): String {
        return try {
            val sb = StringBuilder()
            NetworkInterface.getNetworkInterfaces().toList().forEach { iface ->
                val addresses = iface.inetAddresses.toList()
                    .filter { !it.isLoopbackAddress }
                    .mapNotNull { address ->
                        when (address) {
                            is Inet4Address -> address.hostAddress?.takeIf { !it.startsWith("169.254") }
                            is Inet6Address -> address.hostAddress?.substringBefore('%')
                            else -> null
                        }
                    }
                if (addresses.isNotEmpty()) {
                    sb.append("${iface.name}: ${addresses.joinToString(", ")}\n")
                }
            }
            sb.toString().trim().ifEmpty { "No active network addresses" }
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }

    fun getAddressSnapshot(): AddressSnapshot {
        return try {
            var ipv4: String? = null
            var ipv6: String? = null
            NetworkInterface.getNetworkInterfaces().toList().forEach { iface ->
                iface.inetAddresses.toList()
                    .filter { !it.isLoopbackAddress }
                    .forEach { address ->
                            when (address) {
                                is Inet4Address -> {
                                    val hostAddress = address.hostAddress
                                    if (ipv4 == null && hostAddress != null && !hostAddress.startsWith("169.254")) {
                                        ipv4 = hostAddress
                                    }
                                }
                                is Inet6Address -> {
                                    if (ipv6 == null && !address.isLinkLocalAddress) {
                                    ipv6 = address.hostAddress?.substringBefore('%')
                                }
                            }
                        }
                    }
            }
            AddressSnapshot(ipv4 = ipv4, ipv6 = ipv6)
        } catch (e: Exception) {
            LogUtil.e("Failed to get local address snapshot", e)
            AddressSnapshot()
        }
    }
}
