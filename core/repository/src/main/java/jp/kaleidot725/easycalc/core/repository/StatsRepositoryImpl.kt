package jp.kaleidot725.easycalc.core.repository

import jp.kaleidot725.easycalc.core.domain.model.stats.StatsData
import jp.kaleidot725.easycalc.core.domain.repository.StatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

class StatsRepositoryImpl(
    private val todayStatsDao: jp.kaleidot725.easycalc.core.database.dao.TodayStatsDao
) : StatsRepository {
    override suspend fun getTodayStreakDays(): StatsData.TodayStreakDays {
        return withContext(Dispatchers.Default) {
            val todayId = todayStatsDao.getAll().lastOrNull()?.id
                ?: return@withContext StatsData.TodayStreakDays(0)
            val previousId = getPreviousTodayId(todayId)

            val todayStats = todayStatsDao.getById(todayId)
            val previousStats = todayStatsDao.getById(previousId)

            var count = 0L

            if (todayStats != null) {
                count++
            }

            if (previousStats != null) {
                count++
                var pastStatsId = getPreviousTodayId(previousId)
                while (true) {
                    val pastStats = todayStatsDao.getById(pastStatsId)
                    if (pastStats != null) {
                        count++
                        pastStatsId = getPreviousTodayId(pastStatsId)
                        continue
                    } else {
                        break
                    }
                }
            }
            StatsData.TodayStreakDays(count)
        }
    }

    override suspend fun getTotalStreakDays(): StatsData.TotalStreakDays {
        return withContext(Dispatchers.Default) {
            val allStats = todayStatsDao.getAll()
            if (allStats.isEmpty()) return@withContext StatsData.TotalStreakDays(0)

            var maxConsecutiveDays = 1L
            var currentConsecutiveDays = 1L
            for (i in 1..allStats.size) {
                val previousStats = allStats.getOrNull(i - 1) ?: break
                val currentStats = allStats.getOrNull(i) ?: break
                val expectedPreviousStatsId = getPreviousTodayId(currentStats.id)
                if (expectedPreviousStatsId == previousStats.id) {
                    currentConsecutiveDays++
                    if (currentConsecutiveDays > maxConsecutiveDays) {
                        maxConsecutiveDays = currentConsecutiveDays
                    }
                } else {
                    currentConsecutiveDays = 1L
                }
            }
            return@withContext StatsData.TotalStreakDays(maxConsecutiveDays)
        }
    }

    override suspend fun getTodaySolvedQuizCount(): StatsData.TodaySolvedQuizCount {
        val todayStats = todayStatsDao.getById(getTodayId())
        return StatsData.TodaySolvedQuizCount(todayStats?.solvedQuizCount ?: 0)
    }

    override suspend fun getTodayTrainingTime(): StatsData.TodayTrainingTime {
        val todayStats = todayStatsDao.getById(getTodayId())
        return StatsData.TodayTrainingTime(todayStats?.elapsedTime ?: 0)
    }

    override suspend fun getTotalStudyDays(): StatsData.TotalStudyDays {
        val totalStudyDays = todayStatsDao.getCount()
        return StatsData.TotalStudyDays(totalStudyDays)
    }

    override suspend fun getTotalTrainingTime(): StatsData.TotalTrainingTime {
        val totalTrainingTime = todayStatsDao.getTotalElapsedSeconds()
        return StatsData.TotalTrainingTime(totalTrainingTime)
    }

    override suspend fun getTotalSolvedQuizCount(): StatsData.TotalSolvedQuizCount {
        val totalSolvedQuizCount = todayStatsDao.getTotalSolvedQuizCount()
        return StatsData.TotalSolvedQuizCount(totalSolvedQuizCount)
    }

    override suspend fun getAll(): List<StatsData> {
        return listOf(
            getTodayStreakDays(),
            getTodaySolvedQuizCount(),
            getTodayTrainingTime(),
            getTotalStreakDays(),
            getTotalStudyDays(),
            getTotalTrainingTime(),
            getTotalSolvedQuizCount()
        )
    }

    override suspend fun addTodayStats(epochSeconds: Long, solvedQuizCount: Long) {
        val current = todayStatsDao.getById(getTodayId())
            ?: jp.kaleidot725.easycalc.core.database.entity.TodayStatsEntity(
                getTodayId(),
                0,
                0
            )
        val new = current.copy(
            elapsedTime = current.elapsedTime + epochSeconds,
            solvedQuizCount = current.solvedQuizCount + solvedQuizCount
        )
        todayStatsDao.insert(new)
    }

    private fun getTodayId(): String {
        val localDateTime = Clock.System
            .now()
            .toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
        return getSortableId(
            localDateTime.year,
            localDateTime.monthNumber,
            localDateTime.dayOfMonth
        )
    }

    private fun getPreviousTodayId(todayId: String): String {
        val splits = todayId.split("-")
        val year = splits[0].toInt()
        val monthNumber = splits[1].toInt()
        val dayOfMont = splits[2].toInt()
        val date = LocalDate(year, monthNumber, dayOfMont)
        val previousDate = date.minus(1, DateTimeUnit.DAY)
        return getSortableId(previousDate.year, previousDate.monthNumber, previousDate.dayOfMonth)
    }

    private fun getSortableId(year: Int, monthNumber: Int, dayOfMonth: Int): String {
        val m = monthNumber.toString().padStart(2, '0')
        val d = dayOfMonth.toString().padStart(2, '0')
        return "$year-$m-$d"
    }
}
