import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  `kotlin-dsl`
}

group = "it.paolopasianot.cosmobile.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
  compilerOptions {
    jvmTarget = JvmTarget.JVM_17
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.android.tools.common)
  compileOnly(libs.compose.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
  compileOnly(libs.ksp.gradlePlugin)
  compileOnly(libs.room.gradlePlugin)
  implementation(libs.truth)
}

tasks {
  validatePlugins {
    enableStricterValidation = true
    failOnWarning = true
  }
}

gradlePlugin {
  plugins {
    register("androidApplicationCompose") {
      id = "cosmobile.android.application.compose"
      implementationClass = "AndroidApplicationComposeConventionPlugin"
    }
    register("androidApplication") {
      id = "cosmobile.android.application"
      implementationClass = "AndroidApplicationConventionPlugin"
    }
    register("androidApplicationJacoco") {
      id = "cosmobile.android.application.jacoco"
      implementationClass = "AndroidApplicationJacocoConventionPlugin"
    }
    register("androidLibraryCompose") {
      id = "cosmobile.android.library.compose"
      implementationClass = "AndroidLibraryComposeConventionPlugin"
    }
    register("androidLibrary") {
      id = "cosmobile.android.library"
      implementationClass = "AndroidLibraryConventionPlugin"
    }
    register("androidFeature") {
      id = "cosmobile.android.feature"
      implementationClass = "AndroidFeatureConventionPlugin"
    }
    register("androidLibraryJacoco") {
      id = "cosmobile.android.library.jacoco"
      implementationClass = "AndroidLibraryJacocoConventionPlugin"
    }
    register("androidTest") {
      id = "cosmobile.android.test"
      implementationClass = "AndroidTestConventionPlugin"
    }
    register("androidRoom") {
      id = "cosmobile.android.room"
      implementationClass = "AndroidRoomConventionPlugin"
    }
    register("androidFirebase") {
      id = "cosmobile.android.application.firebase"
      implementationClass = "AndroidApplicationFirebaseConventionPlugin"
    }
    register("androidFlavors") {
      id = "cosmobile.android.application.flavors"
      implementationClass = "AndroidApplicationFlavorsConventionPlugin"
    }
    register("androidLint") {
      id = "cosmobile.android.lint"
      implementationClass = "AndroidLintConventionPlugin"
    }
    register("jvmLibrary") {
      id = "cosmobile.jvm.library"
      implementationClass = "JvmLibraryConventionPlugin"
    }
  }
}
