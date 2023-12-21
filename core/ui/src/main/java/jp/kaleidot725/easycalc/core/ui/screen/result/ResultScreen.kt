package jp.kaleidot725.easycalc.core.ui.screen.result

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.ui.R
import jp.kaleidot725.easycalc.ui.screen.result.component.MathTextResult
import jp.kaleidot725.easycalc.ui.screen.result.component.QuestionResultList

@Composable
fun ResultScreen(
    state: ResultState,
    action: ResultAction,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        action.popBack()
    }

    Box(
        modifier = modifier.then(Modifier.padding(16.dp))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            MathTextResult(
                mathText = state.mathText,
                qaList = state.qaList,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            QuestionResultList(
                qalist = state.qaList,
                modifier = Modifier.wrapContentHeight()
            )

            Spacer(modifier = Modifier.height(80.dp))
        }

        ExtendedFloatingActionButton(
            text = {
                Text(text = stringResource(id = R.string.result_ok))
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(id = R.string.result_ok)
                )
            },
            onClick = { action.finish() },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}
