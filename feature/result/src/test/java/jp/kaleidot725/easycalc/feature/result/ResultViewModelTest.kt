package jp.kaleidot725.easycalc.feature.result

import jp.kaleidot725.easycalc.core.domain.model.question.QAList
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.repository.StatsRepository
import jp.kaleidot725.easycalc.core.repository.TextRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.orbitmvi.orbit.test

class ResultViewModelTest {
    private val mathText = MathText.SingleDigitsAddition
    private val qalist = QAList()
    private val textRepository = mock<TextRepository> {
        onBlocking { getById(mathText.id) } doReturn mathText
    }
    private val statsRepository = mock<StatsRepository> {}

    private val resultViewModel = ResultViewModel(
        mathTextId = mathText.id,
        qaList = qalist,
        mathTextRepository = textRepository,
        statsRepository = statsRepository
    )

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `初期化したときにデータが更新されるか`() = runTest {
        // Action
        val initialState = ResultState(mathText = mathText, qaList = qalist)
        val viewModel = resultViewModel.test(initialState)
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }

        // Verify
        verify(statsRepository).addTodayStats(any(), any())
    }

    @Test
    fun `終了イベントが通知されるか`() = runTest {
        // Action
        val initialState = ResultState(mathText = mathText, qaList = qalist)
        val viewModel = resultViewModel.test(initialState)
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { finish() }

        // Verify
        verify(statsRepository).addTodayStats(any(), any())
        viewModel.assert(initialState) {
            postedSideEffects(ResultEvent.Finish)
        }
    }

    @Test
    fun `リトライイベントが通知されるか`() = runTest {
        // Action
        val initialState = ResultState(mathText = mathText, qaList = qalist)
        val viewModel = resultViewModel.test(initialState)
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { retry() }

        // Verify
        verify(statsRepository).addTodayStats(any(), any())
        viewModel.assert(initialState) {
            postedSideEffects(ResultEvent.Retry(mathText = mathText))
        }
    }

    @Test
    fun `戻るイベントが通知されるか`() = runTest {
        // Action
        val initialState = ResultState(mathText = mathText, qaList = qalist)
        val viewModel = resultViewModel.test(initialState)
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { popBack() }

        // Verify
        verify(statsRepository).addTodayStats(any(), any())
        viewModel.assert(initialState) {
            postedSideEffects(ResultEvent.PopBack)
        }
    }
}
