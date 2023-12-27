package jp.kaleidot725.easycalc.feature.category

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

class CategoryViewModelTest {
    private val category = MathText.Category.ADDITION
    private val mathTexts = MathTextSet(
        additions = MathTexts(value = persistentListOf(MathText.SingleDigitsAddition)),
        divisions = MathTexts.EMPTY,
        subtractions = MathTexts.EMPTY,
        multiplications = MathTexts.EMPTY,
        multiplicationTable = MathTexts.EMPTY,
    )
    private val textRepository = mock<TextRepository> {
        onBlocking { get() } doReturn mathTexts
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
        val initialState = CategoryState(category)
        val viewModel = CategoryViewModel(
            category = category,
            textRepository = textRepository
        ).test(initialState)

        viewModel.runOnCreate()
        viewModel.assert(initialState) {
            states(
                { initialState.copy(mathTexts = mathTexts) }
            )
        }
    }

    @Test
    fun `クリックしたときにClickTextイベントが発生するか`() = runTest {
        val initialState = CategoryState(category)
        val clickTarget = mathTexts.additions.value.first()
        val viewModel = CategoryViewModel(
            category = category,
            textRepository = textRepository
        ).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { clickText(clickTarget) }
        viewModel.assert(initialState) {
            postedSideEffects(
                CategoryEvent.ClickText(clickTarget),
            )
            states(
                { initialState.copy(mathTexts = mathTexts) }
            )
        }
    }

    @Test
    fun `戻るときにPopBackイベントが発生するか`() = runTest {
        val initialState = CategoryState(category)
        val viewModel = CategoryViewModel(
            category = category,
            textRepository = textRepository
        ).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { popBack() }
        viewModel.assert(initialState) {
            postedSideEffects(
                CategoryEvent.PopBack,
            )
            states(
                { initialState.copy(mathTexts = mathTexts) }
            )
        }
    }
}
