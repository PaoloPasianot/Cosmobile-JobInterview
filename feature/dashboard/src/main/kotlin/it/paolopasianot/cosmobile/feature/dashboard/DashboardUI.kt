package it.paolopasianot.cosmobile.feature.dashboard

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import it.paolopasianot.cosmobile.core.designsystem.theme.AppTheme
import it.paolopasianot.cosmobile.core.designsystem.theme.Typography
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlin.text.compareTo


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
      fun getSizeType(size: String): SizeType {
        return when {
          size.matches(Regex("\\d+")) -> SizeType.NUMERIC
          size.matches(Regex("[XSML]+")) -> SizeType.ALPHA
          size.startsWith("IT") -> SizeType.ITALIAN
          size.startsWith("FR") -> SizeType.FRENCH
          size.startsWith("UK") -> SizeType.UK
          else -> SizeType.ALPHA // Default to alpha for unknown types
        }
      }

      fun compareSizesWithinType(size1: String, size2: String, type: SizeType): Int {
        return when (type) {
          SizeType.NUMERIC -> size1.toInt().compareTo(size2.toInt())
          SizeType.ALPHA -> size1.compareTo(size2) // Natural order for alpha sizes
          SizeType.ITALIAN, SizeType.FRENCH, SizeType.UK -> {
            val numericSize1 = size1.substringAfter(" ").toIntOrNull() ?: 0
            val numericSize2 = size2.substringAfter(" ").toIntOrNull() ?: 0
            numericSize1.compareTo(numericSize2)
          }
        }
      }

      val sizeComparator = Comparator<String> { size1, size2 ->
        val sizeType1 = getSizeType(size1)
        val sizeType2 = getSizeType(size2)

        if (sizeType1 != sizeType2) {
          sizeType1.compareTo(sizeType2)
        } else {
          compareSizesWithinType(size1, size2, sizeType1)
        }
      }
      originalList.sortedWith(sizeComparator)
    }
  }

 LazyColumn(
   modifier = modifier
     .padding(24.dp),
   state = lazyState
 ) {
   stickyHeader {
     Text("Un Ordered", style = Typography.displayMedium)
   }
   items(originalList){
     Text(it)
   }
   stickyHeader {
     Text("Ordered", style = Typography.displayMedium)
   }
   items(sortedList){
     Text(it)
   }
 }
}



private enum class SizeType {
  NUMERIC,
  ALPHA,
  ITALIAN,
  FRENCH,
  UK
}

@Preview
@Composable
private fun DashboardPreview() {
  AppTheme {
    DashboardUI(
      modifier = Modifier.fillMaxSize(),
      state = DashboardContract.State,
      event = {},
      effects = MutableSharedFlow(),
    )
  }
}
