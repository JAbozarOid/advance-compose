import com.android.build.gradle.LibraryExtension
import com.example.advancecompose.configureKotlin
import com.example.advancecompose.constant
import com.example.advancecompose.libs
import com.example.advancecompose.setCompileOption
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.applyPlugins()
        target.configureProject()
        target.addDependencies()
    }
}

private fun Project.applyPlugins() {
    this.apply {
        plugin("com.android.library")
        plugin("org.jetbrains.kotlin.android")
    }
}

private fun Project.configureProject() {
    android {
        compileSdk = constant.compileSdk

        configureDefaultConfig()
        configureBuildType()
        setCompileOption()
        configureKotlin()
    }
}

private fun Project.android(func: LibraryExtension.() -> Unit) {
    extensions.getByType(LibraryExtension::class.java).apply {
        func()
    }
}

private fun LibraryExtension.configureBuildType() {
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

private fun LibraryExtension.configureDefaultConfig() {
    defaultConfig {
        minSdk = constant.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

private fun Project.addDependencies() {
    dependencies {
        "implementation"(libs.findLibrary("androidx.core.ktx").get())
        "implementation"(libs.findLibrary("androidx.appcompat").get())
        "implementation"(libs.findLibrary("android.material").get())
        "testImplementation"(libs.findLibrary("junit4").get())
        "androidTestImplementation"(libs.findLibrary("androidx.junit").get())
        "androidTestImplementation"(libs.findLibrary("androidx.test.espresso.core").get())
    }
}
