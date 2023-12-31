package jp.kaleidot725.easycalc.feature.category

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

class CategoryViewModel(
    private val category: MathText.Category,
    private val textRepository: TextRepository,
) : ContainerHost<CategoryState, CategoryEvent>, CategoryAction, ViewModel() {
    override val container = container<CategoryState, CategoryEvent>(CategoryState(category))

    override fun refresh() {
        viewModelScope.launch {
            val textSets = textRepository.get()
            intent {
                reduce {
                    state.copy(
                        mathTexts = textSets.getCategoryMathTexts(category)
                    )
                }
            }
        }
    }

    override fun clickText(mathText: MathText) = intent {
        postSideEffect(CategoryEvent.ClickText(mathText))
    }

    override fun popBack() = intent {
        postSideEffect(CategoryEvent.PopBack)
    }
}
