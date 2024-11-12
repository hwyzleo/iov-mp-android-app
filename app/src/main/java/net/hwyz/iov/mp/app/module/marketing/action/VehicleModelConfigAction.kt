package net.hwyz.iov.mp.app.module.marketing.action

import net.hwyz.iov.mp.app.base.MviAction

sealed class VehicleModelConfigAction : MviAction {
    // 获取销售车型列表
    object GetSaleModelList : VehicleModelConfigAction()

    // 保存心愿单
    data class SaveWishlist(
        val saleCode: String,
        val modelCode: String,
        val modelName: String,
        val spareTireCode: String,
        val exteriorCode: String,
        val wheelCode: String,
        val interiorCode: String,
        val adasCode: String
    ) : VehicleModelConfigAction()

}