package jp.kaleidot725.easycalc.feature.start

import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.repository.TextRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.orbitmvi.orbit.test

class StartViewModelTest {
    private val mathText = MathText.SingleDigitsAddition
    private val textRepository = mock<TextRepository> {
        onBlocking { getById(mathText.id) } doReturn mathText
    }

    private val startViewModel = StartViewModel(
        mathTextId = mathText.id,
        mathTextRepository = textRepository,
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
        // Setup
        whenever(textRepository.isFavorite(mathText.id)).thenReturn(true)

        // Action
        val initialState = StartState(mathText = mathText, isFavorite = false)
        val viewModel = startViewModel.test(initialState)
        viewModel.testIntent { refresh() }

        // Verify
        viewModel.assert(initialState) {
            states({ copy(isFavorite = true) })
        }
    }

    @Test
    fun `計算を開始できるか`() = runTest {
        // Setup
        whenever(textRepository.isFavorite(mathText.id)).thenReturn(true)

        // Action
        val initialState = StartState(mathText = mathText, isFavorite = false)
        val viewModel = startViewModel.test(initialState)
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { startCalculation() }

        // Verify
        viewModel.assert(initialState) {
            states({ copy(isFavorite = true) })
            postedSideEffects(StartEvent.ClickStart(mathText))
        }
        verify(textRepository).addHistory(mathText.id)
    }

    @Test
    fun `お気に入りに登録できるか`() = runTest {
        // Setup
        whenever(textRepository.isFavorite(mathText.id))
            .thenReturn(false)
            .thenReturn(true)

        // Action
        val initialState = StartState(mathText = mathText, isFavorite = false)
        val viewModel = startViewModel.test(initialState)
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { toggleFavorite() }

        // Verify
        viewModel.assert(initialState) {
            states(
                { copy(isFavorite = false) },
                { copy(isFavorite = true) },
            )
        }
        verify(textRepository).addFavorite(mathText.id)
    }

    @Test
    fun `お気に入りを解除できるか`() = runTest {
        // Setup
        whenever(textRepository.isFavorite(mathText.id))
            .thenReturn(true)
            .thenReturn(false)

        // Action
        val initialState = StartState(mathText = mathText, isFavorite = true)
        val viewModel = startViewModel.test(initialState)
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { toggleFavorite() }

        // Verify
        viewModel.assert(initialState) {
            states(
                { copy(isFavorite = true) },
                { copy(isFavorite = false) },
            )
        }
        verify(textRepository).deleteFavorite(mathText.id)
    }

    @Test
    fun `戻るイベントが通知されるか`() = runTest {
        // Setup
        whenever(textRepository.isFavorite(mathText.id)).thenReturn(true)

        // Action
        val initialState = StartState(mathText = mathText, isFavorite = false)
        val viewModel = startViewModel.test(initialState)
        viewModel.testIntent { refresh() }
        viewModel.testIntent { popBack() }

        // Verify
        viewModel.assert(initialState) {
            states({ copy(isFavorite = true) })
            postedSideEffects(StartEvent.PopBack)
        }
    }
}