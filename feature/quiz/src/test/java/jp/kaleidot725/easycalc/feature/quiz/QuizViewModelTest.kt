package jp.kaleidot725.easycalc.feature.quiz

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

class QuizViewModelTest {
    private val mathText = MathText.SingleDigitsAddition
    private val mathTexts = MathTextSet(
        additions = MathTexts(value = persistentListOf(mathText)),
        divisions = MathTexts.EMPTY,
        subtractions = MathTexts.EMPTY,
        multiplications = MathTexts.EMPTY,
        multiplicationTable = MathTexts.EMPTY,
    )
    private val textRepository = mock<TextRepository> {
        onBlocking { get() } doReturn mathTexts
    }
    private val quizViewModel = QuizViewModel(textRepository)

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
        val initialState = QuizState()
        val viewModel = quizViewModel.test(initialState)
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }

        // Verify
        viewModel.assert(initialState) {
            states(
                { copy(mathTextSet = mathTexts) }
            )
        }
    }

    @Test
    fun `選択したカテゴリーに応じてイベントが通知されるか`() = runTest {
        // Action
        val initialState = QuizState()
        val viewModel = quizViewModel.test(initialState)
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { clickCategory(MathText.Category.ADDITION) }

        // Verify
        viewModel.assert(initialState) {
            states(
                { copy(mathTextSet = mathTexts) }
            )
            postedSideEffects(
                QuizEvent.ClickCategory(MathText.Category.ADDITION)
            )
        }
    }

    @Test
    fun `選択したテキストに応じてイベントが通知されるか`() = runTest {
        // Action
        val initialState = QuizState()
        val viewModel = quizViewModel.test(initialState)
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { clickText(mathText) }

        // Verify
        viewModel.assert(initialState) {
            states(
                { copy(mathTextSet = mathTexts) }
            )
            postedSideEffects(
                QuizEvent.ClickText(mathText)
            )
        }
    }
}