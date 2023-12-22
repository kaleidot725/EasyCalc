package jp.kaleidot725.easycalc.feature.start

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

data class StartState(
    val mathText: MathText,
    val isFavorite: Boolean,
)
