package net.hwyz.iov.mp.app.module.marketing.result

import net.hwyz.iov.mp.app.base.MviResult
import net.hwyz.iov.mp.app.data.bean.SaleModelConfig

sealed class VehicleModelConfigResult : MviResult {
    // 更新销售车型
    data class UpdateSaleModel(val saleCode: String, val saleModels: List<SaleModelConfig>) :
        VehicleModelConfigResult()

    // 返回首页
    object BackToIndex : VehicleModelConfigResult()

    data class Failure(val error: Throwable) : VehicleModelConfigResult()
}