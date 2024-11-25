plugins {
    alias(libs.plugins.cosmobile.android.library)
}

android {
    namespace = "it.paolopasianot.cosmobile.core.data.test"
}

dependencies {
    api(projects.core.data)

  implementation(libs.koin.android)
}
