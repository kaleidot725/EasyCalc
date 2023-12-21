package jp.kaleidot725.easycalc.core.domain.model.question.generator.multiplication

import jp.kaleidot725.easycalc.core.domain.model.question.Question
import jp.kaleidot725.easycalc.core.domain.model.question.generator.QuestionGenerator
import jp.kaleidot725.easycalc.core.domain.model.text.MathText

internal class MultiplicationTableGenerator(private val number: Int) : QuestionGenerator {
    private var count = 1

    init {
        require((1..20).contains(number))
    }

    override fun reset() {
        count = 1
    }

    override fun generate(): Question {
        val one = number
        val two = count

        if (count == 9) {
            count = 1
        } else {
            count += 1
        }

        return Question(
            one.toString(),
            two.toString(),
            (one * two).toString(),
            MathText.Category.MULTIPLICATION_TABLE
        )
    }
}
