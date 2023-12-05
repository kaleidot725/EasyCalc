package jp.kaleidot725.easycalc.db

import androidx.room.Room
import jp.kaleidot725.easycalc.db.dao.TodayStatsDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single<StatsDatabase> {
        Room.databaseBuilder(androidApplication(), StatsDatabase::class.java, "stats").build()
    }

    single<TodayStatsDao> {
        val db: StatsDatabase = get()
        db.todayStatsDao()
    }
}
