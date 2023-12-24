package jp.kaleidot725.easycalc.feature.setting.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.easycalc.core.domain.model.language.Language
import jp.kaleidot725.easycalc.core.repository.LanguageRepository
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
                            japaneseSelected = language == Language.Japanese,
                            englishSelected = language == Language.English,
                            germanSelected = language == Language.German,
                            spainSelected = language == Language.Spain,
                            franceSelected = language == Language.France,
                            indonesiaSelected = language == Language.Indonesia,
                            italySelected = language == Language.Italy,
                            portugalSelected = language == Language.Portugal,
                            thaiSelected = language == Language.Thai,
                            chineseSelected = language == Language.Chinese,
                        )
                    }
                }
            }
        }
    }

    override fun clickLanguage(language: Language) =
        intent {
            languageRepository.update(language)
        }

    override fun popBack() = intent {
        postSideEffect(LanguageEvent.PopBack)
    }
}
