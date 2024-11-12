package net.hwyz.iov.mp.app.data.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.math.BigDecimal
import java.util.Objects

/**
 * TSP平台通用响应实体
 */
data class TspResponse<T>(
    var code: Int,
    var message: String,
    var ts: Long,
    var data: T?
)

/**
 * 登录验证码请求
 */
data class LoginVerifyCodeRequest(
    val countryRegionCode: String,
    val mobile: String
)

/**
 * 验证码登录请求
 */
data class VerifyCodeLoginRequest(
    val countryRegionCode: String,
    val mobile: String,
    val verifyCode: String
)

/**
 * 登录响应
 */
@Parcelize
data class LoginResponse(
    var mobile: String,
    var nickname: String,
    var avatar: String?,
    var token: String,
    var tokenExpires: Long,
    var refreshToken: String,
    var refreshTokenExpires: Long
) : Parcelable

/**
 * 车辆销售订单
 */
@Parcelize
data class VehicleSaleOrder(
    // 订单号
    var orderNum: String,
    // 订单状态
    var orderState: Int,
    // 显示名称
    var displayName: String
) : Parcelable

/**
 * 销售车型配置
 */
@Parcelize
data class SaleModelConfig(
    // 销售代码
    var saleCode: String,
    // 销售车型配置类型
    var type: String,
    // 销售车型配置类型代码
    var typeCode: String,
    // 销售车型配置类型名称
    var typeName: String,
    // 销售车型配置类型价格
    var typePrice: BigDecimal,
    // 销售车型配置类型图片
    var typeImage: List<String>,
    // 销售车型配置类型描述
    var typeDesc: String,
    // 销售车型配置类型参数
    var typeParam: String
) : Parcelable

/**
 * 已选择的销售车型
 */
@Parcelize
data class SelectedSaleModel(
    // 销售代码
    var saleCode: String,
    // 销售车型名称
    var modelName: String,
    // 是否允许意向金
    var earnestMoney: Boolean,
    // 意向金价格
    var earnestMoneyPrice: BigDecimal,
    // 是否允许定金
    var downPayment: Boolean,
    // 定金价格
    var downPaymentPrice: BigDecimal,
    // 车型配置代码
    var modelConfigCode: String,
    // 销售车型图片集
    var saleModelImages: List<String>,
    // 销售车型描述
    var saleModelDesc: String,
    // 销售车型配置名称
    var saleModelConfigName: Map<String, String>,
    // 销售车型配置价格
    var saleModelConfigPrice: Map<String, BigDecimal>,
    // 车型总价格
    var totalPrice: BigDecimal,
    // 购车权益简介
    var purchaseBenefitsIntro: String
) : Parcelable

/**
 * 心愿单
 */
@Parcelize
data class Wishlist(
    // 销售代码
    var saleCode: String,
    // 订单号
    var orderNum: String?,
    // 销售车型配置类型
    var saleModelConfigType: Map<String, String>,
    // 销售车型配置名称
    var saleModelConfigName: Map<String, String>?,
    // 销售车型配置价格
    var saleModelConfigPrice: Map<String, BigDecimal>?,
    // 销售车型图片集
    var saleModelImages: List<String>?,
    // 销售车型描述
    var saleModelDesc: String?,
    // 总价格
    var totalPrice: BigDecimal?,
    // 是否有效
    var isValid: Boolean?
) : Parcelable

/**
 * 上牌区域
 */
@Parcelize
data class LicenseArea(
    // 省级行政区代码
    var provinceCode: String,
    // 地区级行政区代码
    var cityCode: String?,
    // 显示名称
    var displayName: String
) : Parcelable

/**
 * 销售门店
 */
@Parcelize
data class Dealership(
    // 门店代码
    var code: String,
    // 门店名称
    var name: String,
    // 门店地址
    var address: String,
    // 门店距离
    var distance: Double?
) : Parcelable

/**
 * 订单
 */
@Parcelize
data class Order(
    // 销售代码
    var saleCode: String?,
    // 订单号
    var orderNum: String,
    // 订单状态
    var orderState: Int,
    // 销售车型配置类型
    var saleModelConfigType: Map<String, String>,
    // 销售车型配置名称
    var saleModelConfigName: Map<String, String>,
    // 销售车型配置价格
    var saleModelConfigPrice: Map<String, BigDecimal>,
    // 销售车型图片集
    var saleModelImages: List<String>,
    // 销售车型描述
    var saleModelDesc: String,
    // 总价格
    var totalPrice: BigDecimal,
    // 下单时间
    var orderTime: Long,
    // 下单人类型
    var orderPersonType: Int?,
    // 购车方案
    var purchasePlan: Int?,
    // 下单人名称
    var orderPersonName: String?,
    // 下单人证件类型
    var orderPersonIdType: Int?,
    // 下单人证件号
    var orderPersonIdNum: String?,
    // 上牌城市代码
    var licenseCityCode: String?,
    // 销售门店代码
    var dealershipCode: String?,
    // 交付中心代码
    var deliveryCenterCode: String?
) : Parcelable

/**
 * 订单支付请求
 */
@Parcelize
data class OrderPaymentRequest(
    // 订单号
    var orderNum: String,
    // 订单支付阶段
    var orderPaymentPhase: Int,
    // 支付金额
    var paymentAmount: BigDecimal,
    // 支付渠道
    var paymentChannel: String
) : Parcelable

/**
 * 订单支付响应
 */
@Parcelize
data class OrderPaymentResponse(
    // 订单号
    var orderNum: String,
    // 支付商户
    var paymentMerchant: String,
    // 支付流水号
    var paymentReference: String,
    // 支付金额
    var paymentAmount: BigDecimal,
    // 支付数据类型
    var paymentDateType: Int,
    // 支付数据
    var paymentData: String
) : Parcelable

/**
 * 账号信息
 */
@Parcelize
data class AccountInfo(
    var mobile: String,
    var nickname: String,
    var avatar: String?,
    var gender: String
) : Parcelable

/**
 * 车控请求
 */
@Parcelize
data class ControlRequest(
    val vin: String,
    val params: @RawValue Map<String, Any>
) : Parcelable

/**
 * 车控状态
 */
@Parcelize
data class ControlState(
    // 车架号
    val vin: String,
    // 指令ID
    val cmdId: String,
    // 指令状态
    val cmdState: String,
    // 远控指令错误信息
    val failureMsg: String?
) : Parcelable

/**
 * 更新客户端配置请求
 */
@Parcelize
data class UpdateClientConfigRequest(
    // 推送注册ID
    val pushRegId: String,
    // 操作系统
    val os: String = "ANDROID"
) : Parcelable