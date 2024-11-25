package it.paolopasianot.cosmobile.feature.navigation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import it.paolopasianot.cosmobile.core.ui.TrackDisposableJank

/**
 * Stores information about navigation events to be used with JankStats
 */
@Composable
internal fun NavigationTrackingSideEffect(navController: NavHostController) {
  TrackDisposableJank(navController) { metricsHolder ->
    val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
      metricsHolder.state?.putState("Navigation", destination.route.toString())
    }

    navController.addOnDestinationChangedListener(listener)

    onDispose {
      navController.removeOnDestinationChangedListener(listener)
    }
  }
}
