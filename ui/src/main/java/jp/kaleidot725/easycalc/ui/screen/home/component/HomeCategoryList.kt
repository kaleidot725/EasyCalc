package jp.kaleidot725.easycalc.ui.screen.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.domain.model.utils.ElapsedTimeCalculator
import jp.kaleidot725.easycalc.ui.R
import jp.kaleidot725.easycalc.ui.component.icon.TextIcon
import jp.kaleidot725.easycalc.ui.screen.home.HomeAction
import jp.kaleidot725.easycalc.ui.screen.home.HomeState

@Composable
internal fun HomeCategoryList(
    state: HomeState,
    action: HomeAction,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 110.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            val trainingTime =
                ElapsedTimeCalculator.calc(state.todayTrainingTime?.elapsedSeconds ?: 0)
            val trainingTimeText =
                stringResource(
                    id = R.string.homes_stats_elapsed_time,
                    trainingTime.hours,
                    trainingTime.minutes,
                    trainingTime.seconds
                )
            HomeStats(
                solvedCount = state.todaySolvedQuizCount?.count?.toString() ?: "-",
                streakDays = state.todayStreakDays?.streakDays?.toString() ?: "-",
                trainingTime = trainingTimeText,
                onClick = action::clickStats,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        if (state.histories.isNotEmpty) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                HomeTitle(
                    icon = Icons.Default.HistoryEdu,
                    title = stringResource(id = R.string.category_history),
                    onClick = action::clickHistory,
                )
            }

            items(
                key = { it.id.value + "history" },
                items = state.histories.value,
            ) { text ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height(180.dp)
                        .width(110.dp)
                        .clickable { action.clickText(text) }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        TextIcon(mathText = text, modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }

        if (state.mylist.isNotEmpty) {
            item(span = { GridItemSpan(3) }) {
                HomeTitle(
                    icon = Icons.Default.HistoryEdu,
                    title = stringResource(id = R.string.mylist_title),
                    onClick = action::clickMyList
                )
            }

            items(
                key = { it.id.value + "mylist" },
                items = state.mylist.value,
            ) { text ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height(180.dp)
                        .width(110.dp)
                        .clickable { action.clickText(text) }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        TextIcon(mathText = text, modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}
