package jp.kaleidot725.easycalc.feature.quiz

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

interface QuizAction {
    fun clickCategory(category: MathText.Category)
    fun clickText(mathText: MathText)
}
