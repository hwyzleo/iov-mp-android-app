package net.hwyz.iov.mp.app.data.http

import net.hwyz.iov.mp.app.data.bean.AccountInfo
import net.hwyz.iov.mp.app.data.bean.ControlRequest
import net.hwyz.iov.mp.app.data.bean.ControlState
import net.hwyz.iov.mp.app.data.bean.Dealership
import net.hwyz.iov.mp.app.data.bean.LicenseArea
import net.hwyz.iov.mp.app.data.bean.LoginResponse
import net.hwyz.iov.mp.app.data.bean.LoginVerifyCodeRequest
import net.hwyz.iov.mp.app.data.bean.Order
import net.hwyz.iov.mp.app.data.bean.OrderPaymentRequest
import net.hwyz.iov.mp.app.data.bean.OrderPaymentResponse
import net.hwyz.iov.mp.app.data.bean.SaleModelConfig
import net.hwyz.iov.mp.app.data.bean.SelectedSaleModel
import net.hwyz.iov.mp.app.data.bean.TspResponse
import net.hwyz.iov.mp.app.data.bean.UpdateClientConfigRequest
import net.hwyz.iov.mp.app.data.bean.VehicleSaleOrder
import net.hwyz.iov.mp.app.data.bean.VerifyCodeLoginRequest
import net.hwyz.iov.mp.app.data.bean.Wishlist
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

    // 获取有效车辆销售订单列表
    @GET("/mp/vehicleSaleOrder/order")
    suspend fun getValidVehicleSaleOrderList(): TspResponse<List<VehicleSaleOrder>>

    // 获取销售车型列表
    @GET("/mp/saleModel/{saleCode}")
    suspend fun getSaleModelList(@Query("saleCode") saleCode: String): TspResponse<List<SaleModelConfig>>

    // 创建心愿单
    @POST("/mp/vehicleSaleOrder/wishlist/action/create")
    suspend fun createWishlist(@Body request: Wishlist): TspResponse<String>

    // 修改心愿单
    @POST("/mp/vehicleSaleOrder/wishlist/action/modify")
    suspend fun modifyWishlist(@Body request: Wishlist): TspResponse<String>

    // 获取心愿单详情
    @GET("/mp/vehicleSaleOrder/wishlist/{orderNum}")
    suspend fun getWishlist(@Query("orderNum") orderNum: String): TspResponse<Wishlist>

    // 删除心愿单
    @POST("/mp/vehicleSaleOrder/wishlist/action/delete")
    suspend fun deleteWishlist(@Body request: Order): TspResponse<Void>

    // 获取已选择的销售车型及配置
    @GET("/mp/saleModel/selectedSaleModel")
    suspend fun getSelectedSaleModel(
        @Query("saleCode") saleCode: String,
        @Query("modelCode") modelCode: String,
        @Query("exteriorCode") exteriorCode: String,
        @Query("interiorCode") interiorCode: String,
        @Query("wheelCode") wheelCode: String,
        @Query("spareTireCode") spareTireCode: String,
        @Query("adasCode") adasCode: String
    ): TspResponse<SelectedSaleModel>

    // 获取上牌区域
    @GET("/mp/saleModel/licenseArea")
    suspend fun getLicenseArea(): TspResponse<List<LicenseArea>>

    // 获取销售门店
    @GET("/mp/dealership")
    suspend fun getDealership(@Query("serviceType") serviceType: String): TspResponse<List<Dealership>>

    // 意向金下订单
    @POST("/mp/vehicleSaleOrder/action/earnestMoneyOrder")
    suspend fun earnestMoneyOrder(@Body request: Order): TspResponse<String>

    // 定金下订单
    @POST("/mp/vehicleSaleOrder/action/downPaymentOrder")
    suspend fun downPaymentOrder(@Body request: Order): TspResponse<String>

    // 获取订单详情
    @GET("/mp/vehicleSaleOrder/order/{orderNum}")
    suspend fun getOrder(@Query("orderNum") orderNum: String): TspResponse<Order>

    // 取消订单
    @POST("/mp/vehicleSaleOrder/order/action/cancel")
    suspend fun cancelOrder(@Body request: Order): TspResponse<Void>

    // 支付订单
    @POST("/mp/vehicleSaleOrder/order/action/pay")
    suspend fun payOrder(@Body request: OrderPaymentRequest): TspResponse<OrderPaymentResponse>

    // 意向金转定金
    @POST("/mp/vehicleSaleOrder/order/action/earnestMoneyToDownPayment")
    suspend fun earnestMoneyToDownPayment(@Body request: Order): TspResponse<Void>

    // 锁定订单
    @POST("/mp/vehicleSaleOrder/order/action/lock")
    suspend fun lockOrder(@Body request: Order): TspResponse<Void>

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
