package it.paolopasianot.cosmobile.core.network.di

import android.content.Context
import androidx.tracing.trace
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.util.DebugLogger
import it.paolopasianot.cosmobile.core.network.BuildConfig
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val coilModule = module {

  single<Call.Factory>{
    trace("OkHttpClient") {
      OkHttpClient.Builder()
        .addInterceptor(
          HttpLoggingInterceptor()
            .apply {
              if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
              }
            },
        )
        .build()
    }
  }

  /**
   * Since we're displaying SVGs in the app, Coil needs an ImageLoader which supports this
   * format. During Coil's initialization it will call `applicationContext.newImageLoader()` to
   * obtain an ImageLoader.
   *
   * @see <a href="https://github.com/coil-kt/coil/blob/main/coil-singleton/src/main/java/coil/Coil.kt">Coil</a>
   */
  single<ImageLoader> {
    trace("ImageLoader") {
      ImageLoader.Builder(get<Context>())
        .callFactory { get<Call.Factory>() }
        .components { add(SvgDecoder.Factory()) }
        // Assume most content images are versioned urls
        // but some problematic images are fetching each time
        .respectCacheHeaders(false)
        .apply {
          if (BuildConfig.DEBUG) {
            logger(DebugLogger())
          }
        }
        .build()
    }
  }
}
