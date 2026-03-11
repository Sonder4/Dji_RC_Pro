# DJI RC Pro 综合测试记录（2026-03-11）

**适用目录**: `/home/xuan/Tools/Dji_RC_Pro`  
**测试日期**: 2026-03-11  
**测试目标**: 在真机上重新完成 `ble_only`、`udp_only`、`ble_udp` 三种通信模式复验，并把测试步骤固化为可由人工重复执行的操作手册。

---

## 1. 测试环境

- 仓库目录：`/home/xuan/Tools/Dji_RC_Pro`
- RC Pro `adb` serial：`4QQZLC700522QY`
- 主机名：`xuan-QNLYS`
- 主机 Wi-Fi IPv4：`10.202.168.216`
- 主机 Wi-Fi IPv6：
  - `240c:c901:2:3806:b:7ff1:73a8:82dd`
  - `240c:c901:2:3806:62d5:2587:86ad:d71b`
  - `240c:c901:2:3806:d7c4:e9d4:2817:e35a`
- RC Pro `wlan0` IPv4：`10.202.200.141/16`
- RC Pro `wlan0` IPv6：
  - `240c:c901:2:2901:557a:1be1:a9a4:32cb`
  - `240c:c901:2:2901:fca:7a2b:d87c:ba87`
- 蓝牙控制器：`hci0`
- 蓝牙地址：`10:5F:AD:63:70:17`
- 当日验证 APK：`app/build/outputs/apk/debug/app-debug.apk`

---

## 2. 当日构建与安装

### 2.1 Android

执行命令：

```bash
GRADLE_USER_HOME=/tmp/gradle ./gradlew testDebugUnitTest assembleDebug
adb -s 4QQZLC700522QY install -r app/build/outputs/apk/debug/app-debug.apk
```

结果：

- `testDebugUnitTest` 通过
- `assembleDebug` 通过
- `adb install -r` 返回 `Success`

说明：

- 本轮真机测试使用当前工作区源码重新构建后的 debug APK
- `app-debug.apk` 文件时间为 `2026-03-11 10:45`

### 2.2 ROS2

执行命令：

```bash
./scripts/build_ros2_dji_rc_pro_ws.sh
```

结果：通过

构建日志：

- `logs/ros2/colcon_build_2026-03-11_11-02-50.log`

---

## 3. 人工复现前置步骤

以下步骤适用于三种模式，模式值只在启动 gateway 和启动 App 时替换。

1. 确认 RC Pro 已连上 ADB。

```bash
adb devices -l
```

预期：出现 `4QQZLC700522QY device`

2. 打开 3 个终端。

- 终端 A：运行 ROS2 gateway
- 终端 B：运行 ADB 启动和日志抓取
- 终端 C：执行 ROS2 topic 查询

3. 在每种模式开始前清理状态。

```bash
adb -s 4QQZLC700522QY shell am force-stop com.example.dji_rc_pro
adb -s 4QQZLC700522QY logcat -c
```

4. 启动对应模式的 gateway。

```bash
TRANSPORT_MODE=<mode> ./scripts/run_dji_rc_pro_gateway.sh
```

等待条件：

- `udp_only`：等待看到 `DJI RC Pro gateway ready`
- `ble_only` / `ble_udp`：等待看到 `BLE gateway ready` 或 `BLE service composition transport_mode=...`
- 建议等待 2 到 3 秒后再启动 App

5. 用 debug intent 启动 RC Pro App。

```bash
adb -s 4QQZLC700522QY shell am start -W -S -n com.example.dji_rc_pro/.MainActivity \
  --es transport_mode <mode> \
  --es connection_mode auto_pair \
  --es pair_code NCURC2026 \
  --ez forget_pairing true \
  --ez start_primary true
```

6. 保持 App 停留在主控制界面，不进入设置抽屉，等待自动配对完成。

- `udp_only`：等待 4 到 6 秒
- `ble_only`：等待 5 到 8 秒
- `ble_udp`：等待 6 到 10 秒

7. 做人工控制动作。

