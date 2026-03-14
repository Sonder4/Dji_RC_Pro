package com.example.dji_rc_pro.util

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Ping工具类
 * 用于测试目标IP的连通性
 */
object PingUtil {

    private const val TAG = "PingUtil"
    private const val DEFAULT_PACKET_COUNT = 4
    private const val DEFAULT_TIMEOUT_SECONDS = 5

    enum class IpFamily(val label: String) {
        IPV4("IPv4"),
        IPV6("IPv6"),
    }

    /**
     * Ping结果数据类
     */
    data class PingResult(
        val ip: String,
        val family: IpFamily,
        val isSuccess: Boolean,
        val packetsSent: Int = 0,
        val packetsReceived: Int = 0,
        val packetLossPercent: Int = 0,
        val minTimeMs: Long = 0,
        val maxTimeMs: Long = 0,
        val avgTimeMs: Long = 0,
        val errorMessage: String = ""
    ) {
        /**
         * 获取格式化的ping结果字符串
         */
        fun formatResult(): String {
            return if (isSuccess) {
                buildString {
                    appendLine("Ping $ip:")
                    appendLine("地址族: ${family.label}")
                    appendLine("✓ 成功 ($packetsReceived/$packetsSent 包, $packetLossPercent% 丢失)")
                    if (packetsReceived > 0) {
                        appendLine("延迟: 最短=${minTimeMs}ms, 最长=${maxTimeMs}ms, 平均=${avgTimeMs}ms")
                    }
                }.trim()
            } else {
                "Ping $ip (${family.label}): ✗ 失败${if (errorMessage.isNotEmpty()) " - $errorMessage" else ""}"
            }
        }
    }

    fun inferFamily(ip: String): IpFamily = if (ip.contains(':')) IpFamily.IPV6 else IpFamily.IPV4

    fun buildCommand(
        ip: String,
        family: IpFamily,
        packetCount: Int = DEFAULT_PACKET_COUNT,
        timeoutSeconds: Int = DEFAULT_TIMEOUT_SECONDS
    ): List<String> {
        return when (family) {
            IpFamily.IPV4 -> listOf(
                "/system/bin/ping",
                "-4",
                "-c",
                packetCount.toString(),
                "-W",
                timeoutSeconds.toString(),
                ip
            )
            IpFamily.IPV6 -> listOf(
                "/system/bin/ping6",
                "-c",
                packetCount.toString(),
                "-W",
                timeoutSeconds.toString(),
                ip
            )
        }
    }

    /**
     * 执行ping命令
     *
     * @param ip 目标IP地址
     * @param packetCount 发送的包数量（默认4个）
     * @param timeoutSeconds 超时时间（秒）
     * @return PingResult ping结果
     */
    suspend fun ping(
        ip: String,
        family: IpFamily = inferFamily(ip),
        packetCount: Int = DEFAULT_PACKET_COUNT,
        timeoutSeconds: Int = DEFAULT_TIMEOUT_SECONDS
    ): PingResult = withContext(Dispatchers.IO) {
        try {
            val process = ProcessBuilder(buildCommand(ip, family, packetCount, timeoutSeconds))
                .redirectErrorStream(true)
                .start()

            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val output = StringBuilder()
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                output.appendLine(line)
                Log.d(TAG, line!!)
            }

            reader.close()
            process.waitFor()

            // 解析ping结果
            parsePingOutput(ip, family, output.toString())

        } catch (e: Exception) {
            Log.e(TAG, "Ping failed", e)
            PingResult(
                ip = ip,
                family = family,
                isSuccess = false,
                errorMessage = e.message ?: "未知错误"
            )
        }
    }

    /**
     * 快速ping - 只检查是否可达，不获取详细统计
     *
     * @param ip 目标IP地址
     * @param timeoutMs 超时时间（毫秒）
     * @return 是否可达
     */
    suspend fun isReachable(
        ip: String,
        family: IpFamily = inferFamily(ip),
        timeoutMs: Int = 3000
    ): Boolean = withContext(Dispatchers.IO) {
        try {
            val process = ProcessBuilder(
                buildCommand(
                    ip = ip,
                    family = family,
                    packetCount = 1,
                    timeoutSeconds = (timeoutMs / 1000).coerceAtLeast(1)
                )
            )
                .redirectErrorStream(true)
                .start()
            
            val result = process.waitFor()
            result == 0
        } catch (e: Exception) {
            Log.e(TAG, "Quick ping failed", e)
            false
        }
    }

    /**
     * 解析ping命令输出
     */
    private fun parsePingOutput(ip: String, family: IpFamily, output: String): PingResult {
        try {
            var packetsSent = 0
            var packetsReceived = 0
            var packetLossPercent = 0
            var minTime: Long = 0
            var maxTime: Long = 0
            var avgTime: Long = 0
            var isSuccess = false

            val lines = output.lines()

            // 解析丢包率行
            // 示例: 4 packets transmitted, 4 received, 0% packet loss, time 3005ms
            for (line in lines) {
                when {
                    line.contains("packets transmitted") -> {
                        val sentMatch = Regex("""(\d+) packets transmitted""").find(line)
                        val receivedMatch = Regex("""(\d+) received""").find(line)
                        val lossMatch = Regex("""(\d+)% packet loss""").find(line)

                        packetsSent = sentMatch?.groupValues?.get(1)?.toIntOrNull() ?: 0
                        packetsReceived = receivedMatch?.groupValues?.get(1)?.toIntOrNull() ?: 0
                        packetLossPercent = lossMatch?.groupValues?.get(1)?.toIntOrNull() ?: 0
                        isSuccess = packetsReceived > 0
                    }

                    // 解析延迟统计行
                    // 示例: rtt min/avg/max/mdev = 41.123/89.456/115.789/12.345 ms
                    line.contains("rtt min/avg/max") -> {
                        val timeMatch = Regex("""= ([\d.]+)/([\d.]+)/([\d.]+)""").find(line)
                        if (timeMatch != null) {
                            minTime = (timeMatch.groupValues[1].toDoubleOrNull() ?: 0.0).toLong()
                            avgTime = (timeMatch.groupValues[2].toDoubleOrNull() ?: 0.0).toLong()
                            maxTime = (timeMatch.groupValues[3].toDoubleOrNull() ?: 0.0).toLong()
                        }
                    }
                }
            }

            // 如果没有解析到延迟统计但有收到包，尝试从单个回复中解析
            if (minTime == 0L && packetsReceived > 0) {
                val timeMatches = Regex("""time=([\d.]+) ms""").findAll(output)
                val times = timeMatches.mapNotNull { it.groupValues[1].toDoubleOrNull()?.toLong() }.toList()
                if (times.isNotEmpty()) {
                    minTime = times.minOrNull() ?: 0
                    maxTime = times.maxOrNull() ?: 0
                    avgTime = times.average().toLong()
                }
            }

            return PingResult(
                ip = ip,
                family = family,
                isSuccess = isSuccess,
                packetsSent = packetsSent,
                packetsReceived = packetsReceived,
                packetLossPercent = packetLossPercent,
                minTimeMs = minTime,
                maxTimeMs = maxTime,
                avgTimeMs = avgTime
            )

        } catch (e: Exception) {
            Log.e(TAG, "Failed to parse ping output", e)
            return PingResult(
                ip = ip,
                family = family,
                isSuccess = false,
                errorMessage = "解析结果失败: ${e.message}"
            )
        }
    }
}
