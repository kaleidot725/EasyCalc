package jp.kaleidot725.easycalc.feature.history

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

interface HistoryAction {
    fun clickText(mathText: MathText)
    fun popBack()
    fun refresh()
}
