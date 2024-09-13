package net.hwyz.iov.mp.app.ui.widget.title

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import net.hwyz.iov.mp.app.ui.theme.AppTheme
import net.hwyz.iov.mp.app.ui.theme.H4

@Composable
fun MainTitle(
    title: String,
    modifier: Modifier = Modifier,
    maxLine: Int = 1,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = AppTheme.colors.textPrimary,
    isLoading: Boolean = false
) {
    Title(
        title = title,
        modifier = modifier,
        fontSize = H4,
        color = color,
        fontWeight = FontWeight.SemiBold,
        maxLine = maxLine,
        textAlign = textAlign,
        isLoading = isLoading
    )
}