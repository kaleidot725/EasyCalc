package jp.kaleidot725.easycalc.domain.repository

import jp.kaleidot725.easycalc.domain.model.setting.Setting
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    suspend fun update(setting: Setting)
    fun get(): Flow<Setting>
}
