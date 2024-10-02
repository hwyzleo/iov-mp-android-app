package net.hwyz.iov.mp.app.module.login

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
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.theme.AppTheme
import net.hwyz.iov.mp.app.component.bar.TopBackTitleBar
import net.hwyz.iov.mp.app.component.button.RoundedCornerButton
import net.hwyz.iov.mp.app.component.textfield.MobileTextField
import net.hwyz.iov.mp.app.utils.RouteUtils.back

@ExperimentalComposeUiApi
@Composable
fun LoginStep1View(
    navCtrl: NavHostController,
    keyboardController: SoftwareKeyboardController?,
    intent: (LoginIntent) -> Unit
) {
    val mobile = remember { mutableStateOf(TextFieldValue()) }
    val isMobileFilled = remember { mutableStateOf(false) }
    val btnBgColor = remember { mutableStateOf(Color.Gray) }
    val isSelect = remember { mutableStateOf(false) }
    val currentSelectImage = remember { mutableStateOf(R.drawable.black_unselect) }
    val showDialog = remember { mutableStateOf(false) }
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
            Text(
                text = stringResource(id = R.string.input_mobile),
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 10.dp, bottom = 30.dp)
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
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(0.5.dp)
                        .background(AppTheme.colors.fontPrimary)
                )
                MobileTextField(
                    value = mobile.value,
                    onValueChange = { newValue ->
                        mobile.value = newValue
                        isMobileFilled.value = newValue.text.length == 13
                        btnBgColor.value = if (isMobileFilled.value) Color.Black else Color.Gray
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    fontSize = 20.sp
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 30.dp))
            RoundedCornerButton(
                text = stringResource(id = R.string.get_verify_code),
                bgColor = btnBgColor.value,
                textColor = Color.White
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
            Row(modifier = Modifier.padding(top = 10.dp)) {
                Column(modifier = Modifier.padding(end = 5.dp)) {
                    Spacer(modifier = Modifier.padding(top = 3.dp))
                    Image(
                        painter = painterResource(id = currentSelectImage.value),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                if (!isSelect.value) {
                                    isSelect.value = true
                                    currentSelectImage.value = R.drawable.icon_circle_check
                                } else {
                                    isSelect.value = false
                                    currentSelectImage.value = R.drawable.icon_circle
                                }
                            }
                            .size(12.dp)
                    )
                }
                Text(
                    text = stringResource(id = R.string.login_confirm_tip),
                    fontSize = 12.sp
                )
            }
            if (showDialog.value) {
                if (!isSelect.value) {
                    AlertDialog(
                        onDismissRequest = { showDialog.value = false },
                        title = { Text(stringResource(id = R.string.tip)) },
                        text = { Text(stringResource(id = R.string.agree_user_agreement)) },
                        confirmButton = {
                            Button(
                                onClick = { showDialog.value = false },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
                            ) {
                                Text(text = stringResource(id = R.string.confirm), color = Color.White)
                            }
                        }
                    )
                } else if (!isMobileFilled.value) {
                    AlertDialog(
                        onDismissRequest = { showDialog.value = false },
                        title = { Text(stringResource(id = R.string.tip)) },
                        text = { Text(stringResource(id = R.string.input_mobile)) },
                        confirmButton = {
                            Button(onClick = { showDialog.value = false }) {
                                Text(stringResource(id = R.string.confirm))
                            }
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun LoginStep1ViewPreview() {
    val navCtrl = rememberNavController()
    val keyboardController = LocalSoftwareKeyboardController.current
    LoginStep1View(navCtrl = navCtrl, keyboardController = keyboardController, intent = {})
}