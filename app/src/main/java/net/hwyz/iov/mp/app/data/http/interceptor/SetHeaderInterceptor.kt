package net.hwyz.iov.data.http.interceptor

import net.hwyz.iov.mp.app.utils.AppUserUtil
import net.hwyz.iov.mp.app.utils.DeviceUtil
import okhttp3.Interceptor
import okhttp3.Response

class SetHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("clientId", DeviceUtil.getDeviceId())
        builder.addHeader("token", AppUserUtil.token)
        return chain.proceed(builder.build())
    }
}