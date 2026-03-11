# 传输说明

本文档说明公开仓库中的三种运行模式：`udp_only`、`ble_only`、`ble_udp`。

## 总览

| 模式 | 发现方式 | 控制主链路 | 典型用途 |
|---|---|---|---|
| `udp_only` | Wi-Fi 自动发现或手动地址 | UDP | 同网段点对点控制 |
| `ble_only` | BLE 广播 + GATT 配对 | BLE `control_stream` | 没有可用 UDP 时的纯 BLE 控制 |
| `ble_udp` | BLE 启动会话并下发网络信息 | UDP，BLE 兜底 | 推荐模式，先配对后切换到 UDP |

## UDP 点对点链路

### 手动模式

`Connection = Manual` 时，App 不做自动发现，直接使用用户填写的：

- `Target Address`
- `Target Port`
- `Local Port`

UDP 启动前会做如下校验：

- 目标地址不能为空
- 目标端口必须在 `1..65535`
- 本地端口必须在 `1..65535`
- IPv4、IPv6 都支持，`[IPv6]` 会先被规范化为裸地址

通过校验后，`UdpService` 会：

1. 按目标地址解析地址族。
2. 用 `0.0.0.0` 或 `::` 绑定本地 `Local Port`。
3. 按当前发送频率发送控制帧到 `Target Address:Target Port`。
4. 同时开启接收循环，把收到的数据交给 `UdpCustomDataManager`。

### 自动配对模式

`Connection = Auto Pair` 且允许 UDP 时，App 会先做 Wi-Fi 发现与短码配对：

1. 向 `ff12::2026:1388` 发送发现探测。
2. 网关返回候选主机信息、控制端口、租约和地址族。
3. 如果只有一个可用主机，自动选中；如果有多个可用主机，由用户点选。
4. 配对成功后，保存主机地址、端口和租约信息。
5. UDP 使用配对得到的端点作为点对点控制目标。

`Bootstrap Address` 只在自动发现失败时作为补充入口，不改变“先发现、后配对”的主流程。

## BLE 链路

BLE 网关设备名以 `RCBridge-ROS2` 开头，App 会优先扫描并连接这类设备。

GATT 服务定义如下：

| 名称 | UUID | 作用 |
|---|---|---|
| service | `8f231100-6e52-4f7c-9b16-1b20c1a50001` | ROS2 BLE 主服务 |
| `pair_control` | `8f231100-6e52-4f7c-9b16-1b20c1a50002` | BLE 配对请求/应答 |
| `network_info` | `8f231100-6e52-4f7c-9b16-1b20c1a50003` | 地址、端口、会话信息 |
| `control_stream` | `8f231100-6e52-4f7c-9b16-1b20c1a50004` | BLE 控制帧写入 |
| `status` | `8f231100-6e52-4f7c-9b16-1b20c1a50005` | UDP/BLE/MCU 状态同步 |

`ble_only` 模式下：

- 设置界面固定为 `Auto Pair`
- 不显示手动 UDP 目标配置
- 控制帧直接走 BLE `control_stream`

## `ble_udp` 切换逻辑

这是推荐模式，链路行为分为两段：

1. BLE 负责发现网关、短码鉴权、建立会话、同步网络地址。
2. 一旦 UDP 可用，UDP 成为主链路，BLE 只保留状态同步和兜底能力。

ROS2 网关端的处理规则是：

- 如果最近 `1500 ms` 内已经收到 UDP 控制帧，则忽略 BLE 控制帧。
- 如果 UDP 静默且 BLE 仍然就绪，则重新接受 BLE 控制帧。
- 如果设备先通过 BLE 授权，随后同一客户端地址开始发 UDP，网关会把该客户端提升为 UDP 租约持有者。

Android 侧的规则是：

- BLE 已连接但会话未就绪时，不发送 BLE 控制帧。
- 会话已配对且 `udpReady=false` 时，BLE 可以继续发控制帧。
- `udpReady=true` 后，BLE 控制发送会被阻断，UDP 维持主链路。

## 默认端口

| 名称 | 默认值 | 用途 |
|---|---|---|
| 发现端口 | `1388` | Wi-Fi 自动发现 |
| 控制端口 | `1387` | UDP 主控制口 |
| App 本地端口 | `1346` | RC Pro UDP bind 端口 |

## 推荐使用方式

- 同网段稳定网络下，优先用 `ble_udp`。
- 只有局域网、无需 BLE 时，用 `udp_only`。
- 无法建立 UDP 或需要最小链路时，用 `ble_only`。
