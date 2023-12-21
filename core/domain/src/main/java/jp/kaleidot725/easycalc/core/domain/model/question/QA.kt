package jp.kaleidot725.easycalc.core.domain.model.question

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class QA(val q: jp.kaleidot725.easycalc.core.domain.model.question.Question, val a: jp.kaleidot725.easycalc.core.domain.model.question.Answer) {
    val id = UUID.randomUUID().toString()
}
