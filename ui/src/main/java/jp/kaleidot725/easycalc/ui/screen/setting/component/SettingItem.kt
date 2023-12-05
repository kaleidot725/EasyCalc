package jp.kaleidot725.easycalc.ui.screen.setting.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun SettingItem(
    title: String,
    icon: ImageVector,
    iconDescription: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconDescription,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically)
        )

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .weight(1.0f)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MaterialTheme {
        Surface {
            SettingItem(
                title = "Title",
                icon = Icons.Default.Star,
                iconDescription = "star",
                modifier = Modifier
                    .padding(4.dp)
                    .width(320.dp)
                    .height(40.dp)
                    .wrapContentHeight()
            )
        }
    }
}
