package jp.kaleidot725.easycalc.feature.progress

import androidx.lifecycle.ViewModel
import jp.kaleidot725.easycalc.core.domain.model.question.Answer
import jp.kaleidot725.easycalc.core.domain.model.question.QAList
import jp.kaleidot725.easycalc.core.domain.model.question.selector.QuestionSelector
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextId
import jp.kaleidot725.easycalc.core.repository.SettingRepository
import jp.kaleidot725.easycalc.core.repository.TextRepository
import jp.kaleidot725.easycalc.feature.progress.model.FocusMode
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
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
        ProgressState.createInitState(text, generator)
    )

    override fun refresh() = intent {
        updateSetting()
    }

    override fun changeEffectMute(isMute: Boolean) = intent {
        settingRepository.update(state.setting.copy(isEffectMute = isMute))
        updateSetting()
    }

    override fun changeBgmMute(isMute: Boolean) = intent {
        settingRepository.update(state.setting.copy(isBgmMute = isMute))
        updateSetting()
    }

    override fun clickNumber(number: Int) = intent {
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

        reduce { state.copy(isSuccess = true) }
        postSideEffect(ProgressEvent.Success(state.setting.isEffectMute))
        delay(500)

        next()
    }

    override fun changeFocus(focusMode: FocusMode) = intent {
        reduce { state.copy(focusMode = focusMode) }
    }

    override fun skip() {
        timeout()
    }

    override fun timeout() = intent {
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

        next()
    }

    override fun delete() = intent {
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

        next()
    }

    override fun clear() = intent {
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

    override fun interrupt() = intent {
        postSideEffect(ProgressEvent.Interrupted(state.setting.isEffectMute))
    }

    override fun updateTimeoutProgress(value: Float) = intent {
        reduce { state.copy(timeoutProgress = value) }
    }

    private suspend fun SimpleSyntax<ProgressState, ProgressEvent>.updateSetting() {
        val setting = settingRepository.get()
        reduce {
            state.copy(setting = setting)
        }

        val sideEffect = if (setting.isBgmMute) {
            ProgressEvent.StopBGM
        } else {
            ProgressEvent.StartBGM
        }
        postSideEffect(sideEffect = sideEffect)
    }

    private fun next() = intent {
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
