package jp.kaleidot725.easycalc.ui.screen.common

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

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
