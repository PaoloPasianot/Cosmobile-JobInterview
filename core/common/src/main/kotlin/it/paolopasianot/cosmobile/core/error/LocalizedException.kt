package it.paolopasianot.cosmobile.core.error

/**
 *
 */
class LocalizedException(
  val resourceMessage: Int,
): Throwable(){

  override val message: String?
    get() = null

}
