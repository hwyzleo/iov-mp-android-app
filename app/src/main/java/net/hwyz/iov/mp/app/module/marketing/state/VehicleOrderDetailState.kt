package net.hwyz.iov.mp.app.module.marketing.state

import net.hwyz.iov.mp.app.base.MviState
import net.hwyz.iov.mp.app.utils.OrderState
import net.hwyz.iov.mp.app.utils.VehicleType
import java.math.BigDecimal

data class VehicleOrderDetailState(
    var saleModelImages: List<String> = mutableListOf(),
    var saleModelName: String = "",
    var saleModelPrice: BigDecimal = BigDecimal.ZERO,
    var saleSpareTireName: String = "",
    var saleSpareTirePrice: BigDecimal = BigDecimal.ZERO,
    var saleExteriorName: String = "",
    var saleExteriorPrice: BigDecimal = BigDecimal.ZERO,
    var saleWheelName: String = "",
    var saleWheelPrice: BigDecimal = BigDecimal.ZERO,
    var saleInteriorName: String = "",
    var saleInteriorPrice: BigDecimal = BigDecimal.ZERO,
    var saleAdasName: String = "",
    var saleAdasPrice: BigDecimal = BigDecimal.ZERO,
    var totalPrice: BigDecimal = BigDecimal.ZERO
) : MviState