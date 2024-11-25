plugins {
  alias(libs.plugins.cosmobile.android.library)
  alias(libs.plugins.cosmobile.android.library.compose)
  alias(libs.plugins.cosmobile.android.library.jacoco)
  alias(libs.plugins.roborazzi)
}

android {
  defaultConfig {
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  namespace = "it.paolopasianot.cosmobile.core.designsystem"
}

dependencies {
  lintPublish(projects.lint)

  api(libs.androidx.compose.foundation)
  api(libs.androidx.compose.foundation.layout)
  api(libs.androidx.compose.material.iconsExtended)
  api(libs.androidx.compose.material3)
  api(libs.androidx.compose.material3.adaptive)
  api(libs.androidx.compose.material3.navigationSuite)
  api(libs.androidx.compose.runtime)
  api(libs.androidx.compose.ui.util)

  implementation(libs.androidx.compose.google.font)
  implementation(libs.coil.kt.compose)

  testImplementation(libs.androidx.compose.ui.test)
  testImplementation(libs.androidx.compose.ui.testManifest)

  testImplementation(libs.robolectric)

  androidTestImplementation(libs.bundles.androidx.compose.ui.test)
}
