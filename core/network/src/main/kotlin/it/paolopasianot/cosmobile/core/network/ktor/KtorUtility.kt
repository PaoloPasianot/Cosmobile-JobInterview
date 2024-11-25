package it.paolopasianot.cosmobile.core.network.ktor

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import it.paolopasianot.cosmobile.core.log.log
import kotlin.jvm.Throws

/**
 * Risolve una risposta http controllando i codici errore generati
 *
 * @return [Result] oggetto di ritorno se codice d'errore è compreso tra 200 e 299
 * @throws NetworkRequestException lo stato della chiamata non è 2xx
 */
suspend inline fun <reified T> HttpResponse.resolve(): T {
  bodyAsText().log("api response body")
  return if (status.value in 200..299) {
    body<T>()
  }else{
    throw NetworkRequestException(status)
  }
}
