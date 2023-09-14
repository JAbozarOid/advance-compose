plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.core"
    compileSdk = rootProject.ext.get("compileSdkVersion") as Int?

    defaultConfig {
        minSdk = rootProject.ext.get("minSdkVersion") as Int?

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

    //viewmodel scope
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")

    // Network
    implementation("com.squareup.okhttp3:okhttp:${rootProject.ext.get("okhttp_version")}")
    implementation("com.squareup.okhttp3:logging-interceptor:${rootProject.ext.get("okhttp_version")}")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:${rootProject.ext.get("retrofit_version")}")
    implementation("com.squareup.retrofit2:converter-moshi:${rootProject.ext.get("retrofit_version")}")

    // Moshi
    implementation("com.squareup.moshi:moshi-kotlin:${rootProject.ext.get("moshi")}")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:${rootProject.ext.get("moshi")}")
    implementation("com.squareup.moshi:moshi-adapters:${rootProject.ext.get("moshi")}")

    // Hilt
    implementation("com.google.dagger:hilt-android:${rootProject.ext.get("hilt_version")}")
    kapt("com.google.dagger:hilt-android-compiler:${rootProject.ext.get("hilt_version")}")
    kapt("com.google.dagger:hilt-compiler:${rootProject.ext.get("hilt_version")}")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}