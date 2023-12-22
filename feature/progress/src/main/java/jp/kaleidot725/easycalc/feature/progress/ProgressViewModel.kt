package jp.kaleidot725.easycalc.feature.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.easycalc.core.domain.model.question.Answer
import jp.kaleidot725.easycalc.core.domain.model.question.QAList
import jp.kaleidot725.easycalc.core.domain.model.question.selector.QuestionSelector
import jp.kaleidot725.easycalc.core.domain.model.setting.Setting
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextId
import jp.kaleidot725.easycalc.core.domain.repository.SettingRepository
import jp.kaleidot725.easycalc.core.domain.repository.TextRepository
import jp.kaleidot725.easycalc.feature.progress.model.FocusMode
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProgressViewModel(
    private val settingRepository: SettingRepository,
    mathTextId: MathTextId,
    mathTextRepository: TextRepository,
    questionSelector: QuestionSelector,
) : ContainerHost<ProgressState, ProgressEvent>, ProgressAction, ViewModel() {
    private val text = mathTextRepository.getById(mathTextId)
    private val generator = questionSelector.select(text)
    private val qaList = QAList().apply { start() }

    override val container = container<ProgressState, ProgressEvent>(
        ProgressState(
            mathText = text,
            textProgress = 1,
            textAnswer = "",
            textRemainder = "",
            question = generator.generate(),
            isSuccess = false,
            isFailed = false,
            setting = Setting(isBgmMute = false, isEffectMute = false),
            focusMode = FocusMode.ANSWER,
            timeoutProgress = 1f,
        )
    )

    init {
        viewModelScope.launch {
            settingRepository.get().collectLatest {
                intent {
                    reduce {
                        state.copy(setting = it)
                    }
                }

                intent {
                    if (it.isBgmMute) {
                        postSideEffect(ProgressEvent.StopBGM)
                    } else {
                        postSideEffect(ProgressEvent.StartBGM)
                    }
                }
            }
        }
    }

    override fun onChangeEffectMute(isMute: Boolean) = intent {
        settingRepository.update(state.setting.copy(isEffectMute = isMute))
    }

    override fun onChangeBgmMute(isMute: Boolean) = intent {
        settingRepository.update(state.setting.copy(isBgmMute = isMute))
    }

    override fun onClickNumber(number: Int) = intent {
        if (state.isSuccess || state.isFailed) return@intent

        postSideEffect(ProgressEvent.Input(state.setting.isEffectMute))

        when (state.focusMode) {
            FocusMode.ANSWER -> {
                val current = state.textAnswer
                val next = current + number.toString()
                reduce { state.copy(textAnswer = next) }

                val isInvalidAnswer = state.question.answer != next
                val isInvalidRemainder =
                    state.question.hasRemainder && state.question.remainder != state.textRemainder
                if (isInvalidAnswer || isInvalidRemainder) return@intent
            }

            FocusMode.REMAINDER -> {
                val current = state.textRemainder
                val next = current + number.toString()
                reduce { state.copy(textRemainder = next) }

                val isInvalidAnswer = state.question.answer != state.textAnswer
                val isInvalidRemainder =
                    state.question.hasRemainder && state.question.remainder != next
                if (isInvalidAnswer || isInvalidRemainder) return@intent
            }
        }

        qaList.add(
            state.question,
            Answer.Success(
                state.textAnswer,
                state.textRemainder
            )
        )

        reduce {
            state.copy(
                textProgress = state.textProgress,
                isSuccess = true
            )
        }
        postSideEffect(ProgressEvent.Success(state.setting.isEffectMute))
        delay(500)

        createNextQuestion()
    }

    override fun onChangeFocus(focusMode: FocusMode) = intent {
        reduce { state.copy(focusMode = focusMode) }
    }

    override fun onSkip() {
        onTimeout()
    }

    override fun onTimeout() = intent {
        if (state.isSuccess || state.isFailed) return@intent
        qaList.add(
            state.question,
            Answer.Failed(
                state.textAnswer,
                state.textRemainder
            )
        )

        reduce {
            state.copy(
                textProgress = state.textProgress,
                isFailed = true
            )
        }

        postSideEffect(ProgressEvent.Failed(state.setting.isEffectMute))
        delay(500)

        createNextQuestion()
    }

    override fun onDelete() = intent {
        if (state.isSuccess || state.isFailed) return@intent

        postSideEffect(ProgressEvent.Input(state.setting.isEffectMute))

        when (state.focusMode) {
            FocusMode.ANSWER -> {
                val current = state.textAnswer
                if (current.isEmpty()) return@intent

                val startIndex = 0
                val endIndex = current.lastIndex
                val textAnswer = current.substring(startIndex, endIndex)
                reduce { state.copy(textAnswer = textAnswer) }

                val isInvalidAnswer = state.question.answer != textAnswer
                val isInvalidRemainder =
                    state.question.hasRemainder && state.question.remainder != state.textRemainder
                if (isInvalidAnswer || isInvalidRemainder) return@intent
            }

            FocusMode.REMAINDER -> {
                val current = state.textRemainder
                if (current.isEmpty()) return@intent

                val startIndex = 0
                val endIndex = current.lastIndex
                val textRemainder = current.substring(startIndex, endIndex)
                reduce { state.copy(textRemainder = textRemainder) }

                val isInvalidAnswer = state.question.answer != state.textAnswer
                val isInvalidRemainder =
                    state.question.hasRemainder && state.question.remainder != textRemainder
                if (isInvalidAnswer || isInvalidRemainder) return@intent
            }
        }

        qaList.add(
            state.question,
            Answer.Success(
                state.textAnswer,
                state.textRemainder
            )
        )

        reduce {
            state.copy(
                textProgress = state.textProgress,
                isSuccess = true
            )
        }
        postSideEffect(ProgressEvent.Success(state.setting.isEffectMute))
        delay(500)

        createNextQuestion()
    }

    override fun onClear() = intent {
        if (state.isSuccess || state.isFailed) return@intent

        postSideEffect(ProgressEvent.Clear(state.setting.isEffectMute))

        when (state.focusMode) {
            FocusMode.ANSWER -> {
                reduce { state.copy(textAnswer = "") }
            }

            FocusMode.REMAINDER -> {
                reduce { state.copy(textRemainder = "") }
            }
        }
    }

    override fun onInterrupt() = intent {
        postSideEffect(ProgressEvent.Interrupted(state.setting.isEffectMute))
    }

    override fun onUpdateTimeoutProgress(value: Float) = intent {
        reduce { state.copy(timeoutProgress = value) }
    }

    private fun createNextQuestion() = intent {
        if (qaList.questionCount < state.mathText.count) {
            reduce {
                state.copy(
                    textProgress = state.textProgress + 1,
                    textAnswer = "",
                    textRemainder = "",
                    question = generator.generate(),
                    isSuccess = false,
                    isFailed = false,
                    focusMode = FocusMode.ANSWER,
                    timeoutProgress = 1f,
                )
            }
        } else {
            qaList.finish()
            postSideEffect(ProgressEvent.StopBGM)
            postSideEffect(ProgressEvent.Finish(state.setting.isEffectMute, state.mathText, qaList))
        }
    }
}
