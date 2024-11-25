package it.paolopasianot.cosmobile.core.ui

import androidx.compose.runtime.Immutable
import it.paolopasianot.cosmobile.core.ui.error.UIStringHolder

/**
 * Ingloba le informazioni per creare un'alert
 *
 * @property title titolo dell'alert
 * @property message messaggio dell'alert
 * @property positiveButton testo del bottone di conferma se presente
 * @property negativeButton testo del bottone per annullare se presente
 */
@Immutable
data class AlertValue<PositiveEvent, NegativeEvent>(
    val title: UIStringHolder? = null,
    val message: UIStringHolder,
    val positiveButton: AlertBtnHolder<PositiveEvent>? = null,
    val negativeButton: AlertBtnHolder<NegativeEvent>? = null,

    )


@Immutable
data class AlertBtnHolder<Event>(
    val text: UIStringHolder,
    val event: Event? = null,
)
