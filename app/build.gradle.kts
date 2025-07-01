plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.skillsync"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.skillsync"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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

/// firebase
    implementation(platform("com.google.firebase:firebase-bom:33.16.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("androidx.navigation:navigation-compose:2.7.7")
    // 🔐 Firebase Auth
    implementation("com.google.firebase:firebase-auth-ktx")

// 🔥 Firebase Firestore
    implementation("com.google.firebase:firebase-firestore-ktx")



    implementation("androidx.room:room-runtime:2.7.1")
    implementation("androidx.room:room-ktx:2.7.1")


//    implementation(libs.androidx.storage)
//    kapt("androidx.room:room-compiler:2.7.1")


    // hilt
    // Hilt core
    implementation("com.google.dagger:hilt-android:2.56.1")
//    kapt("com.google.dagger:hilt-android-compiler:2.56.1")

    // kapt("com.google.dagger:hilt-android-compiler:2.48")
    // For viewmodel support
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    // If you use Room or other Jetpack components

    //kapt("androidx.hilt:hilt-compiler:1.2.0")

    //firebase
//    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))
//    implementation("com.google.firebase:firebase-analytics")
//    // firebase authentication
//    implementation("com.google.firebase:firebase-auth-ktx")
//    // firebase database
//    implementation("com.google.firebase:firebase-database-ktx")
//    //firebase storage
//    implementation("com.google.firebase:firebase-storage-ktx")

    
    // coil : image loader
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")
    //retrofit for network communication
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")










    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}