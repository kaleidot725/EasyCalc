package jp.kaleidot725.easycalc.domain.repository

import jp.kaleidot725.easycalc.domain.model.language.Language
import kotlinx.coroutines.flow.Flow

interface LanguageRepository {
    suspend fun initialize()
    fun get(): Flow<Language>
    suspend fun update(theme: Language)
}
