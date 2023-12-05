package jp.kaleidot725.easycalc.ui.screen.start.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import jp.kaleidot725.easycalc.ui.component.icon.IconTextItem
import jp.kaleidot725.easycalc.ui.component.icon.TextIcon
import jp.kaleidot725.easycalc.ui.component.divider.VerticalDivider
import jp.kaleidot725.easycalc.ui.extention.clickableNoRipple
import jp.kaleidot725.easycalc.ui.screen.resources.MathTextResource

private const val OneMinuteSeconds = 60

@Composable
fun StartTextCard(
    mathText: MathText,
    isFavorite: Boolean,
    onChangeFavorite: (Boolean) -> Unit,
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
                    .align(Alignment.CenterVertically)
                    .width(125.dp)
            )

            VerticalDivider(modifier = Modifier.padding(vertical = 16.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
            ) {
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(width = 24.dp, height = 32.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 0.dp,
                                bottomEnd = 4.dp,
                                bottomStart = 4.dp
                            )
                        )
                        .clickableNoRipple { onChangeFavorite(!isFavorite) }
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.BookmarkAdded else Icons.Default.BookmarkBorder,
                        contentDescription = "favorite",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(18.dp)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
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
private fun StartTextCard_Preview() {
    StartTextCard(
        mathText = MathText.SingleDigitsAddition,
        isFavorite = true,
        onChangeFavorite = {}
    )
}

@Preview
@Composable
private fun StartTextCard_isFavorite_False_Preview() {
    StartTextCard(
        mathText = MathText.SingleDigitsAddition,
        isFavorite = false,
        onChangeFavorite = {}
    )
}
