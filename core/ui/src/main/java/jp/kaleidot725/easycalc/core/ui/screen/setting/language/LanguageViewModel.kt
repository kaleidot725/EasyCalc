package jp.kaleidot725.easycalc.core.ui.screen.setting.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.easycalc.core.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class LanguageViewModel(
    private val languageRepository: LanguageRepository,
) : ContainerHost<LanguageState, LanguageEvent>, LanguageAction, ViewModel() {
    override val container = container<LanguageState, LanguageEvent>(LanguageState())

    init {
        viewModelScope.launch {
            languageRepository.get().collectLatest { language ->
                intent {
                    reduce {
                        state.copy(
                            japaneseSelected = language == jp.kaleidot725.easycalc.core.domain.model.language.Language.Japanese,
                            englishSelected = language == jp.kaleidot725.easycalc.core.domain.model.language.Language.English,
                            germanSelected = language == jp.kaleidot725.easycalc.core.domain.model.language.Language.German,
                            spainSelected = language == jp.kaleidot725.easycalc.core.domain.model.language.Language.Spain,
                            franceSelected = language == jp.kaleidot725.easycalc.core.domain.model.language.Language.France,
                            indonesiaSelected = language == jp.kaleidot725.easycalc.core.domain.model.language.Language.Indonesia,
                            italySelected = language == jp.kaleidot725.easycalc.core.domain.model.language.Language.Italy,
                            portugalSelected = language == jp.kaleidot725.easycalc.core.domain.model.language.Language.Portugal,
                            thaiSelected = language == jp.kaleidot725.easycalc.core.domain.model.language.Language.Thai,
                            chineseSelected = language == jp.kaleidot725.easycalc.core.domain.model.language.Language.Chinese,
                        )
                    }
                }
            }
        }
    }

    override fun clickLanguage(language: jp.kaleidot725.easycalc.core.domain.model.language.Language) =
        intent {
            languageRepository.update(language)
        }

    override fun popBack() = intent {
        postSideEffect(LanguageEvent.PopBack)
    }
}
