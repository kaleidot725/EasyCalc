package jp.kaleidot725.easycalc.ui

// import jp.kaleidot725.easycalc.domain.model.question.Question
// import jp.kaleidot725.easycalc.domain.model.question.generator.QuestionGenerator
// import jp.kaleidot725.easycalc.domain.model.question.selector.QuestionSelector
// import jp.kaleidot725.easycalc.domain.model.setting.Setting
// import jp.kaleidot725.easycalc.domain.model.text.MathText
// import jp.kaleidot725.easycalc.domain.model.text.MathTextId
// import jp.kaleidot725.easycalc.domain.repository.SettingRepository
// import jp.kaleidot725.easycalc.domain.repository.TextRepository
// import jp.kaleidot725.easycalc.ui.screen.progress.FocusMode
// import jp.kaleidot725.easycalc.ui.screen.progress.ProgressState
// import jp.kaleidot725.easycalc.ui.screen.progress.ProgressViewModel
// import kotlinx.coroutines.Dispatchers
// import kotlinx.coroutines.flow.flow
// import kotlinx.coroutines.test.resetMain
// import kotlinx.coroutines.test.runTest
// import kotlinx.coroutines.test.setMain
// import org.junit.jupiter.api.AfterEach
// import org.junit.jupiter.api.BeforeEach
// import org.junit.jupiter.api.Test
// import org.mockito.kotlin.doReturn
// import org.mockito.kotlin.mock
// import org.orbitmvi.orbit.test

// class ProgressViewModelTest {
//    private val textId: MathTextId = MathText.SingleDigitsAddition.id
//    private val text: MathText = MathText.SingleDigitsAddition
//    private val question: Question = Question(
//        one = 1.toString(),
//        two = 2.toString(),
//        answer = 3.toString(),
//        category = MathText.Category.ADDITION
//    )
//    private val textRepository: TextRepository = mock {
//        on { getById(textId) } doReturn text
//    }
//    private val questionSelector: QuestionSelector = mock {
//        on { select(text) } doReturn object : QuestionGenerator {
//            override fun generate(): Question = question
//            override fun reset() = Unit
//        }
//    }
//    private val settingRepository: SettingRepository = mock {
//        on { get() } doReturn flow { false }
//    }
//
//    @BeforeEach
//    fun setup() {
//        Dispatchers.setMain(Dispatchers.Unconfined)
//    }
//
//    @AfterEach
//    fun tearDown() {
//        Dispatchers.resetMain()
//    }
//
//    @Test
//    fun `最大値を入力したときにエラーにならないか`() = runTest {
//        val initialState = ProgressState(
//            mathText = text,
//            question = question,
//            textAnswer = "",
//            textProgress = 1,
//            isFailed = false,
//            isSuccess = false,
//            setting = Setting(isBgmMute = false, isEffectMute = false),
//            timeoutProgress = 1f,
//            textRemainder = "",
//            focusMode = FocusMode.ANSWER,
//        )
//        val viewModel = ProgressViewModel(
//            inputDelay = 0,
//            mathTextId = textId,
//            mathTextRepository = textRepository,
//            questionSelector = questionSelector,
//            settingRepository = settingRepository
//        ).test(initialState)
//
//        viewModel.runOnCreate()
//        viewModel.testIntent { onClickNumber(1) }
//        viewModel.testIntent { onClickNumber(2) }
//        viewModel.testIntent { onClickNumber(3) }
//        viewModel.testIntent { onClickNumber(4) }
//        viewModel.testIntent { onClickNumber(5) }
//        viewModel.testIntent { onClickNumber(6) }
//        viewModel.testIntent { onClickNumber(7) }
//        viewModel.testIntent { onClickNumber(8) }
//        viewModel.testIntent { onClickNumber(9) }
//        viewModel.testIntent { onClickNumber(0) }
//        viewModel.testIntent { onClickNumber(1) }
//
//        viewModel.assert(initialState) {
//            states(
//                { initialState.copy(textAnswer = "1") },
//                { initialState.copy(textAnswer = "12") },
//                { initialState.copy(textAnswer = "123") },
//                { initialState.copy(textAnswer = "1234") },
//                { initialState.copy(textAnswer = "12345") },
//                { initialState.copy(textAnswer = "123456") },
//                { initialState.copy(textAnswer = "1234567") },
//                { initialState.copy(textAnswer = "12345678") },
//                { initialState.copy(textAnswer = "123456789") },
//                { initialState.copy(textAnswer = "1234567890") },
//                { initialState.copy(textAnswer = "12345678901") },
//            )
//        }
//    }
// }
