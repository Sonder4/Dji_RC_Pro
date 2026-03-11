# Error Log

## Read/Write Rules
- **Read**: Check this file before fixing bugs to see if a similar error has occurred.
- **Write**: Record any new compilation or runtime error, its cause, and the solution.

## Log Entries

### [2025-01-16] Kotlin Version Mismatch
- **Error**: `Module was compiled with an incompatible version of Kotlin. The binary version of its metadata is 2.1.0, expected version is 1.9.0.`
- **Cause**: The project was using Kotlin 1.9.0 but a dependency (likely DJI MSDK or a transitive one) was compiled with Kotlin 2.1.0.
- **Solution**: Updated `kotlin` version to `2.1.0` in `gradle/libs.versions.toml`.

### [2025-01-16] Unresolved Reference: BluetoothProfile
- **Error**: `Unresolved reference: BluetoothProfile` in `MainScreen.kt`.
- **Cause**: Missing import statement for `android.bluetooth.BluetoothProfile`.
- **Solution**: Added `import android.bluetooth.BluetoothProfile` to `MainScreen.kt`.

### [2025-01-16] App Name Override
- **Error**: App name displayed as "DJI Pilot 2" on Chinese language devices.
- **Cause**: DJI MSDK V5 includes a default `values-zh-rCN/strings.xml` that defines `app_name` as "DJI Pilot 2", which overrides the main `values/strings.xml`.
- **Solution**: 
  1. Created `app/src/main/res/values-zh-rCN/strings.xml` with `<string name="app_name">Dji_RC_Pro</string>`.
  2. Added `tools:replace="android:label"` to the `<application>` tag in `AndroidManifest.xml`.

### [2025-01-16] Compile-time Reference Error: secneo
- **Error**: `Unresolved reference: secneo` in `NCUApplication.kt` when calling `com.secneo.sdk.Helper.install(this)`.
- **Cause**: The `com.secneo.sdk.Helper` class is not available in the compile-time classpath of MSDK V5.17.0 (likely obfuscated or removed from public artifacts), but is required for initialization.
- **Solution**: Used Java reflection to invoke `install` method dynamically, bypassing compile-time checks. (Note: This prevents build failure but does not fix runtime class loading if the class is missing).

### [2025-01-16] Missing MSDK Dependencies
- **Error**: `Could not find com.dji:dji-sdk-v5-networkless:5.17.0`.
- **Cause**: The `networkless` artifact does not exist for version 5.17.0 in the DJI Maven repository.
- **Solution**: Switched to `dji-sdk-v5-networkImp:5.17.0` and `dji-sdk-v5-aircraft:5.17.0`.

### [2025-01-16] Runtime ClassNotFoundException: SDKManager
- **Error**: `java.lang.NoClassDefFoundError: Failed resolution of: Ldji/v5/manager/SDKManager;` causing app crash on launch.
- **Cause**: The public Maven artifacts for MSDK V5.17.0 appear to be incomplete or missing the core classes.
- **Solution**: 
  1. Verified user provided `dji-sdk-v5-networkImp-5.17.0.aar` in `DJI MSDK` folder.
  2. Copied it to `app/libs`.
  3. Modified `build.gradle.kts` to use local AAR for `networkImp`.
  4. Excluded transitive `networkImp` from `aircraft` dependency to avoid "Duplicate class" errors.

### [2025-01-16] Duplicate Class Definition
- **Error**: `Type dji.v5.network.ProgressResponseBody$InnerProgressListener is defined multiple times`.
- **Cause**: Including `dji-sdk-v5-networkImp` both as a local AAR and as a transitive dependency of `dji-sdk-v5-aircraft`.
- **Solution**: Added `exclude(group = "com.dji", module = "dji-sdk-v5-networkImp")` to the `dji-sdk-v5-aircraft` dependency block.

### [2025-01-16] App Crash on Startup (secneo)
- **Error**: App crashes on startup due to missing native library loading (UnsatisfiedLinkError likely).
- **Cause**: `com.secneo.sdk.Helper.install(this)` was commented out because of compile-time resolution issues.
- **Solution**: Implemented reflection-based call to `Helper.install` in `NCUApplication.attachBaseContext`. This allows the app to compile and attempts to load the helper at runtime.
