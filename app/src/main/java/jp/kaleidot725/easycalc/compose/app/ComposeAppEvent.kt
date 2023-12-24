package jp.kaleidot725.easycalc.compose.app

import jp.kaleidot725.easycalc.core.domain.model.language.Language

sealed interface ComposeAppEvent {
    data class ChangeLanguage(val language: Language) : ComposeAppEvent
}
