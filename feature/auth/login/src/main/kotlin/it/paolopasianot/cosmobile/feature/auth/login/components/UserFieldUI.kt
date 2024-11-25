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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import it.paolopasianot.cosmobile.core.designsystem.icon.CosmobileIcons
import it.paolopasianot.cosmobile.core.designsystem.theme.Colors
import it.paolopasianot.cosmobile.core.designsystem.theme.Typography
import it.paolopasianot.cosmobile.core.ui.StringTextFieldValue
import it.paolopasianot.cosmobile.feature.auth.login.LoginContract
import it.paolopasianot.cosmobile.feature.auth.login.R
import it.paolopasianot.cosmobile.feature.auth.login.utility.LoginScrollContentType
import it.paolopasianot.cosmobile.feature.auth.login.utility.LoginTextFieldColors


/**
 * Campo di per l'inserimento dell'utente.
 * Il campo può essere sia una mail che un'username
 */
internal fun LazyListScope.userFieldUI(
  modifier: Modifier = Modifier,
  state: LoginContract.State,
  event: (LoginContract.Event) -> Unit,
  onNext: KeyboardActionScope.() -> Unit
){
  item(
    key = R.id.feature_auth_login_user,
    contentType = LoginScrollContentType.FORM,
  ) {
    // User
    TextField(
      modifier = modifier,
      value = state.user.text,
      onValueChange = {
        event.invoke(
          LoginContract.Event.OnValueChange(
            LoginContract.Fields.User(it),
          ),
        )
      },
      label = {
        Text(
          text = stringResource(
            id = R.string.feature_auth_login_username_field_label,
          )
        )
      },
      leadingIcon = {
        Icon(
          imageVector = CosmobileIcons.Person,
          contentDescription = null,
        )
      },
      placeholder = {
        Text(
          text = stringResource(
            id = R.string.feature_auth_login_username_field_placeholder,
          ),
          style = Typography.labelSmall,
        )
      },
      trailingIcon = {
        if (!state.user.isEmpty()) {
          IconButton(
            onClick = {
              event.invoke(
                LoginContract.Event.OnValueChange(
                  LoginContract.Fields.User(StringTextFieldValue.BLANK),
                ),
              )
            },
          ) {
            Icon(
              imageVector = CosmobileIcons.Close,
              contentDescription = stringResource(
                id = R.string.feature_auth_login_user_field_clear_cd,
                stringResource(id = R.string.feature_auth_login_username_field_label),
              ),
            )
          }
        }
      },
      supportingText = {
        AnimatedVisibility(
          visible = state.user.isError(),
        ) {
          Text(
            text = stringResource(
              id = R.string.feature_auth_login_user_field_error_prefix,
              (state.user.error?.getMessage() ?: ""),
            ),
            color = Colors.error,
          )
        }

        AnimatedVisibility(
          visible = !state.user.isError(),
        ) {
          Text(
            text = stringResource(
              id =  R.string.feature_auth_login_username_field_supporting_text
            ),
            style = Typography.labelSmall,
            color = Colors.onPrimaryContainer
          )
        }
      },
      keyboardActions = KeyboardActions(
        onNext = onNext
      ),
      keyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Unspecified
      ),
      isError = state.user.isError(),
      colors = LoginTextFieldColors,
    )//User
  }
}
