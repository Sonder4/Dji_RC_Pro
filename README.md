# DJI RC Pro ROS2 Bridge

[![Android](https://img.shields.io/badge/Android-RC%20Pro%20%28RM510%29-3DDC84)](https://www.dji.com/rc-pro)
[![ROS2](https://img.shields.io/badge/ROS2-Humble-22314E)](https://docs.ros.org/en/humble/)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.0-7F52FF)](https://kotlinlang.org/)
[![License](https://img.shields.io/badge/License-MIT-lightgrey)](./LICENSE)

`DJI RC Pro ROS2 Bridge` 是一个运行在 DJI RC Pro 上的 Android 控制端，配套一个独立的 ROS2 工作空间，用于通过 `UDP`、`BLE`、`BLE + UDP` 三种模式将摇杆与按键控制数据发送到机器人侧 ROS2 节点。

## 功能概览

- 三种传输模式：
  - `udp_only`
  - `ble_only`
  - `ble_udp`
- RC Pro 横屏控制界面，支持实体输入和虚拟摇杆
- Wi-Fi 自动发现、短码配对与地址下发
- BLE 会话建立、状态同步与 BLE-only 控制兜底
- ROS2 侧统一发布：
  - `/dji_rc_pro_bridge/cmd_vel`
  - `/dji_rc_pro_bridge/chassis_ctrl_raw`
  - `/dji_rc_pro_bridge/ble/control_frame`
  - `/dji_rc_pro_bridge/transport_status`

## 仓库结构

```text
.
├── app/                          # Android 应用
├── gradle/                       # Gradle wrapper 与版本目录
├── ros2_ws_dji_rc_pro/           # 独立 ROS2 工作空间
│   └── src/dji_rc_pro_bridge/
├── scripts/                      # 构建、启动、ADB 辅助脚本
├── DEPENDENCIES.md               # 依赖与工具清单
├── LICENSE
└── README.md
```

## 环境要求

### Android

- Android Studio Ladybug 或更高
- JDK 17 以上
- Android SDK:
  - `compileSdk = 35`
  - `minSdk = 29`
  - `targetSdk = 33`
- 设备：DJI RC Pro (RM510)

### ROS2 / 主机

- Ubuntu 22.04
- ROS2 Humble
- `colcon`
- Python 3.10+
- 蓝牙适配器，支持 BlueZ 外设模式
- 与 RC Pro 同网段的 Wi-Fi 网络

## 快速开始

### 1. Android 配置

在仓库根目录创建或修改 `local.properties`：

```properties
sdk.dir=/path/to/Android/Sdk
DJI_API_KEY=YOUR_DJI_APP_KEY
```

说明：

- `DJI_API_KEY` 不在仓库中提交
- 发布版 `README` 不提供真实密钥，只使用占位符

### 2. Android 构建

```bash
./gradlew testDebugUnitTest assembleDebug
```

安装到 RC Pro：

```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### 3. ROS2 构建

```bash
./scripts/build_ros2_dji_rc_pro_ws.sh
```

### 4. 启动 ROS2 gateway

```bash
TRANSPORT_MODE=udp_only ./scripts/run_dji_rc_pro_gateway.sh
```

可选模式：

- `TRANSPORT_MODE=udp_only`
- `TRANSPORT_MODE=ble_only`
- `TRANSPORT_MODE=ble_udp`

### 5. 通过 ADB 启动 App

```bash
adb shell am start -W -S -n com.example.dji_rc_pro/.MainActivity \
  --es transport_mode ble_udp \
  --es connection_mode auto_pair \
  --es pair_code CHANGE_ME_PAIR_CODE \
  --ez forget_pairing true \
  --ez start_primary true
```

## 配置方案

### Android 侧持久化配置

Android 使用 `DataStore` 持久化以下配置：

- `target_ip`
- `target_port`
- `local_port`
- `connection_mode`
- `transport_isolation_mode`
- `client_id`
- `pair_code`
- `paired_host_*`

默认值：

- `transport_isolation_mode = ble_udp`
- `connection_mode = auto_pair`
- `target_port = 1387`
- `local_port = 1346`
- `pair_code = CHANGE_ME_PAIR_CODE`

### Debug 启动参数

可以通过 ADB Intent 在启动时覆盖配置：

| 参数 | 含义 | 示例 |
|---|---|---|
| `transport_mode` | 传输模式 | `udp_only` |
| `connection_mode` | 连接模式 | `auto_pair` |
| `pair_code` | 配对短码 | `CHANGE_ME_PAIR_CODE` |
| `target_ip` | 手动目标地址 | `198.51.100.83` |
| `target_port` | 手动目标端口 | `1387` |
| `forget_pairing` | 清理已有配对 | `true` |
| `start_primary` | 启动后立即拉起主链路 | `true` |

### ROS2 启动环境变量

`scripts/run_dji_rc_pro_gateway.sh` 支持以下环境变量：

| 变量 | 默认值 | 用途 |
|---|---|---|
| `PAIR_CODE` | `CHANGE_ME_PAIR_CODE` | 配对短码 |
| `TRANSPORT_MODE` | `udp_only` | 当前通信模式 |
| `CMD_VEL_TOPIC` | `/dji_rc_pro_bridge/cmd_vel` | 速度话题 |
| `RAW_TOPIC` | `/dji_rc_pro_bridge/chassis_ctrl_raw` | 原始协议话题 |
| `STATUS_TOPIC` | `/mcu_comm_node/status` | MCU 状态输入 |

### 通信端口

| 端口 | 方向 | 含义 |
|---|---|---|
| `1388` | discovery | Wi-Fi 自动发现 |
| `1387` | control | 主控制端口 |
| `1346` | app local | RC Pro 本地 UDP 绑定端口 |

## 常用验证命令

查看设备：

```bash
adb devices -l
```

抓取 Android 侧 transport 日志：

```bash
./scripts/adb_capture_transport_logs.sh -s <serial> -m all --no-clear --dump-once
```

查看 ROS2 状态：

```bash
source /opt/ros/humble/setup.bash
source ros2_ws_dji_rc_pro/install/setup.bash
ros2 topic echo /dji_rc_pro_bridge/transport_status
```

## 文档导航

- [TRANSPORT.md](./TRANSPORT.md) : BLE、UDP 点对点链路、`ble_udp` 切换逻辑
- [PROTOCOL.md](./PROTOCOL.md) : Wi-Fi 发现配对、BLE 紧凑配对协议、控制帧格式
- [UI_SETTINGS.md](./UI_SETTINGS.md) : 主界面抽屉、设置项、保存与校验行为
- [KEY_MAPPING.md](./KEY_MAPPING.md) : 实体摇杆、滚轮、五向键和虚拟按钮映射
- [ARCHITECTURE.md](./ARCHITECTURE.md) : 原理说明、代码入口与 Android/ROS2 侧模块划分

## 依赖清单

完整依赖见：

- [DEPENDENCIES.md](./DEPENDENCIES.md)

## 许可证

本项目采用 [MIT License](./LICENSE)。
