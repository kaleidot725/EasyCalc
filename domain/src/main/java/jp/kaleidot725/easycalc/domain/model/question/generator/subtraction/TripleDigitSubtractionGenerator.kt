package jp.kaleidot725.easycalc.domain.model.question.generator.subtraction

import jp.kaleidot725.easycalc.domain.model.question.Question
import jp.kaleidot725.easycalc.domain.model.question.generator.QuestionGenerator
import jp.kaleidot725.easycalc.domain.model.text.MathText

internal class TripleDigitSubtractionGenerator : QuestionGenerator {
    private val min = 100
    private val max = 999
    private var lastQuestion: Question? = null

    override fun reset() {
        lastQuestion = null
    }

    override fun generate(): Question {
        while (true) {
            val one = (min..max).random()
            val two = (one..max).random()
            val question = Question(
                two.toString(),
                one.toString(),
                (two - one).toString(),
                MathText.Category.SUBTRACTION
            )
            if (one == two) continue
            if (lastQuestion == question) continue
            lastQuestion = question
            return question
        }
    }
}
