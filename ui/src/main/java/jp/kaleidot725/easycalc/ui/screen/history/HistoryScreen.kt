package jp.kaleidot725.easycalc.ui.screen.history

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.ui.screen.common.TextIconWithDetails

@Composable
fun HistoryScreen(
    state: HistoryState,
    action: HistoryAction,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier
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
