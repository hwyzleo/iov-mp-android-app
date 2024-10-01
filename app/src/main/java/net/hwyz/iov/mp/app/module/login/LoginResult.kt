package net.hwyz.iov.mp.app.module.login

import net.hwyz.iov.mp.app.base.MviResult

sealed class LoginResult : MviResult {
    object DisplayStep1 : LoginResult()
    object DisplayLoading : LoginResult()
    object SendVerifyCode : LoginResult() {
        data class Success(val countryRegionCode: String, val mobile: String) : LoginResult()
        data class Failure(val error: Throwable) : LoginResult()
    }

    object VerifyCodeLogin : LoginResult() {
        object Success : LoginResult()
        data class Failure(val error: Throwable) : LoginResult()
    }
}