package jp.kaleidot725.easycalc.core.ui.screen.progress.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.ui.screen.main.md_theme_light_inversePrimary
import jp.kaleidot725.easycalc.ui.screen.resources.ColorResource

private const val ZERO = 0f
private const val WARNING = 1f / 3f
private const val CAUTION = 1f / 3f * 2f

@Composable
fun ProgressBar(progress: Float, modifier: Modifier = Modifier) {
    val color = when {
        (progress in ZERO..WARNING) -> ColorResource.red()
        (progress in WARNING..CAUTION) -> ColorResource.yellow()
        else -> md_theme_light_inversePrimary
    }
    LinearProgressIndicator(
        progress = progress,
        color = color,
        modifier = modifier
    )
}

@Preview
@Composable
private fun ProgressBar_Preview() {
    val animation = rememberInfiniteTransition(label = "anime")
    val progress by animation.animateFloat(
        label = "anime",
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
    )

    ProgressBar(
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    )
}
