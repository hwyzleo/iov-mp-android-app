package net.hwyz.iov.mp.app.component.button

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.hwyz.iov.mp.app.theme.AppTheme

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    RoundedCornerButton(
        text = text,
        modifier = modifier,
        textColor = AppTheme.colors.fontPrimary,
        onClick = onClick,
        bgColor = AppTheme.colors.themeUi
    )
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(
        text = "按钮",
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {}
}