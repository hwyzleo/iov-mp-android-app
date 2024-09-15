package net.hwyz.iov.mp.app.data.http.interceptor

import net.hwyz.iov.mp.app.utils.AppUserUtil
import net.hwyz.iov.mp.app.utils.DeviceUtil
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Header拦截器
 *
 * @author hwyz_leo
 */
class SetHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("Content-Type", "application/json; charset=utf-8")
        builder.addHeader("clientId", DeviceUtil.getDeviceId())
        builder.addHeader("token", AppUserUtil.token)
        return chain.proceed(builder.build())
    }

}