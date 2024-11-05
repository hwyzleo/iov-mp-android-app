package net.hwyz.iov.mp.app.data.http.interceptor

import com.google.gson.Gson
import net.hwyz.iov.mp.app.data.bean.AccountInfo
import net.hwyz.iov.mp.app.data.bean.TspResponse
import net.hwyz.iov.mp.app.data.bean.mockLoginResponse
import net.hwyz.iov.mp.app.data.bean.mockSaleModelList
import net.hwyz.iov.mp.app.data.bean.mockTspResponse
import net.hwyz.iov.mp.app.utils.GlobalStateManager
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
        if (GlobalStateManager.isMock) {
            val gson = Gson()
            val path = url.replace(GlobalStateManager.apiUrl, "")
            var responseBody: ResponseBody =
                "{}".toResponseBody("application/json".toMediaTypeOrNull())
            when {
                Regex("/mp/login/action/sendSmsVerifyCode").matches(path) -> {
                    responseBody = gson.toJson(mockTspResponse())
                        .toResponseBody("application/json".toMediaTypeOrNull())
                }

                Regex("/mp/login/action/smsVerifyCodeLogin").matches(path) -> {
                    responseBody = gson.toJson(mockTspResponse(mockLoginResponse()))
                        .toResponseBody("application/json".toMediaTypeOrNull())
                }

                Regex("/mp/saleModel/.*").matches(path) -> {
                    responseBody = gson.toJson(mockTspResponse(mockSaleModelList()))
                        .toResponseBody("application/json".toMediaTypeOrNull())
                }

                Regex("/account/mp/account/info").matches(path) -> {
                    responseBody = mockAccountInfo()
                }
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