package jp.kaleidot725.easycalc.ui.screen.progress.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.kaleidot725.easycalc.domain.model.text.MathText
import jp.kaleidot725.easycalc.ui.R
import jp.kaleidot725.easycalc.ui.component.text.AutoSizeableText
import jp.kaleidot725.easycalc.ui.screen.progress.FocusMode
import jp.kaleidot725.easycalc.ui.screen.resources.ColorResource
import jp.kaleidot725.easycalc.ui.screen.resources.MathTextResource

@Composable
private fun getFocusColor(current: FocusMode, target: FocusMode): Color {
    return if (current == target) {
        ColorResource.focusColor()
    } else {
        Color.Transparent
    }
}

@Composable
fun QuestionCard(
    first: String,
    second: String,
    category: MathText.Category,
    answer: String,
    remainder: String?,
    isSuccess: Boolean,
    isFailed: Boolean,
    hasRemainder: Boolean,
    focusMode: FocusMode,
    onFocusChange: (FocusMode) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Column(
            modifier = Modifier.padding(vertical = 48.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row {
                AutoSizeableText(
                    text = first,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp,
                    ),
                    modifier = Modifier.weight(1.0f)
                )
                AutoSizeableText(
                    text = MathTextResource.getOperator(category = category),
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp,
                    ),
                    modifier = Modifier.wrapContentWidth()
                )
                AutoSizeableText(
                    text = second,
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp,
                    ),
                    modifier = Modifier.weight(1.0f)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .align(Alignment.CenterHorizontally),
            ) {
                AutoSizeableText(
                    text = "=",
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        fontSize = 36.sp,
                    ),
                    maxLines = 1,
                    modifier = Modifier.wrapContentWidth()
                )

                Box(
                    modifier = Modifier
                        .widthIn(min = 100.dp)
                        .border(ButtonDefaults.outlinedButtonBorder.copy(width = 0.5.dp))
                        .background(getFocusColor(focusMode, FocusMode.ANSWER))
                        .clickable { onFocusChange(FocusMode.ANSWER) }
                        .padding(horizontal = 16.dp)
                ) {
                    AutoSizeableText(
                        text = answer,
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 36.sp,
                        ),
                        maxLines = 1,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                if (hasRemainder) {
                    Text(
                        text = stringResource(id = R.string.remainder),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                    Box(
                        modifier = Modifier
                            .widthIn(min = 100.dp)
                            .border(ButtonDefaults.outlinedButtonBorder.copy(width = 0.5.dp))
                            .background(getFocusColor(focusMode, FocusMode.REMAINDER))
                            .clickable { onFocusChange(FocusMode.REMAINDER) }
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = remainder ?: "",
                            textAlign = TextAlign.Center,
                            fontSize = 36.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }

        Crossfade(
            targetState = isSuccess,
            label = "isSuccess",
            modifier = Modifier.align(Alignment.Center)
        ) { isSuccess ->
            if (isSuccess) {
                Image(
                    painter = painterResource(id = R.drawable.ic_success_big),
                    contentDescription = "success",
                )
            }
        }

        Crossfade(
            targetState = isFailed,
            label = "isFailed",
            modifier = Modifier.align(Alignment.Center)
        ) { isFailed ->
            if (isFailed) {
                Image(
                    painter = painterResource(id = R.drawable.ic_failed_big),
                    contentDescription = "failed"
                )
            }
        }
    }
}

@Preview
@Composable
private fun QuestionCard_Preview() {
    QuestionCard(
        first = "100",
        second = "200",
        category = MathText.Category.ADDITION,
        answer = "360",
        remainder = "3",
        isSuccess = false,
        isFailed = false,
        hasRemainder = false,
        focusMode = FocusMode.ANSWER,
        onFocusChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    )
}
