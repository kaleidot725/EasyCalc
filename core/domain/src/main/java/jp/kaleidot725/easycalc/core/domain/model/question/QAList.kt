package jp.kaleidot725.easycalc.core.domain.model.question

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
class QAList {
    private val _values: MutableList<QA> = mutableListOf()
    val values: List<QA> = _values

    val successCount: Long get() = values.count { it.a is Answer.Success }.toLong()
    val questionCount: Long get() = values.count().toLong()
    val percent: Float get() = successCount.toFloat() / questionCount.toFloat() * 100.0f

    var startTime: Instant = Clock.System.now()
        private set

    var finishTime: Instant = Clock.System.now()
        private set

    fun start() {
        startTime = Clock.System.now()
    }

    fun finish() {
        finishTime = Clock.System.now()
    }

    fun add(question: Question, answer: Answer) {
        _values.add(
            QA(
                question,
                answer
            )
        )
    }

    fun clear() {
        _values.clear()
    }
}
