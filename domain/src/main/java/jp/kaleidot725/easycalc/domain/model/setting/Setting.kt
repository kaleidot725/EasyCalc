package jp.kaleidot725.easycalc.domain.model.setting

import kotlinx.serialization.Serializable

@Serializable
data class Setting(val isBgmMute: Boolean, val isEffectMute: Boolean)
