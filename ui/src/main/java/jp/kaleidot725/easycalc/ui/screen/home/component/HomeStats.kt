package jp.kaleidot725.easycalc.ui.screen.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.StackedBarChart
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.ui.R
import jp.kaleidot725.easycalc.ui.component.divider.VerticalDivider
import jp.kaleidot725.easycalc.ui.extention.clickableNoRipple

@Composable
fun HomeStats(
    streakDays: String,
    solvedCount: String,
    trainingTime: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        HomeTitle(
            icon = Icons.Default.StackedBarChart,
            title = stringResource(id = R.string.home_stats_title),
            onClick = onClick
        )

        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(top = 8.dp)
                .clickableNoRipple { onClick.invoke() }
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(vertical = 8.dp),
            ) {
                HomeStatsItem(
                    icon = Icons.Default.CalendarMonth,
                    text = streakDays,
                    title = stringResource(id = R.string.home_stats_today_streak_days_title),
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.33f)
                )

                VerticalDivider()

                HomeStatsItem(
                    icon = Icons.Default.TaskAlt,
                    text = solvedCount,
                    title = stringResource(id = R.string.home_stats_today_solved_quiz_count_title),
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.33f)
                )

                VerticalDivider()

                HomeStatsItem(
                    icon = Icons.Default.Timelapse,
                    text = trainingTime,
                    title = stringResource(id = R.string.home_stats_today_training_time_title),
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.33f)
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeStats_Preview() {
    HomeStats(
        streakDays = "100",
        solvedCount = "100",
        trainingTime = "60m",
        onClick = {}
    )
}
