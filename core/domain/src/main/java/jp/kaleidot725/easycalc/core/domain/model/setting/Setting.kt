package jp.kaleidot725.easycalc.core.domain.model.setting

import kotlinx.serialization.Serializable

@Serializable
data class Setting(val isBgmMute: Boolean, val isEffectMute: Boolean)
