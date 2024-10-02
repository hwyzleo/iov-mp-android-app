package net.hwyz.iov.mp.app.component.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.hwyz.iov.mp.app.theme.AppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldItem(
    modifier: Modifier = Modifier,
    text: String = "内容",
    hintText: String = "",
    deleteIconColor: Color = AppTheme.colors.themeUi,
    onValueChanged: ((String) -> Unit)? = null,
    onDeleteClick: (() -> Unit)? = null,
    inputCursorColor: Color = AppTheme.colors.themeUi,
    inputTextColor: Color = AppTheme.colors.fontPrimary,
    borderColor: Color = AppTheme.colors.fontSecondary,
    keyboardType: KeyboardType = KeyboardType.Text,
    isHideKeyboard: Boolean = true,
) {
    val keyboardService = LocalTextInputService.current

    TextField(
        value = text,
        onValueChange = { onValueChanged?.invoke(it) },
        textStyle = TextStyle(lineHeight = 24.sp, fontSize = 16.sp),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
            .pointerInteropFilter { false },
        placeholder = {
            TextContentItem(hintText)
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = deleteIconColor,
                    modifier = Modifier.clickable { onDeleteClick?.invoke() }
                )
            }
        },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            textColor = inputTextColor,
            placeholderColor = AppTheme.colors.fontSecondary,
            cursorColor = inputCursorColor
        ),
        keyboardActions = if (!isHideKeyboard) KeyboardActions.Default
        else KeyboardActions { keyboardService?.hideSoftwareKeyboard() },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Next
        )
    )
}

@Preview
@Composable
fun TextFieldItemPreview() {
    TextFieldItem()
}