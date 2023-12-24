package jp.kaleidot725.easycalc.core.ui.component.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Thunderstorm
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SimpleBottomNavigation(
    modifier: Modifier = Modifier,
    content: @Composable (RowScope.() -> Unit),
) {
    NavigationBar(
        contentColor = Color.Transparent,
        containerColor = Color.Transparent,
        content = content,
        modifier = modifier
    )
}

@Preview
@Composable
private fun SimpleBottomNavigationPreview() {
    Surface {
        SimpleBottomNavigation {
            SimpleBottomNavigationItem(
                icon = {
                    Icon(imageVector = Icons.Default.Thunderstorm, contentDescription = null)
                },
                label = {
                    Text(text = "Thunder")
                },
                selected = false,
                onClick = { /* TODO */ }
            )
            SimpleBottomNavigationItem(
                icon = {
                    Icon(imageVector = Icons.Default.Cloud, contentDescription = null)
                },
                label = {
                    Text(text = "Cloud")
                },
                selected = true,
                onClick = { /* TODO */ }
            )
        }
    }
}