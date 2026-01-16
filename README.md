# DJI RC Pro Controller App (NCU-RC2026)

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)]()
[![Platform](https://img.shields.io/badge/platform-Android-green)]()
[![SDK](https://img.shields.io/badge/DJI%20MSDK-V5-blue)]()

## 📖 项目简介

**NCU-RC2026** 是一个运行在 **DJI RC Pro (RM510)** 遥控器上的 Android 应用程序。旨在解锁遥控器的通用控制能力，使其能够通过 UDP/BLE 协议控制非 DJI 官方的机器人（如运行 Ubuntu 的无人车/无人机）。

核心功能：
1.  **硬件抽象**: 通过 DJI MSDK V5 获取实体摇杆、按键、5D 按钮的数据。
2.  **通用协议**: 将控制数据封装为标准 UDP 数据包发送至目标设备。
3.  **可配置性**: 支持通过 JSON 文件自定义按键映射和通信地址。
4.  **可视化**: 提供虚拟摇杆界面，实时显示控制状态。

## 🛠️ 技术栈

*   **语言**: Kotlin
*   **架构**: MVVM (Model-View-ViewModel)
*   **SDK**: DJI Mobile SDK V5 (Android)
*   **通信**: UDP Socket (Java/Kotlin Standard Lib)
*   **UI**: Android View System / Jetpack Compose (Planned)

## 🚀 快速开始

### 1. 环境准备
*   Android Studio Ladybug 或更新版本。
*   DJI RC Pro (RM510) 遥控器，固件更新至最新。
*   申请 DJI 开发者账号并获取 App Key。

### 2. 配置 App Key
在 `local.properties` 中添加：
```properties
DJI_API_KEY=d4c36b38844cab1a267ef684
```
(注意：实际项目中建议动态注入或配置在 AndroidManifest.xml)

### 3. 编译与安装
```bash
./gradlew assembleDebug
adb install app/build/outputs/apk/debug/app-debug.apk
```

## 📂 项目结构

详细结构请参阅 [开发文档](docs/development.md)。

*   `app/`: Android 源代码。
*   `docs/`: 项目文档与 6A 工作流记录。
*   `prompts/`: AI 辅助开发提示词。

## 🤝 贡献指南

本项目遵循 **Vibe Coding** 规范。提交代码前请确保：
1.  更新了相关文档。
2.  通过了所有单元测试。
3.  遵循 Kotlin 代码风格。

## 📄 许可证

[MIT License](LICENSE)
