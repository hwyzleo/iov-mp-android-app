package net.hwyz.iov.mp.app.data.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import retrofit2.http.Field

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
 * 账号信息
 */
@Parcelize
data class AccountInfo(
    var mobile: String,
    var nickname: String,
    var avatar: String?,
    var gender: String
) : Parcelable