package jp.kaleidot725.easycalc.domain.model.text

import kotlinx.serialization.Serializable

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
