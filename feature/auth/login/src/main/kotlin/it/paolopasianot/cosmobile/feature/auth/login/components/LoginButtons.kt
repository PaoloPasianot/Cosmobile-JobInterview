package it.paolopasianot.cosmobile.feature.auth.login.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import it.paolopasianot.cosmobile.feature.auth.login.LoginContract
import it.paolopasianot.cosmobile.feature.auth.login.R
import it.paolopasianot.cosmobile.feature.auth.login.utility.LoginScrollContentType

/**
 * Esegue il submit della form
 */
internal fun LazyListScope.submitButton(
  modifier: Modifier = Modifier,
  event: (LoginContract.Event) -> Unit,
) {
  item(
    key = R.id.feature_auth_login_submit,
    contentType = LoginScrollContentType.BUTTON,
  ) {
    val semantic = stringResource(id = R.string.feature_auth_login_submit_semantic)
    //Submit
    Button(
        modifier = modifier.semantics { contentDescription = semantic },
        onClick = {
            event.invoke(
                LoginContract.Event.Submit,
            )
        },
    ) {
      Text(
        modifier = Modifier
          .padding(horizontal = 8.dp),
        text = stringResource(
          id = R.string.feature_auth_login_submit,
        ),
      )
    } //Submit
  }
}








