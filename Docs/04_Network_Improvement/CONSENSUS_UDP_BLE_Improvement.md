# CONSENSUS_UDP_BLE_Improvement.md

## 需求确认与技术决策

### 需求确认总结

基于ALIGNMENT文档的分析和用户反馈，以下需求已明确并获得确认：

| 需求类别 | 具体内容 | 优先级 | 确认状态 |
|---------|---------|--------|---------|
| 连接监控 | UDP连接状态实时监控 | 高 | 已确认 |
| 连接监控 | BLE连接状态实时监控 | 高 | 已确认 |
| 错误处理 | 详细错误分类和用户反馈 | 高 | 已确认 |
| 自动重连 | 心跳检测和断线重连 | 高 | 已确认 |
| 日志系统 | 完整日志记录和导出 | 高 | 已确认 |
| 数据频率 | 100Hz基础频率，可配置10-200Hz | 高 | 已确认（用户更新） |
| 非阻塞设计 | 确保无阻塞操作 | 高 | 已确认（用户更新） |
| 崩溃捕获 | 全局异常捕获和报告 | 高 | 已确认（用户更新） |
| UI反馈 | 状态面板和错误对话框 | 中 | 已确认 |
| BLE优化 | 写入队列管理 | 中 | 已确认 |

### 技术决策确认

以下技术决策已基于项目现有架构和团队知识做出：

**决策1：日志系统采用Timber + 自定义FileTree**
- 理由：与现有Log API兼容，支持分级控制，自定义FileTree可实现日志文件轮转
- 依赖：implementation 'com.jakewharton.timber:timber:5.0.1'
- 变更：文件写入采用完全异步模式，不阻塞任何线程

**决策2：用户反馈采用增强型状态面板 + 错误对话框**
- 理由：提供最清晰的问题描述和解决建议，用户体验最佳
- 实现：在MainScreen顶部添加状态区域，连接失败时弹出AlertDialog

**决策3：连接管理采用统一ConnectionManager代理**
- 理由：解耦具体通信协议和状态管理，便于扩展和维护
- 位置：domain/connection/ConnectionManager.kt

**决策4：错误代码采用枚举类统一管理**
- 理由：便于扩展和本地化，与现有错误处理风格一致
- 位置：domain/error/ConnectionError.kt

**决策5：数据频率采用可配置模式**
- 理由：满足用户100Hz基础频率和可配置需求
- 实现：DataStore存储频率设置，Service层动态读取
- 默认值：100Hz
- 范围：10-200Hz

**决策6：崩溃捕获采用自定义全局处理器**
- 理由：满足用户对崩溃信息的需求，不依赖第三方服务
- 实现：Thread.setDefaultUncaughtExceptionHandler
- 内容：堆栈跟踪、设备信息、应用状态、最后100条日志

## 技术实现计划

### 整体架构设计

