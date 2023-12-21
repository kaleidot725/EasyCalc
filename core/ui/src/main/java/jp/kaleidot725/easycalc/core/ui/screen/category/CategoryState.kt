package jp.kaleidot725.easycalc.core.ui.screen.category

import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.domain.model.text.MathTexts

data class CategoryState(
    val category: MathText.Category,
    val mathTexts: MathTexts = MathTexts(),
)
