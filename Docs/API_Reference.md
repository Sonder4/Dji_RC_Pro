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

传输循环，持续发送数据。

**行为**:
- 根据频率计算发送间隔
- 循环调用 `transmitPacket()`
- 处理频率变化

---

## 2. MainViewModel API

### 2.1 类定义

```kotlin
class MainViewModel(
    private val frequencyManager: FrequencyManager,
    private val heartbeatManager: HeartbeatManager,
    private val connectionManager: ConnectionManager,
    private val udpDataLogManager: UdpDataLogManager
) : ViewModel()
```

### 2.2 公共方法

#### startUdpService

```kotlin
fun startUdpService()
```

启动 UDP 服务。

**行为**:
- 初始化 UdpService
- 启动传输服务
- 更新状态

---

#### stopUdpService

```kotlin
fun stopUdpService()
```

停止 UDP 服务。

---

#### updateTargetIp

```kotlin
fun updateTargetIp(ip: String)
```

更新目标 IP 地址。

**参数**:
| 参数 | 类型 | 说明 |
|-----|------|------|
| `ip` | `String` | 目标设备 IP 地址 |

---

#### updateTargetPort

```kotlin
fun updateTargetPort(port: Int)
```

更新目标端口。

**参数**:
| 参数 | 类型 | 说明 |
|-----|------|------|
| `port` | `Int` | 目标设备端口 (1-65535) |

---

#### updateLocalPort

```kotlin
fun updateLocalPort(port: Int)
```

更新本地端口。

**参数**:
| 参数 | 类型 | 说明 |
|-----|------|------|
| `port` | `Int` | 本地监听端口 (1-65535) |

---

#### updateFrequencyHz

```kotlin
fun updateFrequencyHz(hz: Int)
```

更新发送频率。

**参数**:
| 参数 | 类型 | 说明 |
|-----|------|------|
| `hz` | `Int` | 发送频率 Hz (1-1000) |

---

### 2.3 状态属性

#### isUdpRunning

```kotlin
val isUdpRunning: StateFlow<Boolean>
```

UDP 服务运行状态。

---

#### isBleConnected

```kotlin
val isBleConnected: StateFlow<Boolean>
```

BLE 连接状态。

---

#### connectionState

```kotlin
val connectionState: StateFlow<ConnectionState>
```

连接状态（已连接、断开、错误等）。

---

#### currentFrequencyHz

```kotlin
val currentFrequencyHz: StateFlow<Int>
```

当前发送频率。

---

#### targetIp / targetPort / localPort

```kotlin
val targetIp: StateFlow<String>
val targetPort: StateFlow<Int>
val localPort: StateFlow<Int>
```

网络配置状态。

---

## 3. UsbHidManager API

### 3.1 类定义

```kotlin
class UsbHidManager(
    private val context: Context
)
```

### 3.2 公共方法

#### initialize

```kotlin
fun initialize()
```

初始化 USB HID 管理器。

---

#### startReading

```kotlin
fun startReading()
```

开始读取 USB HID 数据。

---

#### stopReading

```kotlin
fun stopReading()
```

停止读取 USB HID 数据。

---

### 3.3 回调属性

#### onDataReceived

```kotlin
var onDataReceived: ((ByteArray, Int) -> Unit)?
```

数据接收回调。

**参数**:
| 参数 | 类型 | 说明 |
|-----|------|------|
| `data` | `ByteArray` | 接收到的数据 |
| `length` | `Int` | 数据长度 |

**示例**:
```kotlin
usbHidManager.onDataReceived = { data, len ->
    // 处理接收到的数据
    Log.d(TAG, "Received $len bytes: ${data.toHex()}")
}
```

---

## 4. ConfigRepository API

### 4.1 类定义

```kotlin
object ConfigRepository
```

单例对象，管理应用配置。

### 4.2 配置属性

#### targetIp

```kotlin
val targetIp: Flow<String>
```

目标 IP 地址流。

---

#### targetPort

```kotlin
val targetPort: Flow<Int>
```

目标端口流。

---

#### localPort

```kotlin
val localPort: Flow<Int>
```

本地端口流。

---

#### frequencyHz

```kotlin
val frequencyHz: Flow<Int>
```

发送频率流。

---

### 4.3 更新方法

#### updateTargetIp

```kotlin
suspend fun updateTargetIp(ip: String)
```

更新目标 IP。

---

#### updateTargetPort

```kotlin
suspend fun updateTargetPort(port: Int)
```

更新目标端口。

---

#### updateLocalPort

```kotlin
suspend fun updateLocalPort(port: Int)
```

更新本地端口。

---

#### updateFrequencyHz

```kotlin
suspend fun updateFrequencyHz(hz: Int)
```

更新发送频率。

---

## 5. 数据模型

### 5.1 UdpDataLog

```kotlin
data class UdpDataLog(
    val timestamp: Long,
    val type: DataType,
    val data: ByteArray,
    val size: Int
) {
    enum class DataType {
        SENT,       // 发送的数据
        RECEIVED    // 接收的数据
    }
}
```

UDP 数据日志模型。

