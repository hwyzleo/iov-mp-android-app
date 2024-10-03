package net.hwyz.iov.mp.app.module.login

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import net.hwyz.iov.mp.app.component.bar.SNACK_ERROR
import net.hwyz.iov.mp.app.component.bar.popupSnackBar
import net.hwyz.iov.mp.app.component.view.LoadingView

@ExperimentalComposeUiApi
@Composable
fun LoginPage(
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
            LoginStep1Page(
                navCtrl = navCtrl,
                keyboardController = keyboardController,
                intent = {
                    viewModel.intent(it)
                }
            )
        }

        LoginResult.DisplayLoading -> LoadingView()
        LoginResult.SendVerifyCode -> {
            LoginStep2Page(
                navCtrl = navCtrl,
                keyboardController = keyboardController,
                intent = {
                    viewModel.intent(it)
                },
                mobile = viewStates.mobile
            )
        }

        else -> {}
    }
}


