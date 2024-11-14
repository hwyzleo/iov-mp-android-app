package net.hwyz.iov.mp.app.module.marketing.intent

import net.hwyz.iov.mp.app.base.MviIntent
import net.hwyz.iov.mp.app.utils.OrderState

sealed class VehicleOrderDetailIntent : MviIntent {
    // 页面加载
    object OnLaunched : VehicleOrderDetailIntent()
}