import com.helper.ANDROIDX_CORE_KTX_ALIAS
import com.helper.ANDROID_APPLICATION_NAME
import com.helper.DAGGER_HILT_ALIAS
import com.helper.DependencyType
import com.helper.ORG_JETBRAINS_KOTLIN_NAME
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.helper.BuildTypes
import com.helper.Dimensions
import com.helper.configureAndroidCompose
import com.helper.configureKotlin
import com.helper.advanceAppConstant
import com.helper.libs
import com.helper.setCompileOption
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType


class AdvanceApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {

        with(target) {

            pluginManager.apply(ANDROID_APPLICATION_NAME)
            pluginManager.apply(ORG_JETBRAINS_KOTLIN_NAME)
            configureProject()


            dependencies {
                DependencyType.IMPLEMENTATION.configurationName(
                    libs.findLibrary(ANDROIDX_CORE_KTX_ALIAS).get()
                )
                DependencyType.IMPLEMENTATION.configurationName(
                    libs.findLibrary(DAGGER_HILT_ALIAS).get()
                )
            }
        }

    }
}

private fun Project.configureProject() {
    android {
        compileSdk = advanceAppConstant.compileSdk
        configureDefaultConfig()
        configureBuildType()
        configureFlavors()
        setCompileOption()
        configureKotlin()
        val extension = extensions.getByType<ApplicationExtension>()
        configureAndroidCompose(extension)

        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
    }
}

fun BaseAppModuleExtension.configureFlavors() {

    val dimensionList = listOf(Dimensions.MARKET, Dimensions.HOST)
    flavorDimensionList += dimensionList.map { it.value }

    productFlavors {
        dimensionList.forEach { dimen ->
            dimen.flavors.forEach { flavor ->
                create(flavor.value){
                    dimension = dimen.value
                }
            }
        }
    }
}

private fun BaseAppModuleExtension.configureDefaultConfig() {
    defaultConfig {
        applicationId = advanceAppConstant.packageName
        minSdk = advanceAppConstant.minSdk
        targetSdk = advanceAppConstant.targetSdk
        versionCode = advanceAppConstant.versionCode
        versionName = advanceAppConstant.versionName

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

fun BaseAppModuleExtension.configureBuildType() {
    buildTypes {

        getByName(BuildTypes.DEBUG.value) {
            versionNameSuffix = BuildTypes.DEBUG.versionNameSuffix
            isDebuggable = BuildTypes.DEBUG.isDebuggable
        }

        getByName(BuildTypes.RELEASE.value) {
            versionNameSuffix = BuildTypes.DEBUG.versionNameSuffix
            isDebuggable = BuildTypes.DEBUG.isDebuggable
        }

    }
}

