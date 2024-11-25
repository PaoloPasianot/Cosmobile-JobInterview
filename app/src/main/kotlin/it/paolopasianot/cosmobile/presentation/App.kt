package it.paolopasianot.cosmobile.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import it.paolopasianot.cosmobile.feature.navigation.CosmobileNavigation

@Composable
fun AppUI(){
  CosmobileNavigation(modifier = Modifier.fillMaxSize())
}
