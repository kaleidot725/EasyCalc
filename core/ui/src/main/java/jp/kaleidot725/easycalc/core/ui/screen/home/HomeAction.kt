package jp.kaleidot725.easycalc.core.ui.screen.home

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

interface HomeAction {
    fun clickHistory()
    fun clickMyList()
    fun clickText(mathText: MathText)
    fun clickStats()
    fun refresh()
}
