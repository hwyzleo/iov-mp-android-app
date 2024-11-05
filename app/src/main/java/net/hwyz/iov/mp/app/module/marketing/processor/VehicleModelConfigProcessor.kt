package net.hwyz.iov.mp.app.module.marketing.processor

import net.hwyz.iov.mp.app.base.MviActionProcessor
import net.hwyz.iov.mp.app.data.http.TspApi
import net.hwyz.iov.mp.app.module.marketing.action.VehicleModelConfigAction
import net.hwyz.iov.mp.app.module.marketing.result.VehicleModelConfigResult
import javax.inject.Inject

open class VehicleModelConfigProcessor @Inject constructor(
    private var service: TspApi,
) :
    MviActionProcessor<VehicleModelConfigAction, VehicleModelConfigResult> {

    override suspend fun executeAction(action: VehicleModelConfigAction): VehicleModelConfigResult {
        return when (action) {
            VehicleModelConfigAction.GetSaleModelList -> {
                try {
                    val saleModelList = service.getSaleModelList(saleCode = "H01")
                    if (saleModelList.code == 0) {
                        VehicleModelConfigResult.UpdateSaleModel.Success(
                            "H01",
                            saleModelList.data!!
                        )
                    } else {
                        throw Exception(saleModelList.message)
                    }
                } catch (e: Exception) {
                    VehicleModelConfigResult.UpdateSaleModel.Failure(e)
                }
            }
        }
    }
}