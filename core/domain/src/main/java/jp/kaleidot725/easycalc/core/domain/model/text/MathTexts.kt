package jp.kaleidot725.easycalc.core.domain.model.text

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class MathTexts(
    val value: List<MathText> = emptyList()
) {
    val isEmpty: Boolean get() = value.isEmpty()
    val isNotEmpty: Boolean get() = !isEmpty

    companion object {
        val EMPTY = MathTexts(emptyList())
    }
}
