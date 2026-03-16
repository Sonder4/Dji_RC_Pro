# DJI RC Pro BLE + UDP 联调记录（2026-03-16）

**适用目录**: `/home/xuan/Tools/Dji_RC_Pro`  
**联调日期**: 2026-03-16  
**联调目标**: 将工作区对齐到 GitHub 仓库版本，重新构建 Android 与 ROS2 两端，完成 DJI RC Pro 真机 BLE 配对、UDP 主通道建立、双端地址同步和现场连通性验证，并记录问题与解决方案。

---

## 1. 本次联调环境

- 仓库远端：`https://github.com/Sonder4/Dji_RC_Pro`
- 本地 / 远端基线提交：`3387126`
- RC Pro `adb serial`：`4QQZLC700522QY`
- Android 包：`app/build/outputs/apk/debug/app-debug.apk`
- ROS2 后台会话：`tmux session rcpro_ros2_bleudp`
- 传输模式：`BLE + UDP`
- 主机实测地址：
  - IPv4：`10.202.168.216`
  - IPv6：`240c:c901:2:2a5c:bd3b:6e9f:8a2c:167`
- RC Pro 实测地址：
  - IPv4：`10.202.200.141`
  - IPv6：`240c:c901:2:1d07:1592:17a1:9c40:3ef7`

---

## 2. 本次遇到的主要问题

| 序号 | 问题现象 | 根本原因 | 处理结果 |
|---|---|---|---|
| 1 | App 长时间停留在“蓝牙已连接，等待短码配对” | ROS2 网关未启动时，App 只能完成 BLE 物理连接，无法继续拿到配对应答与状态同步 | ✅ 已确认依赖关系并完成联调 |
| 2 | `pair_control` 读取回来的不是主机 offer / ack，而是设备自己写进去的 probe / request | 紧凑配对载荷过大，超出 RC Pro 单次稳定读取预算 | ✅ 已缩减载荷 |
| 3 | App 侧拿不到 `network_info` / `status`，界面没有对端 IPv4 / IPv6 | BLE 状态载荷过大，读取出现 `status=133` 或空载荷 | ✅ 已缩减载荷 |
| 4 | `BLE_UDP` 下启用通知不稳定，CCCD 写入后没有稳定回调 | 当前 RC Pro 固件环境下 `BLE_UDP + CCCD` 不可靠 | ✅ 已回退为轮询方案 |
| 5 | 现场一度怀疑“IPv4 一直 ping 失败” | 旧配对地址、ROS2 未启动和状态不同步会导致目标地址判断失真；并非本轮最终链路故障 | ✅ 本轮最终 IPv4 / IPv6 均实测可通 |
| 6 | 需要确认地址是否自动获取，而非写死 | 主链路地址是动态获取，但 Android 端仍保留默认目标 IPv4 与固定 BLE MAC 回退项 | ⚠️ 已核对，属于剩余风险 |

---

## 3. 问题一：ROS2 未启动时，App 停留在等待配对

### 3.1 现象

- App 侧显示：
  - “蓝牙已连接，等待短码配对”
  - “正在发现主机”
- ROS2 话题列表存在，但未真正启动对应 gateway 时，App 侧拿不到完整链路状态。

### 3.2 根本原因

BLE 物理连接成功不等于 ROS2 端业务链路已经就绪。  
本项目当前流程中，真正的配对推进依赖 ROS2 端 BLE gateway 提供：

- `pair probe -> offer -> pair request -> pair ack`
- `network_info`
- `status`

如果 ROS2 gateway 未启动，App 只能看到蓝牙已连上，但不会得到可用主机地址和主通道信息。

### 3.3 解决方案

联调顺序固定为：

1. 先构建并启动 ROS2 gateway。
2. 再启动 App。
3. 再观察 BLE 配对与 UDP 主通道切换。

本轮实际采用：

```bash
./scripts/build_ros2_dji_rc_pro_ws.sh
tmux new-session -d -s rcpro_ros2_bleudp 'cd /home/xuan/Tools/Dji_RC_Pro && TRANSPORT_MODE=ble_udp ./scripts/run_dji_rc_pro_gateway.sh'
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell monkey -p com.example.dji_rc_pro -c android.intent.category.LAUNCHER 1
```

---

## 4. 问题二：紧凑配对载荷过大，导致 `pair_control` 回退

### 4.1 现象

早期现场日志里，App 在读 `pair_control` 时并没有拿到主机发出的紧凑 offer / ack，而是读回：

- 自己写入的 `pair probe`
- 或自己写入的 `pair request`

这会让 UI 看起来像“蓝牙已连上，但始终没有真正配对成功”。

### 4.2 根本原因

