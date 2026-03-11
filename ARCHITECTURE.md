# 原理与代码说明

## 系统原理

这个项目由两部分组成：

1. DJI RC Pro 上的 Android App
2. 机器人侧的 ROS2 bridge / gateway

整体数据流如下：

1. 用户通过实体摇杆、五向键或屏幕虚拟控件输入控制量。
2. Android 侧把输入归一化到统一的 `ControllerState`。
3. 传输服务把 `ControllerState` 编码成 ROS2 底盘控制帧。
4. 控制帧通过 UDP、BLE 或 BLE+UDP 发到主机侧网关。
5. ROS2 网关解析控制帧，发布原始消息和 `cmd_vel`。

## Android 侧模块

### 入口

- `app/src/main/java/com/example/dji_rc_pro/MainActivity.kt`
- `app/src/main/java/com/example/dji_rc_pro/NCUApplication.kt`

### UI

- `ui/MainScreen.kt`
  - 主控制台
  - 抽屉面板
  - 设置、BLE、日志弹窗
- `ui/components/`
  - 频率对话框、虚拟摇杆、状态组件

### 视图模型

- `viewmodel/MainViewModel.kt`

主要职责：

- 聚合 DataStore 配置
- 管理自动发现和配对
- 管理 UDP/BLE 服务启停
- 对 UI 暴露状态流

### 输入层

- `domain/input/ControllerManager.kt`
- `domain/input/StickTransformer.kt`
- `domain/usb/UsbHidProtocolParser.kt`
- `domain/usb/UsbHidManager.kt`

主要职责：

- 统一实体输入和虚拟输入
- 做摇杆死区和量程映射
- 维护按钮位图

### 配置与发现

- `domain/config/ConfigRepository.kt`
- `domain/config/ConnectionMode.kt`
- `domain/config/TransportIsolationMode.kt`
- `domain/config/DebugLaunchOverrides.kt`
- `domain/discovery/DiscoveryProtocol.kt`
- `domain/discovery/WifiDiscoveryManager.kt`
- `domain/discovery/WifiPairingSelector.kt`

主要职责：

- 保存目标地址、端口、短码、已配对主机
- 处理 Wi-Fi 自动发现和主机选择
- 解析/构造发现配对消息

### BLE

- `domain/ble/BleManager.kt`
- `domain/ble/Ros2BleProfile.kt`
- `service/BleService.kt`

主要职责：

- 扫描并连接 `RC26-ROS2*`
- 维护 BLE 会话状态
- 在 UDP 未就绪时发送 BLE 控制帧

### UDP

- `service/UdpService.kt`
- `util/UdpEndpointConfig.kt`

主要职责：

- 校验目标地址和端口
- 建立 UDP socket
- 以设定频率发送控制帧
- 接收来自网关的数据

### 协议与运行期工具

- `protocol/Ros2ChassisControlPacket.kt`
- `protocol/Crc16.kt`
- `manager/FrequencyManager.kt`
- `manager/DataLogManager.kt`
- `manager/ConnectionManager.kt`
- `manager/HeartbeatManager.kt`
- `manager/ReconnectManager.kt`

## ROS2 侧模块

ROS2 代码位于：

- `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/`

关键文件：

- `src/tools/dji_rc_pro_gateway.cpp`
  - UDP 网关
  - BLE 帧入口
  - 解析控制帧并发布 ROS2 话题
- `src/tools/dji_rc_pro_ble_gateway.py`
  - BLE GATT 外设
  - 广播、配对、状态同步
- `src/protocol/protocol_parser.cpp`
- `include/protocol/protocol_parser.hpp`
  - 二进制帧解析器
- `msg/ChassisCtrl.msg`
  - 原始底盘控制消息

## 关键设计点

### 1. Android 和 ROS2 共用同一套二进制控制帧

Android 侧构帧，ROS2 侧按同样的头、长度、CRC 规则解析，避免在链路上做额外 JSON 或字符串封装。

### 2. BLE 负责配对，UDP 负责主控制

`ble_udp` 的设计目标不是双主链路并发，而是：

- BLE 提供发现、短码鉴权、会话同步
- UDP 提供主控制吞吐
- UDP 不可用时再回退到 BLE

### 3. UI 与传输逻辑分离

UI 只改状态；真正的链路启停、校验、配对、服务管理都收敛到 `MainViewModel` 和传输服务中。
