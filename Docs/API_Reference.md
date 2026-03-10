# API接口文档

**文档版本**: 1.0  
**最后更新**: 2026-02-01  
**适用范围**: DJI RC Pro Controller App (NCU-RC2026)

---

## 目录

1. [UdpService API](#1-udpservice-api)
2. [MainViewModel API](#2-mainviewmodel-api)
3. [UsbHidManager API](#3-usbhidmanager-api)
4. [ConfigRepository API](#4-configrepository-api)
5. [数据模型](#5-数据模型)
6. [协议定义](#6-协议定义)

---

## 1. UdpService API

### 1.1 类定义

```kotlin
class UdpService private constructor() : BaseTransmissionService()
```

**单例模式**: 使用 `getInstanceOrNull()` 获取实例。

### 1.2 公共方法

#### getInstanceOrNull

```kotlin
fun getInstanceOrNull(): UdpService?
```

获取 UdpService 的单例实例。

**返回值**:
- `UdpService?` - 服务实例，如果未创建则返回 null

**示例**:
```kotlin
val udpService = UdpService.getInstanceOrNull()
if (udpService != null) {
    // 使用服务
}
```

---

#### initialize

```kotlin
fun initialize(
    frequencyManager: FrequencyManager,
    heartbeatManager: HeartbeatManager,
    connectionManager: ConnectionManager,
    udpDataLogManager: UdpDataLogManager
)
```

初始化服务依赖的管理器。

**参数**:
| 参数 | 类型 | 说明 |
|-----|------|------|
| `frequencyManager` | `FrequencyManager` | 频率管理器 |
| `heartbeatManager` | `HeartbeatManager` | 心跳管理器 |
| `connectionManager` | `ConnectionManager` | 连接管理器 |
| `udpDataLogManager` | `UdpDataLogManager` | 数据日志管理器 |

**示例**:
```kotlin
udpService.initialize(
    frequencyManager = frequencyManager,
    heartbeatManager = heartbeatManager,
    connectionManager = connectionManager,
    udpDataLogManager = udpDataLogManager
)
```

---

#### startTransmission

```kotlin
override fun startTransmission()
```

启动 UDP 传输服务。

**行为**:
- 检查服务是否已在运行
- 读取配置（目标IP、端口等）
- 等待初始化完成（最多2秒）
- 创建并绑定 Socket
- 启动数据接收协程
- 进入传输循环

**示例**:
```kotlin
udpService.startTransmission()
```

---

#### stopTransmission

```kotlin
override fun stopTransmission()
```

停止 UDP 传输服务。

**行为**:
- 设置 `isRunning = false`
- 取消所有协程
- 关闭 Socket
- 清理资源

**示例**:
```kotlin
udpService.stopTransmission()
```

---

#### addUsbHidData

```kotlin
fun addUsbHidData(data: ByteArray)
```

添加 USB HID 数据到发送队列。

**参数**:
| 参数 | 类型 | 说明 |
|-----|------|------|
| `data` | `ByteArray` | USB HID 原始数据 |

**行为**:
- 将数据添加到线程安全的队列
- 队列大小限制为100条，超出则丢弃最旧数据

**示例**:
```kotlin
val usbHidData = byteArrayOf(0xAA, 0x01, 0x02, ...)
udpService.addUsbHidData(usbHidData)
```

---

#### sendCustomData

```kotlin
fun sendCustomData(data: ByteArray)
```

发送自定义数据包。

**参数**:
| 参数 | 类型 | 说明 |
|-----|------|------|
| `data` | `ByteArray` | 自定义数据 |

**示例**:
```kotlin
val customData = byteArrayOf(0xBB, 0x01, 0x02, 0x03)
udpService.sendCustomData(customData)
```

---

#### isRunning

```kotlin
val isRunning: Boolean
```

检查服务是否正在运行。

**返回值**:
- `Boolean` - true 表示服务正在运行

---

### 1.3 保护方法

#### transmitPacket

```kotlin
protected suspend fun transmitPacket()
```

发送一个数据包。

**行为**:
- 从 `usbHidDataQueue` 取出数据
- 创建 UDP 数据包
- 通过 Socket 发送
- 记录发送日志

---

#### transmissionLoop

```kotlin
protected suspend fun transmissionLoop()
```

传输循环，持续