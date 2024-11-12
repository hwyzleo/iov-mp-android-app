package net.hwyz.iov.mp.app.module.marketing.state

import net.hwyz.iov.mp.app.base.MviState

data class MarketingIndexState(
    var hasOrder: Boolean = false,
    var displayName: String = "",
    var saleModelImages: List<String> = emptyList(),
    var saleModelDesc: String = ""
) : MviState