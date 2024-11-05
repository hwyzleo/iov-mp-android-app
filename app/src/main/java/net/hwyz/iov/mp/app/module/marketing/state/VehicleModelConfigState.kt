package net.hwyz.iov.mp.app.module.marketing.state

import net.hwyz.iov.mp.app.base.MviState
import net.hwyz.iov.mp.app.data.bean.SaleModelConfig
import java.math.BigDecimal

data class VehicleModelConfigState(
    var saleCode: String = "",
    var models: MutableList<SaleModelConfig> = mutableListOf(),
    var selectModel: String = "",
    var selectModelName: String = "",
    var selectModelPrice: BigDecimal = BigDecimal.ZERO,
    var spareTires: MutableList<SaleModelConfig> = mutableListOf(),
    var selectSpareTire: String = "",
    var selectSpareTirePrice: BigDecimal = BigDecimal.ZERO,
    var exteriors: MutableList<SaleModelConfig> = mutableListOf(),
    var selectExterior: String = "",
    var selectExteriorPrice: BigDecimal = BigDecimal.ZERO,
    var wheels: MutableList<SaleModelConfig> = mutableListOf(),
    var selectWheel: String = "",
    var selectWheelPrice: BigDecimal = BigDecimal.ZERO,
    var interiors: MutableList<SaleModelConfig> = mutableListOf(),
    var selectInterior: String = "",
    var selectInteriorPrice: BigDecimal = BigDecimal.ZERO,
    var adases: MutableList<SaleModelConfig> = mutableListOf(),
    var selectAdas: String = "",
    var selectAdasPrice: BigDecimal = BigDecimal.ZERO,
    var totalPrice: BigDecimal = BigDecimal.ZERO,
    var isShowTotalPrice: Boolean = false
) : MviState