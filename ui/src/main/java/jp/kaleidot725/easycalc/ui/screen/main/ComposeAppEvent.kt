package jp.kaleidot725.easycalc.ui.screen.main

import jp.kaleidot725.easycalc.domain.model.language.Language

sealed interface ComposeAppEvent {
    data class ChangeLanguage(val language: Language) : ComposeAppEvent
    object ShowAppInReview : ComposeAppEvent
}
