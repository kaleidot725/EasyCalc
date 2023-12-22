package jp.kaleidot725.easycalc.feature.history

import jp.kaleidot725.easycalc.core.domain.model.text.MathTexts

data class HistoryState(
    val mathTexts: MathTexts = MathTexts(),
)
