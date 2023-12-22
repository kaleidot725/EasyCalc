package jp.kaleidot725.easycalc.feature.result

import jp.kaleidot725.easycalc.core.domain.model.question.QAList
import jp.kaleidot725.easycalc.core.domain.model.text.MathText

data class ResultState(
    val mathText: MathText,
    val qaList: QAList
)
