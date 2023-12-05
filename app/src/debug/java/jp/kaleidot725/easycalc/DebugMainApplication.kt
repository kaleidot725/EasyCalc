package jp.kaleidot725.easycalc

import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.soloader.SoLoader

class DebugMainApplication : MainApplication() {
    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
        val client = AndroidFlipperClient.getInstance(this)
        client.addPlugin(DatabasesFlipperPlugin(this))
        client.start()
    }
}