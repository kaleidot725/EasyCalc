package jp.kaleidot725.easycalc.ui.screen.category

import jp.kaleidot725.easycalc.domain.model.text.MathText

sealed interface CategoryEvent {
    data class ClickText(val mathText: MathText) : CategoryEvent
    object PopBack : CategoryEvent
}
