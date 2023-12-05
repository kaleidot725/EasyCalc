package jp.kaleidot725.easycalc

import android.app.Application
import android.util.Log
import com.google.android.gms.ads.MobileAds
import com.google.firebase.crashlytics.FirebaseCrashlytics
import jp.kaleidot725.easycalc.db.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

open class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) {}
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule, repositoryModule, dbModule)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(false)
        } else {
            Timber.plant(ReleaseTree())
        }
    }
}

private class ReleaseTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        when (priority) {
            Log.INFO -> writeCrashlytics("INFO", tag ?: "", message, t)
            Log.WARN -> writeCrashlytics("WARN", tag ?: "", message, t)
            Log.ERROR -> writeCrashlytics("ERROR", tag ?: "", message, t)
        }
    }

    private fun writeCrashlytics(prefix: String, tag: String?, message: String, t: Throwable?) {
        FirebaseCrashlytics.getInstance().log("$prefix,$tag,$message,$t")
    }
}
