package jp.kaleidot725.easycalc.feature.history

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

sealed interface HistoryEvent {
    data class ClickText(val mathText: MathText) : HistoryEvent
    object PopBack : HistoryEvent
}
