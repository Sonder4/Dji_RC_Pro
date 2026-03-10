# UDP通信模块详细文档

**文档版本**: 1.0  
**最后更新**: 2026-02-01  
**关联文件**: `app/src/main/java/com/example/dji_rc_pro/service/UdpService.kt`

---

## 目录

1. [模块概述](#1-模块概述)
2. [类架构](#2-类架构)
3. [核心流程](#3-核心流程)
4. [关键代码解析](#4-关键代码解析)
5. [初始化修复](#5-初始化修复)
6. [USB HID数据转发](#6-usb-hid数据转发)

---

## 1. 模块概述

`UdpService` 是 DJI RC Pro Controller App 的核心通信模块，负责通过 UDP 协议与目标设备（如运行 Ubuntu/ROS 的无人车）进行双向数据通信。

### 1.1 主要功能

| 功能 | 描述 |
|-----|------|
| **Socket管理** | 创建、绑定、关闭 UDP Socket |
| **数据发送** | 将 USB HID 数据转发到目标设备 |
| **数据接收** | 接收来自目标设备的控制指令 |
| **心跳维护** | 定期发送心跳包保持连接活性 |
| **频率控制** | 根据配置的频率控制发送速率 |
| **错误处理** | 自动重连和错误恢复 |

### 1.2 设计特点

- **单例模式**: 确保全局唯一实例
- **协程驱动**: 使用 Kotlin Coroutines 处理异步操作
- **队列缓冲**: USB HID 数据队列保证数据不丢失
- **IPv4绑定**: 明确绑定到 wlan0 接口的 IPv4 地址

---

## 2. 类架构

### 2.1 继承关系

```
BaseTransmissionService (抽象基类)
         │
         ▼
    UdpService (实现类)
```

### 2.2 类结构

```kotlin
class UdpService private constructor() : BaseTransmissionService() {
    
    // ========== 配置属性 ==========
    private var targetIp: String = "192.168.1.100"
    private var targetPort: Int = 1347
    private var localPort: Int = 1346
    
    // ========== 核心组件 ==========
    private var socket: DatagramSocket? = null
    private var receiveJob: Job? = null
    
    // ========== 数据队列 ==========
    private val usbHidDataQueue = ConcurrentLinkedQueue<ByteArray>()
    
    // ========== 管理器引用 ==========
    private var frequencyManager: FrequencyManager? = null
    private var heartbeatManager: HeartbeatManager? = null
    private var connectionManager: ConnectionManager? = null
    private var udpDataLogManager: UdpDataLogManager? = null
    
    companion object {
        @Volatile
        private var instance: UdpService? = null
        
        fun getInstanceOrNull(): UdpService? = instance
    }
}
```

### 2.3 依赖关系

```
UdpService
    ├── FrequencyManager      (频率控制)
    ├── HeartbeatManager      (心跳维护)
    ├── ConnectionManager     (连接状态)
    ├── UdpDataLogManager     (数据日志)
    ├── ConfigRepository      (配置读取)
    └── UsbHidDataQueue       (数据缓冲)
```

---

## 3. 核心流程

### 3.1 服务启动流程

```
┌─────────────────────────────────────────────────────────────────┐
│                     服务启动流程                                 │
└─────────────────────────────────────────────────────────────────┘

  startTransmission()
       │
       ├── 检查 isRunning 状态
       │
       ├── 启动 serviceScope.launch
       │
       ├── 读取配置 (targetIp, targetPort, localPort)
       │
       ├── 等待初始化完成 (frequencyManager