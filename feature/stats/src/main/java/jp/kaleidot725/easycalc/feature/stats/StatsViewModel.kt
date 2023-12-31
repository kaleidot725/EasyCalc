package jp.kaleidot725.easycalc.feature.stats

import androidx.lifecycle.ViewModel
import jp.kaleidot725.easycalc.core.repository.StatsRepository
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class StatsViewModel(
    private val statsRepository: StatsRepository
) : ContainerHost<StatsState, StatsEvent>, StatsAction, ViewModel() {
    override val container = container<StatsState, StatsEvent>(
        StatsState(isLoading = true, items = emptyList())
    )

    override fun refresh() = intent {
        val items = statsRepository.getAll()
        reduce {
            state.copy(isLoading = false, items = items)
        }
    }
}
