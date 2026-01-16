# AGENTS

<skills_system priority="1">

## Available Skills

<!-- SKILLS_TABLE_START -->
<usage>
Invoke: Bash("openskills read <skill-name>")
</usage>

<available_skills>

<skill>
<name>android-studio-kotlin</name>
<description>Android Studio + Kotlin app workflow (Gradle, Compose, architecture, debugging, tests, release).</description>
<location>project</location>
</skill>

<skill>
<name>dji-msdk</name>
<description>DJI Mobile SDK (Android) API documentation helper. Use this skill to retrieve API details, class references, and integration guides from the local documentation.</description>
<location>project</location>
</skill>

<skill>
<name>doc-coauthoring</name>
<description>Guide users through a structured workflow for co-authoring documentation.</description>
<location>project</location>
</skill>

<skill>
<name>docs2markdown</name>
<description>Convert HTML documentation to GitHub-flavored Markdown.</description>
<location>project</location>
</skill>

<skill>
<name>moai-lang-kotlin</name>
<description>Kotlin 2.0+ development specialist covering Ktor, coroutines, Compose Multiplatform, and Kotlin-idiomatic patterns.</description>
<location>project</location>
</skill>

<skill>
<name>mobile-development</name>
<description>Build modern mobile applications with React Native, Flutter, Swift/SwiftUI, and Kotlin/Jetpack Compose.</description>
<location>project</location>
</skill>

</available_skills>
<!-- SKILLS_TABLE_END -->

</skills_system>

# Build Commands
**Compilation Commands:**
```powershell
cd E:\Desktop\app\Dji_RC_Pro
.\gradlew.bat clean assembleDebug
```

**Common Commands:**
```powershell
# Build only (no clean)
.\gradlew.bat assembleDebug
# Build Release
.\gradlew.bat assembleRelease
# Build and Install
.\gradlew.bat installDebug
# Detailed Error Log
.\gradlew.bat assembleDebug --stacktrace
```

**APK Output Location:**
`app\build\outputs\apk\debug\app-debug.apk`
