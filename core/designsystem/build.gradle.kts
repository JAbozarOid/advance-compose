plugins {
    alias(libs.plugins.advance.android.library)
    alias(libs.plugins.advance.android.library.compose)
}

android {
    namespace = "com.example.advancecompose.core.designsystem"

}

dependencies {

    api(libs.androidx.compose.material3)
    implementation(libs.androidx.core.ktx)
}