package jp.kaleidot725.easycalc.core.repository

import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextId
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextSet
import jp.kaleidot725.easycalc.core.domain.model.text.MathTexts

interface TextRepository {
    suspend fun get(): MathTextSet
    fun getById(id: MathTextId): MathText
    suspend fun getHistory(): MathTexts
    suspend fun getFavorite(): MathTexts
    suspend fun isFavorite(id: MathTextId): Boolean

    suspend fun addHistory(id: MathTextId)
    suspend fun clearHistory()
    suspend fun addFavorite(id: MathTextId)
    suspend fun deleteFavorite(id: MathTextId)
}
