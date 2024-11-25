package it.paolopasianot.cosmobile.di

import it.paolopasianot.cosmobile.feature.navigation.di.navigationModule
import it.paolopasianot.cosmobile.presentation.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

  includes(navigationModule)

  viewModelOf(::MainViewModel)
}
