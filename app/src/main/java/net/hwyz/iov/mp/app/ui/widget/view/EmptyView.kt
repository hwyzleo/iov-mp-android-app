package net.hwyz.iov.mp.app.ui.widget.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import net.hwyz.iov.mp.app.ui.theme.AppTheme
import net.hwyz.iov.mp.app.ui.widget.item.TextContentItem

@Composable
fun EmptyView(
    tips: String = "啥都没有~",
    imageVector: ImageVector = Icons.Default.Info,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .defaultMinSize(minHeight = 480.dp)
    ) {
        Column(
            Modifier
                .wrapContentSize()
                .align(Alignment.Center)
                .clickable { onClick.invoke() }
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                tint = AppTheme.colors.textSecondary,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            TextContentItem(text = tips, modifier = Modifier.padding(top = 10.dp))
        }
    }
}