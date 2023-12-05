package jp.kaleidot725.easycalc.ui.screen.history

import jp.kaleidot725.easycalc.domain.model.text.MathText

interface HistoryAction {
    fun clickText(mathText: MathText)
    fun popBack()
}
