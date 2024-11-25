package it.paolopasianot.cosmobile.core.ui.utility

import androidx.compose.ui.graphics.Color

fun String.toColor() = Color(android.graphics.Color.parseColor(this))
