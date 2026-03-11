# 通信协议说明

本文档只描述当前公开仓库里实际用到的协议。

## 1. Wi-Fi 发现与配对协议

### 基本参数

- 协议版本：`RC26_DISCOVERY/2`
- 组播地址：`ff12::2026`
- 发现端口：`1388`
- 默认控制端口：`1387`
- 默认短码：`NCURC2026`

消息格式是 UTF-8 文本，按 `key=value` 逐行编码，末尾空行结束。

### 交互流程

1. `probe`
2. `offer`
3. `pair_request`
4. `pair_ack` 或 `pair_busy`
5. 可选 `unpair`

### 主要字段

`probe` 由 App 发出，包含：

- `proto`
- `type=probe`
- `client_id`
- `client_name`
- `client_nonce`
- `support_ipv4`
- `support_ipv6`
- `proof`

`offer` 由网关返回，包含：

- `host_id`
- `host_name`
- `client_id`
- `client_nonce`
- `host_nonce`
- `control_port`
- `discovery_port`
- `lease_ms`
- `ipv4`
- `ipv6`
- `ready`
- `busy`
- `proof`

`pair_ack` 会确认：

- `session_id`
- `lease_ms`
- `control_port`
- `selected_family`
- `address`

### 鉴权方式

发现与配对消息使用 `HmacSHA256(pair_code, signature)` 生成 `proof`。

短码不直接明文参与会话判断，而是参与每一步签名计算。只要短码不一致，`offer`、`pair_ack`、`pair_busy` 都无法通过校验。

## 2. BLE 紧凑配对协议

BLE 侧为了减少负载，使用了一套更短的键名。

### 版本与通道

- 紧凑协议版本：`1`
- 配对通道：`pair_control`
- 网络信息通道：`network_info`
- 状态通道：`status`

### 类型码

| 类型 | 含义 |
|---|---|
| `p` | pair probe |
| `o` | offer |
| `r` | pair request |
| `a` | pair ack |
| `b` | busy / reject |
| `n` | network info |
| `s` | status |

### 常见字段

| 键 | 含义 |
|---|---|
| `v` | 协议版本 |
| `t` | 类型 |
| `c` | client id |
| `n` | client nonce |
| `h` | host id |
| `o` | host nonce |
| `p` | control port |
| `l` | lease ms |
| `f` | 选中的地址族 |
| `a` | 选中的地址 |
| `m` | proof |
| `s` | session id |
| `u` | udp ready |
| `b` | ble ready / busy，取决于上下文 |
| `k` | mcu ready |
| `d` | paired |

BLE 紧凑协议同样使用短码参与 proof 计算，只是签名串和字段名更短。

## 3. 控制帧格式

Android 侧当前真正发送的是 `Ros2ChassisControlPacket`，不是旧版 9 字节 `ControlPacket`。

### 帧布局

| 字节偏移 | 长度 | 含义 | 固定值/说明 |
|---|---:|---|---|
| 0 | 1 | Header 1 | `0x52` |
| 1 | 1 | Header 2 | `0x43` |
| 2 | 1 | Heart | 递增计数 |
| 3 | 1 | MID | `0x01` |
| 4 | 1 | PL | `0x0A` |
| 5 | 1 | PID | `0x01` |
| 6 | 1 | LEN | `0x08` |
| 7 | 2 | `v_mps_milli` | int16 little-endian |
| 9 | 2 | `d_rad_milli` | int16 little-endian |
| 11 | 2 | `w_radps_milli` | int16 little-endian |
| 13 | 2 | `heading_angle_milli` | int16 little-endian |
| 15 | 2 | CRC16 | 多项式 `0x1021`，初值 `0xFFFF` |
| 17 | 1 | Tail | `0xED` |

总长度为 `18` 字节。

### 语义

控制帧的 8 字节负载依次表示：

- 线速度模长 `v`
- 速度方向角 `d`
- 角速度 `w`
- 朝向角 `heading`

ROS2 网关解析后会：

1. 发布 `dji_rc_pro_bridge/msg/ChassisCtrl`
2. 将 `v`、`d`、`w` 转成 `geometry_msgs/Twist`
3. 发布到 `/dji_rc_pro_bridge/cmd_vel`

### 数值缩放

- 摇杆原始值范围：`0..255`
- 中位：`127`
- 线速度上限：`4.0 m/s`
- 角速度上限：`2*pi rad/s`
- 发送时统一乘 `1000`，再写入 `int16`

其中：

- 左摇杆决定平面线速度向量
- 右摇杆 X 决定角速度
- `heading_angle_milli` 当前固定为 `0`

## 4. 旧版控制包

仓库里仍保留一个旧版 `ControlPacket`：

- Header：`0xAA`
- 4 个摇杆字节
- 2 字节按键掩码
- CRC16
- 总长 9 字节

它不是当前 ROS2 bridge 主链路所使用的控制帧，只作为历史兼容代码保留。
