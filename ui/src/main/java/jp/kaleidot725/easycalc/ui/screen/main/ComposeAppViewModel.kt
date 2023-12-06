package jp.kaleidot725.easycalc.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.easycalc.domain.repository.LanguageRepository
import jp.kaleidot725.easycalc.domain.repository.ThemeRepository
import kotlinx.coroutines.delay
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

    override fun finish() = intent {
        val newCount = state.count + 1
        reduce { state.copy(count = newCount) }

        if (newCount == 3) postSideEffect(ComposeAppEvent.ShowAppInReview)
        if (newCount % 5 == 0) {
            reduce { state.copy(isLoading = true) }
        }
    }

    override fun showedAd() = intent {
        delay(200)
        reduce { state.copy(isLoading = false) }
    }
}
