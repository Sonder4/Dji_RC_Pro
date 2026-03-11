package com.example.dji_rc_pro.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.StatFs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.system.exitProcess

object CrashReporter {
    private const val CRASH_LOG_DIR = "crash_logs"
    private const val CRASH_REPORT_FILE = "crash_report.txt"
    private const val MAX_CACHED_LOGS = 100

    private var previousHandler: Thread.UncaughtExceptionHandler? = null
    private val cachedLogs = java.util.Collections.synchronizedList(mutableListOf<CrashInfo>())
    private var crashCallback: ((CrashInfo) -> Unit)? = null
    private var fileLoggingTree: FileLoggingTree? = null
    private val isInitialized = java.util.concurrent.atomic.AtomicBoolean(false)
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())

    fun init(context: Context, loggingTree: FileLoggingTree? = null) {
        if (!isInitialized.compareAndSet(false, true)) {
            LogUtil.w("CrashReporter already initialized")
            return
        }

        fileLoggingTree = loggingTree
        previousHandler = Thread.getDefaultUncaughtExceptionHandler()

        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            handleCrash(thread, throwable, context)
            previousHandler?.uncaughtException(thread, throwable)
            exitProcess(1)
        }

        File(context.filesDir, CRASH_LOG_DIR).mkdirs()
        LogUtil.i("CrashReporter initialized")
    }

    private fun handleCrash(thread: Thread, throwable: Throwable, context: Context) {
        try {
            val crashInfo = collectCrashInfo(thread, throwable, context)
            cacheCrashInfo(crashInfo)

            crashCallback?.invoke(crashInfo)

            fileLoggingTree?.let { tree ->
                GlobalScope.launch(Dispatchers.IO) {
                    tree.exportCrashLog(throwable, buildString {
                        appendLine("Thread: ${thread.name}")
                        appendLine("Crash Info: ${crashInfo.summary}")
                    })
                }
            }

            saveCrashReport(crashInfo, context)

            LogUtil.e(throwable, "CRASH: ${crashInfo.summary}")
        } catch (e: Exception) {
            System.err.println("Error handling crash: ${e.message}")
        }
    }

    private fun collectCrashInfo(thread: Thread, throwable: Throwable, context: Context): CrashInfo {
        val timestamp = System.currentTimeMillis()

        val deviceInfo = buildDeviceInfo(context)

        val stackTrace = StringWriter()
        throwable.printStackTrace(PrintWriter(stackTrace))

        val rootCause = findRootCause(throwable)

        val threadInfo = buildThreadInfo(thread)

        val memoryInfo = collectMemoryInfo()

        val summary = buildSummary(throwable, rootCause)

        return CrashInfo(
            id = generateCrashId(timestamp),
            timestamp = timestamp,
            summary = summary,
            rootCause = rootCause,
            stackTrace = stackTrace.toString(),
            threadInfo = threadInfo,
            deviceInfo = deviceInfo,
            memoryInfo = memoryInfo,
            threadName = thread.name
        )
    }

    private fun buildDeviceInfo(context: Context): DeviceInfo {
        val packageInfo = try {
            context.packageManager.getPackageInfo(context.packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }

        val statFs = StatFs(context.filesDir.path)
        val totalMemory = statFs.totalBytes
        val availableMemory = statFs.availableBytes

        return DeviceInfo(
            appVersion = packageInfo?.versionName ?: "Unknown",
            appVersionCode = packageInfo?.versionCode?.toString() ?: "Unknown",
            androidVersion = Build.VERSION.RELEASE,
            sdkVersion = Build.VERSION.SDK_INT.toString(),
            deviceModel = Build.MODEL,
            deviceManufacturer = Build.MANUFACTURER,
            deviceBrand = Build.BRAND,
            cpuAbi = Build.SUPPORTED_ABIS.joinToString(", "),
            totalRam = totalMemory,
            availableRam = availableMemory,
            totalStorage = totalMemory,
            availableStorage = availableMemory,
            isRooted = isDeviceRooted()
        )
    }

    private fun buildThreadInfo(thread: Thread): ThreadInfo {
        val stackElements = thread.stackTrace
        val formattedStack = stackElements.joinToString("\n") { "    at $it" }

        return ThreadInfo(
            name = thread.name,
            id = thread.id,
            priority = thread.priority,
            state = thread.state.name,
            stackTrace = formattedStack
        )
    }

    private fun collectMemoryInfo(): MemoryInfo {
        val runtime = Runtime.getRuntime()
        val usedMemory = runtime.totalMemory() - runtime.freeMemory()
        val maxMemory = runtime.maxMemory()

        return MemoryInfo(
            usedMemory = usedMemory,
            freeMemory = runtime.freeMemory(),
            maxMemory = maxMemory,
            totalMemory = runtime.totalMemory(),
            gcCount = 0,
            gcTime = 0
        )
    }

    private fun findRootCause(throwable: Throwable): String {
        var current = throwable
        while (current.cause != null) {
            current = current.cause!!
        }
        return "${current.javaClass.simpleName}: ${current.message ?: "No message"}"
    }

    private fun buildSummary(throwable: Throwable, rootCause: String): String {
        return "${throwable.javaClass.simpleName} - $rootCause"
    }

    private fun generateCrashId(timestamp: Long): String {
        return "crash_${timestamp}_${(1000..9999).random()}"
    }

    private fun isDeviceRooted(): Boolean {
        val rootPaths = arrayOf(
            "/system/bin/su", "/system/xbin/su", "/sbin/su",
            "/system/app/Superuser.apk", "/system/app/SuperSU.apk",
            "/system/app/Superuser.apk", "/system/bin/failsafe/su"
        )
        return rootPaths.any { File(it).exists() } ||
                System.getenv("PATH")?.split(":")?.any { path ->
                    File(path, "su").exists()
                } == true
    }

    private fun cacheCrashInfo(crashInfo: CrashInfo) {
        cachedLogs.add(crashInfo)

        while (cachedLogs.size > MAX_CACHED_LOGS) {
            cachedLogs.removeAt(0)
        }
    }

    private fun saveCrashReport(crashInfo: CrashInfo, context: Context) {
        try {
            val reportFile = File(context.filesDir, CRASH_REPORT_FILE)
            reportFile.appendText(buildCrashReportContent(crashInfo))
        } catch (e: Exception) {
            LogUtil.e("Failed to save crash report: ${e.message}")
        }
    }

    private fun buildCrashReportContent(crashInfo: CrashInfo): String {
        return buildString {
            appendLine("=" .repeat(60))
            appendLine("CRASH REPORT")
            appendLine("=".repeat(60))
            appendLine()
            appendLine("Crash ID: ${crashInfo.id}")
            appendLine("Timestamp: ${dateFormat.format(Date(crashInfo.timestamp))}")
            appendLine()
            appendLine("-".repeat(60))
            appendLine("SUMMARY")
            appendLine("-".repeat(60))
            appendLine(crashInfo.summary)
            appendLine()
            appendLine("-".repeat(60))
            appendLine("ROOT CAUSE")
            appendLine("-".repeat(60))
            appendLine(crashInfo.rootCause)
            appendLine()
            appendLine("-".repeat(60))
            appendLine("STACK TRACE")
            appendLine("-".repeat(60))
            appendLine(crashInfo.stackTrace)
            appendLine()
            appendLine("-".repeat(60))
            appendLine("THREAD INFO")
            appendLine("-".repeat(60))
            appendLine("Thread: ${crashInfo.threadInfo.name} (ID: ${crashInfo.threadInfo.id})")
            appendLine("Priority: ${crashInfo.threadInfo.priority}")
            appendLine("State: ${crashInfo.threadInfo.state}")
            appendLine()
            appendLine("-".repeat(60))
            appendLine("DEVICE INFO")
            appendLine("-".repeat(60))
            appendLine("App Version: ${crashInfo.deviceInfo.appVersion} (${crashInfo.deviceInfo.appVersionCode})")
            appendLine("Android: ${crashInfo.deviceInfo.androidVersion} (SDK ${crashInfo.deviceInfo.sdkVersion})")
            appendLine("Device: ${crashInfo.deviceInfo.deviceModel} (${crashInfo.deviceInfo.deviceManufacturer})")
            appendLine("Brand: ${crashInfo.deviceInfo.deviceBrand}")
            appendLine("Rooted: ${crashInfo.deviceInfo.isRooted}")
            appendLine()
            appendLine("-".repeat(60))
            appendLine("MEMORY INFO")
            appendLine("-".repeat(60))
            appendLine("Used: ${formatBytes(crashInfo.memoryInfo.usedMemory)}")
            appendLine("Free: ${formatBytes(crashInfo.memoryInfo.freeMemory)}")
            appendLine("Max: ${formatBytes(crashInfo.memoryInfo.maxMemory)}")
            appendLine("Total: ${formatBytes(crashInfo.memoryInfo.totalMemory)}")
            appendLine()
            appendLine("=".repeat(60))
            appendLine("END OF CRASH REPORT")
            appendLine("=".repeat(60))
            appendLine()
        }
    }

    private fun formatBytes(bytes: Long): String {
        return when {
            bytes < 1024 -> "$bytes B"
            bytes < 1024 * 1024 -> "${bytes / 1024} KB"
            bytes < 1024 * 1024 * 1024 -> "${bytes / (1024 * 1024)} MB"
            else -> "${bytes / (1024 * 1024 * 1024)} GB"
        }
    }

    fun setCrashCallback(callback: (CrashInfo) -> Unit) {
        crashCallback = callback
    }

    fun getCachedCrashLogs(): List<CrashInfo> {
        return cachedLogs.toList()
    }

    fun getLatestCrash(): CrashInfo? {
        return cachedLogs.lastOrNull()
    }

    suspend fun exportAllCrashes(context: Context): File = withContext(Dispatchers.IO) {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val exportFile = File(context.cacheDir, "crash_export_$timestamp.txt")

        try {
            PrintWriter(exportFile).use { writer ->
                cachedLogs.forEach { crashInfo ->
                    writer.write(buildCrashReportContent(crashInfo))
                    writer.write("\n\n")
                }
            }
            LogUtil.i("All crashes exported to: ${exportFile.absolutePath}")
            exportFile
        } catch (e: Exception) {
            LogUtil.e("Failed to export crashes: ${e.message}")
            throw e
        }
    }

    fun clearCache() {
        cachedLogs.clear()
    }

    fun shareCrashReport(context: Context, crashInfo: CrashInfo? = getLatestCrash()): Intent? {
        if (crashInfo == null) {
            LogUtil.w("No crash info to share")
            return null
        }

        val content = buildCrashReportContent(crashInfo)

        return Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Crash Report - ${crashInfo.id}")
            putExtra(Intent.EXTRA_TEXT, content)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
    }

    fun uncaughtException(thread: Thread, throwable: Throwable) {
        previousHandler?.uncaughtException(thread, throwable)
    }

    data class CrashInfo(
        val id: String,
        val timestamp: Long,
        val summary: String,
        val rootCause: String,
        val stackTrace: String,
        val threadInfo: ThreadInfo,
        val deviceInfo: DeviceInfo,
        val memoryInfo: MemoryInfo,
        val threadName: String
    )

    data class ThreadInfo(
        val name: String,
        val id: Long,
        val priority: Int,
        val state: String,
        val stackTrace: String
    )

    data class DeviceInfo(
        val appVersion: String,
        val appVersionCode: String,
        val androidVersion: String,
        val sdkVersion: String,
        val deviceModel: String,
        val deviceManufacturer: String,
        val deviceBrand: String,
        val cpuAbi: String,
        val totalRam: Long,
        val availableRam: Long,
        val totalStorage: Long,
        val availableStorage: Long,
        val isRooted: Boolean
    )

    data class MemoryInfo(
        val usedMemory: Long,
        val freeMemory: Long,
        val maxMemory: Long,
        val totalMemory: Long,
        val gcCount: Int,
        val gcTime: Long
    )
}
