# 按键与输入映射

## 1. 控制状态模型

App 维护的控制状态包含：

- `leftStickX`
- `leftStickY`
- `rightStickX`
- `rightStickY`
- `buttonMask`
- `leftWheel`
- `rightWheel`

摇杆和滚轮统一映射到 `0..255`：

- 中位值：`127`
- 输入死区：`0.05`
- 归一化范围：`-1.0 .. 1.0`

## 2. 屏幕虚拟按钮映射

界面上的虚拟按钮与 `buttonMask` 位号对应关系如下：

| UI 标签 | bit |
|---|---:|
| `A` | 0 |
| `B` | 1 |
| `X` | 2 |
| `Y` | 3 |
| `L1` | 4 |
| `R1` | 5 |
| `C1` | 6 |
| `C2` | 7 |
| `UP` | 10 |
| `DN` | 11 |
| `LT` | 12 |
| `RT` | 13 |

说明：

- `LT` / `RT` 在当前 UI 里是方向簇里的左右键标签，不是模拟扳机轴。
- 位 `8`、`9`、`14`、`15` 在公开代码里没有分配到 UI。

## 3. USB HID 实体输入映射

当前 USB HID 解析器识别的数据头是：

- `0x02 0x0E`

随后读取：

- 左右摇杆
- 左右滚轮
- 功能键字节
- 五向键字节

### 功能键字节

| 实体键 | HID 位值 | 映射到 `buttonMask` |
|---|---:|---:|
| Back | `0x02` | bit `0` (`A`) |
| Record | `0x04` | bit `4` (`L1`) |
| Photo | `0x08` | bit `5` (`R1`) |

### 五向键字节

| 实体键 | HID 位值 | 映射到 `buttonMask` |
|---|---:|---:|
| Up | `0x01` | bit `10` |
| Down | `0x02` | bit `11` |
| Left | `0x04` | bit `12` |
| Right | `0x08` | bit `13` |
| Center | `0x10` | bit `3` (`Y`) |

## 4. 摇杆到运动控制的映射

当前 ROS2 主控制帧只使用摇杆数据，不直接携带 `buttonMask`、`leftWheel`、`rightWheel`。

映射方式如下：

- 左摇杆 X/Y 先转成平面速度向量
- 线速度模长 `v = sqrt(x^2 + y^2)`
- 方向角 `d = atan2(y, x)`
- 右摇杆 X 生成角速度 `w`
- `heading` 当前固定为 `0`

因此：

- 按键和滚轮目前主要保留在本地控制状态、UI 和日志里
- ROS2 `cmd_vel` 的主链路只由摇杆控制

## 5. 兼容性说明

公开仓库里保留了旧版 `ControlPacket`，其中包含按键掩码字段；但 Android 传输服务当前实际发送的是 `Ros2ChassisControlPacket`。如果你计划把按键事件也同步到 ROS2，需要在 Android 发包和 ROS2 解析两端同时扩展。
