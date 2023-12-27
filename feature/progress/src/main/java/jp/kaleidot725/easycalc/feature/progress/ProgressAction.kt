package jp.kaleidot725.easycalc.feature.progress

import jp.kaleidot725.easycalc.feature.progress.model.FocusMode

interface ProgressAction {
    fun refresh()
    fun clickNumber(number: Int)
    fun changeEffectMute(isMute: Boolean)
    fun changeBgmMute(isMute: Boolean)
    fun changeFocus(focusMode: FocusMode)
    fun skip()
    fun timeout()
    fun delete()
    fun clear()
    fun interrupt()
    fun updateTimeoutProgress(value: Float)
}
