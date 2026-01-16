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

## 3. 核心流程 (Core Workflows)

### 3.1 MSDK 集成
*   App 启动时在 `Application` 类中初始化 SDK。
*   使用 `SDKManager.getInstance().init()` 进行注册。
*   注册成功后，通过 `KeyManager` 监听遥控器输入。

### 3.2 UDP & BLE 通信 (Dual Send)
*   **UDP**: `UdpService` 启动后，开启 50Hz 协程循环。
*   **BLE**: `BleService` 启动后，开启 50Hz 协程循环。
*   两者均从 `MsdkManager` (单例) 获取最新 `ControllerState`。
*   `BleManager` 负责扫描与连接，`BleService` 负责数据传输。
*   UI 层通过 `MainViewModel` 控制扫描与连接。

## 4. 规范与约定 (Conventions)

*   **代码风格**: 遵循 Kotlin 官方编码规范。
*   **Git 提交**: `feat:`, `fix:`, `docs:`, `refactor:` 前缀。
*   **文档更新**: 代码变更必须同步更新 `docs/` 下的相关文档。
