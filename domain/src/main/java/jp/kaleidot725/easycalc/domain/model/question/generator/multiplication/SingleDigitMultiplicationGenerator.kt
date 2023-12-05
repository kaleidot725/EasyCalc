package jp.kaleidot725.easycalc.domain.model.question.generator.multiplication

import jp.kaleidot725.easycalc.domain.model.question.Question
import jp.kaleidot725.easycalc.domain.model.question.generator.QuestionGenerator
import jp.kaleidot725.easycalc.domain.model.text.MathText

internal class SingleDigitMultiplicationGenerator : QuestionGenerator {
    private val min = 1
    private val max = 9
    private var lastQuestion: Question? = null

    override fun reset() {
        lastQuestion = null
    }
    override fun generate(): Question {
        while (true) {
            val one = (min..max).random()
            val two = (min..max).random()
            val question = Question(
                one.toString(),
                two.toString(),
                (two * one).toString(),
                MathText.Category.MULTIPLICATION
            )
            if (lastQuestion == question) continue
            lastQuestion = question
            return question
        }
    }
}
