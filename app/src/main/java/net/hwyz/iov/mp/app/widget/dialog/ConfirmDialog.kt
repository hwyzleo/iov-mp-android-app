package net.hwyz.iov.mp.app.widget.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import net.hwyz.iov.mp.app.theme.AppTheme

/**
 * 确认对话框
 */
@Composable
fun ConfirmDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text("确认") },
            text = { Text("你确定要执行这个操作吗？") },
            confirmButton = {
                TextButton(onClick = {
                    onConfirm()
                    onDismiss()
                }) {
                    Text("确定", color = AppTheme.colors.primaryText)
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("取消", color = AppTheme.colors.primaryText)
                }
            }
        )
    }
}

@Preview
@Composable
fun ConfirmDialogPreview() {
    ConfirmDialog(
        showDialog = true,
        onDismiss = {},
        onConfirm = {}
    )
}