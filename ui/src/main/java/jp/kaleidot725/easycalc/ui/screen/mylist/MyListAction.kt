package jp.kaleidot725.easycalc.ui.screen.mylist

import jp.kaleidot725.easycalc.domain.model.text.MathText

interface MyListAction {
    fun clickText(mathText: MathText)
    fun popBack()
}
