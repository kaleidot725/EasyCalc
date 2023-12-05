package jp.kaleidot725.easycalc

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.core.os.LocaleListCompat
import androidx.core.view.WindowCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.play.core.review.ReviewManagerFactory
import jp.kaleidot725.easycalc.ui.screen.main.ComposeApp
import jp.kaleidot725.easycalc.ui.screen.main.ComposeAppEvent
import jp.kaleidot725.easycalc.ui.screen.main.ComposeAppViewModel
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val appViewModel = koinViewModel<ComposeAppViewModel>()
            val appState by appViewModel.collectAsState()
            appViewModel.collectSideEffect(
                sideEffect = {
                    when (it) {
                        is ComposeAppEvent.ChangeLanguage -> {
                            val list = LocaleListCompat.forLanguageTags(it.language.lang)
                            AppCompatDelegate.setApplicationLocales(list)
                        }
                        is ComposeAppEvent.DisplayAd -> {
                            val adRequest = AdRequest.Builder().build()
                            InterstitialAd.load(
                                this,
                                it.unitId,
                                adRequest,
                                object : InterstitialAdLoadCallback() {
                                    override fun onAdLoaded(value: InterstitialAd) {
                                        value.show(this@MainActivity)
                                        appViewModel.showedAd()
                                    }

                                    override fun onAdFailedToLoad(p0: LoadAdError) {
                                        appViewModel.showedAd()
                                    }
                                }
                            )
                        }
                        ComposeAppEvent.ShowAppInReview -> {
                            showAppInReview()
                        }
                    }
                }
            )
            ComposeApp(
                appState = appState,
                appAction = appViewModel
            )
        }
    }

    private fun showAppInReview() {
        val reviewManager = ReviewManagerFactory.create(this.baseContext)
        val request = reviewManager.requestReviewFlow()
        request.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val reviewInfo = task.result
                val flow = reviewManager.launchReviewFlow(this, reviewInfo)
                flow.addOnCompleteListener { result ->
                    Timber.i("Complete app in review.")
                }
            } else {
                Timber.e("Failed app in review(${task.exception}).")
            }
        }
    }

    private fun showAd(unitId: String, appViewModel: ComposeAppViewModel) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            this,
            unitId,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(value: InterstitialAd) {
                    value.show(this@MainActivity)
                    appViewModel.showedAd()
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    appViewModel.showedAd()
                }
            }
        )
    }
}
