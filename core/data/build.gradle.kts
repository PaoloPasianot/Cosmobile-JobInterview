plugins {
  alias(libs.plugins.cosmobile.android.library)
  alias(libs.plugins.cosmobile.android.library.jacoco)
  id("kotlinx-serialization")
}

android {
  namespace = "it.paolopasianot.cosmobile.core.data"
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
      isReturnDefaultValues = true
    }
  }
}

dependencies {
  api(projects.core.common)
  api(projects.core.model)
  api(projects.core.network)

  implementation(libs.koin.android)

  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.kotlinx.serialization.json)
}
