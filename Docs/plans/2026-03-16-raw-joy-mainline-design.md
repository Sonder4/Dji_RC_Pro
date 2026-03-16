# 原始输入主链路设计

## 背景

当前 `main` 分支已经稳定完成蓝牙与 UDP 配对、地址同步和链路状态显示，但主控制链路仍然是：

`遥控器状态 -> Android 端速度语义帧 PID=0x01 -> ROS2 网关 -> /cmd_vel`

这条链路的问题是：

1. Android 端提前把摇杆映射成速度，ROS2 侧无法统一复用原始输入。
2. 后续要做不同机器人、不同映射策略时，Android 端协议扩展成本高。
3. 用户已经明确要求当前主链路切换为“原始输入协议”，并由 ROS2 侧 `Joy` 映射节点统一生成 `cmd_vel`。

## 目标

1. Android 端主发送协议切换为原始输入帧。
2. ROS2 网关解码原始输入并发布 `sensor_msgs/msg/Joy`。
3. 新增 Joy 映射节点，由 ROS2 侧统一发布 `/dji_rc_pro_bridge/cmd_vel`。
4. 保持现有三种传输模式 `udp_only`、`ble_only`、`ble_udp` 全覆盖可用。
5. 尽量减少对当前蓝牙/UDP 配对稳定化代码的改动。

## 非目标

1. 不迁移 `feature/raw-joy-protocol` 分支上的其它 UI、链路状态或架构重构。
2. 不改动现有蓝牙有限状态机与地址同步策略的对外行为。
3. 不删除现有 `PID=0x01` 接收能力，保留兼容期。

## 方案比较

### 方案 A：整体合并 `feature/raw-joy-protocol`

优点：
- 原始输入和 Joy 映射能力一次到位。

缺点：
- 会把该分支上其它未验证改动一起带入。
- 容易覆盖 `main` 已验证稳定的蓝牙/UDP 修复。

结论：
- 不采用。

### 方案 B：选择性迁移原始输入协议与 Joy 映射节点

优点：
- 只迁移用户要求的两块功能。
- 最大限度保留 `main` 现有稳定链路与文档。
- 风险和回归面最小。

缺点：
- 需要手工对齐 `main` 当前代码结构。

结论：
- 采用。

### 方案 C：继续保留 Android 速度语义主链路，仅在 ROS2 侧补一个 Joy

优点：
- 改动最小。

缺点：
- 不满足“主链路改成原始输入协议”的明确要求。
- ROS2 侧会拿到伪造的原始输入，语义不真实。

结论：
- 不采用。

## 已批准架构

### Android 发送端

保留现有 `RC` 外层帧封装，业务 `PID` 切换为原始输入：

- 帧头 `0x52 0x43`
- 心跳 `heart_counter`
- 设备 `mid = 0x01`
- 负载段长度 `pl = 0x0C`
- 业务 `pid = 0x09`
- 业务长度 `len = 0x0A`
- 负载 `payload = 10 bytes`
- CRC16：继续沿用现有规则，对 `[5..16]` 求值
- 尾字节 `0xED`
- 总长度 `20 bytes`

### 原始输入负载定义

| 字节 | 字段 | 类型 | 说明 |
|---|---|---|---|
| 0 | `left_stick_x` | `uint8` | 左摇杆 X，中心值 127 |
| 1 | `left_stick_y` | `uint8` | 左摇杆 Y |
| 2 | `right_stick_x` | `uint8` | 右摇杆 X |
| 3 | `right_stick_y` | `uint8` | 右摇杆 Y |
| 4..7 | `button_mask` | `uint32` 小端 | 按键位图 |
| 8 | `left_wheel` | `uint8` | 左拨轮 |
| 9 | `right_wheel` | `uint8` | 右拨轮 |

### 按键位定义

沿用当前工程现有位定义，避免 Android UI、控制器管理和 ROS2 解析三处出现偏差：

- bit 0: `A`
- bit 1: `B`
- bit 2: `X`
- bit 3: `Y`
- bit 4: `L1`
- bit 5: `R1`
- bit 6: `C1`
- bit 7: `C2`
- bit 10: `上`
- bit 11: `下`
- bit 12: `左`
- bit 13: `右`

### ROS2 网关职责

网关改为同时兼容两种输入：

