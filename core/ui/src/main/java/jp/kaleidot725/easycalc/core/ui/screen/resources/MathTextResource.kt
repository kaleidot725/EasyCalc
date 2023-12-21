package jp.kaleidot725.easycalc.core.ui.screen.resources

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextId
import jp.kaleidot725.easycalc.ui.R

object MathTextResource {
    @Composable
    fun getTitleById(textId: MathTextId): String {
        return getTitle(MathText.all.first { it.id == textId })
    }

    @Composable
    fun getTitle(text: MathText): String {
        return when (text) {
            MathText.SingleDigitsAddition ->
                stringResource(id = R.string.text_single_digits_addition_title)

            MathText.DoubleDigitsAddition ->
                stringResource(id = R.string.text_double_digits_addition_title)

            MathText.TripleDigitsAddition ->
                stringResource(id = R.string.text_triple_digits_addition_title)

            MathText.SingleDigitsSubtraction ->
                stringResource(id = R.string.text_single_digits_subtraction_title)

            MathText.DoubleDigitsSubtraction ->
                stringResource(id = R.string.text_double_digits_subtraction_title)

            MathText.TripleDigitsSubtraction ->
                stringResource(id = R.string.text_triple_digits_subtraction_title)

            MathText.SingleDigitsMultiplication ->
                stringResource(id = R.string.text_single_digits_multiplication_title)

            MathText.DoubleDigitsMultiplication ->
                stringResource(id = R.string.text_double_digits_multiplication_title)

            MathText.TripleDigitsMultiplication ->
                stringResource(id = R.string.text_triple_digits_multiplication_title)

            MathText.SingleDigitsDivision ->
                stringResource(id = R.string.text_single_digits_division_title)

            MathText.TripleDigitsDivision ->
                stringResource(id = R.string.text_triple_digits_division_title)

            MathText.DoubleDigitsDivision ->
                stringResource(id = R.string.text_double_digits_division_title)

            MathText.SingleDigitsDivisionRemainder ->
                stringResource(id = R.string.text_single_digits_division_remainder_title)

            MathText.TripleDigitsDivisionRemainder ->
                stringResource(id = R.string.text_triple_digits_division_remainder_title)

            MathText.DoubleDigitsDivisionRemainder ->
                stringResource(id = R.string.text_double_digits_division_remainder_title)

            MathText.MultiplicationTableForOne ->
                stringResource(id = R.string.text_multiplication_table_title, 1)

            MathText.MultiplicationTableForTwo ->
                stringResource(id = R.string.text_multiplication_table_title, 2)

            MathText.MultiplicationTableForThree ->
                stringResource(id = R.string.text_multiplication_table_title, 3)

            MathText.MultiplicationTableForFour ->
                stringResource(id = R.string.text_multiplication_table_title, 4)

            MathText.MultiplicationTableForFive ->
                stringResource(id = R.string.text_multiplication_table_title, 5)

            MathText.MultiplicationTableForSix ->
                stringResource(id = R.string.text_multiplication_table_title, 6)

            MathText.MultiplicationTableForSeven ->
                stringResource(id = R.string.text_multiplication_table_title, 7)

            MathText.MultiplicationTableForEight ->
                stringResource(id = R.string.text_multiplication_table_title, 8)

            MathText.MultiplicationTableForNine ->
                stringResource(id = R.string.text_multiplication_table_title, 9)

            MathText.MultiplicationTableFor10 ->
                stringResource(id = R.string.text_multiplication_table_title, 10)

            MathText.MultiplicationTableFor11 ->
                stringResource(id = R.string.text_multiplication_table_title, 11)

            MathText.MultiplicationTableFor12 ->
                stringResource(id = R.string.text_multiplication_table_title, 12)

            MathText.MultiplicationTableFor13 ->
                stringResource(id = R.string.text_multiplication_table_title, 13)

            MathText.MultiplicationTableFor14 ->
                stringResource(id = R.string.text_multiplication_table_title, 14)

            MathText.MultiplicationTableFor15 ->
                stringResource(id = R.string.text_multiplication_table_title, 15)

            MathText.MultiplicationTableFor16 ->
                stringResource(id = R.string.text_multiplication_table_title, 16)

            MathText.MultiplicationTableFor17 ->
                stringResource(id = R.string.text_multiplication_table_title, 17)

            MathText.MultiplicationTableFor18 ->
                stringResource(id = R.string.text_multiplication_table_title, 18)

            MathText.MultiplicationTableFor19 ->
                stringResource(id = R.string.text_multiplication_table_title, 19)

            MathText.MultiplicationTableFor20 ->
                stringResource(id = R.string.text_multiplication_table_title, 20)
        }
    }

