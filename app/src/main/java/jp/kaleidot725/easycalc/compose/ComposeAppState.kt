package jp.kaleidot725.easycalc.compose

import jp.kaleidot725.easycalc.core.domain.model.theme.Theme

data class ComposeAppState(
    val theme: Theme? = null,
    val language: jp.kaleidot725.easycalc.core.domain.model.language.Language = jp.kaleidot725.easycalc.core.domain.model.language.Language.English,
    val isLoading: Boolean = false,
    val isDialog: Boolean = false,
    val count: Int = 0,
)
