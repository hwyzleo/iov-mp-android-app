package net.hwyz.iov.mp.app.widget.textfield

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun HintTextField(
    value: TextFieldValue,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (TextFieldValue) -> Unit,
    hint: String,
    fontSize: TextUnit = 12.sp,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = value,
        keyboardOptions = keyboardOptions,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = TextStyle(fontSize = fontSize),
        decorationBox = { innerTextField ->
            Box {
                if (value.text.isEmpty()) {
                    Text(
                        text = hint,
                        fontSize = fontSize,
                        color = Color.Gray
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview
@Composable
fun HintTextFieldPreview() {
    HintTextField(
        value = TextFieldValue(""),
        onValueChange = { },
        hint = "提示文字"
    )
}