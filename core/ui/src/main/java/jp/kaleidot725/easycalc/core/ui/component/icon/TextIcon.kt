package jp.kaleidot725.easycalc.core.ui.component.icon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.ui.component.text.AutoSizeableText
import jp.kaleidot725.easycalc.core.ui.screen.resources.MathTextResource

@Composable
fun TextIcon(mathText: MathText, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        AutoSizeableText(
            text = MathTextResource.getTitle(text = mathText),
            maxLines = 2,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = MathTextResource.getEmoji(text = mathText),
            fontSize = 48.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
private fun CategoryTextItem_Preview() {
    Card(
        modifier = Modifier
            .height(190.dp)
            .width(125.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            TextIcon(
                mathText = MathText.SingleDigitsAddition,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
