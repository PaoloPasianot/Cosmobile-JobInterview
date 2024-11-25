package it.paolopasianot.cosmobile.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

interface UnidirectionalViewModel<STATE, EVENT, EFFECT> {
  val state: StateFlow<STATE>
  val effect: SharedFlow<EFFECT>
  fun event(event: EVENT)
}

@Composable
inline fun <reified STATE, EVENT, EFFECT> use(
    viewModel: UnidirectionalViewModel<STATE, EVENT, EFFECT>,
): StateDispatchEffect<STATE, EVENT, EFFECT> {
  val state by viewModel.state.collectAsStateWithLifecycle()

  val dispatch: (EVENT) -> Unit = { event ->
    viewModel.event(event)
  }

  return StateDispatchEffect(
    state = state,
    effectFlow = viewModel.effect,
    dispatch = dispatch,
  )
}

@Composable
fun <T> SharedFlow<T>.CollectInLaunchedEffect(function: suspend (value: T) -> Unit) {
  val sharedFlow = this
  LaunchedEffect(key1 = sharedFlow) {
    sharedFlow.collectLatest(function)
  }
}

data class StateDispatchEffect<STATE, EVENT, EFFECT>(
  val state: STATE,
  val dispatch: (EVENT) -> Unit,
  val effectFlow: SharedFlow<EFFECT>,
)
