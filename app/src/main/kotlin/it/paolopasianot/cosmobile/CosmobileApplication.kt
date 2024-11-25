package it.paolopasianot.cosmobile

import android.app.Application
import it.paolopasianot.cosmobile.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup.onKoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import it.paolopasianot.cosmobile.util.ProfileVerifierLogger

/**
 * [Application] class for Cosmobile
 */
@OptIn(KoinExperimentalAPI::class)
class CosmobileApplication: Application() {

  init {
    onKoinStartup {
      androidContext(this@CosmobileApplication)
      androidLogger()
      androidFileProperties()
      modules(
        appModule
      )
    }
  }

  override fun onCreate() {
    super.onCreate()
    ProfileVerifierLogger()
  }
}
