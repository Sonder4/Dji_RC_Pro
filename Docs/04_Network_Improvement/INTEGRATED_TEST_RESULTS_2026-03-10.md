# DJI RC Pro 综合测试记录（2026-03-10）

**适用目录**: `/home/xuan/Tools/Dji_RC_Pro`  
**测试日期**: 2026-03-10  
**测试目标**: 对 `udp_only`、`ble_only`、`ble_udp` 三种传输模式做 Android + ROS2 真机综合复验，并固化最终验收结果与已知限制。

---

## 1. 测试环境

- 仓库目录：`/home/xuan/Tools/Dji_RC_Pro`
- RC Pro `adb` serial：`4QQZLC700522QY`
- Wi-Fi SSID：`NCUWLAN`
- 电脑 Wi-Fi IPv4：`10.202.168.216`
- 电脑 Wi-Fi IPv6：`240c:c901:2:3806:62d5:2587:86ad:d71b`
- RC Pro Wi-Fi IPv4：`10.202.200.141`
- RC Pro Wi-Fi IPv6：`240c:c901:2:f34:54b3:1aba:d463:d556`
- 电脑蓝牙适配器：`hci0`（Intel AX101）

---

## 2. 构建验证

### Android

执行命令：

```bash
GRADLE_USER_HOME=/tmp/gradle ./gradlew testDebugUnitTest assembleDebug
```

结果：通过

说明：

- Debug APK 已重新安装到 RC Pro
- 本轮新增的 `MainViewModel` / `BleManager` 收口补丁已纳入该构建

### ROS2

执行命令：

```bash
./scripts/build_ros2_dji_rc_pro_ws.sh
```

结果：通过

构建日志：

- `logs/ros2/colcon_build_2026-03-10_12-16-14.log`

---

## 3. 真机测试矩阵

| 模式 | 预期关键字 | 实际结果 | 结论 |
|---|---|---|---|
| `udp_only` | `Offer sent` / `Paired client` / `UDP TX [18]` / `CHASSIS_CTRL decoded` | RC Pro 通过 IPv6 direct probe 完成配对，ROS2 侧出现 `Paired client` 与 `CHASSIS_CTRL decoded`，`transport_status` 为 `udp_active=1` | 通过 |
| `ble_only` | `ROS2 BLE service discovered` / `BLE-only auth accepted` / `BLE write control_stream` / `BLE control frame received` / `/dji_rc_pro_bridge/ble/control_frame` | Android 侧已刷新 GATT 缓存并只发现 `pair_control + control_stream`；ROS2 侧已收到 `pair_control` 与持续 `control_stream` 写入 | 通过 |
| `ble_udp` | `ROS2 BLE service discovered` / `network_info` / `UDP TX [18]` / `CHASSIS_CTRL decoded` | BLE 侧完成 `pair_control` 轮询握手并读回 `network_info/status`，Android 持续 `UDP TX [18]`，`transport_status` 为 `udp_active=1` | 通过 |

---

## 4. 分模式记录

### 4.1 `udp_only`

执行命令：

```bash
./scripts/run_dji_rc_pro_gateway.sh
adb -s 4QQZLC700522QY shell am start -S -n com.example.dji_rc_pro/.MainActivity \
  --es transport_mode udp_only \
  --es connection_mode auto_pair \
  --es pair_code NCURC2026 \
  --es target_ip 10.202.168.216 \
  --ei target_port 1387 \
  --ez forget_pairing true \
  --ez start_primary true
./scripts/adb_capture_transport_logs.sh -s 4QQZLC700522QY -m udp --no-clear --dump-once
```

日志产物：

- `logs/ros2/dji_rc_pro_gateway_2026-03-10_12-20-12.log`
- `logs/adb/transport_udp_2026-03-10_12-20-57.log`

实际观察：

- Android 侧持续出现 `WifiDiscoveryManager: Sent direct probe to 10.202.168.216 via ipv4`
- ROS2 gateway 日志没有出现 `Offer sent`、`Paired client`、`CHASSIS_CTRL decoded`

附加网络证据：

- `adb shell ping -c 2 10.202.168.216` 返回 `Destination Host Unreachable`
- `adb shell ping6 -c 2 240c:c901:2:3806:62d5:2587:86ad:d71b` 为 `100% packet loss`

判断：

- 当前 `NCUWLAN` 现场网络下，RC Pro 无法直达本机 IPv4/IPv6，`udp_only` 无法完成局域网发现与配对闭环。

