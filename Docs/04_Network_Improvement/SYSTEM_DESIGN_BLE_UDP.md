# DJI RC Pro BLE + UDP 系统设计说明

**最后更新**: 2026-03-11  
**适用目录**: `/home/xuan/Tools/Dji_RC_Pro`  
**目标**: 固化 DJI RC Pro App 与 `ros2_ws_dji_rc_pro/` 之间的最终通信主线，实现 `udp_only`、`ble_only`、`ble_udp` 三种模式的稳定配对、地址同步、控制帧传输与真机验收。

---

## 1. 设计目标

本轮只收口 RC Pro <-> BLE/UDP <-> ROS2 主线，不扩展其它功能。最终约束如下：

1. 不引入公网服务，发现、配对和控制全部在本机 BLE/Wi-Fi 范围内完成。
2. Android 与 ROS2 都保持三种模式可切换：`udp_only`、`ble_only`、`ble_udp`。
3. `BLE + UDP` 模式中，BLE 负责首配与地址同步，UDP 负责主控制通道。
4. 设计必须能被 `adb logcat`、ROS2 日志和话题直接验证。
5. 文档以 2026-03-11 真机复验结果为准，不再保留旧的“未通过”阶段性结论。

---

## 2. 系统总览

```text
DJI RC Pro App
├── MainActivity
├── MainViewModel
│   ├── WifiDiscoveryManager
│   ├── BleManager
│   ├── UdpService
│   └── BleService
└── Ros2ChassisControlPacket
             |
             +-- BLE: pair_control / network_info / status / control_stream
             |
             +-- UDP: discovery / pair_request / pair_ack / CHASSIS_CTRL
             |
ROS2 Workspace: ros2_ws_dji_rc_pro
├── dji_rc_pro_gateway.cpp
├── dji_rc_pro_ble_gateway.py
└── launch/*.launch.py
```

链路拆成两层：

- 控制面：发现、配对、地址同步、链路状态同步。
- 数据面：`CHASSIS_CTRL` 控制帧。

最终角色分工：

- `udp_only`：纯 UDP 完成发现、配对与控制。
- `ble_only`：纯 BLE 完成授权与控制。
- `ble_udp`：BLE 建立会话并下发目标地址，UDP 切主，BLE 保留兜底能力。

---

## 3. 传输模式矩阵

| 模式 | Android 侧 | ROS2 侧 | 最终行为 |
|---|---|---|---|
| `udp_only` | 启用 Wi-Fi 自动发现与自动配对；不启 BLE 扫描与 BLE 控制 | 仅启动 UDP discovery/control socket | 纯 UDP 闭环已通过真机复验 |
| `ble_only` | 仅 BLE 扫描、授权和控制；不启 Wi-Fi discovery，不启 UDP | 仅启动 BLE GATT 外设；禁用 UDP socket | 纯 BLE 控制链路已通过真机复验 |
| `ble_udp` | BLE 与 Wi-Fi 同时启用；BLE 同步会话和地址；UDP 自动切主 | UDP 网关与 BLE GATT 同时运行；持续发布 `transport_status` | BLE 建会话后 UDP 成为主通道，已通过真机复验 |

Android 模式枚举为 `TransportIsolationMode`，保存在 `ConfigRepository` 中。

---

## 4. Android 侧设计

## 4.1 启动与配置

Android 侧最终收口包含三点：

1. `MainActivity` 在创建 `ViewModel` 前先捕获 `intent` 中的调试启动参数，通过 `DebugLaunchOverrides` 提前覆盖配置，避免 `ViewModel` 用旧模式先跑错分支。
2. `MainViewModel` 对 `startService/stopService` 做安全封装，避免后台启动直接崩溃。
3. BLE 服务改为“连接成功后再启动”，规避 RC Pro Android 10 上的后台服务限制。

## 4.2 控制帧生成统一入口

`UdpService` 与 `BleService` 都继承 `BaseTransmissionService`，统一从：

- `ControllerManager.instance.controllerState`
- `Ros2ChassisControlPacket.fromControllerState(state)`

生成协议帧。

因此实际链路固定为：

```text
摇杆/按键 -> ControllerManager -> Ros2ChassisControlPacket -> UDP 或 BLE
```

`UDP` 与 `BLE` 都不再透传原始 HID 数据。

## 4.3 UDP 发现与配对

`WifiDiscoveryManager` 负责：

- IPv4 广播 `probe`
- IPv6 组播 `probe`
- 对已知地址发 `direct probe`

协议版本为 `RC26_DISCOVERY/2`，默认端口：

- `discovery_port = 1388`
- `control_port = 1387`

基本流程：

1. Android 发送 `probe`
2. ROS2 网关回复 `offer`
3. Android 发送 `pair_request`
4. ROS2 网关回复 `pair_ack`
5. Android 保存 `host_id / selected_family / selected_address / control_port / session_id`
6. `UdpService` 持续发送 `CHASSIS_CTRL`

## 4.4 BLE 最终实现

BLE 侧使用独立 GATT Profile：

