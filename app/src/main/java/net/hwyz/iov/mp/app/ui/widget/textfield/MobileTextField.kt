package net.hwyz.iov.mp.app.ui.widget.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun MobileTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "请输入手机号",
    fontSize: TextUnit = 12.sp
) {
    HintTextField(
        value = value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        onValueChange = { newValue ->
            val digitsOnly = newValue.text.filter { it.isDigit() }
            if (digitsOnly.length <= 11) {
                val formatted = formatPhoneNumber(digitsOnly)
                val newCursorPosition = formatted.length
                val formattedValue = TextFieldValue(
                    text = formatted,
                    selection = TextRange(newCursorPosition)
                )
                onValueChange(formattedValue)
            }
        },
        hint = hint,
        fontSize = fontSize,
        modifier = modifier
    )
}

/**
 * 格式化手机号
 */
fun formatPhoneNumber(input: String): String {
    return when {
        input.length > 7 -> "${input.substring(0, 3)} ${
            input.substring(
                3,
                7
            )
        } ${input.substring(7)}"

        input.length > 3 -> "${input.substring(0, 3)} ${input.substring(3)}"
        else -> input
    }
}

@Preview
@Composable
fun MobileTextFieldPreview() {
    MobileTextField(
        value = TextFieldValue(""),
        onValueChange = { }
    )
}