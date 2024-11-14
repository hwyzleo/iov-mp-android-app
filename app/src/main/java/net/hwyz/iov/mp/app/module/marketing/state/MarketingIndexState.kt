package net.hwyz.iov.mp.app.module.marketing.state

import net.hwyz.iov.mp.app.base.MviState
import net.hwyz.iov.mp.app.utils.OrderState
import net.hwyz.iov.mp.app.utils.VehicleType

data class MarketingIndexState(
    var hasOrder: Boolean = false,
    var displayName: String = "",
    var saleModelImages: List<String> = emptyList(),
    var saleModelDesc: String = "",
    var vehicleType: VehicleType = VehicleType.WISHLIST,
    var orderState: OrderState = OrderState.WISHLIST
) : MviState