RC Pro 在当前 BLE 环境下，对 `pair_control` 的稳定读取预算有限。  
原来的紧凑 offer / ack 中仍携带了重复字段，尤其是：

- `host_ipv4`
- `host_ipv6`

导致主机写入成功，但 App 侧读取时回退到旧值或自身写入值。

### 4.3 解决方案

缩减 `pair_control` 载荷，只保留配对推进所需字段，去掉 offer / ack 中重复的主机地址字段。

本次处理后的关键结果：

- App 日志出现：

```text
ROS2 BLE pair ack: session=0e8f323ed9124496 family=IPV6 address=240c:c901:2:2a5c:bd3b:6e9f:8a2c:167
```

- ROS2 日志出现：

```text
Compact BLE paired client=cf9fe9d501728452 family=ipv6 address=240c:c901:2:2a5c:bd3b:6e9f:8a2c:167:1387
```

---

## 5. 问题三：`network_info` / `status` 过大，导致界面缺少对端地址

### 5.1 现象

在配对成功后，App 一度仍然存在以下表现：

- 链路状态页没有对端 IPv4 / IPv6
- BLE 一直显示“正在验证 UDP”
- `onCharacteristicRead` 返回 `status=133` 或空载荷

### 5.2 根本原因

ROS2 端用于补充链路状态的两个 BLE 特征值载荷过大：

- `network_info`
- `status`

早期现场日志显示：

- `BLE read network_info bytes=398`
- `BLE read status bytes=344`

这超出了当前设备环境下的稳定读取范围。

### 5.3 解决方案

将两个载荷缩减为真正的紧凑格式：

- `network_info` 保留：
  - `4`
  - `6`
  - `c4`
  - `c6`
  - `f`
  - `b`
- `status` 保留：
  - `f`
  - `4`
  - `6`
  - `c4`
  - `c6`
  - `u`
  - `b`
  - `k`
  - `d`

本轮实测结果：

- App 日志：

```text
ROS2 BLE read network_info started=true
ROS2 BLE onCharacteristicRead uuid=...50003 status=0 bytes=130
ROS2 BLE read status started=true
ROS2 BLE onCharacteristicRead uuid=...50005 status=0 bytes=142
```

- ROS2 日志：

```text
BLE read network_info bytes=130
BLE read status bytes=142
```

说明 App 已能稳定读回双端地址和状态补充信息。

---

## 6. 问题四：`BLE_UDP` 通知不稳定

### 6.1 现象

尝试在 `BLE_UDP` 模式下启用通知时，现场日志出现：

- `CCCD writeStarted=true`
- 但 `onDescriptorWrite` 没有稳定回调

### 6.2 根本原因

这台 RC Pro 当前固件 / 蓝牙栈环境下，`BLE_UDP + CCCD` 组合不稳定。  
继续依赖通知会让配对推进和状态读取时好时坏。

### 6.3 解决方案

将 `BLE_UDP` 策略明确回退为：

- `pair_control`: 轮询
- `network_info`: 读
- `status`: 轮询读

即：

- `BLE_UDP -> POLL_ONLY`
- `BLE_ONLY + supportsNotify -> NOTIFY_WITH_POLL_FALLBACK`

这样能保证在 RC Pro 上的稳定性优先于“理论上更省轮询”的实现。

---

## 7. 问题五：为什么现场看起来“IPv4 一直 ping 失败”

### 7.1 现场误判来源

在以下条件下，IPv4 会表现得像是“总失败”：

- ROS2 端未启动
- 旧配对地址没有及时清理
- App 还没从 BLE / discovery 拿到最新地址
- Android 端仍在回退使用缓存目标地址

这时看到的失败，并不等于“当前 Wi-Fi 环境下 IPv4 一定不通”。

### 7.2 本轮最终实测结果

本轮在双端重新完成 BLE 配对和 UDP 主通道切换后，电脑到 RC Pro 的 IPv4 / IPv6 都已经实测成功：

```bash
ping -c 2 -W 1 10.202.200.141
ping -6 -c 2 -W 1 240c:c901:2:1d07:1592:17a1:9c40:3ef7
```

结果：

- IPv4：`2/2` 成功，平均约 `6.7 ms`
- IPv6：`2/2` 成功，平均约 `8.8 ms`

### 7.3 当前结论

- 本轮 `2026-03-16` 实测中，IPv4 和 IPv6 都可以正常互通。
- 因此，“IPv4 一直 ping 失败”不是本轮最终状态。
- 如果未来再次出现同类现象，应优先检查：
  - ROS2 gateway 是否已启动
  - 旧配对地址是否已清理
  - BLE 是否已同步最新对端地址

---

## 8. 动态取址检查结果

### 8.1 已确认是动态获取的部分

