# RustDesk Source Modification Plan (Phase 1) - COMPLETED

This plan details the modifications required in the `rustdesk` Flutter project to integrate it as an embedded view within the DJI RC Pro Android app.

## Objectives
1.  **Strip UI**: Remove default Home, Settings, Server, and File Transfer interfaces.
2.  **Embedded Mode**: App should launch into a "Waiting" state, controlled entirely by the Android Host.
3.  **MethodChannel**: Implement `com.dji.rcpro/rustdesk` to receive connection commands.
4.  **Direct Connect**: Support direct IP connection with password, bypassing ID server.
5.  **Feature Filtering**: Retain Remote Control, Terminal, and Camera; disable others.

## Modified Files

### 1. `lib/main.dart` (Entry Point) [COMPLETED]
**Goal**: Initialize MethodChannel and listen for commands.

*   **Action**:
    *   Define `static const platform = MethodChannel('com.dji.rcpro/rustdesk');`
    *   In `runMobileApp()`, immediately after `WidgetsFlutterBinding.ensureInitialized()`, set up the method call handler.
    *   **Handler Logic**:
        *   `connect`: Extract `id` (IP), `password`, `type` (Remote/Terminal/Camera).
        *   Trigger the connection logic (see `ConnectionPage` modifications).
        *   `disconnect`: Close current session.
    *   **Cleanup**: Disabled `checkUpdate()` and `gFFI.userModel.refreshCurrentUser()`.

### 2. `lib/mobile/pages/home_page.dart` (UI Stripping) [COMPLETED]
**Goal**: Replace the default tabbed interface with a passive "Waiting" screen.

*   **Action**:
    *   Remove `BottomNavigationBar` and all tab pages (`ConnectionPage`, `ServerPage`, `SettingsPage`, `ChatPage`).
    *   Replace `build()` body with a `Scaffold` containing a black background and a center text "Waiting for Host Command...".

### 3. `lib/mobile/pages/connection_page.dart` (Logic Extraction) [SKIPPED/NOT NEEDED]
**Goal**: Expose connection logic to `main.dart` without requiring user input.

*   **Status**: The `connect()` function in `lib/common.dart` was identified as the correct entry point. It accepts all necessary parameters (`password`, `isTerminal`, `isViewCamera`) directly. No need to modify `connection_page.dart` as we bypass it entirely.

### 4. `lib/common.dart` (Configuration) [VERIFIED]
**Goal**: Disable unwanted features globally.

*   **Status**: Verified `connect()` function signature. It handles direct connection and parameters correctly. Updates and User Refresh were disabled in `main.dart`.

## Implementation Steps

1.  **Backup**: Ensure current state is committed.
2.  **Step 1**: Modify `home_page.dart` to show a blank screen. Verify app builds and runs "empty". [DONE]
3.  **Step 2**: Implement `MethodChannel` in `main.dart`. Log received commands to console for verification. [DONE]
4.  **Step 3**: Link `MethodChannel` command to `connect()` function. Hardcode a test IP to verify. [DONE]
5.  **Step 4**: Handle "Terminal" and "Camera" modes by passing specific flags to the connection session. [DONE]

## Feature Verification

| Feature | Implementation Strategy | Status |
| :--- | :--- | :--- |
| **Auto-Reconnect** | Handled by Android Host (MethodChannel retries). Flutter side just accepts `connect` calls. | Ready |
| **Password** | Passed via MethodChannel. Injected into `connect()` -> `RemotePage` -> `gFFI.start()`. | Ready |
| **Landscape** | Enforced by Android Manifest (`screenOrientation="landscape"`). | Ready |
| **Scaling** | Default RustDesk behavior (Adaptive). | Ready |

## Next Actions (Phase 2)
*   Build AAR (`flutter build aar`).
*   Sync with Android project.
