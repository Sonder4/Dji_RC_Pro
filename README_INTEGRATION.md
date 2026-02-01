# RustDesk Integration Guide

## Status: Source Modified, Build Pending

The RustDesk source code has been copied to `rustdesk/` and modified according to the requirements (Phase 1).
However, the automatic AAR compilation (Phase 2) could not be completed because the Flutter environment was locked by another process.

## Manual Build Steps

Please perform the following steps to complete the integration:

1.  **Close other Flutter processes**: Ensure no other IDEs or terminals are running Flutter commands.
2.  **Navigate to the Flutter project**:
    ```powershell
    cd e:\Desktop\app\Dji_RC_Pro\rustdesk\flutter
    ```
3.  **Build the AAR**:
    ```powershell
    flutter build aar --no-profile --no-debug --release
    ```
4.  **Copy the AAR**:
    After a successful build, copy the generated AAR file:
    *   From: `build\host\outputs\repo\com\rustdesk\flutter_release\1.0.0\flutter_release-1.0.0.aar`
    *   To: `e:\Desktop\app\Dji_RC_Pro\app\libs\`
5.  **Sync Gradle**:
    Open the Android project in Android Studio and sync Gradle. The `app/build.gradle.kts` has already been updated to include `libs/*.aar`.

## Modifications Applied

*   **MethodChannel**: `com.dji.rcpro/rustdesk` is registered in `main.dart`.
*   **UI Stripping**: `home_page.dart` has been replaced with a "Waiting for Host Command" screen.
*   **Connection Logic**: `main.dart` listens for `connect` commands and invokes the RustDesk connection logic.
