# DJI RC Pro Controller App - 技术文档中心

**项目代号**: NCU-RC2026  
**文档版本**: 1.0  
**最后更新**: 2026-03-09

---

## 📚 文档列表

### 核心文档

| 文档 | 说明 | 路径 |
|-----|------|------|
| **技术文档总览** | 项目概述、架构设计、技术栈 | [Technical_Documentation.md](./Technical_Documentation.md) |
| **UDP模块文档** | UdpService详细设计、流程、代码解析 | [UDP_Module.md](./UDP_Module.md) |
| **问题诊断记录** | 调试过程、问题修复、经验总结 | [Troubleshooting_Log.md](./Troubleshooting_Log.md) |
| **API接口文档** | 公共API、数据模型、协议定义 | [API_Reference.md](./API_Reference.md) |
| **部署使用指南** | 环境配置、编译安装、使用说明 | [Deployment_Guide.md](./Deployment_Guide.md) |
| **RC Pro ROS2 独立工作空间** | 独立 ROS2 bridge、局域网自动配对、首配兜底说明 | [04_Network_Improvement/DJI_RC_PRO_ROS2_WORKSPACE.md](./04_Network_Improvement/DJI_RC_PRO_ROS2_WORKSPACE.md) |
| **BLE + UDP 系统设计** | BLE/UDP 三模式、自动配对、地址同步、实测闭环与代码索引 | [04_Network_Improvement/SYSTEM_DESIGN_BLE_UDP.md](./04_Network_Improvement/SYSTEM_DESIGN_BLE_UDP.md) |

---

## 🚀 快速开始

### 1. 了解项目

👉 阅读 [技术文档总览](./Technical_Documentation.md) 了解：
- 项目背景与目标
- 系统架构（MVVM）
- 核心功能模块
- 数据流向

### 2. 深入UDP模块

👉 阅读 [UDP模块文档](./UDP_Module.md) 了解：
- UdpService架构设计
- 初始化流程与修复
- USB HID数据转发
- 关键代码实现

### 3. 排查问题

👉 阅读 [问题诊断记录](./Troubleshooting_Log.md) 了解：
- "Connection reset by peer"错误分析
- UDP服务启动问题
- 调试方法与工具
- 解决方案

### 4. 查看API

👉 阅读 [API接口文档](./API_Reference.md) 了解：
- UdpService公共方法
- MainViewModel API
- 数据模型定义
- 协议格式

### 5. 部署应用

👉 阅读 [部署使用指南](./Deployment_Guide.md) 了解：
- 环境准备
- 编译构建
- 安装部署
- 使用指南

👉 阅读 [RC Pro ROS2 独立工作空间说明](./04_Network_Improvement/DJI_RC_PRO_ROS2_WORKSPACE.md) 了解：
- 独立工作空间构建与启动
- 局域网自动发现与首配兜底
- 新旧 ROS2 网关切换关系
- 现场闭环验证方法

👉 阅读 [BLE + UDP 系统设计说明](./04_Network_Improvement/SYSTEM_DESIGN_BLE_UDP.md) 了解：
- `udp_only` / `ble_only` / `ble_udp` 三种模式
- BLE 紧凑配对、Wi‑Fi discovery、UDP 主通道切换
- Android 与 ROS2 两侧的关键代码入口
- 实机联调证据与日志位置

---

## 📋 文档结构

```
Docs/
├── README.md                          # 本文档（索引）
├── Technical_Documentation.md         # 技术文档总览
├── UDP_Module.md                      # UDP模块详细文档
├── Troubleshooting_Log.md             # 问题诊断与修复记录
├── API_Reference.md                   # API接口文档
├── Deployment_Guide.md                # 部署与使用指南
├── 04_Network_Improvement/           # 网络与 ROS2 接入改造
│   ├── DJI_RC_PRO_ROS2_WORKSPACE.md
│   └── SYSTEM_DESIGN_BLE_UDP.md
└── development.md                    # 开发文档（原有）
```

---

## 🔧 主要内容概览

### 技术架构

```
MVVM Architecture
├── UI Layer (Jetpack Compose)
├── ViewModel Layer (MainViewModel)
├── Service Layer (UdpService, BleService)
├── Manager Layer (UsbHidManager, ConnectionManager, etc.)
└── Domain Layer (ConfigRepository, Protocol)
```

### 核心功能

| 功能 | 状态 | 文档 |
|-----|------|------|
| UDP通信 | ✅ 已完成 | [UDP_Module.md](./UDP_Module.md) |
| USB HID读取 | ✅ 已完成 | [Technical_Documentation.md](./Technical_Documentation.md) |
| 数据转发 | ✅ 已完成 | [UDP_Module.md](./UDP_Module.md) |
| BLE通信 | ✅ 已完成 | [04_Network_Improvement/SYSTEM_DESIGN_BLE_UDP.md](./04_Network_Improvement/SYSTEM_DESIGN_BLE_UDP.md) |
| 数据监控 | ✅ 已完成 | [Technical_Documentation.md](./Technical_Documentation.md) |

### 修复记录

| 问题 | 严重程度 | 状态 |
|-----|---------|------|
| Connection reset by peer | 高 | ✅ 已修复 |
| UDP服务启动后立即停止 | 高 | ✅ 已修复 |
| Socket绑定到IPv6地址 | 中 | ✅ 已修复 |

---

## 📝 更新日志

### v1.0 (2026-02-01)

- ✅ 完成UDP服务修复
- ✅ 实现USB HID数据转发
- ✅ 编写完整技术文档
- ✅ 记录问题诊断过程

---

## 🤝 贡献

如需更新文档：
1. 修改对应 `.md` 文件
2. 更新版本号和日期
3. 在更新日志中添加记录

---

## 📞 支持

- **项目主页**: [GitHub Repository](https://github.com/your-org/dji-rc-pro)
- **问题反馈**: [Issues](https://github.com/your-org/dji-rc-pro/issues)
- **开发团队**: NCU-RC2026 Team

---

**维护者**: NCU-RC2026 开发团队  
**最后更新**: 2026-03-09
