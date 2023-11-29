plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    //id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.advancecompose"
    compileSdk = rootProject.ext.get("compileSdkVersion") as Int?

    defaultConfig {
        applicationId = "com.example.advancecompose"
        minSdk = rootProject.ext.get("minSdkVersion") as Int?
        targetSdk = rootProject.ext.get("targetSdkVersion") as Int?
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    flavorDimensions += "version"
    productFlavors {
        create("demo") {
            dimension = "version"
            applicationIdSuffix = ".demo"
            versionNameSuffix = "-demo"

            buildConfigField("String", "BASE_URL", "\"https://fakestoreapi.com/\"")
        }
        create("prod") {
            dimension = "version"
            applicationIdSuffix = ".full"
            versionNameSuffix = "-full"

            buildConfigField("String", "BASE_URL", "\"https://fakestoreapi.com/\"")
        }
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":data"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))

    // Network
    api("com.squareup.okhttp3:okhttp:${rootProject.ext.get("okhttp_version")}")
    api("com.squareup.okhttp3:logging-interceptor:${rootProject.ext.get("okhttp_version")}")

    //retrofit
    api("com.squareup.retrofit2:retrofit:${rootProject.ext.get("retrofit_version")}")
    api("com.squareup.retrofit2:converter-moshi:${rootProject.ext.get("retrofit_version")}")

    // Moshi
    api("com.squareup.moshi:moshi-kotlin:${rootProject.ext.get("moshi")}")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    kapt("com.squareup.moshi:moshi-kotlin-codegen:${rootProject.ext.get("moshi")}")
    api("com.squareup.moshi:moshi-adapters:${rootProject.ext.get("moshi")}")

    // Hilt
    implementation("com.google.dagger:hilt-android:${rootProject.ext.get("hilt_version")}")
    kapt("com.google.dagger:hilt-android-compiler:${rootProject.ext.get("hilt_version")}")
    kapt("com.google.dagger:hilt-compiler:${rootProject.ext.get("hilt_version")}")
    implementation("androidx.hilt:hilt-navigation-compose:${rootProject.ext.get("hilt_navigation_compose")}")

    // koin
    implementation("io.insert-koin:koin-android:${rootProject.ext.get("koin_version")}")
    implementation("io.insert-koin:koin-annotations:${rootProject.ext.get("koin_ksp_version")}")
    //ksp("io.insert-koin:koin-ksp-compiler:${rootProject.ext.get("koin_ksp_version")}")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}