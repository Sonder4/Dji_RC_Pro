# 开发文档 (Development Documentation)

## 1. 项目结构 (Project Structure)

本项目遵循 Android MVVM 架构与 Vibe Coding 工程化规范。

```
Dji_RC_Pro/
├── README.md                 # 项目说明文档
├── requirements.txt          # Python 脚本依赖
├── AGENTS.md                 # AI 智能体技能配置
├── CLAUDE.md                 # Claude 上下文配置
├── .gitignore                # Git 忽略规则
│
├── app/                      # Android App 源码
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/dji_rc_pro/
│   │   │   │   ├── model/          # 数据模型
│   │   │   │   ├── viewmodel/      # ViewModel
│   │   │   │   ├── repository/     # 数据仓库
│   │   │   │   ├── service/        # 后台服务
│   │   │   │   ├── ui/             # Compose UI
│   │   │   │   │   ├── theme/      # 主题定义
│   │   │   │   │   └── components/ # UI 组件
│   │   │   │   ├── manager/        # 管理器（新增）
│   │   │   │   │   ├── ConnectionManager.kt  # 连接管理
│   │   │   │   │   ├── HeartbeatManager.kt   # 心跳检测
│   │   │   │   │   ├── ReconnectManager.kt   # 重连管理
│   │   │   │   │   └── FrequencyManager.kt   # 频率管理
│   │   │   │   ├── util/           # 工具类（新增）
│   │   │   │   │   ├── LogUtil.kt           # 日志工具
│   │   │   │   │   ├── CrashReporter.kt     # 崩溃报告
│   │   │   │   │   └── NonBlockingQueue.kt  # 无锁队列
│   │   │   │   └── MainActivity.kt # 入口 Activity
│   │   │   ├── res/                # 资源文件
│   │   │   └── AndroidManifest.xml
│   │   └── test/                   # 单元测试
│   └── build.gradle          # App 模块构建配置
│
├── docs/                     # 项目文档
│   ├── context/              # Vibe Coding 上下文
│   ├── 01_Initialization/    # 初始化阶段文档 (6A)
│   ├── 02_Architecture/      # 架构设计文档 (6A)
│   ├── 03_Usb_Integration/   # USB集成文档 (6A)
│   ├── 04_Network_Improvement/ # 网络改进文档 (6A) [新增]
│   │   ├── ALIGNMENT_UDP_BLE_Improvement.md
│   │   ├── CONSENSUS_UDP_BLE_Improvement.md
│   │   ├── DESIGN_UDP_BLE_Improvement.md
│   │   ├── TASK_UDP_BLE_Improvement.md
│   │   └── ACCEPTANCE_UDP_BLE_Improvement.md
│   └── development.md        # 本开发文档
│
├── prompts/                  # AI 提示词库
│   ├── System_Prompt.md      # 系统级提示词
│   └── Task_Prompts.md       # 任务级提示词
│
├── ErrorHistory/             # 错误记录 (Vibe Coding)
├── MemoryContext/            # 记忆上下文 (Vibe Coding)
│
└── build.gradle              # 项目级构建配置
```

## 2. 开发环境 (Development Environment)

*   **IDE**: Android Studio Ladybug | 2024.2.1+
*   **JDK**: JDK 17
*   **Android SDK**: API 29 (Android 10) - 34
*   **Build System**: Gradle 8.x
*   **Dependencies**:
    *   DJI MSDK V5.17.0
    *   Kotlin Coroutines
    *   Jetpack Lifecycle (ViewModel, LiveData)
    *   Jetpack DataStore (Settings)
    *   **Timber** (日志框架 - 新增)
    *   **Compose UI** (Jetpack Compose)

## 3. 核心流程 (Core Workflows)

### 3.1 MSDK 集成
*   App 启动时在 `Application` 类中初始化 SDK。
*   使用 `SDKManager.getInstance().init()` 进行注册。
*   注册成功后，通过 `KeyManager` 监听遥控器输入。

### 3.2 UDP & BLE 通信 (Dual Send)
*   **UDP**: `UdpService` 启动后，开启可配置频率的协程循环（默认100Hz，可配置10-200Hz）。
*   **BLE**: `BleService` 启动后，开启可配置频率的协程循环（默认100Hz，可配置10-200Hz）。
*   两者均从 `MsdkManager` (单例) 获取最新 `ControllerState`。
*   `BleManager` 负责扫描与连接，`BleService` 负责数据传输。
*   UI 层通过 `MainViewModel` 控制扫描与连接。

### 3.3 连接管理与监控 (新增)
*   **ConnectionManager**: 统一管理UDP和BLE连接状态
*   **HeartbeatManager**: 定期发送心跳包检测连接活性
*   **ReconnectManager**: 断线后自动重连，采用指数退避策略
*   **FrequencyManager**: 管理数据传输频率，支持运行时调整

### 3.4 非阻塞设计原则 (新增)
*   所有IO操作使用 `Dispatchers.IO` 或 `Dispatchers.Default`
*   数据传输使用无锁队列 `NonBlockingQueue`
*   UI状态更新使用 `StateFlow` + `viewModelScope`
*   日志写入异步执行，不阻塞主线程

### 3.5 崩溃报告机制 (新增)
*   `CrashReporter` 捕获全局未处理异常
*   自动收集设备信息、堆栈跟踪、内存状态
*   崩溃日志本地持久化存储
*   支持用户导出崩溃报告用于问题诊断

## 4. 规范与约定 (Conventions)

*   **代码风格**: 遵循 Kotlin 官方编码规范。
*   **Git 提交**: `feat:`, `fix:`, `docs:`, `refactor:` 前缀。
*   **文档更新**: 代码变更必须同步更新 `docs/` 下的相关文档。

## 5. 当前开发任务 (Current Development)

### 5.1 UDP/BLE 网络改进任务
基于 6A 工作流的 UDP/BLE 通信优化任务，详见 `docs/04_Network_Improvement/`。

**任务目标**:
*   实现100Hz可配置数据传输频率（10-200Hz范围）
*   全面采用非阻塞设计模式
*   添加连接监控、心跳检测、自动重连机制
*   实现完整的崩溃报告系统

**任务分解** (20个原子任务):
| 阶段 | 任务 | 状态 |
|------|------|------|
| Phase 1: 基础设施 | T1-T7 | 待开发 |
| Phase 2: 核心管理器 | T8-T11 | 待开发 |
| Phase 3: 服务增强 | T12-T13 | 待开发 |
| Phase 4: UI组件 | T14-T20 | 待开发 |

**技术要求**:
*   Kotlin Coroutines 实现异步非阻塞
*   ConcurrentLinkedQueue 实现无锁队列
*   DataStore 持久化频率配置
*   Timber + FileLoggingTree 异步日志
