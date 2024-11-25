package it.paolopasianot.cosmobile.feature.auth.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import it.paolopasianot.cosmobile.core.designsystem.theme.AppTheme
import it.paolopasianot.cosmobile.core.designsystem.theme.Colors
import it.paolopasianot.cosmobile.core.designsystem.theme.Shape
import it.paolopasianot.cosmobile.core.designsystem.theme.Typography
import it.paolopasianot.cosmobile.core.ui.CollectInLaunchedEffect
import it.paolopasianot.cosmobile.core.ui.DevicePreviews
import it.paolopasianot.cosmobile.core.ui.error.UIStringHolder
import it.paolopasianot.cosmobile.core.ui.utility.isCompactWidth
import it.paolopasianot.cosmobile.core.ui.utility.isLandscape
import it.paolopasianot.cosmobile.feature.auth.login.components.passwordFieldUI
import it.paolopasianot.cosmobile.feature.auth.login.components.submitButton
import it.paolopasianot.cosmobile.feature.auth.login.components.userFieldUI
import it.paolopasianot.cosmobile.feature.auth.login.utility.LoginScrollContentType
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

/**
 * Permette ad un utente di effettuare il login tramite
 *  - username o email
 *  - password
 *
 * @param modifier il [Modifier] da applicare alla base del login
 * @param state lo [LoginContract.State] che pilota la grafica
 * @param event gli [LoginContract.Event] che la grafica genera per il suo funzionamento
 * @param effects possibili [LoginContract.Effect] lanciati dal model che la login deve gestire
 */
@Composable
fun LoginUI(
  modifier: Modifier = Modifier,
  state: LoginContract.State,
  event: (LoginContract.Event) -> Unit,
  effects: SharedFlow<LoginContract.Effect>,
) {
  //Window Adaptive
  val windowAdaptiveInfo = currentWindowAdaptiveInfo()

  //Focus Manager
  val (user, password) = remember { FocusRequester.createRefs() }
  val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

  //Lazy List
  val listState = rememberLazyListState()

  //Effects
  effects.CollectInLaunchedEffect { effect ->
    when (effect) {
      LoginContract.Effect.FocusErrorMessage -> {
        listState.animateScrollToItem(index = 2)//Error message
      }
    }
  }

  Box(
    modifier = modifier
      .background(Colors.background)
      .imePadding(),
  ) {
    Surface(
      modifier = (
        if (windowAdaptiveInfo.isCompactWidth)
          if (isKeyboardVisible) Modifier.fillMaxSize()
          else Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
        else
          if (isLandscape) Modifier.fillMaxSize()
          else Modifier
        )
        .animateContentSize()
        .align(Alignment.Center),
      color = Colors.primaryContainer,
      tonalElevation = 4.dp,
      shadowElevation = 2.dp,
      shape = if (isKeyboardVisible) RectangleShape else Shape.extraLarge,
    ) {
      LazyColumn(
        modifier = Modifier,
        contentPadding = PaddingValues(
          vertical = 32.dp,
          horizontal = if (windowAdaptiveInfo.isCompactWidth) 0.dp else 32.dp,
        ),
        state = listState,
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {

        item(
          key = R.id.feature_auth_login_title,
          contentType = LoginScrollContentType.TITLE,
        ) {
          Text(
            modifier = Modifier
              .padding(top = 16.dp),
            text = stringResource(id = R.string.feature_auth_login_title),
            color = Colors.onPrimaryContainer,
            style = Typography.headlineLarge,
          )
        }

        //IMPORTANT! Edit effect if u change this item position
        item(
          key = R.id.feature_auth_login_error_message,
          contentType = LoginScrollContentType.ERROR,
        ) {
          AnimatedVisibility(
            visible = state.errorMessage != null,
            label = "Generic Error Label",
          ) {
            Text(
              text = state.errorMessage?.getMessage() ?: stringResource(
                id = R.string.feature_auth_login_generic_error,
              ),
              color = Colors.error,
            )
          }
        }

        // Form field USER
        userFieldUI(
          modifier = Modifier
            .padding(top = 32.dp, bottom = 8.dp)
            .focusRequester(user),
          state = state,
          event = event,
          onNext = {
            password.requestFocus()
          },
        )

        //Form field PASSWORD
        passwordFieldUI(
          modifier = Modifier
            .padding(bottom = 32.dp, top = 8.dp)
            .focusRequester(password),
          state = state,
          event = event,
          onDone = {
            event.invoke(
              LoginContract.Event.Submit,
            )
          },
        )

        //SUBMIT
        submitButton(
          modifier = Modifier
            .padding(vertical = 4.dp)
            .width(200.dp),
          event = event,
        )

      }
    }

    //Set the focus on user field at start
    LaunchedEffect(Unit) {
      user.requestFocus()
    }
  }
}

@DevicePreviews
@Composable
private fun BasicLoginUIPreview() {
  AppTheme {
    LoginUI(
      modifier = Modifier.fillMaxSize(),
      state = LoginContract.State(
        errorMessage = UIStringHolder.Str("errore dal server"),
      ),
      event = {},
      effects = MutableSharedFlow(),
    )
  }
}
