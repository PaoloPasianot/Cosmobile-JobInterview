package it.paolopasianot.cosmobile.core.di

import android.content.Context
import it.paolopasianot.cosmobile.core.deviceid.DeviceId
import it.paolopasianot.cosmobile.core.deviceid.getDeviceId
import org.koin.dsl.module

val baseConfigModule = module {

  single<DeviceId> {
    val context = get<Context>()
    getDeviceId(context)
  }

}
