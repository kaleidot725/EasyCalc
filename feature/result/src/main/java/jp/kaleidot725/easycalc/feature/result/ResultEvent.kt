package jp.kaleidot725.easycalc.feature.result

import jp.kaleidot725.easycalc.core.domain.model.text.MathText

sealed interface ResultEvent {
    object Finish : ResultEvent
    data class Retry(val mathText: MathText) : ResultEvent
    object PopBack : ResultEvent
}
