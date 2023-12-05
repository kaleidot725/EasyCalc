package jp.kaleidot725.easycalc

import android.app.Application
import android.util.Log
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.google.android.gms.ads.MobileAds
import com.google.firebase.crashlytics.FirebaseCrashlytics
import jp.kaleidot725.easycalc.db.dbModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class DebugMainApplication : MainApplication() {
    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
        val client = AndroidFlipperClient.getInstance(this)
        client.addPlugin(DatabasesFlipperPlugin(this))
        client.start()
    }
}