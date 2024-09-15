package net.hwyz.iov.mp.app.ui.page.login

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.mp.app.ui.theme.AppTheme
import net.hwyz.iov.mp.app.ui.widget.bar.TopTitleBar
import net.hwyz.iov.mp.app.ui.widget.textfield.OtpTextField
import net.hwyz.iov.mp.app.utils.RouteUtils.back

@ExperimentalComposeUiApi
@Composable
fun LoginStep2View(
    navCtrl: NavHostController,
    keyboardController: SoftwareKeyboardController?,
    intent: (LoginIntent) -> Unit,
    mobile: String = ""
) {
    var verifyCode = remember { mutableStateOf("") }
    Column(
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
        TopTitleBar(onBack = { navCtrl.back() })
        Text(
            text = "请输入验证码",
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
        )
        Text(
            text = "验证码已发送到您的手机：${mobile}",
            color = Color.Gray,
            modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 30.dp)
        )
        Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            OtpTextField(
                otpText = verifyCode.value,
                onOtpTextChange = {
                    verifyCode.value = it
                    if (it.length == 6) {
                        keyboardController?.hide()
                        intent(LoginIntent.VerifyCodeLogin(verifyCode = verifyCode.value))
                    }
                }
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Row(modifier = Modifier.padding(top = 40.dp)) {
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "重新获取验证码",
                color = Color.Gray,
                modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 30.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun LoginStep2ViewPreview() {
    val navCtrl = rememberNavController()
    val keyboardController = LocalSoftwareKeyboardController.current
    LoginStep2View(navCtrl = navCtrl, keyboardController = keyboardController, intent = {})
}