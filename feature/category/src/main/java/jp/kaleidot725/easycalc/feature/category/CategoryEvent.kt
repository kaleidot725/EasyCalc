package jp.kaleidot725.easycalc.feature.category

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

sealed interface CategoryEvent {
    data class ClickText(val mathText: MathText) : CategoryEvent
    object PopBack : CategoryEvent
}
