package jp.kaleidot725.easycalc.ui.screen.setting.theme

import jp.kaleidot725.easycalc.domain.model.theme.Theme

interface ThemeAction {
    fun clickTheme(theme: Theme)
    fun popBack()
}
