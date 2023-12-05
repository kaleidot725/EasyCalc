package jp.kaleidot725.easycalc.ui.screen.progress

import jp.kaleidot725.easycalc.domain.model.question.Question
import jp.kaleidot725.easycalc.domain.model.setting.Setting
import jp.kaleidot725.easycalc.domain.model.text.MathText

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
