package jp.kaleidot725.easycalc.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.repository.StatsRepository
import jp.kaleidot725.easycalc.core.repository.TextRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class HomeViewModel(
    private val textRepository: jp.kaleidot725.easycalc.core.repository.TextRepository,
    private val statsRepository: jp.kaleidot725.easycalc.core.repository.StatsRepository,
) : ContainerHost<HomeState, HomeEvent>, HomeAction, ViewModel() {
    override val container = container<HomeState, HomeEvent>(HomeState())

    init {
        refresh()
    }

    override fun clickHistory() = intent {
        postSideEffect(HomeEvent.ClickHistory)
    }

    override fun clickMyList() = intent {
        postSideEffect(HomeEvent.ClickMyList)
    }

    override fun clickText(mathText: MathText) = intent {
        postSideEffect(HomeEvent.ClickText(mathText))
    }

    override fun clickStats() = intent {
        postSideEffect(HomeEvent.ClickStats)
    }

    override fun refresh() {
        viewModelScope.launch {
            textRepository.getHistory().collectLatest {
                intent { reduce { state.copy(histories = it) } }
            }
        }
        viewModelScope.launch {
            textRepository.getFavorite().collectLatest {
                intent {
                    reduce {
                        state.copy(mylist = it)
                    }
                }
            }
        }
        viewModelScope.launch {
            val todayStreakDays = statsRepository.getTodayStreakDays()
            val todaySolvedQuizCount = statsRepository.getTodaySolvedQuizCount()
            val todayTrainingTime = statsRepository.getTodayTrainingTime()
            intent {
                reduce {
                    state.copy(
                        todayStreakDays = todayStreakDays,
                        todaySolvedQuizCount = todaySolvedQuizCount,
                        todayTrainingTime = todayTrainingTime
                    )
                }
            }
        }
    }
}
