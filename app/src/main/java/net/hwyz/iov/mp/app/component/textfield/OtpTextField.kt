package net.hwyz.iov.mp.app.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * 验证码输入框
 *
 * @author hwyz_leo
 */
@Composable
fun OtpTextField(
    otpText: String,
    onOtpTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = otpText,
        onValueChange = { if (it.length <= 6) onOtpTextChange(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row {
                repeat(6) { index ->
                    if (index > 0) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    CharView(
                        index = index,
                        text = otpText
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val char = when {
        index >= text.length -> ""
        else -> text[index].toString()
    }
    val isFocused = text.length == index

    Box(
        modifier = Modifier
            .width(40.dp)
            .height(60.dp)
            .border(
                1.dp,
                if (isFocused) Color.DarkGray else Color.LightGray,
                RoundedCornerShape(8.dp)
            )
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = char,
            style = MaterialTheme.typography.h4,
            color = Color.DarkGray
        )
    }
}

@Preview
@Composable
fun OtpTextFieldPreview() {
    OtpTextField(
        otpText = "",
        onOtpTextChange = { }
    )
}