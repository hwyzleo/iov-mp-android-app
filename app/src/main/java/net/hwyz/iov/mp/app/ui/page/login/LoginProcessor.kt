package net.hwyz.iov.mp.app.ui.page.login

import net.hwyz.iov.mp.app.base.MviActionProcessor
import net.hwyz.iov.mp.app.data.bean.LoginVerifyCodeRequest
import net.hwyz.iov.mp.app.data.bean.VerifyCodeLoginRequest
import net.hwyz.iov.mp.app.data.http.TspApi
import net.hwyz.iov.mp.app.utils.AppUserUtil
import javax.inject.Inject

open class LoginProcessor @Inject constructor(
    private var service: TspApi,
) :
    MviActionProcessor<LoginAction, LoginResult> {

    override suspend fun executeAction(action: LoginAction): LoginResult {
        return when (action) {
            is LoginAction.DisplayStep1 -> LoginResult.DisplayStep1
            is LoginAction.DisplayLoading -> LoginResult.DisplayLoading
            is LoginAction.SendVerifyCode -> {
                sendVerifyCode(
                    action.countryRegionCode,
                    action.mobile
                )
            }

            is LoginAction.VerifyCodeLogin -> {
                verifyCodeLogin(
                    action.countryRegionCode,
                    action.mobile,
                    action.verifyCode
                )
            }
        }
    }

    private suspend fun sendVerifyCode(countryRegionCode: String, mobile: String): LoginResult {
        return try {
            val response = service.sendLoginVerifyCode(
                LoginVerifyCodeRequest(
                    countryRegionCode.trim(),
                    mobile.trim()
                )
            )
            if (response.code == 0) {
                LoginResult.SendVerifyCode.Success(countryRegionCode, mobile)
            } else {
                throw Exception(response.message)
            }
        } catch (e: Exception) {
            LoginResult.SendVerifyCode.Failure(e)
        }
    }

    private suspend fun verifyCodeLogin(
        countryRegionCode: String,
        mobile: String,
        verifyCode: String
    ): LoginResult {
        return try {
            val response =
                service.verifyCodeLogin(
                    VerifyCodeLoginRequest(
                        countryRegionCode.trim(), mobile.trim(), verifyCode.trim()
                    )
                )
            if (response.code == 0) {
                AppUserUtil.onLogin(response.data!!)
                LoginResult.VerifyCodeLogin.Success
            } else {
                throw Exception(response.message)
            }
        } catch (e: Exception) {
            LoginResult.VerifyCodeLogin.Failure(e)
        }
    }
}