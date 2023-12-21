package jp.kaleidot725.easycalc.core.ui.screen.progress

import jp.kaleidot725.easycalc.core.domain.model.question.Question
import jp.kaleidot725.easycalc.core.domain.model.setting.Setting
import jp.kaleidot725.easycalc.core.domain.model.text.MathText

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