### 4.1.1 `udp_only` 验收通过（2026-03-10 14:38-14:39）

执行命令：

```bash
TRANSPORT_MODE=udp_only ./scripts/run_dji_rc_pro_gateway.sh
adb -s 4QQZLC700522QY shell am start -n com.example.dji_rc_pro/.MainActivity \
  --es transport_mode udp_only \
  --es connection_mode auto_pair \
  --es pair_code NCURC2026 \
  --ez forget_pairing true \
  --ez start_primary true
source /opt/ros/humble/setup.bash
source /home/xuan/Tools/Dji_RC_Pro/ros2_ws_dji_rc_pro/install/setup.bash
timeout 4 ros2 topic echo --once /dji_rc_pro_bridge/transport_status
```

新增日志产物：

- `logs/ros2/dji_rc_pro_gateway_2026-03-10_14-38-08.log`
- `logs/adb/transport_udp_2026-03-10_14-39-19.log`

实际观察：

- ROS2 侧出现：
  - `Offer sent to client=... peer=240c:c901:... family=ipv6`
  - `Paired client=... control=240c:c901:...:1387`
  - `CHASSIS_CTRL decoded: ...`
- Android 侧出现持续：
  - `UDP TX [18]: 52 43 ...`
- `transport_status` 结果：
  - `udp_active=1`
  - `ble_active=0`

判断：

- 当前环境下 `udp_only` 已恢复可用，且本轮实际走通的是 IPv6 发现与控制通道。

### 4.2 `ble_only`

执行命令：

```bash
./scripts/run_dji_rc_pro_ble_only.sh
adb -s 4QQZLC700522QY shell am start -S -n com.example.dji_rc_pro/.MainActivity \
  --es transport_mode ble_only \
  --es connection_mode auto_pair \
  --es pair_code NCURC2026 \
  --ez forget_pairing true \
  --ez start_primary true
./scripts/adb_capture_transport_logs.sh -s 4QQZLC700522QY -m ble --no-clear --dump-once
```

日志产物：

- `logs/ros2/dji_rc_pro_ble_only_2026-03-10_12-34-30.log`
- `logs/adb/transport_ble_2026-03-10_12-35-16.log`

实际观察：

- Android 侧已出现：
  - `ROS2 BLE service discovered: pair=true network=true status=true control=true`
  - `ROS2 BLE requestMtu started=true`
  - `ROS2 BLE dispatch pair probe`
  - `ROS2 BLE pair probe writeStarted=true`
- 随后 `network_info` 连续重试 6 次，均为：
  - `ROS2 BLE onCharacteristicRead uuid=...0003 status=1 bytes=0`
- 未出现：
  - `ROS2 BLE read status started=true`
  - `BLE write control_stream`
  - `CHASSIS_CTRL decoded`

附加现象：

- `MainViewModel` 多次记录 `Skipping BleService start while app is backgrounded`
- 说明 RC Pro 当前启动时序下，GATT 连上后应用仍被系统视为后台，`BleService` 自动启动被拒绝

判断：

- `ble_only` 已从“连上后卡死在服务发现前”推进到“稳定发现服务并写出 pair probe”，但仍被 `network_info` 读失败和 `BleService` 后台启动限制阻塞。

### 4.2.1 `ble_only` 复测补充（2026-03-10 13:08-13:13）

执行命令：

```bash
bash -lc 'source /opt/ros/humble/setup.bash && source ros2_ws_dji_rc_pro/install/setup.bash && \
  ros2 launch dji_rc_pro_bridge dji_rc_pro_stack.launch.py transport_mode:=ble_only pair_code:=NCURC2026 host_name:=$(hostname)'
adb -s 4QQZLC700522QY shell am start -S -n com.example.dji_rc_pro/.MainActivity \
  --es transport_mode ble_only \
  --es connection_mode auto_pair \
  --es pair_code NCURC2026
```

新增日志产物：

- `logs/adb/transport_ble_2026-03-10_13-12-37.log`
- `logs/adb/transport_ble_stack_2026-03-10_13-12-40.log`

新增观察：

- 已确认先前一处测试入口缺陷：
  - `scripts/run_dji_rc_pro_gateway.sh` 在 `ble_only` / `ble_udp` 下原先启动的是 `dji_rc_pro_gateway.launch.py`
  - 该 launch 不包含 `dji_rc_pro_ble_gateway.py`
  - 已修正为 BLE 模式自动切换到 `dji_rc_pro_stack.launch.py`
