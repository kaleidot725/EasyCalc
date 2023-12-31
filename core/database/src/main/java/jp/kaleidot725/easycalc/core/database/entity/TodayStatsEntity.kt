package jp.kaleidot725.easycalc.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "today_stats")
data class TodayStatsEntity(
    @PrimaryKey val id: String,
    val solvedQuizCount: Long,
    val elapsedTime: Long,
)
