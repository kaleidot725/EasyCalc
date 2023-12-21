package jp.kaleidot725.easycalc.core.ui.screen.quiz.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.domain.model.text.MathTextSet
import jp.kaleidot725.easycalc.ui.R
import jp.kaleidot725.easycalc.ui.component.icon.TextIcon
import jp.kaleidot725.easycalc.ui.extention.clickableNoRipple

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun QuizCategoryList(
    mathTextSet: MathTextSet,
    tabCategory: QuizTabCategory,
    onClickCategory: (MathText.Category) -> Unit,
    onClickText: (MathText) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 110.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        if (
            mathTextSet.additions.isNotEmpty &&
            (tabCategory == QuizTabCategory.ALL || tabCategory == QuizTabCategory.ADDITION)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                QuizCategoryTitle(
                    icon = Icons.Default.Calculate,
                    title = stringResource(id = R.string.category_addition),
                    modifier = Modifier
                        .clickableNoRipple { onClickCategory(MathText.Category.ADDITION) }
                        .animateItemPlacement()
                )
            }

            items(
                key = { it.id.value },
                items = mathTextSet.additions.value
            ) { text ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height(180.dp)
                        .width(110.dp)
                        .clickable { onClickText(text) }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        TextIcon(mathText = text, modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }

        if (
            mathTextSet.subtractions.isNotEmpty &&
            (tabCategory == QuizTabCategory.ALL || tabCategory == QuizTabCategory.SUBTRACTION)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                QuizCategoryTitle(
                    icon = Icons.Default.Calculate,
                    title = stringResource(id = R.string.category_subtraction),
                    modifier = Modifier
                        .clickableNoRipple { onClickCategory(MathText.Category.SUBTRACTION) }
                        .animateItemPlacement()
                )
            }

            items(
                key = { it.id.value },
                items = mathTextSet.subtractions.value,
            ) { text ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height(180.dp)
                        .width(110.dp)
                        .clickable { onClickText(text) }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        TextIcon(mathText = text, modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }

        if (
            mathTextSet.multiplications.isNotEmpty &&
            (tabCategory == QuizTabCategory.ALL || tabCategory == QuizTabCategory.MULTIPLICATION)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                QuizCategoryTitle(
                    icon = Icons.Default.Calculate,
                    title = stringResource(id = R.string.category_multiplication),
                    modifier = Modifier
                        .clickableNoRipple { onClickCategory(MathText.Category.MULTIPLICATION) }
                        .animateItemPlacement()
                )
            }

            items(
                key = { it.id.value },
                items = mathTextSet.multiplications.value,
            ) { text ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height(180.dp)
                        .width(110.dp)
                        .clickable { onClickText(text) }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        TextIcon(mathText = text, modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }

        if (
            mathTextSet.divisions.isNotEmpty &&
            (tabCategory == QuizTabCategory.ALL || tabCategory == QuizTabCategory.DIVISION)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                QuizCategoryTitle(
                    icon = Icons.Default.Calculate,
                    title = stringResource(id = R.string.category_division),
                    modifier = Modifier
                        .clickableNoRipple { onClickCategory(MathText.Category.DIVISION) }
                        .animateItemPlacement()
                )
            }

            items(
                key = { it.id.value },
                items = mathTextSet.divisions.value,
            ) { text ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height(180.dp)
                        .width(110.dp)
                        .clickable { onClickText(text) }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        TextIcon(mathText = text, modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }

        if (
            mathTextSet.multiplicationTable.isNotEmpty &&
            (tabCategory == QuizTabCategory.ALL || tabCategory == QuizTabCategory.MULTIPLICATION_TABLE)
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                QuizCategoryTitle(
                    icon = Icons.Default.Calculate,
                    title = stringResource(id = R.string.category_multiplication_table),
                    modifier = Modifier
                        .clickableNoRipple { onClickCategory(MathText.Category.MULTIPLICATION_TABLE) }
                        .animateItemPlacement()
                )
            }

            items(
                key = { it.id.value },
                items = mathTextSet.multiplicationTable.value,
            ) { text ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height(180.dp)
                        .width(110.dp)
                        .clickable { onClickText(text) }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        TextIcon(mathText = text, modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        }

        item(span = { GridItemSpan(3) }) {
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}
