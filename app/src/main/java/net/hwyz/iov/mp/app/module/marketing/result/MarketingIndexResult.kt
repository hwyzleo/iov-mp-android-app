package net.hwyz.iov.mp.app.module.marketing.result

import net.hwyz.iov.mp.app.base.MviResult
import net.hwyz.iov.mp.app.data.bean.Order
import net.hwyz.iov.mp.app.data.bean.Wishlist

sealed class MarketingIndexResult : MviResult {
    // 显示心愿单
    data class DisplayWishlist(val wishlist: Wishlist) : MarketingIndexResult()

    // 显示订单
    data class DisplayOrder(val order: Order) : MarketingIndexResult()

    // 失败
    data class Failure(val error: Throwable) : MarketingIndexResult()
}