package net.hwyz.iov.mp.app.ui.page.login

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import net.hwyz.iov.mp.app.ui.theme.AppTheme
import net.hwyz.iov.mp.app.ui.widget.bar.SNACK_ERROR
import net.hwyz.iov.mp.app.ui.widget.bar.TopTitleBar
import net.hwyz.iov.mp.app.ui.widget.bar.popupSnackBar
import net.hwyz.iov.mp.app.ui.widget.button.RoundedCornerButton
import net.hwyz.iov.mp.app.ui.widget.view.LoadingView
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
            LoginStep1View(
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
            RoundedCornerButton(
                text = "登录",
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                keyboardController?.hide()
                intent(LoginIntent.VerifyCodeLogin(verifyCode = verifyCode.value))
            }
        }
    }
}