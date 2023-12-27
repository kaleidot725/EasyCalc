package jp.kaleidot725.easycalc.feature.history

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

class HistoryViewModel(
    private val textRepository: TextRepository
) : ContainerHost<HistoryState, HistoryEvent>, HistoryAction, ViewModel() {
    override val container = container<HistoryState, HistoryEvent>(HistoryState())

    override fun refresh() {
        viewModelScope.launch {
            val histories = textRepository.getHistory()
            intent {
                reduce { state.copy(mathTexts = histories) }
            }
        }
    }

    override fun clickText(mathText: MathText) = intent {
        postSideEffect(HistoryEvent.ClickText(mathText))
    }

    override fun popBack() = intent {
        postSideEffect(HistoryEvent.PopBack)
    }
}
