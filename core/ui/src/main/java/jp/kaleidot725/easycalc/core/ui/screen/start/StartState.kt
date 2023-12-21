package jp.kaleidot725.easycalc.core.ui.screen.start

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

data class StartState(
    val mathText: MathText,
    val isFavorite: Boolean,
)
