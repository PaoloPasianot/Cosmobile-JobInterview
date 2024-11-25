package it.paolopasianot.cosmobile.core

import kotlinx.serialization.json.Json
import org.koin.dsl.module

val commonModule = module {
  single<Json> {
    Json {
      ignoreUnknownKeys = true
      prettyPrint = true
      allowStructuredMapKeys = true
    }
  }
}
