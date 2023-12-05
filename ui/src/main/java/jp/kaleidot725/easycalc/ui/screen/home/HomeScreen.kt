package jp.kaleidot725.easycalc.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle.Event.*
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import jp.kaleidot725.easycalc.ui.screen.home.component.HomeCategoryList

@Composable
fun HomeScreen(
    state: HomeState,
    action: HomeAction,
    modifier: Modifier = Modifier,
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                ON_RESUME -> action.refresh()
                else -> {}
            }
        }
        lifecycle.addObserver(observer)
        onDispose { lifecycle.removeObserver(observer) }
    }

    Box(modifier) {
        HomeCategoryList(
            state = state,
            action = action,
            modifier = Modifier.fillMaxSize()
        )

        if (state.enableAd) {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                factory = { context ->
                    AdView(context).apply {
                        setAdSize(AdSize.BANNER)
                        setAdUnitId(state.adUnitId)
                        loadAd(AdRequest.Builder().build())
                    }
                }
            )
        }
    }
}
