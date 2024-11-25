package it.paolopasianot.cosmobile.feature.dashboard.di

import org.koin.dsl.module
import it.paolopasianot.cosmobile.feature.dashboard.DashboardViewModel
import org.koin.core.module.dsl.viewModel

val dashboardModule = module {

  viewModel { params ->
    DashboardViewModel(
      params.get(),
    )
  }
}
