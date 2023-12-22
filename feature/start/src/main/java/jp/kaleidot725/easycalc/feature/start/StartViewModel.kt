package jp.kaleidot725.easycalc.feature.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextId
import jp.kaleidot725.easycalc.core.domain.repository.TextRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class StartViewModel(
    private val mathTextId: MathTextId,
    private val mathTextRepository: TextRepository
) : ContainerHost<StartState, StartEvent>, StartAction, ViewModel() {
    override val container = container<StartState, StartEvent>(
        StartState(
            mathText = mathTextRepository.getById(mathTextId),
            isFavorite = false
        )
    )

    init {
        viewModelScope.launch {
            mathTextRepository.isFavorite(mathTextId).collectLatest {
                intent {
                    reduce {
                        state.copy(isFavorite = it)
                    }
                }
            }
        }
    }

    override fun startCalculation() = intent {
        mathTextRepository.addHistory(mathTextId)
        postSideEffect(StartEvent.ClickStart(state.mathText))
    }

    override fun toggleFavorite() = intent {
        if (state.isFavorite) {
            mathTextRepository.deleteFavorite(mathTextId)
        } else {
            mathTextRepository.addFavorite(mathTextId)
        }
    }

    override fun popBack() = intent {
        postSideEffect(StartEvent.PopBack)
    }
}
