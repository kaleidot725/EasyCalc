package jp.kaleidot725.easycalc.domain.model.question

import kotlinx.serialization.Serializable

@Serializable
sealed class Answer : java.io.Serializable {
    abstract val value: String
    abstract val remainder: String

    @Serializable
    data class Success(override val value: String, override val remainder: String) : Answer()

    @Serializable
    data class Failed(override val value: String, override val remainder: String) : Answer()
}
