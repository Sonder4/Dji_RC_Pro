# 🧠 项目头脑风暴与需求梳理 (Project Brainstorming & Requirements)

> 本文档继承自 [DESIGN_Architecture.md](../02_Architecture/DESIGN_Architecture.md) 与 [ALIGNMENT_Init.md](../01_Initialization/ALIGNMENT_Init.md)，旨在明确详细开发要求。

## 1. 继承上下文 (Inherited Context)

基于现有架构与对齐文档，我们已达成以下共识：

*   **核心目标**: 解锁 DJI RC Pro (RM510) 作为通用机器人控制器，控制 Ubuntu 无人车。
*   **技术架构**: Android Native (Kotlin) + MVVM + DJI MSDK V5。
*   **通信链路**:
    *   **主要**: Wi-Fi UDP (Client 模式)。
    *   **协议**: `[Start, LX, LY, RX, RY, Mask, CRC]`。
*   **输入源**: 实体摇杆 + 实体按键 (C1-C3, 5D) + 虚拟屏幕摇杆。

---

## 2. 待确认问题 (Questions for Clarification)

为了进入编码阶段，我们需要明确以下细节。请针对下列问题提供反馈：

### 2.1 通信协议细节 (UDP Protocol)
*   **Q1.1 摇杆数值定义**:
    *   发送的 `Byte` 范围是 `0-255` 吗？
    *   摇杆回中（无操作）时的数值是 `127` 还是 `128`？
    *   方向定义：向上/向右是增加还是减少？
*   **Q1.2 按键掩码 (Button Bitmask)**:
    *   `BTN_MASK` 字节的每一位 (Bit 0 - Bit 7) 分别对应哪个按键？
    *   *建议方案*: `Bit0: C1`, `Bit1: C2`, `Bit2: C3`, `Bit3: 5D_Up`, `Bit4: 5D_Down`, `Bit5: 5D_Left`, `Bit6: 5D_Right`, `Bit7: 5D_Press`。
*   **Q1.3 校验算法 (CRC)**:
    *   具体使用哪种算法？(例如：简单的字节累加和 Checksum, CRC-8/MAXIM, CRC-16/CCITT?)

### 2.2 功能范围 (Feature Scope)
*   **Q2.1 视频图传 (Video Feed)**:
    *   App 是否需要显示来自 Ubuntu 小车的视频画面？
    *   如果需要，使用什么传输协议？(RTSP, UDP Raw H.264, WebRTC?)
    *   *如果不确定，建议第一阶段先暂不实现视频功能。*

### 2.3 交互与配置 (UI & Config)
*   **Q3.1 配置方式**:
    *   目标 IP 和 端口 (`Target IP`, `Port`) 是否需要在 App 界面上提供输入框进行实时修改？
    *   还是仅通过读取 `config.json` 文件来固定配置？
*   **Q3.2 虚拟摇杆逻辑**:
    *   当**实体摇杆**和**屏幕虚拟摇杆**同时被操作时，如何处理？
    *   *方案 A*: 简单的数值叠加（需限制在最大范围内）。
    *   *方案 B*: 触摸优先（触摸时忽略实体）。
    *   *方案 C*: 实体优先。

### 2.5 进阶通信需求 (Advanced Communication)

*   **Q5.1 蓝牙设备选择 (BLE Selection)**:
    *   用户提到 "通过按键进行蓝牙设备的选取"。
    *   **交互逻辑**: 具体是利用哪几个实体按键？(例如：`C1` 扫描，`5D_Left/Right` 切换列表，`5D_Press` 连接？)
    *   **UI 反馈**: 是否需要在屏幕上弹出一个设备列表供查看？
*   **Q5.2 蓝牙协议 (BLE Protocol)**:
    *   BLE 传输的数据包结构是否与 UDP 完全一致？(`0xAA...CRC`)
    *   使用的是 BLE GATT (特征值写入) 还是经典蓝牙 SPP？(Android 10+ 通常推荐 BLE GATT)。
*   **Q5.3 WiFi/BLE 共存策略 (Coexistence)**:
    *   是 **双发** (同时通过 WiFi 和 BLE 发送数据)？
    *   还是 **主备切换** (优先 WiFi，断连切 BLE)？
    *   或者是 **手动切换** (在设置里选通道)？

---

## 3. 决策记录 (Decision Log)

*(此处将根据您的回答实时更新)*

| 类别 | 问题 | 最终决策 (Confirmed Decision) |
| :--- | :--- | :--- |
| **Protocol** | 摇杆数值范围 | **待实测** (TBD). 设计 `StickTransformer` 模块，暂定目标输出 0-255，输入需根据 MSDK 实测值适配。 |
| **Protocol** | 按键位映射 | **确认** (Confirmed). 使用建议方案: `Bit0: C1`, `Bit1: C2`, `Bit2: C3`, `Bit3: 5D_Up`, `Bit4: 5D_Down`, `Bit5: 5D_Left`, `Bit6: 5D_Right`, `Bit7: 5D_Press`。 |
| **Protocol** | CRC 算法 | **CRC16**. (具体多项式待定，代码需模块化)。 |
| **Feature** | 视频图传 | **预留框架** (Framework Only). UI 需预留视频显示区域及开关按钮，但具体流传输逻辑推迟开发。 |
| **UI** | IP/Port 配置 | **App 内修改 + 持久化**. 建议使用 Android Jetpack **DataStore** 替代 JSON，因为主要是在 App 内修改，DataStore 更原生且稳定。 |
| **UI** | 虚拟摇杆/按键 | **双向**. 需显示虚拟摇杆和虚拟按键。**实体优先** (Physical Priority): 当实体摇杆有动作时，屏蔽触控输入。 |
| **Identity** | App 包名 | **保持不变** (`com.example.dji_rc_pro`). |
| **UI** | 屏幕方向 | **横屏锁定** (Landscape Only). |
| **Comm** | 蓝牙选择 | **屏幕触控** (Screen UI). 使用屏幕上的虚拟按钮/列表选择蓝牙设备，而非实体按键。 |
| **Comm** | 共存策略 | **双发** (Dual Send). WiFi 和 蓝牙 同时进行数据发送与接收。 |
| **Protocol** | 参考标准 | **Xbox 风格**. 数据包内容包含双摇杆+按键，逻辑上参考 Xbox 手柄布局，但物理层协议保持自定义的 `0xAA...` 结构。 |

## 4. 补充分析 (Additional Analysis)

### 4.1 关于配置存储 (JSON vs DataStore)
用户提问：*配置需要可以在app内进行修改...是否需要使用json进行数据保存？*

**分析**:
*   **JSON 文件**: 优势是可以在电脑上编辑文件然后导入 App。缺点是 Android 10+ 的文件读写权限管理较严，且在 App 内修改后再回写 JSON 比较繁琐。
*   **DataStore (SharedPreferences)**: Android 原生推荐的轻量级存储。优势是读写极快，代码简单，自动处理并发。
*   **结论**: 既然要求 "在 App 内进行修改"，建议**优先使用 DataStore** 存储 IP、Port 和简单的开关配置。如果是为了备份配置，可以增加一个 "导出为 JSON" 的功能，但核心存储使用 DataStore。

### 4.2 摇杆归一化逻辑
由于 MSDK 的摇杆数值范围未知，我们将实现一个 `InputNormalizer` 类：
1.  **Raw Input**: 获取 MSDK 原始 Int 值。
2.  **Calibration**: 记录 Min/Max/Center。
3.  **Mapping**: 映射到 `0-255`。
    *   Center (128)
    *   Up/Right (255)
    *   Down/Left (0)

