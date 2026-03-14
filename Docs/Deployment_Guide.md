# 部署与使用指南

**文档版本**: 1.1  
**最后更新**: 2026-03-15  
**适用范围**: DJI RC Pro Controller App (NCU-RC2026)

---

## 目录

1. [环境准备](#1-环境准备)
2. [项目配置](#2-项目配置)
3. [编译构建](#3-编译构建)
4. [安装部署](#4-安装部署)
5. [使用指南](#5-使用指南)
6. [故障排除](#6-故障排除)
7. [电脑热点直连 RC Pro](#7-电脑热点直连-rc-pro)

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

### 2.3 配置网络参数（可选）

默认配置位于 `ConfigRepository.kt`，可在应用内修改：

```kotlin
// 默认配置
const val DEFAULT_TARGET_IP = "192.168.1.100"
const val DEFAULT_TARGET_PORT = 1347
const val DEFAULT_LOCAL_PORT = 1346
const val DEFAULT_FREQUENCY_HZ = 50
```

---

## 3. 编译构建

### 3.1 清理项目

```bash
./gradlew clean
```

### 3.2 编译 Debug 版本

```bash
./gradlew :app:assembleDebug
```

**输出位置**: `app/build/outputs/apk/debug/app-debug.apk`

### 3.3 编译 Release 版本

```bash
./gradlew :app:assembleRelease
```

**输出位置**: `app/build/outputs/apk/release/app-release.apk`

### 3.4 构建变体

| 变体 | 命令 | 说明 |
|-----|------|------|
| Debug | `./gradlew assembleDebug` | 开发调试版本 |
| Release | `./gradlew assembleRelease` | 发布版本 |

---

## 4. 安装部署

### 4.1 连接设备

1. 使用 USB-C 线连接 DJI RC Pro 到电脑
2. 在遥控器上启用 **USB调试**：
   - 设置 → 关于 → 连续点击版本号7次开启开发者模式
   - 设置 → 开发者选项 → 开启 USB调试

### 4.2 验证连接

```bash
# 查看连接的设备
adb devices

# 输出示例
List of devices attached
1234567890ABCDEF    device
```

### 4.3 安装 APK

#### 方法1：使用 ADB 命令

```bash
# 安装 Debug 版本
adb install app/build/outputs/apk/debug/app-debug.apk

# 覆盖安装（保留数据）
adb install -r app/build/outputs/apk/debug/app-debug.apk

# 卸载后安装
adb uninstall com.example.dji_rc_pro
adb install app/build/outputs/apk/debug/app-debug.apk
```

#### 方法2：使用 Android Studio

1. 点击 **Run** 按钮（▶️）
2. 选择连接的设备
3. 自动编译并安装

### 4.4 验证安装

```bash
# 检查应用进程
adb shell pidof com.example.dji_rc_pro

# 查看应用信息
adb shell pm list packages | grep dji_rc_pro
```

---

## 5. 使用指南

### 5.1 启动应用

在 DJI RC Pro 上：
1. 打开应用列表
2. 点击 **NCU-RC2026** 图标
3. 授予必要权限（存储、网络等）

### 5.2 配置网络

#### 步骤1：进入高级设置

点击主界面 **"ADVANCED"** 按钮

#### 步骤2：配置目标设备

| 参数 | 示例值 | 说明 |
|-----|-------|------|
| Target IP | 192.168.1.100 | 目标设备IP（Ubuntu/ROS） |
| Target Port | 1347 | 目标设备端口 |
| Local Port | 1346 | 本地监听端口 |

#### 步骤3：配置发送频率

- 默认: 50Hz
- 范围: 1-1000Hz
- 推荐: 50-100Hz

#### 步骤4：保存配置

点击 **"SAVE"** 按钮保存

### 5.3 启动 UDP 服务

#### 步骤1：返回主界面

点击 **"BACK"** 返回主界面

#### 步骤2：启动服务

点击 **"START UDP"** 按钮

**成功标志**:
- 按钮变为 **"STOP UDP"**
- 状态显示 **"UDP Running"**
- 日志显示数据发送

### 5.4 连接 USB HID

#### 自动连接

应用启动时会自动尝试连接 USB HID 设备（DJI RC Pro 内置）

#### 手动连接

如果未自动连接：
1. 确保遥控器已开机
2. 点击 **"RECONNECT USB"** 按钮
3. 等待连接成功

### 5.5 查看数据日志

#### 实时日志

主界面显示：
- 发送数据包计数
- 接收数据包计数
- 连接状态
- 当前频率

#### 详细日志

```bash
# 查看实时日志
adb logcat | findstr "UdpService"

# 查看特定标签日志
adb logcat -s UdpService:D
```

### 5.6 停止服务

点击 **"STOP UDP"** 按钮停止服务

---

## 6. 故障排除

### 6.1 常见问题

#### Q1: 安装失败 "INSTALL_FAILED_ALREADY_EXISTS"

**原因**: 应用已存在

**解决**:
```bash
adb uninstall com.example.dji_rc_pro
adb install app/build/outputs/apk/debug/app-debug.apk
```

#### Q2: "Connection reset by peer" 错误

**原因**: UDP服务未正确启动

**解决**:
1. 检查服务状态
2. 重新启动应用
3. 查看日志排查问题

参考: [问题诊断与修复记录](./Troubleshooting_Log.md)

#### Q3: 无法连接 USB HID

**原因**: 权限问题或设备未识别

**解决**:
1. 检查 USB 调试是否开启
2. 重新插拔 USB 线
3. 重启遥控器

#### Q4: 数据发送但目标设备未接收

**原因**: 网络配置错误

**解决**:
1. 检查目标 IP 地址
2. 检查防火墙设置
3. 使用 ping 测试连通性

```bash
adb shell "ping -c 4 192.168.1.100"
```

### 6.2 调试命令

```bash
# 查看应用日志
adb logcat -d --pid=$(adb shell pidof com.example.dji_rc_pro)

# 查看网络状态
adb shell "netstat -anu | grep 1346"

# 查看设备IP
adb shell "ifconfig wlan0"

# 清除应用数据
adb shell pm clear com.example.dji_rc_pro

# 强制停止应用
adb shell am force-stop com.example.dji_rc_pro
```

### 6.3 日志分析

#### 正常启动日志

```
D/UdpService: Starting UDP transmission
D/UdpService: Target: 192.168.1.100:1347, Local Port: 1346
D/UdpService: Initialization complete, starting UDP service
D/UdpService: Socket successfully bound to /192.168.1.86:1346
D/UdpService: Starting transmission loop at 50Hz
D/UdpService: UDP TX [9]: AA 7F 7F 7F 7F 00 00 CD 5D
```

#### 错误日志

```
E/UdpService: transmissionLoop: frequencyManager is null
E/UdpService: Failed to bind socket on port 1346
```

### 6.4 性能优化

#### 降低发送频率

如果网络不稳定，降低频率：
- 推荐: 30-50Hz
- 最低: 10Hz

#### 优化网络环境

- 使用 5GHz WiFi
- 减少网络干扰
- 确保信号强度 > -60dBm

---

## 7. 电脑热点直连 RC Pro

当现网 IPv4 被隔离、但希望稳定使用 UDP 时，推荐由 Ubuntu 电脑直接开启热点给 RC Pro 连接。

### 7.1 适用场景

- 当前 Wi-Fi 下 IPv4 双向 Ping 失败
- 需要固定、可控的本地 IPv4 网段
- 需要优先保证 ROS2 UDP 控制链路稳定

### 7.2 创建热点

```bash
nmcli connection add type wifi ifname wlo1 con-name rcpro-hotspot autoconnect no ssid RCProHotspot
nmcli connection modify rcpro-hotspot \
  802-11-wireless.mode ap \
  802-11-wireless.band bg \
  ipv4.method shared \
  ipv4.addresses 10.42.0.1/24 \
  ipv6.method disabled \
  wifi-sec.key-mgmt wpa-psk \
  wifi-sec.psk NCURC2026
nmcli connection up rcpro-hotspot
```

说明：

- 热点接口为 `wlo1`
- 电脑热点地址固定为 `10.42.0.1`
- RC Pro 连入后通常会拿到 `10.42.0.x`
- 开热点后电脑会从当前外部 Wi-Fi 断开，这是正常行为

### 7.3 RC Pro 连接与验证

1. 在 RC Pro 上打开 Wi-Fi
2. 连接 `RCProHotspot`
3. 输入密码 `NCURC2026`
4. 验证 RC Pro 是否拿到 IPv4：

```bash
adb shell ip -4 addr show wlan0
```

### 7.4 ROS2 端启动

```bash
cd /home/xuan/Tools/Dji_RC_Pro
TRANSPORT_MODE=ble_udp ./scripts/run_dji_rc_pro_gateway.sh
```

如果已有后台会话，建议重启：

```bash
tmux kill-session -t rcpro_ros2_bleudp
tmux new-session -d -s rcpro_ros2_bleudp 'cd /home/xuan/Tools/Dji_RC_Pro && TRANSPORT_MODE=ble_udp ./scripts/run_dji_rc_pro_gateway.sh'
```

### 7.5 App 配置建议

- 传输模式：`BLE + UDP`
- 优先地址族：IPv4
- 手动模式下目标 IPv4：`10.42.0.1`
- 控制端口：`1387`

### 7.6 防火墙

如果主机启用了 `ufw`，需要放行：

```bash
sudo ufw allow 1387/udp
sudo ufw allow 1388/udp
```

### 7.7 快速验证

```bash
# 主机 Ping RC Pro
ping -c 1 10.42.0.2

# RC Pro Ping 主机
adb shell ping -c 1 -W 1 10.42.0.1

# ROS2 查看链路状态
source /opt/ros/humble/setup.bash
source /home/xuan/Tools/Dji_RC_Pro/ros2_ws_dji_rc_pro/install/setup.bash
timeout 5s ros2 topic echo --once /dji_rc_pro_bridge/transport_status
```

---

## 8. 更新维护

### 8.1 更新应用

```bash
# 1. 拉取最新代码
git pull origin main

# 2. 重新编译
./gradlew :app:assembleDebug

# 3. 重新安装
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### 8.2 备份配置

应用配置存储在设备本地，卸载后会丢失。如需保留：

```bash
# 备份应用数据
adb backup -f dji_rc_pro_backup.ab com.example.dji_rc_pro

# 恢复应用数据
adb restore dji_rc_pro_backup.ab
```

---

## 9. 相关文档

- [技术文档总览](./Technical_Documentation.md)
- [UDP模块详细文档](./UDP_Module.md)
- [问题诊断与修复记录](./Troubleshooting_Log.md)
- [API接口文档](./API_Reference.md)

---

**文档维护**: NCU-RC2026 开发团队  
**最后更新**: 2026-03-15
