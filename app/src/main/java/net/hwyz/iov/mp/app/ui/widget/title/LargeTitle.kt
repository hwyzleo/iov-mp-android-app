package net.hwyz.iov.mp.app.ui.widget.title

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import net.hwyz.iov.mp.app.ui.theme.AppTheme
import net.hwyz.iov.mp.app.ui.theme.H3

@Composable
fun LargeTitle(
    title: String,
    modifier: Modifier = Modifier,
    color: Color? = null,
    isLoading: Boolean = false
) {
    Title(
        title = title,
        modifier = modifier,
        fontSize = H3,
        color = color ?: AppTheme.colors.textPrimary,
        fontWeight = FontWeight.Bold,
        isLoading = isLoading
    )
}