plugins {
  alias(libs.plugins.cosmobile.android.library)
  alias(libs.plugins.cosmobile.android.library.compose)
  alias(libs.plugins.cosmobile.android.library.jacoco)
}

android {
  defaultConfig {
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  namespace = "it.paolopasianot.cosmobile.core.ui"
}

dependencies {
  api(libs.androidx.metrics)
  api(projects.core.designsystem)
  api(projects.core.model)
  api(projects.core.common)

  implementation(libs.androidx.compose.material)

  implementation(libs.androidx.lifecycle.runtimeCompose)
  implementation(libs.androidx.browser)
  implementation(libs.coil.kt)
  implementation(libs.coil.kt.compose)
}
