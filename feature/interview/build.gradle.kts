plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.advancecompose.feature.interview"
}

dependencies {
    implementation(libs.gson)

}