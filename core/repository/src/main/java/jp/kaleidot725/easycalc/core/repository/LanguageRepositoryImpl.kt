package jp.kaleidot725.easycalc.core.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import jp.kaleidot725.easycalc.core.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.Locale

class LanguageRepositoryImpl(
    private val applicationContext: Context,
) : LanguageRepository {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = FILE_NAME)

    override suspend fun initialize() {
        val settings = applicationContext.dataStore.data.first()
        val lang = settings[LANGUAGE_KEY]
        val language = jp.kaleidot725.easycalc.core.domain.model.language.Language.entries.firstOrNull { it.lang == lang }
        if (language == null) {
            val currentLocale = Locale.getDefault()
            val currentLanguage = jp.kaleidot725.easycalc.core.domain.model.language.Language.entries.firstOrNull {
                currentLocale.language == it.locale.language
            } ?: jp.kaleidot725.easycalc.core.domain.model.language.Language.English
            update(currentLanguage)
        }
    }

    override fun get(): Flow<jp.kaleidot725.easycalc.core.domain.model.language.Language> {
        return applicationContext.dataStore.data.map { settings ->
            val lang = settings[LANGUAGE_KEY]
            jp.kaleidot725.easycalc.core.domain.model.language.Language.values().firstOrNull { it.lang == lang } ?: jp.kaleidot725.easycalc.core.domain.model.language.Language.English
        }
    }

    override suspend fun update(theme: jp.kaleidot725.easycalc.core.domain.model.language.Language) {
        applicationContext.dataStore.edit { settings ->
            settings[LANGUAGE_KEY] = theme.lang
        }
    }

    companion object {
        private const val FILE_NAME = "language_theme"
        private val LANGUAGE_KEY = stringPreferencesKey("language")
    }
}
