import com.android.build.gradle.LibraryExtension
import it.paolopasianot.cosmobile.configureGradleManagedDevices
import it.paolopasianot.cosmobile.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      pluginManager.apply {
        apply("cosmobile.android.library")
        apply("org.jetbrains.kotlin.plugin.serialization")
      }
      extensions.configure<LibraryExtension> {
        testOptions.animationsDisabled = true
        configureGradleManagedDevices(this)
      }

      dependencies {
        add("implementation", project(":core:ui"))
        add("implementation", project(":core:designsystem"))

        add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
        add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
        add("implementation", libs.findLibrary("androidx.navigation.compose").get())
        add("implementation", libs.findLibrary("androidx.tracing.ktx").get())
        add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
        add("implementation", libs.findLibrary("koin.android").get())
        add("implementation", libs.findLibrary("koin.android.compose").get())

        add("testImplementation", libs.findLibrary("androidx.navigation.testing").get())
        add("androidTestImplementation", libs.findLibrary("androidx.lifecycle.runtimeTesting").get())
      }
    }
  }
}
