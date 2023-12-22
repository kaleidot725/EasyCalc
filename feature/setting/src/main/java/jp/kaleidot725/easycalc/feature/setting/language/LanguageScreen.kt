package jp.kaleidot725.easycalc.feature.setting.language

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.core.domain.model.language.Language
import jp.kaleidot725.easycalc.core.ui.R
import jp.kaleidot725.easycalc.feature.setting.component.SettingCheckItem

@Composable
fun LanguageScreen(
    state: LanguageState,
    action: LanguageAction,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        SettingCheckItem(
            title = stringResource(R.string.language_english),
            icon = Icons.Default.Language,
            checked = state.englishSelected,
            onCheckedChange = { action.clickLanguage(Language.English) },
            iconDescription = "english",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(12.dp)
        )

        Divider()

        SettingCheckItem(
            title = stringResource(R.string.language_japanese),
            icon = Icons.Default.Language,
            checked = state.japaneseSelected,
            onCheckedChange = { action.clickLanguage(Language.Japanese) },
            iconDescription = "japanese",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(12.dp)
        )

        Divider()

        SettingCheckItem(
            title = stringResource(R.string.language_german),
            icon = Icons.Default.Language,
            checked = state.germanSelected,
            onCheckedChange = { action.clickLanguage(Language.German) },
            iconDescription = "German",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(12.dp)
        )

        Divider()

        SettingCheckItem(
            title = stringResource(R.string.language_spain),
            icon = Icons.Default.Language,
            checked = state.spainSelected,
            onCheckedChange = { action.clickLanguage(Language.Spain) },
            iconDescription = "Spain",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(12.dp)
        )

        Divider()

        SettingCheckItem(
            title = stringResource(R.string.language_indonesia),
            icon = Icons.Default.Language,
            checked = state.indonesiaSelected,
            onCheckedChange = { action.clickLanguage(Language.Indonesia) },
            iconDescription = "Indonesia",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(12.dp)
        )

        Divider()

        SettingCheckItem(
            title = stringResource(R.string.language_italy),
            icon = Icons.Default.Language,
            checked = state.italySelected,
            onCheckedChange = { action.clickLanguage(Language.Italy) },
            iconDescription = "Italy",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(12.dp)
        )

        Divider()

        SettingCheckItem(
            title = stringResource(R.string.language_portugal),
            icon = Icons.Default.Language,
            checked = state.portugalSelected,
            onCheckedChange = { action.clickLanguage(Language.Portugal) },
            iconDescription = "Portugal",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(12.dp)
        )

        Divider()

        SettingCheckItem(
            title = stringResource(R.string.language_thai),
            icon = Icons.Default.Language,
            checked = state.thaiSelected,
            onCheckedChange = { action.clickLanguage(Language.Thai) },
            iconDescription = "Thai",
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(12.dp)
        )

        Divider()

        SettingCheckItem(
            title = stringResource(R.string.language_chinese),
            icon = Icons.Default.Language,
            checked = state.chineseSelected,
            onCheckedChange = { action.clickLanguage(Language.Chinese) },
            iconDescription = "Chinese",
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
    LanguageScreen(
        state = LanguageState(),
        action = object : LanguageAction {
            override fun clickLanguage(language: Language) = TODO()
            override fun popBack() = TODO()
        },
        modifier = Modifier,
    )
}
