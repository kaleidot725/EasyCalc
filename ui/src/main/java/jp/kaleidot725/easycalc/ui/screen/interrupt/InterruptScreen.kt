package jp.kaleidot725.easycalc.ui.screen.interrupt

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.ui.R

@Composable
fun InterruptScreen(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = onConfirm,
                modifier = Modifier.padding(bottom = 12.dp, end = 12.dp)
            ) {
                Text(text = stringResource(id = R.string.interrupt_ok))
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                modifier = Modifier.padding(bottom = 12.dp, end = 12.dp)
            ) {
                Text(text = stringResource(id = R.string.interrupt_cancel))
            }
        },
        title = { Text(text = stringResource(id = R.string.interrupt_title)) },
        text = { Text(text = stringResource(id = R.string.interrupt_text)) },
        modifier = modifier
    )
}
