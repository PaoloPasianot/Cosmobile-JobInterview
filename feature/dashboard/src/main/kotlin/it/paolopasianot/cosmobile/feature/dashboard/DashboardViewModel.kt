package it.paolopasianot.cosmobile.feature.dashboard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigator
import it.paolopasianot.cosmobile.core.coroutines.WhileSubscribedOrRetained
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class DashboardViewModel(
  private val savedStateHandle: SavedStateHandle,
) : ViewModel(), DashboardContract {

  private val mutableState = MutableStateFlow(DashboardContract.State)
  override val state: StateFlow<DashboardContract.State> = mutableState
    .stateIn(
      scope = viewModelScope,
      initialValue = DashboardContract.State,
      started = WhileSubscribedOrRetained,
    )

  private val effectFlow = MutableSharedFlow<DashboardContract.Effect>()
  override val effect: SharedFlow<DashboardContract.Effect> = effectFlow.asSharedFlow()


  override fun event(event: DashboardContract.Event) {
    //TODO()
  }

}
