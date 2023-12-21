package jp.kaleidot725.easycalc.core.ui.screen.category

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

sealed interface CategoryEvent {
    data class ClickText(val mathText: MathText) : CategoryEvent
    object PopBack : CategoryEvent
}
