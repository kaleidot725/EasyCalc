package jp.kaleidot725.easycalc.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import jp.kaleidot725.easycalc.domain.model.setting.Setting
import jp.kaleidot725.easycalc.domain.repository.SettingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingRepositoryImpl(
    private val applicationContext: Context,
) : SettingRepository {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = FILE_NAME)

    override fun get(): Flow<Setting> {
        return applicationContext.dataStore.data.map { settings ->
            val isEffectMute = settings[IS_MUTE_KEY] ?: false
            val isBgmMute = settings[IS_BGM_MUTE_KEY] ?: false
            Setting(isBgmMute = isBgmMute, isEffectMute = isEffectMute)
        }
    }

    override suspend fun update(data: Setting) {
        applicationContext.dataStore.edit { settings ->
            settings[IS_MUTE_KEY] = data.isEffectMute
            settings[IS_BGM_MUTE_KEY] = data.isBgmMute
        }
    }

    companion object {
        private const val FILE_NAME = "setting_settings"
        private val IS_MUTE_KEY = booleanPreferencesKey("isMute")
        private val IS_BGM_MUTE_KEY = booleanPreferencesKey("isBgmMute")
    }
}
