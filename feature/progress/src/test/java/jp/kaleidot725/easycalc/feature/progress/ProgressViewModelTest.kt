package jp.kaleidot725.easycalc.feature.progress

import jp.kaleidot725.easycalc.core.domain.model.question.Question
import jp.kaleidot725.easycalc.core.domain.model.question.generator.QuestionGenerator
import jp.kaleidot725.easycalc.core.domain.model.question.selector.QuestionSelector
import jp.kaleidot725.easycalc.core.domain.model.setting.Setting
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.repository.SettingRepository
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
import org.mockito.kotlin.whenever
import org.orbitmvi.orbit.test

class ProgressViewModelTest {
    private val mathText = MathText.SingleDigitsAddition
    private val question = Question(
        one = "1",
        two = "2",
        answer = "3",
        category = MathText.Category.ADDITION,
        remainder = null
    )
    private val generator = mock<QuestionGenerator> {
        on { generate() } doReturn question
    }
    private val selector = mock<QuestionSelector> {
        on { select(mathText) } doReturn generator
    }
    private val textRepository = mock<TextRepository> {
        onBlocking { getById(mathText.id) } doReturn mathText
    }

    private val setting = Setting(isBgmMute = false, isEffectMute = false)
    private val settingRepository = mock<SettingRepository> {}

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `初期化したときに設定が更新されるか`() = runTest {
        val initialState = ProgressState.createInitState(mathText, generator)
        whenever(settingRepository.get()).doReturn(setting)
        val viewModel = ProgressViewModel(
            settingRepository = settingRepository,
            mathTextId = mathText.id,
            mathTextRepository = textRepository,
            questionSelector = selector,
        ).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.assert(initialState) {
            states(
                { copy(setting = setting) }
            )
            postedSideEffects(
                ProgressEvent.StartBGM,
            )
        }
    }

    @Test
    fun `BGMをミュートにできるか`() = runTest {
        val initialState = ProgressState.createInitState(mathText, generator)
        whenever(settingRepository.get())
            .thenReturn(setting)
            .thenReturn(setting.copy(isBgmMute = true))

        val viewModel = ProgressViewModel(
            settingRepository = settingRepository,
            mathTextId = mathText.id,
            mathTextRepository = textRepository,
            questionSelector = selector,
        ).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { changeBgmMute(isMute = true) }
        viewModel.assert(initialState) {
            states(
                { copy(setting = setting) },
                { copy(setting = setting.copy(isBgmMute = true)) }
            )
            postedSideEffects(
                ProgressEvent.StartBGM,
                ProgressEvent.StopBGM
            )
        }
    }

    @Test
    fun `効果音をミュートにできるか`() = runTest {
        val initialState = ProgressState.createInitState(mathText, generator)
        whenever(settingRepository.get())
            .thenReturn(setting)
            .thenReturn(setting.copy(isEffectMute = true))

        val viewModel = ProgressViewModel(
            settingRepository = settingRepository,
            mathTextId = mathText.id,
            mathTextRepository = textRepository,
            questionSelector = selector,
        ).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { changeEffectMute(isMute = true) }
        viewModel.testIntent { clickNumber(3) }
        viewModel.assert(initialState) {
            states(
                { copy(setting = setting) },
                { copy(setting = setting.copy(isEffectMute = true)) },
                { copy(textAnswer = "3") },
                { copy(isSuccess = true) }
            )
            postedSideEffects(
                ProgressEvent.StartBGM,
                ProgressEvent.StartBGM,
                ProgressEvent.Input(isMute = true),
                ProgressEvent.Success(isMute = true)
            )
        }
    }
}
