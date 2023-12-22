package jp.kaleidot725.easycalc.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import jp.kaleidot725.easycalc.core.domain.model.theme.Theme
import jp.kaleidot725.easycalc.core.ui.resource.ColorResource

@Composable
fun ComposeTheme(
    theme: Theme?,
    enableBackgroundFilter: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier) {
        when (theme) {
            Theme.DARK ->
                Surface {
                    DarkTheme(enableBackgroundFilter, content)
                }

            Theme.LIGHT ->
                Surface {
                    LightTheme(enableBackgroundFilter, content)
                }

            Theme.SYSTEM -> {
                Surface {
                    if (isSystemInDarkTheme()) {
                        DarkTheme(enableBackgroundFilter, content)
                    } else {
                        LightTheme(enableBackgroundFilter, content)
                    }
                }
            }

            else -> {
            }
        }
    }
}

@Composable
private fun LightTheme(enableBackgroundFilter: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors
    ) {
        Surface {
            val systemUiController = rememberSystemUiController()
            val backgroundColor = ColorResource.materialBackground()
            val backgroundColorFilter = ColorResource.materialBackgroundColorFilter()
            LaunchedEffect(systemUiController, enableBackgroundFilter) {
                val color = if (enableBackgroundFilter) {
                    backgroundColorFilter.compositeOver(backgroundColor)
                } else {
                    backgroundColor
                }
                systemUiController.setSystemBarsColor(color = color)
            }
            content()
        }
    }
}

@Composable
private fun DarkTheme(enableBackgroundFilter: Boolean, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors
    ) {
        val systemUiController = rememberSystemUiController()
        val backgroundColor = ColorResource.materialBackground()
        val backgroundColorFilter = ColorResource.materialBackgroundColorFilter()
        Surface(color = backgroundColor) {
            LaunchedEffect(systemUiController, enableBackgroundFilter) {
                val color = if (enableBackgroundFilter) {
                    backgroundColorFilter.compositeOver(backgroundColor)
                } else {
                    backgroundColor
                }
                systemUiController.setSystemBarsColor(color = color)
            }
            content()
        }
    }
}
