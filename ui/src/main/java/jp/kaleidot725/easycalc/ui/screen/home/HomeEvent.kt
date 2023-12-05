package jp.kaleidot725.easycalc.ui.screen.home

import jp.kaleidot725.easycalc.domain.model.text.MathText

sealed interface HomeEvent {
    object ClickHistory : HomeEvent
    object ClickMyList : HomeEvent
    data class ClickText(val mathText: MathText) : HomeEvent
    object ClickStats : HomeEvent
}
