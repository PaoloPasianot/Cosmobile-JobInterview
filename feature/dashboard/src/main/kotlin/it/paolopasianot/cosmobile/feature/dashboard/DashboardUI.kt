package it.paolopasianot.cosmobile.feature.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.paolopasianot.cosmobile.core.designsystem.theme.AppTheme
import it.paolopasianot.cosmobile.core.designsystem.theme.Colors
import it.paolopasianot.cosmobile.core.designsystem.theme.Typography
import it.paolopasianot.cosmobile.feature.dashboard.utility.OrderSizes
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

/**
 * View della dashboard
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DashboardUI(
  modifier: Modifier = Modifier,
  state: DashboardContract.State,
  event: (DashboardContract.Event) -> Unit,
  effects: SharedFlow<DashboardContract.Effect>,
) {

  val lazyState = rememberLazyListState()

  val originalList = remember {
    mutableStateListOf(
      "S",
      "43",
      "XL",
      "40",
      "44",
      "M",
      "12",
      "IT 35",
      "IT 43",
      "FR 12",
      "UK 50",
      "XXL",
      "IT 50",
    )
  }

  val sortedList by remember {
    derivedStateOf<List<String>> {
      OrderSizes.order(originalList)
    }
  }

  LazyColumn(
    modifier = modifier
      .padding(24.dp),
    state = lazyState,
  ) {
    stickyHeader {
      Text("Un Ordered", style = Typography.displayMedium)
    }
    items(originalList) {
      Text(it)
    }
    stickyHeader {
      Text("Ordered", style = Typography.displayMedium)
    }
    items(sortedList) {
      Text(it)
    }
  }
}


@Preview
@Composable
private fun DashboardPreview() {
  AppTheme {
    DashboardUI(
      modifier = Modifier
        .fillMaxSize()
        .background(Colors.background),
      state = DashboardContract.State,
      event = {},
      effects = MutableSharedFlow(),
    )
  }
}
