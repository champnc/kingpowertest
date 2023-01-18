plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.hilt)
}

android {
    namespace = "com.champnc.kingpowertest"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.champnc.kingpowertest"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField(type = "String", name = "BASE_URL", value = "\"https://jsonplaceholder.typicode.com/\"")
        }
        release {
            buildConfigField(type = "String", name = "BASE_URL", value = "\"https://jsonplaceholder.typicode.com/\"")
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
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        correctErrorTypes = true
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle)

//    test
    testImplementation(libs.androidx.junit)
    testImplementation(platform(libs.androidx.junit.bom))
    testImplementation(libs.androidx.junit.jupiter)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)
    testImplementation(libs.jetbrains.kotlinx.coroutine.test)
    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

//    compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

//    accompanist
    implementation(libs.google.accompanist.navigation.animation)
    implementation(libs.google.accompanist.navigation.material)

//    hilt
    implementation(libs.google.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.google.hilt.android.compiler)

//    retrofit
    implementation(libs.square.retrofit2)
    implementation(libs.square.gson.converter)
    implementation(libs.square.okhttp3)
    implementation(libs.square.okhttp3.logging)
    implementation(libs.google.gson)

//    coil
    implementation(libs.coil.compose)
}
