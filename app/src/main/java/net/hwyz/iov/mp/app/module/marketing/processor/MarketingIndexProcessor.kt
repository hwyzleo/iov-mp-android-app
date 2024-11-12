package net.hwyz.iov.mp.app.module.marketing.processor

import net.hwyz.iov.mp.app.base.MviActionProcessor
import net.hwyz.iov.mp.app.data.http.TspApi
import net.hwyz.iov.mp.app.module.marketing.action.MarketingIndexAction
import net.hwyz.iov.mp.app.module.marketing.result.MarketingIndexResult
import net.hwyz.iov.mp.app.utils.VehicleManager
import net.hwyz.iov.mp.app.utils.VehicleType
import javax.inject.Inject

open class MarketingIndexProcessor @Inject constructor(
    private var service: TspApi,
) :
    MviActionProcessor<MarketingIndexAction, MarketingIndexResult> {

    override suspend fun executeAction(action: MarketingIndexAction): MarketingIndexResult {
        return when (action) {
            MarketingIndexAction.CheckAndGetCurrentOrder -> {
                try {
                    val response = service.getValidVehicleSaleOrderList()
                    if (response.code == 0) {
                        VehicleManager.update(response.data!!)
                        if (VehicleManager.hasOrder()) {
                            VehicleManager.getCurrentVehicle()?.let {
                                when (it.type) {
                                    VehicleType.WISHLIST -> {
                                        val wishlist = service.getWishlist(it.id)
                                        if (wishlist.code == 0) {
                                            return MarketingIndexResult.DisplayWishlist(wishlist.data!!)
                                        } else {
                                            throw Exception(response.message)
                                        }
                                    }

                                    VehicleType.ACTIVATED -> {
                                        val order = service.getOrder(it.id)
                                        if (order.code == 0) {
                                            MarketingIndexResult.DisplayOrder(order.data!!)
                                        } else {
                                            throw Exception(response.message)
                                        }
                                    }

                                    VehicleType.ORDER -> {

                                    }
                                }
                            }
                        }
                        throw Exception(response.message)
                    } else {
                        throw Exception(response.message)
                    }
                } catch (e: Exception) {
                    MarketingIndexResult.Failure(e)
                }
            }
        }
    }
}