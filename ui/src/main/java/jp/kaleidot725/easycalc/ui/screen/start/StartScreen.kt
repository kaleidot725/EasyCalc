package jp.kaleidot725.easycalc.ui.screen.start

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.ui.R
import jp.kaleidot725.easycalc.ui.screen.start.component.StartTextCard

@Composable
fun StartScreen(
    state: StartState,
    action: StartAction,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.then(Modifier.padding(16.dp))) {
        StartTextCard(
            mathText = state.mathText,
            isFavorite = state.isFavorite,
            onChangeFavorite = { action.toggleFavorite() },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        ExtendedFloatingActionButton(
            text = {
                Text(text = stringResource(id = R.string.start_ok))
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = stringResource(id = R.string.result_retry)
                )
            },
            onClick = { action.startCalculation() },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}
