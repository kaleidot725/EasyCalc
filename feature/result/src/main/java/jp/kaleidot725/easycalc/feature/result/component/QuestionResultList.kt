package jp.kaleidot725.easycalc.feature.result.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.easycalc.core.domain.model.question.Answer
import jp.kaleidot725.easycalc.core.domain.model.question.QAList
import jp.kaleidot725.easycalc.core.domain.model.question.Question
import jp.kaleidot725.easycalc.core.domain.model.text.MathText
import jp.kaleidot725.easycalc.core.ui.R
import jp.kaleidot725.easycalc.core.ui.component.divider.HorizontalDivider
import jp.kaleidot725.easycalc.core.ui.resource.MathTextResource

@Composable
internal fun QuestionResultList(
    qalist: QAList,
    modifier: Modifier = Modifier
) {
    val yourAnswerWeight = 3.0f
    val answerWeight = 1.5f

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Row {
                TableCellTitle(
                    text = stringResource(id = R.string.result_your_answer_title),
                    weight = yourAnswerWeight
                )
                TableCellTitle(
                    text = stringResource(id = R.string.result_answer_title),
                    weight = answerWeight
                )
            }

            qalist.values.forEach { value ->
                HorizontalDivider()

                Row {
                    val operator = MathTextResource.getOperator(category = value.q.category)
                    val yourAnswer = if (value.q.hasRemainder) {
                        stringResource(
                            R.string.result_answer_with_remainder,
                            value.q.one.toIntOrNull() ?: 0,
                            operator,
                            value.q.two.toIntOrNull() ?: 0,
                            value.a.value.toIntOrNull() ?: 0,
                            value.a.remainder.toIntOrNull() ?: 0,
                        )
                    } else {
                        stringResource(
                            R.string.result_answer,
                            value.q.one.toIntOrNull() ?: 0,
                            operator,
                            value.q.two.toIntOrNull() ?: 0,
                            value.a.value.toIntOrNull() ?: 0,
                        )
                    }

                    val icon = when (value.a) {
                        is Answer.Failed -> R.drawable.ic_failed
                        is Answer.Success -> R.drawable.ic_success
                    }
                    val answer = if (value.q.hasRemainder) {
                        value.q.answer + " " + stringResource(id = R.string.remainder) + " " + value.q.remainder
                    } else {
                        value.q.answer
                    }
                    TableCellWithIcon(text = yourAnswer, icon = icon, weight = yourAnswerWeight)
                    TableCell(text = answer, weight = answerWeight)
                }
            }
        }
    }
}

@Composable
private fun RowScope.TableCellTitle(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .weight(weight)
            .height(36.dp)
            .padding(8.dp)
    )
}

@Composable
private fun RowScope.TableCellWithIcon(
    text: String,
    icon: Int,
    weight: Float
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .weight(weight)
            .height(36.dp)
            .padding(8.dp)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
        )

        Image(
            painter = painterResource(icon),
            contentDescription = "success",
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .weight(weight)
            .height(36.dp)
            .padding(8.dp)
    )
}

@Preview
@Composable
private fun QuestionResultList_Preview() {
    val qaList =
        QAList().apply {
            add(
                Question(
                    100.toString(),
                    200.toString(),
                    300.toString(),
                    MathText.Category.ADDITION
                ),
                Answer.Success(
                    300.toString(),
                    0.toString()
                )
            )
            add(
                Question(
                    100.toString(),
                    200.toString(),
                    300.toString(),
                    MathText.Category.ADDITION
                ),
                Answer.Failed(
                    200.toString(),
                    0.toString()
                )
            )
            add(
                Question(
                    100.toString(),
                    200.toString(),
                    300.toString(),
                    MathText.Category.ADDITION
                ),
                Answer.Success(
                    300.toString(),
                    0.toString()
                )
            )
            add(
                Question(
                    100.toString(),
                    200.toString(),
                    300.toString(),
                    MathText.Category.ADDITION
                ),
                Answer.Failed(
                    400.toString(),
                    0.toString()
                ),
            )
        }
    QuestionResultList(qaList)
}
