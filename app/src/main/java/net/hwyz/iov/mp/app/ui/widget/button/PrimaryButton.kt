package net.hwyz.iov.mp.app.ui.widget.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.hwyz.iov.mp.app.ui.theme.AppTheme

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    AppButton(
        text = text,
        modifier = modifier,
        textColor = AppTheme.colors.textPrimary,
        onClick = onClick,
        bgColor = AppTheme.colors.themeUi
    )
}