# DJI Mobile SDK (Android) 组件概览

本文档旨在帮助开发者快速理解 `Docs/Android_API/en` 目录下的 DJI Mobile SDK 组件结构。所有 API 文档文件均为 **HTML 格式**，请在浏览器中查看或使用编辑器打开。

## 组件目录结构 (`Docs/Android_API/en/Components/`)

以下是所有可用组件目录的中文说明：

### 核心 SDK 管理 (Core SDK Management)

- **`Components/DJISDKManager/`**
  - **功能**：SDK 注册、连接、初始化入口。
  - **关键类**：`DJISDKManager`, `SDKManagerCallback`。
  
- **`Components/IKeyManager/`**
  - **功能**：统一的 Key-Value API，用于访问所有硬件能力（电池、相机、云台、飞行控制器等）。这是现代 DJI MSDK 开发中最核心的接口。
  - **关键类**：`DJIKey` (基类), `CameraKey`, `FlightControllerKey`, `BatteryKey`。

- **`Components/DJIError/`**
  - **功能**：定义 SDK 返回的错误码及错误处理类。

- **`Components/DJICommonCallbacks/`**
  - **功能**：SDK 通用的回调接口定义（如异步操作完成的回调 `CompletionCallback`）。

### 飞行与任务控制 (Flight & Mission Control)

- **`Components/IIntelligentFlightManager/`**
  - **功能**：管理智能飞行模式。
  - **包含功能**：智能跟随 (ActiveTrack)、航点飞行 (Waypoint Mission)、兴趣点环绕 (POI)。

- **`Components/IFlyZoneManager/`**
  - **功能**：管理 GEO 地理围栏系统。
  - **包含功能**：禁飞区 (NFZ) 状态查询、解禁申请、限飞区警告。

- **`Components/IFlightLogManager/`**
  - **功能**：访问和管理飞行日志数据。

- **`Components/FlightRecordManager/`**
  - **功能**：飞行记录管理（旧版接口或替代实现）。

- **`Components/DJIFlightRecordManager/`**
  - **功能**：DJI 专有的飞行记录管理功能，处理飞行数据的记录与同步。

### 设备状态与健康 (Device State & Health)

- **`Components/IDeviceHealthManager/`**
  - **功能**：提供飞行器及其各子组件（传感器、动力系统等）的实时诊断信息和健康状态。

- **`Components/IDeviceStatusManager/`**
  - **功能**：监控已连接设备（飞行器、遥控器）的整体连接状态。

- **`Components/IDataProtectionManager/`**
  - **功能**：管理用户数据的隐私设置和保护策略。

- **`Components/ILDMManager/`**
  - **功能**：本地数据模式 (Local Data Mode, LDM) 管理。
  - **作用**：控制 SDK 是否允许进行网络数据传输，满足高安全性需求。

- **`Components/IAreaCodeManager/`**
  - **功能**：管理设备的地区代码，确保无线电发射符合当地法规。

### 外设与连接 (Peripheral & Connectivity)

- **`Components/ILTEManager/`**
  - **功能**：管理 4G/LTE Dongle 模块的连接状态、认证和信号强度。

- **`Components/IMediaDataCenter/`**
  - **功能**：高带宽媒体数据管理中心。
  - **包含功能**：相机实时视频流 (Live Stream) 获取、照片/视频文件的预览与下载。

- **`Components/DJIError_DJILDMError/`**
  - **功能**：专门针对本地数据模式 (LDM) 的错误码定义。

## 如何使用 API 文档

1.  **查找功能**：
    *   如果需要控制相机参数（ISO、快门、拍照），请查阅 `IKeyManager` 中的 `CameraKey`。
    *   如果需要执行自动飞行任务，请查阅 `IIntelligentFlightManager`。
    *   如果需要处理视频流，请查阅 `IMediaDataCenter`。

2.  **阅读格式**：
    *   目录下的文件均为 `.html` 文件。
    *   文件内容包含类的方法签名、参数说明和常量定义。

3.  **Key-Value 模式**：
    *   MSDK V5 及部分 V4 功能重度依赖 `KeyManager`。
    *   使用模式通常为：`KeyManager.getInstance().performAction(key, ...)` 或 `KeyManager.getInstance().setValue(key, ...)`。
    *   请在 `Components/IKeyManager/` 下查找具体的 `Key` 类定义。
