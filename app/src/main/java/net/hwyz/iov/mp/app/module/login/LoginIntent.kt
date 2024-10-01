package net.hwyz.iov.mp.app.module.login

import net.hwyz.iov.mp.app.base.MviIntent

sealed class LoginIntent : MviIntent {
    object OnLaunched : LoginIntent()
    data class SendVerifyCode(val countryRegionCode: String, val mobile: String) : LoginIntent()
    data class VerifyCodeLogin(val verifyCode: String) : LoginIntent()
}