- 已确认 `BleService` 前台启动修复生效：
  - `BleService created`
  - `Starting transmission service`
  - 不再出现 `Skipping BleService start while app is backgrounded`
- 在正确启动 BLE gateway 后，RC Pro 现已稳定进入：
  - `CONNECTED`
  - `discoverServices`
  - `ROS2 BLE service discovered`
  - `requestMtu started=true`
- 之后出现两类 RC Pro 蓝牙栈阻塞：
  - `pair_control` 读回退路径：`onCharacteristicRead uuid=...0002 status=1`
  - `CCCD` 路径：`CCCD write ... writeStarted=true` 后没有 `onDescriptorWrite`，随后 `pair probe writeStarted=false`
- ROS2 侧 `dji_rc_pro_ble_gateway.py` 日志仍只有启动信息，没有任何 `BLE write pair_control` / `BLE read pair_control` / `BLE read network_info`

判断：

- 当前 BLE 主阻塞已收敛为 RC Pro Android 10 蓝牙栈与 BlueZ 外部 GATT 服务的 ATT 交互兼容性问题：
  - `readCharacteristic(pair_control/network_info)` 没有真正落到 BlueZ `ReadValue`
  - `writeDescriptor(CCCD)` 也没有完成回调
- 该问题已经不是：
  - 启动脚本选错入口
  - `BleService` 后台启动被系统拒绝
  - GATT 连接或服务发现失败

### 4.2.2 `ble_only` 纯写链路复测（2026-03-10 13:30-13:48）

执行命令：

```bash
TRANSPORT_MODE=ble_only ./scripts/run_dji_rc_pro_gateway.sh
adb -s 4QQZLC700522QY install -r app/build/outputs/apk/debug/app-debug.apk
adb -s 4QQZLC700522QY shell am start -n com.example.dji_rc_pro/.MainActivity \
  --es transport_mode ble_only \
  --es connection_mode auto_pair \
  --es pair_code NCURC2026 \
  --ez forget_pairing true \
  --ez start_primary true
```

新增日志产物：

- `logs/adb/transport_ble_2026-03-10_13-31-24.log`
- `logs/adb/transport_ble_2026-03-10_13-45-25.log`
- `logs/adb/transport_ble_2026-03-10_13-46-41.log`
- `logs/ros2/dji_rc_pro_gateway_2026-03-10_13-44-56.log`

新增代码收口：

- Android `ble_only` 不再依赖 `CCCD` / `network_info` / `status` 读回，改为：
  - 连接成功
  - 发送一次 `pair_control` 紧凑探测包
  - 本地标记 `BLE-only auth ready`
  - 直接持续写 `control_stream`
- ROS2 `dji_rc_pro_ble_gateway.py` 新增 `ble_only` 一次性授权逻辑，并修正 BlueZ GATT 注册线程模型：
  - `SystemBus + GATT 注册 + GLib loop` 统一收敛到同一后台线程

实际观察：

- Android 侧纯写路径已经打通：
  - `ROS2 BLE pair probe writeStarted=true`
  - `ROS2 BLE-only auth marked ready locally`
  - `ROS2 BLE write uuid=...0004 bytes=18 type=1 started=true`
  - `onCharacteristicWrite uuid=...0004 status=0`
- 中间尝试过 `WRITE_TYPE_DEFAULT` 兼容性修正，但 RC Pro 回调为：
  - `onCharacteristicWrite uuid=...0002 status=1`
  - `onCharacteristicWrite uuid=...0004 status=1`
  - 因此已回退为 `WRITE_TYPE_NO_RESPONSE`
- ROS2 侧仍未出现：
  - `BLE-only auth accepted`
  - `BLE control frame received`
  - `/dji_rc_pro_bridge/ble/control_frame` 话题消息

判断：

- 当前 `ble_only` 的最终阻塞已进一步收敛：
  - RC Pro Android 侧纯写链路已成立
  - BlueZ GATT 服务注册也已恢复稳定
  - 但 `WRITE_TYPE_NO_RESPONSE` 的 `pair_control/control_stream` 仍未真正落入 BlueZ 用户态 `WriteValue`
- 因此截至 2026-03-10 13:48，`ble_only` 仍未完成“控制数据进入 ROS2 topic 并被网关解码”的最终验收。

