package it.paolopasianot.cosmobile.core.destination.navigation

import androidx.navigation.NavOptionsBuilder
import it.paolopasianot.cosmobile.core.destination.Destination
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class DefaultNavigator(
  override val startDestination: Destination
) : Navigator {

  private val _navigationActions = Channel<NavigationAction>()
  override val navigationActions: Flow<NavigationAction> = _navigationActions.receiveAsFlow()

  override suspend fun navigate(
    destination: Destination,
    navOptions: NavOptionsBuilder.() -> Unit,
  ) {
    _navigationActions.send(
      NavigationAction.Navigate(
        destination = destination,
        navOptions = navOptions
      )
    )
  }

  override suspend fun navigateUp() {
    _navigationActions.send(
      NavigationAction.NavigateUp
    )
  }
}
