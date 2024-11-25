package it.paolopasianot.cosmobile.feature.dashboard

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.SharedFlow


/**
 * View della dashboard
 */
@Composable
fun DashboardUI(
  modifier: Modifier = Modifier,
  viewModel: DashboardViewModel,
  state: DashboardContract.State,
  event: (DashboardContract.Event) -> Unit,
  effects: SharedFlow<DashboardContract.Effect>,
) {

  Box(modifier = modifier){

  }
}

