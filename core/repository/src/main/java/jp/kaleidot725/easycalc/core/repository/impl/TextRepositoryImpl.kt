package jp.kaleidot725.easycalc.core.repository.impl

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextId
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextSet
import jp.kaleidot725.easycalc.core.domain.model.text.MathTexts
import jp.kaleidot725.easycalc.core.repository.TextRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class TextRepositoryImpl(
    private val applicationContext: Context,
) : TextRepository {
    private val Context.historyStore: DataStore<Preferences> by preferencesDataStore(name = FILE_NAME)
    private val Context.favoriteStore: DataStore<Preferences> by preferencesDataStore(name = FAVORITE_FILE_NAME)

    private val additions = listOf(
        MathText.SingleDigitsAddition,
        MathText.DoubleDigitsAddition,
        MathText.TripleDigitsAddition
    )

    private val subtractions = listOf(
        MathText.SingleDigitsSubtraction,
        MathText.DoubleDigitsSubtraction,
        MathText.TripleDigitsSubtraction
    )

    private val multiplications = listOf(
        MathText.SingleDigitsMultiplication,
        MathText.DoubleDigitsMultiplication,
        MathText.TripleDigitsMultiplication
    )

    private val divisions = listOf(
        MathText.SingleDigitsDivision,
        MathText.DoubleDigitsDivision,
        MathText.TripleDigitsDivision,
        MathText.SingleDigitsDivisionRemainder,
        MathText.DoubleDigitsDivisionRemainder,
        MathText.TripleDigitsDivisionRemainder,
    )

    private val multiplicationTable = listOf(
        MathText.MultiplicationTableForOne,
        MathText.MultiplicationTableForTwo,
        MathText.MultiplicationTableForThree,
        MathText.MultiplicationTableForFour,
        MathText.MultiplicationTableForFive,
        MathText.MultiplicationTableForSix,
        MathText.MultiplicationTableForSeven,
        MathText.MultiplicationTableForEight,
        MathText.MultiplicationTableForNine,
        MathText.MultiplicationTableFor10,
        MathText.MultiplicationTableFor11,
        MathText.MultiplicationTableFor12,
        MathText.MultiplicationTableFor13,
        MathText.MultiplicationTableFor14,
        MathText.MultiplicationTableFor15,
        MathText.MultiplicationTableFor16,
        MathText.MultiplicationTableFor17,
        MathText.MultiplicationTableFor18,
        MathText.MultiplicationTableFor19,
        MathText.MultiplicationTableFor20,
    )

    private val all = additions + subtractions + multiplications + divisions + multiplicationTable

    override suspend fun get(): MathTextSet {
        return MathTextSet(
            additions = MathTexts(additions),
            subtractions = MathTexts(subtractions),
            multiplications = MathTexts(multiplications),
            multiplicationTable = MathTexts(multiplicationTable),
            divisions = MathTexts(divisions),
        )
    }

    override fun getHistory(): Flow<MathTexts> {
        return applicationContext.historyStore.data.map { settings ->
            val currentHistoryListJson = settings[HISTORY_LIST_KEY]
            val currentHistoryList = try {
                Json.decodeFromString<List<String>>(currentHistoryListJson ?: "{}")
            } catch (_: IllegalArgumentException) {
                emptyList()
            }
            val values =
                currentHistoryList.mapNotNull { id -> all.firstOrNull { it.id.value == id } }
            MathTexts(values)
        }
    }

    override fun getFavorite(): Flow<MathTexts> {
        return applicationContext.favoriteStore.data.map { settings ->
            val currentFavoriteListJson = settings[FAVORITE_LIST_KEY]
            val currentFavoriteList = try {
                Json.decodeFromString<List<String>>(currentFavoriteListJson ?: "{}")
            } catch (_: IllegalArgumentException) {
                emptyList()
            }
            val values =
                currentFavoriteList.mapNotNull { id -> all.firstOrNull { it.id.value == id } }
            MathTexts(values)
        }
    }

    override fun getById(id: MathTextId): MathText {
        return all.first { it.id == id }
    }

    override fun isFavorite(id: MathTextId): Flow<Boolean> {
        return applicationContext.favoriteStore.data.map { settings ->
            val currentFavoriteListJson = settings[FAVORITE_LIST_KEY]
            val currentFavoriteList = try {
                Json.decodeFromString<List<String>>(currentFavoriteListJson ?: "{}")
            } catch (_: IllegalArgumentException) {
                emptyList()
            }
            currentFavoriteList.any { id.value == it }
        }
    }

    override suspend fun addHistory(id: MathTextId) {
        withContext(Dispatchers.IO) {
            applicationContext.historyStore.edit { settings ->
                val currentHistoryListJson = settings[HISTORY_LIST_KEY]
                val currentHistoryList = try {
                    Json.decodeFromString<List<String>>(currentHistoryListJson ?: "{}")
                } catch (_: IllegalArgumentException) {
                    emptyList()
                }

                val newHistoryList = currentHistoryList.toMutableList().apply {
                    removeAll { it == id.value }
                    if (HISTORY_COUNT_MAX <= count()) removeLast()
                    add(0, id.value)
                }
                val newHistoryListJson = Json.encodeToString(newHistoryList)
                settings[HISTORY_LIST_KEY] = newHistoryListJson
            }
        }
    }

    override suspend fun clearHistory() {
        withContext(Dispatchers.IO) {
            applicationContext.historyStore.edit { settings ->
                settings[HISTORY_LIST_KEY] = ""
            }
        }
    }

    override suspend fun addFavorite(id: MathTextId) {
        withContext(Dispatchers.IO) {
            applicationContext.favoriteStore.edit { settings ->
                val currentFavoriteListJson = settings[FAVORITE_LIST_KEY]
                val currentFavoriteList = try {
                    Json.decodeFromString<List<String>>(currentFavoriteListJson ?: "{}")
                } catch (_: IllegalArgumentException) {
                    emptyList()
                }

                val newFavoriteList = currentFavoriteList + id.value
                val newFavoriteListJson = Json.encodeToString(newFavoriteList)
                settings[FAVORITE_LIST_KEY] = newFavoriteListJson
            }
        }
    }

    override suspend fun deleteFavorite(id: MathTextId) {
        withContext(Dispatchers.IO) {
            applicationContext.favoriteStore.edit { settings ->
                val currentFavoriteListJson = settings[FAVORITE_LIST_KEY]
                val currentFavoriteList = try {
                    Json.decodeFromString<List<String>>(currentFavoriteListJson ?: "{}")
                } catch (_: IllegalArgumentException) {
                    emptyList()
                }

                val newFavoriteList = currentFavoriteList.filter { id.value != it }
                val newFavoriteListJson = Json.encodeToString(newFavoriteList)
                settings[FAVORITE_LIST_KEY] = newFavoriteListJson
            }
        }
    }

    companion object {
        private const val HISTORY_COUNT_MAX = 10
        private const val FILE_NAME = "text_history"
        private val HISTORY_LIST_KEY = stringPreferencesKey("history")

        private const val FAVORITE_FILE_NAME = "text_favorite"
        private val FAVORITE_LIST_KEY = stringPreferencesKey("favorite")
    }
}
