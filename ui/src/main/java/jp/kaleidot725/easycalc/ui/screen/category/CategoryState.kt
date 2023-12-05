package jp.kaleidot725.easycalc.ui.screen.category

import jp.kaleidot725.easycalc.domain.model.text.MathText
import jp.kaleidot725.easycalc.domain.model.text.MathTexts

data class CategoryState(
    val category: MathText.Category,
    val mathTexts: MathTexts = MathTexts(),
)
