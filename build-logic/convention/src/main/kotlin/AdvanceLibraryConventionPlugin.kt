import com.android.build.gradle.LibraryExtension
import com.helper.ANDROIDX_CORE_KTX_ALIAS
import com.helper.ANDROID_LIBRARY_NAME
import com.helper.DependencyType
import com.helper.Dimensions
import com.helper.ORG_JETBRAINS_KOTLIN_NAME
import com.helper.configureKotlin
import com.helper.advanceAppConstant
import com.helper.libs
import com.helper.setCompileOption
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class AdvanceLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.applyPlugins()
        target.configureProject()
        target.addDependencies()

    }
}

private fun Project.applyPlugins() {
    this.apply {
        pluginManager.apply(ANDROID_LIBRARY_NAME)
        pluginManager.apply(ORG_JETBRAINS_KOTLIN_NAME)
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
    }
}

private fun Project.android(func: LibraryExtension.() -> Unit) {
    extensions.getByType(LibraryExtension::class.java).apply {
        func()
    }
}

private fun LibraryExtension.configureBuildType() {
    buildTypes {

    }
}

private fun LibraryExtension.configureDefaultConfig() {
    defaultConfig {
        minSdk = advanceAppConstant.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

private fun Project.addDependencies() {
    dependencies {
        add(
            DependencyType.IMPLEMENTATION.configurationName,
            libs.findLibrary(ANDROIDX_CORE_KTX_ALIAS).get()
        )
        add(
            DependencyType.IMPLEMENTATION.configurationName,
            libs.findLibrary(ANDROIDX_CORE_KTX_ALIAS).get()
        )
    }
}

fun LibraryExtension.configureFlavors() {

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