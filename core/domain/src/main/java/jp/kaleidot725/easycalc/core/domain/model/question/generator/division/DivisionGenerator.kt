package jp.kaleidot725.easycalc.core.domain.model.question.generator.division

import jp.kaleidot725.easycalc.core.domain.model.question.Question
import jp.kaleidot725.easycalc.core.domain.model.question.generator.QuestionGenerator
import jp.kaleidot725.easycalc.core.domain.model.text.MathText

internal data class DivisionGenerator(
    val min: Int = 1,
    val max: Int = 9
) : QuestionGenerator {
    private var lastQuestion: Question? = null

    override fun reset() {
        lastQuestion = null
    }

    override fun generate(): Question {
        while (true) {
            val targetPrimeNumbers = DIVISION_TABLE
            val targetPrimeNumber = targetPrimeNumbers.random()
            val multipleCandidates =
                (2..max).map { number -> number * targetPrimeNumber }.filter { it in min..max }
            if (multipleCandidates.isNotEmpty()) {
                val multipleCandidate = multipleCandidates.random()
                if (multipleCandidates.count() != 1 && targetPrimeNumber != multipleCandidate) {
                    val question = Question(
                        one = multipleCandidate.toString(),
                        two = targetPrimeNumber.toString(),
                        answer = (multipleCandidate / targetPrimeNumber).toString(),
                        category = MathText.Category.DIVISION
                    )
                    if (lastQuestion != question) {
                        lastQuestion = question
                        return question
                    }
                }
            }
        }
    }
}
