package jp.kaleidot725.easycalc.feature.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.core.ui.R
import jp.kaleidot725.easycalc.feature.quiz.component.QuizCategoryList
import jp.kaleidot725.easycalc.feature.quiz.component.QuizTabCategory

@Composable
fun QuizScreen(
    state: QuizState,
    action: QuizAction,
    modifier: Modifier = Modifier,
) {
    var tabSelected by rememberSaveable { mutableStateOf(QuizTabCategory.ALL.index) }

    Box(modifier = modifier) {
        Column {
            ScrollableTabRow(
                selectedTabIndex = tabSelected,
                edgePadding = 4.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
            ) {
                QuizTabCategory.values().forEach { category ->
                    Tab(
                        text = {
                            Text(
                                text = when (category) {
                                    QuizTabCategory.ALL -> {
                                        stringResource(id = R.string.category_all)
                                    }

                                    QuizTabCategory.ADDITION -> {
                                        stringResource(id = R.string.category_addition)
                                    }

                                    QuizTabCategory.SUBTRACTION -> {
                                        stringResource(id = R.string.category_subtraction)
                                    }

                                    QuizTabCategory.MULTIPLICATION -> {
                                        stringResource(id = R.string.category_multiplication)
                                    }

                                    QuizTabCategory.MULTIPLICATION_TABLE -> {
                                        stringResource(id = R.string.category_multiplication_table)
                                    }

                                    QuizTabCategory.DIVISION -> {
                                        stringResource(id = R.string.category_division)
                                    }
                                }
                            )
                        },
                        selected = tabSelected == category.index,
                        onClick = { tabSelected = category.index }
                    )
                }
            }

            QuizCategoryList(
                mathTextSet = state.mathTextSet,
                tabCategory = QuizTabCategory.entries.first { it.index == tabSelected },
                onClickCategory = action::clickCategory,
                onClickText = action::clickText,
                modifier = Modifier.weight(1.0f)
            )
        }
    }
}
