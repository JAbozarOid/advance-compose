plugins {
    alias(libs.plugins.advance.android.library)
    alias(libs.plugins.advance.android.library.compose)

}

android {
    namespace = "com.example.advancecompose.feature.interview"
}

dependencies {
    implementation(libs.gson)
    implementation(libs.kotlinx.coroutines.android)
}