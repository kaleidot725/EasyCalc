package jp.kaleidot725.easycalc.feature.home.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.kaleidot725.easycalc.core.ui.component.text.AutoSizeableText

@Composable
internal fun HomeStatsItem(
    icon: ImageVector,
    text: String,
    title: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Icon(
                imageVector = icon,
                contentDescription = "Timer",
                modifier = Modifier
                    .size(54.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            AutoSizeableText(
                text = title,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                ),
                maxLines = 2,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Crossfade(
                targetState = text,
                label = "text",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) { text ->
                AutoSizeableText(
                    text = text,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HomeStatsItem(
        icon = Icons.Default.Timer,
        text = "90%",
        title = "Progress"
    )
}
