package jp.kaleidot725.easycalc

import android.app.Application
import jp.kaleidot725.easycalc.core.database.di.dbModule
import jp.kaleidot725.easycalc.core.repository.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

open class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(featureModule, repositoryModule, dbModule)
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
