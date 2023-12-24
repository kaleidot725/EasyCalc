package jp.kaleidot725.easycalc.core.domain.model.text

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class MathTexts(
    val value: ImmutableList<MathText> = persistentListOf()
) {
    val isEmpty: Boolean get() = value.isEmpty()
    val isNotEmpty: Boolean get() = !isEmpty

    companion object {
        val EMPTY = MathTexts(persistentListOf())
    }
}
