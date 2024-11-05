package net.hwyz.iov.mp.app.module.marketing.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import net.hwyz.iov.mp.app.base.presentation.BaseViewModel
import net.hwyz.iov.mp.app.module.marketing.action.MarketingIndexAction
import net.hwyz.iov.mp.app.module.marketing.intent.MarketingIndexIntent
import net.hwyz.iov.mp.app.module.marketing.processor.MarketingIndexProcessor
import net.hwyz.iov.mp.app.module.marketing.result.MarketingIndexResult
import net.hwyz.iov.mp.app.module.marketing.state.MarketingIndexState
import javax.inject.Inject

@HiltViewModel
class MarketingIndexViewModel @Inject constructor(
    override val actionProcessor: MarketingIndexProcessor
) : BaseViewModel<MarketingIndexIntent, MarketingIndexState, MarketingIndexAction, MarketingIndexResult>() {
    var viewStates by mutableStateOf(MarketingIndexState())
        private set

    override fun actionFromIntent(intent: MarketingIndexIntent): List<MarketingIndexAction> {
        return when (intent) {
            is MarketingIndexIntent.OnLaunched -> listOf(MarketingIndexAction.CheckAndGetCurrentOrder)
        }
    }

    override suspend fun reducer(result: MarketingIndexResult) {
        when (result) {
            is MarketingIndexResult.DisplayWishlist.Success -> {
                viewStates.hasOrder = true
            }

            is MarketingIndexResult.DisplayWishlist.Failure -> {

            }

            is MarketingIndexResult.DisplayOrder.Success -> {
                viewStates.hasOrder = true
            }

            is MarketingIndexResult.DisplayOrder.Failure -> {

            }

            else -> {}
        }
    }


}