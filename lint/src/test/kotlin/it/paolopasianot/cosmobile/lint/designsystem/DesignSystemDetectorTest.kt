package it.paolopasianot.cosmobile.lint.designsystem

import com.android.tools.lint.checks.infrastructure.TestFile
import com.android.tools.lint.checks.infrastructure.TestFiles.kotlin
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import it.paolopasianot.cosmobile.lint.designsystem.DesignSystemDetector.Companion.ISSUE
import it.paolopasianot.cosmobile.lint.designsystem.DesignSystemDetector.Companion.METHOD_NAMES
import it.paolopasianot.cosmobile.lint.designsystem.DesignSystemDetector.Companion.RECEIVER_NAMES
import org.junit.Test

class DesignSystemDetectorTest {

  @Test
  fun `detect replacements of Composable`() {
    lint()
      .issues(ISSUE)
      .allowMissingSdk()
      .files(
        COMPOSABLE_STUB,
        STUBS,
        @Suppress("LintImplTrimIndent")
        kotlin(
          """
                    |import androidx.compose.runtime.Composable
                    |
                    |@Composable
                    |fun App() {
                    ${METHOD_NAMES.keys.joinToString("\n") { "|    $it()" }}
                    |}
                    """.trimMargin(),
        ).indented(),
      )
      .run()
      .expect(
        """
                src/test.kt:5: Error: Using MaterialTheme instead of CosmobileTheme [DesignSystem]
                    MaterialTheme()
                    ~~~~~~~~~~~~~~~
                1 errors, 0 warnings
                """.trimIndent(),
      )
  }

  @Test
  fun `detect replacements of Receiver`() {
    lint()
      .issues(ISSUE)
      .allowMissingSdk()
      .files(
        COMPOSABLE_STUB,
        STUBS,
        @Suppress("LintImplTrimIndent")
        kotlin(
          """
                    |fun main() {
                    ${RECEIVER_NAMES.keys.joinToString("\n") { "|    $it.toString()" }}
                    |}
                    """.trimMargin(),
        ).indented(),
      )
      .run()
      .expect(
        """
                src/test.kt:2: Error: Using Icons instead of CosmobileIcons [DesignSystem]
                    Icons.toString()
                    ~~~~~~~~~~~~~~~~
                1 errors, 0 warnings
                """.trimIndent(),
      )
  }

  private companion object {

    private val COMPOSABLE_STUB: TestFile = kotlin(
      """
            package androidx.compose.runtime
            annotation class Composable
            """.trimIndent(),
    ).indented()

    private val STUBS: TestFile = kotlin(
      """
            |import androidx.compose.runtime.Composable
            |
            ${METHOD_NAMES.keys.joinToString("\n") { "|@Composable fun $it() = {}" }}
            ${RECEIVER_NAMES.keys.joinToString("\n") { "|object $it" }}
            |
            """.trimMargin(),
    ).indented()
  }
}
