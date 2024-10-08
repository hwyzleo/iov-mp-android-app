package net.hwyz.iov.mp.app.component.title

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import net.hwyz.iov.mp.app.theme.AppTheme
import net.hwyz.iov.mp.app.theme.H5

@Composable
fun MediumTitle(
    title: String,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.fontPrimary,
    textAlign: TextAlign = TextAlign.Start,
    isLoading: Boolean = false
) {
    Title(
        title = title,
        fontSize = H5,
        modifier = modifier,
        color = color,
        textAlign = textAlign,
        isLoading = isLoading
    )
}