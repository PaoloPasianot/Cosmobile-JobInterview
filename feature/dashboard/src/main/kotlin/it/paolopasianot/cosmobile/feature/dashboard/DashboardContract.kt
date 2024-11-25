package it.paolopasianot.cosmobile.feature.dashboard

import androidx.compose.runtime.Immutable
import it.paolopasianot.cosmobile.core.ui.UnidirectionalViewModel

interface DashboardContract :
  UnidirectionalViewModel<DashboardContract.State, DashboardContract.Event, DashboardContract.Effect> {

  @Immutable
  data object State

  sealed interface Event {


  }

  sealed interface Effect {


  }


}

