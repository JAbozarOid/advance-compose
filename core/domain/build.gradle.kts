plugins {
    alias(libs.plugins.advance.android.library)
    alias(libs.plugins.advance.android.dagger.hilt)
}

android {
    namespace = "com.example.advancecompose.core.domain"

}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}