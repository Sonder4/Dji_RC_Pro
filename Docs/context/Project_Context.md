# Project Context

## Project Overview
**Name**: Dji_RC_Pro
**Goal**: Develop an Android application for DJI RC Pro using MSDK V5, focusing on remote controller data acquisition (sticks, buttons, wheels) and Bluetooth/WiFi transmission.
**Tech Stack**: Kotlin 2.1.0, Jetpack Compose, DJI MSDK V5.17.0, Android SDK 34.

## Current Status
- **Phase**: Core Integration & Debugging
- **State**: 🟢 **Active**
- **Note**: Bluetooth Paired Devices support added. UI refined.

## Recent Changes
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
- [ ] Verify Wheel Data Acquisition (Pending User Feedback)
- [ ] Implement Bluetooth Data Transmission (Write Logic needs verification)
- [ ] Implement WiFi Data Transmission

## Known Issues
- Deprecated Bluetooth API usage (writeCharacteristic, value setter) - works for target SDK but should be migrated to `writeCharacteristic(char, value, type)` for Android 13+ if needed.
