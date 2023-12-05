package jp.kaleidot725.easycalc.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "today_stats")
data class TodayStatsEntity(
    @PrimaryKey val id: String,
    val solvedQuizCount: Long,
    val elapsedTime: Long,
)
