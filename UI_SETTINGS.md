# UI 与设置说明

## 主界面结构

主界面是横屏控制台，核心区域包括：

- 左虚拟摇杆
- 右虚拟摇杆
- 中央状态桥接面板
- 虚拟按键区
- 抽屉面板：`Connection`、`BLE`、`Data Logs`

## 1. Connection 抽屉

### Connection

- `Auto Pair`
  - 推荐模式
  - 用于 Wi-Fi 自动发现或 BLE 配对
- `Manual`
  - 直接指定 UDP 目标地址
  - 在 `ble_only` 模式下会被禁用

### Transport

- `UDP`
  - BLE 关闭
  - 发现和控制都走 UDP
- `BLE + UDP`
  - BLE 建立会话，UDP 成为主链路
- `BLE`
  - 纯 BLE 模式
  - 手动目标输入被禁用

### Runtime

- `Frequency`
  - 打开发送频率对话框
  - 范围 `10..200 Hz`
  - 默认值 `100 Hz`
  - 预设值：`10`、`50`、`100`、`150`、`200`
- `Video ON/OFF`
  - 控制中间面板的视频预览开关

说明：

- 当前代码中，UDP 发送循环跟随 `FrequencyManager`。
- BLE 发送服务仍按固定 `50 Hz` 调度，因此频率设置主要影响 UDP 主链路。

### Current Status

显示当前链路状态，包括：

- `Link status`
- 当前已配对主机名
- 当前端点地址与控制端口

## 2. Auto Pair 视图

当 `Connection = Auto Pair` 时显示以下配置：

- `Bootstrap Address`
  - 仅在 Wi-Fi 自动发现不可用时提供一个主机入口
  - 支持 IPv4/IPv6
- `Pair Code`
  - BLE 与 Wi-Fi 共同使用的短码
- `Local Port`
  - RC Pro App 本地 UDP 绑定端口
  - `ble_only` 模式下不显示
- `Discovered Hosts`
  - 列出发现到的主机
  - 每个主机显示 `Ready`、`Busy` 或 `Waiting`
  - 点击 `Use` 触发配对
- `Forget Pairing`
  - 清除本地缓存的主机和会话信息

## 3. Manual Target 视图

当 `Connection = Manual` 时显示：

- `Target Address`
- `Ping`
- `Target Port`
- `Local Port`

保存前行为：

- 地址会先规范化
- 端口必须在 `1..65535`
- 校验失败时显示 `Validation`
- `Ping` 结果会显示发包/收包/丢包率与时延统计

## 4. BLE 抽屉

BLE 面板用于直接管理 BLE 网关连接：

- `Start Scan` / `Stop Scan`
- `Disconnect`
- `Close`

列表行为：

- 只要模式允许 BLE，就可以开始扫描
- `RC26-ROS2` 设备会优先排到前面
- 同类设备再按 RSSI 从高到低排序

状态标签：

- `DISABLED`
- `READY`
- `SCANNING`
- `CONNECTED`

## 5. Data Logs 抽屉

数据日志面板提供运行期收发观察：

- 过滤开关：`UDP`、`BLE`、`Debug`
- 日志会自动滚动到最新项
- `Clear` 清空当前缓存

颜色约定：

- UDP 日志使用主色容器背景
- BLE 日志使用次色容器背景
- 发送、接收、调试项会用不同文字颜色区分

## 6. Save / Cancel 逻辑

点击 `Save` 时：

- 如果当前模式允许 UDP，会先保存 `Local Port`
- `Manual` 模式会保存 `Target Port`
- `Auto Pair` 模式会保存 `Pair Code`
- `Auto Pair` 且非 `ble_only` 时，会保存 `Bootstrap Address`
- `Manual` 且允许 UDP 时，会保存 `Target Address`

只有端口校验通过后，对话框才会关闭；否则保留在当前页面并显示错误。
