// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.1.0" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

buildscript {
    extra.apply {
        set("minSdkVersion", 24)
        set("targetSdkVersion", 33)
        set("compileSdkVersion", 33)

        set("okhttp_version","4.10.0")
        set("retrofit_version","2.9.0")

        set("moshi","1.13.0")

        set("hilt_version",2.44)
        set("hilt_navigation_compose","1.0.0")
    }
}