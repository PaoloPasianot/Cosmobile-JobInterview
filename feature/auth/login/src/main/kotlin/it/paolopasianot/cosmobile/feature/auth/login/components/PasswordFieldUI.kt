package it.paolopasianot.cosmobile.feature.auth.login.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import it.paolopasianot.cosmobile.core.designsystem.icon.CosmobileIcons
import it.paolopasianot.cosmobile.core.designsystem.theme.Colors
import it.paolopasianot.cosmobile.core.designsystem.theme.Typography
import it.paolopasianot.cosmobile.core.ui.StringTextFieldValue
import it.paolopasianot.cosmobile.feature.auth.login.LoginContract
import it.paolopasianot.cosmobile.feature.auth.login.R
import it.paolopasianot.cosmobile.feature.auth.login.utility.LoginScrollContentType
import it.paolopasianot.cosmobile.feature.auth.login.utility.LoginTextFieldColors

/**
 * Campo di per l'inserimento della password.
 */
internal fun LazyListScope.passwordFieldUI(
  modifier: Modifier = Modifier,
  state: LoginContract.State,
  event: (LoginContract.Event) -> Unit,
  onDone: KeyboardActionScope.() -> Unit,
){
  item(
    key = R.id.feature_auth_login_password,
    contentType = LoginScrollContentType.FORM,
  ) {
    //Password
    var showPassword by remember { mutableStateOf(false) }
    TextField(
      modifier = modifier,
      value = state.password.text,
      onValueChange = {
        event.invoke(
          LoginContract.Event.OnValueChange(
            LoginContract.Fields.Password(it),
          ),
        )
      },
      label = {
        Text(
          text = stringResource(
            id = R.string.feature_auth_login_password_field_label,
          ),
          color = Colors.onPrimaryContainer
        )
      },
      placeholder = {
        Text(
          text = stringResource(
            id = R.string.feature_auth_login_password_field_placeholder,
          ),
          style = Typography.labelSmall,
        )
      },
      leadingIcon = {
        IconButton(
          onClick = {
            showPassword = !showPassword
          },
        ) {
          Icon(
            imageVector = if (showPassword) CosmobileIcons.HidePassword
            else CosmobileIcons.ShowPassword,
            contentDescription = stringResource(
              id = if (showPassword) R.string.feature_auth_login_password_field_hide_cd
              else R.string.feature_auth_login_password_field_show_cd,
            ),
          )
        }
      },
      trailingIcon = {
        if (!state.password.isEmpty()) {
          IconButton(
            onClick = {
              event.invoke(
                LoginContract.Event.OnValueChange(
                  LoginContract.Fields.Password(StringTextFieldValue.BLANK),
                ),
              )
            }
          ) {
            Icon(
              imageVector = CosmobileIcons.Close,
              contentDescription = stringResource(
                id = R.string.feature_auth_login_password_field_clear_cd,
              ),
            )
          }
        }
      },
      supportingText = {
        AnimatedVisibility(
          visible = state.password.isError(),
        ) {
          Text(
            text = stringResource(
              id = R.string.feature_auth_login_password_field_error_prefix,
              (state.password.error?.getMessage() ?: ""),
            ),
            color = Colors.error,
          )
        }

        AnimatedVisibility(
          visible = !state.password.isError(),
        ) {
          Text(
            text = stringResource(
              id = R.string.feature_auth_login_password_field_supporting_text,
            ),
            style = Typography.labelSmall,
            color = Colors.onPrimaryContainer
          )
        }
      },
      visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
      keyboardActions = KeyboardActions(
        onDone = onDone,
      ),
      keyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Done,
        keyboardType = KeyboardType.Password,
      ),
      isError = state.password.isError(),
      colors = LoginTextFieldColors,
    )//Password

  }
}
