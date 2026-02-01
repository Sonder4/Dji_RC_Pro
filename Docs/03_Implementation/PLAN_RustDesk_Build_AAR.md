# RustDesk AAR Build Guide (Phase 2)

This guide details the steps to build the `flutter-release.aar` from the modified RustDesk source.

## Prerequisites
- Flutter SDK installed and in PATH.
- Android SDK installed (which you have).
- Rust toolchain (cargo) installed (for `liblibrustdesk.so` if rebuilding native libs, though `flutter build aar` usually expects prebuilt libs or handles it via build scripts).

## Build Command

Open a terminal in `e:\Desktop\app\Dji_RC_Pro\rustdesk\flutter` and run:

```bash
flutter build aar --no-profile --no-debug --release
```

## Expected Output
The build process will generate a local Maven repository structure at:
`e:\Desktop\app\Dji_RC_Pro\rustdesk\flutter\build\host\outputs\repo`

## Integration Steps (to be performed in Phase 3/4)

1.  **Copy AAR**: Move `flutter-release.aar` to `e:\Desktop\app\Dji_RC_Pro\app\libs\`.
2.  **Add Dependencies**: Ensure `app/build.gradle.kts` includes the local AAR and transitive dependencies (Flutter embedding, plugins).

## Troubleshooting

### "Command not found: flutter"
If you see this error, you need to add Flutter to your PATH or use the full path to the flutter executable.

### "Missing liblibrustdesk.so"
If the build fails due to missing native libraries:
1.  Navigate to `rustdesk` root.
2.  Run `./build.py --android` (requires Python and Cargo).
    *   *Note*: This might be complex on Windows. If you already have the prebuilt `.so` files in `rustdesk/flutter/android/app/src/main/jniLibs`, you can skip this.

## Validation
Once built, verify the AAR exists:
`ls build/host/outputs/repo/com/rustdesk/flutter_release/1.0.0/flutter_release-1.0.0.aar`