- `SERVICE_UUID = 8f231100-6e52-4f7c-9b16-1b20c1a50001`
- `PAIR_CONTROL_UUID = ...0002`
- `NETWORK_INFO_UUID = ...0003`
- `CONTROL_STREAM_UUID = ...0004`
- `STATUS_UUID = ...0005`

最终实现的关键点如下：

1. 对所有 ROS2 BLE 模式，在连接成功后、`discoverServices()` 之前先调用隐藏 `BluetoothGatt.refresh()`，清掉 RC Pro 保留的旧 GATT 缓存。
2. `ble_only` 只接受 `pair_control + control_stream` 两个特征；如果还发现 `network_info/status`，说明仍在命中旧缓存。
3. `ble_udp` 需要完整 4 个特征：`pair_control + network_info + status + control_stream`。
4. RC Pro 上 `CCCD`/notify 回调不稳定，所以 `ble_udp` 不再依赖 `pair_control notify` 建会话，而是改成轮询握手：
   - 写 `pair_control`
   - 读 `pair_control`
   - 读 `network_info`
   - 读 `status`
5. 控制特征写入时，优先使用 `WRITE_TYPE_DEFAULT`；只有目标特征不支持普通写时才退回 `WRITE_TYPE_NO_RESPONSE`。这一步是 RC Pro -> BlueZ 用户态 GATT 写入稳定落地的必要条件。

## 4.5 BLE 和 UDP 的主备切换

`BleManager.shouldTransmitControlFrames()` 与 `GatewaySession.shouldTransmitControlOverBle` 共同控制 BLE 是否还承担数据面。

最终规则：

- `ble_only`：授权成功后，BLE 一直发送 `control_stream`。
- `ble_udp` 且 `udp_active=0`：BLE 可以临时承接控制帧。
- `ble_udp` 且 `udp_active=1`：BLE 停止发送控制帧，UDP 成为主通道。

`MainViewModel.applyBleGatewayTarget()` 会把 BLE 下发的 `selected_address / control_port` 写回 `ConfigRepository`，并在 `ble_udp` 下拉起 `UdpService`。

---

## 5. ROS2 侧设计

## 5.1 独立工作空间

主线工作空间为：

```text
ros2_ws_dji_rc_pro/
└── src/dji_rc_pro_bridge/
```

核心节点与话题：

- 节点：
  - `dji_rc_pro_gateway`
  - `dji_rc_pro_ble_gateway`
- 话题：
  - `/dji_rc_pro_bridge/cmd_vel`
  - `/dji_rc_pro_bridge/chassis_ctrl_raw`
  - `/dji_rc_pro_bridge/ble/control_frame`
  - `/dji_rc_pro_bridge/transport_status`

该工作空间与原 `mcu_comm` 入口隔离，不复用原 RC Pro 网关实现。

## 5.2 `dji_rc_pro_gateway.cpp`

UDP 网关承担以下职责：

1. `probe / offer / pair_request / pair_ack / unpair`
2. `CHASSIS_CTRL` UDP 数据接收与解析
3. `ble/control_frame` 订阅与解码
4. `transport_status` 发布

最终行为细节：

- `ble_only` 时不打开 UDP discovery/control socket。
- `udp_only` 与 `ble_udp` 时同时监听 IPv4/IPv6。
- `transport_status` 按最近 2 秒内是否收到帧来发布 `udp_active` 与 `ble_active`。
- `ble_udp` 时，如果最近刚收到 BLE 控制帧，而 UDP 数据先于正式 UDP lease 到达，网关会把该 peer 提升为临时 UDP lease，避免第一批 UDP 帧被误丢弃。

`CHASSIS_CTRL` 解码后发布：

- `/dji_rc_pro_bridge/chassis_ctrl_raw`
- `/dji_rc_pro_bridge/cmd_vel`

## 5.3 `dji_rc_pro_ble_gateway.py`

BLE 网关基于 BlueZ 外设模式实现，承担：

1. 广播 ROS2 BLE 服务
2. 按模式裁剪 GATT 组成
3. 维护 BLE lease 与地址选择
4. 暴露 `network_info` 与 `status`
5. 将 `control_stream` 转发到 `/dji_rc_pro_bridge/ble/control_frame`

最终实现约束：

- `ble_only` 启动时只注册 `pair_control + control_stream`
- `ble_udp` 启动时注册 `pair_control + network_info + status + control_stream`
- 启动日志会打印 `BLE service composition transport_mode=... characteristics=[...]`，用于现场确认实际 GATT 组成
- `status` 特征直接反映 ROS2 侧 `transport_status`，其中 `u=1` 表示 UDP 已成为主通道

`ble_only` 下，`pair_control` 写入会直接触发 BLE 授权，日志关键字为：

- `BLE-only auth accepted`
- `BLE control frame received`

---

## 6. 端到端链路

## 6.1 `udp_only`

```text
RC Pro
  -> WifiDiscoveryManager send probe/direct probe
  -> ROS2 gateway returns offer
  -> RC Pro sends pair_request
  -> ROS2 gateway returns pair_ack
  -> UdpService sends 52 43 ... ED
  -> ROS2 gateway decodes CHASSIS_CTRL
```

