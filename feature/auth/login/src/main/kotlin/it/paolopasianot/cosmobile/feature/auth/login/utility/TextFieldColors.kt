package it.paolopasianot.cosmobile.feature.auth.login.utility

import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import it.paolopasianot.cosmobile.core.designsystem.theme.Colors

private const val placeholderAlpha = .7f

internal val LoginTextFieldColors: TextFieldColors
  @Composable
  get() = TextFieldDefaults.colors(
    //Container
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    errorContainerColor = Color.Transparent,
    disabledContainerColor = Color.Transparent,

    //Indicator
    focusedIndicatorColor = Colors.primary,
    unfocusedIndicatorColor = Colors.primary,
    errorIndicatorColor = Colors.primary,
    disabledIndicatorColor = Colors.primary,

    //Text
    focusedTextColor = Colors.onPrimaryContainer,
    unfocusedTextColor = Colors.onPrimaryContainer,
    errorTextColor = Colors.onPrimaryContainer,
    disabledTextColor = Colors.onPrimaryContainer,

    //Icons
    errorTrailingIconColor = Colors.onPrimaryContainer,
    focusedTrailingIconColor = Colors.onPrimaryContainer,
    unfocusedTrailingIconColor = Colors.onPrimaryContainer,
    disabledTrailingIconColor = Colors.onPrimaryContainer,
    errorLeadingIconColor = Colors.onPrimaryContainer,
    focusedLeadingIconColor = Colors.onPrimaryContainer,
    unfocusedLeadingIconColor = Colors.onPrimaryContainer,
    disabledLeadingIconColor = Colors.onPrimaryContainer,

    //Label
    unfocusedLabelColor = Colors.onPrimaryContainer,
    focusedLabelColor = Colors.onPrimaryContainer,
    errorLabelColor = Colors.onPrimaryContainer,
    disabledLabelColor = Colors.onPrimaryContainer,

    //Placeholder
    disabledPlaceholderColor = Colors.onPrimaryContainer.copy(alpha = placeholderAlpha),
    errorPlaceholderColor = Colors.onPrimaryContainer.copy(alpha = placeholderAlpha),
    focusedPlaceholderColor = Colors.onPrimaryContainer.copy(alpha = placeholderAlpha),
    unfocusedPlaceholderColor = Colors.onPrimaryContainer.copy(alpha = placeholderAlpha),

  )
