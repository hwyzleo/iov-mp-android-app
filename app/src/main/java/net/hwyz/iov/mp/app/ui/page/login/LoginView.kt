package net.hwyz.iov.mp.app.ui.page.login

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import net.hwyz.iov.mp.app.ui.widget.view.LoadingView
import net.hwyz.iov.mp.app.ui.theme.AppTheme
import net.hwyz.iov.mp.app.ui.widget.bar.SNACK_ERROR
import net.hwyz.iov.mp.app.ui.widget.bar.TopTitleBar
import net.hwyz.iov.mp.app.ui.widget.bar.popupSnackBar
import net.hwyz.iov.mp.app.ui.widget.button.AppButton
import net.hwyz.iov.mp.app.ui.widget.view.LoginEditView
import net.hwyz.iov.mp.app.utils.RouteUtils.back

@ExperimentalComposeUiApi
@Composable
fun LoginView(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val viewStates = viewModel.viewStates
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineState = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.viewEvents.collect {
            if (it is LoginViewEvent.PopBack) {
                navCtrl.popBackStack()
            } else if (it is LoginViewEvent.ErrorMessage) {
                popupSnackBar(coroutineState, scaffoldState, label = SNACK_ERROR, it.message)
            }
        }
        viewModel.intent(LoginIntent.OnLaunched)
    }

    when (viewStates.result) {
        LoginResult.DisplayStep1 -> {
            Step1Screen(
                navCtrl = navCtrl,
                keyboardController = keyboardController,
                intent = {
                    viewModel.intent(it)
                }
            )
        }

        LoginResult.DisplayLoading -> LoadingView()
        LoginResult.SendVerifyCode -> {
            Step2Screen(
                navCtrl = navCtrl,
                keyboardController = keyboardController,
                intent = {
                    viewModel.intent(it)
                }
            )
        }

        else -> {}
    }
}

@ExperimentalComposeUiApi
@Composable
fun Step1Screen(
    navCtrl: NavHostController,
    keyboardController: SoftwareKeyboardController?,
    intent: (LoginIntent) -> Unit
) {
    var expanded = remember { mutableStateOf(false) }
    var selectedIndex = remember { mutableStateOf(0) }
    val countryRegion = listOf("中国大陆", "中国香港", "中国澳门", "中国台湾")
    val countryRegionCode = listOf("+86", "+852", "+853", "+886")
    val mobile = remember { mutableStateOf("") }
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
                title = "登录 / 注册",
                onBack = { navCtrl.back() }
            )
        }
        item {
            Text(
                text = "请输入手机号", fontSize = 25.sp,
                modifier = Modifier.padding(start = 20.dp, top = 30.dp, bottom = 40.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextButton(
                    onClick = { expanded.value = true },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        backgroundColor = Color.Transparent
                    ),
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Text(
                        text = countryRegion[selectedIndex.value]
                    )
                }
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }) {
                    countryRegion.forEachIndexed { index, s ->
                        DropdownMenuItem(onClick = {
                            expanded.value = false
                            selectedIndex.value = index
                        }) {
                            Text(text = s)
                        }
                    }
                }
                LoginEditView(
                    text = mobile.value,
                    labelText = "",
                    hintText = "请输入手机号",
                    onValueChanged = {
                        mobile.value = it
                    },
                    onDeleteClick = {
                        mobile.value = ""
                    }
                )
            }
            Spacer(modifier = Modifier.padding(bottom = 10.dp))
        }
        item {
            AppButton(
                text = "获取验证码",
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                keyboardController?.hide()
                intent(
                    LoginIntent.SendVerifyCode(
                        countryRegionCode = countryRegionCode[selectedIndex.value],
                        mobile = mobile.value
                    )
                )
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun Step2Screen(
    navCtrl: NavHostController,
    keyboardController: SoftwareKeyboardController?,
    intent: (LoginIntent) -> Unit
) {
    var verifyCode = remember { mutableStateOf("") }
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
                title = "登录 / 注册",
                onBack = { navCtrl.back() }
            )
        }
        item {
            LoginEditView(
                text = verifyCode.value,
                labelText = "验证码",
                hintText = "请输入验证码",
                onValueChanged = {
                    verifyCode.value = it
                },
                onDeleteClick = {
                    verifyCode.value = ""
                }
            )
        }
        item {
            AppButton(
                text = "登录",
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                keyboardController?.hide()
                intent(LoginIntent.VerifyCodeLogin(verifyCode = verifyCode.value))
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun LoginPagePreview() {
    val navCtrl = rememberNavController()
    val keyboardController = LocalSoftwareKeyboardController.current
    Step1Screen(
        navCtrl = navCtrl,
        keyboardController = keyboardController,
        intent = {}
    )
}