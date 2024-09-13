package net.hwyz.iov.mp.app.ui.widget.button

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.placeholder.material.placeholder
import net.hwyz.iov.mp.app.ui.theme.AppTheme
import net.hwyz.iov.mp.app.ui.theme.white1
import org.jetbrains.annotations.NotNull

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LabelTextButton(
    @NotNull text: String,
    modifier: Modifier = Modifier,
    isSelect: Boolean = true,
    specTextColor: Color? = null,
    cornerValue: Dp = 25.dp / 2,
    isLoading: Boolean = false,
    onClick: (() -> Unit)? = null,
    onLongClick: (() -> Unit)? = null
) {
    Text(
        text = text,
        modifier = modifier
            .height(25.dp)
            .clip(shape = RoundedCornerShape(cornerValue))
            .background(
                color = if (isSelect && !isLoading) AppTheme.colors.themeUi else AppTheme.colors.secondBtnBg,
            )
            .padding(
                horizontal = 10.dp,
                vertical = 3.dp
            )
            .combinedClickable(
                enabled = !isLoading,
                onClick = { onClick?.invoke() },
                onLongClick = { onLongClick?.invoke() }
            )
            .placeholder(
                visible = isLoading,
                color = AppTheme.colors.placeholder
            ),
        fontSize = 13.sp,
        textAlign = TextAlign.Center,
        color = specTextColor ?: if (isSelect) white1 else AppTheme.colors.textSecondary,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}