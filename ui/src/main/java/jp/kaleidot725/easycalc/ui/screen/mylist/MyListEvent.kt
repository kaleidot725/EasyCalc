package jp.kaleidot725.easycalc.ui.screen.mylist

import jp.kaleidot725.easycalc.domain.model.text.MathText

sealed interface MyListEvent {
    data class ClickText(val mathText: MathText) : MyListEvent
    object PopBack : MyListEvent
}
