pluginManagement {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        google()
        mavenCentral()
        maven { url = uri("https://developer.dji.com/api/m2/android/maven") }
        // Optional local Flutter AAR repo; only add when present to avoid invalid
        // Windows-only URI scheme errors on Linux/macOS.
        val localFlutterRepo = file("${rootDir}/rustdesk/flutter_module/build/host/outputs/repo")
        if (localFlutterRepo.exists()) {
            maven { url = localFlutterRepo.toURI() }
        }
        maven { url = uri("https://storage.flutter-io.cn/download.flutter.io") }
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Dji_RC_Pro"
include(":app")
