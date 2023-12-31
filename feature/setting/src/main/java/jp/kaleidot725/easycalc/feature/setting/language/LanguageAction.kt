package jp.kaleidot725.easycalc.feature.setting.language

import jp.kaleidot725.easycalc.core.domain.model.language.Language

interface LanguageAction {
    fun refresh()
    fun clickLanguage(language: Language)
    fun popBack()
}
