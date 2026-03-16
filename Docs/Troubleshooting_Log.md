# 问题诊断与修复记录

**文档版本**: 1.3  
**最后更新**: 2026-03-16  
**记录范围**: UDP、BLE 与原始输入主链路调试全过程

---

## 目录

1. [问题概述](#1-问题概述)
2. [问题一：Connection reset by peer](#2-问题一connection-reset-by-peer)
3. [问题二：UDP服务启动后立即停止](#3-问题二udp服务启动后立即停止)
4. [问题三：Socket绑定到IPv6地址](#4-问题三socket绑定到ipv6地址)
5. [问题四：IPv4 Ping 失败但 IPv6 与 UDP 正常](#5-问题四ipv4-ping-失败但-ipv6-与-udp-正常)
6. [调试方法与工具](#6-调试方法与工具)
7. [修复验证](#7-修复验证)
8. [2026-03-16 主链路切换为原始输入协议](#10-2026-03-16-主链路切换为原始输入协议)

---

## 1. 问题概述

在开发 DJI RC Pro Controller App 的 UDP 通信功能过程中，遇到了以下关键问题：

| 序号 | 问题描述 | 严重程度 | 状态 |
|-----|---------|---------|------|
| 1 | 外部软件发送UDP数据时出现 "Connection reset by peer" 错误 | 高 | ✅ 已修复 |
| 2 | UDP服务启动后立即停止，无法持续发送数据 | 高 | ✅ 已修复 |
| 3 | Socket绑定到IPv6地址 `::/::` 而非IPv4地址 | 中 | ✅ 已修复 |
| 4 | IPv4 双向 Ping 失败，但 IPv6 与 UDP 主通道正常 | 高 | ✅ 已定位 |
| 5 | 主链路需要切换为原始输入协议，`cmd_vel` 改由 ROS2 Joy 映射统一生成 | 高 | ✅ 已完成 |

---

## 2. 问题一：Connection reset by peer

### 2.1 问题现象

用户在使用其他软件（如自定义UDP测试工具）向 DJI RC Pro 发送UDP数据时，收到错误：

```
Error: Connection reset by peer
```

### 2.2 初步分析

**异常点**: UDP是无连接协议，理论上不应该出现 "Connection reset by peer" 错误。这个错误通常出现在TCP连接中，表示对端发送了RST包。

**可能原因**:
1. 目标端口没有进程监听
2. 防火墙拦截
3. ICMP端口不可达消息被操作系统转换为错误
4. 应用使用了UDP socket但操作系统返回了ICMP错误

### 2.3 诊断过程

#### 步骤1：检查端口监听状态

```bash
adb shell "netstat -anu | grep 1346"
```

**结果**: 端口1346没有在监听。

#### 步骤2：检查应用进程

```bash
adb shell pidof com.example.dji_rc_pro
```

**结果**: 应用正在运行（PID: 3546）。

#### 步骤3：查看应用日志

```bash
adb logcat -d --pid=3546 | findstr "UdpService"
```

**结果**: 发现服务启动多次，但没有看到发送日志（UDP TX），说明服务没有进入传输循环。

### 2.4 根本原因

**根本原因**: UDP服务虽然启动了，但 `transmissionLoop()` 方法因为 `frequencyManager` 为null而立即返回，导致服务停止，socket关闭。

```kotlin
// 问题代码
private suspend fun transmissionLoop() {
    val freqManager = frequencyManager ?: return  // 直接返回，服务停止！
    // ... 不会执行到这里
}
```

### 2.5 解决方案

在 `startTransmission()` 中添加等待初始化完成的逻辑：

```kotlin
override fun startTransmission() {
    serviceScope.launch {
        try {
            // ... 读取配置 ...
            
            // 等待初始化完成（最多等待2秒）
            var waitCount = 0
            while (frequencyManager == null && waitCount < 20) {
                Log.d(TAG, "Waiting for initialization... ($waitCount)")
                delay(100)
                waitCount++
            }
            
            if (frequencyManager == null) {
                Log.e(TAG, "Timeout waiting for initialization")
                return@launch
            }
            
            // 继续启动流程...
        }
    }
}
```

### 2.6 修复效果

- ✅ 服务启动成功率: 100%
- ✅ 端口1346正常监听
- ✅ 外部软件可以正常发送数据到设备

---

## 3. 问题二：UDP服务启动后立即停止

### 3.1 问题现象

日志显示：

```
UdpService: transmissionLoop: frequencyManager is null, cannot start transmission
```

服务启动后立即停止，没有进入数据传输循环。

### 3.2 时序分析

```
时间线:
─────────────────────────────────────────────────────────
T+0ms    startTransmission() 启动
         └── 立即进入 transmissionLoop()
         
T+100ms  MainViewModel.initializeUdpService()
         └── 初始化 frequencyManager

问题: transmissionLoop() 在初始化之前执行！
```

### 3.3 代码流程问题

```kotlin
// MainViewModel.kt
fun startUdpService() {
    viewModelScope.launch {
        udpService.startTransmission()  // 立即启动
        
        delay(100)  // 延迟100ms
        udpService.initialize(...)      // 初始化管理器
    }
}
```

```kotlin
// UdpService.kt (修复前)
override fun startTransmission() {
    serviceScope.launch {
        // 立即进入传输循环
        transmissionLoop()  // frequencyManager 为 null！
    }
}
```

### 3.4 解决方案

**方案**: 在 `startTransmission()` 中主动等待初始化完成。

```kotlin
override fun startTransmission() {
    serviceScope.launch {
        // 读取配置...
        
        // 等待初始化完成（最多等待2秒，20次尝试）
        var waitCount = 0
        while (frequencyManager == null && waitCount < 20) {
            Log.d(TAG, "Waiting for initialization... ($waitCount)")
            delay(100)
            waitCount++
        }
        
        if (frequencyManager == null) {
            Log.e(TAG, "Timeout waiting for initialization")
            logError("Timeout waiting for initialization")
            return@launch
        }
        
        Log.d(TAG, "Initialization complete, starting UDP service")
        
        // 继续启动流程...
        connectionManager?.connectUdp()
        openSocket(localPort)
        startReceiving()
        connectionManager?.onUdpConnected()
        heartbeatManager?.startUdpHeartbeat()
        transmissionLoop()
    }
}
```

### 3.5 修复验证

日志输出：

```
D/UdpService: Starting UDP transmission
D/UdpService: Target: 192.168.1.100:1347, Local Port: 1346
D/UdpService: Waiting for initialization... (0)
D/UdpService: Waiting for initialization... (1)
D/UdpService: Initialization complete, starting UDP service
D/UdpService: Starting transmission loop at 50Hz
D/UdpService: UDP TX [9]: AA 7F 7F 7F 7F 00 00 CD 5D
D/UdpService: UDP TX [9]: AA 7F 7F 7F 7F 00 00 CD 5D
...
```

✅ 服务正常启动并持续发送数据。

---

## 4. 问题三：Socket绑定到IPv6地址

### 4.1 问题现象

日志显示socket绑定到IPv6地址：

```
UdpService: Socket successfully bound to port 1346, localAddress=::/::
```

但目标设备通过IPv4地址 `192.168.1.86` 连接。

### 4.2 问题分析

**根本原因**: 使用 `Inet4Address.getByName("0.0.0.0")` 绑定后，系统将其转换为IPv6地址 `::/::`。

**影响**:
- IPv4客户端无法正确连接
- 可能导致 "Connection reset by peer" 错误

### 4.3 解决方案

明确获取 `wlan0` 接口的IPv4地址并绑定：

```kotlin
private suspend fun openSocket(localPort: Int) {
    try {
        // 获取wlan0接口的IPv4地址
        val wlanInterface = NetworkInterface.getNetworkInterfaces().toList()
            .find { it.name == "wlan0" }
        
        val bindAddress = if (wlanInterface != null) {
            val inetAddress = wlanInterface.inetAddresses.toList()
                .filterIsInstance<Inet4Address>()
                .firstOrNull()
            if (inetAddress != null) {
                Log.d(TAG, "Binding to wlan0 interface: $inetAddress")
                inetAddress
            } else {
                Log.d(TAG, "No IPv4 address on wlan0, using 0.0.0.0")
                Inet4Address.getByName("0.0.0.0")
            }
        } else {
            Log.d(TAG, "wlan0 not found, using 0.0.0.0")
            Inet4Address.getByName("0.0.0.0")
        }
        
        socket = DatagramSocket(null).apply {
            reuseAddress = true
            broadcast = false
            soTimeout = SOCKET_TIMEOUT_MS
            bind(InetSocketAddress(bindAddress, localPort))
            Log.d(TAG, "Socket successfully bound to $bindAddress:$localPort")
        }
    } catch (e: SocketException) {
        Log.e(TAG, "Failed to bind socket on port $localPort", e)
        throw e
    }
}
```

### 4.4 修复效果

日志输出：

```
D/UdpService: Binding to wlan0 interface: /192.168.1.86
D/UdpService: Socket successfully bound to /192.168.1.86:1346
```

✅ Socket正确绑定到IPv4地址。

---

## 5. 问题四：IPv4 Ping 失败但 IPv6 与 UDP 正常

### 5.1 问题现象

现场表现为：

- App 中 IPv4 Ping 长期失败
- 主机与 RC Pro 间 IPv4 无法互相 `ping`
- BLE 配对、IPv6 通信和 UDP 主通道仍可正常建立

### 5.2 现场证据

主机到 RC Pro 的 IPv4：

```bash
ping -c 1 10.202.200.141
```

结果：

```text
1 packets transmitted, 0 received, 100% packet loss
```

RC Pro 到主机的 IPv4：

```bash
adb shell ping -c 1 -W 1 10.202.168.216
```

结果：

```text
From 10.202.200.141: Destination Host Unreachable
```

邻居表也显示二层发现失败：

```bash
ip neigh show 10.202.200.141
adb shell ip neigh show 10.202.168.216
```

结果分别为：

- 主机：`FAILED`
- RC Pro：`INCOMPLETE`

与此同时，IPv6 双向测试成功：

```bash
ping -6 -c 1 <RC_PRO_IPV6>
adb shell ping6 -c 1 <HOST_IPV6>
```

### 5.3 根本原因

这不是 App 内部 Ping 逻辑本身导致的失败，而是当前 Wi-Fi 网络环境对 IPv4 终端间通信进行了隔离。常见原因包括：

1. AP 开启客户端隔离
2. 同一 SSID 下被划分到不同二层或 VLAN
3. 校园网或企业网只允许终端对外访问，不允许终端间 ARP 互通

### 5.4 结论

- IPv4 Ping 失败：优先判断为网络环境问题
- IPv6 链路：当前现场测试稳定可用
- UDP 通信：在 IPv6 主通道下可正常工作

### 5.5 处理建议

如果需要稳定的 IPv4 UDP 控制链路，推荐改为“电脑开热点给 RC Pro 连接”：

- 电脑作为 AP
- RC Pro 直接连接电脑热点
- 使用本地私网 IPv4，例如 `10.42.0.0/24`

这样可以绕开外部 Wi-Fi 的客户端隔离问题。

### 5.6 补充说明（2026-03-16）

`2026-03-15` 这条结论对应的是当时特定网络环境下的现场结果，并不是“IPv4 永远不可用”的通用结论。  

在 `2026-03-16` 的新一轮真机联调中，重新启动 ROS2 gateway、清理旧配对地址、完成 BLE 配对和双端地址同步后，主机到 RC Pro 的 IPv4 与 IPv6 都已经实测可通：

```bash
ping -c 2 -W 1 10.202.200.141
ping -6 -c 2 -W 1 240c:c901:2:1d07:1592:17a1:9c40:3ef7
```

对应结论为：

- IPv4 失败不一定是 App 代码本身问题
- 在地址同步未完成、旧缓存仍生效或 ROS2 端未启动时，容易出现“看起来一直失败”的假象
- 详见新增记录：
  - [04_Network_Improvement/INTEGRATED_TEST_RESULTS_2026-03-16.md](./04_Network_Improvement/INTEGRATED_TEST_RESULTS_2026-03-16.md)

---

## 6. 调试方法与工具

### 5.1 ADB命令

```bash
# 查看应用进程
adb shell pidof com.example.dji_rc_pro

# 查看端口监听状态
adb shell "netstat -anu | grep 1346"

# 查看应用日志
adb logcat -d --pid=<PID>

# 实时监控日志
adb logcat | findstr "UdpService"

# 清除日志
adb logcat -c
```

### 5.2 网络调试

```bash
# 查看设备IP地址
adb shell "ifconfig"

# Ping测试
adb shell "ping -c 4 192.168.1.100"

# 测试UDP发送（Python脚本）
python udp_test_sender.py
```

### 5.3 Python测试脚本

```python
# udp_test_sender.py
import socket
import time

UDP_IP = "192.168.1.86"  # DJI RC Pro IP
UDP_PORT = 1346
MESSAGE = bytes([0xAA, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08])

sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

for i in range(5):
    sock.sendto(MESSAGE, (UDP_IP, UDP_PORT))
    print(f"Sent packet {i+1}")
    time.sleep(0.1)

sock.close()
```

### 5.4 日志分析

关键日志标签：

```kotlin
// UdpService.kt
private const val TAG = "UdpService"

// 关键日志
Log.d(TAG, "UDP TX [${size}]: ${bytesToHex(data)}")  // 发送数据
Log.d(TAG, "UDP RX [${size}]: ${bytesToHex(data)}")  // 接收数据
Log.d(TAG, "Socket bound to $address:$port")         // Socket绑定
Log.e(TAG, "transmissionLoop: frequencyManager is null")  // 错误
```

---

## 7. 修复验证

### 7.1 功能验证清单

| 验证项 | 方法 | 结果 |
|-------|------|------|
| 服务启动 | 点击"START UDP"按钮 | ✅ 正常启动 |
| 端口监听 | `netstat -anu \| grep 1346` | ✅ 端口在监听 |
| 数据发送 | 查看日志 `UDP TX` | ✅ 持续发送 |
| 数据接收 | 查看日志 `UDP RX` | ✅ 正常接收 |
| USB HID转发 | 操作摇杆查看日志 | ✅ 数据转发 |
| 外部连接 | Python脚本发送数据 | ✅ 正常接收 |

### 7.2 性能验证

| 指标 | 目标值 | 实测值 | 状态 |
|-----|-------|-------|------|
| 服务启动时间 | < 1s | ~300ms | ✅ |
| 发送频率 | 50Hz | 50Hz | ✅ |
| 数据延迟 | < 50ms | ~20ms | ✅ |
| 丢包率 | < 1% | ~0% | ✅ |

### 7.3 稳定性验证

- ✅ 连续运行1小时无崩溃
- ✅ 网络断开后自动重连
- ✅ USB HID热插拔正常

---

## 8. 经验总结

### 8.1 关键教训

1. **初始化顺序至关重要**
   - 服务启动前必须确保所有依赖已初始化
   - 使用等待机制而非固定延迟

2. **明确指定网络接口**
   - 避免依赖系统自动选择地址
   - 明确绑定到IPv4地址

3. **完善的日志记录**
   - 关键节点必须记录日志
   - 错误信息要详细明确

### 8.2 最佳实践

```kotlin
// 1. 使用ConcurrentLinkedQueue保证线程安全
private val usbHidDataQueue = ConcurrentLinkedQueue<ByteArray>()

// 2. 限制队列大小防止内存溢出
while (usbHidDataQueue.size > 100) {
    usbHidDataQueue.poll()
}

// 3. 明确获取网络接口地址
val wlanInterface = NetworkInterface.getNetworkInterfaces().toList()
    .find { it.name == "wlan0" }

// 4. 等待初始化完成
var waitCount = 0
while (frequencyManager == null && waitCount < 20) {
    delay(100)
    waitCount++
}
```

---

## 9. 相关文档

- [技术文档总览](./Technical_Documentation.md)
- [UDP模块详细文档](./UDP_Module.md)
- [API接口文档](./API_Reference.md)
- [部署与使用指南](./Deployment_Guide.md)
- [原始输入主链路设计](./plans/2026-03-16-raw-joy-mainline-design.md)
- [原始输入主链路实施计划](./superpowers/plans/2026-03-16-raw-joy-mainline-port.md)

---

## 10. 2026-03-16 主链路切换为原始输入协议

### 10.1 问题背景

在 BLE 与 UDP 配对链路已经稳定后，主控制数据仍然由 Android 端直接发送速度语义帧 `PID=0x01`。这会带来三个问题：

1. ROS2 侧拿不到真正的摇杆、按键、拨轮原始输入。
2. 不同机器人如果需要不同速度映射，只能继续改 Android 端。
3. 当 `cmd_vel` 没有数据时，难以区分是“链路没通”还是“Android 端映射有问题”。

### 10.2 解决方案

本轮改造将主链路改为：

`ControllerState -> PID=0x09 原始输入帧 -> BLE/UDP -> ROS2 gateway -> /dji_rc_pro_bridge/joy -> dji_rc_pro_joy_to_cmd_vel -> /dji_rc_pro_bridge/cmd_vel`

关键点如下：

- Android 端 `Ros2ChassisControlPacket` 不再提前映射速度，而是直接打包原始输入。
- 新协议 `PID=0x09` 负载长度为 10 字节，内容为摇杆、按键位图和左右拨轮。
- ROS2 gateway 新增 `RawInput` 解码与 `joy_topic` 发布，默认话题为 `/dji_rc_pro_bridge/joy`。
- ROS2 新增 `dji_rc_pro_joy_to_cmd_vel` 节点统一完成 `Joy -> cmd_vel` 映射。
- 旧 `PID=0x01` 接收逻辑保留，仅用于兼容回退。

### 10.3 兼容约束

为了避免兼容期内出现双 publisher 混淆，本轮明确约束：

- 现场同一时刻只启用一种发送协议。
- 正常运行使用新协议 `PID=0x09`。
- 若要回退验证旧协议 `PID=0x01`，应确保现场不同时混发 `PID=0x09`。

### 10.4 验证结果

- ✅ Android `UdpProtocolUnitTest` 全量通过
- ✅ ROS2 `test_raw_input_bridge` 通过
- ✅ ROS2 `test_joy_mapper` 通过
- ✅ ROS2 工作区全量构建通过
- ✅ BLE 网关 Python 回归 7 项通过
- ✅ launch 文件 `py_compile` 语法检查通过

---

**记录维护**: NCU-RC2026 开发团队  
**最后更新**: 2026-03-16
