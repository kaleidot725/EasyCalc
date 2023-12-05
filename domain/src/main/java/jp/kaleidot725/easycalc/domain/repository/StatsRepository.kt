package jp.kaleidot725.easycalc.domain.repository

import jp.kaleidot725.easycalc.domain.model.stats.StatsData

interface StatsRepository {
    suspend fun getTodayStreakDays(): StatsData.TodayStreakDays
    suspend fun getTodaySolvedQuizCount(): StatsData.TodaySolvedQuizCount
    suspend fun getTodayTrainingTime(): StatsData.TodayTrainingTime

    suspend fun getTotalStreakDays(): StatsData.TotalStreakDays
    suspend fun getTotalStudyDays(): StatsData.TotalStudyDays
    suspend fun getTotalTrainingTime(): StatsData.TotalTrainingTime
    suspend fun getTotalSolvedQuizCount(): StatsData.TotalSolvedQuizCount

    suspend fun getAll(): List<StatsData>
    suspend fun addTodayStats(epochSeconds: Long, solvedQuizCount: Long)
}
