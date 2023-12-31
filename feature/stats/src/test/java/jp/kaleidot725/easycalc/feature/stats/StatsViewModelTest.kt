package jp.kaleidot725.easycalc.feature.stats

import jp.kaleidot725.easycalc.core.domain.model.stats.StatsData
import jp.kaleidot725.easycalc.core.repository.StatsRepository
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

class StatsViewModelTest {
    private val stats = listOf(StatsData.TodayStreakDays(100))
    private val statsRepository = mock<StatsRepository> {
        onBlocking { getAll() } doReturn stats
    }
    private val startViewModel = StatsViewModel(statsRepository = statsRepository)

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `初期化したときにデータが更新されるか`() = runTest {
        // Action
        val initialState = StatsState(isLoading = true, items = emptyList())
        val viewModel = startViewModel.test(initialState)
        viewModel.testIntent { refresh() }

        // Verify
        viewModel.assert(initialState) {
            states({ copy(isLoading = false, items = stats) })
        }
    }
}
