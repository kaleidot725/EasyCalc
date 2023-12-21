package jp.kaleidot725.easycalc.core.ui.screen.history

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

interface HistoryAction {
    fun clickText(mathText: MathText)
    fun popBack()
}