#### ROS2 主机地址

ROS2 端地址不是写死的，而是从本机当前网卡实时读取：

- Python BLE gateway 使用 `ip -j addr show up`
- C++ UDP gateway 使用 `getifaddrs()`

因此：

- 主机 IPv4 是当前网卡实时值
- 主机 IPv6 是当前网卡实时值

#### RC Pro 本机地址

Android 端在发 BLE probe 时，会先从 `NetworkInterface` 枚举当前设备地址，再把：

- `clientIpv4`
- `clientIpv6`

带入 probe。

#### 对端地址

对端地址来自：

- discovery offer
- BLE `offer`
- BLE `network_info`
- BLE `status`
- pair ack

也就是说，主链路上的双端 IP 同步逻辑本身是动态的。

### 8.2 仍然存在的硬编码回退项

这轮代码核对后，Android 端仍保留两个需要注意的固定值：

1. 默认目标 IPv4：`192.168.1.83`
2. 固定 BLE MAC：`10:5F:AD:63:70:17`

它们不是当前主链路同步的主来源，但在“尚未同步到最新地址”的早期阶段，仍可能参与回退逻辑。

### 8.3 当前结论

- 结论一：**双端实际连接使用的 IP 地址以动态同步结果为主，不是写死值。**
- 结论二：**当前实现还没有做到完全零硬编码回退。**
- 结论三：如果后续要进一步提升鲁棒性，建议移除默认目标 IPv4 回退，并把固定 BLE MAC 改成“按服务 UUID / 广播名称匹配”优先。

---

## 9. 本轮构建与验证结果

### 9.1 ROS2

执行：

```bash
pytest -q ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/test/test_ble_gateway_ip_fields.py \
  ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/test/test_ble_gateway_executable.py
./scripts/build_ros2_dji_rc_pro_ws.sh
```

结果：

- `7 passed in 0.30s`
- ROS2 工作区构建通过

### 9.2 Android

执行：

```bash
./gradlew testDebugUnitTest assembleDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

结果：

- `BUILD SUCCESSFUL`
- `adb install -r` 返回 `Success`

### 9.3 App 与 ROS2 两端联调证据

Android 侧关键日志：

```text
ROS2 BLE pair ack: session=0e8f323ed9124496 family=IPV6 address=240c:c901:2:2a5c:bd3b:6e9f:8a2c:167
ROS2 BLE onCharacteristicRead uuid=...50003 status=0 bytes=130
ROS2 BLE onCharacteristicRead uuid=...50005 status=0 bytes=142
UDP connected
Heartbeat received from UDP
```

ROS2 侧关键日志：

```text
Compact BLE paired client=cf9fe9d501728452 family=ipv6 address=240c:c901:2:2a5c:bd3b:6e9f:8a2c:167:1387
transport_status update ble_state=udp_primary primary=udp host_ipv4=10.202.168.216 host_ipv6=240c:c901:2:2a5c:bd3b:6e9f:8a2c:167 client_ipv4=10.202.200.141 client_ipv6=240c:c901:2:1d07:1592:17a1:9c40:3ef7
BLE read network_info bytes=130
BLE read status bytes=142
```

当前主结论：

- BLE 配对成功
- UDP 主通道成功建立
- 双端 IPv4 / IPv6 已同步
- 摇杆 / 按键链路未被本轮修复动作破坏

---

## 10. 相关代码位置

- Android：
  - `app/src/main/java/com/example/dji_rc_pro/domain/ble/BleManager.kt`
  - `app/src/main/java/com/example/dji_rc_pro/domain/ble/Ros2BleProfile.kt`
  - `app/src/main/java/com/example/dji_rc_pro/domain/ble/Ros2BlePairControlDelivery.kt`
- ROS2：
  - `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/src/tools/dji_rc_pro_ble_gateway.py`
  - `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/test/test_ble_gateway_ip_fields.py`
  - `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/test/test_ble_gateway_executable.py`

---

## 11. 最终结论

这轮问题的核心不是“DJI RC Pro 无法连接电脑”，而是：

1. 需要先启动 ROS2 端，App 才能完成完整业务配对。
2. BLE 紧凑配对和状态载荷必须严格控制大小，否则 RC Pro 会读不到主机真实状态。
3. `BLE_UDP` 模式下当前设备更适合轮询，不适合依赖通知。
4. 本轮最终实测中，IPv4 与 IPv6 都已经可以正常 `ping`，UDP 主通道稳定工作。
5. 当前主链路地址同步是动态的，但 Android 端仍有默认目标 IPv4 与固定 BLE MAC 两个残余回退项，后续可以继续清理。

---

**记录维护**: NCU-RC2026 开发团队  
**最后更新**: 2026-03-16
