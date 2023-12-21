package jp.kaleidot725.easycalc.core.domain.model.stats

sealed interface StatsData {
    data class TodayStreakDays(val streakDays: Long) : StatsData

    data class TodaySolvedQuizCount(val count: Long) : StatsData

    data class TodayTrainingTime(val elapsedSeconds: Long) : StatsData

    data class TotalStreakDays(val streakDays: Long) : StatsData

    data class TotalStudyDays(val studyDays: Long) : StatsData

    data class TotalTrainingTime(val elapsedSeconds: Long) : StatsData

    data class TotalSolvedQuizCount(val totalSolvedQuizCount: Long) : StatsData
}
