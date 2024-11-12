package net.hwyz.iov.mp.app.module.marketing.processor

import net.hwyz.iov.mp.app.base.MviActionProcessor
import net.hwyz.iov.mp.app.data.bean.TspResponse
import net.hwyz.iov.mp.app.data.bean.Wishlist
import net.hwyz.iov.mp.app.data.http.TspApi
import net.hwyz.iov.mp.app.module.marketing.action.VehicleModelConfigAction
import net.hwyz.iov.mp.app.module.marketing.result.VehicleModelConfigResult
import net.hwyz.iov.mp.app.utils.VehicleManager
import net.hwyz.iov.mp.app.utils.VehicleType
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
                        VehicleModelConfigResult.UpdateSaleModel(
                            "H01",
                            saleModelList.data!!
                        )
                    } else {
                        throw Exception(saleModelList.message)
                    }
                } catch (e: Exception) {
                    VehicleModelConfigResult.Failure(e)
                }
            }

            is VehicleModelConfigAction.SaveWishlist -> {
                try {
                    val saleModelConfigType = mutableMapOf<String, String>()
                    saleModelConfigType["MODEL"] = action.modelCode
                    saleModelConfigType["SPARE_TIRE"] = action.spareTireCode
                    saleModelConfigType["EXTERIOR"] = action.exteriorCode
                    saleModelConfigType["WHEEL"] = action.wheelCode
                    saleModelConfigType["INTERIOR"] = action.interiorCode
                    saleModelConfigType["ADAS"] = action.adasCode
                    var response: TspResponse<String>
                    if (VehicleManager.getCurrentVehicleId() == null) {
                        response = service.createWishlist(
                            Wishlist(
                                saleCode = action.saleCode,
                                orderNum = null,
                                saleModelConfigType = saleModelConfigType,
                                saleModelConfigName = null,
                                saleModelConfigPrice = null,
                                saleModelImages = null,
                                saleModelDesc = null,
                                totalPrice = null,
                                isValid = null
                            )
                        )
                    } else {
                        response = service.modifyWishlist(
                            Wishlist(
                                saleCode = action.saleCode,
                                orderNum = null,
                                saleModelConfigType = saleModelConfigType,
                                saleModelConfigName = null,
                                saleModelConfigPrice = null,
                                saleModelImages = null,
                                saleModelDesc = null,
                                totalPrice = null,
                                isValid = null
                            )
                        )
                    }
                    if (response.code == 0) {
                        VehicleManager.add(response.data!!, VehicleType.WISHLIST, action.modelName)
                        VehicleManager.setCurrentVehicleId(response.data!!)
                        VehicleModelConfigResult.BackToIndex
                    } else {
                        throw Exception(response.message)
                    }
                } catch (e: Exception) {
                    VehicleModelConfigResult.Failure(e)
                }
            }
        }
    }
}