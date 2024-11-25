package it.paolopasianot.cosmobile.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import it.paolopasianot.cosmobile.core.destination.Destination
import it.paolopasianot.cosmobile.core.destination.navigation.NavigationAction
import it.paolopasianot.cosmobile.core.destination.navigation.Navigator
import it.paolopasianot.cosmobile.core.destination.navigation.ObserveNavigation
import it.paolopasianot.cosmobile.feature.navigation.destination.dashboard.dashboardScreen
import it.paolopasianot.cosmobile.feature.navigation.destination.auth.loginScreen
import it.paolopasianot.cosmobile.feature.navigation.util.NavigationTrackingSideEffect
import org.koin.compose.koinInject

@Composable
fun CosmobileNavigation(
  modifier: Modifier = Modifier,
) {

  val navController = rememberNavController()
  val navigator = koinInject<Navigator>()

  NavigationTrackingSideEffect(navController)

  ObserveNavigation(
    flow = navigator.navigationActions,
  ) { action ->
    when (action) {
      is NavigationAction.Navigate -> navController.navigate(action.destination){
        action.navOptions(this)
      }
      NavigationAction.NavigateUp -> navController.popBackStack()
    }
  }


  NavHost(
    navController = navController,
    startDestination = navigator.startDestination,
  ) {
    navigation<Destination.DashboardGraph>(
        startDestination = Destination.Dashboard,
    ) {
      dashboardScreen(modifier = modifier)
    }

    navigation<Destination.AuthGraph>(
        startDestination = Destination.Login,
    ) {
      loginScreen(modifier = modifier)
    }
  }
}
