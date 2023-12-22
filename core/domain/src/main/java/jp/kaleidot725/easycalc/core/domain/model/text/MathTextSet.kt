package jp.kaleidot725.easycalc.core.domain.model.text

import kotlinx.serialization.Serializable

@Serializable
data class MathTextSet(
    val additions: MathTexts,
    val subtractions: MathTexts,
    val multiplications: MathTexts,
    val multiplicationTable: MathTexts,
    val divisions: MathTexts,
) {
    fun getCategoryMathTexts(category: MathText.Category): MathTexts {
        return when (category) {
            MathText.Category.ADDITION -> additions
            MathText.Category.SUBTRACTION -> subtractions
            MathText.Category.MULTIPLICATION -> multiplications
            MathText.Category.MULTIPLICATION_TABLE -> multiplicationTable
            MathText.Category.DIVISION -> divisions
        }
    }

    companion object {
        val EMPTY = MathTextSet(
            additions = MathTexts(),
            subtractions = MathTexts(),
            multiplications = MathTexts(),
            multiplicationTable = MathTexts(),
            divisions = MathTexts()
        )
    }
}
