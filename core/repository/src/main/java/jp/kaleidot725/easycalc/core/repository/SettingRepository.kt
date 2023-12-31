package jp.kaleidot725.easycalc.core.repository

import jp.kaleidot725.easycalc.core.domain.model.setting.Setting

interface SettingRepository {
    suspend fun update(setting: Setting)
    suspend fun get(): Setting
}
