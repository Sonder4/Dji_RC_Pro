# Project Context

## Project Overview
**Name**: Dji_RC_Pro
**Goal**: Develop an Android application for DJI RC Pro using MSDK V5, focusing on remote controller data acquisition (sticks, buttons, wheels) and Bluetooth/WiFi transmission.
**Tech Stack**: Kotlin 2.1.0, Jetpack Compose, DJI MSDK V5.17.0, Android SDK 34.

## Current Status
- **Phase**: RustDesk Integration (Fusion)
- **State**: 🟢 **Active**
- **Note**: Integration design finalized. Rules and workflow defined in `vibecoding.md`.

## Recent Changes
- **Deployment** (2026-02-07):
  - **APK安装**: 成功使用ADB安装 `app-release.apk` 到设备 `4QQZLC700522QY`
  - **包名验证**: `com.example.dji_rc_pro` 已安装到设备
  - **ADB命令**: `adb install -r app-release.apk` 执行成功
- **Docs**: 
  - Created `docs/02_Architecture/DESIGN_RustDesk_Integration.md` defining the integration architecture and API.
  - Updated `.trae/rules/vibecoding.md` with "RustDesk Integration Workflow".
- **Bluetooth**:
  - **Paired Devices**: Now automatically loaded and displayed at the top of the scan list.
  - **Sorting**: List sorted by [Paired Status] -> [RSSI].
  - **UI**: Added "PAIRED" indicator to device cards.
- **UI**: 
  - **Virtual Sticks**: 150dp size, 24dp padding (equidistant).
  - **Virtual Buttons**: 45dp size.
  - **Fixes**: Deprecated `LinearProgressIndicator` usage updated.
- **Input**: 
  - **Wheel Debug**: Broadened scan to include all axes + VSCROLL/HSCROLL.
- **C1/C2 按键修复**:
  - **HID 调试日志**: 在 `UsbHidProtocolParser.kt` 的 `parse()` 函数中添加了原始数据的 HEX 打印日志，便于通过 Logcat 观察按键变化。
  - **按键监控**: 在 `MainActivity.kt` 的 `onKeyDown/onKeyUp` 中增加了日志打印，捕获所有按键的 `keyCode`。
- **系统功能**:
  - **屏幕常亮**: 在 `MainActivity.kt` 中添加了 `FLAG_KEEP_SCREEN_ON` 标志位，确保 App 在前台时屏幕保持开启。
  - **权限检查**: 确认 AndroidManifest.xml 中已包含所有必需权限（BLUETOOTH、WiFi、Location 等）。

## Build Commands
- **Build Debug**: `.\gradlew.bat assembleDebug`
- **Build Release**: `.\gradlew.bat assembleRelease`
- **Install Debug**: `.\gradlew.bat installDebug`
- **Clean & Build**: `.\gradlew.bat clean assembleDebug`
- **Stacktrace**: `.\gradlew.bat assembleDebug --stacktrace`
- **APK Location**: `app\build\outputs\apk\debug\app-debug.apk`

## Todo List
- [x] Create initial project structure
- [x] Implement UI (MainScreen) with Compose
- [x] Implement MSDK Manager (Skeleton)
- [x] Configure Bluetooth (Permissions, Manager)
- [x] Add Virtual Buttons (UI)
- [x] Fix compilation errors (Kotlin, Compose)
- [x] Fix App Name issue
- [x] Resolve Runtime Crash (Missing MSDK Classes)
- [x] Verify Stick/Button Data Acquisition
- [x] Add Debug Mode for Wheel Input (Dual Source)
- [x] Update UI Styling (150dp Sticks, 24dp Padding)
- [x] Implement Bluetooth Paired Devices Scanning/Sorting
- [x] **[FUSION]** Finalize Integration Design & Rules
- [x] Implement STM32 USB Serial Support (Library + Helper + Test UI)
- [x] **[FUSION]** Phase 1: Modify RustDesk Source (Strip UI, Channel Impl)
- [ ] **[FUSION]** Phase 2: Build RustDesk AAR
- [ ] **[FUSION]** Phase 3: Implement Navigation & Host Logic
- [ ] **[FUSION]** Phase 4: Integrate AAR & Verify
- [ ] Verify Wheel Data Acquisition (Pending User Feedback)
- [ ] Implement Bluetooth Data Transmission (Write Logic needs verification)
- [ ] Implement WiFi Data Transmission

## Known Issues
- Deprecated Bluetooth API usage (writeCharacteristic, value setter) - works for target SDK but should be migrated to `writeCharacteristic(char, value, type)` for Android 13+ if needed.
