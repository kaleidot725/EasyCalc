package jp.kaleidot725.easycalc.feature.start

import androidx.lifecycle.ViewModel
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextId
import jp.kaleidot725.easycalc.core.repository.TextRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

private typealias PrivateIntent = SimpleSyntax<StartState, StartEvent>

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

    override fun refresh() = intent { updateFavorite() }

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
        updateFavorite()
    }

    private suspend fun PrivateIntent.updateFavorite() {
        val isFavorite = mathTextRepository.isFavorite(mathTextId)
        reduce {
            state.copy(isFavorite = isFavorite)
        }
    }

    override fun popBack() = intent {
        postSideEffect(StartEvent.PopBack)
    }
}
