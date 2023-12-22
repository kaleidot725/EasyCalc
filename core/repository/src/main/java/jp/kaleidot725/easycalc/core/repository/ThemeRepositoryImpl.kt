package jp.kaleidot725.easycalc.core.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import jp.kaleidot725.easycalc.core.domain.model.theme.Theme
import jp.kaleidot725.easycalc.core.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeRepositoryImpl(
    private val applicationContext: Context,
) : ThemeRepository {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = FILE_NAME)

    override fun get(): Flow<Theme> {
        return applicationContext.dataStore.data.map { settings ->
            val text = settings[THEME_KEY]
            Theme.values().firstOrNull { it.text == text } ?: Theme.LIGHT
        }
    }

    override suspend fun update(theme: Theme) {
        applicationContext.dataStore.edit { settings ->
            settings[THEME_KEY] = theme.text
        }
    }

    companion object {
        private const val FILE_NAME = "theme_settings"
        private val THEME_KEY = stringPreferencesKey("theme")
    }
}
