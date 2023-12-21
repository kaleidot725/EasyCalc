package jp.kaleidot725.easycalc.core.ui.screen.stats

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.core.domain.model.stats.StatsData
import jp.kaleidot725.easycalc.core.domain.model.utils.ElapsedTimeCalculator
import jp.kaleidot725.easycalc.ui.R
import jp.kaleidot725.easycalc.ui.screen.stats.component.StatsCard

@Composable
fun StatsScreen(
    state: StatsState,
    modifier: Modifier = Modifier
) {
    Crossfade(
        targetState = state.isLoading,
        label = "statsScreen",
        modifier = modifier
    ) { isLoading ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Center)
                )
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.items) { item ->
                    val icon = when (item) {
                        is StatsData.TodaySolvedQuizCount -> Icons.Default.CheckCircleOutline
                        is StatsData.TodayStreakDays -> Icons.Default.CalendarToday
                        is StatsData.TodayTrainingTime -> Icons.Default.Timelapse
                        is StatsData.TotalSolvedQuizCount -> Icons.Default.CheckCircleOutline
                        is StatsData.TotalStreakDays -> Icons.Default.CalendarMonth
                        is StatsData.TotalStudyDays -> Icons.Default.Today
                        is StatsData.TotalTrainingTime -> Icons.Default.Timelapse
                    }
                    val title = when (item) {
                        is StatsData.TodaySolvedQuizCount ->
                            stringResource(id = R.string.home_stats_today_solved_quiz_count_title)

                        is StatsData.TodayStreakDays ->
                            stringResource(id = R.string.home_stats_today_streak_days_title)

                        is StatsData.TodayTrainingTime ->
                            stringResource(id = R.string.home_stats_today_training_time_title)

                        is StatsData.TotalSolvedQuizCount ->
                            stringResource(id = R.string.home_stats_total_solved_quiz_count_title)

                        is StatsData.TotalStreakDays ->
                            stringResource(id = R.string.home_stats_total_streak_days_title)

                        is StatsData.TotalStudyDays ->
                            stringResource(id = R.string.home_stats_total_study_days)

                        is StatsData.TotalTrainingTime ->
                            stringResource(id = R.string.home_stats_total_training_time_time)
                    }
                    val text = when (item) {
                        is StatsData.TodaySolvedQuizCount -> item.count.toString()
                        is StatsData.TodayStreakDays -> item.streakDays.toString()
                        is StatsData.TodayTrainingTime -> {
                            val trainingTime = ElapsedTimeCalculator.calc(item.elapsedSeconds)
                            stringResource(
                                id = R.string.homes_stats_elapsed_time,
                                trainingTime.hours,
                                trainingTime.minutes,
                                trainingTime.seconds
                            )
                        }

                        is StatsData.TotalSolvedQuizCount -> item.totalSolvedQuizCount.toString()
                        is StatsData.TotalStreakDays -> item.streakDays.toString()
                        is StatsData.TotalStudyDays -> item.studyDays.toString()
                        is StatsData.TotalTrainingTime -> {
                            val trainingTime = ElapsedTimeCalculator.calc(item.elapsedSeconds)
                            stringResource(
                                id = R.string.homes_stats_elapsed_time,
                                trainingTime.hours,
                                trainingTime.minutes,
                                trainingTime.seconds
                            )
                        }
                    }
                    StatsCard(
                        icon = icon,
                        title = title,
                        text = text,
                        modifier = Modifier.aspectRatio(1f)
                    )
                }
            }
        }
    }
}
