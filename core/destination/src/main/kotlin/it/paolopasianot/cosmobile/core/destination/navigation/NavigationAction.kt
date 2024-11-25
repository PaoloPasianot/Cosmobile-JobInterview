package it.paolopasianot.cosmobile.core.destination.navigation

import androidx.navigation.NavOptionsBuilder
import it.paolopasianot.cosmobile.core.destination.Destination

sealed interface NavigationAction {

  data class Navigate(
    val destination: Destination,
    val navOptions: NavOptionsBuilder.() -> Unit = {},
  ): NavigationAction

  data object NavigateUp : NavigationAction
}
