package jp.kaleidot725.easycalc.core.domain.model.text

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable
import java.util.concurrent.TimeUnit

@Immutable
@Serializable
sealed interface MathText {
    val id: MathTextId
    val category: Category
    val timeout: Long
    val count: Long

    @Immutable
    object SingleDigitsAddition : MathText {
        override val id: MathTextId = MathTextId("ADDITION_SINGLE_DIGITS")
        override val category: Category = Category.ADDITION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 10
    }

    @Immutable
    object DoubleDigitsAddition : MathText {
        override val id: MathTextId = MathTextId("ADDITION_DOUBLE_DIGITS")
        override val category: Category = Category.ADDITION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(30)
        override val count: Long = 10
    }

    @Immutable
    object TripleDigitsAddition : MathText {
        override val id: MathTextId = MathTextId("ADDITION_TRIPLE_DIGITS")
        override val category: Category = Category.ADDITION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(60)
        override val count: Long = 10
    }

    @Immutable
    object SingleDigitsSubtraction : MathText {
        override val id: MathTextId = MathTextId("SUBTRACTION_SINGLE_DIGITS")
        override val category: Category = Category.SUBTRACTION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 10
    }

    @Immutable
    object DoubleDigitsSubtraction : MathText {
        override val id: MathTextId = MathTextId("SUBTRACTION_DOUBLE_DIGITS")
        override val category: Category = Category.SUBTRACTION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(30)
        override val count: Long = 10
    }

    @Immutable
    object TripleDigitsSubtraction : MathText {
        override val id: MathTextId = MathTextId("SUBTRACTION_TRIPLE_DIGITS")
        override val category: Category = Category.SUBTRACTION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(60)
        override val count: Long = 10
    }

    @Immutable
    object SingleDigitsMultiplication : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_SINGLE_DIGITS")
        override val category: Category = Category.MULTIPLICATION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 10
    }

    @Immutable
    object DoubleDigitsMultiplication : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_DOUBLE_DIGITS")
        override val category: Category = Category.MULTIPLICATION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(30)
        override val count: Long = 10
    }

    @Immutable
    object TripleDigitsMultiplication : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TRIPLE_DIGITS")
        override val category: Category = Category.MULTIPLICATION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(60)
        override val count: Long = 10
    }

    @Immutable
    object SingleDigitsDivision : MathText {
        override val id: MathTextId = MathTextId("DIVISION_SINGLE_DIGITS")
        override val category: Category = Category.DIVISION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 10
    }

    @Immutable
    object DoubleDigitsDivision : MathText {
        override val id: MathTextId = MathTextId("DIVISION_DOUBLE_DIGITS")
        override val category: Category = Category.DIVISION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(30)
        override val count: Long = 10
    }

    @Immutable
    object TripleDigitsDivision : MathText {
        override val id: MathTextId = MathTextId("DIVISION_TRIPLE_DIGITS")
        override val category: Category = Category.DIVISION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(60)
        override val count: Long = 10
    }

    @Immutable
    object SingleDigitsDivisionRemainder : MathText {
        override val id: MathTextId = MathTextId("DIVISION_SINGLE_DIGITS_REMAINDER")
        override val category: Category = Category.DIVISION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 10
    }

    @Immutable
    object DoubleDigitsDivisionRemainder : MathText {
        override val id: MathTextId = MathTextId("DIVISION_DOUBLE_DIGITS_REMAINDER")
        override val category: Category = Category.DIVISION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(30)
        override val count: Long = 10
    }

    @Immutable
    object TripleDigitsDivisionRemainder : MathText {
        override val id: MathTextId = MathTextId("DIVISION_TRIPLE_DIGITS_REMAINDER")
        override val category: Category = Category.DIVISION
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(60)
        override val count: Long = 10
    }

    @Immutable
    object MultiplicationTableForOne : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_ONE")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableForTwo : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_TWO")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableForThree : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_THREE")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableForFour : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_FOUR")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableForFive : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_FIVE")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableForSix : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_SIX")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableForSeven : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_SEVEN")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableForEight : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_EIGHT")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableForNine : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_NINE")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableFor10 : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_10")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableFor11 : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_11")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableFor12 : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_12")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableFor13 : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_13")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableFor14 : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_14")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableFor15 : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_15")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableFor16 : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_16")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableFor17 : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_17")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableFor18 : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_18")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableFor19 : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_19")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    @Immutable
    object MultiplicationTableFor20 : MathText {
        override val id: MathTextId = MathTextId("MULTIPLICATION_TABLE_FOR_20")
        override val category: Category = Category.MULTIPLICATION_TABLE
        override val timeout: Long = TimeUnit.SECONDS.toSeconds(15)
        override val count: Long = 9
    }

    enum class Category {
        ADDITION,
        SUBTRACTION,
        MULTIPLICATION,
        MULTIPLICATION_TABLE,
        DIVISION
    }

    companion object {
        val all = listOf(
            SingleDigitsAddition,
            DoubleDigitsAddition,
            TripleDigitsAddition,
            SingleDigitsSubtraction,
            DoubleDigitsSubtraction,
            TripleDigitsSubtraction,
            SingleDigitsMultiplication,
            DoubleDigitsMultiplication,
            TripleDigitsMultiplication,
            SingleDigitsDivision,
            DoubleDigitsDivision,
            TripleDigitsDivision,
            SingleDigitsDivisionRemainder,
            DoubleDigitsDivisionRemainder,
            TripleDigitsDivisionRemainder,
            MultiplicationTableForOne,
            MultiplicationTableForTwo,
            MultiplicationTableForThree,
            MultiplicationTableForFour,
            MultiplicationTableForFive,
            MultiplicationTableForSix,
            MultiplicationTableForSeven,
            MultiplicationTableForEight,
            MultiplicationTableForNine,
            MultiplicationTableFor10,
            MultiplicationTableFor11,
            MultiplicationTableFor12,
            MultiplicationTableFor13,
            MultiplicationTableFor14,
            MultiplicationTableFor15,
            MultiplicationTableFor16,
            MultiplicationTableFor17,
            MultiplicationTableFor18,
            MultiplicationTableFor19,
            MultiplicationTableFor20,
        )
    }
}
