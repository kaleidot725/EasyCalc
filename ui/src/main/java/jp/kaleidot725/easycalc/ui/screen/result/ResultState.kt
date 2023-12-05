package jp.kaleidot725.easycalc.ui.screen.result

import jp.kaleidot725.easycalc.domain.model.question.QAList
import jp.kaleidot725.easycalc.domain.model.text.MathText

data class ResultState(val mathText: MathText, val qaList: QAList)
