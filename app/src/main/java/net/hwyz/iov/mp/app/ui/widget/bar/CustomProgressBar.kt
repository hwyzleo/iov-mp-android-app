package net.hwyz.iov.mp.app.ui.widget.bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomProgressBar(
    progress: Float,
    height: Dp = 20.dp,
    color: Color = Color.Green
) {
    Box(
        modifier = Modifier
            .height(height)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .clip(RoundedCornerShape(10.dp))
                .background(color)
        )
    }
}

@Preview
@Composable
fun CustomProgressBarPreview() {
    CustomProgressBar(
        progress = 0.7f
    )
}