package net.hwyz.iov.mp.app.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.hwyz.iov.mp.app.R

/**
 * 图片按钮
 */
@Composable
fun ImageButton(
    painter: Painter,
    contentDescription: String = "",
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clickable {
                onClick()
            }
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = modifier,
        )
    }
}

@Preview
@Composable
fun ImageButtonPreview() {
    ImageButton(
        painter = painterResource(id = R.drawable.icon_setting),
        contentDescription = "设置",
        modifier = Modifier
            .size(30.dp),
    ) {}
}