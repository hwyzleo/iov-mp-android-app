package net.hwyz.iov.mp.app.module.marketing.action

import net.hwyz.iov.mp.app.base.MviAction

sealed class MarketingIndexAction : MviAction {

    /**
     * 检查并获取当前订单
     */
    object CheckAndGetCurrentOrder : MarketingIndexAction()

}