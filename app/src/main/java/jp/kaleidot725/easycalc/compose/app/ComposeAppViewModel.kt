package jp.kaleidot725.easycalc.compose.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.easycalc.core.repository.LanguageRepository
import jp.kaleidot725.easycalc.core.repository.ThemeRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ComposeAppViewModel(
    private val themeRepository: ThemeRepository,
    private val languageRepository: LanguageRepository,
) : ContainerHost<ComposeAppState, ComposeAppEvent>, ViewModel(), ComposeAppAction {
    override val container = container<ComposeAppState, ComposeAppEvent>(ComposeAppState())

    init {
        viewModelScope.launch {
            themeRepository.get().collectLatest { theme ->
                intent { reduce { state.copy(theme = theme) } }
            }
        }
        viewModelScope.launch {
            languageRepository.initialize()
            languageRepository.get().collectLatest { language ->
                intent {
                    postSideEffect(ComposeAppEvent.ChangeLanguage(language))
                    reduce { state.copy(language = language) }
                }
            }
        }
    }
}
