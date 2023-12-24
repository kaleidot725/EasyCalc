package jp.kaleidot725.easycalc.compose.app

import jp.kaleidot725.easycalc.core.domain.model.language.Language
import jp.kaleidot725.easycalc.core.domain.model.theme.Theme

data class ComposeAppState(
    val theme: Theme? = null,
    val language: Language = Language.English,
    val isLoading: Boolean = false,
    val isDialog: Boolean = false,
    val count: Int = 0,
)
