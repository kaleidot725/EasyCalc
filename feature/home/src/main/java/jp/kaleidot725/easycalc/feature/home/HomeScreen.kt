package jp.kaleidot725.easycalc.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle.Event.ON_RESUME
import androidx.lifecycle.LifecycleEventObserver
import jp.kaleidot725.easycalc.feature.home.component.HomeCategoryList

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

    HomeCategoryList(
        state = state,
        action = action,
        modifier = modifier
    )
}
