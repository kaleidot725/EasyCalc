package jp.kaleidot725.easycalc.feature.mylist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.core.ui.R
import jp.kaleidot725.easycalc.core.ui.component.icon.TextIconWithDetails

@Composable
fun MyListScreen(
    state: MyListState,
    action: MyListAction,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        if (state.mathTexts.isNotEmpty) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                modifier = Modifier.fillMaxSize()
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

                item {
                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        } else {
            Text(
                text = stringResource(id = R.string.mylist_not_found),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 48.dp)
            )
        }
    }
}
