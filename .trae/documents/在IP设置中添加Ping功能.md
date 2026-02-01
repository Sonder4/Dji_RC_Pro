## 实现计划：在IP设置界面添加Ping功能

### 1. 创建Ping工具类
**文件**: `app/src/main/java/com/example/dji_rc_pro/util/PingUtil.kt`
- 使用ProcessBuilder执行ping命令
- 解析ping结果（发送包数、接收包数、丢包率、延迟统计）
- 支持超时设置

### 2. 添加Ping状态到MainViewModel
**文件**: `app/src/main/java/com/example/dji_rc_pro/viewmodel/MainViewModel.kt`
- 添加ping结果状态（PingResult data class）
- 添加isPinging状态（是否正在ping）
- 添加performPing(ip: String)方法

### 3. 修改SettingsDialog界面
**文件**: `app/src/main/java/com/example/dji_rc_pro/ui/MainScreen.kt`
- 在Target IP输入框旁边添加Ping按钮
- 添加ping结果显示区域：
  - 状态指示（绿色=成功，红色=失败）
  - 统计信息（发送/接收/丢失）
  - 延迟信息（最短/最长/平均）
- 添加圆形进度指示器（ping进行中）

### 4. 添加权限（如需要）
**文件**: `app/src/main/AndroidManifest.xml`
- 检查是否需要INTERNET权限

### Ping结果显示格式示例：
```
Ping 192.168.1.86:
✓ 成功 (4/4 包, 0% 丢失)
延迟: 最短=41ms, 最长=115ms, 平均=89ms
```

### 界面布局：
```
[Target IP: 192.168.1.86] [Ping按钮]
[Ping结果显示区域]
[Target Port: 1387]
[Local Port: 1346]
```

请确认这个计划后，我将开始实施具体的代码修改。