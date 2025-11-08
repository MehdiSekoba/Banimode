plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "ir.mehdisekoba.banimode"
    compileSdk = 36

    defaultConfig {
        applicationId = "ir.mehdisekoba.banimode"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        compilerOptions.freeCompilerArgs.add("-XXLanguage:+PropertyParamAnnotationDefaultTargetMode")
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.foundation.layout)
    implementation("androidx.compose.material:material-icons-extended")

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    //di
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.interceptor)
    implementation(libs.moshi.kotlin)
    implementation(libs.converter.moshi)
    //image loading
    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.placeholder)
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    implementation(libs.coil.network.okhttp)

    //hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)
    //view model
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    //animation
    implementation(libs.lottie.compose)
    //navigation
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.material3.navigation3)
    //pars html
    implementation(libs.jsoup)
    //shimmer
    implementation(libs.compose.placeholder)
    //network
    implementation(libs.connectivity.core)
    implementation(libs.connectivity.android)
    implementation(libs.connectivity.compose.device)
    //constraintlayout
    implementation(libs.androidx.constraintlayout.compose)
}