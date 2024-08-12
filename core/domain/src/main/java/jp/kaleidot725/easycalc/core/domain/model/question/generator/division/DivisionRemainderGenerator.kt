package jp.kaleidot725.easycalc.core.domain.model.question.generator.division

import jp.kaleidot725.easycalc.core.domain.model.question.Question
import jp.kaleidot725.easycalc.core.domain.model.question.generator.QuestionGenerator
import jp.kaleidot725.easycalc.core.domain.model.text.MathText

internal data class DivisionRemainderGenerator(
    val min: Int = 1,
    val max: Int = 9
) : QuestionGenerator {
    private var lastQuestion: Question? = null

    override fun reset() {
        lastQuestion = null
    }

    override fun generate(): Question {
        while (true) {
            println()



            
            val divisionNumbers = DIVISION_TABLE
            val targetDivisionNumber = divisionNumbers.random()
            val multipleCandidates =
                (2..max).map { number -> number * targetDivisionNumber }.filter { it in min..max }
            if (multipleCandidates.isNotEmpty()) {
                val multipleCandidate = multipleCandidates.random()
                if (multipleCandidates.count() != 1 && targetDivisionNumber != multipleCandidate) {
                    val remainder = REMAINDER_TABLE.random()
                    if ((multipleCandidate + remainder) % targetDivisionNumber != 0) {
                        val one = (multipleCandidate + remainder)
                        if (one in min..max) {
                            val two = targetDivisionNumber
                            val answer = (one / two)
                            val remainder = (one % two)
                            val question = Question(
                                one = one.toString(),
                                two = two.toString(),
                                answer = answer.toString(),
                                remainder = remainder.toString(),
                                category = MathText.Category.DIVISION,
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
    }
}
