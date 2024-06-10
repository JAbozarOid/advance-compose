plugins {
    alias(libs.plugins.advance.android.library)
    alias(libs.plugins.advance.android.library.compose)
    alias(libs.plugins.advance.android.dagger.hilt)
    alias(libs.plugins.advance.android.features)
}

android {
    namespace = "com.example.advancecompose.feature.converter"

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}