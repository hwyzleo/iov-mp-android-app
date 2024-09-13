package net.hwyz.iov.mp.app.ui.widget.title

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.google.accompanist.placeholder.material.placeholder
import net.hwyz.iov.mp.app.ui.theme.AppTheme

@Composable
fun Title(
    title: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit,
    color: Color = AppTheme.colors.textSecondary,
    fontWeight: FontWeight = FontWeight.Normal,
    maxLine: Int = 1,
    textAlign: TextAlign = TextAlign.Start,
    isLoading: Boolean = false
) {
    Text(
        text = title,
        modifier = modifier
            .placeholder(
                visible = isLoading,
                color = AppTheme.colors.placeholder
            ),
        fontSize = fontSize,
        color = color,
        maxLines = maxLine,
        overflow = TextOverflow.Ellipsis,
        textAlign = textAlign
    )
}