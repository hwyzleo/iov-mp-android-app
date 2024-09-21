package net.hwyz.iov.mp.app.data.http

import net.hwyz.iov.mp.app.data.bean.AccountInfo
import net.hwyz.iov.mp.app.data.bean.ControlRequest
import net.hwyz.iov.mp.app.data.bean.ControlState
import net.hwyz.iov.mp.app.data.bean.LoginResponse
import net.hwyz.iov.mp.app.data.bean.LoginVerifyCodeRequest
import net.hwyz.iov.mp.app.data.bean.TspResponse
import net.hwyz.iov.mp.app.data.bean.UpdateClientConfigRequest
import net.hwyz.iov.mp.app.data.bean.VerifyCodeLoginRequest
import retrofit2.http.*

/**
 * Http服务接口
 *
 * @author hwyz_leo
 */
interface TspApi {

    // 发送登录验证码
    @POST("/mp/login/action/sendSmsVerifyCode")
    suspend fun sendLoginVerifyCode(@Body request: LoginVerifyCodeRequest): TspResponse<Void>

    // 验证码登录
    @POST("/mp/login/action/smsVerifyCodeLogin")
    suspend fun verifyCodeLogin(@Body request: VerifyCodeLoginRequest): TspResponse<LoginResponse>

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

    // 寻车
    @POST("/mp/rvc/action/findVehicle")
    suspend fun findVehicle(@Body request: ControlRequest): TspResponse<ControlState>

    // 获取指令状态
    @GET("/mp/rvc/cmd")
    suspend fun getCmdState(
        @Query("vin") vin: String,
        @Query("cmdId") cmdId: String
    ): TspResponse<ControlState>

    // 更新客户端配置
    @POST("/mp/client/action/updateConfig")
    suspend fun updateClientConfig(@Body request: UpdateClientConfigRequest): TspResponse<Void>

}
