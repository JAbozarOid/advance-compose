plugins {
    alias(libs.plugins.advance.android.library)
    alias(libs.plugins.advance.android.dagger.hilt)
}

android {
    namespace = "com.example.advancecompose.core.ui"

}

dependencies {

    implementation(libs.androidx.lifecycle.viewModelCompose)
}