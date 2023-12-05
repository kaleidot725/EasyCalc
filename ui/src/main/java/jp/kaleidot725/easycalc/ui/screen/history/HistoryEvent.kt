package jp.kaleidot725.easycalc.ui.screen.history

import jp.kaleidot725.easycalc.domain.model.text.MathText

sealed interface HistoryEvent {
    data class ClickText(val mathText: MathText) : HistoryEvent
    object PopBack : HistoryEvent
}
