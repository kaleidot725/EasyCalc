package jp.kaleidot725.easycalc.core.repository

import jp.kaleidot725.easycalc.core.domain.model.setting.Setting
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    suspend fun update(setting: Setting)
    fun get(): Flow<Setting>
}
