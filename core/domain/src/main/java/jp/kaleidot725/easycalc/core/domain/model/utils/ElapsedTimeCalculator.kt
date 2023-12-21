package jp.kaleidot725.easycalc.core.domain.model.utils

import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.Instant
import kotlinx.datetime.toDateTimePeriod

object ElapsedTimeCalculator {
    fun calc(startTime: Instant, finishTime: Instant): DateTimePeriod {
        val duration = finishTime - startTime
        return duration.toDateTimePeriod()
    }

    fun calc(elapsedTimeSeconds: Long): DateTimePeriod {
        val startTime = Instant.fromEpochSeconds(0)
        val finishTime = Instant.fromEpochSeconds(elapsedTimeSeconds)
        val duration = finishTime - startTime
        return duration.toDateTimePeriod()
    }
}
