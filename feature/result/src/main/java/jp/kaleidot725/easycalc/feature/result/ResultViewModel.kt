package jp.kaleidot725.easycalc.feature.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.kaleidot725.easycalc.core.domain.model.question.QAList
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextId
import jp.kaleidot725.easycalc.core.repository.StatsRepository
import jp.kaleidot725.easycalc.core.repository.TextRepository
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class ResultViewModel(
    mathTextId: MathTextId,
    qaList: QAList,
    mathTextRepository: jp.kaleidot725.easycalc.core.repository.TextRepository,
    statsRepository: jp.kaleidot725.easycalc.core.repository.StatsRepository,
) : ContainerHost<ResultState, ResultEvent>, ResultAction, ViewModel() {
    override val container = container<ResultState, ResultEvent>(
        ResultState(
            mathText = mathTextRepository.getById(mathTextId),
            qaList = qaList
        )
    )

    init {
        viewModelScope.launch {
            val diffSeconds = qaList.finishTime.epochSeconds - qaList.startTime.epochSeconds
            val count = qaList.questionCount
            statsRepository.addTodayStats(diffSeconds, count)
        }
    }

    override fun finish() = intent {
        postSideEffect(ResultEvent.Finish)
    }

    override fun retry() = intent {
        postSideEffect(ResultEvent.Retry(state.mathText))
    }

    override fun popBack() = intent {
        postSideEffect(ResultEvent.PopBack)
    }
}
