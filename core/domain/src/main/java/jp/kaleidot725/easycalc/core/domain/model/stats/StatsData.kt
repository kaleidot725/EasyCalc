package jp.kaleidot725.easycalc.core.domain.model.stats

import androidx.compose.runtime.Immutable

@Immutable
sealed interface StatsData {
    @Immutable
    data class TodayStreakDays(val streakDays: Long) : StatsData

    @Immutable
    data class TodaySolvedQuizCount(val count: Long) : StatsData

    @Immutable
    data class TodayTrainingTime(val elapsedSeconds: Long) : StatsData

    @Immutable
    data class TotalStreakDays(val streakDays: Long) : StatsData

    @Immutable
    data class TotalStudyDays(val studyDays: Long) : StatsData

    @Immutable
    data class TotalTrainingTime(val elapsedSeconds: Long) : StatsData

    @Immutable
    data class TotalSolvedQuizCount(val totalSolvedQuizCount: Long) : StatsData
}
