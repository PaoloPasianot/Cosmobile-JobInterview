package it.paolopasianot.cosmobile.core.destination

import kotlinx.serialization.Serializable

sealed interface Destination {

  //Graph - Dashboard
  @Serializable
  data object DashboardGraph : Destination

  //Graph - Dashboard - Destinations
  @Serializable
  data object Dashboard : Destination


  //Graph - Auth
  @Serializable
  data object AuthGraph : Destination

  //Graph - Auth - Destinations
  @Serializable
  data object Login : Destination


}
