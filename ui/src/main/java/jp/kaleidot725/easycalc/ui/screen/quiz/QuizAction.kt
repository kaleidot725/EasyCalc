package jp.kaleidot725.easycalc.ui.screen.quiz

import jp.kaleidot725.easycalc.domain.model.text.MathText

interface QuizAction {
    fun clickCategory(category: MathText.Category)
    fun clickText(mathText: MathText)
}
