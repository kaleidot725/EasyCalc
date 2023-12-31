package jp.kaleidot725.easycalc.feature.mylist

import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextSet
import jp.kaleidot725.easycalc.core.domain.model.text.MathTexts
import jp.kaleidot725.easycalc.core.repository.TextRepository
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.orbitmvi.orbit.test

class MyListViewModelTest {
    private val firstMathTexts = MathTextSet(
        additions = MathTexts(value = persistentListOf(MathText.SingleDigitsAddition)),
        divisions = MathTexts.EMPTY,
        subtractions = MathTexts.EMPTY,
        multiplications = MathTexts.EMPTY,
        multiplicationTable = MathTexts.EMPTY,
    )
    private val textRepository = mock<TextRepository> {
        onBlocking { getFavorite() } doReturn firstMathTexts.additions
    }

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `初期化したときにテキストが通知されるか`() = runTest {
        val initialState = MyListState()
        val viewModel = MyListViewModel(textRepository = textRepository).test(initialState)
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.assert(initialState) {
            states(
                { copy(mathTexts = firstMathTexts.additions) }
            )
        }
    }

    @Test
    fun `クリックしたときにClickTextイベントが発生するか`() = runTest {
        val initialState = MyListState()
        val viewModel = MyListViewModel(textRepository = textRepository).test(initialState)
        val clickTarget = firstMathTexts.additions.value.first()
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { clickText(clickTarget) }
        viewModel.assert(initialState) {
            postedSideEffects(
                MyListEvent.ClickText(clickTarget),
            )
            states(
                { copy(mathTexts = firstMathTexts.additions) }
            )
        }
    }

    @Test
    fun `戻るときにPopBackイベントが発生するか`() = runTest {
        val initialState = MyListState()
        val viewModel = MyListViewModel(textRepository = textRepository).test(initialState)
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { popBack() }
        viewModel.assert(initialState) {
            postedSideEffects(
                MyListEvent.PopBack,
            )
            states(
                { copy(mathTexts = firstMathTexts.additions) }
            )
        }
    }
}
