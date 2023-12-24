package jp.kaleidot725.easycalc.core.ui.component.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Thunderstorm
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import jp.kaleidot725.easycalc.core.ui.resource.ColorResource

@Composable
fun RowScope.SimpleBottomNavigationItem(
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    selected: Boolean,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        icon = icon,
        label = label,
        selected = selected,
        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = MaterialTheme.colorScheme.onBackground,
            selectedIconColor = MaterialTheme.colorScheme.onBackground,
            unselectedTextColor = ColorResource.unselected(),
            unselectedIconColor = ColorResource.unselected(),
        ),
        onClick = onClick,
    )
}

@Preview
@Composable
private fun SimpleBottomNavigationItemPreview() {
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
