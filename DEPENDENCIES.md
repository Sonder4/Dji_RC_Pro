# Dependencies

## Android Build Toolchain

| Item | Version / Requirement |
|---|---|
| Android Studio | Ladybug or newer |
| Gradle Wrapper | 8.7 |
| Android Gradle Plugin | 8.6.0 |
| Kotlin | 2.1.0 |
| JDK | 17+ recommended |
| compileSdk | 35 |
| minSdk | 29 |
| targetSdk | 33 |

## Android Runtime Libraries

| Dependency | Version |
|---|---|
| `androidx.core:core-ktx` | `1.10.1` |
| `androidx.lifecycle:lifecycle-runtime-ktx` | `2.6.1` |
| `androidx.activity:activity-compose` | `1.8.0` |
| `androidx.compose:compose-bom` | `2024.04.01` |
| `androidx.compose.ui:ui` | BOM-managed |
| `androidx.compose.ui:ui-graphics` | BOM-managed |
| `androidx.compose.ui:ui-tooling-preview` | BOM-managed |
| `androidx.compose.material3:material3` | BOM-managed |
| `androidx.datastore:datastore-preferences` | `1.0.0` |
| `org.jetbrains.kotlinx:kotlinx-coroutines-android` | `1.7.3` |
| `com.github.mik3y:usb-serial-for-android` | `3.7.0` |
| `com.jakewharton.timber:timber` | `5.0.1` |

## Android Test Dependencies

| Dependency | Version |
|---|---|
| `junit:junit` | `4.13.2` |
| `androidx.test.ext:junit` | `1.1.5` |
| `androidx.test.espresso:espresso-core` | `3.5.1` |
| `androidx.compose.ui:ui-test-junit4` | BOM-managed |
| `androidx.compose.ui:ui-tooling` | BOM-managed |
| `androidx.compose.ui:ui-test-manifest` | BOM-managed |

## ROS2 Dependencies

### Host Environment

- Ubuntu 22.04
- ROS2 Humble
- `colcon`
- `ament_cmake`
- Python 3.10+
- BlueZ with GATT peripheral support

### ROS2 Package Dependencies

From `ros2_ws_dji_rc_pro/src/dji_rc_pro_bridge/package.xml`:

| Dependency | Type |
|---|---|
| `ament_cmake` | buildtool |
| `rosidl_default_generators` | buildtool |
| `geometry_msgs` | build / exec |
| `rclcpp` | build / exec |
| `std_msgs` | build / exec |
| `rosidl_default_runtime` | exec |
| `rclpy` | exec |

## External Tools

| Tool | Purpose |
|---|---|
| `adb` | Install APK, start app, capture logs |
| `bluetoothctl` | Inspect local adapter state |
| `ros2` CLI | Launch and inspect topics |
| `bash` | Run helper scripts |

## Non-Repository Configuration

These values are required locally but intentionally not committed:

- `local.properties`
  - `sdk.dir`
  - `DJI_API_KEY`
- Host Bluetooth adapter state
- A reachable Wi-Fi network shared by RC Pro and ROS2 host
