package jp.kaleidot725.easycalc

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.getValue
import androidx.core.os.LocaleListCompat
import jp.kaleidot725.easycalc.compose.app.ComposeApp
import jp.kaleidot725.easycalc.compose.app.ComposeAppEvent
import jp.kaleidot725.easycalc.compose.app.ComposeAppViewModel
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
                    }
                }
            )
            ComposeApp(
                appState = appState,
                appAction = appViewModel
            )
        }
    }
}
