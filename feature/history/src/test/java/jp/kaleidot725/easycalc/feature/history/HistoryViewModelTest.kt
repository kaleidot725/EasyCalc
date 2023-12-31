package jp.kaleidot725.easycalc.feature.history

import jp.kaleidot725.easycalc.core.domain.model.text.MathText
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

class HistoryViewModelTest {
    private val firstMathText = MathText.SingleDigitsAddition
    private val firstMathTexts = MathTexts(value = persistentListOf(firstMathText))
    private val textRepository = mock<TextRepository> {
        onBlocking { getHistory() } doReturn firstMathTexts
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
    fun `初期化したときに履歴が通知されるか`() = runTest {
        val initialState = HistoryState()
        val viewModel = HistoryViewModel(textRepository = textRepository).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.assert(initialState) {
            states(
                { copy(mathTexts = firstMathTexts) }
            )
        }
    }

    @Test
    fun `クリックしたときにClickTextイベントが発生するか`() = runTest {
        val initialState = HistoryState()
        val viewModel = HistoryViewModel(textRepository = textRepository).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { clickText(firstMathText) }
        viewModel.assert(initialState) {
            postedSideEffects(
                HistoryEvent.ClickText(firstMathText)
            )
            states(
                { copy(mathTexts = firstMathTexts) }
            )
        }
    }

    @Test
    fun `戻るときにPopBackイベントが発生するか`() = runTest {
        val initialState = HistoryState()
        val viewModel = HistoryViewModel(textRepository = textRepository).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { popBack() }
        viewModel.assert(initialState) {
            postedSideEffects(
                HistoryEvent.PopBack
            )
            states(
                { copy(mathTexts = firstMathTexts) }
            )
        }
    }
}
