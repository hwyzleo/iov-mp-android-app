package net.hwyz.iov.mp.app.ui.widget.bar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import net.hwyz.iov.mp.app.ui.theme.AppTheme
import net.hwyz.iov.mp.app.ui.theme.H5
import net.hwyz.iov.mp.app.ui.theme.ToolBarHeight
import net.hwyz.iov.mp.app.ui.theme.ToolBarTitleSize
import net.hwyz.iov.mp.app.ui.widget.item.TextContentItem

/**
 * 顶部标题栏
 */
@Composable
fun TopTitleBar(
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
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    null,
                    Modifier
                        .clickable(onClick = onBack)
                        .align(Alignment.CenterVertically)
                        .padding(12.dp),
                    tint = AppTheme.colors.icon
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (!rightText.isNullOrEmpty() && imageVector == null) {
                TextContentItem(
                    text = rightText,
                    color = AppTheme.colors.mainColor,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 20.dp)
                        .clickable { onRightClick?.invoke() }
                )
            }

            if (imageVector != null) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    tint = AppTheme.colors.textPrimary,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 12.dp)
                        .clickable {
                            onRightClick?.invoke()
                        })
            }
        }
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 40.dp),
            color = AppTheme.colors.textPrimary,
            textAlign = TextAlign.Center,
            fontSize = if (title.length > 14) H5 else ToolBarTitleSize,
            fontWeight = FontWeight.W500,
            maxLines = 1
        )

    }
}