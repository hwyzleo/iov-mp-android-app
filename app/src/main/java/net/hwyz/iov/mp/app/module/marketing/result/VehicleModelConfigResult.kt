package net.hwyz.iov.mp.app.module.marketing.result

import net.hwyz.iov.mp.app.base.MviResult
import net.hwyz.iov.mp.app.data.bean.SaleModelConfig

sealed class VehicleModelConfigResult : MviResult {
    // 更新销售车型
    object UpdateSaleModel : VehicleModelConfigResult() {
        data class Success(val saleCode: String, val saleModels: List<SaleModelConfig>) :
            VehicleModelConfigResult()

        data class Failure(val error: Throwable) : VehicleModelConfigResult()
    }

    object DefaultResult : VehicleModelConfigResult()
}