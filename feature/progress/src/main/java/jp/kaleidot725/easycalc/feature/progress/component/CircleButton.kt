package jp.kaleidot725.easycalc.feature.progress.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.kaleidot725.easycalc.core.ui.component.text.AutoSizeableText

@Composable
internal fun CircleButton(
    text: String,
    fontSize: TextUnit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.clickable { onClick() }.clip(CircleShape)
    ) {
        AutoSizeableText(
            text = text,
            style = TextStyle(fontSize = fontSize),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun CircleButton_Preview() {
    CircleButton(text = "1", fontSize = 32.sp, onClick = {}, modifier = Modifier.size(80.dp))
}
