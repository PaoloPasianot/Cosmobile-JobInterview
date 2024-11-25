package it.paolopasianot.cosmobile.feature.navigation.destination.auth

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import it.paolopasianot.cosmobile.core.destination.Destination
import it.paolopasianot.cosmobile.core.ui.use
import it.paolopasianot.cosmobile.feature.auth.login.LoginUI
import it.paolopasianot.cosmobile.feature.auth.login.LoginViewModel
import it.paolopasianot.cosmobile.feature.dashboard.DashboardUI
import it.paolopasianot.cosmobile.feature.dashboard.DashboardViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * Show login screen
 */
internal fun NavGraphBuilder.loginScreen(
  modifier: Modifier = Modifier
){
  composable<Destination.Login>{
    val viewModel = koinViewModel<LoginViewModel>()

    val (state, event, effect) = use(viewModel = viewModel)

    LoginUI(
      modifier = modifier,
      state = state,
      event = event,
      effects = effect
    )
  }
}
