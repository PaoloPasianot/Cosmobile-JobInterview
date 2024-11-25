import com.android.builder.core.apiVersionFromString
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `java-library`
  kotlin("jvm")
  alias(libs.plugins.cosmobile.android.lint)
}

java {
  // Up to Java 11 APIs are available through desugaring
  // https://developer.android.com/studio/write/java11-minimal-support-table
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile>().configureEach {
  compilerOptions{
    jvmTarget.set(JvmTarget.JVM_11)
    apiVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0)
  }
}

dependencies {
  compileOnly(libs.kotlin.stdlib)
  compileOnly(libs.lint.api)
  testImplementation(libs.lint.checks)
  testImplementation(libs.lint.tests)
  testImplementation(kotlin("test"))
}
