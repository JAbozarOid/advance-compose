package com.helper

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies




internal fun Project.configureAndroidCompose(
    commonExtensions: CommonExtension<*, *, *, *, *>
) {
    commonExtensions.apply {

        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion =
                libs.findVersion(COMPOSE_COMPILER_ALIAS).get().toString()
        }
        dependencies {
            val bom = libs.findLibrary(COMPOSE_BOM_ALIAS).get()


            add(configurationName = DependencyType.API.configurationName, platform(bom))
            add(
                configurationName = DependencyType.API.configurationName,
                libs.findLibrary(COMPOSE_PREVIEW_ALIAS).get()
            )
            add(
                configurationName = DependencyType.API.configurationName,
                libs.findLibrary(COMPOSE_FOUNDATION_ALIAS).get()
            )
            add(
                configurationName = DependencyType.API.configurationName,
                libs.findLibrary(COMPOSE_RUNTIME_ALIAS).get()
            )
            add(
                configurationName = DependencyType.API.configurationName,
                libs.findLibrary(COMPOSE_UI_TOOLING_ALIAS).get()
            )
            add(
                configurationName = DependencyType.API.configurationName,
                libs.findLibrary(COMPOSE_UI_ALIAS).get()
            )
            add(configurationName = DependencyType.ANDROID_TEST.configurationName, platform(bom))

        }

    }


}
