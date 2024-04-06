plugins {
    alias(libs.plugins.advance.android.library)
    alias(libs.plugins.advance.android.dagger.hilt)
    alias(libs.plugins.advance.android.library.compose)
}

android {
    namespace = "com.example.advancecompose.core.ui"

}

dependencies {

    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
}