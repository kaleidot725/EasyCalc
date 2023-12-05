package jp.kaleidot725.easycalc.ui.screen.main

import jp.kaleidot725.easycalc.domain.model.language.Language
import jp.kaleidot725.easycalc.domain.model.theme.Theme

data class ComposeAppState(
    val theme: Theme? = null,
    val language: Language = Language.English,
    val isLoading: Boolean = false,
    val isDialog: Boolean = false,
    val count: Int = 0,
)