- 右侧摇杆左右各拖动 1 到 2 秒
- 左侧摇杆向前拖动 1 到 2 秒
- 至少重复 2 轮

说明：

- 本轮最终文档面向人工复现，推荐直接手指拖动可见摇杆
- 我在 `ble_only` / `ble_udp` 中额外尝试过 ADB 合成滑动，事件未稳定产生非零量，所以不把 ADB 合成触摸作为主验收方式

8. 抓取 Android 侧日志，并在终端 C 查询 ROS2 话题。

```bash
source /opt/ros/humble/setup.bash
source /home/xuan/Tools/Dji_RC_Pro/ros2_ws_dji_rc_pro/install/setup.bash
```

9. 每轮结束后停止 App 和 gateway，再开始下一模式。

- App：`adb -s 4QQZLC700522QY shell am force-stop com.example.dji_rc_pro`
- gateway：在终端 A 里 `Ctrl+C`

---

## 4. 当日测试矩阵

| 模式 | 关键日志判据 | 实际结果 | 结论 |
|---|---|---|---|
| `ble_only` | `BLE-only auth accepted`、`BLE control frame received`、`/dji_rc_pro_bridge/ble/control_frame` 有数据 | BLE 认证通过，BLE 控制帧进入 ROS2，`transport_status` 为 `udp_active=0 ble_active=1` | 通过 |
| `udp_only` | `Offer sent`、`Paired client`、`UDP TX [18]`、`CHASSIS_CTRL decoded` | Wi-Fi IPv6 自动配对成功，Android 持续发 UDP 帧，ROS2 侧出现多条非零 `w` 解码值 | 通过 |
| `ble_udp` | `BLE service composition transport_mode=ble_udp`、`BLE read network_info`、`UDP TX [18]`、`transport_status udp_active=1` | BLE 完成会话与状态同步，UDP 拉起并成为主通道，Android 明确打印 `Skipping BLE control frame - UDP remains primary` | 通过 |

---

## 5. 分模式记录

### 5.1 `ble_only`（11:03 - 11:07）

执行命令：

```bash
adb -s 4QQZLC700522QY shell am force-stop com.example.dji_rc_pro
adb -s 4QQZLC700522QY logcat -c
TRANSPORT_MODE=ble_only ./scripts/run_dji_rc_pro_gateway.sh
adb -s 4QQZLC700522QY shell am start -W -S -n com.example.dji_rc_pro/.MainActivity \
  --es transport_mode ble_only \
  --es connection_mode auto_pair \
  --es pair_code NCURC2026 \
  --ez forget_pairing true \
  --ez start_primary true
./scripts/adb_capture_transport_logs.sh -s 4QQZLC700522QY -m ble --no-clear --dump-once
source /opt/ros/humble/setup.bash
source /home/xuan/Tools/Dji_RC_Pro/ros2_ws_dji_rc_pro/install/setup.bash
timeout 8 ros2 topic echo --once /dji_rc_pro_bridge/ble/control_frame
timeout 8 ros2 topic echo --once /dji_rc_pro_bridge/transport_status
```

日志产物：

- `logs/ros2/dji_rc_pro_gateway_2026-03-11_11-03-55.log`
- `logs/adb/transport_ble_2026-03-11_11-07-15.log`

实际观察：

- ROS2 日志出现：
  - `BLE-only auth accepted client=73a521093ed98861 ... endpoint=ipv6:240c:c901:2:3806:b:7ff1:73a8:82dd`
  - `BLE control frame received bytes=18 client=73a521093ed98861`
  - `CHASSIS_CTRL decoded: v=0.000 d=0.000 w=0.000 theta=0.000`
- Android 日志出现：
  - `BleService: [BleService] Transmitted 18 bytes via BLE`
  - `BleManager: ROS2 BLE onCharacteristicWrite uuid=...0004 status=0 controlStream=true notifyReady=false`
- `/dji_rc_pro_bridge/ble/control_frame` 话题返回一帧：

```text
data: [82, 67, 163, 1, 10, 1, 8, 0, 0, 0, 0, 0, 0, 0, 0, 167, 131, 237]
```

