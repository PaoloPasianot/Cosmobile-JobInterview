package it.paolopasianot.cosmobile.core.ui

import androidx.compose.runtime.Immutable
import it.paolopasianot.cosmobile.core.ui.error.UIStringHolder


/**
 * Abilita una gestione completa di un "Field" in una form
 *
 * @property text valore reale contenuto nel campo
 * @property error se valorizzato (diverso da null) identifica l'errore commesso dall'utente in fase di compilazione
 * @property isValidField funzione di validazione del campo, viene richiamata ogni qualvolta c'è bisogno di validare il contenuto del campo
 */
@Immutable
abstract class TextFieldValue<Value> {

  abstract val text: Value
  abstract val error: UIStringHolder?
  abstract val isValidField: (Value) -> UIStringHolder?

  /**
   * Definisce se il valore inserito è considerabile vuoto o meno
   *
   * @return se vero il valore inserito è cosiderabile vuoto, false altrimenti
   */
  abstract fun isEmpty(): Boolean

  /**
   * Modifica il valore salvato e controlla che il valore inserito si valido
   *
   * @param value nuovo valore da salvare
   * @return copia dell'oggetto di partenza con il nuovo valore ed eventualmente salvato anche l'errore
   */
  abstract fun changeValueAndValidate(value: Value): TextFieldValue<Value>

  /**
   * Controlla il valore inserito e determina se è valido o meno
   *
   * @return se vero il valore inserito è valido, false altrimenti
   */
  fun isValid(): Boolean {
    return isValidField(text) == null
  }

  fun isError(): Boolean {
    return error != null
  }
}

@Immutable
data class StringTextFieldValue(
  override val text: String,
  override val error: UIStringHolder?,
  override val isValidField: (String) -> UIStringHolder?,
) : TextFieldValue<String>() {

  companion object {

    const val BLANK: String = ""

    fun empty(isValidField: (String) -> UIStringHolder?) = StringTextFieldValue(
        text = "",
        error = null,
        isValidField = isValidField,
    )
  }

  override fun isEmpty(): Boolean {
    return this.text.isBlank() || this.text.isEmpty()
  }

  override fun changeValueAndValidate(value: String): StringTextFieldValue {
    return this.copy(
      text = value,
      error = isValidField(value)
    )
  }

}


