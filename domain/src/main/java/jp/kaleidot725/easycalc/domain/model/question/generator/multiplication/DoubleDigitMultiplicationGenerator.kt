package jp.kaleidot725.easycalc.domain.model.question.generator.multiplication

import jp.kaleidot725.easycalc.domain.model.question.Question
import jp.kaleidot725.easycalc.domain.model.question.generator.QuestionGenerator
import jp.kaleidot725.easycalc.domain.model.text.MathText

internal class DoubleDigitMultiplicationGenerator : QuestionGenerator {
    private val min = 10
    private val max = 99
    private val twoMin = 1
    private val twoMax = 9

    private var lastQuestion: Question? = null

    override fun reset() {
        lastQuestion = null
    }

    override fun generate(): Question {
        while (true) {
            val one = (min..max).random()
            val two = (twoMin..twoMax).random()
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
