# Alignment Document: DJI RC Pro Custom Controller

## 1. Project Context Analysis

### 1.1 Project Overview
*   **Device**: DJI RC Pro (RM510).
*   **OS**: Android 10.
*   **Development Environment**: Android Studio, Kotlin.
*   **Core Dependency**: DJI MSDK V5.
*   **Target System**: Custom Drone/Rover running Ubuntu.
*   **Goal**: Use RC Pro as a generic controller for the custom vehicle.
*   **Package Name**: `com.example.dji_rc_pro`
*   **App Name**: `NCU-RC2026`
*   **App Key**: `d4c36b38844cab1a267ef684`

### 1.2 Communication Architecture
*   **Primary Link**: Wi-Fi (UDP).
*   **Topology**: **Client Mode**. RC Pro connects to an external AP (Router or PC Hotspot).
*   **Protocol**: Custom UDP Packet.
    *   Format: `[StartByte, StickL_X, StickL_Y, StickR_X, StickR_Y, Buttons, Checksum]`
*   **Secondary Link**: BLE (Optional/Backup).

### 1.3 Input Mapping
*   **Joysticks**:
    *   **Physical**: Left/Right sticks (4 axes) read via MSDK.
    *   **Virtual**: On-screen touch joysticks (Visual feedback + Touch control).
*   **Buttons**:
    *   Configuration via **JSON file**.
    *   Mappings for C1, C2, C3, 5D Button.

## 2. Requirement Understanding & Confirmation

### 2.1 Functional Requirements
1.  **Input Capture**:
    *   Read Physical Sticks via MSDK.
    *   Read Physical Buttons via MSDK.
    *   Implement Virtual Sticks on UI.
2.  **Network Transmission**:
    *   UDP Client.
    *   Send data at high frequency (e.g., 50Hz).
3.  **UI/UX**:
    *   **Virtual Joysticks**: Two on-screen circles mirroring physical input or accepting touch.
    *   **Status**: Connection state, IP/Port config.
4.  **Configuration**:
    *   Load button mapping from JSON.
    *   Configure DJI MSDK with App Key.

### 2.2 Boundary Confirmation
*   **In Scope**: Android App (Kotlin), UDP Client, MSDK Integration, UI.
*   **Out of Scope**: Ubuntu side code.

## 3. Intelligent Decision Strategy

### 3.1 Architecture Pattern
*   **MVVM**:
    *   `ViewModel`: Handles UI state (Virtual Sticks, Connection Status).
    *   `Service`: Background worker for MSDK polling and UDP sending.
    *   `Repository`: Abstraction over MSDK KeyManager.

### 3.2 Protocol Design (Confirmed)
*   **Packet Structure** (Byte Array):
    *   `0xAA` (Start)
    *   `LX` (Byte/Int16 mapped to 0-255 or similar)
    *   `LY`
    *   `RX`
    *   `RY`
    *   `BTN_MASK` (Bits for C1, C2, etc.)
    *   `CRC` (Checksum)

### 3.3 MSDK Configuration
*   **Registration**: Must call `SDKManager.getInstance().init()` with the API Key.
*   **Data Access**: Use `KeyManager.getInstance().listen()` on `RemoteControllerKey` items.

## 4. Next Steps
*   Create `DESIGN_Architecture.md` detailing the Class Structure and MSDK implementation.
*   Initialize Android Project.
