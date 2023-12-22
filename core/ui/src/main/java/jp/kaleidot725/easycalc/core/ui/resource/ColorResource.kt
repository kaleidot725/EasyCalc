package jp.kaleidot725.easycalc.core.ui.resource

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object ColorResource {
    private const val unselected = 0xFF858585
    private const val yellow = 0xFFE1D8A5
    private const val red = 0xFFF6B3B3

    @Composable
    fun unselected(): Color = Color(unselected)

    @Composable
    fun red(): Color = Color(red)

    @Composable
    fun yellow(): Color = Color(yellow)

    @Composable
    fun materialBackgroundColorFilter(): Color {
        return Color.Black.copy(alpha = 0.25f)
    }

    @Composable
    fun focusColor(): Color {
        return MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
    }

    @Composable
    fun materialBackground(): Color {
        return MaterialTheme.colorScheme.background
    }
}
