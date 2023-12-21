package jp.kaleidot725.easycalc.core.ui.screen.mylist

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

interface MyListAction {
    fun clickText(mathText: MathText)
    fun popBack()
}
