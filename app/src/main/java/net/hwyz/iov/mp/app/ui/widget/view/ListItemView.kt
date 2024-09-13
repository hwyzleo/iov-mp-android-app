package net.hwyz.iov.mp.app.ui.widget.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.material.placeholder
import net.hwyz.iov.mp.app.ui.theme.AppTheme
import net.hwyz.iov.mp.app.ui.theme.ListTitleHeight
import net.hwyz.iov.mp.app.ui.widget.item.TextContentItem
import net.hwyz.iov.mp.app.ui.widget.title.MediumTitle

@Composable
fun ListTitle(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String = "",
    isLoading: Boolean = false,
    onSubtitleClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .placeholder(false)
            .fillMaxWidth()
            .height(ListTitleHeight)
            .background(color = AppTheme.colors.background)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .width(5.dp)
                .height(16.dp)
                .align(Alignment.CenterVertically)
                .background(color = AppTheme.colors.textPrimary)
        )
        MediumTitle(
            title = title,
            modifier = Modifier.align(Alignment.CenterVertically),
            isLoading = isLoading
        )
        Spacer(modifier = Modifier.weight(1f))
        TextContentItem(
            text = subTitle,
            modifier = Modifier
                .padding(end = 10.dp)
                .clickable {
                    onSubtitleClick.invoke()
                },
            isLoading = isLoading
        )
    }

}