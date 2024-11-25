package it.paolopasianot.cosmobile.feature.navigation.destination.dashboard

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import it.paolopasianot.cosmobile.core.destination.Destination
import it.paolopasianot.cosmobile.core.ui.use
import it.paolopasianot.cosmobile.feature.dashboard.DashboardUI
import it.paolopasianot.cosmobile.feature.dashboard.DashboardViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * Show che dashboard
 */
internal fun NavGraphBuilder.dashboardScreen(
  modifier: Modifier = Modifier
){
  composable<Destination.Dashboard>{
    val  viewModel = koinViewModel<DashboardViewModel>()

    val (state, event, effect) = use(viewModel = viewModel)

    DashboardUI(
      modifier = modifier,
      state = state,
      event = event,
      effects = effect,
      viewModel = viewModel
    )
  }
}
