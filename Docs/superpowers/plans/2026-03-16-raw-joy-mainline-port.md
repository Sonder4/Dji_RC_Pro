# 原始输入主链路迁移 Implementation Plan

> **For agentic workers:** REQUIRED: Use superpowers:subagent-driven-development (if subagents available) or superpowers:executing-plans to implement this plan. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** 将当前主链路切换为原始输入协议，由 ROS2 侧 Joy 映射节点统一生成 `/dji_rc_pro_bridge/cmd_vel`，同时保留现有 BLE/UDP 稳定化能力与旧协议接收兼容期。

**Architecture:** Android 端只改协议载荷，不改现有发送线程与链路管理；ROS2 网关增加原始输入解码与 `/joy` 发布，并通过独立 `joy_to_cmd_vel` 节点生成速度指令；网关继续兼容旧 `PID=0x01` 以降低回退成本。

**Tech Stack:** Kotlin、Android 单元测试、ROS2 Humble、C++、Python launch、CMake、`sensor_msgs/msg/Joy`、`geometry_msgs/msg/Twist`

---

## Chunk 1: Android 原始输入协议

### Task 1: 将 Android 发送协议切换为原始输入帧

**Files:**
- Modify: `app/src/main/java/com/example/dji_rc_pro/protocol/Ros2ChassisControlPacket.kt`
- Test: `app/src/test/java/com/example/dji_rc_pro/UdpProtocolUnitTest.kt`

- [ ] **Step 1: 写失败测试，定义新的原始输入帧格式**

在 `UdpProtocolUnitTest.kt` 中新增或改写测试，覆盖：
- 帧总长度为 `20`
- `pid = 0x09`
- `pl = 0x0C`
- `len = 0x0A`
- 负载字段按 `LX LY RX RY button_mask(LE) LW RW` 排列
- CRC 对 `frame[5..16]` 计算

- [ ] **Step 2: 运行 Android 单测确认红灯**

Run: `bash ./gradlew testDebugUnitTest --tests com.example.dji_rc_pro.UdpProtocolUnitTest`
Expected: FAIL，表现为旧 `PID=0x01`、旧长度或旧负载断言不成立

- [ ] **Step 3: 最小实现原始输入帧构造**

在 `Ros2ChassisControlPacket.kt` 中：
- 删除速度语义 `ChassisControlPayload`
- 改为 `RawInputPayload`
- `createPayload()` 直接从 `ControllerState` 复制原始值
- `buildFrame()` 输出 `PID=0x09`、10 字节载荷、20 字节总帧

- [ ] **Step 4: 重新运行 Android 单测确认绿灯**

Run: `bash ./gradlew testDebugUnitTest --tests com.example.dji_rc_pro.UdpProtocolUnitTest`
Expected: PASS

- [ ] **Step 5: 提交当前任务**

```bash
git add app/src/main/java/com/example/dji_rc_pro/protocol/Ros2ChassisControlPacket.kt app/src/test/java/com/example/dji_rc_pro/UdpProtocolUnitTest.kt
git commit -m "feat: switch android bridge to raw input frames"
```

## Chunk 2: ROS2 原始输入解码与 Joy 发布

### Task 2: 为网关增加原始输入解码与 `/joy` 发布

**Files:**
- Modify: `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/CMakeLists.txt`
- Modify: `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/include/protocol/protocol_parser.hpp`
- Modify: `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/src/tools/dji_rc_pro_gateway.cpp`
- Create: `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/include/dji_rc_pro_bridge/raw_input.hpp`
- Create: `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/src/raw_input.cpp`
- Test: `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/test/test_raw_input_bridge.cpp`

- [ ] **Step 1: 写失败测试，定义原始输入解码与 Joy 语义**

新增 `test_raw_input_bridge.cpp`，覆盖：
- 10 字节原始输入载荷可解码
- 轴归一化结果正确
- 按键位图可展开到 32 个按钮
- 通过 `ProtocolParser` 的 `RAW_INPUT` 帧能解析为 `Joy`

- [ ] **Step 2: 运行 ROS2 针对性测试确认红灯**

Run:
`source /opt/ros/humble/setup.bash && cd ros2_ws_dji_rc_pro && colcon test --packages-select dji_rc_pro_bridge --ctest-args -R test_raw_input_bridge`
Expected: FAIL，表现为缺少 `RAW_INPUT` 定义、缺少解码函数或测试目标

- [ ] **Step 3: 最小实现原始输入解码模块**

实现：
- `PacketID::RAW_INPUT = 0x09`
- `RawInputState`
- `DecodeRawInputPayload`
- `NormalizeRawAxis`
- `ExpandButtonMask`
- `RawInputToJoyMessage`

- [ ] **Step 4: 在网关中接入原始输入**

在 `dji_rc_pro_gateway.cpp` 中：
- 声明 `joy_topic`
- 创建 `sensor_msgs::msg::Joy` 发布器
- `HandleControlPayload()` 中兼容 `PID=0x09` 和 `PID=0x01`
- `PID=0x09` 发布 `/joy`
- `PID=0x01` 保留旧 `PublishChassisControl()`

- [ ] **Step 5: 更新构建系统**

