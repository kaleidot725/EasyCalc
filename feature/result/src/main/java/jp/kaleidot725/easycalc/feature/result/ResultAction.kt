package jp.kaleidot725.easycalc.feature.result

interface ResultAction {
    fun refresh()
    fun finish()
    fun retry()
    fun popBack()
}
