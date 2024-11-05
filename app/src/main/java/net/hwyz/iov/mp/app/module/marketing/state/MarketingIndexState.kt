package net.hwyz.iov.mp.app.module.marketing.state

import net.hwyz.iov.mp.app.base.MviState

data class MarketingIndexState(
    var hasOrder: Boolean = false
) : MviState