package jp.kaleidot725.easycalc.core.domain.model.question

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
sealed class Answer : java.io.Serializable {
    abstract val value: String
    abstract val remainder: String

    @Immutable
    @Serializable
    data class Success(
        override val value: String,
        override val remainder: String
    ) : Answer()

    @Immutable
    @Serializable
    data class Failed(
        override val value: String,
        override val remainder: String
    ) : Answer()
}
