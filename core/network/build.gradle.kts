plugins {
  alias(libs.plugins.cosmobile.android.library)
  alias(libs.plugins.cosmobile.android.library.jacoco)
  id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
  id("kotlinx-serialization")
}

android {
  buildFeatures {
    buildConfig = true
  }
  namespace = "it.paolopasianot.cosmobile.core.network"
  testOptions {
    unitTests {
      isIncludeAndroidResources = true
    }
  }
}

secrets {
  defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
  api(libs.kotlinx.datetime)
  api(projects.core.common)
  api(projects.core.model)

  implementation(libs.coil.kt)
  implementation(libs.coil.kt.svg)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.okhttp.logging)

  implementation(libs.koin.android)

  implementation(libs.ktor)
  implementation(libs.ktor.logging)
  implementation(libs.ktor.contentNegotiation)
  implementation(libs.ktor.serialization.kotlinxJson)

  implementation(libs.slf4j)

  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.ktor.unittest)
  testImplementation(libs.ktor.mockserver)
}
