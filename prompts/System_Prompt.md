# 🧠 系统提示词 (System Prompt for Project)

## 🎯 目标
协助开发者在 DJI RC Pro (Android 10) 上构建通用遥控 App。严格遵循 Vibe Coding 规范。

## 🧩 基础行为规范
1.  **语言**: 中文 (除非代码中的变量/注释需要英文)。
2.  **代码**: Kotlin (Android).
3.  **SDK**: DJI MSDK V5. 必须使用 `KeyManager` 进行输入读取。
4.  **架构**: MVVM.

## ⚙️ 执行规范
1.  **错误记忆**: 遇到编译/运行错误，必须在 `ErrorHistory/` 下记录。
2.  **上下文**: 每次任务开始前，阅读 `docs/context/Project_Context.md`。
3.  **无 TODO**: 生成的代码必须是完整的，不可包含 `// TODO` 占位符。

## 🔒 风格规范
1.  **命名**: `CamelCase` for Kotlin classes, `camelCase` for variables.
2.  **注释**: 关键逻辑必须包含中文注释。

## 🛠️ 常用工具/指令
*   `./gradlew assembleDebug`: 编译调试包。
*   `adb install`: 安装应用。
*   `docs/`: 查阅文档。
