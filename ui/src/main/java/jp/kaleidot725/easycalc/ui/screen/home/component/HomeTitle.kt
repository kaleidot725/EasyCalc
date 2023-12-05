package jp.kaleidot725.easycalc.ui.screen.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.kaleidot725.easycalc.ui.screen.ext.clickableNoRipple

@Composable
internal fun HomeTitle(
    icon: ImageVector,
    title: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier.then(Modifier.clickableNoRipple { onClick?.invoke() }),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically)
        )

        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1.0f)
        )

        if (onClick != null) {
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "arrow",
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
private fun HomeTitle_Preview() {
    HomeTitle(
        icon = Icons.Default.Calculate,
        title = "足し算",
        onClick = {},
        modifier = Modifier.padding(8.dp)
    )
}
