package com.helper


enum class DependencyType(val configurationName: String) {

    IMPLEMENTATION("implementation"),
    ANDROID_TEST("androidTestImplementation"),
    DEBUG_IMPLEMENTATION("debugImplementation"),
    TEST("testImplementation"),
    API("api"),
    KAPT("kapt"),
    KSP("ksp"),

}