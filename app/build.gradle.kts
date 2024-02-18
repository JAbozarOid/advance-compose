plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.dagger.hilt.plugin)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.advancecompose"
}

dependencies{
    //hilt
    ksp(libs.dagger.hilt.compiler)
    implementation(libs.dagger.hilt)
}