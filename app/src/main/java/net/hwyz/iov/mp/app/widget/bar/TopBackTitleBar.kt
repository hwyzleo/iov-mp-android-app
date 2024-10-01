package net.hwyz.iov.mp.app.widget.bar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.theme.AppTheme
import net.hwyz.iov.mp.app.theme.H5
import net.hwyz.iov.mp.app.theme.ToolBarHeight
import net.hwyz.iov.mp.app.theme.ToolBarTitleSize
import net.hwyz.iov.mp.app.widget.item.TextContentItem

/**
 * 顶部带返回的标题Bar
 */
@Composable
fun TopBackTitleBar(
    title: String = "",
    rightText: String? = null,
    onBack: (() -> Unit)? = null,
    onRightClick: (() -> Unit)? = null,
    imageVector: ImageVector? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(ToolBarHeight)
            .background(AppTheme.colors.themeUi)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            if (onBack != null) {
                Image(
                    painter = painterResource(id = R.drawable.icon_arrow_left),
                    null,
                    Modifier
                        .size(30.dp)
                        .clickable(onClick = onBack)
                        .align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (!rightText.isNullOrEmpty() && imageVector == null) {
                TextContentItem(
                    text = rightText,
                    color = AppTheme.colors.themeUi,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable { onRightClick?.invoke() }
                )
            }

            if (imageVector != null) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = AppTheme.colors.primaryText,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            onRightClick?.invoke()
                        })
            }
        }
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.Center),
            color = AppTheme.colors.primaryText,
            textAlign = TextAlign.Center,
            fontSize = if (title.length > 14) H5 else ToolBarTitleSize,
            fontWeight = FontWeight.W500,
            maxLines = 1
        )

    }
}

@Preview
@Composable
fun TopTitleBarPreview() {
    TopBackTitleBar(
        title = "标题",
        onBack = {}
    )
}