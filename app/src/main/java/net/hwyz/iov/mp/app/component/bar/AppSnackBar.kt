package net.hwyz.iov.mp.app.component.bar

import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.hwyz.iov.mp.app.theme.AppTheme

const val SNACK_INFO = ""
const val SNACK_WARN = " "
const val SNACK_ERROR = "  "
const val SNACK_SUCCESS = "OK"

@Composable
fun AppSnackBar(data: SnackbarData) {
    Snackbar(
        snackbarData = data,
        backgroundColor = when (data.actionLabel) {
            SNACK_INFO -> AppTheme.colors.themeUi
            SNACK_WARN -> AppTheme.colors.fontPrimary
            SNACK_ERROR -> AppTheme.colors.fontPrimary
            SNACK_SUCCESS -> AppTheme.colors.fontPrimary
            else -> AppTheme.colors.themeUi
        },
        actionColor = AppTheme.colors.fontPrimary,
        contentColor = AppTheme.colors.fontPrimary,
    )
}

fun popupSnackBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    label: String,
    message: String,
    onDismissCallback: () -> Unit = {}
) {
    scope.launch {
        scaffoldState.snackbarHostState.showSnackbar(actionLabel = label, message = message)
        onDismissCallback.invoke()
    }

}