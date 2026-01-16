plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.dji_rc_pro"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.dji_rc_pro"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        
        multiDexEnabled = true
        ndk {
            abiFilters.add("arm64-v8a")
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        jniLibs {
            pickFirsts += "lib/arm64-v8a/libc++_shared.so"
            pickFirsts += "lib/armeabi-v7a/libc++_shared.so"
            keepDebugSymbols += "*/*/libconstants.so"
            keepDebugSymbols += "*/*/libdji_innertools.so"
            keepDebugSymbols += "*/*/libdjibase.so"
            keepDebugSymbols += "*/*/libDJICSDKCommon.so"
            keepDebugSymbols += "*/*/libDJIFlySafeCore-CSDK.so"
            keepDebugSymbols += "*/*/libdjifs_jni-CSDK.so"
            keepDebugSymbols += "*/*/libDJIRegister.so"
            keepDebugSymbols += "*/*/libdjisdk_jni.so"
            keepDebugSymbols += "*/*/libDJIUpgradeCore.so"
            keepDebugSymbols += "*/*/libDJIUpgradeJNI.so"
            keepDebugSymbols += "*/*/libDJIWaypointV2Core-CSDK.so"
            keepDebugSymbols += "*/*/libdjiwpv2-CSDK.so"
            keepDebugSymbols += "*/*/libFlightRecordEngine.so"
            keepDebugSymbols += "*/*/libvideo-framing.so"
            keepDebugSymbols += "*/*/libwaes.so"
            keepDebugSymbols += "*/*/libagora-rtsa-sdk.so"
            keepDebugSymbols += "*/*/libc++.so"
            keepDebugSymbols += "*/*/libc++_shared.so"
            keepDebugSymbols += "*/*/libmrtc_28181.so"
            keepDebugSymbols += "*/*/libmrtc_agora.so"
            keepDebugSymbols += "*/*/libmrtc_core.so"
            keepDebugSymbols += "*/*/libmrtc_core_jni.so"
            keepDebugSymbols += "*/*/libmrtc_data.so"
            keepDebugSymbols += "*/*/libmrtc_log.so"
            keepDebugSymbols += "*/*/libmrtc_onvif.so"
            keepDebugSymbols += "*/*/libmrtc_rtmp.so"
            keepDebugSymbols += "*/*/libmrtc_rtsp.so"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // DJI MSDK V5
    implementation("com.dji:dji-sdk-v5-aircraft:5.14.0")
    compileOnly("com.dji:dji-sdk-v5-aircraft-provided:5.14.0")
    
    // MSDK Dependencies (Strictly matched with reference project)
    implementation("com.google.code.gson:gson:2.9.1")
    implementation("com.squareup.okio:okio:1.17.2")
    implementation("com.squareup.wire:wire-runtime:2.2.0")
    implementation("com.squareup.okhttp3:okhttp:3.14.9")
    implementation("com.iqiyi.xcrash:xcrash-android-lib:3.1.0")
    implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.0")
    implementation("io.reactivex.rxjava2:rxjava:2.2.4")

    // Jetpack DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}