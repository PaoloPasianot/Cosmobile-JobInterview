package it.paolopasianot.cosmobile.core.network.ktor

import io.ktor.http.HttpStatusCode

/**
 * Identifica che una chiamata http ha generato un errore diverso da 200-299
 *
 * @property httpStatusCode stato della chiamata http
 */
class NetworkRequestException(val httpStatusCode: HttpStatusCode): Throwable(
  message = "${httpStatusCode.value} - ${httpStatusCode.description}"
)
