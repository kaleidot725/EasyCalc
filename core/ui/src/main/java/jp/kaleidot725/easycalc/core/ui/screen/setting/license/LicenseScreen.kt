package jp.kaleidot725.easycalc.core.ui.screen.setting.license

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.ui.compose.LibrariesContainer
import com.mikepenz.aboutlibraries.util.withContext

@Composable
fun LicenseScreen(
    modifier: Modifier = Modifier
) {
    LibrariesContainer(
        modifier = modifier,
        librariesBlock = { context ->
            Libs.Builder().withContext(context).build().apply {
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    LicenseScreen()
}
