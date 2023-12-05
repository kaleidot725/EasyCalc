package jp.kaleidot725.easycalc.ui.screen.quiz

import jp.kaleidot725.easycalc.domain.model.text.MathTextSet

data class QuizState(
    val enableAd: Boolean = true,
    val adUnitId: String = "",
    val mathTextSet: MathTextSet = MathTextSet.EMPTY,
)
