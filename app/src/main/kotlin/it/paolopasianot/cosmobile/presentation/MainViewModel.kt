package it.paolopasianot.cosmobile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.paolopasianot.cosmobile.core.coroutines.WhileSubscribedOrRetained
import it.paolopasianot.cosmobile.core.data.repository.user.UserRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
  userRepository: UserRepository,
): ViewModel() {

  val uiState: StateFlow<MainState> = userRepository.userData.map {
    MainState.Success(it)
  }.stateIn(
    scope = viewModelScope,
    initialValue = MainState.Loading,
    started = WhileSubscribedOrRetained,
  )
}
