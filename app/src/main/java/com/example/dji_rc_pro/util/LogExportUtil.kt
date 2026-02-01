package com.example.dji_rc_pro.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.dji_rc_pro.util.CrashReporter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.*

object LogExportUtil {
    private const val LOG_DIR = "logs"
    private const val CRASH_DIR = "crashes"
    private const val MAX_LOG_FILES = 10
    private const val MAX_CRASH_FILES = 20
    private const val TAG = "LogExportUtil"

    @Volatile
    private var fileLoggingTree: FileLoggingTree? = null

    fun init(context: Context) {
        fileLoggingTree = FileLoggingTree(context)
    }

    fun getLogDirectory(context: Context): File {
        val dir = File(context.filesDir, LOG_DIR)
        if (!dir.exists()) dir.mkdirs()
        return dir
    }

    fun getCrashDirectory(context: Context): File {
        val dir = File(context.filesDir, CRASH_DIR)
        if (!dir.exists()) dir.mkdirs()
        return dir
    }

    suspend fun exportCurrentLogs(context: Context): Result<File> = withContext(Dispatchers.IO) {
        try {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
            val fileName = "app_log_$timestamp.txt"
            val logFile = File(getLogDirectory(context), fileName)

            PrintWriter(FileWriter(logFile)).use { writer ->
                writer.println("=== Application Log Export ===")
                writer.println("Generated: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date())}")
                writer.println("Device: ${Build.MANUFACTURER} ${Build.MODEL}")
                writer.println("Android: ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})")
                writer.println("================================")
                writer.println()

                val logcatLines = collectLogcatLines(1000)
                logcatLines.forEach { writer.println(it) }

                writer.println()
                writer.println("=== Custom App Logs ===")
                val customLogs = fileLoggingTree?.getRecentLogs(500) ?: emptyList()
                customLogs.forEach { writer.println(it) }
            }

            cleanupOldLogs(context)
            Result.success(logFile)
        } catch (e: Exception) {
            LogUtil.e(e, "LogExportUtil exportCurrentLogs failed")
            Result.failure(e)
        }
    }

