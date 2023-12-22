package jp.kaleidot725.easycalc.feature.progress

import jp.kaleidot725.easycalc.core.domain.model.question.QAList
import jp.kaleidot725.easycalc.core.domain.model.text.MathText

sealed interface ProgressEvent {
    data class Finish(
        val isMute: Boolean,
        val mathText: MathText,
        val qalist: QAList
    ) : ProgressEvent

    data class Success(val isMute: Boolean) : ProgressEvent
    data class Failed(val isMute: Boolean) : ProgressEvent
    data class Interrupted(val isMute: Boolean) : ProgressEvent
    data class Input(val isMute: Boolean) : ProgressEvent
    data class Clear(val isMute: Boolean) : ProgressEvent
    object StartBGM : ProgressEvent
    object StopBGM : ProgressEvent
}
