package net.hwyz.iov.mp.app.widget.dialog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.AlertDialog
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import net.hwyz.iov.mp.app.theme.AppTheme
import net.hwyz.iov.mp.app.widget.item.TextContentItem
import net.hwyz.iov.mp.app.widget.title.MediumTitle

/**
 * 常用的警告对话框
 */
@Composable
fun CommonAlertDialog(
    title: String,
    content: String,
    cancelText: String = "取消",
    confirmText: String = "继续",
    onConfirmClick: () -> Unit,
    //onCancelClick: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = {
            MediumTitle(title = title)
        },
        text = {
            TextContentItem(text = content)
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onConfirmClick.invoke()
                onDismiss.invoke()
            }) {
                TextContentItem(text = confirmText, color = AppTheme.colors.primaryText)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss.invoke() }) {
                TextContentItem(text = cancelText)
            }
        },
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    )
}