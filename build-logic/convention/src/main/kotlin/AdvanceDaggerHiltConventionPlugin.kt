import com.helper.DAGGER_HILT_NAME
import com.helper.DependencyType
import com.helper.GOOGLE_KSP
import com.helper.HILT_ANDROID_ALIAS
import com.helper.HILT_COMPILER_ALIAS
import com.helper.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class AdvanceDaggerHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            pluginManager.apply(DAGGER_HILT_NAME)
            pluginManager.apply(GOOGLE_KSP)

            dependencies {
                add(
                    configurationName = DependencyType.IMPLEMENTATION.configurationName,
                    libs.findLibrary(HILT_ANDROID_ALIAS).get()
                )
                add(
                    configurationName = DependencyType.KSP.configurationName,
                    libs.findLibrary(HILT_COMPILER_ALIAS).get()
                )
            }

        }
    }
}