```
┌─────────────────────────────────────────────────────────────┐
│                      MainScreen (UI层)                       │
│  ┌─────────────────┐  ┌─────────────────────────────────┐  │
│  │   状态面板区域   │  │         错误对话框              │  │
│  │ (UDP/BLE状态)   │  │ (连接失败时自动弹出)            │  │
│  │ 频率设置入口    │  │         崩溃报告对话框          │  │
│  └─────────────────┘  └─────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                    MainViewModel (ViewModel层)               │
│  ┌──────────────┐  ┌────────────────────────────────────┐  │
│  │ Toast消息    │  │  连接状态流 (UDP/BLE)              │  │
│  │ 管理         │  │  错误状态流                         │  │
│  │ 频率配置     │  │  崩溃报告状态                       │  │
│  └──────────────┘  └────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                   ConnectionManager (新增)                   │
│  ┌──────────────────────────────────────────────────────┐  │
│  │ • 统一连接状态管理                                     │  │
│  │ • 错误分类和路由                                       │  │
│  │ • 心跳检测调度                                         │  │
│  │ • 自动重连逻辑                                         │  │
│  │ • 频率配置管理                                         │  │
│  └──────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
          │                           │
          ▼                           ▼
┌─────────────────────┐   ┌─────────────────────────────┐
│    UdpService       │   │       BleManager            │
│  (增强版, 100Hz)    │   │       (增强版)               │
│  • 错误回调注册     │   │  • 错误回调注册              │
│  • 心跳支持         │   │  • 写入队列(无锁)            │
│  • 状态上报         │   │  • 连接参数优化              │
│  • 动态频率         │   │  • 状态上报                  │
└─────────────────────┘   └─────────────────────────────┘
          │
          ▼
┌─────────────────────────────────────────────────────────────┐
│                   CrashReporter (新增)                       │
│  ┌──────────────────────────────────────────────────────┐  │
│  │ • 全局异常捕获                                        │  │
│  │ • 日志收集                                            │  │
│  │ • 报告生成                                            │  │
│  └──────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

### 核心模块设计

#### 1. 错误代码枚举 (ConnectionError.kt)

```kotlin
sealed class ConnectionError(
    val code: Int,
    val messageResId: Int,
    val suggestionResId: Int
) {
    // UDP错误
    data object UdpTargetUnreachable : ConnectionError(
        code = 1001,
        messageResId = R.string.error_udp_unreachable,
        suggestionResId = R.string.error_udp_unreachable_suggestion
    )
    
    data object UdpPortClosed : ConnectionError(
        code = 1002,
        messageResId = R.string.error_udp_port_closed,
        suggestionResId = R.string.error_udp_port_closed_suggestion
    )
    
    // BLE错误
    data object BleScanFailed : ConnectionError(
        code = 2001,
        messageResId = R.string.error_ble_scan_failed,
        suggestionResId = R.string.error_ble_scan_failed_suggestion
    )
    
    data object BleConnectionFailed : ConnectionError(
        code = 2002,
        messageResId = R.string.error_ble_connection_failed,
        suggestionResId = R.string.error_ble_connection_failed_suggestion
    )
    
    data object BleServiceNotFound : ConnectionError(
        code = 2003,
        messageResId = R.string.error_ble_service_not_found,
        suggestionResId = R.string.error_ble_service_not_found_suggestion
    )
}
```

#### 2. 连接状态数据类

```kotlin
enum class ConnectionState {
    DISCONNECTED,    // 未连接
    CONNECTING,      // 连接中
    CONNECTED,       // 已连接
    RECONNECTING,    // 断线重连中
    ERROR,           // 错误状态
    HEARTBEAT_FAILED // 心跳超时
}

enum class ConnectionType {
    UDP,
    BLE
}

data class ConnectionStatus(
    val type: ConnectionType,
    val state: ConnectionState,
    val error: ConnectionError? = null,
    val timestamp: Long = System.currentTimeMillis(),
    val stats: ConnectionStats = ConnectionStats()
)

data class ConnectionStats(
    val packetsSent: Long = 0,
    val packetsFailed: Long = 0,
    val lastPacketTime: Long = 0,
    val retryCount: Int = 0
)

data class DataRateConfig(
    val hz: Int = 100,  // 默认100Hz
    val minHz: Int = 10,
    val maxHz: Int = 200
) {
    init {
        require(hz in minHz..maxHz) { "频率必须在 $minHz-$maxHz 范围内" }
    }
    
    val delayMs: Long
        get() = 1000L / hz
}
```

#### 3. 非阻塞队列设计 (BLE写入队列)

```kotlin
// 使用ConcurrentLinkedQueue实现无锁队列
class NonBlockingQueue<T>(
    private val maxSize: Int = 1000
) {
    private val queue = ConcurrentLinkedQueue<T>()
    
    fun offer(item: T): Boolean {
        if (queue.size >= maxSize) {
            queue.poll() // 移除最旧的
        }
        return queue.offer(item)
    }
    
    fun poll(): T? = queue.poll()
    
    fun isEmpty(): Boolean = queue.isEmpty()
    
    fun size(): Int = queue.size
    
    fun clear() = queue.clear()
}

// BLE写入服务
class BleWriteQueue {
    private val queue = NonBlockingQueue<ByteArray>(500)
    private var isWriting = AtomicBoolean(false)
    
    suspend fun write(data: ByteArray) {
        queue.offer(data)
        processQueue()
    }
    
    private suspend fun processQueue() {
        if (!isWriting.compareAndSet(false, true)) return
        
        while (!queue.isEmpty()) {
            queue.poll()?.let { data ->
                try {
                    writeCharacteristic.value = data
                    gatt.writeCharacteristic(writeCharacteristic)
                } catch (e: Exception) {
                    LogUtil.e(TAG, "BLE write failed", e)
                }
            }
        }
        
        isWriting.set(false)
    }
}
```

#### 4. 崩溃捕获设计

```kotlin
object CrashReporter {
    private const val CRASH_LOG_DIR = "crash_logs"
    private const val MAX_CACHED_LOGS = 100
    private const val CRASH_REPORT_FILE = "crash_report.txt"
    
