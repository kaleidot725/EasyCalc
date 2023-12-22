package jp.kaleidot725.easycalc.feature.progress

import jp.kaleidot725.easycalc.core.domain.model.question.Question
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
)
