package it.paolopasianot.cosmobile.core.ui.error

import it.paolopasianot.cosmobile.core.error.LocalizedException
import it.paolopasianot.cosmobile.core.ui.R

fun Throwable.toUIStringHolder(): UIStringHolder {
  return when {
    message != null -> UIStringHolder.Str(
      value = this.message!!
    )
    this is LocalizedException -> UIStringHolder.R(
      res = this.resourceMessage
    )
    else -> UIStringHolder.R(
      res = R.string.core_ui_generic_error
    )
  }
}
