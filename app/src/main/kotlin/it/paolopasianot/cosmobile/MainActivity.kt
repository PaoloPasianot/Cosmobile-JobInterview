package it.paolopasianot.cosmobile

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.metrics.performance.JankStats
import it.paolopasianot.cosmobile.presentation.MainState
import it.paolopasianot.cosmobile.presentation.MainViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import it.paolopasianot.cosmobile.core.designsystem.theme.AppTheme
import it.paolopasianot.cosmobile.presentation.AppUI

private const val TAG = "MainActivity"

class MainActivity: ComponentActivity() {

  /**
   * Lazily inject [JankStats], which is used to track jank throughout the app.
   */
  private val lazyStats: JankStats by lazy {
      JankStats.createAndTrack(window) { frameData ->
          // Make sure to only log janky frames.
          if (frameData.isJank) {
              // We're currently logging this but would better report it to a backend.
              Log.v("Cosmobile Jank", frameData.toString())
          }
      }
  }

  private val viewModel: MainViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    val splashScreen = installSplashScreen()
    super.onCreate(savedInstanceState)

    var mainUiState: MainState by mutableStateOf(MainState.Loading)

    // Update the uiState
    lifecycleScope.launch {
      lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.uiState
          .onEach { mainUiState = it }
          .collect()
      }
    }

    // Keep the splash screen on-screen until the UI state is loaded. This condition is
    // evaluated each time the app needs to be redrawn so it should be fast to avoid blocking
    // the UI.
    splashScreen.setKeepOnScreenCondition {
      when (mainUiState) {
        MainState.Loading -> true
        is MainState.Success -> false
      }
    }

    // Turn off the decor fitting system windows, which allows us to handle insets,
    // including IME animations, and go edge-to-edge
    // This also sets up the initial system bar style based on the platform theme
    enableEdgeToEdge()

    setContent {
      val darkTheme = isSystemInDarkTheme()

      // Update the edge to edge configuration to match the theme
      // This is the same parameters as the default enableEdgeToEdge call, but we manually
      // resolve whether or not to show dark theme using uiState, since it can be different
      // than the configuration's dark theme value based on the user preference.
      DisposableEffect(darkTheme) {
        enableEdgeToEdge(
          statusBarStyle = SystemBarStyle.auto(
            android.graphics.Color.TRANSPARENT,
            android.graphics.Color.TRANSPARENT,
          ) { darkTheme },
          navigationBarStyle = SystemBarStyle.auto(
            lightScrim,
            darkScrim,
          ) { darkTheme },
        )
        onDispose {}
      }

      AppTheme(
        darkTheme = darkTheme,
      ) {
        AppUI()
      }
    }
  }

}

/**
 * The default light scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=35-38;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val lightScrim = android.graphics.Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * The default dark scrim, as defined by androidx and the platform:
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:activity/activity/src/main/java/androidx/activity/EdgeToEdge.kt;l=40-44;drc=27e7d52e8604a080133e8b842db10c89b4482598
 */
private val darkScrim = android.graphics.Color.argb(0x80, 0x1b, 0x1b, 0x1b)
