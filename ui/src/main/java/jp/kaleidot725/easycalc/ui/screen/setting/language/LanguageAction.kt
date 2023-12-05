package jp.kaleidot725.easycalc.ui.screen.setting.language

import jp.kaleidot725.easycalc.domain.model.language.Language

interface LanguageAction {
    fun clickLanguage(language: Language)
    fun popBack()
}
