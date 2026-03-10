# DJI RC Pro Controller App - 技术文档中心

**项目代号**: NCU-RC2026  
**文档版本**: 1.0  
**最后更新**: 2026-02-01

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
└── development.md                     # 开发文档（原有）
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
|