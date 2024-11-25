package it.paolopasianot.cosmobile.core.network.demo

import it.paolopasianot.cosmobile.core.error.LocalizedException
import it.paolopasianot.cosmobile.core.network.NetworkAuthDataSource
import it.paolopasianot.cosmobile.core.network.R
import it.paolopasianot.cosmobile.core.network.dto.response.auht.LoginResponseDTO


internal class NetworkAuthDemoSource : NetworkAuthDataSource {

  override suspend fun login(username: String, password: String): Result<LoginResponseDTO> {
    return if (username == "prova" && password == "prova") {
      Result.success(
        LoginResponseDTO(
          token = "token di test",
        ),
      )
    } else {
      Result.failure(LocalizedException(R.string.core_network_login_invalid))
    }
  }
}
