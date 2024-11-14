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
import net.hwyz.iov.mp.app.module.marketing.intent.VehicleOrderDetailIntent
import net.hwyz.iov.mp.app.module.marketing.processor.MarketingIndexProcessor
import net.hwyz.iov.mp.app.module.marketing.result.MarketingIndexResult
import net.hwyz.iov.mp.app.module.marketing.state.MarketingIndexState
import net.hwyz.iov.mp.app.module.marketing.state.VehicleOrderDetailState
import net.hwyz.iov.mp.app.utils.VehicleManager
import javax.inject.Inject

@HiltViewModel
class VehicleOrderDetailViewModel @Inject constructor(
    override val actionProcessor: MarketingIndexProcessor
) : BaseViewModel<VehicleOrderDetailIntent, VehicleOrderDetailState, MarketingIndexAction, MarketingIndexResult>() {
    var viewStates by mutableStateOf(VehicleOrderDetailState())
    private val _viewEvents = Channel<MarketingIndexViewEvent>(Channel.BUFFERED)
    val viewEvents = _viewEvents.receiveAsFlow()

    override fun actionFromIntent(intent: VehicleOrderDetailIntent): List<MarketingIndexAction> {
        return when (intent) {
            is VehicleOrderDetailIntent.OnLaunched -> listOf(MarketingIndexAction.CheckAndGetCurrentOrder)
        }
    }

    override suspend fun reducer(result: MarketingIndexResult) {
        when (result) {
            is MarketingIndexResult.DisplayWishlist -> {
            }

            is MarketingIndexResult.DisplayOrder -> {

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
sealed class VehicleOrderDetailViewEvent {
    object PopBack : VehicleOrderDetailViewEvent()
    data class ErrorMessage(val message: String) : VehicleOrderDetailViewEvent()
}