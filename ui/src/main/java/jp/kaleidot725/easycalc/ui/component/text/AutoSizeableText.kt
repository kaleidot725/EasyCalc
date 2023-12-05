package jp.kaleidot725.easycalc.ui.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun AutoSizeableText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
) {
    var fontSize by remember(text) { mutableStateOf(style.fontSize) }

    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        maxLines = maxLines,
        modifier = modifier,
        overflow = TextOverflow.Ellipsis,
        style = style,
        onTextLayout = {
            if (it.hasVisualOverflow && fontSize > 0.sp) {
                fontSize = (fontSize.value - 1.0F).sp
            }
        }
    )
}
