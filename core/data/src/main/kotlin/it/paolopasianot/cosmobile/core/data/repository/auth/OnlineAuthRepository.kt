package it.paolopasianot.cosmobile.core.data.repository.auth

import it.paolopasianot.cosmobile.core.network.NetworkAuthDataSource

internal class OnlineAuthRepository(
  private val network: NetworkAuthDataSource,
) : AuthRepository {

  override suspend fun login(username: String, password: String): Result<Unit> {
    return try {
      network.login(username = username, password = password).map {
        //check token validation

        //save in user data store

      }
    }catch (e: Exception){
      e.printStackTrace()
      Result.failure(e)
    }
  }


}
