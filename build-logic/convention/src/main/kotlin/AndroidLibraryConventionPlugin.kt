import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import it.paolopasianot.cosmobile.configureFlavors
import it.paolopasianot.cosmobile.configureGradleManagedDevices
import it.paolopasianot.cosmobile.configureKotlinAndroid
import it.paolopasianot.cosmobile.configurePrintApksTask
import it.paolopasianot.cosmobile.disableUnnecessaryAndroidTests
import it.paolopasianot.cosmobile.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.library")
        apply("org.jetbrains.kotlin.android")
        apply("cosmobile.android.lint")
      }

      extensions.configure<LibraryExtension> {
        configureKotlinAndroid(this)
        defaultConfig.targetSdk = 34
        defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testOptions.animationsDisabled = true
        configureFlavors(this)
        configureGradleManagedDevices(this)
        // The resource prefix is derived from the module name,
        // so resources inside ":core:module1" must be prefixed with "core_module1_"
        resourcePrefix = path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_").lowercase() + "_"
      }
      extensions.configure<LibraryAndroidComponentsExtension> {
        configurePrintApksTask(this)
        disableUnnecessaryAndroidTests(target)
      }
      dependencies {
        add("androidTestImplementation", kotlin("test"))
        add("testImplementation", kotlin("test"))

        add("implementation", libs.findLibrary("androidx.tracing.ktx").get())
      }
    }
  }
}
