package net.hwyz.iov.mp.app.ui.page.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.ui.theme.AppTheme
import net.hwyz.iov.mp.app.ui.widget.bar.TopTitleBar
import net.hwyz.iov.mp.app.ui.widget.button.RoundedCornerButton
import net.hwyz.iov.mp.app.ui.widget.textfield.HintTextField
import net.hwyz.iov.mp.app.utils.RouteUtils.back

@ExperimentalComposeUiApi
@Composable
fun LoginStep1View(
    navCtrl: NavHostController,
    keyboardController: SoftwareKeyboardController?,
    intent: (LoginIntent) -> Unit
) {
    var mobile = remember { mutableStateOf(TextFieldValue()) }
    var isMobileFilled = remember { mutableStateOf(false) }
    var btnBgColor = remember { mutableStateOf(Color.Gray) }
    var isSelect = remember { mutableStateOf(false) }
    var currentSelectImage = remember { mutableStateOf(R.drawable.black_unselect) }
    var showDialog = remember { mutableStateOf(false) }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.themeUi)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        keyboardController?.hide()
                    }
                )
            },
    ) {
        item {
            TopTitleBar(
                title = "",
                onBack = { navCtrl.back() }
            )
        }
        item {
            Text(
                text = "请输入手机号", fontSize = 20.sp,
                modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 30.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(40.dp)
            ) {
                Text(
                    text = "+86",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .width(65.dp)
                        .padding(start = 20.dp)
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(0.5.dp)
                        .background(Color.Black)
                )
                HintTextField(
                    value = mobile.value,
                    onValueChange = { newValue ->
                        val digitsOnly = newValue.text.filter { it.isDigit() }
                        if (digitsOnly.length <= 11) {
                            val formatted = formatPhoneNumber(digitsOnly)
                            val newCursorPosition = formatted.length
                            mobile.value = TextFieldValue(
                                text = formatted,
                                selection = TextRange(newCursorPosition)
                            )
                            isMobileFilled.value = digitsOnly.length == 11
                            btnBgColor.value = if (isMobileFilled.value) Color.Black else Color.Gray
                        }
                    },
                    hint = "请输入手机号",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 20.dp)
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 30.dp))
        }
        item {
            RoundedCornerButton(
                text = "获取验证码",
                bgColor = btnBgColor.value,
                textColor = Color.White,
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                keyboardController?.hide()
                if (!isSelect.value || !isMobileFilled.value) {
                    showDialog.value = true
                } else {
                    intent(
                        LoginIntent.SendVerifyCode(
                            countryRegionCode = "+86",
                            mobile = mobile.value.text
                        )
                    )
                }
            }
        }
        item {
            Row(modifier = Modifier.padding(20.dp, 10.dp, 20.dp, 0.dp)) {
                Column(modifier = Modifier.padding(end = 5.dp)) {
                    Spacer(modifier = Modifier.padding(top = 3.dp))
                    Image(
                        painter = painterResource(id = currentSelectImage.value),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                if (!isSelect.value) {
                                    isSelect.value = true
                                    currentSelectImage.value = R.drawable.red_select
                                } else {
                                    isSelect.value = false
                                    currentSelectImage.value = R.drawable.black_unselect
                                }
                            }
                            .size(12.dp)
                    )
                }
                Text(
                    text = "我已阅读并同意《用户协议》《隐私政策》，用户首次登录将会同步注册APP账号",
                    fontSize = 12.sp
                )
            }
        }
        item {
            if (showDialog.value) {
                if (!isSelect.value) {
                    AlertDialog(
                        onDismissRequest = { showDialog.value = false },
                        title = { Text("提示") },
                        text = { Text("请勾选同意用户协议") },
                        confirmButton = {
                            Button(
                                onClick = { showDialog.value = false },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
                            ) {
                                Text(text = "确定", color = Color.White)
                            }
                        }
                    )
                } else if (!isMobileFilled.value) {
                    AlertDialog(
                        onDismissRequest = { showDialog.value = false },
                        title = { Text("提示") },
                        text = { Text("请输入手机号") },
                        confirmButton = {
                            Button(onClick = { showDialog.value = false }) {
                                Text("确定")
                            }
                        }
                    )
                }
            }
        }
    }
}

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

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun MyPageNotLoginPreview() {
    val navCtrl = rememberNavController()
    val keyboardController = LocalSoftwareKeyboardController.current
    LoginStep1View(navCtrl = navCtrl, keyboardController = keyboardController, intent = {})
}