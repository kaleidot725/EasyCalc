package jp.kaleidot725.easycalc.feature.mylist

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

interface MyListAction {
    fun refresh()
    fun clickText(mathText: MathText)
    fun popBack()
}
