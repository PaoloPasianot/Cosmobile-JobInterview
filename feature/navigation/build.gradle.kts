plugins {
  alias(libs.plugins.cosmobile.android.feature)
  alias(libs.plugins.cosmobile.android.library.compose)
  alias(libs.plugins.cosmobile.android.library.jacoco)
  id("kotlinx-serialization")
}

android {
  namespace = "it.paolopasianot.cosmobile.feature.navigation"
}

dependencies {
  implementation(projects.core.common)
  implementation(projects.core.destination)

  implementation(projects.feature.dashboard)

  implementation(projects.feature.auth.login)

  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.compose.ui.android)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.koin.android.navigation)

  implementation(libs.androidx.compose.material3.windowSizeClass)

  androidTestImplementation(libs.bundles.androidx.compose.ui.test)
}
