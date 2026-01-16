# Design Document: NCU-RC2026 Controller App

## 1. System Architecture

### 1.1 Layered Design (MVVM)

```mermaid
graph TD
    UI[UI Layer (Activity/XML)] --> VM[MainViewModel]
    VM --> Repo[ControllerRepository]
    Service[Background Service] --> Repo
    Service --> UDP[UdpClient]
    Repo --> MSDK[DJI MSDK Manager]
    MSDK --> HW[RC Pro Hardware]
```

### 1.2 Core Components
1.  **MsdkWrapper**: Singleton wrapper around `DJIKeyManager`. Handles registration and key listening.
2.  **ControllerRepository**: Exposes `Flow<StickState>` and `Flow<ButtonState>`.
3.  **UdpSenderService**: Foreground Service (to keep alive). Collects state from Repository and sends UDP packets.
4.  **VirtualStickView**: Custom View for drawing the joysticks on screen.

## 2. Module Design

### 2.1 MSDK Integration (`MsdkWrapper`)
*   **Initialization**: `SDKManager.getInstance().init(context, callback)`
*   **Key Listening**:
    *   `KeyManager.getInstance().listen(KeyTools.createKey(RemoteControllerKey.KeyStickLeftHorizontal), listener)`
    *   Same for other axes and buttons.
*   **Data Normalization**: Convert MSDK integer values (usually 0-1024 or similar) to Float [-1.0, 1.0].

### 2.2 Protocol Implementation (`UdpClient`)
*   **Packet Format**:
    ```kotlin
    data class ControlPacket(
        val start: Byte = 0xAA.toByte(),
        val lx: Byte, // Map -1.0..1.0 to 0..255
        val ly: Byte,
        val rx: Byte,
        val ry: Byte,
        val btnMask: Byte, // Bits: 0=C1, 1=C2, 2=C3, 3=5D_Up, etc.
        val crc: Byte
    )
    ```
*   **Frequency**: 50Hz timer.

### 2.3 Configuration (`JsonConfig`)
*   **File**: `config.json` in App Storage.
*   **Structure**:
    ```json
    {
      "udp_ip": "192.168.1.100",
      "udp_port": 8888,
      "buttons": {
        "C1": "TOGGLE_LED",
        "C2": "ARM_DISARM"
      }
    }
    ```

## 3. Interface Specifications

### 3.1 Data Models
```kotlin
data class StickState(
    val lx: Float, // -1.0 to 1.0
    val ly: Float,
    val rx: Float,
    val ry: Float
)

data class ButtonState(
    val c1: Boolean,
    val c2: Boolean,
    val c3: Boolean,
    val fiveD: FiveDimState // UP, DOWN, LEFT, RIGHT, PRESS, NONE
)
```

## 4. Implementation Plan
1.  **Setup**: Configure Gradle for MSDK V5.
2.  **Core**: Implement `MsdkWrapper` and `ControllerRepository`.
3.  **Network**: Implement `UdpClient`.
4.  **UI**: Build Main Activity with Virtual Sticks.
5.  **Integration**: Wire everything in `ViewModel` and `Service`.
