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
    private val mathText = MathText.SingleDigitsAddition
    private val mathTexts = MathTexts(value = persistentListOf(mathText))
    private val textRepository = mock<TextRepository> {
        onBlocking { getHistory() } doReturn mathTexts
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
        val initialState = HistoryState(MathTexts.EMPTY)
        val viewModel = HistoryViewModel(textRepository = textRepository).test(initialState)

        viewModel.runOnCreate()
        viewModel.assert(initialState) {
            states(
                { initialState.copy(mathTexts = mathTexts) }
            )
        }
    }

    @Test
    fun `クリックしたときにClickTextイベントが発生するか`() = runTest {
        val initialState = HistoryState(MathTexts.EMPTY)
        val viewModel = HistoryViewModel(textRepository = textRepository).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { clickText(mathText) }
        viewModel.assert(initialState) {
            postedSideEffects(
                HistoryEvent.ClickText(mathText)
            )
            states(
                { initialState.copy(mathTexts = mathTexts) }
            )
        }
    }

    @Test
    fun `戻るときにPopBackイベントが発生するか`() = runTest {
        val initialState = HistoryState(MathTexts.EMPTY)
        val viewModel = HistoryViewModel(textRepository = textRepository).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { popBack() }
        viewModel.assert(initialState) {
            postedSideEffects(
                HistoryEvent.PopBack
            )
            states(
                { initialState.copy(mathTexts = mathTexts) }
            )
        }
    }
}