package it.paolopasianot.cosmobile.core.ui.error

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.res.stringResource

/**
 * Permette di poter salvare in una variabile sia [String] raw che [StringRes]
 */
@Immutable
sealed class UIStringHolder {

  /**
   * Indipendentemente da come è stata salvata la stringa fornisce la sua versione raw
   *
   * @return raw string
   */
  @Composable
  abstract fun getMessage(): String

  /**
   * Permette di salvare una [StringRes]
   *
   * @property res string in formato risorsa
   */
  data class R(
    @StringRes private val res: Int
  ): UIStringHolder(){
    @Composable
    override fun getMessage(): String {
      return stringResource(id = this.res)
    }
  }

  /**
   * Permette di salvare una [String]
   *
   * @property value raw string
   */
  data class Str(
    private val value: String
  ): UIStringHolder(){
    @Composable
    override fun getMessage(): String {
      return this.value
    }
  }
}
