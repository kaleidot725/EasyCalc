package jp.kaleidot725.easycalc.core.domain.model.question

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class QA(
    val q: Question,
    val a: Answer
) {
    val id = UUID.randomUUID().toString()
}
