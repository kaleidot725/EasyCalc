package jp.kaleidot725.easycalc.core.domain.model.setting

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Setting(val isBgmMute: Boolean, val isEffectMute: Boolean)
