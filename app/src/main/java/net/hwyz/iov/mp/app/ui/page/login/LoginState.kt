package net.hwyz.iov.mp.app.ui.page.login

import net.hwyz.iov.mp.app.base.MviState

data class LoginState(
    var result: LoginResult = LoginResult.DisplayStep1,
    var countryRegionCode: String = "",
    val mobile: String = "",
    val verifyCode: String = "",
    val isAgree: Boolean = false,
) : MviState