    suspend fun exportLogsForDate(context: Context, date: Date): Result<File> = withContext(Dispatchers.IO) {
        try {
            val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.US)
            val fileName = "app_log_${dateFormat.format(date)}.txt"
            val logFile = File(getLogDirectory(context), fileName)

            if (logFile.exists()) {
                Result.success(logFile)
            } else {
                Result.failure(Exception("Log file not found for date: $dateFormat"))
            }
        } catch (e: Exception) {
            LogUtil.e(e, "LogExportUtil exportLogsForDate failed")
            Result.failure(e)
        }
    }

    suspend fun exportCrashReport(context: Context, crashInfo: CrashReporter.CrashInfo): Result<File> = withContext(Dispatchers.IO) {
        try {
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date(crashInfo.timestamp))
            val fileName = "crash_report_$timestamp.txt"
            val crashFile = File(getCrashDirectory(context), fileName)

            PrintWriter(FileWriter(crashFile)).use { writer ->
                writer.println("=== Crash Report ===")
                writer.println("Time: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date(crashInfo.timestamp))}")
                writer.println("Device: ${Build.MANUFACTURER} ${Build.MODEL}")
                writer.println("Android: ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})")
                writer.println("App Version: ${getAppVersion(context)}")
                writer.println("================================")
                writer.println()

                writer.println("=== Exception Info ===")
                writer.println("Summary: ${crashInfo.summary}")
                writer.println("Root Cause: ${crashInfo.rootCause}")
                writer.println()

                writer.println("=== Stack Trace ===")
                writer.println(crashInfo.stackTrace)
                writer.println()

                writer.println("=== Thread Info ===")
                writer.println("Thread Name: ${crashInfo.threadInfo.name}")
                writer.println("Thread Priority: ${crashInfo.threadInfo.priority}")
                writer.println("Thread State: ${crashInfo.threadInfo.state}")
                writer.println()

                writer.println("=== Memory Info ===")
                writer.println("Used: ${formatBytes(crashInfo.memoryInfo.usedMemory)}")
                writer.println("Free: ${formatBytes(crashInfo.memoryInfo.freeMemory)}")
                writer.println("Max: ${formatBytes(crashInfo.memoryInfo.maxMemory)}")
                writer.println("Total: ${formatBytes(crashInfo.memoryInfo.totalMemory)}")
                writer.println()

                writer.println("=== Device Info ===")
                writer.println("App Version: ${crashInfo.deviceInfo.appVersion}")
                writer.println("Android: ${crashInfo.deviceInfo.androidVersion} (SDK ${crashInfo.deviceInfo.sdkVersion})")
                writer.println("Device: ${crashInfo.deviceInfo.deviceModel} (${crashInfo.deviceInfo.deviceManufacturer})")
                writer.println()

                writer.println("=== Logcat (Last 50 lines) ===")
                val logcatLines = collectLogcatLines(50)
                logcatLines.forEach { writer.println(it) }
            }

            cleanupOldCrashes(context)
            Result.success(crashFile)
        } catch (e: Exception) {
            LogUtil.e(e, "LogExportUtil exportCrashReport failed")
            Result.failure(e)
        }
    }

    fun shareFile(context: Context, file: File, title: String = "Share") {
        try {
            val uri = getFileUri(context, file)
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(Intent.createChooser(intent, title).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        } catch (e: Exception) {
            LogUtil.e(e, "LogExportUtil shareFile failed")
            Toast.makeText(context, "Share failed: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    fun copyToClipboard(context: Context, text: String, label: String = "Copied to clipboard") {
        try {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText(label, text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, label, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            LogUtil.e(e, "LogExportUtil copyToClipboard failed")
        }
    }

    fun getLogFiles(context: Context): List<FileInfo> {
        val logDir = getLogDirectory(context)
        return logDir.listFiles()
            ?.filter { it.name.startsWith("app_log_") && it.name.endsWith(".txt") }
            ?.map { file ->
                FileInfo(
                    name = file.name,
                    path = file.absolutePath,
                    size = file.length(),
                    lastModified = file.lastModified()
                )
            }
            ?.sortedByDescending { it.lastModified }
            ?: emptyList()
    }

    fun getCrashFiles(context: Context): List<FileInfo> {
        val crashDir = getCrashDirectory(context)
        return crashDir.listFiles()
            ?.filter { it.name.startsWith("crash_report_") && it.name.endsWith(".txt") }
            ?.map { file ->
                FileInfo(
                    name = file.name,
                    path = file.absolutePath,
                    size = file.length(),
                    lastModified = file.lastModified()
                )
            }
            ?.sortedByDescending { it.lastModified }
            ?: emptyList()
    }

    suspend fun deleteLogFile(context: Context, fileName: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val logDir = getLogDirectory(context)
            val file = File(logDir, fileName)
            if (file.exists() && file.name.startsWith("app_log_")) {
                file.delete()
                true
            } else {
                false
            }
        } catch (e: Exception) {
            LogUtil.e(e, "LogExportUtil deleteLogFile failed")
            false
        }
    }

    suspend fun deleteAllLogs(context: Context): Boolean = withContext(Dispatchers.IO) {
        try {
            val logDir = getLogDirectory(context)
            logDir.listFiles()
                ?.filter { it.name.startsWith("app_log_") }
                ?.forEach { it.delete() }
            true
        } catch (e: Exception) {
            LogUtil.e(e, "LogExportUtil deleteAllLogs failed")
            false
        }
    }

    fun getTotalLogSize(context: Context): Long {
        val logDir = getLogDirectory(context)
        return logDir.listFiles()
            ?.filter { it.name.startsWith("app_log_") }
            ?.sumOf { it.length() }
            ?: 0L
    }

    fun getTotalCrashSize(context: Context): Long {
        val crashDir = getCrashDirectory(context)
        return crashDir.listFiles()
            ?.filter { it.name.startsWith("crash_report_") }
            ?.sumOf { it.length() }
            ?: 0L
    }

    private fun cleanupOldLogs(context: Context) {
        try {
            val logDir = getLogDirectory(context)
            val logFiles = logDir.listFiles()
                ?.filter { it.name.startsWith("app_log_") && it.name.endsWith(".txt") }
                ?.sortedByDescending { it.lastModified() }
                ?: return

            if (logFiles.size > MAX_LOG_FILES) {
                logFiles.drop(MAX_LOG_FILES).forEach { it.delete() }
            }
        } catch (e: Exception) {
            LogUtil.e(e, "LogExportUtil cleanupOldLogs failed")
        }
    }

    private fun cleanupOldCrashes(context: Context) {
        try {
            val crashDir = getCrashDirectory(context)
            val crashFiles = crashDir.listFiles()
                ?.filter { it.name.startsWith("crash_report_") && it.name.endsWith(".txt") }
                ?.sortedByDescending { it.lastModified() }
                ?: return

            if (crashFiles.size > MAX_CRASH_FILES) {
                crashFiles.drop(MAX_CRASH_FILES).forEach { it.delete() }
            }
        } catch (e: Exception) {
            LogUtil.e(e, "LogExportUtil cleanupOldCrashes failed")
        }
    }

    private fun getFileUri(context: Context, file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
    }

    private fun collectLogcatLines(maxLines: Int): List<String> {
        return try {
            val process = Runtime.getRuntime().exec(arrayOf("logcat", "-d", "-t", maxLines.toString()))
            BufferedReader(process.inputStream.reader()).use { reader ->
                reader.readLines()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun getAppVersion(context: Context): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName ?: "Unknown"
        } catch (e: Exception) {
            "Unknown"
        }
    }

    private fun formatBytes(bytes: Long): String {
        return when {
            bytes >= 1024 * 1024 -> String.format("%.2f MB", bytes / (1024.0 * 1024.0))
            bytes >= 1024 -> String.format("%.2f KB", bytes / 1024.0)
            else -> "$bytes B"
        }
    }

    data class FileInfo(
        val name: String,
        val path: String,
        val size: Long,
        val lastModified: Long
    ) {
        fun getFormattedSize(): String {
            return when {
                size >= 1024 * 1024 -> String.format("%.2f MB", size / (1024.0 * 1024.0))
                size >= 1024 -> String.format("%.2f KB", size / 1024.0)
                else -> "$size B"
            }
        }

        fun getFormattedDate(): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
            return sdf.format(Date(lastModified))
        }
    }
}
