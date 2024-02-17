import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.example.advancecompose.configureKotlin
import com.example.advancecompose.constant
import com.example.advancecompose.libs
import com.example.advancecompose.setCompileOption
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidAppConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.applyPlugins()
        target.configureProject()
        target.addDependencies()
    }
}

private fun Project.applyPlugins() {
    this.apply {
        plugin("com.android.application")
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

        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = constant.kotlinCompilerExtensionVersion
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}

private fun BaseAppModuleExtension.configureDefaultConfig() {
    defaultConfig {
        applicationId = "com.example.advancecompose"
        minSdk = constant.minSdk
        targetSdk = constant.targetSdk
        versionCode = constant.versionCode
        versionName = constant.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
}

private fun Project.android(func: BaseAppModuleExtension.() -> Unit) {
    extensions.getByType(BaseAppModuleExtension::class.java).apply {
        func()
    }
}

private fun BaseAppModuleExtension.configureBuildType() {
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

private fun Project.addDependencies() {
    dependencies {
        "implementation"(libs.findLibrary("androidx.core.ktx").get())
        "implementation"(libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
        "implementation"(platform(libs.findLibrary("androidx.compose.bom").get()))
        "implementation"(libs.findLibrary("dagger.hilt").get())
        "testImplementation"(libs.findLibrary("junit4").get())
        "androidTestImplementation"(libs.findLibrary("androidx.junit").get())
        "androidTestImplementation"(libs.findLibrary("androidx.test.espresso.core").get())
        "androidTestImplementation"(platform(libs.findLibrary("androidx.compose.bom").get()))
        "androidTestImplementation"(libs.findLibrary("androidx.compose.ui.test").get())
        "debugImplementation"(libs.findLibrary("androidx.compose.ui.tooling").get())
        "debugImplementation"(libs.findLibrary("androidx.compose.ui.testManifest").get())
        "implementation"(libs.findBundle("compose").get())
    }
}
