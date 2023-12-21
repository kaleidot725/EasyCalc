package jp.kaleidot725.easycalc.core.ui.screen.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.ui.component.icon.TextIconWithDetails

@Composable
fun CategoryScreen(
    state: CategoryState,
    action: CategoryAction,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier,
    ) {
        items(
            items = state.mathTexts.value,
            key = { mathText -> mathText.id.value }
        ) { mathText ->
            TextIconWithDetails(
                mathText = mathText,
                modifier = Modifier.clickable { action.clickText(mathText) }
            )
        }
    }
}
