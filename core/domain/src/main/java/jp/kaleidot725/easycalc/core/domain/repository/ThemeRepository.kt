package jp.kaleidot725.easycalc.core.domain.repository

import jp.kaleidot725.easycalc.core.domain.model.theme.Theme
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    fun get(): Flow<Theme>
    suspend fun update(theme: Theme)
}
