package jp.kaleidot725.easycalc.ui.screen.start

import jp.kaleidot725.easycalc.domain.model.text.MathText

sealed interface StartEvent {
    data class ClickStart(val mathText: MathText) : StartEvent
    object PopBack : StartEvent
}
