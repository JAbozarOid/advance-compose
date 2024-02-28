import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {

    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.android.gradle)

}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "advance.android.library"
            implementationClass = "AdvanceLibraryConventionPlugin"
        }
        register("androidApplication") {
            id = "advance.android.application"
            implementationClass = "AdvanceApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "advance.android.application.compose"
            implementationClass = "AdvanceApplicationComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "advance.android.library.compose"
            implementationClass = "AdvanceLibraryComposeConventionPlugin"
        }
        register("androidDaggerHilt") {
            id = "advance.android.dagger.hilt"
            implementationClass = "AdvanceDaggerHiltConventionPlugin"
        }
        register("androidFeatures") {
            id = "advance.android.features"
            implementationClass = "AdvanceFeatureConventionPlugin"
        }
    }
}
