package jp.kaleidot725.easycalc.feature.category

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

interface CategoryAction {
    fun clickText(mathText: MathText)
    fun popBack()
}
