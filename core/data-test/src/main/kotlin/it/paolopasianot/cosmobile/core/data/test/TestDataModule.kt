package it.paolopasianot.cosmobile.core.data.test

import it.paolopasianot.cosmobile.core.data.util.NetworkMonitor
import it.paolopasianot.cosmobile.core.data.util.TimeZoneMonitor
import org.koin.dsl.module

val testDataModule = module {
    single<NetworkMonitor> { AlwaysOnlineNetworkMonitor() }
    single<TimeZoneMonitor> { get<DefaultZoneIdTimeZoneMonitor>() }
}
