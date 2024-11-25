package it.paolopasianot.cosmobile.core.data.repository.user

import it.paolopasianot.cosmobile.model.UserData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeUserRepository: UserRepository {

  override val userData: Flow<UserData>
    get() = flow {
      delay(1000)
      emit(UserData(sessionToken = null))
    }
}
