package net.hwyz.iov.mp.app.data.http

import android.annotation.SuppressLint
import net.hwyz.iov.mp.app.data.http.interceptor.CacheCookieInterceptor
import net.hwyz.iov.mp.app.data.http.interceptor.LogInterceptor
import net.hwyz.iov.mp.app.data.http.interceptor.MockInterceptor
import net.hwyz.iov.mp.app.data.http.interceptor.SetCookieInterceptor
import net.hwyz.iov.mp.app.data.http.interceptor.SetHeaderInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

object TspNetworkManager {

    /**
     * 请求超时时间
     */
    private const val DEFAULT_TIMEOUT = 30000
    private lateinit var SERVICE: TspApi
    private const val TSP_URL = "http://192.168.2.223:8081"


    //手动创建一个OkHttpClient并设置超时时间
    val retrofit: TspApi
        get() {
            if (!TspNetworkManager::SERVICE.isInitialized) {
                SERVICE = Retrofit.Builder()
                    .client(okHttp)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(TSP_URL)
                    .build()
                    .create(TspApi::class.java)
            }
            return SERVICE
        }

    //手动创建一个OkHttpClient并设置超时时间
    val okHttp: OkHttpClient
        get() {
            return OkHttpClient.Builder().run {
                connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                addInterceptor(MockInterceptor())
                addInterceptor(SetCookieInterceptor())
                addInterceptor(SetHeaderInterceptor())
                addInterceptor(CacheCookieInterceptor())
                addInterceptor(LogInterceptor())
                //不验证证书
//                sslSocketFactory(createSSLSocketFactory())
                hostnameVerifier(TrustAllNameVerifier())
                build()
            }
        }

    private fun createSSLSocketFactory(): SSLSocketFactory {
        lateinit var ssfFactory: SSLSocketFactory
        try {
            val sslFactory = SSLContext.getInstance("TLS")
            sslFactory.init(null, arrayOf(TrustAllCerts()), SecureRandom());
            ssfFactory = sslFactory.socketFactory
        } catch (e: Exception) {
            print("SSL错误：${e.message}")
        }
        return ssfFactory
    }

}

class TrustAllNameVerifier : HostnameVerifier {
    @SuppressLint("BadHostnameVerifier")
    override fun verify(hostname: String?, session: SSLSession?): Boolean = true
}

@SuppressLint("CustomX509TrustManager")
class TrustAllCerts : X509TrustManager {

    @SuppressLint("TrustAllX509TrustManager")
    override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
    }

    @SuppressLint("TrustAllX509TrustManager")
    override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
}
