package jp.kaleidot725.easycalc.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import jp.kaleidot725.easycalc.core.domain.model.theme.Theme
import jp.kaleidot725.easycalc.core.ui.resource.ColorResource

@Composable
fun ComposeTheme(
    theme: Theme?,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier) {
        when (theme) {
            Theme.DARK ->
                Surface {
                    DarkTheme(content)
                }

            Theme.LIGHT ->
                Surface {
                    LightTheme(content)
                }

            Theme.SYSTEM -> {
                Surface {
                    if (isSystemInDarkTheme()) {
                        DarkTheme(content)
                    } else {
                        LightTheme(content)
                    }
                }
            }

            else -> {
            }
        }
    }
}

@Composable
private fun LightTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors
    ) {
        Surface {
            content()
        }
    }
}

@Composable
private fun DarkTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors
    ) {
        val backgroundColor = ColorResource.materialBackground()
        Surface(color = backgroundColor) {
            content()
        }
    }
}
