package net.hwyz.iov.mp.app.module.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import net.hwyz.iov.mp.app.base.presentation.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    override val actionProcessor: LoginProcessor
) : BaseViewModel<LoginIntent, LoginState, LoginAction, LoginResult>() {
    var viewStates by mutableStateOf(LoginState())
    private val _viewEvents = Channel<LoginViewEvent>(Channel.BUFFERED)
    val viewEvents = _viewEvents.receiveAsFlow()

    override fun actionFromIntent(intent: LoginIntent): List<LoginAction> {
        return when (intent) {
            is LoginIntent.OnLaunched -> listOf(LoginAction.DisplayStep1)
            is LoginIntent.SendVerifyCode -> listOf(
                LoginAction.DisplayLoading,
                LoginAction.SendVerifyCode(
                    intent.countryRegionCode,
                    intent.mobile.replace(" ", "")
                )
            )

            is LoginIntent.VerifyCodeLogin -> listOf(
                LoginAction.DisplayLoading,
                LoginAction.VerifyCodeLogin(
                    viewStates.countryRegionCode,
                    viewStates.mobile,
                    intent.verifyCode
                )
            )
        }
    }

    override suspend fun reducer(result: LoginResult) {
        when (result) {
            is LoginResult.DisplayStep1 -> displayStep1()
            is LoginResult.SendVerifyCode.Success -> sendVerifyCodeSuccess(
                result.countryRegionCode,
                result.mobile
            )

            is LoginResult.SendVerifyCode.Failure -> sendVerifyCodeFailure(
                result.error.message ?: ""
            )

            is LoginResult.VerifyCodeLogin.Success -> verifyCodeLoginSuccess()
            is LoginResult.VerifyCodeLogin.Failure -> verifyCodeLoginFailure(
                result.error.message ?: ""
            )

            else -> {}
        }
    }

    private fun displayStep1() {
        viewStates = viewStates.copy(result = LoginResult.DisplayStep1)
    }

    private fun sendVerifyCodeSuccess(countryRegionCode: String, mobile: String) {
        viewStates = viewStates.copy(
            result = LoginResult.SendVerifyCode,
            countryRegionCode = countryRegionCode,
            mobile = mobile
        )
    }

    private suspend fun sendVerifyCodeFailure(error: String) {
        _viewEvents.send(LoginViewEvent.ErrorMessage(error))
    }

    private suspend fun verifyCodeLoginSuccess() {
        _viewEvents.send(LoginViewEvent.PopBack)
    }

    private suspend fun verifyCodeLoginFailure(error: String) {
        _viewEvents.send(LoginViewEvent.ErrorMessage(error))
    }

}

/**
 * 一次性事件
 */
sealed class LoginViewEvent {
    object PopBack : LoginViewEvent()
    data class ErrorMessage(val message: String) : LoginViewEvent()
}