package net.hwyz.iov.mp.app.ui.widget.button

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import net.hwyz.iov.mp.app.R

/**
 * 圆形图片按钮
 */
@Composable
fun CircularImageButton(
    modifier: Modifier = Modifier,
    buttonSize: Dp = 50.dp,
    imageRes: Int,
    imageSize: Dp = 30.dp,
    contentDescription: String? = null,
    countdownSeconds: Int = 0,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    val remainingSeconds = remember(countdownSeconds) { mutableStateOf(countdownSeconds) }
    val shouldStartCountdown = remember { mutableStateOf(true) }
    val showCountdown = remainingSeconds.value > 0 && shouldStartCountdown.value
    val animatedProgress by animateFloatAsState(
        targetValue = if (showCountdown) 1f - (remainingSeconds.value.toFloat() / countdownSeconds) else 0f,
        animationSpec = tween(durationMillis = 1000)
    )
    LaunchedEffect(key1 = remainingSeconds, key2 = showCountdown) {
        if (shouldStartCountdown.value) {
            remainingSeconds.value = countdownSeconds
            if (remainingSeconds.value > 0) {
                while (showCountdown) {
                    delay(1000)
                    remainingSeconds.value--
                }
                shouldStartCountdown.value = false
            }
        }
    }

    Box(
        modifier = modifier
            .size(buttonSize)
            .clip(CircleShape)
            .background(Color.White)
            .border(width = 1.dp, color = Color.Black, shape = CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false, radius = 28.dp),
                onClick = onClick
            )
    ) {
        if (showCountdown) {
            // 倒计时进度环
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawArc(
                    color = Color.Green,
                    startAngle = -90f,
                    sweepAngle = 360f * animatedProgress,
                    useCenter = false,
                    style = Stroke(width = 4.dp.toPx())
                )
            }

            // 倒计时文本
            Text(
                text = remainingSeconds.value.toString(),
                modifier = Modifier.align(Alignment.Center),
                color = Color.Black,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(imageSize)
                        .align(Alignment.Center),
                    color = Color.Black,
                    strokeWidth = 2.dp
                )
            } else {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .size(imageSize)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview
@Composable
fun CircularImageButtonPreview() {
    CircularImageButton(
        imageRes = R.drawable.icon_setting,
        contentDescription = "设置",
        modifier = Modifier.size(30.dp),
        isLoading = false,
        countdownSeconds = 0
    ) {}
}