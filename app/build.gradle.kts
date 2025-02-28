plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.fishtail.hstuadmissionhelper"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.fishtail.hstuadmissionhelper"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_17 // Updated to Java 17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.3")) // Ensure latest BOM version
    implementation("com.google.firebase:firebase-firestore") // Firestore
    implementation("com.google.android.gms:play-services-base:18.2.0") // Google Services Base
   //Cloud Messaging
    implementation("com.google.firebase:firebase-messaging")
    implementation ("com.google.firebase:firebase-auth")
    implementation ("com.google.android.gms:play-services-auth:20.3.0")

    // Maps
    implementation ("com.google.android.gms:play-services-maps:17.0.1")
    implementation ("com.google.android.gms:play-services-location:17.0.0")


    // AndroidX Libraries
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.maps)

    // UI Components
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.github.denzcoskun:ImageSlideshow:0.1.0")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //FAB

    implementation ("com.google.android.material:material:1.9.0")



}
