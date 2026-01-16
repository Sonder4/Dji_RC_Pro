# Task Atomization: NCU-RC2026 Controller App

> **Status**: Planned
> **Source**: [Project_Context.md](./Project_Context.md)
> **Goal**: Break down the development into executable atomic tasks.
> **Rules**: 
> *   **Context7**: Must be used for Android/Kotlin best practices.
> *   **DJI MSDK Skill**: Must be invoked (`openskills read dji-msdk`) before implementing MSDK logic.

## Phase 1: Project Foundation & Infrastructure

### Task 1.1: Project Initialization [COMPLETED]
*   **Goal**: Create a clean Android project structure.
*   **Input**: `CLAUDE.md`, `Project_Context.md`.
*   **Action**:
    *   Ensure directory structure matches `docs/development.md`.
    *   Configure `build.gradle` (MinSDK 29, TargetSDK 33+).
    *   Configure `AndroidManifest.xml` (Permissions: Internet, Bluetooth, Location, USB).
    *   Set Screen Orientation to `landscape`.
*   **Output**: Compilable "Hello World" App in Landscape mode.

### Task 1.2: Dependency Management [COMPLETED]
*   **Goal**: Integrate necessary libraries.
*   **Action**:
    *   Add DJI MSDK V5 dependencies (Maven repo).
    *   Add Jetpack DataStore (Preferences).
    *   Add Kotlin Coroutines.
    *   Add Lifecycle (ViewModel, LiveData).
*   **Output**: Updated `build.gradle` files.

### Task 1.3: Architecture Skeleton [COMPLETED]
*   **Goal**: Set up MVVM base.
*   **Action**:
    *   Create `BaseViewModel`.
    *   Create `NCUApplication` class (Context holder).
    *   Create empty `MainViewModel`.
*   **Output**: Basic class structure.

---

## Phase 2: Core Domain Logic (Pure Kotlin)

### Task 2.1: Protocol Implementation [COMPLETED]
*   **Goal**: Handle data packet construction.
*   **Action**:
    *   Implement `Crc16` util class (CCITT/IBM).
    *   Create `ControlPacket` data class.
    *   Implement `PacketBuilder` (takes Stick/Button state -> returns ByteArray).
    *   **Unit Test**: Verify Packet bytes against expected Xbox-like layout.
*   **Output**: `com.ncu.rc2026.protocol` package.

### Task 2.2: Input Normalization [COMPLETED]
*   **Goal**: Map arbitrary MSDK inputs to 0-255.
*   **Action**:
    *   Create `StickTransformer` class.
    *   Implement `calibrate(min, max, center)` logic.
    *   Implement `transform(rawValue) -> Int(0-255)` logic.
*   **Output**: `com.ncu.rc2026.domain.input` package.

### Task 2.3: Configuration Persistence [COMPLETED]
*   **Goal**: Save IP/Port settings.
*   **Action**:
    *   Implement `ConfigRepository` using DataStore.
    *   Keys: `TARGET_IP`, `TARGET_PORT`, `JOYSTICK_DEADZONE`.
*   **Output**: Working persistence layer.

---

## Phase 3: Hardware Integration (MSDK)

### Task 3.1: MSDK Initialization [COMPLETED]
*   **Goal**: Register App with DJI.
*   **Action**:
    *   Implement `MsdkWrapper` singleton.
    *   Call `SDKManager.init()` in Application `onCreate`.
    *   Handle Registration Callback (Success/Fail).
*   **Output**: App logs "MSDK Registered" on startup.

### Task 3.2: Input Listener [COMPLETED]
*   **Goal**: Read Physical Hardware.
*   **Action**:
    *   Use `KeyManager` to listen to Left/Right Stick.
    *   Use `KeyManager` to listen to C1, C2, 5D buttons.
    *   Feed data into `StickTransformer`.
*   **Output**: Logcat shows real-time stick values when moved.

---

## Phase 4: Communication Modules

### Task 4.1: UDP Service [COMPLETED]
*   **Goal**: High-frequency UDP sending.
*   **Action**:
    *   Create `UdpService` (Foreground Service).
    *   Create background thread for Socket IO.
    *   Implement 50Hz Loop: `GetState -> BuildPacket -> Send`.
*   **Output**: UDP packets sending to configured IP.

### Task 4.2: Bluetooth LE Service [COMPLETED]
*   **Goal**: BLE Connectivity.
*   **Action**:
    *   Create `BleService`.
    *   Implement `scan()` (for UI).
    *   Implement `connect(device)`.
    *   Implement `writeCharacteristic()` loop (Dual Send).
*   **Output**: BLE connection and data transmission.

---

## Phase 5: User Interface

### Task 5.1: Virtual Controls
*   **Goal**: On-screen Joysticks.
*   **Action**:
    *   Create `VirtualStickView` (Custom View).
    *   Handle Touch Events (Multitouch).
    *   Implement Priority Logic (Physical overrides Touch).
*   **Output**: Interactive Joysticks on screen.

### Task 5.2: Main Dashboard
*   **Goal**: Main Operation Screen.
*   **Action**:
    *   Layout: Two Virtual Sticks, Status Bar (WiFi/BLE/Battery).
    *   Placeholder: Video Surface (Blank black box).
    *   Button: "Settings" (IP/Port).
    *   Button: "Bluetooth" (Scan Dialog).
*   **Output**: Complete UI.

### Task 5.3: Dialogs
*   **Goal**: Configuration UI.
*   **Action**:
    *   `SettingsDialog`: Edit IP/Port.
    *   `BleScanDialog`: List devices, Click to connect.
*   **Output**: Functional Dialogs.

---

## Phase 6: Integration & Verification

### Task 6.1: Wiring
*   **Goal**: Connect all components.
*   **Action**:
    *   `MainViewModel` observes `MsdkWrapper` & `VirtualStickView`.
    *   `MainViewModel` updates `UdpService` & `BleService`.
*   **Output**: Fully functional App.

### Task 6.2: Final Acceptance
*   **Goal**: Verify against requirements.
*   **Action**:
    *   Check Landscape Mode.
    *   Check Dual Send.
    *   Check Config Persistence.
    *   Check MSDK Input.
*   **Output**: Ready for Release.
