package it.paolopasianot.cosmobile.core.data.repository.auth

interface AuthRepository {

  /**
   * Esegue il login con il codice d'invito che viene fornito al cliente
   *
   * @param username nome dell'utente
   * @param password password dell'utente
   * @return se [Result.success] true tutto ok, se false è stato impossibile capire il problema
   * se [Result.failure] fornisce il messaggio d'errore generato dalla chiamata al backend
   */
  suspend fun login(username: String, password: String): Result<Unit>
}