在 `CMakeLists.txt` 中：
- 引入 `sensor_msgs`
- 编译 `src/raw_input.cpp`
- 注册 `test_raw_input_bridge`

- [ ] **Step 6: 重新运行 ROS2 针对性测试确认绿灯**

Run:
`source /opt/ros/humble/setup.bash && cd ros2_ws_dji_rc_pro && colcon test --packages-select dji_rc_pro_bridge --ctest-args -R test_raw_input_bridge`
Expected: PASS

- [ ] **Step 7: 提交当前任务**

```bash
git add ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/CMakeLists.txt ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/include/protocol/protocol_parser.hpp ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/include/dji_rc_pro_bridge/raw_input.hpp ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/src/raw_input.cpp ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/src/tools/dji_rc_pro_gateway.cpp ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/test/test_raw_input_bridge.cpp
git commit -m "feat: decode raw controller input in ros2 gateway"
```

## Chunk 3: Joy 映射节点与 launch 接入

### Task 3: 增加 Joy 映射节点并接入启动链路

**Files:**
- Modify: `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/CMakeLists.txt`
- Modify: `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/launch/dji_rc_pro_gateway.launch.py`
- Modify: `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/launch/dji_rc_pro_stack.launch.py`
- Create: `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/include/dji_rc_pro_bridge/joy_mapper.hpp`
- Create: `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/src/tools/dji_rc_pro_joy_to_cmd_vel.cpp`
- Create: `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/config/dji_rc_pro_joy.config.yaml`
- Test: `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/test/test_joy_mapper.cpp`

- [ ] **Step 1: 写失败测试，定义 Joy 到速度映射语义**

新增 `test_joy_mapper.cpp`，覆盖：
- 默认轴映射 `x<-axes[1]`
- 默认轴映射 `y<-axes[0]` 且带 `-1.0` 缩放
- 默认轴映射 `yaw<-axes[2]`
- 按下加速键时使用 turbo 缩放
- 轴越界或按钮缺失时输出安全的零速度

- [ ] **Step 2: 运行 ROS2 针对性测试确认红灯**

Run:
`source /opt/ros/humble/setup.bash && cd ros2_ws_dji_rc_pro && colcon test --packages-select dji_rc_pro_bridge --ctest-args -R test_joy_mapper`
Expected: FAIL，表现为缺少 `joy_mapper` 实现、缺少测试目标或断言不成立

- [ ] **Step 3: 最小实现 Joy 映射逻辑**

实现：
- `JoyToCmdVelConfig`
- `ComputeCmdVelFromJoy`
- `dji_rc_pro_joy_to_cmd_vel` 节点
- 默认映射 `x<-1 y<-0 yaw<-2`

- [ ] **Step 4: 接入 launch**

在两个 launch 中：
- 新增 `joy_topic`
- 网关参数加入 `joy_topic`
- 启动 `dji_rc_pro_joy_to_cmd_vel`
- 加载 `config/dji_rc_pro_joy.config.yaml`

- [ ] **Step 5: 重新运行 ROS2 构建确认绿灯**

先运行：
`source /opt/ros/humble/setup.bash && cd ros2_ws_dji_rc_pro && colcon test --packages-select dji_rc_pro_bridge --ctest-args -R test_joy_mapper`
Expected: PASS

再运行：
`bash ./scripts/build_ros2_dji_rc_pro_ws.sh`
Expected: PASS，且 `dji_rc_pro_joy_to_cmd_vel` 成功安装

- [ ] **Step 6: 提交当前任务**

```bash
git add ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/CMakeLists.txt ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/launch/dji_rc_pro_gateway.launch.py ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/launch/dji_rc_pro_stack.launch.py ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/include/dji_rc_pro_bridge/joy_mapper.hpp ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/src/tools/dji_rc_pro_joy_to_cmd_vel.cpp ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/config/dji_rc_pro_joy.config.yaml ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/test/test_joy_mapper.cpp
git commit -m "feat: add joy to cmd vel mapper node"
```

## Chunk 4: 端到端验证与文档

### Task 4: 运行回归验证并同步文档

**Files:**
- Modify: `Docs/Troubleshooting_Log.md`
- Modify: `Docs/README.md`
- Optional: `Docs/03_Implementation/` 下补充迁移说明

- [ ] **Step 1: 运行 Android 全量单测**

Run: `bash ./gradlew testDebugUnitTest`
Expected: PASS

- [ ] **Step 2: 运行 ROS2 全量构建**

Run: `bash ./scripts/build_ros2_dji_rc_pro_ws.sh`
Expected: PASS

- [ ] **Step 3: 运行 Python 测试，确认既有蓝牙网关逻辑未回归**

Run:
`pytest -q ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/test/test_ble_gateway_ip_fields.py ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/test/test_ble_gateway_executable.py`
Expected: PASS

- [ ] **Step 4: 更新文档**

记录：
- 主链路已切换为原始输入协议
- `/dji_rc_pro_bridge/joy` 新增
- `/dji_rc_pro_bridge/cmd_vel` 改为由 Joy 映射节点生成
- `PID=0x01` 仍作为兼容接收保留

- [ ] **Step 5: 提交当前任务**

```bash
git add Docs/Troubleshooting_Log.md Docs/README.md
git commit -m "docs: record raw joy mainline migration"
```
