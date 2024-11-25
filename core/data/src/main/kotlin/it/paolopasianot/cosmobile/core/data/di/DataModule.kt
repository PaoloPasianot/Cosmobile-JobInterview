package it.paolopasianot.cosmobile.core.data.di

import it.paolopasianot.cosmobile.core.data.repository.auth.AuthRepository
import it.paolopasianot.cosmobile.core.data.repository.auth.OnlineAuthRepository
import it.paolopasianot.cosmobile.core.data.repository.user.FakeUserRepository
import it.paolopasianot.cosmobile.core.data.repository.user.UserRepository
import it.paolopasianot.cosmobile.core.network.di.networkModule
import org.koin.dsl.module


val dataModule = module {

  includes(networkModule)

  single<AuthRepository> { OnlineAuthRepository(get()) }

  single<UserRepository> { FakeUserRepository() }
}
