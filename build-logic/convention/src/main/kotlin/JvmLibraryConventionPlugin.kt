import it.paolopasianot.cosmobile.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project

class JvmLibraryConventionPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("org.jetbrains.kotlin.jvm")
        apply("cosmobile.android.lint")
      }
      configureKotlinJvm()
    }
  }
}
