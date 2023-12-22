package jp.kaleidot725.easycalc.feature.result.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.core.domain.model.question.QAList
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.domain.model.utils.ElapsedTimeCalculator
import jp.kaleidot725.easycalc.core.ui.R
import jp.kaleidot725.easycalc.core.ui.component.divider.VerticalDivider
import jp.kaleidot725.easycalc.core.ui.component.icon.IconTextItem
import jp.kaleidot725.easycalc.core.ui.component.icon.TextIcon
import jp.kaleidot725.easycalc.core.ui.resource.MathTextResource

@Composable
internal fun MathTextResult(
    mathText: MathText,
    qaList: QAList,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    var height by remember { mutableStateOf(220.dp) }

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.height(height)
    ) {
        Row {
            TextIcon(
                mathText = mathText,
                modifier = Modifier
                    .width(125.dp)
                    .align(Alignment.CenterVertically)
            )

            VerticalDivider(modifier = Modifier.padding(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically)
                    .onGloballyPositioned { layout ->
                        height = with(density) { layout.size.height.toDp() }
                    }
                    .padding(24.dp),
            ) {
                Text(
                    text = MathTextResource.getContent(text = mathText),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )

                val timePeriod = ElapsedTimeCalculator.calc(qaList.startTime, qaList.finishTime)
                IconTextItem(
                    icon = Icons.Default.Timer,
                    text = stringResource(
                        id = R.string.result_elapsed_time,
                        timePeriod.hours,
                        timePeriod.minutes,
                        timePeriod.seconds
                    ),
                )

                IconTextItem(
                    icon = Icons.Default.Numbers,
                    text = stringResource(
                        id = R.string.result_count,
                        qaList.successCount,
                        qaList.questionCount
                    ),
                )

                IconTextItem(
                    icon = Icons.Default.CheckBox,
                    text = stringResource(id = R.string.result_percent, qaList.percent),
                )
            }
        }
    }
}

@Preview
@Composable
private fun MathTextResult_Preview() {
    MathTextResult(
        MathText.SingleDigitsAddition,
        QAList()
    )
}
