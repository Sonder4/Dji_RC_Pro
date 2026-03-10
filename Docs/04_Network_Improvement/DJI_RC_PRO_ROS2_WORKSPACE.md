# DJI RC Pro 独立 ROS2 工作空间说明

**最后更新**: 2026-03-10  
**适用目录**: `/home/xuan/Tools/Dji_RC_Pro`  
**目标**: 说明 `ros2_ws_dji_rc_pro/` 如何作为独立工作空间承接 DJI RC Pro 的 `udp_only`、`ble_only`、`ble_udp` 三种模式，并给出当前可重复执行的构建、启动与验收方法。

---

## 1. 工作空间定位

`ros2_ws_dji_rc_pro/` 是 RC Pro 通信主线的独立 ROS2 工作空间，与原 `mcu_comm` 主工程隔离。

目录结构：

```text
ros2_ws_dji_rc_pro/
└── src/
    └── dji_rc_pro_bridge/
        ├── launch/
        │   ├── dji_rc_pro_gateway.launch.py
        │   └── dji_rc_pro_stack.launch.py
        ├── msg/ChassisCtrl.msg
        ├── src/protocol/
        └── src/tools/
            ├── dji_rc_pro_gateway.cpp
            └── dji_rc_pro_ble_gateway.py
```

默认节点与话题：

- 节点：
  - `dji_rc_pro_gateway`
  - `dji_rc_pro_ble_gateway`
- 输出话题：
  - `/dji_rc_pro_bridge/cmd_vel`
  - `/dji_rc_pro_bridge/chassis_ctrl_raw`
  - `/dji_rc_pro_bridge/ble/control_frame`
  - `/dji_rc_pro_bridge/transport_status`
- 默认状态输入话题：
  - `/mcu_comm_node/status`

这样可以单独启动 RC Pro 链路，而不和原工程节点名、launch 入口冲突。

---

## 2. 构建

在仓库根目录执行：

```bash
./scripts/build_ros2_dji_rc_pro_ws.sh
```

该脚本会：

1. `source /opt/ros/humble/setup.bash`
2. 构建 `ros2_ws_dji_rc_pro/`
3. 把输出写到 `logs/ros2/colcon_build_*.log`

2026-03-10 已验证通过：

- `GRADLE_USER_HOME=/tmp/gradle ./gradlew testDebugUnitTest assembleDebug`
- `./scripts/build_ros2_dji_rc_pro_ws.sh`

---

## 3. 启动方式

统一入口脚本：

```bash
./scripts/run_dji_rc_pro_gateway.sh
```

该脚本会根据 `TRANSPORT_MODE` 自动选择 launch：

- `udp_only` -> `dji_rc_pro_gateway.launch.py`
- `ble_only` -> `dji_rc_pro_stack.launch.py`
- `ble_udp` -> `dji_rc_pro_stack.launch.py`

也保留了两个包装脚本：

- `./scripts/run_dji_rc_pro_ble_only.sh`
- `./scripts/run_dji_rc_pro_stack.sh`

### 3.1 `udp_only`

```bash
TRANSPORT_MODE=udp_only ./scripts/run_dji_rc_pro_gateway.sh
```

### 3.2 `ble_only`

```bash
TRANSPORT_MODE=ble_only ./scripts/run_dji_rc_pro_gateway.sh
```

或：

```bash
./scripts/run_dji_rc_pro_ble_only.sh
```

### 3.3 `ble_udp`

```bash
TRANSPORT_MODE=ble_udp ./scripts/run_dji_rc_pro_gateway.sh
```

或：

```bash
./scripts/run_dji_rc_pro_stack.sh
```

常用环境变量：

```bash
PAIR_CODE=NCURC2026 \
CMD_VEL_TOPIC=/dji_rc_pro_bridge/cmd_vel \
RAW_TOPIC=/dji_rc_pro_bridge/chassis_ctrl_raw \
STATUS_TOPIC=/mcu_comm_node/status \
./scripts/run_dji_rc_pro_gateway.sh
```

日志默认写入：

- `logs/ros2/dji_rc_pro_gateway_*.log`
- `logs/ros2/dji_rc_pro_ble_only_*.log`
- `logs/ros2/dji_rc_pro_stack_*.log`

---

## 4. 三种模式的 ROS2 行为

## 4.1 `udp_only`

`dji_rc_pro_gateway.cpp` 负责：

- 监听 IPv4/IPv6 discovery socket
- 处理 `probe -> offer -> pair_request -> pair_ack`
- 接收 UDP `CHASSIS_CTRL`
- 发布 `/dji_rc_pro_bridge/chassis_ctrl_raw` 与 `/dji_rc_pro_bridge/cmd_vel`

## 4.2 `ble_only`

ROS2 只保留 BLE GATT 外设，不打开 UDP discovery/control socket。

GATT 组成固定为：

- `pair_control`
- `control_stream`

