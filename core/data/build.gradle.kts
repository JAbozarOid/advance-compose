plugins {
    alias(libs.plugins.advance.android.library)
    alias(libs.plugins.advance.android.dagger.hilt)
}

android {
    namespace = "com.example.advancecompose.core.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(projects.core.domain)
}