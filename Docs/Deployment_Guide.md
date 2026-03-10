# 部署与使用指南

**文档版本**: 1.0  
**最后更新**: 2026-02-01  
**适用范围**: DJI RC Pro Controller App (NCU-RC2026)

---

## 目录

1. [环境准备](#1-环境准备)
2. [项目配置](#2-项目配置)
3. [编译构建](#3-编译构建)
4. [安装部署](#4-安装部署)
5. [使用指南](#5-使用指南)
6. [故障排除](#6-故障排除)

---

## 1. 环境准备

### 1.1 开发环境

| 组件 | 版本要求 | 说明 |
|-----|---------|------|
| **Android Studio** | Ladybug 或更新 | IDE |
| **JDK** | 17+ | Java开发工具包 |
| **Kotlin** | 1.9.x | 编程语言 |
| **Android SDK** | API 33+ | Android开发SDK |
| **Gradle** | 8.0+ | 构建工具 |

### 1.2 硬件要求

| 设备 | 型号 | 说明 |
|-----|------|------|
| **遥控器** | DJI RC Pro (RM510) | 目标运行设备 |
| **固件** | 最新版本 | 确保兼容性 |
| **USB线** | USB-C | 连接电脑调试 |

### 1.3 网络环境

- WiFi 网络（2.4GHz/5GHz）
- 目标设备（Ubuntu/ROS）与遥控器在同一网络
- 网络延迟 < 50ms

---

## 2. 项目配置

### 2.1 克隆项目

```bash
git clone https://github.com/your-org/dji-rc-pro.git
cd dji-rc-pro
```

### 2.2 配置 DJI App Key

在 `local.properties` 文件中添加 DJI App Key：

```properties
# local.properties
DJI_API_KEY=your_dji_app_key_here
```

**获取 App Key**:
1. 访问 [DJI Developer](https://developer.dji.com/)
2. 注册开发者账号
3. 创建应用获取 App Key

### 2.3 配置网络参数