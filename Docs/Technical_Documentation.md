# DJI RC Pro Controller App - 技术文档

**项目代号**: NCU-RC2026  
**文档版本**: 1.0  
**最后更新**: 2026-02-01

---

## 目录

1. [项目概述](#1-项目概述)
2. [系统架构](#2-系统架构)
3. [技术栈](#3-技术栈)
4. [核心模块](#4-核心模块)
5. [数据流](#5-数据流)

---

## 1. 项目概述

### 1.1 项目背景

**NCU-RC2026** 是一个运行在 **DJI RC Pro (RM510)** 遥控器上的 Android 应用程序。该项目旨在解锁遥控器的通用控制能力，使其能够通过 UDP/BLE 协议控制非 DJI 官方的机器人（如运行 Ubuntu 的无人车/无人机）。

### 1.2 核心功能

| 功能模块 | 描述 |
|---------|------|
| **硬件抽象** | 通过 DJI MSDK V5 获取实体摇杆、按键、5D 按钮的数据 |
| **通用协议** | 将控制数据封装为标准 UDP 数据包发送至目标设备 |
| **可配置性** | 支持通过 JSON 文件自定义按键映射和通信地址 |
| **可视化** | 提供虚拟摇杆界面，实时显示控制状态 |
| **数据监控** | 实时显示 UDP 收发数据日志 |

### 1.3 目标设备

- **硬件**: DJI RC Pro (RM510) 遥控器
- **固件**: 最新版本
- **SDK**: DJI Mobile SDK V5 (Android)

---

## 2. 系统架构

### 2.1 架构模式

本项目采用 **MVVM (Model-View-ViewModel)** 架构模式：

```
┌─────────────────────────────────────────────────────────────────┐
│                         UI Layer (View)                          │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────────┐  │
│  │ MainScreen   │  │AdvancedScreen│  │ ConnectionStatusPanel│  │
│  └──────────────┘  └──────────────┘  └──────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│                    ViewModel Layer                               │
│                    ┌──────────────────┐                         │
│                    │   MainViewModel  │                         │
│                    └──────────────────┘                         │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│                    Service Layer                                 │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────────┐  │
│  │ UdpService   │  │  BleService  │  │BaseTransmissionService│  │
│  └──────────────┘  └──────────────┘  └──────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│                    Manager Layer                                 │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────────┐  │
│  │UsbHidManager │  │ConnectionMgr │  │ FrequencyManager     │  │
│  ├──────────────┤  ├──────────────┤  ├──────────────────────┤  │
│  │HeartbeatMgr  │  │ReconnectMgr  │  │ UdpDataLogManager    │  │
│  └──────────────┘  └──────────────┘  └──────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────┐
│                    Domain Layer                                  │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────────────┐  │
│  │ConfigRepo    │  │ControlPacket │  │UsbHidProtocolParser  │  │
│  └──────────────┘  └──────────────┘  └──────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
```

### 2.2 模块职责

| 层级 | 模块 | 职责 |
|-----|------|------|
| **UI** | MainScreen | 主界面，显示虚拟摇杆和连接状态 |
| **UI** | AdvancedScreen | 高级设置界面，配置网络参数 |
| **ViewModel** | MainViewModel | 协调UI与Service，管理应用状态 |
| **Service** | UdpService | UDP通信服务，数据收发核心 |
| **Service** | BleService | 蓝牙BLE通信服务 |
| **Manager** | UsbHidManager | USB HID设备管理，读取遥控器数据 |
| **Manager** | ConnectionManager | 连接状态管理 |
| **Manager** | FrequencyManager | 发送频率控制 |
| **Domain** | ConfigRepository | 配置数据持久化 |
| **Domain** | ControlPacket | 控制数据包协议定义 |

---

## 3. 技术栈

### 3.1 核心技术

| 技术 | 版本 | 用途 |
|-----|------|------|
| **Kotlin** | 1.9.x | 主要开发语言 |
| **Android SDK** | API 33+ | 目标平台 |
| **DJI MSDK V5** | 5.x | DJI硬件交互 |
| **Jetpack Compose** | 1.5.x | UI框架 |
| **Coroutines** | 1.7.x | 异步编程 |
| **Flow** | - | 响应式数据流 |

### 3.2 网络通信

| 协议 | 用途 | 端口 |
|-----|------|------|
| **UDP** | 控制数据传输 | 可配置 (默认1346) |
| **BLE** | 备用通信通道 | - |
| **USB HID** | 读取遥控器硬件数据 | - |

### 3.3 项目结构

```
Dji_RC_Pro/
├── app/
│   ├── src/main/java/com/example/dji_rc_pro/
│   │   ├── MainActivity.kt
│   │   ├── NCUApplication.kt
│   │   ├── service/
│   │   │   ├── UdpService.kt          # UDP通信服务
│   │   │   ├── BleService.kt          # BLE通信服务
│   │   │   └── BaseTransmissionService.kt
│   │   ├── viewmodel/
│   │   │   └── MainViewModel.kt       # 主ViewModel
│   │   ├── manager/
│   │   │   ├── UsbHidManager.kt       # USB HID管理
│   │   │   ├── ConnectionManager.kt   # 连接管理
│   │   │   ├── FrequencyManager.kt    # 频率管理
│   │   │   ├── HeartbeatManager.kt    # 心跳管理
│   │   │   └── ReconnectManager.kt    # 重连管理
│   │   ├── domain/
│   │   │   ├── usb/
│   │   │   │   ├── UsbHidProtocolParser.kt
│   │   │   │   └── UsbHidManager.kt
│   │   │   ├── config/
│   │   │   │   └── ConfigRepository.kt
│   │   │   └── model/
│   │   │       └── UdpDataLog.kt
│   │   ├── protocol/
│   │   │   ├── ControlPacket.kt
│   │   │   └── Crc16.kt
│   │   └── ui/
│   │       ├── MainScreen.kt
│   │       ├── AdvancedScreen.kt
│   │       └── components/
│   └── build.gradle.kts
├── Docs/                              # 文档目录
└── README.md
```

---

## 4. 核心模块

### 4.1 UdpService - UDP通信服务

`UdpService` 是项目的核心组件，负责 UDP 数据的发送和接收。

**主要功能**:
- UDP Socket 管理
- 数据包发送（USB HID数据转发）
- 数据包接收
- 心跳维护
- 频率控制

**关键特性**:
- 支持 IPv4 绑定（绑定到 wlan0 接口）
- 并发安全的 USB HID 数据队列
- 自动重连机制
- 实时数据日志

### 4.2 UsbHidManager - USB HID管理器

`UsbHidManager` 负责与 DJI RC Pro 的硬件交互，读取遥控器数据。

**主要功能**:
- USB HID 设备枚举和连接
- 实时读取摇杆、按键数据
- 数据解析和回调

**数据格式**:
```kotlin
// USB HID 数据包结构（64字节）
// 包含摇杆位置、按键状态、5D按钮状态等
```

### 4.3 MainViewModel - 主视图模型

`MainViewModel` 是 UI 层与 Service 层之间的桥梁。

**主要功能**:
- 管理应用状态（连接状态、运行状态）
- 协调 UDP 和 USB HID 服务
- 处理用户输入
- 数据转发（USB → UDP）

---

## 5. 数据流

### 5.1 USB HID 到 UDP 数据流

```
┌─────────────────────────────────────────────────────────────────┐
│                    数据流向图                                    │
└─────────────────────────────────────────────────────────────────┘

    DJI RC Pro 硬件
         │
         │ USB HID Protocol
         ▼
    ┌──────────────┐
    │UsbHidManager │  ← 读取原始HID数据
    └──────────────┘
         │
         │ onDataReceived callback
         ▼
    ┌──────────────┐
    │UsbHidProtocol│  ← 解析数据
    │   Parser     │
    └──────────────┘
         │
         │ 解析后的数据
         ▼
    ┌──────────────┐
    │MainViewModel │  ← 转发到UDP服务
    └──────────────┘
         │
         │ addUsbHidData()
         ▼
    ┌──────────────┐
    │  UdpService  │  ← 添加到发送队列
    │  usbHidData  │
    │    Queue     │
    └──────────────┘
         │
         │ transmitPacket()
         ▼
    ┌──────────────┐
    │  UDP Socket  │  ← 发送到目标设备
    └──────────────┘
         │
         │ UDP Protocol
         ▼
    目标设备 (Ubuntu/ROS)
```

### 5.2 控制数据包格式

```kotlin
// 控制数据包结构 (9字节)
// +--------+--------+--------+--------+--------+--------+--------+--------+--------+
// | 0xAA   | LeftX  | LeftY  | RightX | RightY | Buttons|  CRC   |  CRC   |        |
// | (Header)|(0-255) |(0-255) |(0-255) |(0-255) |(Bitmap)|  High  |  Low   |        |
// +--------+--------+--------+--------+--------+--------+--------+--------+

// 数据包示例: AA 7F 7F 7F 7F 00 00 CD 5D
// - AA: 包头
// - 7F 7F 7F 7F: 摇杆中位值 (127)
// - 00: 无按键按下
// - CD 5D: CRC16校验
```

### 5.3 频率控制

```
┌─────────────────────────────────────────────────────────────┐
│                     频率控制流程                             │
└─────────────────────────────────────────────────────────────┘

FrequencyManager
       │
       ├── 默认频率: 50Hz (20ms间隔)
       │
       ├── 可调范围: 1Hz - 1000Hz
       │
       └── 动态调整: 根据网络状况自动调整
       │
       ▼
UdpService.transmissionLoop()
       │
       ├── 根据频率计算延迟
       │   delay = 1000ms / frequencyHz
       │
       └── 定时调用 transmitPacket()
```

---

## 6. 配置参数

### 6.1 网络配置

| 参数 | 默认值 | 说明 |
|-----|-------|------|
| `target_ip` | 192.168.1.100 | 目标设备IP地址 |
| `target_port` | 1347 | 目标设备端口 |
| `local_port` | 1346 | 本地监听端口 |
| `frequency_hz` | 50 | 发送频率 (Hz) |

### 6.2 配置存储

配置使用 Android DataStore 持久化存储：

```kotlin
// ConfigRepository.kt
object ConfigRepository {
    val targetIp: Flow<String>
    val targetPort: Flow<Int>
    val localPort: Flow<Int>
    val frequencyHz: Flow<Int>
    
    suspend fun updateTargetIp(ip: String)
    suspend fun updateTargetPort(port: Int)
    suspend fun updateLocalPort(port: Int)
    suspend fun updateFrequencyHz(hz: Int)
}
```

---

## 7. 日志与调试

### 7.1 日志级别

| 级别 | 用途 |
|-----|------|
| **VERBOSE** | 详细调试信息 |
| **DEBUG** | 开发调试信息 |
| **INFO** | 一般信息 |
| **WARN** | 警告信息 |
| **ERROR** | 错误信息 |

### 7.2 关键日志标签

```kotlin
// UdpService 日志
private const val TAG = "UdpService"

// 关键日志输出
Log.d(TAG, "UDP TX [${size}]: ${bytesToHex(data)}")  // 发送数据
Log.d(TAG, "UDP RX [${size}]: ${bytesToHex(data)}")  // 接收数据
Log.d(TAG, "Socket bound to $address:$port")         // Socket绑定
```

---

## 8. 相关文档

- [UDP模块详细文档](./UDP_Module.md)
- [问题诊断与修复记录](./Troubleshooting_Log.md)
- [API接口文档](./API_Reference.md)
- [部署与使用指南](./Deployment_Guide.md)

---

## 9. 版本历史

| 版本 | 日期 | 变更内容 |
|-----|------|---------|
| 1.0 | 2026-02-01 | 初始版本，完成UDP服务修复和USB HID数据转发功能 |

---

**文档维护**: NCU-RC2026 开发团队  
**联系方式**: [项目主页](https://github.com/your-org/dji-rc-pro)
