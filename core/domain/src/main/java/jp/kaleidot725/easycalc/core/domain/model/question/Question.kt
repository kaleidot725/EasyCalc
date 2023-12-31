package jp.kaleidot725.easycalc.core.domain.model.question

import androidx.compose.runtime.Immutable
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Question(
    val one: String,
    val two: String,
    val answer: String,
    val category: MathText.Category,
    val remainder: String? = null,
) {
    val hasRemainder get() = remainder != null && category == MathText.Category.DIVISION
}
