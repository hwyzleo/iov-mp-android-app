package net.hwyz.iov.mp.app.module.marketing.action

import net.hwyz.iov.mp.app.base.MviAction

sealed class VehicleModelConfigAction : MviAction {
    // 获取销售车型列表
    object GetSaleModelList : VehicleModelConfigAction()

}