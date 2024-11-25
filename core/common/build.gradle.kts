plugins {
  alias(libs.plugins.cosmobile.android.library)
  alias(libs.plugins.cosmobile.android.library.jacoco)
  id("kotlinx-serialization")
}

android {
  namespace = "it.paolopasianot.cosmobile.core.common"
}

dependencies {

  implementation(libs.kotlinx.coroutines)
  implementation(libs.koin.android)

  implementation(libs.kotlinx.serialization.json)

  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.turbine)
}