工作方式：

1. Android 写 `pair_control`
2. BLE 网关授权并建立 lease
3. Android 连续写 `control_stream`
4. BLE 网关发布 `/dji_rc_pro_bridge/ble/control_frame`
5. `dji_rc_pro_gateway` 订阅该话题并解码 `CHASSIS_CTRL`

## 4.3 `ble_udp`

ROS2 同时运行 UDP 网关与 BLE GATT 外设。

GATT 组成固定为：

- `pair_control`
- `network_info`
- `status`
- `control_stream`

工作方式：

1. Android 通过 BLE 建会话
2. 读回 `network_info` 与 `status`
3. Android 持久化 `selected_address / control_port`
4. Android 启动 UDP
5. `transport_status` 出现 `udp_active=1`
6. BLE 退为会话和兜底链路

为避免 `ble_udp` 首批 UDP 帧在 lease 尚未建立时被误丢弃，`dji_rc_pro_gateway.cpp` 还实现了 BLE 到 UDP 的临时租约提升逻辑。

---

## 5. 观察与验收

ROS2 侧常用观察命令：

```bash
source /opt/ros/humble/setup.bash
source ros2_ws_dji_rc_pro/install/setup.bash

ros2 topic echo /dji_rc_pro_bridge/chassis_ctrl_raw
ros2 topic echo /dji_rc_pro_bridge/cmd_vel
ros2 topic echo /dji_rc_pro_bridge/ble/control_frame
ros2 topic echo /dji_rc_pro_bridge/transport_status
```

关键日志关键字：

- UDP：`Offer sent`、`Paired client`、`CHASSIS_CTRL decoded`
- BLE only：`BLE service composition transport_mode=ble_only`、`BLE-only auth accepted`、`BLE control frame received`
- BLE + UDP：`BLE service composition transport_mode=ble_udp`、`BLE read network_info`、`udp_active=1`

Android 启动命令与 `adb` 抓日志方法，统一记录在：

- `Docs/04_Network_Improvement/INTEGRATED_TEST_RESULTS_2026-03-10.md`

---

## 6. 2026-03-10 真机复验结论

本轮对三种模式重新完成了 Android + ROS2 真机综合复验，结果如下：

| 模式 | 结果 | ROS2 侧证据 |
|---|---|---|
| `udp_only` | 通过 | `Offer sent`、`Paired client`、`CHASSIS_CTRL decoded`，`transport_status` 为 `udp_active=1 ble_active=0` |
| `ble_only` | 通过 | `BLE service composition transport_mode=ble_only characteristics=[...0002,...0004]`，`BLE-only auth accepted`，`/dji_rc_pro_bridge/ble/control_frame` 有数据 |
| `ble_udp` | 通过 | `BLE service composition transport_mode=ble_udp`，并且完整暴露 `pair_control/control_stream/network_info/status`；`transport_status` 为 `udp_active=1 ble_active=0` |

对应日志文件：

- `logs/ros2/dji_rc_pro_gateway_2026-03-10_14-38-08.log`
- `logs/ros2/dji_rc_pro_gateway_2026-03-10_14-17-45.log`
- `logs/ros2/dji_rc_pro_gateway_2026-03-10_14-35-45.log`
- `logs/adb/transport_udp_2026-03-10_14-39-19.log`
- `logs/adb/transport_ble_2026-03-10_14-18-49.log`
- `logs/adb/transport_ble_udp_2026-03-10_14-37-21.log`

完整命令和日志摘录见：

- `Docs/04_Network_Improvement/INTEGRATED_TEST_RESULTS_2026-03-10.md`

---

## 7. 与原 `mcu_comm` 的关系

当前推荐入口已经收敛到本项目内的独立工作空间：

- `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/src/tools/dji_rc_pro_gateway.cpp`
- `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/src/tools/dji_rc_pro_ble_gateway.py`

原 `mcu_comm` 主工程可以继续通过话题对接：

- 订阅 `/dji_rc_pro_bridge/cmd_vel`
- 或订阅 `/dji_rc_pro_bridge/chassis_ctrl_raw`

如需接入其它命名空间，只需要改 launch 参数，不需要修改协议。

---

## 8. 已知限制与操作注意事项

1. RC Pro 对 `pair_control` 的 `CCCD`/notify 回调仍不稳定，所以 `ble_udp` 当前以轮询读回为正式实现，不再依赖 notify 建会话。
2. BLE 外设能力依赖主机 BlueZ 与蓝牙硬件；当前验证环境为 `Intel AX101`。
3. 现场网络的最终可达路径可能是 IPv6；验收时应以 `selected_family / selected_address` 与实际日志为准。
4. 终端操作中应避免留下悬挂 PTY 或后台交互进程；长时间抓日志时优先使用一次性脚本或 `timeout` 包装，避免再次触发 `Waited for background terminal` 类问题。
