plugins {
    alias(libs.plugins.advance.android.library)
    alias(libs.plugins.advance.android.library.compose)
    alias(libs.plugins.advance.android.features)

}

android {
    namespace = "com.example.advancecompose.feature.interview"

}

dependencies {
    implementation(libs.gson)
    implementation(libs.kotlinx.coroutines.android)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    testImplementation(libs.androidx.test.ext.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockito)
}