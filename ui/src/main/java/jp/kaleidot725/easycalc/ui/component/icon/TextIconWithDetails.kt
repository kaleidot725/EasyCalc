package jp.kaleidot725.easycalc.ui.component.icon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.domain.model.text.MathText
import jp.kaleidot725.easycalc.ui.R
import jp.kaleidot725.easycalc.ui.component.divider.VerticalDivider
import jp.kaleidot725.easycalc.ui.screen.resources.MathTextResource

private const val OneMinuteSeconds = 60

@Composable
fun TextIconWithDetails(
    mathText: MathText,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.height(190.dp)
    ) {
        Row {
            TextIcon(
                mathText = mathText,
                modifier = Modifier
                    .width(125.dp)
                    .align(Alignment.CenterVertically)
            )

            VerticalDivider(
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(start = 24.dp),
                ) {
                    Text(
                        text = MathTextResource.getContent(text = mathText),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 40.dp)
                    )

                    IconTextItem(
                        icon = Icons.Default.Numbers,
                        text = stringResource(id = R.string.start_count, mathText.count)
                    )

                    IconTextItem(
                        icon = Icons.Default.DirectionsRun,
                        text = stringResource(id = R.string.start_limit_of_time, mathText.timeout)
                    )

                    val minute = mathText.timeout * mathText.count / OneMinuteSeconds
                    IconTextItem(
                        icon = Icons.Default.Timer,
                        text = stringResource(id = R.string.start_length_of_time, minute),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TextIconWithDetails_Preview() {
    TextIconWithDetails(MathText.SingleDigitsAddition)
}
