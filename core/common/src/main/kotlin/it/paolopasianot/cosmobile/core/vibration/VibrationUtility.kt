package it.paolopasianot.cosmobile.core.vibration

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.core.content.getSystemService

/**
 * Esegue una vibrazione
 *
 * @param milliseconds durata della vibrazione
 */
fun Context.vibrate(milliseconds: Long = 500){
  val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
    getSystemService<VibratorManager>()?.defaultVibrator
  } else {
    @Suppress("DEPRECATION")
    getSystemService(VIBRATOR_SERVICE) as Vibrator
  }

  if (vibrator != null && vibrator.hasVibrator()) {
    val vibrationEffect = VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE)
    vibrator.vibrate(vibrationEffect)
  }
}
