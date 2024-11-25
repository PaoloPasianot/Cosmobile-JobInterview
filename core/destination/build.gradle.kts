plugins {
  alias(libs.plugins.cosmobile.android.library)
  alias(libs.plugins.cosmobile.android.library.compose)
  alias(libs.plugins.cosmobile.android.library.jacoco)
  id("kotlinx-serialization")
}

android {
  defaultConfig {
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  namespace = "it.paolopasianot.cosmobile.core.destination"
}


dependencies {
  implementation(libs.androidx.navigation.compose)
  implementation(libs.kotlinx.serialization.json)

  implementation(libs.koin.android)
}
