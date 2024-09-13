package net.hwyz.iov.mp.app.ui.widget.title

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import net.hwyz.iov.mp.app.ui.theme.AppTheme
import net.hwyz.iov.mp.app.ui.theme.H7

@Composable
fun MiniTitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.textSecondary,
    maxLines: Int = 1,
    textAlign: TextAlign = TextAlign.Start,
    isLoading: Boolean = false
) {
    Title(
        title = text,
        modifier = modifier,
        fontSize = H7,
        color = color,
        maxLine = maxLines,
        textAlign = textAlign,
        isLoading = isLoading,
    )
}