package jp.kaleidot725.easycalc

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.core.os.LocaleListCompat
import androidx.core.view.WindowCompat
import com.google.android.play.core.review.ReviewManagerFactory
import jp.kaleidot725.easycalc.compose.app.ComposeApp
import jp.kaleidot725.easycalc.compose.app.ComposeAppEvent
import jp.kaleidot725.easycalc.compose.app.ComposeAppViewModel
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
}
