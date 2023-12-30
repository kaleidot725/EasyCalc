package jp.kaleidot725.easycalc.feature.progress

import jp.kaleidot725.easycalc.core.domain.model.question.Question
import jp.kaleidot725.easycalc.core.domain.model.question.generator.QuestionGenerator
import jp.kaleidot725.easycalc.core.domain.model.setting.Setting
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.feature.progress.model.FocusMode

data class ProgressState(
    val mathText: MathText,
    val question: Question,
    val textAnswer: String,
    val textRemainder: String,
    val textProgress: Long,
    val isSuccess: Boolean,
    val isFailed: Boolean,
    val setting: Setting,
    val focusMode: FocusMode,
    val timeoutProgress: Float,
) {
    companion object {
        fun createInitState(mathText: MathText, generator: QuestionGenerator): ProgressState {
            return ProgressState(
                mathText = mathText,
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
        }
    }
}