### 4.2.3 `ble_only` 验收通过（2026-03-10 14:17-14:19）

执行命令：

```bash
TRANSPORT_MODE=ble_only ./scripts/run_dji_rc_pro_gateway.sh
adb -s 4QQZLC700522QY install -r app/build/outputs/apk/debug/app-debug.apk
adb -s 4QQZLC700522QY logcat -c
adb -s 4QQZLC700522QY shell am force-stop com.example.dji_rc_pro
adb -s 4QQZLC700522QY shell am start -n com.example.dji_rc_pro/.MainActivity \
  --es transport_mode ble_only \
  --es connection_mode auto_pair \
  --es pair_code NCURC2026 \
  --ez forget_pairing true \
  --ez start_primary true
source /opt/ros/humble/setup.bash
source /home/xuan/Tools/Dji_RC_Pro/ros2_ws_dji_rc_pro/install/setup.bash
timeout 4 ros2 topic echo --once /dji_rc_pro_bridge/ble/control_frame
```

新增日志产物：

- `logs/ros2/dji_rc_pro_gateway_2026-03-10_14-17-45.log`
- `logs/adb/transport_ble_2026-03-10_14-18-49.log`

新增代码收口：

- Android `BleManager` 在 `ble_only` 连接后先调用隐藏 `BluetoothGatt.refresh()`，再执行 `discoverServices()`
- Android 在 ROS2 特征支持 `PROPERTY_WRITE` 时改为优先使用 `WRITE_TYPE_DEFAULT`
- ROS2 `dji_rc_pro_ble_gateway.py` 启动时打印实际 GATT 组成，确认 `ble_only` 只注册：
  - `8f231100-...0002` `pair_control`
  - `8f231100-...0004` `control_stream`

实际观察：

- ROS2 侧明确打印：
  - `BLE service composition transport_mode=ble_only characteristics=[...0002,...0004]`
  - `BLE write pair_control bytes=76`
  - `BLE-only auth accepted client=73a521093ed98861 ...`
  - `BLE write control_stream bytes=18`
  - `BLE control frame received bytes=18 client=73a521093ed98861`
- Android 侧明确打印：
  - `ROS2 BLE refreshed GATT cache before service discovery`
  - `ROS2 BLE service discovered: pair=true network=false status=false control=true`
  - `ROS2 BLE characteristic properties: pair=write|write_no_response ... control=write|write_no_response`
  - `ROS2 BLE write uuid=...0002 bytes=76 type=2 started=true`
  - `ROS2 BLE write uuid=...0004 bytes=18 type=2 started=true`
- ROS2 topic 验证成功：
  - `/dji_rc_pro_bridge/ble/control_frame` 收到 18 字节控制帧

根因结论：

- 先前 `ble_only` 未通过的根因不是 BlueZ 仍在暴露旧 4 特征，也不是 Python `WriteValue` 回调本身失效
- 根因是 RC Pro Android 端保留了旧的 GATT 缓存，导致它一直按旧特征表访问；同时 `WRITE_TYPE_NO_RESPONSE` 不足以稳定穿透到当前 BlueZ 用户态路径
- 在连接后刷新 GATT 缓存，并对可写特征切换到 `WRITE_TYPE_DEFAULT` 后，BLE-only 控制链路已完成闭环

### 4.3 `ble_udp`

执行命令：

```bash
./scripts/run_dji_rc_pro_stack.sh
adb -s 4QQZLC700522QY shell am start -S -n com.example.dji_rc_pro/.MainActivity \
  --es transport_mode ble_udp \
  --es connection_mode auto_pair \
  --es pair_code NCURC2026 \
  --es target_ip 10.202.168.216 \
  --ei target_port 1387 \
  --ez forget_pairing true \
  --ez start_primary true
./scripts/adb_capture_transport_logs.sh -s 4QQZLC700522QY -m all --no-clear --dump-once
```

日志产物：

- `logs/ros2/dji_rc_pro_stack_2026-03-10_12-37-27.log`
- `logs/adb/transport_all_2026-03-10_12-38-11.log`

实际观察：

- BLE 侧与 `ble_only` 相同：
  - `ROS2 BLE service discovered`
  - `pair probe writeStarted=true`
  - `network_info` 连续 6 次 `status=1`
- Wi-Fi 侧与 `udp_only` 相同：
  - 持续 `Sent direct probe to 10.202.168.216 via ipv4`
  - 未出现 `Offer sent` / `Pair ack` / `UDP TX [18]`

