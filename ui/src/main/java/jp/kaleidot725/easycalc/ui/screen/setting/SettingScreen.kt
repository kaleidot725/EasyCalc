package jp.kaleidot725.easycalc.ui.screen.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.CardMembership
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.ui.R
import jp.kaleidot725.easycalc.ui.screen.setting.component.SettingItem

@Composable
fun SettingScreen(
    onNavigateLicense: () -> Unit,
    onNavigateTheme: () -> Unit,
    onNavigateLanguage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SettingItem(
            title = stringResource(id = R.string.theme_title),
            icon = Icons.Default.ColorLens,
            iconDescription = "theme",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clickable(onClick = onNavigateTheme)
                .padding(12.dp)
        )

        Divider()

        SettingItem(
            title = stringResource(id = R.string.language_title),
            icon = Icons.Default.Language,
            iconDescription = "language",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clickable(onClick = onNavigateLanguage)
                .padding(12.dp)
        )

//                Divider()
//
//                SettingItem(
//                    title = stringResource(id = R.string.privacy_policy_title),
//                    icon = Icons.Default.PrivacyTip,
//                    iconDescription = "privacy",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(64.dp)
//                        .clickable(onClick = onNavigatePrivacyPolicy)
//                        .padding(12.dp)
//                )

        Divider()

        SettingItem(
            title = stringResource(id = R.string.license_title),
            icon = Icons.Default.CardMembership,
            iconDescription = "license",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clickable(onClick = onNavigateLicense)
                .padding(12.dp)
        )

        Divider()

        SettingItem(
            title = stringResource(R.string.version_info),
            icon = Icons.Default.Apps,
            iconDescription = "version",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clickable { }
                .padding(12.dp)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    SettingScreen(
        onNavigateLicense = {},
        onNavigateTheme = {},
        onNavigateLanguage = {},
    )
}
