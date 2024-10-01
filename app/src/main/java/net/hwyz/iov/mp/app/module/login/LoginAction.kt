package net.hwyz.iov.mp.app.module.login

import net.hwyz.iov.mp.app.base.MviAction

sealed class LoginAction : MviAction {
    object DisplayStep1 : LoginAction()
    object DisplayLoading : LoginAction()
    data class SendVerifyCode(
        val countryRegionCode: String,
        val mobile: String
    ) : LoginAction()

    data class VerifyCodeLogin(
        val countryRegionCode: String,
        val mobile: String,
        val verifyCode: String
    ) : LoginAction()
}