- `/dji_rc_pro_bridge/transport_status` 返回：

```text
udp_active=0
ble_active=1
mcu_ready=1
```

判断：

- `ble_only` 已满足“仅使用 BLE 将控制帧送入 ROS2”的验收要求
- 本轮自动触发的帧主要是中心值，是否为非零量取决于现场人工拖动幅度

### 5.2 `udp_only`（11:11 - 11:15）

执行命令：

```bash
adb -s 4QQZLC700522QY shell am force-stop com.example.dji_rc_pro
adb -s 4QQZLC700522QY logcat -c
TRANSPORT_MODE=udp_only ./scripts/run_dji_rc_pro_gateway.sh
adb -s 4QQZLC700522QY shell am start -W -S -n com.example.dji_rc_pro/.MainActivity \
  --es transport_mode udp_only \
  --es connection_mode auto_pair \
  --es pair_code NCURC2026 \
  --ez forget_pairing true \
  --ez start_primary true
./scripts/adb_capture_transport_logs.sh -s 4QQZLC700522QY -m udp --no-clear --dump-once
source /opt/ros/humble/setup.bash
source /home/xuan/Tools/Dji_RC_Pro/ros2_ws_dji_rc_pro/install/setup.bash
timeout 8 ros2 topic echo --once /dji_rc_pro_bridge/transport_status
```

日志产物：

- `logs/ros2/dji_rc_pro_gateway_2026-03-11_11-11-40.log`
- `logs/adb/transport_udp_2026-03-11_11-15-18.log`

实际观察：

- Android 日志出现：
  - `WifiDiscoveryManager: Discovered host xuan-QNLYS (... family=ipv6)`
  - `WifiDiscoveryManager: Pair ack from rc26-e7443b5b67f8fafb@240c:c901:2:3806:b:7ff1:73a8:82dd:1387`
  - `UdpService: UDP TX [18]: 52 43 ... A7 83 ED`
- ROS2 日志出现：
  - `Offer sent to client=... peer=240c:c901:2:2901:557a:1be1:a9a4:32cb family=ipv6`
  - `Paired client=... control=240c:c901:2:3806:b:7ff1:73a8:82dd:1387`
  - `CHASSIS_CTRL decoded: v=0.000 d=0.000 w=3.265 theta=0.000`
  - `CHASSIS_CTRL decoded: v=0.000 d=0.000 w=-4.057 theta=0.000`
  - `CHASSIS_CTRL decoded: v=0.000 d=0.000 w=4.106 theta=0.000`
- `/dji_rc_pro_bridge/transport_status` 返回：

```text
udp_active=1
ble_active=0
mcu_ready=1
```

判断：

- `udp_only` 本轮明确通过 IPv6 自动发现与配对完成主链路
- 非零 `w` 值已经出现，说明至少一部分人工摇杆输入已被 UDP 主通道成功传入 ROS2

### 5.3 `ble_udp`（11:15 - 11:17）

执行命令：

```bash
adb -s 4QQZLC700522QY shell am force-stop com.example.dji_rc_pro
adb -s 4QQZLC700522QY logcat -c
TRANSPORT_MODE=ble_udp ./scripts/run_dji_rc_pro_gateway.sh
adb -s 4QQZLC700522QY shell am start -W -S -n com.example.dji_rc_pro/.MainActivity \
  --es transport_mode ble_udp \
  --es connection_mode auto_pair \
  --es pair_code NCURC2026 \
  --ez forget_pairing true \
  --ez start_primary true
./scripts/adb_capture_transport_logs.sh -s 4QQZLC700522QY -m all --no-clear --dump-once
source /opt/ros/humble/setup.bash
source /home/xuan/Tools/Dji_RC_Pro/ros2_ws_dji_rc_pro/install/setup.bash
timeout 8 ros2 topic echo --once /dji_rc_pro_bridge/transport_status
```

日志产物：

- `logs/ros2/dji_rc_pro_gateway_2026-03-11_11-15-59.log`
- `logs/adb/transport_all_2026-03-11_11-17-17.log`

实际观察：

