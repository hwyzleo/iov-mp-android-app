package net.hwyz.iov.mp.app.data.http.interceptor

import com.google.gson.Gson
import net.hwyz.iov.mp.app.data.bean.AccountInfo
import net.hwyz.iov.mp.app.data.bean.LoginResponse
import net.hwyz.iov.mp.app.data.bean.TspResponse
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Mock数据拦截器
 *
 * @author hwyz_leo
 */
class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()
        if (url.contains("mock")) {
            var responseBody: ResponseBody? = null
            if (url.endsWith("/account/mp/login/sendVerifyCode")) {
                responseBody = mockSendVerifyCode()
            }
            if (url.endsWith("/account/mp/login/verifyCodeLogin")) {
                responseBody = mockVerifyCodeLogin()
            }
            if (url.endsWith("/account/mp/account/info")) {
                responseBody = mockAccountInfo()
            }
            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .message("OK")
                .body(responseBody)
                .build()
        } else {
            return chain.proceed(request)
        }
    }

    private fun mockSendVerifyCode(): ResponseBody {
        val gson = Gson()
        return gson.toJson(
            TspResponse(
                code = 0,
                message = "",
                ts = System.currentTimeMillis(),
                data = null
            )
        ).toResponseBody("application/json".toMediaTypeOrNull())
    }

    private fun mockVerifyCodeLogin(): ResponseBody {
        val gson = Gson()
        return gson.toJson(
            TspResponse(
                code = 0,
                message = "",
                ts = System.currentTimeMillis(),
                data = LoginResponse(
                    mobile = "13917288107",
                    nickname = "hwyz_leo",
                    avatar = "https://pic.imgdb.cn/item/66e667a0d9c307b7e93075e8.png",
                    token = "token",
                    tokenExpires = System.currentTimeMillis() + 1000000000,
                    refreshToken = "refreshToken",
                    refreshTokenExpires = System.currentTimeMillis() + 1000000000
                )
            )
        ).toResponseBody("application/json".toMediaTypeOrNull())
    }

    private fun mockAccountInfo(): ResponseBody {
        val gson = Gson()
        return gson.toJson(
            TspResponse(
                code = 0,
                message = "",
                ts = System.currentTimeMillis(),
                data = AccountInfo(
                    mobile = "13917288107",
                    nickname = "hwyz_leo",
                    avatar = "https://pic.imgdb.cn/item/66e667a0d9c307b7e93075e8.png",
                    gender = "MALE"
                )
            )
        ).toResponseBody("application/json".toMediaTypeOrNull())
    }

}