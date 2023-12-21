package jp.kaleidot725.easycalc.core.ui.screen.quiz

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

sealed interface QuizEvent {
    data class ClickCategory(val category: MathText.Category) : QuizEvent
    data class ClickText(val mathText: MathText) : QuizEvent
}
