package jp.kaleidot725.easycalc.feature.setting.theme

import androidx.lifecycle.ViewModel
import jp.kaleidot725.easycalc.core.domain.model.theme.Theme
import jp.kaleidot725.easycalc.core.repository.ThemeRepository
import kotlinx.coroutines.flow.collectLatest
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ThemeViewModel(
    private val themeRepository: ThemeRepository,
) : ContainerHost<ThemeState, ThemeEvent>, ThemeAction, ViewModel() {
    override val container = container<ThemeState, ThemeEvent>(ThemeState())

    override fun refresh() = intent {
        themeRepository.get().collectLatest { theme ->
            reduce {
                state.copy(
                    lightSelected = theme == Theme.LIGHT,
                    darkSelected = theme == Theme.DARK,
                    systemSelected = theme == Theme.SYSTEM
                )
            }
        }
    }

    override fun clickTheme(theme: Theme) = intent {
        themeRepository.update(theme)
    }

    override fun popBack() = intent {
        postSideEffect(ThemeEvent.PopBack)
    }
}
