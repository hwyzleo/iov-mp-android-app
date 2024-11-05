package net.hwyz.iov.mp.app.module.marketing.intent

import net.hwyz.iov.mp.app.base.MviIntent

sealed class MarketingIndexIntent : MviIntent {
    object OnLaunched : MarketingIndexIntent()
}