- Android 日志出现：
  - `WifiDiscoveryManager: Pair ack from rc26-e7443b5b67f8fafb@240c:c901:2:3806:b:7ff1:73a8:82dd:1387`
  - `BleManager: ROS2 BLE service discovered: pair=true network=true status=true control=true`
  - `BleManager: ROS2 BLE using pair_control polling instead of CCCD notifications`
  - `BleManager: ROS2 BLE read network_info started=true`
  - `UdpService: UDP TX [18]: 52 43 ... A7 83 ED`
  - `BleService: [BleService] Skipping BLE control frame - UDP remains primary`
- ROS2 日志出现：
  - `BLE service composition transport_mode=ble_udp characteristics=[...0002,...0004,...0003,...0005]`
  - `Compact BLE offer sent client=73a521093ed98861 ready=True busy=False family=ipv6 address=240c:c901:2:3806:b:7ff1:73a8:82dd:1387`
  - `Compact BLE paired client=73a521093ed98861 family=ipv6 address=240c:c901:2:3806:b:7ff1:73a8:82dd:1387`
  - `BLE read network_info bytes=168`
  - `BLE read status bytes=115`
  - `BLE control frame received bytes=18 client=73a521093ed98861`
  - `Offer sent to client=... peer=240c:c901:2:2901:557a:1be1:a9a4:32cb family=ipv6`
  - `Paired client=... control=240c:c901:2:3806:b:7ff1:73a8:82dd:1387`
- `/dji_rc_pro_bridge/transport_status` 返回：

```text
udp_active=1
ble_active=0
mcu_ready=1
```

判断：

- `ble_udp` 已完成 BLE 会话建立、`network_info/status` 读取和 UDP 主通道接管
- 当前实现下，只要 UDP 已活跃，Android 会打印 `Skipping BLE control frame - UDP remains primary`
- 因此 `ble_udp` 下 `transport_status` 为 `udp_active=1 ble_active=0` 属于预期，不应判为故障

---

## 6. 关键日志与产物清单

### 构建

- `logs/ros2/colcon_build_2026-03-11_11-02-50.log`

### BLE only

- `logs/ros2/dji_rc_pro_gateway_2026-03-11_11-03-55.log`
- `logs/adb/transport_ble_2026-03-11_11-07-15.log`

### UDP only

- `logs/ros2/dji_rc_pro_gateway_2026-03-11_11-11-40.log`
- `logs/adb/transport_udp_2026-03-11_11-15-18.log`

### BLE + UDP

- `logs/ros2/dji_rc_pro_gateway_2026-03-11_11-15-59.log`
- `logs/adb/transport_all_2026-03-11_11-17-17.log`

---

## 7. 已知限制与复现注意事项

- `ble_udp` 模式下，不应把 `ble_active=0` 解释为 BLE 没有工作。
  - 当前设计里 BLE 负责会话、地址同步和必要时兜底
  - 一旦 `udp_active=1`，控制主通道切到 UDP，BLE 控制帧会被主动跳过
- `ble_only` 与 `ble_udp` 中，ADB 合成滑动未稳定产生非零控制量。
  - 日志已经证明 BLE 控制帧进入 ROS2
  - 若要复验非零量，优先使用人工手指拖动摇杆，而不是 `adb shell input swipe`
- `adb_capture_transport_logs.sh` 必须在 App 进程存活时执行，否则无法抓到本轮 transport 日志。
- 若复现时发现上一轮 gateway 未退出，必须先停掉旧进程，否则日志会混线。
- 每轮开始前务必执行 `adb logcat -c`，否则同一份日志文件里会混入前一模式的内容。

---

## 8. 结论

- `ble_only`：通过。当前 App 已可只依赖 BLE 把控制帧送入 ROS2。
- `udp_only`：通过。当前环境下实际走通的是 IPv6 自动发现与主控制通道。
- `ble_udp`：通过。BLE 完成会话与地址同步后，UDP 成为主通道，行为与设计文档一致。

本次 2026-03-11 真机综合测试满足“通信主线闭环 + 可人工复现”的验收要求。
