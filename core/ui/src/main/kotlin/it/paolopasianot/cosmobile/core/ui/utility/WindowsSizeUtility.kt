package it.paolopasianot.cosmobile.core.ui.utility

import android.content.res.Configuration
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

/**
 * Checks if the window is in landscape mode.
 *
 * @return [Boolean.true] if the window is in landscape mode, [Boolean.false] otherwise.
 */
val isLandscape: Boolean
  @Composable
  get() {
    return LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
  }

// WIDTH -------------------------------------------------------------------------------------------

/**
 * Checks if the window is in compact width.
 *
 * @return [Boolean.true] if the window is in compact width, [Boolean.false] otherwise.
 */
val WindowAdaptiveInfo.isCompactWidth: Boolean
  get() {
    return windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT
  }

/**
 * Checks if the window is in medium width.
 *
 * @return [Boolean.true] if the window is in medium width, [Boolean.false] otherwise.
 */
val WindowAdaptiveInfo.isMediumWidth: Boolean
  get() {
    return windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM
  }

/**
 * Checks if the window is in expanded width.
 *
 * @return [Boolean.true] if the window is in expanded width, [Boolean.false] otherwise.
 */
val WindowAdaptiveInfo.isExpandedWidth: Boolean
  get() {
    return windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED
  }

// HEIGHT ------------------------------------------------------------------------------------------

/**
 * Checks if the window is in compact height.
 *
 * @return [Boolean.true] if the window is in compact height, [Boolean.false] otherwise.
 */
val WindowAdaptiveInfo.isCompactHeight: Boolean
  get() {
    return windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT
  }

/**
 * Checks if the window is in medium height.
 *
 * @return [Boolean.true] if the window is in medium height, [Boolean.false] otherwise.
 */
val WindowAdaptiveInfo.isMediumHeight: Boolean
  get() {
    return windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.MEDIUM
  }

/**
 * Checks if the window is in expanded height.
 *
 * @return [Boolean.true] if the window is in expanded height, [Boolean.false] otherwise.
 */
val WindowAdaptiveInfo.isExpandedHeight: Boolean
  get() {
    return windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.EXPANDED
  }
