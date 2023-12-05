package jp.kaleidot725.easycalc.domain.model.question.generator

import jp.kaleidot725.easycalc.domain.model.question.Question

interface QuestionGenerator {
    fun reset()
    fun generate(): Question
}
