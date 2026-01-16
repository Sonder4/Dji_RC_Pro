# Project Context

## Project Overview
**Name**: Dji_RC_Pro
**Goal**: Develop an Android application for DJI RC Pro using MSDK V5, focusing on remote controller data acquisition (sticks, buttons) and Bluetooth/WiFi transmission.
**Tech Stack**: Kotlin 2.1.0, Jetpack Compose, DJI MSDK V5.17.0, Android SDK 34.

## Current Status
- **Phase**: Core Integration & Debugging
- **State**: 🔴 **Blocked** (Runtime Crash)
- **Blocker**: `NoClassDefFoundError: SDKManager` persists despite successful compilation and installation. The public Maven artifacts for MSDK V5.17.0 seem to be missing core classes.

## Recent Changes
- **Dependencies**: Upgraded to Kotlin 2.1.0, MSDK 5.17.0. Added `okio`, `wire-runtime`, `lottie`.
- **Configuration**: Enabled MultiDex, configured `arm64-v8a` ABI filters, added ProGuard rules.
- **Code**: 
  - Fixed `MsdkManager` key references.
  - Implemented reflection for `secneo` initialization (currently commented out).
  - Added `MultiDex.install(this)` to `NCUApplication`.
  - Forced app name to "Dji_RC_Pro" via `values-zh-rCN`.

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
- [ ] **Resolve Runtime Crash (Missing MSDK Classes)**
- [ ] Verify Stick/Button Data Acquisition
- [ ] Implement Bluetooth Data Transmission
- [ ] Implement WiFi Data Transmission

## Known Issues
- **Runtime Crash**: App crashes immediately on launch due to `NoClassDefFoundError: SDKManager`. Multidex and ProGuard fixes have not resolved this, pointing to defective Maven artifacts.