1. `PID=0x09`
   - 解码原始输入
   - 发布到参数 `joy_topic`
   - `joy_topic` 默认值为 `/dji_rc_pro_bridge/joy`
2. `PID=0x01`
   - 继续按当前逻辑解码
   - 发布 `/dji_rc_pro_bridge/chassis_ctrl_raw`
   - 发布 `/dji_rc_pro_bridge/cmd_vel`

兼容期内 Android 主发送端只发送 `PID=0x09`，ROS2 网关保留 `PID=0x01` 接收能力，便于回退和测试。
兼容期的前提是“同一时刻只存在一种发送协议”。也就是说，现场正常运行只会收到 `PID=0x09`，`PID=0x01` 仅用于人工回退验证，不作为与新协议并行混发的正式模式。

### Joy 消息语义

- `axes[0] = left_stick_x`
- `axes[1] = left_stick_y`
- `axes[2] = right_stick_x`
- `axes[3] = right_stick_y`
- `axes[4] = left_wheel`
- `axes[5] = right_wheel`
- 归一化公式：`(raw - 127) / 127.0`
- 归一化结果强制钳位到 `[-1.0, 1.0]`
- `buttons[0..31]` 由 `button_mask` 展开

### Joy 到速度映射

新增独立 ROS2 节点 `dji_rc_pro_joy_to_cmd_vel`，默认映射：

- `linear.x <- axes[1]`
- `linear.y <- axes[0]`
- `angular.z <- axes[2]`

默认缩放保持与参考分支一致：

- `x = 1.0`
- `y = -1.0`
- `yaw = 1.0`

默认按钮与 turbo 规则如下：

- `require_enable_button = false`
- `enable_button = 4`，对应 `L1`
- `enable_turbo_button = 5`，对应 `R1`
- 默认 `scale_chassis_turbo` 与 `scale_chassis` 相同，现场可通过 YAML 独立配置
- 当 `R1` 按下时优先采用 `scale_chassis_turbo`
- 当索引越界、按钮不存在或输入无效时，节点输出安全零速度或使用默认零值

## 三种传输模式下的数据流

### `udp_only`

`ControllerState -> PID=0x09 -> UDP -> gateway -> joy_topic(默认 /dji_rc_pro_bridge/joy) -> joy_to_cmd_vel -> /cmd_vel`

### `ble_only`

`ControllerState -> PID=0x09 -> BLE -> gateway -> joy_topic(默认 /dji_rc_pro_bridge/joy) -> joy_to_cmd_vel -> /cmd_vel`

### `ble_udp`

`ControllerState -> PID=0x09 -> UDP 优先，BLE 回退 -> gateway -> joy_topic(默认 /dji_rc_pro_bridge/joy) -> joy_to_cmd_vel -> /cmd_vel`

蓝牙有限状态机、地址同步和 UDP 回退逻辑继续沿用 `main` 当前稳定实现，不在本次设计中重写。

## 影响文件

### Android

- `app/src/main/java/com/example/dji_rc_pro/protocol/Ros2ChassisControlPacket.kt`
- `app/src/test/java/com/example/dji_rc_pro/UdpProtocolUnitTest.kt`

### ROS2

- `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/include/protocol/protocol_parser.hpp`
- `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/CMakeLists.txt`
- `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/launch/dji_rc_pro_gateway.launch.py`
- `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/launch/dji_rc_pro_stack.launch.py`
- `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/src/tools/dji_rc_pro_gateway.cpp`
- 新增 `raw_input` 解码与 `joy_mapper` 相关文件
- 新增对应测试与配置文件

## 测试策略

1. Android 单元测试先验证新帧长度、字段顺序、CRC 和原始输入载荷。
2. ROS2 单元测试验证原始输入解码、`Joy` 归一化钳位和按键展开。
3. ROS2 单元测试验证默认轴映射、使能/加速按钮和安全零输出。
4. ROS2 构建后验证网关与 Joy 映射节点均可编译安装。
5. 启动 `dji_rc_pro_gateway.launch.py` 时，确认 `gateway + joy_to_cmd_vel` 同时被拉起。

## 回退策略

如果原始输入主链路在现场验证中出现问题，可临时切回旧 Android 发送逻辑，ROS2 网关仍能接收 `PID=0x01`。回退验证时应保证现场只启用旧协议发送端，不与 `PID=0x09` 混发。这保证本次迁移是“可前进、可回退”的。
