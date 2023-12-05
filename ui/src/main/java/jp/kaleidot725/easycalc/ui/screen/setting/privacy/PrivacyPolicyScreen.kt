package jp.kaleidot725.easycalc.ui.screen.setting.privacy

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun PrivacyPolicyScreen(
    modifier: Modifier = Modifier
) {
    val webViewState = rememberWebViewState(url = "https://example.com/")
    WebView(
        state = webViewState,
        modifier = modifier
    )
}

@Preview
@Composable
private fun Preview() {
    PrivacyPolicyScreen()
}
