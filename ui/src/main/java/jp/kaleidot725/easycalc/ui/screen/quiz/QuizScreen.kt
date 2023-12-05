package jp.kaleidot725.easycalc.ui.screen.quiz

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import jp.kaleidot725.easycalc.ui.R
import jp.kaleidot725.easycalc.ui.screen.quiz.component.QuizCategoryList
import jp.kaleidot725.easycalc.ui.screen.quiz.component.QuizTabCategory

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
                                    QuizTabCategory.ALL -> stringResource(id = R.string.category_all)
                                    QuizTabCategory.ADDITION -> stringResource(id = R.string.category_addition)
                                    QuizTabCategory.SUBTRACTION -> stringResource(id = R.string.category_subtraction)
                                    QuizTabCategory.MULTIPLICATION -> stringResource(
                                        id = R.string.category_multiplication
                                    )
                                    QuizTabCategory.MULTIPLICATION_TABLE -> stringResource(
                                        id = R.string.category_multiplication_table
                                    )
                                    QuizTabCategory.DIVISION -> stringResource(id = R.string.category_division)
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
                tabCategory = QuizTabCategory.values().first { it.index == tabSelected },
                onClickCategory = action::clickCategory,
                onClickText = action::clickText,
                modifier = Modifier.weight(1.0f)
            )
        }

        if (state.enableAd) {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                factory = { context ->
                    AdView(context).apply {
                        setAdSize(AdSize.BANNER)
                        setAdUnitId(state.adUnitId)
                        loadAd(AdRequest.Builder().build())
                    }
                }
            )
        }
    }
}
