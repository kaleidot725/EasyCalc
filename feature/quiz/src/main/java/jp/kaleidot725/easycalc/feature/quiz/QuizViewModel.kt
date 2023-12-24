package jp.kaleidot725.easycalc.feature.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.repository.TextRepository
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class QuizViewModel(
    private val textRepository: TextRepository,
) : ContainerHost<QuizState, QuizEvent>, QuizAction, ViewModel() {
    override val container = container<QuizState, QuizEvent>(QuizState())

    init {
        viewModelScope.launch {
            val textSet = textRepository.get()
            intent { reduce { state.copy(mathTextSet = textSet) } }
        }
    }

    override fun clickCategory(category: MathText.Category) = intent {
        postSideEffect(QuizEvent.ClickCategory(category))
    }

    override fun clickText(mathText: MathText) = intent {
        postSideEffect(QuizEvent.ClickText(mathText))
    }
}
