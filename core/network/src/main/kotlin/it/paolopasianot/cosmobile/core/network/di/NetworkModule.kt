package it.paolopasianot.cosmobile.core.network.di

import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.cache.storage.FileStorage
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import it.paolopasianot.cosmobile.core.commonModule
import it.paolopasianot.cosmobile.core.network.NetworkAuthDataSource
import it.paolopasianot.cosmobile.core.network.demo.NetworkAuthDemoSource
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import java.io.File

val networkModule = module {

  includes(commonModule, coilModule)

  single<HttpClient> {
    val context = get<Context>()
    val serialization = get<Json>()
    HttpClient(Android) {

      install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.HEADERS
      }

      install(HttpCache){
        val cacheFile = File(context.cacheDir, "/http-cache").apply {
          mkdirs()
        }

        publicStorage(FileStorage(cacheFile))
      }

      install(ContentNegotiation) {
        json(serialization)
      }

      engine {
        connectTimeout = 100_000
        socketTimeout = 100_000
      }
    }
  }

  single<NetworkAuthDataSource> { NetworkAuthDemoSource() }


}

