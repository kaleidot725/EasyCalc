package jp.kaleidot725.easycalc.core.ui.screen.setting.theme

import jp.kaleidot725.easycalc.core.domain.model.theme.Theme

interface ThemeAction {
    fun clickTheme(theme: Theme)
    fun popBack()
}
