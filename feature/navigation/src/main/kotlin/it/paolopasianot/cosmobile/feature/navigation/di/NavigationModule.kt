package it.paolopasianot.cosmobile.feature.navigation.di

import it.paolopasianot.cosmobile.core.destination.Destination
import it.paolopasianot.cosmobile.core.destination.navigation.DefaultNavigator
import it.paolopasianot.cosmobile.core.destination.navigation.Navigator
import it.paolopasianot.cosmobile.core.di.baseConfigModule
import it.paolopasianot.cosmobile.feature.auth.login.di.loginModule
import it.paolopasianot.cosmobile.feature.dashboard.di.dashboardModule
import org.koin.dsl.module

val navigationModule = module {

  includes(baseConfigModule)

  single<Navigator> {
    DefaultNavigator(
      startDestination = Destination.AuthGraph,
    )
  }

  //SCREENS
  includes(
    loginModule,
    dashboardModule
  )

}
