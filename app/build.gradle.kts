import it.paolopasianot.cosmobile.CosmobileBuildType

plugins {
  alias(libs.plugins.cosmobile.android.application)
  alias(libs.plugins.cosmobile.android.application.compose)
  alias(libs.plugins.cosmobile.android.application.flavors)
  alias(libs.plugins.cosmobile.android.application.jacoco)
  alias(libs.plugins.baselineprofile)
  alias(libs.plugins.roborazzi)
  id("kotlinx-serialization")
}

android {
  namespace = "it.paolopasianot.cosmobile"
  compileSdk = 35

  defaultConfig {
    applicationId = "it.paolopasianot.cosmobile"
    versionCode = 1
    versionName = "0.0.1" // X.Y.Z; X = Major, Y = minor, Z = Patch level

    // Custom test runner to set up Hilt dependency graph
    testInstrumentationRunner = "it.paolopasianot.cosmobile.core.testing.CosmobileTestRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  buildTypes {
    debug {
      applicationIdSuffix = CosmobileBuildType.DEBUG.applicationIdSuffix
    }
    release {
      isMinifyEnabled = true
      applicationIdSuffix = CosmobileBuildType.RELEASE.applicationIdSuffix
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

      // To publish on the Play store a private signing key is required, but to allow anyone
      // who clones the code to sign and run the release variant, use the debug signing key.
      // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
      signingConfig = signingConfigs.getByName("debug")
      // Ensure Baseline Profile is fresh for release builds.
      baselineProfile.automaticGenerationDuringBuild = true
    }
  }

  packaging {
    resources {
      excludes.add("/META-INF/{AL2.0,LGPL2.1}")
      excludes.add("/META-INF/INDEX.LIST")
    }
  }
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }
  namespace = "it.paolopasianot.cosmobile"
}

dependencies {

  implementation(projects.feature.navigation)

  implementation(projects.core.common)
  implementation(projects.core.ui)
  implementation(projects.core.designsystem)
  implementation(projects.core.data)
  implementation(projects.core.model)
  implementation(projects.core.destination)

  implementation(libs.koin.android)
  implementation(libs.koin.android.compose)
  implementation(libs.koin.android.startup)
  implementation(libs.koin.android.workmanager)

  implementation(libs.kotlinx.serialization.json)

  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.compose.google.font)
  implementation(libs.androidx.compose.material3.adaptive)
  implementation(libs.androidx.compose.material3.adaptive.layout)
  implementation(libs.androidx.compose.material3.adaptive.navigation)
  implementation(libs.androidx.compose.material3.windowSizeClass)
  implementation(libs.androidx.compose.runtime.tracing)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.core.splashscreen)
  implementation(libs.androidx.lifecycle.runtimeCompose)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.profileinstaller)
  implementation(libs.androidx.tracing.ktx)
  implementation(libs.androidx.window.core)
  implementation(libs.kotlinx.coroutines.guava)
  implementation(libs.coil.kt)

  debugImplementation(libs.androidx.compose.ui.testManifest)

  testDemoImplementation(libs.robolectric)
  testDemoImplementation(libs.roborazzi)

  androidTestImplementation(kotlin("test"))
  androidTestImplementation(libs.androidx.test.espresso.core)
  androidTestImplementation(libs.androidx.navigation.testing)
  androidTestImplementation(libs.androidx.compose.ui.test)

  baselineProfile(projects.benchmarks)
}

baselineProfile {
  // Don't build on every iteration of a full assemble.
  // Instead enable generation directly for the release build variant.
  automaticGenerationDuringBuild = false
}

dependencyGuard {
  configuration("prodReleaseRuntimeClasspath")
}
