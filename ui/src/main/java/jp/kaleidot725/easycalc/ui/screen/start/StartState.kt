package jp.kaleidot725.easycalc.ui.screen.start

import jp.kaleidot725.easycalc.domain.model.text.MathText

data class StartState(
    val mathText: MathText,
    val isFavorite: Boolean,
)
