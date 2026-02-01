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

    /**
     * Ping结果数据类
     */
    data class PingResult(
        val ip: String,
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
                    appendLine("✓ 成功 ($packetsReceived/$packetsSent 包, $packetLossPercent% 丢失)")
                    if (packetsReceived > 0) {
                        appendLine("延迟: 最短=${minTimeMs}ms, 最长=${maxTimeMs}ms, 平均=${avgTimeMs}ms")
                    }
                }.trim()
            } else {
                "Ping $ip: ✗ 失败${if (errorMessage.isNotEmpty()) " - $errorMessage" else ""}"
            }
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
        packetCount: Int = DEFAULT_PACKET_COUNT,
        timeoutSeconds: Int = DEFAULT_TIMEOUT_SECONDS
    ): PingResult = withContext(Dispatchers.IO) {
        try {
            // 构建ping命令
            // Android使用 -c 参数指定包数量，-W 指定超时（秒）
            val process = ProcessBuilder("/system/bin/ping", "-c", packetCount.toString(), "-W", timeoutSeconds.toString(), ip)
                .redirectErrorStream(true)
                .start()

            val reader = BufferedReader(InputStreamReader(process.inputStream))
            val output = StringBuilder()
            var line: String?

            while (reader.readLine().also { line