package it.paolopasianot.cosmobile.presentation

import it.paolopasianot.cosmobile.model.UserData

sealed interface MainState {
  data object Loading : MainState
  data class Success(val userData: UserData) : MainState
}
