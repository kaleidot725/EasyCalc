package jp.kaleidot725.easycalc.ui.screen.quiz

import jp.kaleidot725.easycalc.domain.model.text.MathTextSet

data class QuizState(
    val mathTextSet: MathTextSet = MathTextSet.EMPTY,
)
