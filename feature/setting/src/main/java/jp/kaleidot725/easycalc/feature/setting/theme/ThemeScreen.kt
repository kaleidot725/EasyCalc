package jp.kaleidot725.easycalc.feature.setting.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.core.domain.model.theme.Theme
import jp.kaleidot725.easycalc.core.ui.R
import jp.kaleidot725.easycalc.feature.setting.component.SettingCheckItem

@Composable
fun ThemeScreen(
    state: ThemeState,
    action: ThemeAction,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        SettingCheckItem(
            title = stringResource(id = R.string.theme_light),
            icon = Icons.Default.LightMode,
            checked = state.lightSelected,
            onCheckedChange = { action.clickTheme(Theme.LIGHT) },
            iconDescription = "LightMode",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(12.dp)
        )

        Divider()

        SettingCheckItem(
            title = stringResource(id = R.string.theme_dark),
            icon = Icons.Default.DarkMode,
            checked = state.darkSelected,
            onCheckedChange = { action.clickTheme(Theme.DARK) },
            iconDescription = "DarkMode",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(12.dp)
        )

        Divider()

        SettingCheckItem(
            title = stringResource(id = R.string.theme_system),
            icon = Icons.Default.Sync,
            checked = state.systemSelected,
            onCheckedChange = { action.clickTheme(Theme.SYSTEM) },
            iconDescription = "Sync",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(12.dp)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    ThemeScreen(
        state = ThemeState(),
        action = object : ThemeAction {
            override fun refresh() = TODO()
            override fun clickTheme(theme: Theme) = TODO()
            override fun popBack() = TODO()
        },
        modifier = Modifier,
    )
}