    @Composable
    fun getEmoji(text: MathText): String {
        return when (text) {
            MathText.SingleDigitsAddition -> "🍇"
            MathText.DoubleDigitsAddition -> "🍌"
            MathText.TripleDigitsAddition -> "🍎"
            MathText.SingleDigitsSubtraction -> "🥝"
            MathText.DoubleDigitsSubtraction -> "🍋"
            MathText.TripleDigitsSubtraction -> "🍉"
            MathText.SingleDigitsMultiplication -> "🥭"
            MathText.DoubleDigitsMultiplication -> "🥑"
            MathText.TripleDigitsMultiplication -> "🍑"
            MathText.SingleDigitsDivision -> "🍇"
            MathText.TripleDigitsDivision -> "🍌"
            MathText.DoubleDigitsDivision -> "🍎"
            MathText.SingleDigitsDivisionRemainder -> "🍍"
            MathText.TripleDigitsDivisionRemainder -> "🍹"
            MathText.DoubleDigitsDivisionRemainder -> "🍓"
            MathText.MultiplicationTableForOne -> "🍍"
            MathText.MultiplicationTableForTwo -> "🍹"
            MathText.MultiplicationTableForThree -> "🍓"
            MathText.MultiplicationTableForFour -> "🍈"
            MathText.MultiplicationTableForFive -> "🥑"
            MathText.MultiplicationTableForSix -> "🍒"
            MathText.MultiplicationTableForSeven -> "🥭"
            MathText.MultiplicationTableForEight -> "🍑"
            MathText.MultiplicationTableForNine -> "🍐"
            MathText.MultiplicationTableFor10 -> "🍍"
            MathText.MultiplicationTableFor11 -> "🍹"
            MathText.MultiplicationTableFor12 -> "🍓"
            MathText.MultiplicationTableFor13 -> "🍈"
            MathText.MultiplicationTableFor14 -> "🥑"
            MathText.MultiplicationTableFor15 -> "🍒"
            MathText.MultiplicationTableFor16 -> "🥭"
            MathText.MultiplicationTableFor17 -> "🍑"
            MathText.MultiplicationTableFor18 -> "🍐"
            MathText.MultiplicationTableFor19 -> "🥝"
            MathText.MultiplicationTableFor20 -> "🍎"
        }
    }

    @Composable
    fun getContent(text: MathText): String {
        return when (text) {
            MathText.SingleDigitsAddition ->
                stringResource(id = R.string.text_single_digits_addition_content)

            MathText.DoubleDigitsAddition ->
                stringResource(id = R.string.text_double_digits_addition_content)

            MathText.TripleDigitsAddition ->
                stringResource(id = R.string.text_triple_digits_addition_content)

            MathText.SingleDigitsSubtraction ->
                stringResource(id = R.string.text_single_digits_subtraction_content)

            MathText.DoubleDigitsSubtraction ->
                stringResource(id = R.string.text_double_digits_subtraction_content)

            MathText.TripleDigitsSubtraction ->
                stringResource(id = R.string.text_triple_digits_subtraction_content)

            MathText.SingleDigitsMultiplication ->
                stringResource(id = R.string.text_single_digits_multiplication_content)

            MathText.DoubleDigitsMultiplication ->
                stringResource(id = R.string.text_double_digits_multiplication_content)

            MathText.TripleDigitsMultiplication ->
                stringResource(id = R.string.text_triple_digits_multiplication_content)

            MathText.SingleDigitsDivision ->
                stringResource(id = R.string.text_single_digits_division_content)

            MathText.DoubleDigitsDivision ->
                stringResource(id = R.string.text_double_digits_division_content)

            MathText.TripleDigitsDivisionRemainder ->
                stringResource(id = R.string.text_triple_digits_division_remainder_content)

            MathText.SingleDigitsDivisionRemainder ->
                stringResource(id = R.string.text_single_digits_division_remainder_content)

            MathText.DoubleDigitsDivisionRemainder ->
                stringResource(id = R.string.text_double_digits_division_remainder_content)

            MathText.TripleDigitsDivision ->
                stringResource(id = R.string.text_triple_digits_division_content)

            MathText.MultiplicationTableForOne ->
                stringResource(id = R.string.text_multiplication_table_content, 1)

            MathText.MultiplicationTableForTwo ->
                stringResource(id = R.string.text_multiplication_table_content, 2)

            MathText.MultiplicationTableForThree ->
                stringResource(id = R.string.text_multiplication_table_content, 3)

            MathText.MultiplicationTableForFour ->
                stringResource(id = R.string.text_multiplication_table_content, 4)

            MathText.MultiplicationTableForFive ->
                stringResource(id = R.string.text_multiplication_table_content, 5)

            MathText.MultiplicationTableForSix ->
                stringResource(id = R.string.text_multiplication_table_content, 6)

            MathText.MultiplicationTableForSeven ->
                stringResource(id = R.string.text_multiplication_table_content, 7)

            MathText.MultiplicationTableForEight ->
                stringResource(id = R.string.text_multiplication_table_content, 8)

            MathText.MultiplicationTableForNine ->
                stringResource(id = R.string.text_multiplication_table_content, 9)

            MathText.MultiplicationTableFor10 ->
                stringResource(id = R.string.text_multiplication_table_content, 10)

            MathText.MultiplicationTableFor11 ->
                stringResource(id = R.string.text_multiplication_table_content, 11)

            MathText.MultiplicationTableFor12 ->
                stringResource(id = R.string.text_multiplication_table_content, 12)

            MathText.MultiplicationTableFor13 ->
                stringResource(id = R.string.text_multiplication_table_content, 13)

            MathText.MultiplicationTableFor14 ->
                stringResource(id = R.string.text_multiplication_table_content, 14)

            MathText.MultiplicationTableFor15 ->
                stringResource(id = R.string.text_multiplication_table_content, 15)

            MathText.MultiplicationTableFor16 ->
                stringResource(id = R.string.text_multiplication_table_content, 16)

            MathText.MultiplicationTableFor17 ->
                stringResource(id = R.string.text_multiplication_table_content, 17)

            MathText.MultiplicationTableFor18 ->
                stringResource(id = R.string.text_multiplication_table_content, 18)

            MathText.MultiplicationTableFor19 ->
                stringResource(id = R.string.text_multiplication_table_content, 19)

            MathText.MultiplicationTableFor20 ->
                stringResource(id = R.string.text_multiplication_table_content, 20)
        }
    }

    @Composable
    fun getOperator(category: MathText.Category): String {
        return when (category) {
            MathText.Category.ADDITION -> "+"
            MathText.Category.SUBTRACTION -> "-"
            MathText.Category.MULTIPLICATION -> "✕"
            MathText.Category.MULTIPLICATION_TABLE -> "✕"
            MathText.Category.DIVISION -> "÷"
        }
    }
}
