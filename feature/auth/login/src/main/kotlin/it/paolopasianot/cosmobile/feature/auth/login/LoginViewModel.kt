package it.paolopasianot.cosmobile.feature.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.paolopasianot.cosmobile.core.coroutines.WhileSubscribedOrRetained
import it.paolopasianot.cosmobile.core.data.repository.auth.AuthRepository
import it.paolopasianot.cosmobile.core.destination.Destination
import it.paolopasianot.cosmobile.core.destination.navigation.Navigator
import it.paolopasianot.cosmobile.core.ui.error.UIStringHolder
import it.paolopasianot.cosmobile.core.ui.error.toUIStringHolder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
  private val navigator: Navigator,
  private val authRepository: AuthRepository
) : ViewModel(), LoginContract {

  private val mutableState = MutableStateFlow(LoginContract.State())
  override val state: StateFlow<LoginContract.State> = mutableState
    .stateIn(
      scope = viewModelScope,
      initialValue = LoginContract.State(),
      started = WhileSubscribedOrRetained,
    )

  private val effectFlow = MutableSharedFlow<LoginContract.Effect>()
  override val effect: SharedFlow<LoginContract.Effect> = effectFlow.asSharedFlow()

  override fun event(event: LoginContract.Event) {
    when (event) {
      is LoginContract.Event.OnValueChange -> this.onValueChange(
          field = event.field,
      )

      LoginContract.Event.Submit -> this.submit()
    }
  }

  /**
   * Modifica lo stato con i nuovi valori che sono stati inseriti dall'utente nella form
   */
  private fun onValueChange(
    field: LoginContract.Fields,
  ) {
    when (field) {
      is LoginContract.Fields.Password -> {
        mutableState.update {
          it.copy(
              password = it.password.changeValueAndValidate(field.value),
          )
        }
      }

      is LoginContract.Fields.User -> {
        mutableState.update {
          it.copy(
              user = it.user.changeValueAndValidate(field.value),
          )
        }
      }
    }
  }

  /**
   * Effettua il login dell'utente controllando prima che tutti i dati siano validi
   */
  private fun submit() {
    if (!state.value.user.isValid() && !state.value.password.isValid()) {
      sendErrorMessage(UIStringHolder.R(res = R.string.feature_auth_login_field_generic_error))
      viewModelScope.launch {
        effectFlow.emit(LoginContract.Effect.FocusErrorMessage)
      }
      return
    }

    mutableState.update { it.copy(loading = true, errorMessage = null) }

    viewModelScope.launch {
      val result = authRepository.login(
        username = state.value.user.text,
        password = state.value.password.text,
      )

      result.onFailure { error ->
        mutableState.update { state ->
          state.copy(
            loading = false,
            errorMessage = error.toUIStringHolder()
          )
        }
      }

      result.onSuccess {
        navigator.navigate(Destination.DashboardGraph)
      }
    }
  }

  /**
   * Imposta un messaggio d'errore
   */
  private fun sendErrorMessage(message: UIStringHolder) {
    mutableState.update {
      it.copy(
        errorMessage = message,
      )
    }
  }
}
