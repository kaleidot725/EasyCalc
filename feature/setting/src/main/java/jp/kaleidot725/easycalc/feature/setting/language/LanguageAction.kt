package jp.kaleidot725.easycalc.feature.setting.language

interface LanguageAction {
    fun clickLanguage(language: jp.kaleidot725.easycalc.core.domain.model.language.Language)
    fun popBack()
}
