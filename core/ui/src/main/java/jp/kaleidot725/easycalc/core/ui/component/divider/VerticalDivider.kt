package jp.kaleidot725.easycalc.core.ui.component.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
) {
    val targetThickness = if (thickness == Dp.Hairline) {
        (1f / LocalDensity.current.density).dp
    } else {
        thickness
    }
    Box(
        modifier
            .fillMaxHeight()
            .width(targetThickness)
            .background(color = color)
    )
}

@Preview
@Composable
private fun VerticalDivider_Preview() {
    Row(
        modifier = Modifier
            .size(100.dp)
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.weight(1.0f))
        VerticalDivider()
        Spacer(modifier = Modifier.weight(1.0f))
    }
}
