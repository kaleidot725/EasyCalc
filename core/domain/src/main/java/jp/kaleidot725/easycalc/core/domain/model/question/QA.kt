package jp.kaleidot725.easycalc.core.domain.model.question

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable
import java.util.UUID

@Immutable
@Serializable
data class QA(
    val q: Question,
    val a: Answer
) {
    val id = UUID.randomUUID().toString()
}
