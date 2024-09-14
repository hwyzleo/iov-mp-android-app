package net.hwyz.iov.mp.app.ui.widget.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.hwyz.iov.mp.app.ui.theme.AppTheme

@Composable
fun SecondlyButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    RoundedCornerButton(
        text = text,
        modifier = modifier,
        textColor = AppTheme.colors.textSecondary,
        onClick = onClick
    )
}