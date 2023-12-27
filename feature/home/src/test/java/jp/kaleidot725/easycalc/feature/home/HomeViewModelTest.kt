package jp.kaleidot725.easycalc.feature.home

import jp.kaleidot725.easycalc.core.domain.model.stats.StatsData
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.domain.model.text.MathTexts
import jp.kaleidot725.easycalc.core.repository.StatsRepository
import jp.kaleidot725.easycalc.core.repository.TextRepository
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.orbitmvi.orbit.test

class HomeViewModelTest {
    private val historyMathText = MathText.SingleDigitsAddition
    private val historyMathTexts = MathTexts(value = persistentListOf(historyMathText))

    private val favoriteMathText = MathText.SingleDigitsDivision
    private val favoriteMathTexts = MathTexts(value = persistentListOf(favoriteMathText))
    private val textRepository = mock<TextRepository> {
        onBlocking { getHistory() } doReturn historyMathTexts
        onBlocking { getFavorite() } doReturn favoriteMathTexts
    }

    private val firstTodayStreakDays = StatsData.TodayStreakDays(1L)
    private val firstTodaySolvedQuizCount = StatsData.TodaySolvedQuizCount(2L)
    private val firstTodayTrainingTime = StatsData.TodayTrainingTime(3L)
    private val statsRepository = mock<StatsRepository> {
        onBlocking { getTodayStreakDays() } doReturn firstTodayStreakDays
        onBlocking { getTodaySolvedQuizCount() } doReturn firstTodaySolvedQuizCount
        onBlocking { getTodayTrainingTime() } doReturn firstTodayTrainingTime
    }

    private val expectState = HomeState(
        histories = historyMathTexts,
        mylist = favoriteMathTexts,
        todayStreakDays = firstTodayStreakDays,
        todaySolvedQuizCount = firstTodaySolvedQuizCount,
        todayTrainingTime = firstTodayTrainingTime,
    )

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `初期化したときに履歴が通知されるか`() = runTest {
        val initialState = HomeState()
        val viewModel = HomeViewModel(
            textRepository = textRepository,
            statsRepository = statsRepository
        ).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.assert(initialState) { states({ expectState }) }
    }

    @Test
    fun `クリックしたときにClickHistoryイベントが発生するか`() = runTest {
        val initialState = HomeState()
        val viewModel = HomeViewModel(
            textRepository = textRepository,
            statsRepository = statsRepository
        ).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { clickHistory() }
        viewModel.assert(initialState) {
            postedSideEffects(HomeEvent.ClickHistory)
            states({ expectState })
        }
    }

    @Test
    fun `クリックしたときにClickMyListイベントが発生するか`() = runTest {
        val initialState = HomeState()
        val viewModel = HomeViewModel(
            textRepository = textRepository,
            statsRepository = statsRepository
        ).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { clickMyList() }
        viewModel.assert(initialState) {
            postedSideEffects(HomeEvent.ClickMyList)
            states({ expectState })
        }
    }

    @Test
    fun `クリックしたときにClickStatsイベントが発生するか`() = runTest {
        val initialState = HomeState()
        val viewModel = HomeViewModel(
            textRepository = textRepository,
            statsRepository = statsRepository
        ).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { clickStats() }
        viewModel.assert(initialState) {
            postedSideEffects(HomeEvent.ClickStats)
            states({ expectState })
        }
    }

    @Test
    fun `クリックしたときにClickTextイベントが発生するか`() = runTest {
        val initialState = HomeState()
        val viewModel = HomeViewModel(
            textRepository = textRepository,
            statsRepository = statsRepository
        ).test(initialState)

        viewModel.runOnCreate()
        viewModel.testIntent { refresh() }
        viewModel.testIntent { clickText(historyMathText) }
        viewModel.assert(initialState) {
            postedSideEffects(HomeEvent.ClickText(historyMathText))
            states({ expectState })
        }
    }
}