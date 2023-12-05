package jp.kaleidot725.easycalc.ui.screen.progress

interface ProgressAction {
    fun onClickNumber(number: Int)
    fun onChangeEffectMute(isMute: Boolean)
    fun onChangeBgmMute(isMute: Boolean)
    fun onChangeFocus(focusMode: FocusMode)
    fun onSkip()
    fun onTimeout()
    fun onDelete()
    fun onClear()
    fun onInterrupt()
    fun onUpdateTimeoutProgress(value: Float)
}
