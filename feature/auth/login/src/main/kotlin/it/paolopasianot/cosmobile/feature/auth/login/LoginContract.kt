package it.paolopasianot.cosmobile.feature.auth.login

import androidx.compose.runtime.Immutable
import it.paolopasianot.cosmobile.core.ui.StringTextFieldValue
import it.paolopasianot.cosmobile.core.ui.error.UIStringHolder
import it.paolopasianot.cosmobile.core.ui.UnidirectionalViewModel

interface LoginContract :
  UnidirectionalViewModel<LoginContract.State, LoginContract.Event, LoginContract.Effect> {

  /**
   * @property errorMessage messaggio di errore generico
   * @property loading indica se vi sono fasi di caricamento
   * @property user email o username dell'utente
   * @property password password dell'utente
   */
  @Immutable
  data class State(
    val loading: Boolean = false,
    val errorMessage: UIStringHolder? = null,
    val user: StringTextFieldValue = StringTextFieldValue.empty{ newValue ->
      when{
        newValue.isEmpty() -> UIStringHolder.R(
          res = R.string.feature_auth_login_user_field_error_empty
        )
        else -> null
      }
    },
    val password: StringTextFieldValue = StringTextFieldValue.empty{ newValue ->
      when{
        newValue.isEmpty() -> UIStringHolder.R(
          res = R.string.feature_auth_login_password_field_error_empty
        )
        newValue.length < 5 -> UIStringHolder.R(
          res = R.string.feature_auth_login_password_field_error_length
        )
        else -> null
      }
    },
  )

  sealed interface Event {

    /**
     * Evento di modifica del valore di un campo
     *
     * @property field campo che ha subito un cambiamento
     */
    data class OnValueChange(
      val field: Fields,
    ) : Event

    /**
     * Evento di richiesta di login
     */
    data object Submit : Event

  }

  sealed interface Effect {

    /**
     * Richiede alla view di mettere il focus sul messaggio di errore
     */
    data object FocusErrorMessage: Effect
  }

  /**
   * Campi da compilare nella form
   */
  sealed interface Fields {

    data class Password(
      val value: String,
    ) : Fields

    data class User(
      val value: String,
    ) : Fields
  }

}
