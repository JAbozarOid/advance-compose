pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "AdvanceCompose"
include(":app")
include(":core:data")
include(":core:ui")
include(":core:designsystem")
include(":feature:login")
include(":core:domain")
include(":feature:interview")
include(":feature:converter")
include(":feature:composenavigation")