**属性**:
| 属性 | 类型 | 说明 |
|-----|------|------|
| `timestamp` | `Long` | 时间戳（毫秒） |
| `type` | `DataType` | 数据类型（发送/接收） |
| `data` | `ByteArray` | 数据内容 |
| `size` | `Int` | 数据大小 |

---

### 5.2 ConnectionState

```kotlin
sealed class ConnectionState {
    object Disconnected : ConnectionState()
    object Connecting : ConnectionState()
    object Connected : ConnectionState()
    data class Error(val message: String) : ConnectionState()
}
```

连接状态密封类。

---

### 5.3 ControlPacket

```kotlin
data class ControlPacket(
    val header: Byte = 0xAA,
    val leftStickX: Byte,
    val leftStickY: Byte,
    val rightStickX: Byte,
    val rightStickY: Byte,
    val buttons: Byte,
    val crc: Short
)
```

控制数据包模型。

**属性**:
| 属性 | 类型 | 说明 |
|-----|------|------|
| `header` | `Byte` | 包头 (0xAA) |
| `leftStickX` | `Byte` | 左摇杆 X 轴 (0-255) |
| `leftStickY` | `Byte` | 左摇杆 Y 轴 (0-255) |
| `rightStickX` | `Byte` | 右摇杆 X 轴 (0-255) |
| `rightStickY` | `Byte` | 右摇杆 Y 轴 (0-255) |
| `buttons` | `Byte` | 按键位图 |
| `crc` | `Short` | CRC16 校验 |

---

## 6. 协议定义

### 6.1 UDP 控制协议

#### 数据包格式

```
字节偏移    名称            类型        说明
─────────────────────────────────────────────────
0           Header          Byte        包头 (0xAA)
1           Left Stick X    Byte        左摇杆 X (0-255, 127中位)
2           Left Stick Y    Byte        左摇杆 Y (0-255, 127中位)
3           Right Stick X   Byte        右摇杆 X (0-255, 127中位)
4           Right Stick Y   Byte        右摇杆 Y (0-255, 127中位)
5           Buttons         Byte        按键位图
6-7         CRC16           Short       CRC校验 (大端序)
```

**总长度**: 8 字节

#### 按键位图

```kotlin
object ButtonFlags {
    const val C1 = 0x01        // C1 按键
    const val C2 = 0x02        // C2 按键
    const val RECORD = 0x04    // 录制键
    const val SHUTTER = 0x08   // 快门键
    const val PLAYBACK = 0x10  // 回放键
    const val FN = 0x20        // FN 键
    // ... 其他按键
}
```

#### 示例数据包

```kotlin
// 摇杆中位，无按键
val packet = byteArrayOf(
    0xAA.toByte(),  // Header
    0x7F,           // Left X (中位)
    0x7F,           // Left Y (中位)
    0x7F,           // Right X (中位)
    0x7F,           // Right Y (中位)
    0x00,           // No buttons
    0x00.toByte(),  // CRC High
    0xCD.toByte()   // CRC Low
)
```

---

### 6.2 USB HID 协议

#### 数据包格式

```
字节偏移    名称                类型        说明
─────────────────────────────────────────────────
0-1         Report ID           Short       报告ID
2-3         Left Stick X        Short       左摇杆 X (0-65535)
4-5         Left Stick Y        Short       左摇杆 Y (0-65535)
6-7         Right Stick X       Short       右摇杆 X (0-65535)
8-9         Right Stick Y       Short       右摇杆 Y (0-65535)
10-11       5D Button           Short       5D按钮状态
12-13       C1/C2 Buttons       Short       C1/C2按键状态
14-63       Reserved            Byte[50]    保留
```

**总长度**: 64 字节

---

### 6.3 心跳协议

#### 心跳包格式

```kotlin
// 心跳请求
val heartbeatRequest = byteArrayOf(0xBB, 0x01)

// 心跳响应
val heartbeatResponse = byteArrayOf(0xBB, 0x02)
```

**间隔**: 默认 1000ms (1秒)

---

## 7. 工具函数

### 7.1 字节数组转十六进制

```kotlin
fun bytesToHex(bytes: ByteArray): String {
    return bytes.joinToString(" ") { "%02X".format(it) }
}
```

**示例**:
```kotlin
val data = byteArrayOf(0xAA, 0x01, 0x02)
val hex = bytesToHex(data)  // "AA 01 02"
```

---

### 7.2 CRC16 计算

```kotlin
object Crc16 {
    fun calculate(data: ByteArray): Short
    fun verify(data: ByteArray, crc: Short): Boolean
}
```

**示例**:
```kotlin
val data = byteArrayOf(0xAA, 0x01, 0x02, 0x03)
val crc = Crc16.calculate(data)
```

---

## 8. 相关文档

- [技术文档总览](./Technical_Documentation.md)
- [UDP模块详细文档](./UDP_Module.md)
- [问题诊断与修复记录](./Troubleshooting_Log.md)
- [部署与使用指南](./Deployment_Guide.md)

---

**文档维护**: NCU-RC2026 开发团队  
**最后更新**: 2026-02-01
