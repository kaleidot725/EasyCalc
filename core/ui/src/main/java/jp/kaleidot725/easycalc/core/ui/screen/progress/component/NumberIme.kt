package jp.kaleidot725.easycalc.core.ui.screen.progress.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.MusicOff
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import jp.kaleidot725.easycalc.core.ui.R

@Composable
fun NumberIme(
    isEffectMute: Boolean,
    isBgmMute: Boolean,
    onClickNumber: (Int) -> Unit,
    onSkip: () -> Unit,
    onDelete: () -> Unit,
    onClear: () -> Unit,
    onChangeEffectMute: (Boolean) -> Unit,
    onChangeBgmMute: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    var maxSize by remember { mutableStateOf(80.dp) }

    Box(
        modifier = Modifier
            .onGloballyPositioned { coordinates ->
                val widthMax = with(density) { (coordinates.size.width / 4.5f).toDp() }
                val heightMax = with(density) { (coordinates.size.height / 5.0f).toDp() }
                maxSize = min(widthMax, heightMax)
            }
            .then(modifier),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
            ) {
                CircleIconButton(
                    icon = if (isBgmMute) Icons.Default.MusicOff else Icons.Default.MusicNote,
                    onClick = { onChangeBgmMute(!isBgmMute) },
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                )
                CircleIconButton(
                    icon = if (isEffectMute) Icons.Default.VolumeOff else Icons.Default.VolumeUp,
                    onClick = { onChangeEffectMute(!isEffectMute) },
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                )
                CircleButton(
                    text = stringResource(id = R.string.progress_skip).uppercase(),
                    fontSize = 14.sp,
                    onClick = onSkip,
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                        .align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally)
            ) {
                CircleButton(
                    text = stringResource(id = R.string.progress_number_one),
                    fontSize = 36.sp,
                    onClick = { onClickNumber(1) },
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                        .aspectRatio(1.0f)
                )
                CircleButton(
                    text = stringResource(id = R.string.progress_number_two),
                    fontSize = 36.sp,
                    onClick = { onClickNumber(2) },
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                        .aspectRatio(1.0f)
                )
                CircleButton(
                    text = stringResource(id = R.string.progress_number_three),
                    fontSize = 36.sp,
                    onClick = { onClickNumber(3) },
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                        .aspectRatio(1.0f)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                CircleButton(
                    text = stringResource(id = R.string.progress_number_four),
                    fontSize = 36.sp,
                    onClick = { onClickNumber(4) },
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                        .aspectRatio(1.0f)
                )
                CircleButton(
                    text = stringResource(id = R.string.progress_number_five),
                    fontSize = 36.sp,
                    onClick = { onClickNumber(5) },
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                        .aspectRatio(1.0f)
                )
                CircleButton(
                    text = stringResource(id = R.string.progress_number_six),
                    fontSize = 36.sp,
                    onClick = { onClickNumber(6) },
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                        .aspectRatio(1.0f)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                CircleButton(
                    text = stringResource(id = R.string.progress_number_seven),
                    fontSize = 36.sp,
                    onClick = { onClickNumber(7) },
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                        .aspectRatio(1.0f)
                )
                CircleButton(
                    text = stringResource(id = R.string.progress_number_eight),
                    fontSize = 36.sp,
                    onClick = { onClickNumber(8) },
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                        .aspectRatio(1.0f)
                )
                CircleButton(
                    text = stringResource(id = R.string.progress_number_nine),
                    fontSize = 36.sp,
                    onClick = { onClickNumber(9) },
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                        .aspectRatio(1.0f)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                CircleButton(
                    text = stringResource(id = R.string.progress_number_clear),
                    fontSize = 16.sp,
                    onClick = onClear,
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                        .aspectRatio(1.0f)
                )
                CircleButton(
                    text = stringResource(id = R.string.progress_number_zero),
                    fontSize = 36.sp,
                    onClick = { onClickNumber(0) },
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                        .aspectRatio(1.0f)
                )
                CircleIconButton(
                    icon = Icons.Default.Backspace,
                    onClick = onDelete,
                    modifier = Modifier
                        .widthIn(min = 80.dp, max = maxSize)
                        .aspectRatio(1.0f)
                )
            }
        }
    }
}

@Preview(widthDp = 300, heightDp = 800)
@Composable
private fun NumberIme_Preview() {
    NumberIme(
        modifier = Modifier.fillMaxSize(),
        onSkip = {},
        onClickNumber = {},
        onDelete = {},
        onClear = {},
        isEffectMute = false,
        isBgmMute = false,
        onChangeEffectMute = {},
        onChangeBgmMute = {}
    )
}
