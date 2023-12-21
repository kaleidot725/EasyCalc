package jp.kaleidot725.easycalc.core.domain.model.question.generator

import jp.kaleidot725.easycalc.core.domain.model.question.Question

interface QuestionGenerator {
    fun reset()
    fun generate(): Question
}
