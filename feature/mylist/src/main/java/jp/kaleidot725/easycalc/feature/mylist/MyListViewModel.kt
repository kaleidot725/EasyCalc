package jp.kaleidot725.easycalc.feature.mylist

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

class MyListViewModel(
    private val textRepository: TextRepository,
) : ContainerHost<MyListState, MyListEvent>, MyListAction, ViewModel() {
    override val container = container<MyListState, MyListEvent>(MyListState())

    init {
        viewModelScope.launch {
            val favorite = textRepository.getFavorite()
            intent {
                reduce {
                    state.copy(mathTexts = favorite)
                }
            }
        }
    }

    override fun clickText(mathText: MathText) = intent {
        postSideEffect(MyListEvent.ClickText(mathText))
    }

    override fun popBack() = intent {
        postSideEffect(MyListEvent.PopBack)
    }
}
