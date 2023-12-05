package jp.kaleidot725.easycalc.ui.screen.category

import jp.kaleidot725.easycalc.domain.model.text.MathText

interface CategoryAction {
    fun clickText(mathText: MathText)
    fun popBack()
}
