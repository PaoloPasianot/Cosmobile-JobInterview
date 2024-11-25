pluginManagement {
  includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Cosmobile"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":app-catalog")
include(":benchmarks")

//Core
include(":core:common")
include(":core:model")
include(":core:network")
include(":core:designsystem")
include(":core:data")
include(":core:data-test")
include(":core:ui")
include(":core:destination")

include(":lint")

include(":feature:navigation")

//Feature Auth Screens
include(":feature:auth:login")

//Feature Screens
include(":feature:dashboard")
