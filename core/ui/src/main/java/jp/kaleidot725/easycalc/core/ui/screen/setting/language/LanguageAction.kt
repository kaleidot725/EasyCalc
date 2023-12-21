package jp.kaleidot725.easycalc.core.ui.screen.setting.language

interface LanguageAction {
    fun clickLanguage(language: jp.kaleidot725.easycalc.core.domain.model.language.Language)
    fun popBack()
}
