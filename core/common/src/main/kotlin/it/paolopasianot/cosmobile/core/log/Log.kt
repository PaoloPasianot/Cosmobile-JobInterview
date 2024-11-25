package it.paolopasianot.cosmobile.core.log

import android.util.Log

/**
 * Log object on android standard error log
 * @param tag search identifier in Logcat
 */
fun Any?.logE(tag: String = "Cosmobile") {
  Log.e(tag, this.toString())
}

/**
 * Log object on android standard debug log
 * @param tag search identifier in Logcat
 */
fun Any?.logD(tag: String = "Cosmobile") {
  Log.d(tag, this.toString())
}

/**
 * Log object on android standard verbose log
 * @param tag search identifier in Logcat
 */
fun Any?.logV(tag: String = "Cosmobile") {
  Log.v(tag, this.toString())
}

/**
 * Log object on android standard info log
 * @param tag search identifier in Logcat
 */
fun Any?.logI(tag: String = "Cosmobile") {
  Log.i(tag, this.toString())
}

/**
 * Log object on android standard warning log
 * @param tag search identifier in Logcat
 */
fun Any?.logW(tag: String = "Cosmobile") {
  Log.w(tag, this.toString())
}

/**
 * Log object on android standard what a fuck 🤷🏽‍ log
 * @param tag search identifier in Logcat
 */
fun Any?.log(tag: String = "Cosmobile") {
  //if(BuildConfig.DEBUG) {
    Log.e(tag, this.toString())
  //}
}
