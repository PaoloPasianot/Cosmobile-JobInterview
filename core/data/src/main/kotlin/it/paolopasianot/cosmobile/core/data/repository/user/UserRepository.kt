package it.paolopasianot.cosmobile.core.data.repository.user

import it.paolopasianot.cosmobile.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserRepository {

  /**
   * Stream of [UserData]
   */
  val userData: Flow<UserData>


}
