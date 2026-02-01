## 目标
增加一个界面用于显示UDP接收和发送的原始数据，采用HEX格式显示，带时间戳。

## 实现方案

### 1. 数据模型层
**新增文件：`domain/model/UdpDataLog.kt`**
- 定义数据类 `UdpDataLog` 存储单条日志记录
- 包含字段：timestamp（时间戳）、data（字节数组）、isSent（发送/接收标志）
- 提供HEX格式化方法

### 2. 数据存储层
**修改：`manager/UdpDataLogManager.kt`（新增）**
- 使用 `ArrayDeque` 存储最近的日志（限制1000条避免内存溢出）
- 提供 `StateFlow<List<UdpDataLog>>` 供UI观察
- 提供 `addSentLog()` 和 `addReceivedLog()` 方法
- 提供 `clearLogs()` 方法

### 3. 服务层修改
**修改：`service/UdpService.kt`**
- 在 `transmitPacket()` 中发送数据后记录发送日志
- 启动接收协程监听UDP数据包并记录接收日志
- 注入 `UdpDataLogManager` 依赖

### 4. ViewModel层修改
**修改：`viewmodel/MainViewModel.kt`**
- 添加 `UdpDataLogManager` 实例
- 暴露 `udpDataLogs: StateFlow<List<UdpDataLog>>`
- 添加 `clearUdpDataLogs()` 方法
- 添加对话框状态管理 `showUdpDataDialog`

### 5. UI层
**修改：`ui/MainScreen.kt`**
- 新增按钮 "DATA" 打开数据监控对话框
- 新增 `UdpDataDialog` 组件：
  - 使用 `LazyColumn` 显示日志列表
  - 每行显示格式：`[HH:mm:ss.SSS] HH HH HH...`
  - 发送数据用绿色，接收数据用黄色
  - 提供 "Clear" 按钮清空日志
  - 提供 "Close" 按钮关闭对话框

### 6. 界面布局调整
- 将六个按钮（SETTINGS、BLE、FREQ、VIDEO、START/STOP UDP、DATA）排成一行
- 使用 `horizontalArrangement = Arrangement.spacedBy(4.dp)` 保持间距

## 显示格式示例
```
[18:11:09.702] TX: 52 43 A2 02 1C 03 0C 00 00 03 F0 00 00 00 07 9E 9F 64 2C 05 0C 00 00 03 F0 00 00 00 07 9E 9F 64 2C 01 09 ED
[18:11:09.707] RX: 52 43 A2 02 1C 03 0C 00 00 03 F0 00 00 00 07 9E 9F 64 2C 05 0C 00 00 03 F0 00 00 00 07 9E 9F 64 2C 01 09 ED
```

## 文件变更清单
1. 新增：`domain/model/UdpDataLog.kt`
2. 新增：`manager/UdpDataLogManager.kt`
3. 修改：`service/UdpService.kt` - 添加数据记录和接收功能
4. 修改：`viewmodel/MainViewModel.kt` - 添加日志管理
5. 修改：`ui/MainScreen.kt` - 添加按钮和对话框

## 技术要点
- 使用协程处理UDP接收
- 使用StateFlow实现响应式UI更新
- 限制日志数量防止内存泄漏
- 使用不同颜色区分发送和接收数据