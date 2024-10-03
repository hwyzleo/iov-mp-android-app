package net.hwyz.iov.mp.app.module.login

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.theme.AppTheme
import net.hwyz.iov.mp.app.component.bar.TopBackTitleBar
import net.hwyz.iov.mp.app.component.textfield.OtpTextField
import net.hwyz.iov.mp.app.utils.RouteUtils.back

@ExperimentalComposeUiApi
@Composable
fun LoginStep2Page(
    navCtrl: NavHostController,
    keyboardController: SoftwareKeyboardController?,
    intent: (LoginIntent) -> Unit,
    mobile: String = ""
) {
    var verifyCode = remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .background(AppTheme.colors.themeUi)
            .padding(20.dp)
    ) {
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
            TopBackTitleBar(onBack = { navCtrl.back() })
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.input_verify_code),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(
                    text = stringResource(id = R.string.verify_code_has_sent),
                    color = AppTheme.colors.fontSecondary
                )
                Text(
                    text = mobile,
                    color = AppTheme.colors.fontSecondary
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row {
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
            Spacer(modifier = Modifier.height(40.dp))
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.reget_verigy_code),
                    color = AppTheme.colors.fontSecondary
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun LoginStep2PagePreview() {
    val navCtrl = rememberNavController()
    val keyboardController = LocalSoftwareKeyboardController.current
    LoginStep2Page(navCtrl = navCtrl, keyboardController = keyboardController, intent = {})
}