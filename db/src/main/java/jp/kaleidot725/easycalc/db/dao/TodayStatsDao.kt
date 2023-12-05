package jp.kaleidot725.easycalc.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import jp.kaleidot725.easycalc.db.entity.TodayStatsEntity

@Dao
interface TodayStatsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stats: TodayStatsEntity)

    @Delete
    suspend fun delete(stats: TodayStatsEntity)

    @Query("select * from today_stats order by id")
    suspend fun getAll(): List<TodayStatsEntity>

    @Query("select * from today_stats where id = :id")
    suspend fun getById(id: String): TodayStatsEntity?

    @Query("select count(id) from today_stats")
    suspend fun getCount(): Long

    @Query("select sum(elapsedTime) from today_stats")
    suspend fun getTotalElapsedSeconds(): Long

    @Query("select sum(solvedQuizCount) from today_stats")
    suspend fun getTotalSolvedQuizCount(): Long
}
