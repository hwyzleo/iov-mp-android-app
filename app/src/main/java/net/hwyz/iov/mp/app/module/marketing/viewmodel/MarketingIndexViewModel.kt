package net.hwyz.iov.mp.app.module.marketing.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import net.hwyz.iov.mp.app.base.presentation.BaseViewModel
import net.hwyz.iov.mp.app.module.marketing.action.MarketingIndexAction
import net.hwyz.iov.mp.app.module.marketing.intent.MarketingIndexIntent
import net.hwyz.iov.mp.app.module.marketing.processor.MarketingIndexProcessor
import net.hwyz.iov.mp.app.module.marketing.result.MarketingIndexResult
import net.hwyz.iov.mp.app.module.marketing.state.MarketingIndexState
import net.hwyz.iov.mp.app.utils.VehicleManager
import javax.inject.Inject

@HiltViewModel
class MarketingIndexViewModel @Inject constructor(
    override val actionProcessor: MarketingIndexProcessor
) : BaseViewModel<MarketingIndexIntent, MarketingIndexState, MarketingIndexAction, MarketingIndexResult>() {
    var viewStates by mutableStateOf(MarketingIndexState())
    private val _viewEvents = Channel<MarketingIndexViewEvent>(Channel.BUFFERED)
    val viewEvents = _viewEvents.receiveAsFlow()

    override fun actionFromIntent(intent: MarketingIndexIntent): List<MarketingIndexAction> {
        return when (intent) {
            is MarketingIndexIntent.OnLaunched -> listOf(MarketingIndexAction.CheckAndGetCurrentOrder)
        }
    }

    override suspend fun reducer(result: MarketingIndexResult) {
        when (result) {
            is MarketingIndexResult.DisplayWishlist -> {
                viewStates.hasOrder = true
                VehicleManager.getCurrentVehicle()?.let {
                    viewStates.displayName = it.displayName
                }
                result.wishlist.saleModelDesc?.let {
                    viewStates.saleModelDesc = it
                }
                result.wishlist.saleModelImages?.let {
                    viewStates.saleModelImages = it
                }
            }

            is MarketingIndexResult.DisplayOrder -> {
                viewStates.hasOrder = true
            }

            is MarketingIndexResult.Failure -> {
                _viewEvents.send(
                    MarketingIndexViewEvent.ErrorMessage(
                        result.error.message ?: "系统异常"
                    )
                )
            }
        }
    }


}

/**
 * 一次性事件
 */
sealed class MarketingIndexViewEvent {
    object PopBack : MarketingIndexViewEvent()
    data class ErrorMessage(val message: String) : MarketingIndexViewEvent()
}