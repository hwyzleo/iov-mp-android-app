package net.hwyz.iov.mp.app.ui.widget.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.hwyz.iov.mp.app.ui.theme.buttonCorner
import net.hwyz.iov.mp.app.ui.theme.buttonHeight

@Composable
fun RoundedCornerButton(
    text: String,
    modifier: Modifier = Modifier,
    bgColor: Color = Color.White,
    textColor: Color = Color.Black,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .height(40.dp)
            .background(color = bgColor)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(18.dp)
            )
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = textColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun AppButtonPreview() {
    RoundedCornerButton(
        text = "圆角按钮",
        modifier = Modifier.padding(horizontal = 20.dp),
        bgColor = Color.White
    ) {}
}