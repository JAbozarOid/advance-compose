import com.helper.COMPOSE_LIFECYCLE_ALIAS
import com.helper.COMPOSE_PREVIEW_ALIAS
import com.helper.COMPOSE_RUNTIME_ALIAS
import com.helper.COMPOSE_UI_TOOLING_ALIAS
import com.helper.COMPOSE_VIEW_MODEL_ALIAS
import com.helper.DependencyType
import com.helper.ADVANCECOMPOSE_CORE_UI_NAME
import com.helper.ADVANCECOMPOSE_DESIGN_SYSTEM_NAME
import com.helper.ADVANCECOMPOSE_DOMAIN_NAME
import com.helper.ADVANCECOMPOSE_HILT_ALIAS
import com.helper.ADVANCECOMPOSE_LIBRARY_ALIAS
import com.helper.HILT_NAVIGATION_ALIAS
import com.helper.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


class AdvanceFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(ADVANCECOMPOSE_LIBRARY_ALIAS)
            pluginManager.apply(ADVANCECOMPOSE_HILT_ALIAS)
            dependencies {
                add(
                    configurationName = DependencyType.IMPLEMENTATION.configurationName,
                    project(ADVANCECOMPOSE_DOMAIN_NAME)
                )
                add(
                    configurationName = DependencyType.IMPLEMENTATION.configurationName,
                    project(ADVANCECOMPOSE_CORE_UI_NAME)
                )
                add(
                    configurationName = DependencyType.IMPLEMENTATION.configurationName,
                    project(ADVANCECOMPOSE_DESIGN_SYSTEM_NAME)
                )
                add(
                    configurationName = DependencyType.IMPLEMENTATION.configurationName,
                    libs.findLibrary(HILT_NAVIGATION_ALIAS).get()
                )
                add(
                    configurationName = DependencyType.IMPLEMENTATION.configurationName,
                    libs.findLibrary(COMPOSE_PREVIEW_ALIAS).get()
                )
                add(
                    configurationName = DependencyType.IMPLEMENTATION.configurationName,
                    libs.findLibrary(COMPOSE_UI_TOOLING_ALIAS).get()
                )
                add(
                    configurationName = DependencyType.IMPLEMENTATION.configurationName,
                    libs.findLibrary(COMPOSE_RUNTIME_ALIAS).get()
                )
                add(
                    configurationName = DependencyType.IMPLEMENTATION.configurationName,
                    libs.findLibrary(COMPOSE_RUNTIME_ALIAS).get()
                )
                add(
                    configurationName = DependencyType.IMPLEMENTATION.configurationName,
                    libs.findLibrary(COMPOSE_LIFECYCLE_ALIAS).get()
                )
                add(
                    configurationName = DependencyType.IMPLEMENTATION.configurationName,
                    libs.findLibrary(COMPOSE_VIEW_MODEL_ALIAS).get()
                )
            }
        }
    }
}