package net.hwyz.iov.mp.app.data.http

import net.hwyz.iov.mp.app.data.bean.AccountInfo
import net.hwyz.iov.mp.app.data.bean.LoginResponse
import net.hwyz.iov.mp.app.data.bean.TspResponse
import retrofit2.http.*

/**
 * Http服务接口
 *
 * @author hwyz_leo
 */
interface HttpService {

    companion object {
//        const val url = "http://192.168.2.223:8081"
//        const val url = "http://10.0.68.161:8081"
        const val url = "http://mock"
    }

    // 发送登录验证码
    @FormUrlEncoded
    @POST("/account/mp/login/sendVerifyCode")
    suspend fun sendLoginVerifyCode(
        @Field("countryRegionCode") countryRegionCode: String,
        @Field("mobile") mobile: String
    ): TspResponse<Void>

    // 验证码登录
    @FormUrlEncoded
    @POST("/account/mp/login/verifyCodeLogin")
    suspend fun verifyCodeLogin(
        @Field("countryRegionCode") countryRegionCode: String,
        @Field("mobile") mobile: String,
        @Field("verifyCode") verifyCode: String
    ): TspResponse<LoginResponse>

    // 获取账号信息
    @GET("/account/mp/account/info")
    suspend fun getAccountInfo(): TspResponse<AccountInfo>

    // 更新昵称
    @FormUrlEncoded
    @POST("/account/mp/account/action/modifyNickname")
    suspend fun modifyNickname(
        @Field("nickname") nickname: String
    ): TspResponse<Void>

    // 更新性别
    @FormUrlEncoded
    @POST("/account/mp/account/action/modifyGender")
    suspend fun modifyGender(
        @Field("gender") gender: String
    ): TspResponse<Void>

}
