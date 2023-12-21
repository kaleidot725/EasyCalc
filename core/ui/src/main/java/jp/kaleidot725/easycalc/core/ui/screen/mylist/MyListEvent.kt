package jp.kaleidot725.easycalc.core.ui.screen.mylist

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

sealed interface MyListEvent {
    data class ClickText(val mathText: MathText) : MyListEvent
    object PopBack : MyListEvent
}