判断：

- `ble_udp` 当前同时受 BLE session 未建立 与 Wi-Fi 客户端互访受限 两个阻塞影响，无法完成“BLE 建会话 + UDP 切主”。

### 4.3.1 `ble_udp` 验收通过（2026-03-10 14:35-14:37）

执行命令：

```bash
TRANSPORT_MODE=ble_udp ./scripts/run_dji_rc_pro_gateway.sh
adb -s 4QQZLC700522QY shell am start -n com.example.dji_rc_pro/.MainActivity \
  --es transport_mode ble_udp \
  --es connection_mode auto_pair \
  --es pair_code NCURC2026 \
  --ez forget_pairing true \
  --ez start_primary true
source /opt/ros/humble/setup.bash
source /home/xuan/Tools/Dji_RC_Pro/ros2_ws_dji_rc_pro/install/setup.bash
timeout 4 ros2 topic echo --once /dji_rc_pro_bridge/transport_status
```

新增日志产物：

- `logs/ros2/dji_rc_pro_gateway_2026-03-10_14-35-45.log`
- `logs/adb/transport_ble_udp_2026-03-10_14-37-21.log`

新增代码收口：

- Android 对所有 ROS2 BLE 模式统一在连接后刷新 GATT 缓存，避免 `ble_only` 与 `ble_udp` 之间复用旧特征表
- Android 在 `ble_udp` 下改为 `pair_control` 轮询握手：
  - `write pair_control`
  - `read pair_control`
  - `read network_info`
  - `read status`
- ROS2 gateway 增加 BLE 授权到 UDP 租约的补偿路径，避免 BLE 会话存在但 UDP 侧仍无租约时的误丢包

实际观察：

- Android 侧出现：
  - `ROS2 BLE service discovered: pair=true network=true status=true control=true`
  - `ROS2 BLE using pair_control polling instead of CCCD notifications`
  - `ROS2 BLE pair ack: session=... family=IPV6 address=240c:c901:...`
  - `ROS2 BLE read network_info started=true`
  - `UDP TX [18]: 52 43 ...`
- ROS2 侧出现：
  - `BLE write pair_control bytes=76`
  - `BLE read pair_control bytes=180`
  - `BLE read network_info bytes=174`
  - `BLE read status bytes=118`
  - `CHASSIS_CTRL decoded: ...`
- `transport_status` 结果：
  - `udp_active=1`
  - `ble_active=0`

判断：

- `ble_udp` 已完成 BLE 会话协商并成功切换到 UDP 主通道；BLE 仅承担会话建立与兜底角色。

---

## 5. 本轮代码收口对应证据

### 已验证进入设备的收口点

- `MainViewModel` debug launch override 路径已生效：
  - `udp_only` / `ble_only` / `ble_udp` 启动均进入对应模式分支
- Android BLE 收口补丁已生效：
  - 连接后先刷新 GATT 缓存
  - `ble_only` 正确只发现 `pair_control + control_stream`
  - `ble_udp` 正确发现 `pair_control + network_info + status + control_stream`
- ROS2 BLE gateway 收口补丁已生效：
  - `ble_only` 启动时只注册 2 个特征
  - `ble_udp` 启动时注册完整 4 个特征
- 轮询握手与 UDP 拉起已生效：
  - `ble_udp` 已读回 `pair_control/network_info/status`
  - Android 已持续 `UDP TX [18]`
  - `transport_status` 已出现 `udp_active=1`

### 当前已知限制

1. 当前成功链路以 IPv6 为主  
   证据：`udp_only` 与 `ble_udp` 均通过 `240c:c901:...` 地址完成发现/控制。

2. RC Pro 对 `pair_control` 的 CCCD 回调仍不稳定  
   现状：本轮通过“轮询读回”绕过，不影响最终验收，但后续若要恢复纯 notify 握手仍需单独评估。

---

## 6. 建议的下一步

1. 以当前结果为基线，整理最终设计文档与代码索引，只保留通信主线相关入口。
2. 若后续还要增强 `ble_udp`，优先做两项：
   - 给 UDP 主通道增加更明确的 ROS2 侧收包日志
   - 评估是否还需要保留 CCCD/notify 路径，或彻底固化为轮询握手
3. 若现场网络环境变化，再额外补一组 IPv4-only 复测，确认是否需要对地址族选择策略做收口说明。
