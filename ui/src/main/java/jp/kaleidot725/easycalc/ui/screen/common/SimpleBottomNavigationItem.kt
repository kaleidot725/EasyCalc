package jp.kaleidot725.easycalc.ui.screen.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import jp.kaleidot725.easycalc.ui.screen.resources.ColorResource

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