    private var previousHandler: Thread.UncaughtExceptionHandler? = null
    private val cachedLogs = Collections.synchronizedList(mutableListOf<String>())
    
    fun init(context: Context) {
        previousHandler = Thread.getDefaultUncaughtExceptionHandler()
        
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            handleCrash(thread, throwable, context)
            previousHandler?.uncaughtException(thread, throwable)
        }
        
        // 初始化日志目录
        File(context.filesDir, CRASH_LOG_DIR).mkdirs()
    }
    
    fun cacheLog(log: String) {
        synchronized(cachedLogs) {
            if (cachedLogs.size >= MAX_CACHED_LOGS) {
                cachedLogs.removeAt(0)
            }
            cachedLogs.add(log)
        }
    }
    
    private fun handleCrash(thread: Thread, throwable: Throwable, context: Context) {
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US).format(Date())
        val report = buildString {
            appendLine("=" .repeat(60))
            appendLine("CRASH REPORT")
            appendLine("=" .repeat(60))
            appendLine("Timestamp: $timestamp")
            appendLine("Thread: ${thread.name}")
            appendLine("App Version: ${getAppVersion(context)}")
            appendLine("Android Version: ${Build.VERSION.RELEASE}")
            appendLine("Device: ${Build.MODEL}")
            appendLine()
            appendLine("EXCEPTION:")
            appendLine(throwable.toString())
            appendLine()
            appendLine("STACK TRACE:")
            throwable.stackTrace.forEach { appendLine("  $it") }
            appendLine()
            appendLine("=" .repeat(60))
            appendLine("RECENT LOGS (last ${cachedLogs.size}):")
            appendLine("=" .repeat(60))
            synchronized(cachedLogs) {
                cachedLogs.forEach { appendLine(it) }
            }
        }
        
        // 保存报告
        val reportFile = File(context.filesDir, CRASH_REPORT_FILE)
        reportFile.writeText(report)
        
        // 保存带时间戳的备份
        val backupFile = File(context.filesDir, "$CRASH_LOG_DIR/crash_$timestamp.log")
        backupFile.writeText(report)
    }
    
    fun hasCrashReport(): Boolean {
        return File(NCUApplication.instance?.filesDir, CRASH_REPORT_FILE).exists()
    }
    
    fun getCrashReport(): String? {
        val file = File(NCUApplication.instance?.filesDir, CRASH_REPORT_FILE)
        return if (file.exists()) file.readText() else null
    }
    
    fun clearCrashReport() {
        File(NCUApplication.instance?.filesDir, CRASH_REPORT_FILE).delete()
    }
    
    private fun getAppVersion(context: Context): String {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName ?: "Unknown"
        } catch (e: Exception) {
            "Unknown"
        }
    }
}
```

### 集成计划

#### 阶段一：基础设施（1-2天）

1. 创建错误枚举类ConnectionError
2. 创建日志系统FileLoggingTree（完全异步）
3. 创建连接状态数据类
4. 配置Timber依赖
5. 实现CrashReporter崩溃捕获
6. 添加字符串资源

#### 阶段二：连接管理框架（2-3天）

1. 实现ConnectionManager（支持动态频率）
2. 实现HeartbeatManager（协程调度）
3. 实现ReconnectManager
4. 增强UdpService（100Hz，动态频率）
5. 增强BleManager（无锁队列）

#### 阶段三：用户界面增强（2-3天）

1. 添加状态监控面板组件（频率显示）
2. 实现错误对话框组件
3. 实现日志导出功能
4. 集成到MainViewModel
5. 添加频率设置界面

#### 阶段四：优化和测试（2天）

1. BLE写入队列优化
2. 崩溃报告导出功能
3. 单元测试和集成测试
4. 非阻塞验证测试

## 依赖和配置

### 新增依赖

在build.gradle中添加：

```kotlin
dependencies {
    implementation 'com.jakewharton.timber:timber:5.0.1'
}
```

### 字符串资源

在strings.xml中添加错误消息和频率设置相关字符串：

```xml
<!-- 错误消息 -->
<string name="error_udp_unreachable">UDP connection failed</string>
<string name="error_udp_unreachable_suggestion">Check if the target IP address is correct and the device is reachable.</string>
<string name="error_ble_scan_failed">Bluetooth scan failed</string>
<string name="error_ble_scan_failed_suggestion">Ensure Bluetooth is enabled and location permission is granted.</string>

