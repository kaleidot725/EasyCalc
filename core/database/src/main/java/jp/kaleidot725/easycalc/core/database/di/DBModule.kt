package jp.kaleidot725.easycalc.core.database.di

import androidx.room.Room
import jp.kaleidot725.easycalc.core.database.StatsDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(androidApplication(), StatsDatabase::class.java, "stats").build()
    }

    single {
        val db: StatsDatabase = get()
        db.todayStatsDao()
    }
}
