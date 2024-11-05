package net.hwyz.iov.mp.app.module.marketing.intent

import net.hwyz.iov.mp.app.base.MviIntent
import java.math.BigDecimal

sealed class VehicleModelConfigIntent : MviIntent {
    // 页面加载
    object OnLaunched : VehicleModelConfigIntent()

    // 点击车型
    data class OnTapModel(val code: String, val name: String, val price: BigDecimal) :
        VehicleModelConfigIntent()

    // 点击备胎
    data class OnTapSpareTire(val code: String) : VehicleModelConfigIntent()

    // 点击智驾
    data class OnTapAdas(val code: String) : VehicleModelConfigIntent()
}