<!-- 频率设置 -->
<string name="data_rate_title">Data Transmission Rate</string>
<string name="data_rate_hz">%d Hz</string>
<string name="data_rate_hint">Range: 10-200 Hz</string>

<!-- 崩溃报告 -->
<string name="crash_report_title">Application Crashed</string>
<string name="crash_report_message">The application has crashed. Would you like to send a crash report?</string>
<string name="crash_report_export">Export Report</string>
```

### 文件位置

| 模块 | 文件路径 |
|-----|---------|
| 错误枚举 | domain/error/ConnectionError.kt |
| 连接状态 | domain/connection/ConnectionStatus.kt |
| 连接管理 | domain/connection/ConnectionManager.kt |
| 崩溃捕获 | util/crash/CrashReporter.kt |
| 日志系统 | util/Log/LogUtil.kt, FileLoggingTree.kt |
| 状态面板 | ui/components/ConnectionStatusPanel.kt |
| 错误对话框 | ui/components/ErrorDialog.kt |
| 频率设置 | ui/components/DataRateSettingDialog.kt |

## 验收标准细化

### 功能验收标准

1. **UDP连接监控**
   - [ ] 识别网络不可达、端口关闭、超时等错误类型
   - [ ] 状态面板显示实时连接状态
   - [ ] 自动重连在3次失败后停止并提示用户
   - [ ] 心跳检测间隔5秒，超时10秒判定断开
   - [ ] 数据频率默认100Hz，支持10-200Hz配置

2. **BLE连接增强**
   - [ ] 识别扫描失败、连接失败、服务未找到等错误
   - [ ] 写入队列确保100Hz数据不丢失（无锁队列）
   - [ ] 自动重连采用指数退避策略（1s, 2s, 4s, 8s）
   - [ ] 连接间隔优化至15-25ms范围

3. **日志系统**
   - [ ] 所有关键操作有INFO级别日志
   - [ ] 错误有WARN/ERROR级别日志
   - [ ] 日志文件支持导出
   - [ ] 支持按日志级别过滤
   - [ ] 文件写入完全异步

4. **用户界面**
   - [ ] 状态面板显示UDP/BLE连接状态、频率设置
   - [ ] 错误对话框显示错误码、消息和建议
   - [ ] 频率设置对话框支持10-200Hz调节
   - [ ] 崩溃报告导出生成带时间戳的文本文件

5. **崩溃捕获**
   - [ ] 全局异常处理器正确安装
   - [ ] 崩溃时收集完整堆栈跟踪
   - [ ] 崩溃前最后100条日志自动保存
   - [ ] 下次启动检测并提示用户

### 非阻塞验证标准

1. **非阻塞验证**
   - [ ] StrictMode检测无违规
   - [ ] 主线程无IO操作
   - [ ] 队列写入无阻塞等待
   - [ ] UI状态更新采用Channel

### 性能验证标准

1. **性能要求（无硬性限制）**
   - [ ] 100Hz连续运行稳定
   - [ ] 无内存泄漏
   - [ ] 崩溃后正常恢复

### 兼容性验证标准

1. **兼容性**
   - [ ] 在Android 10-14设备测试通过
   - [ ] 在至少2个不同品牌设备测试通过

2. **代码质量**
   - [ ] 单元测试覆盖率 > 80%
   - [ ] 无TODO注释
   - [ ] 遵循项目代码风格

## 风险识别与应对

| 风险 | 可能性 | 影响 | 应对措施 |
|-----|-------|------|---------|
| BLE连接不稳定 | 中 | 高 | 实现连接参数优化和重试机制 |
| 100Hz频率下队列溢出 | 中 | 中 | 动态调整队列大小，丢弃旧数据 |
| 崩溃捕获本身崩溃 | 低 | 高 | 在init中try-catch保护 |
| 日志文件过大 | 低 | 中 | 实现日志轮转和清理 |

## 确认事项

本CONSENSUS文档确认了以下内容：

- 技术方案已明确：采用ConnectionManager统一管理连接状态
- 错误处理策略已确定：枚举类分类 + 用户友好提示
- 日志系统方案已确定：Timber + FileTree（完全异步）
- 用户反馈方案已确定：状态面板 + 错误对话框
- 数据频率已确定：默认100Hz，支持10-200Hz配置
- 崩溃捕获方案已确定：全局异常处理器 + 日志缓存
- 实现优先级已确定：按阶段顺序实施
- 验收标准已明确：功能和质量标准已定义，包括非阻塞验证

请确认以上技术方案和实现计划是否符合预期。如有修改意见，请在反馈中说明。
