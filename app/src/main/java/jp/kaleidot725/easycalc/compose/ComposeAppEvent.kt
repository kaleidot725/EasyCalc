package jp.kaleidot725.easycalc.compose

import jp.kaleidot725.easycalc.core.domain.model.language.Language

sealed interface ComposeAppEvent {
    data class ChangeLanguage(val language: Language) :
        ComposeAppEvent

    object ShowAppInReview : ComposeAppEvent
}
