package com.example.dji_rc_pro.util

import java.net.Inet4Address
import java.net.Inet6Address
import java.net.InetAddress

/**
 * UDP endpoint normalization and validation helpers.
 */
object UdpEndpointConfig {
    data class ValidatedConfig(
        val targetAddress: String,
        val resolvedAddress: InetAddress,
        val targetPort: Int,
        val localPort: Int
    )

    fun normalizeTargetAddress(raw: String): String {
        val trimmed = raw.trim()
        if (trimmed.isEmpty()) {
            return ""
        }

        return if (trimmed.startsWith("[") && trimmed.endsWith("]") && trimmed.length > 2) {
            trimmed.substring(1, trimmed.length - 1)
        } else {
            trimmed
        }
    }

    fun parsePort(raw: String): Int? {
        val port = raw.trim().toIntOrNull() ?: return null
        return port.takeIf { it in 1..65535 }
    }

    fun resolveAddress(targetAddress: String): InetAddress {
        val normalized = normalizeTargetAddress(targetAddress)
        require(normalized.isNotEmpty()) { "目标地址不能为空" }
        return InetAddress.getByName(normalized)
    }

    fun validate(targetAddress: String, targetPort: String, localPort: String): Result<ValidatedConfig> {
        return runCatching {
            val normalizedTargetAddress = normalizeTargetAddress(targetAddress)
            require(normalizedTargetAddress.isNotEmpty()) { "目标地址不能为空" }

            val parsedTargetPort = parsePort(targetPort)
                ?: throw IllegalArgumentException("目标端口必须在 1-65535 之间")
            val parsedLocalPort = parsePort(localPort)
                ?: throw IllegalArgumentException("本地端口必须在 1-65535 之间")

            ValidatedConfig(
                targetAddress = normalizedTargetAddress,
                resolvedAddress = resolveAddress(normalizedTargetAddress),
                targetPort = parsedTargetPort,
                localPort = parsedLocalPort
            )
        }
    }

    fun getWildcardBindAddress(remoteAddress: InetAddress): InetAddress {
        return when (remoteAddress) {
            is Inet6Address -> InetAddress.getByName("::")
            is Inet4Address -> InetAddress.getByName("0.0.0.0")
            else -> throw IllegalArgumentException("不支持的地址类型: ${remoteAddress.javaClass.simpleName}")
        }
    }
}
