package jp.kaleidot725.easycalc.ui.screen.mylist

import jp.kaleidot725.easycalc.domain.model.text.MathTexts

data class MyListState(
    val enableAd: Boolean = true,
    val adUnitId: String = "",
    val mathTexts: MathTexts = MathTexts(),
)
