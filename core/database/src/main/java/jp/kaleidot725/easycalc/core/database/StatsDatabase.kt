package jp.kaleidot725.easycalc.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.kaleidot725.easycalc.core.database.dao.TodayStatsDao
import jp.kaleidot725.easycalc.core.database.entity.TodayStatsEntity

@Database(entities = [TodayStatsEntity::class], version = 1)
abstract class StatsDatabase : RoomDatabase() {
    abstract fun todayStatsDao(): TodayStatsDao
}
