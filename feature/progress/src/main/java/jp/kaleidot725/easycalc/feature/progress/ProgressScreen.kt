package jp.kaleidot725.easycalc.feature.progress

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import jp.kaleidot725.easycalc.feature.progress.component.NumberIme
import jp.kaleidot725.easycalc.feature.progress.component.ProgressBar
import jp.kaleidot725.easycalc.feature.progress.component.QuestionCard

private val enter = slideInHorizontally { width -> width } + fadeIn()
private val exit = slideOutHorizontally { height -> -height } + fadeOut()

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ProgressScreen(
    state: ProgressState,
    action: ProgressAction,
    modifier: Modifier = Modifier,
) {
    val owner = LocalLifecycleOwner.current
    var paused by remember { mutableStateOf(false) }
    val progressAnimation = remember(state.question) { Animatable(state.timeoutProgress) }
    val progressAnimationRef = rememberUpdatedState(progressAnimation)

    DisposableEffect(owner) {
        val observer = object : DefaultLifecycleObserver {
            override fun onPause(owner: LifecycleOwner) {
                paused = true
            }

            override fun onResume(owner: LifecycleOwner) {
                paused = false
            }

            override fun onDestroy(owner: LifecycleOwner) {
                action.updateTimeoutProgress(progressAnimationRef.value.value)
            }
        }

        owner.lifecycle.addObserver(observer)
        onDispose { owner.lifecycle.removeObserver(observer) }
    }

    LaunchedEffect(state.question, paused) {
        val remainTimeoutMs = (state.mathText.timeout * progressAnimation.value).toInt() * 1000
        progressAnimation.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = remainTimeoutMs, easing = LinearEasing)
        )
        action.timeout()
    }

    BackHandler {
        action.interrupt()
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = modifier
    ) {
        ProgressBar(
            progress = progressAnimation.value,
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )

        AnimatedContent(
            targetState = state.question,
            transitionSpec = { enter with exit },
            label = "question"
        ) { question ->
            QuestionCard(
                first = question.one,
                second = question.two,
                category = state.mathText.category,
                answer = state.textAnswer,
                remainder = state.textRemainder,
                isSuccess = state.isSuccess,
                isFailed = state.isFailed,
                hasRemainder = state.question.hasRemainder,
                focusMode = state.focusMode,
                onFocusChange = action::changeFocus,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
                .padding(horizontal = 16.dp)
        ) {
            NumberIme(
                isEffectMute = state.setting.isEffectMute,
                isBgmMute = state.setting.isBgmMute,
                onClickNumber = action::clickNumber,
                onSkip = action::skip,
                onDelete = action::delete,
                onClear = action::clear,
                onChangeEffectMute = action::changeEffectMute,
                onChangeBgmMute = action::changeBgmMute,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
            )
        }
    }
}
