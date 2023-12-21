package jp.kaleidot725.easycalc.core.domain.model.question.selector

import jp.kaleidot725.easycalc.core.domain.model.question.generator.QuestionGenerator
import jp.kaleidot725.easycalc.core.domain.model.text.MathText

interface QuestionSelector {
    fun select(text: MathText): QuestionGenerator
}
