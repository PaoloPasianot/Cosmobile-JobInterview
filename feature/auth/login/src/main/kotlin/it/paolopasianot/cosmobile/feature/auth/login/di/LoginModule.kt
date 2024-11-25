package it.paolopasianot.cosmobile.feature.auth.login.di

import it.paolopasianot.cosmobile.core.commonModule
import it.paolopasianot.cosmobile.core.data.di.dataModule
import it.paolopasianot.cosmobile.feature.auth.login.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {

  includes(
    dataModule,
    commonModule,
  )

  viewModelOf(::LoginViewModel)

}
