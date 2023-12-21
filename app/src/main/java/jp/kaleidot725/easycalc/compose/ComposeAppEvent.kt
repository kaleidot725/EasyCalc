package jp.kaleidot725.easycalc.compose

sealed interface ComposeAppEvent {
    data class ChangeLanguage(val language: jp.kaleidot725.easycalc.core.domain.model.language.Language) :
        ComposeAppEvent

    object ShowAppInReview : ComposeAppEvent
}
