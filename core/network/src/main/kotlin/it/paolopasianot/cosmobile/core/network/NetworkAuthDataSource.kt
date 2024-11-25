package it.paolopasianot.cosmobile.core.network

import it.paolopasianot.cosmobile.core.network.dto.response.auht.LoginResponseDTO


/**
 * Authentication network gateway
 */
interface NetworkAuthDataSource {

  suspend fun login(username: String, password: String): Result<LoginResponseDTO>

}
