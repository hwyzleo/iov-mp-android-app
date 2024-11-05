package net.hwyz.iov.mp.app.module.marketing.result

import net.hwyz.iov.mp.app.base.MviResult
import net.hwyz.iov.mp.app.data.bean.Order
import net.hwyz.iov.mp.app.data.bean.Wishlist

sealed class MarketingIndexResult : MviResult {
    /**
     * 显示心愿单
     */
    object DisplayWishlist : MarketingIndexResult() {
        data class Success(val wishlist: Wishlist) : MarketingIndexResult()

        data class Failure(val error: Throwable) : MarketingIndexResult()
    }

    /**
     * 显示订单
     */
    object DisplayOrder : MarketingIndexResult() {
        data class Success(val order: Order) : MarketingIndexResult()

        data class Failure(val error: Throwable) : MarketingIndexResult()
    }
}