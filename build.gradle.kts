// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.0" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.3"
}

buildscript {
    extra.apply {
        set("minSdkVersion", 24)
        set("targetSdkVersion", 33)
        set("compileSdkVersion", 34)

        set("okhttp_version", "4.10.0")
        set("retrofit_version", "2.9.0")

        set("moshi", "1.14.0")

        set("hilt_version", 2.44)
        set("hilt_navigation_compose", "1.0.0")

        set("koin_version", "3.3.2")
        set("koin_ksp_version", "1.1.0")

        set("timber","5.0.1")
    }
}

subprojects {
    apply {
        from("$rootDir/detekt/detekt.gradle")
    }
}