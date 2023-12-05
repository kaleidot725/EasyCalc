package jp.kaleidot725.easycalc.domain.model.question.selector

import jp.kaleidot725.easycalc.domain.model.question.generator.QuestionGenerator
import jp.kaleidot725.easycalc.domain.model.question.generator.addition.DoubleDigitAdditionGenerator
import jp.kaleidot725.easycalc.domain.model.question.generator.addition.SingleDigitAdditionGenerator
import jp.kaleidot725.easycalc.domain.model.question.generator.addition.TripleDigitAdditionGenerator
import jp.kaleidot725.easycalc.domain.model.question.generator.division.DivisionGenerator
import jp.kaleidot725.easycalc.domain.model.question.generator.division.DivisionRemainderGenerator
import jp.kaleidot725.easycalc.domain.model.question.generator.multiplication.DoubleDigitMultiplicationGenerator
import jp.kaleidot725.easycalc.domain.model.question.generator.multiplication.MultiplicationTableGenerator
import jp.kaleidot725.easycalc.domain.model.question.generator.multiplication.SingleDigitMultiplicationGenerator
import jp.kaleidot725.easycalc.domain.model.question.generator.multiplication.TripleDigitMultiplicationGenerator
import jp.kaleidot725.easycalc.domain.model.question.generator.subtraction.DoubleDigitSubtractionGenerator
import jp.kaleidot725.easycalc.domain.model.question.generator.subtraction.SingleDigitSubtractionGenerator
import jp.kaleidot725.easycalc.domain.model.question.generator.subtraction.TripleDigitSubtractionGenerator
import jp.kaleidot725.easycalc.domain.model.text.MathText
import kotlin.math.max

class QuestionSelectorImpl : QuestionSelector {
    override fun select(text: MathText): QuestionGenerator {
        return when (text) {
            MathText.SingleDigitsAddition -> SingleDigitAdditionGenerator()
            MathText.DoubleDigitsAddition -> DoubleDigitAdditionGenerator()
            MathText.TripleDigitsAddition -> TripleDigitAdditionGenerator()
            MathText.SingleDigitsSubtraction -> SingleDigitSubtractionGenerator()
            MathText.DoubleDigitsSubtraction -> DoubleDigitSubtractionGenerator()
            MathText.TripleDigitsSubtraction -> TripleDigitSubtractionGenerator()
            MathText.SingleDigitsMultiplication -> SingleDigitMultiplicationGenerator()
            MathText.DoubleDigitsMultiplication -> DoubleDigitMultiplicationGenerator()
            MathText.TripleDigitsMultiplication -> TripleDigitMultiplicationGenerator()
            MathText.SingleDigitsDivision -> DivisionGenerator(min = 1, max = 9)
            MathText.DoubleDigitsDivision -> DivisionGenerator(min = 10, max = 99)
            MathText.TripleDigitsDivision -> DivisionGenerator(min = 100, max = 999)
            MathText.SingleDigitsDivisionRemainder -> DivisionRemainderGenerator(min = 1, max = 9)
            MathText.DoubleDigitsDivisionRemainder -> DivisionRemainderGenerator(min = 10, max = 99)
            MathText.TripleDigitsDivisionRemainder -> DivisionRemainderGenerator(min = 100, max = 999)
            MathText.MultiplicationTableForOne -> MultiplicationTableGenerator(1)
            MathText.MultiplicationTableForTwo -> MultiplicationTableGenerator(2)
            MathText.MultiplicationTableForThree -> MultiplicationTableGenerator(3)
            MathText.MultiplicationTableForFour -> MultiplicationTableGenerator(4)
            MathText.MultiplicationTableForFive -> MultiplicationTableGenerator(5)
            MathText.MultiplicationTableForSix -> MultiplicationTableGenerator(6)
            MathText.MultiplicationTableForSeven -> MultiplicationTableGenerator(7)
            MathText.MultiplicationTableForEight -> MultiplicationTableGenerator(8)
            MathText.MultiplicationTableForNine -> MultiplicationTableGenerator(9)
            MathText.MultiplicationTableFor10 -> MultiplicationTableGenerator(10)
            MathText.MultiplicationTableFor11 -> MultiplicationTableGenerator(11)
            MathText.MultiplicationTableFor12 -> MultiplicationTableGenerator(12)
            MathText.MultiplicationTableFor13 -> MultiplicationTableGenerator(13)
            MathText.MultiplicationTableFor14 -> MultiplicationTableGenerator(14)
            MathText.MultiplicationTableFor15 -> MultiplicationTableGenerator(15)
            MathText.MultiplicationTableFor16 -> MultiplicationTableGenerator(16)
            MathText.MultiplicationTableFor17 -> MultiplicationTableGenerator(17)
            MathText.MultiplicationTableFor18 -> MultiplicationTableGenerator(18)
            MathText.MultiplicationTableFor19 -> MultiplicationTableGenerator(19)
            MathText.MultiplicationTableFor20 -> MultiplicationTableGenerator(20)
        }
    }
}
