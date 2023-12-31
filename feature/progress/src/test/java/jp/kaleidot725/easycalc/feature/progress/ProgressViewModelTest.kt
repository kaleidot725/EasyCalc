package jp.kaleidot725.easycalc.feature.progress

import jp.kaleidot725.easycalc.core.domain.model.question.Answer
import jp.kaleidot725.easycalc.core.domain.model.question.QAList
import jp.kaleidot725.easycalc.core.domain.model.question.Question
import jp.kaleidot725.easycalc.core.domain.model.question.generator.QuestionGenerator
import jp.kaleidot725.easycalc.core.domain.model.question.selector.QuestionSelector
import jp.kaleidot725.easycalc.core.domain.model.setting.Setting
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.repository.SettingRepository
import jp.kaleidot725.easycalc.core.repository.TextRepository
import jp.kaleidot725.easycalc.feature.progress.model.FocusMode
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
    private val setting = Setting(isBgmMute = false, isEffectMute = false)
    private val settingRepository = mock<SettingRepository> {}

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

    private val initialState = ProgressState.createInitState(mathText, generator)
    private val initialViewModel
        get() = ProgressViewModel(
            settingRepository = settingRepository,
            mathTextId = mathText.id,
            mathTextRepository = textRepository,
            questionSelector = selector,
        ).test(initialState)

    private val remainMathText = MathText.SingleDigitsDivisionRemainder
    private val remainQuestion = Question(
        one = "10",
        two = "3",
        answer = "3",
        category = MathText.Category.DIVISION,
        remainder = "1"
    )
    private val remainGenerator = mock<QuestionGenerator> {
        on { generate() } doReturn remainQuestion
    }
    private val remainSelector = mock<QuestionSelector> {
        on { select(remainMathText) } doReturn remainGenerator
    }

    private val remainTextRepository = mock<TextRepository> {
        onBlocking { getById(remainMathText.id) } doReturn remainMathText
    }

    private val remainInitialState = ProgressState.createInitState(remainMathText, remainGenerator)
    private val remainInitialViewModel
        get() = ProgressViewModel(
            settingRepository = settingRepository,
            mathTextId = remainMathText.id,
            mathTextRepository = remainTextRepository,
            questionSelector = remainSelector,
        ).test(remainInitialState)

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
        // Setup
        whenever(settingRepository.get())
            .thenReturn(setting)

        // Action
        val viewModel = initialViewModel
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }

        // Verify
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
        // Setup
        whenever(settingRepository.get())
            .thenReturn(setting)
            .thenReturn(setting.copy(isBgmMute = true))

        // Action
        val viewModel = initialViewModel
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { changeBgmMute(isMute = true) }

        // Verify
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
        // Setup
        whenever(settingRepository.get())
            .thenReturn(setting)
            .thenReturn(setting.copy(isEffectMute = true))

        // Action
        val viewModel = initialViewModel
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { changeEffectMute(isMute = true) }
        viewModel.testIntent { clickNumber(3) }
        viewModel.assert(initialState) {
            states(
                { copy(setting = setting) },
                { copy(setting = setting.copy(isEffectMute = true)) },
                { copy(textAnswer = "3") },
                { copy(isSuccess = true) },
                { copy(textProgress = 2, textAnswer = "", isSuccess = false) }
            )
            postedSideEffects(
                ProgressEvent.StartBGM,
                ProgressEvent.StartBGM,
                ProgressEvent.Input(isMute = true),
                ProgressEvent.Success(isMute = true)
            )
        }
    }

    @Test
    fun `計算を完了できるか`() = runTest {
        // Setup
        whenever(settingRepository.get())
            .thenReturn(setting)

        // Action
        val viewModel = initialViewModel
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { clickNumber(3) }
        viewModel.assert(initialState) {
            states(
                { copy(setting = setting) },
                { copy(textAnswer = "3") },
                { copy(isSuccess = true) },
                { copy(textProgress = 2, textAnswer = "", isSuccess = false) }
            )
            postedSideEffects(
                ProgressEvent.StartBGM,
                ProgressEvent.Input(isMute = false),
                ProgressEvent.Success(isMute = false),
            )
        }
    }

    @Test
    fun `計算の誤りを修正できるか`() = runTest {
        // Setup
        whenever(settingRepository.get())
            .thenReturn(setting)

        // Action
        val viewModel = initialViewModel
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { clickNumber(7) }
        viewModel.testIntent { delete() }

        // Verify
        viewModel.assert(initialState) {
            states(
                { copy(setting = setting) },
                { copy(textAnswer = "7") },
                { copy(textAnswer = "") }
            )
            postedSideEffects(
                ProgressEvent.StartBGM,
                ProgressEvent.Input(isMute = false),
                ProgressEvent.Input(isMute = false)
            )
        }
    }

    @Test
    fun `余りのある計算を完了できるか`() = runTest {
        // Setup
        whenever(settingRepository.get()).thenReturn(setting)

        // Action
        val viewModel = remainInitialViewModel
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { clickNumber(3) }
        viewModel.testIntent { changeFocus(FocusMode.REMAINDER) }
        viewModel.testIntent { clickNumber(1) }

        // Verify
        viewModel.assert(remainInitialState) {
            states(
                { copy(setting = setting) },
                { copy(textAnswer = "3") },
                { copy(focusMode = FocusMode.REMAINDER) },
                { copy(textAnswer = "3", textRemainder = "1") },
                { copy(isSuccess = true) },
                {
                    copy(
                        textProgress = 2,
                        textAnswer = "",
                        textRemainder = "",
                        isSuccess = false,
                        focusMode = FocusMode.ANSWER
                    )
                }
            )
            postedSideEffects(
                ProgressEvent.StartBGM,
                ProgressEvent.Input(isMute = false),
                ProgressEvent.Input(isMute = false),
                ProgressEvent.Success(isMute = false)
            )
        }
    }

    @Test
    fun `余りのある計算の誤りを修正できるか`() = runTest {
        // Setup
        whenever(settingRepository.get()).thenReturn(setting)

        // Action
        val viewModel = remainInitialViewModel
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { clickNumber(3) }
        viewModel.testIntent { changeFocus(FocusMode.REMAINDER) }
        viewModel.testIntent { clickNumber(2) }
        viewModel.testIntent { delete() }

        // Verify
        viewModel.assert(remainInitialState) {
            states(
                { copy(setting = setting) },
                { copy(textAnswer = "3") },
                { copy(focusMode = FocusMode.REMAINDER) },
                { copy(textAnswer = "3", textRemainder = "2") },
                { copy(textAnswer = "3", textRemainder = "") },
            )
            postedSideEffects(
                ProgressEvent.StartBGM,
                ProgressEvent.Input(isMute = false),
                ProgressEvent.Input(isMute = false),
                ProgressEvent.Input(isMute = false),
            )
        }
    }

    @Test
    fun `余りのある計算でフォーカスを変更できるか`() = runTest {
        // Setup
        whenever(settingRepository.get()).thenReturn(setting)

        // Action
        val viewModel = remainInitialViewModel
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { changeFocus(FocusMode.REMAINDER) }
        viewModel.testIntent { changeFocus(FocusMode.ANSWER) }

        // Verify
        viewModel.assert(remainInitialState) {
            states(
                { copy(setting = setting) },
                { copy(focusMode = FocusMode.REMAINDER) },
                { copy(focusMode = FocusMode.ANSWER) },
            )
            postedSideEffects(
                ProgressEvent.StartBGM,
            )
        }
    }

    @Test
    fun `タイムアウトの進捗度を更新できるか`() = runTest {
        // Setup
        whenever(settingRepository.get()).thenReturn(setting)

        // Action
        val viewModel = initialViewModel
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { updateTimeoutProgress(1.0f) }

        // Verify
        viewModel.assert(initialState) {
            states(
                { copy(setting = setting) },
                { copy(timeoutProgress = 1.0f) },
            )
            postedSideEffects(
                ProgressEvent.StartBGM,
            )
        }
    }

    @Test
    fun `中断することができるか`() = runTest {
        // Setup
        whenever(settingRepository.get()).thenReturn(setting)

        // Action
        val viewModel = initialViewModel
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { interrupt() }

        // Verify
        viewModel.assert(initialState) {
            states(
                { copy(setting = setting) },
            )
            postedSideEffects(
                ProgressEvent.StartBGM,
                ProgressEvent.Interrupted(isMute = false),
            )
        }
    }

    @Test
    fun `スキップすることができるか`() = runTest {
        // Setup
        whenever(settingRepository.get()).thenReturn(setting)

        // Action
        val viewModel = initialViewModel
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { skip() }

        // Verify
        viewModel.assert(initialState) {
            states(
                { copy(setting = setting) },
                { copy(isFailed = true) },
                { copy(textProgress = 2, textAnswer = "", isFailed = false) }
            )
            postedSideEffects(
                ProgressEvent.StartBGM,
                ProgressEvent.Failed(isMute = false),
            )
        }
    }

    @Test
    fun `入力内容をクリアすることができるか`() = runTest {
        // Setup
        whenever(settingRepository.get()).thenReturn(setting)

        // Action
        val viewModel = remainInitialViewModel
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { changeFocus(FocusMode.REMAINDER) }
        viewModel.testIntent { clickNumber(1) }
        viewModel.testIntent { clickNumber(1) }
        viewModel.testIntent { clickNumber(1) }
        viewModel.testIntent { clear() }
        viewModel.testIntent { changeFocus(FocusMode.ANSWER) }
        viewModel.testIntent { clickNumber(1) }
        viewModel.testIntent { clickNumber(1) }
        viewModel.testIntent { clickNumber(1) }
        viewModel.testIntent { clear() }

        // Verify
        viewModel.assert(remainInitialState) {
            states(
                { copy(setting = setting) },
                { copy(focusMode = FocusMode.REMAINDER) },
                { copy(textRemainder = "1") },
                { copy(textRemainder = "11") },
                { copy(textRemainder = "111") },
                { copy(textRemainder = "") },
                { copy(focusMode = FocusMode.ANSWER) },
                { copy(textAnswer = "1") },
                { copy(textAnswer = "11") },
                { copy(textAnswer = "111") },
                { copy(textAnswer = "") },
            )
            postedSideEffects(
                ProgressEvent.StartBGM,
                ProgressEvent.Input(isMute = false),
                ProgressEvent.Input(isMute = false),
                ProgressEvent.Input(isMute = false),
                ProgressEvent.Clear(isMute = false),
                ProgressEvent.Input(isMute = false),
                ProgressEvent.Input(isMute = false),
                ProgressEvent.Input(isMute = false),
                ProgressEvent.Clear(isMute = false),
            )
        }
    }

    @Test
    fun `出題を完了することができるか`() = runTest {
        // Setup
        whenever(settingRepository.get()).thenReturn(setting)

        // Action
        val viewModel = initialViewModel
        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        for (i in 0..mathText.count) {
            viewModel.testIntent { skip() }
        }

        // Verify
        viewModel.assert(initialState) {
            states(
                { copy(setting = setting) },
                { copy(isFailed = true) },
                { copy(textProgress = 2, textAnswer = "", isFailed = false) },
                { copy(isFailed = true) },
                { copy(textProgress = 3, textAnswer = "", isFailed = false) },
                { copy(isFailed = true) },
                { copy(textProgress = 4, textAnswer = "", isFailed = false) },
                { copy(isFailed = true) },
                { copy(textProgress = 5, textAnswer = "", isFailed = false) },
                { copy(isFailed = true) },
                { copy(textProgress = 6, textAnswer = "", isFailed = false) },
                { copy(isFailed = true) },
                { copy(textProgress = 7, textAnswer = "", isFailed = false) },
                { copy(isFailed = true) },
                { copy(textProgress = 8, textAnswer = "", isFailed = false) },
                { copy(isFailed = true) },
                { copy(textProgress = 9, textAnswer = "", isFailed = false) },
                { copy(isFailed = true) },
                { copy(textProgress = 10, textAnswer = "", isFailed = false) },
                { copy(isFailed = true) },
            )
            postedSideEffects(
                ProgressEvent.StartBGM,
                ProgressEvent.Failed(isMute = false),
                ProgressEvent.Failed(isMute = false),
                ProgressEvent.Failed(isMute = false),
                ProgressEvent.Failed(isMute = false),
                ProgressEvent.Failed(isMute = false),
                ProgressEvent.Failed(isMute = false),
                ProgressEvent.Failed(isMute = false),
                ProgressEvent.Failed(isMute = false),
                ProgressEvent.Failed(isMute = false),
                ProgressEvent.Failed(isMute = false),
                ProgressEvent.StopBGM,
                ProgressEvent.Finish(
                    isMute = false,
                    mathText = mathText,
                    qalist = QAList().apply {
                        for (i in 0..<mathText.count) {
                            add(question = question, Answer.Failed("", ""))
                        }
                    }
                ),
            )
        }
    }
}
