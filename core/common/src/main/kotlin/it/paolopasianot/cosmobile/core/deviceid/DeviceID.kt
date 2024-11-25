package it.paolopasianot.cosmobile.core.deviceid

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

/**
 * Fornisce un identificatore univoco per il dispositivo.
 * IMPORTANTE! potrebbe essere necessario modificarlo con adv_ids, da verificare
 *
 * @param context Context dell'applicazione.
 * @return L'identificatore univoco del dispositivo.
 */
@SuppressLint("HardwareIds")
fun getDeviceId(context: Context): DeviceId {
  return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
}

typealias DeviceId = String
