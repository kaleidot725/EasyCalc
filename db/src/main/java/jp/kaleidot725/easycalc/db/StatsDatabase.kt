package jp.kaleidot725.easycalc.db

import androidx.room.Database
import androidx.room.RoomDatabase
import jp.kaleidot725.easycalc.db.dao.TodayStatsDao
import jp.kaleidot725.easycalc.db.entity.TodayStatsEntity

@Database(entities = [TodayStatsEntity::class], version = 1)
abstract class StatsDatabase : RoomDatabase() {
    abstract fun todayStatsDao(): TodayStatsDao
}
