package jp.kaleidot725.easycalc.core.ui.screen.quiz

import jp.kaleidot725.easycalc.core.domain.model.text.MathTextSet

data class QuizState(
    val mathTextSet: MathTextSet = MathTextSet.EMPTY,
)