本轮真机复验中，`udp_only` 已完成闭环，实际命中的可达地址为 IPv6。

## 6.2 `ble_only`

```text
RC Pro
  -> scan ROS2 BLE service
  -> connect
  -> refresh GATT cache
  -> discover pair_control + control_stream
  -> write pair_control
  -> BLE gateway accepts lease
  -> write control_stream
  -> ROS2 publishes /dji_rc_pro_bridge/ble/control_frame
  -> dji_rc_pro_gateway decodes CHASSIS_CTRL
```

`ble_only` 最终不依赖 `network_info/status`，而是走纯写链路完成授权和控制。

## 6.3 `ble_udp`

```text
BLE:
  connect
  -> refresh GATT cache
  -> discover full 4-char service
  -> write pair_control
  -> read pair_control
  -> read network_info
  -> read status
  -> persist selected_address/control_port

UDP:
  start UdpService
  -> send CHASSIS_CTRL
  -> ROS2 publishes transport_status udp_active=1

BLE:
  poll/read status
  -> observe udp_active=1
  -> stop sending control_stream except fallback window
```

该模式下 BLE 只负责把会话与目标地址拉起来，UDP 才是最终主通道。

---

## 7. 2026-03-11 真机复验结果

构建结果：

- Android：`GRADLE_USER_HOME=/tmp/gradle ./gradlew testDebugUnitTest assembleDebug` 通过
- ROS2：`./scripts/build_ros2_dji_rc_pro_ws.sh` 通过

三模式验收结果：

| 模式 | 结果 | 关键证据 |
|---|---|---|
| `udp_only` | 通过 | Android 日志出现 `UDP TX [18]`；ROS2 日志出现 `Offer sent`、`Paired client`，并解码出非零 `w` 值 |
| `ble_only` | 通过 | Android 日志出现 `BleService: Transmitted 18 bytes via BLE`；ROS2 日志出现 `BLE-only auth accepted`、`BLE control frame received`；`transport_status` 为 `udp_active=0 ble_active=1` |
| `ble_udp` | 通过 | Android 日志出现 `ROS2 BLE service discovered: pair=true network=true status=true control=true`、`read network_info/status`、`UDP TX [18]`、`Skipping BLE control frame - UDP remains primary`；ROS2 `transport_status` 为 `udp_active=1 ble_active=0` |

对应日志：

- `logs/adb/transport_ble_2026-03-11_11-07-15.log`
- `logs/adb/transport_udp_2026-03-11_11-15-18.log`
- `logs/adb/transport_all_2026-03-11_11-17-17.log`
- `logs/ros2/dji_rc_pro_gateway_2026-03-11_11-03-55.log`
- `logs/ros2/dji_rc_pro_gateway_2026-03-11_11-11-40.log`
- `logs/ros2/dji_rc_pro_gateway_2026-03-11_11-15-59.log`

完整过程、命令和关键字见：

- `Docs/04_Network_Improvement/INTEGRATED_TEST_RESULTS_2026-03-11.md`

---

## 8. 已知限制

1. RC Pro 对 `pair_control` 的 `CCCD`/notify 回调仍不稳定，所以 `ble_udp` 当前固定采用“写后轮询读回”的会话握手。
2. BLE 外设能力依赖宿主机 BlueZ 与硬件适配器，当前验证环境为 `Intel AX101` + `hci0`。
3. 当前现场网络中实际可达路径可能优先落到 IPv6；文档与日志都应以最终 `selected_family / selected_address` 为准，而不是假设固定 IPv4。

---

## 9. 关键文件索引

Android：

- `app/src/main/java/com/example/dji_rc_pro/MainActivity.kt`
- `app/src/main/java/com/example/dji_rc_pro/domain/config/DebugLaunchOverrides.kt`
- `app/src/main/java/com/example/dji_rc_pro/domain/config/TransportIsolationMode.kt`
- `app/src/main/java/com/example/dji_rc_pro/domain/ble/BleManager.kt`
- `app/src/main/java/com/example/dji_rc_pro/domain/discovery/WifiDiscoveryManager.kt`
- `app/src/main/java/com/example/dji_rc_pro/service/UdpService.kt`
- `app/src/main/java/com/example/dji_rc_pro/service/BleService.kt`
- `app/src/main/java/com/example/dji_rc_pro/viewmodel/MainViewModel.kt`

ROS2：

- `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/src/tools/dji_rc_pro_gateway.cpp`
- `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/src/tools/dji_rc_pro_ble_gateway.py`
- `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/launch/dji_rc_pro_gateway.launch.py`
- `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/launch/dji_rc_pro_stack.launch.py`

脚本：

- `scripts/build_ros2_dji_rc_pro_ws.sh`
- `scripts/run_dji_rc_pro_gateway.sh`
- `scripts/run_dji_rc_pro_ble_only.sh`
- `scripts/run_dji_rc_pro_stack.sh`
- `scripts/adb_capture_transport_logs.sh`
