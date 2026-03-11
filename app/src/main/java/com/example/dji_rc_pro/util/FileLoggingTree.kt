package com.example.dji_rc_pro.util

import android.content.Context
import android.os.Environment
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

class FileLoggingTree(
    private val context: Context,
    private val logDirectory: String = "logs",
    private val maxFileSize: Long = 5 * 1024 * 1024,
    private val maxLogFiles: Int = 5
) : Timber.DebugTree() {

    private val logDir: File by lazy {
        File(context.filesDir, logDirectory).apply {
            if (!exists()) mkdirs()
        }
    }

    private val crashDir: File by lazy {
        File(context.filesDir, "crash_logs").apply {
            if (!exists()) mkdirs()
        }
    }

    private val writeQueue = ConcurrentLinkedQueue<LogEntry>()
    private val isWriting = AtomicBoolean(false)
    private var currentLogFile: File? = null
    private var currentFileSize = AtomicInteger(0)

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
    private val fileNameDateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())

    init {
        initializeCurrentLogFile()
    }

    private fun initializeCurrentLogFile() {
        val timestamp = fileNameDateFormat.format(Date())
        currentLogFile = File(logDir, "app_$timestamp.log")
        currentFileSize.set(0)
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, tag, message, t)
        val logEntry = LogEntry(
            timestamp = System.currentTimeMillis(),
            priority = priority,
            tag = tag ?: "",
            message = message,
            throwable = t
        )
        writeQueue.offer(logEntry)
        processQueue()
    }

    private fun processQueue() {
        if (!isWriting.compareAndSet(false, true)) return

        GlobalScope.launch(Dispatchers.IO) {
            try {
                var entry = writeQueue.poll()
                while (entry != null) {
                    writeLogEntry(entry)
                    entry = writeQueue.poll()
                }
            } finally {
                isWriting.set(false)
            }
        }
    }

    private fun writeLogEntry(entry: LogEntry) {
        val currentFile = currentLogFile ?: return

        if (currentFile.length() >= maxFileSize) {
            rotateLogFiles()
        }

        try {
            FileWriter(currentFile, true).use { writer ->
                val formattedMessage = formatLogEntry(entry)
                writer.appendLine(formattedMessage)
                currentFileSize.addAndGet(formattedMessage.toByteArray().size + 1)
            }
        } catch (e: IOException) {
            System.err.println("Failed to write log entry: ${e.message}")
        }
    }

    private fun formatLogEntry(entry: LogEntry): String {
        val dateStr = dateFormat.format(Date(entry.timestamp))
        val priorityChar = when (entry.priority) {
            Log.VERBOSE -> 'V'
            Log.DEBUG -> 'D'
            Log.INFO -> 'I'
            Log.WARN -> 'W'
            Log.ERROR -> 'E'
            Log.ASSERT -> 'A'
            else -> '?'
        }

        val sb = StringBuilder()
        sb.append("$dateStr $priorityChar/${entry.tag}: ${entry.message}")

        entry.throwable?.let { throwable ->
            sb.appendLine()
            sb.append(Log.getStackTraceString(throwable))
        }

        return sb.toString()
    }

    private fun rotateLogFiles() {
        val files = logDir.listFiles { file -> file.name.startsWith("app_") && file.name.endsWith(".log") }
            ?.sortedByDescending { it.lastModified() }
            ?: return

        if (files.size >= maxLogFiles) {
            files.drop(maxLogFiles - 1).forEach { file ->
                file.delete()
            }
        }

        initializeCurrentLogFile()
    }

    suspend fun getAllLogFiles(): List<File> = withContext(Dispatchers.IO) {
        logDir.listFiles { file ->
            file.name.startsWith("app_") && file.name.endsWith(".log")
        }?.sortedByDescending { it.lastModified() } ?: emptyList()
    }

    suspend fun getRecentLogFiles(days: Int = 1): List<File> = withContext(Dispatchers.IO) {
        val cutoffTime = System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000L)
        getAllLogFiles().filter { it.lastModified() > cutoffTime }
    }

    suspend fun getLogContent(): String = withContext(Dispatchers.IO) {
        val files = getAllLogFiles()
        if (files.isEmpty()) return@withContext ""

        val sb = StringBuilder()
        files.forEach { file ->
            try {
                file.readText().let { content ->
                    sb.append(content)
                    sb.appendLine()
                    sb.appendLine("--- Log file boundary: ${file.name} ---")
                    sb.appendLine()
                }
            } catch (e: IOException) {
                LogUtil.e("Failed to read log file: ${file.name}")
            }
        }
        sb.toString()
    }

    suspend fun getRecentLogContent(maxLines: Int = 1000): String = withContext(Dispatchers.IO) {
        val files = getAllLogFiles()
        if (files.isEmpty()) return@withContext ""

        val allLines = mutableListOf<String>()
        for (file in files.sortedByDescending { it.lastModified() }) {
            try {
                val lines = file.readLines()
                allLines.addAll(lines)
            } catch (e: IOException) {
                LogUtil.e("Failed to read log file: ${file.name}")
            }

            if (allLines.size >= maxLines) {
                break
            }
        }

        if (allLines.size > maxLines) {
            allLines.takeLast(maxLines).joinToString("\n")
        } else {
            allLines.joinToString("\n")
        }
    }

    suspend fun exportCrashLog(throwable: Throwable, additionalInfo: String? = null): File =
        withContext(Dispatchers.IO) {
            val timestamp = fileNameDateFormat.format(Date())
            val crashFile = File(crashDir, "crash_$timestamp.log")

            try {
                FileWriter(crashFile).use { writer ->
                    writer.appendLine("=== Crash Report ===")
                    writer.appendLine("Timestamp: ${dateFormat.format(Date())}")
                    writer.appendLine("App Version: ${getAppVersion()}")
                    writer.appendLine("Android Version: ${android.os.Build.VERSION.RELEASE}")
                    writer.appendLine("Device: ${android.os.Build.MODEL} (${android.os.Build.MANUFACTURER})")

                    additionalInfo?.let {
                        writer.appendLine()
                        writer.appendLine("=== Additional Info ===")
                        writer.appendLine(it)
                    }

                    writer.appendLine()
                    writer.appendLine("=== Stack Trace ===")
                    throwable.printStackTrace(java.io.PrintWriter(writer))

                    writer.appendLine()
                    writer.appendLine("=== End of Crash Report ===")
                }
                LogUtil.i("Crash log exported to: ${crashFile.absolutePath}")
                crashFile
            } catch (e: IOException) {
                LogUtil.e("Failed to export crash log: ${e.message}")
                throw e
            }
        }

    suspend fun deleteOldLogs(daysToKeep: Int = 7): Int = withContext(Dispatchers.IO) {
        val cutoffTime = System.currentTimeMillis() - (daysToKeep * 24 * 60 * 60 * 1000L)
        var deletedCount = 0

        logDir.listFiles()?.forEach { file ->
            if (file.lastModified() < cutoffTime && file.delete()) {
                deletedCount++
            }
        }

        crashDir.listFiles()?.forEach { file ->
            if (file.lastModified() < cutoffTime && file.delete()) {
                deletedCount++
            }
        }

        LogUtil.i("Deleted $deletedCount old log files")
        deletedCount
    }

    suspend fun clearAllLogs(): Boolean = withContext(Dispatchers.IO) {
        var success = true
        logDir.listFiles()?.forEach { file ->
            if (!file.delete()) {
                success = false
            }
        }
        crashDir.listFiles()?.forEach { file ->
            if (!file.delete()) {
                success = false
            }
        }
        initializeCurrentLogFile()
        success
    }

    fun getLogDirectory(): File = logDir

    fun getCrashDirectory(): File = crashDir

    fun getRecentLogs(maxLines: Int = 500): List<String> {
        val logs = mutableListOf<String>()
        try {
            val files = logDir.listFiles { file ->
                file.name.startsWith("app_") && file.name.endsWith(".log")
            }?.sortedByDescending { it.lastModified() } ?: return logs

            for (file in files) {
                if (logs.size >= maxLines) break
                file.readLines().forEach { line ->
                    if (logs.size < maxLines) {
                        logs.add(line)
                    }
                }
            }
        } catch (e: Exception) {
            System.err.println("Failed to read recent logs: ${e.message}")
        }
        return logs
    }

    private fun getAppVersion(): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            "${packageInfo.versionName} (${packageInfo.versionCode})"
        } catch (e: Exception) {
            "Unknown"
        }
    }

    data class LogEntry(
        val timestamp: Long,
        val priority: Int,
        val tag: String,
        val message: String,
        val throwable: Throwable?
    )
}
