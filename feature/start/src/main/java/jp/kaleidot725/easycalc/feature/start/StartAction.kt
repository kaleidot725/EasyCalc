package jp.kaleidot725.easycalc.feature.start

interface StartAction {
    fun refresh()
    fun startCalculation()
    fun toggleFavorite()
    fun popBack()
}
