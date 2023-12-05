package jp.kaleidot725.easycalc.ui.screen.quiz

import jp.kaleidot725.easycalc.domain.model.text.MathText

sealed interface QuizEvent {
    data class ClickCategory(val category: MathText.Category) : QuizEvent
    data class ClickText(val mathText: MathText) : QuizEvent
}
