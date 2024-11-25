package it.paolopasianot.cosmobile.core.destination.navigation

import androidx.navigation.NavOptionsBuilder
import it.paolopasianot.cosmobile.core.destination.Destination
import kotlinx.coroutines.flow.Flow

interface Navigator {

  val startDestination: Destination
  val navigationActions: Flow<NavigationAction>

  suspend fun navigate(
    destination: Destination,
    navOptions: NavOptionsBuilder.() -> Unit = {}
  )

  suspend fun navigateUp()
}
