plugins {
    alias(libs.plugins.advance.android.application)
    alias(libs.plugins.advance.android.application.compose)
    alias(libs.plugins.advance.android.dagger.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.advancecompose"
}

dependencies{
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.android.material)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.window.manager)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(projects.core.data)
    implementation(projects.feature.login)
}