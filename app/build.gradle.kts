plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.trigger.geminiassistant"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.trigger.geminiassistant"
        minSdk = 26
        targetSdk = 35
        versionCode = 20250428
        versionName = "1.2"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // ⚡ Thêm phần này để fix lỗi JVM mismatch
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
}
