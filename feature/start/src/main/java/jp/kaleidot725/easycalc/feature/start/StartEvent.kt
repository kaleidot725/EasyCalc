package jp.kaleidot725.easycalc.feature.start

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

sealed interface StartEvent {
    data class ClickStart(val mathText: MathText) : StartEvent
    object PopBack : StartEvent
}
