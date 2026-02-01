# RustDesk Integration Design (Fusion Scheme)

## 1. Architecture Overview
**Integration Method**: Flutter Embedding (AAR)
**Host**: Android Native (Kotlin) + DJI MSDK
**Module**: RustDesk (Flutter) as `dji-rustdesk.aar`

The RustDesk module runs as a Flutter Activity/Fragment within the host app. It communicates with the host via `MethodChannel` for connection control and state synchronization.

```mermaid
graph TD 
    A[MainActivity (Kotlin)] -->|Navigates| B[MainScreen (Compose)] 
    A -->|Navigates| C[RustDeskScreen (Compose/AndroidView)] 
    C -->|Embeds| D[FlutterEngine (RustDesk)] 
    D -->|MethodChannel| E[RustDesk Core (Rust/Dart)] 
    A -->|Maintains| F[MainViewModel] 
    F -->|Controls| G[UdpService (Background)] 
    G -->|Sends Data| H[Remote Endpoint (UDP)] 
```

## 2. Integration Workflow
1.  **Modify Source**: Edit `rustdesk/flutter` to strip UI and add direct connect logic.
2.  **Build AAR**: Run `flutter build aar --build-number=x.x.x` in `rustdesk/flutter`.
3.  **Integrate**: Copy `.aar` to `app/libs/` and sync Gradle.
4.  **Run**: Host app launches `RustDeskActivity` with connection parameters.

## 3. Communication Protocol (MethodChannel)
**Channel Name**: `com.dji.rcpro/rustdesk`

### 3.1 Host -> Flutter (Commands)
| Method | Arguments (JSON) | Description |
| :--- | :--- | :--- |
| `connect` | `{"id": "192.168.1.100", "password": "...", "is_direct": true, "view_only": true}` | Initiate direct LAN connection. |
| `disconnect` | `null` | Force disconnect and cleanup. |
| `updateConfig` | `{"view_only": true, "enable_audio": false}` | Update session settings dynamically. |

### 3.2 Flutter -> Host (Events)
| Method | Arguments (JSON) | Description |
| :--- | :--- | :--- |
| `onStateChange` | `{"state": "connecting"\|"connected"\|"failed", "error": "msg"}` | Connection state update. |
| `requestExit` | `null` | Request host to close the Flutter view (e.g., user clicked Back). |

## 4. Technical Specifications

### 4.1 Connection Logic
*   **Auto-Reconnect**: 
    *   **Max Retries**: 15 attempts.
    *   **Interval**: 2 seconds between attempts.
    *   **Logic**: Handled by Host (`MainViewModel`). If `onStateChange` returns "failed", Host waits 2s and calls `connect` again until max retries reached.
*   **Storage**:
    *   **Mechanism**: Jetpack DataStore (Preferences).
    *   **Keys**: `RUSTDESK_TARGET_IP`, `RUSTDESK_PASSWORD`.
    *   **Scope**: Stored in `MainScreen` logic, passed to `RustDeskScreen` via Intent/ViewModel.

### 4.2 UI/UX Specifications
*   **Orientation**: Forced Landscape in RustDesk view (`Activity.screenOrientation = SCREEN_ORIENTATION_LANDSCAPE`).
*   **Input**:
    *   **Touch**: Mapped to mouse events (default RustDesk behavior).
    *   **Toolbar**: Custom toolbar (Compose overlay) for "Terminal" and "Camera" actions.
    *   **Scaling**: Adaptive (default Flutter behavior).
*   **Background**: `UdpService` must remain active (`START_STICKY`) during projection.

## 5. Feature Matrix
| Feature | Status | Note |
| :--- | :--- | :--- |
| Account/Login | Removed | LAN Direct Connect only. |
| Relay/Proxy | Removed | No internet dependency. |
| Terminal | Retained | Accessible via Toolbar -> Command Input. |
| Camera | Retained | Remote PC USB Camera viewing. |
| Auto-Reconnect | Added | Native-side logic (15 retries). |

## 6. Implementation Plan
1.  **Phase 1**: RustDesk Source Modification (Strip UI, Channel Impl).
    *   Target: `rustdesk/flutter/lib/main.dart`, `connection_page.dart`.
2.  **Phase 2**: AAR Build & Gradle Setup.
3.  **Phase 3**: Host App Navigation & Logic Implementation.
    *   Create `RustDeskActivity`.
    *   Implement `MethodChannel` handler.
    *   Implement Retry Loop.
4.  **Phase 4**: Integrate AAR & Verify.
