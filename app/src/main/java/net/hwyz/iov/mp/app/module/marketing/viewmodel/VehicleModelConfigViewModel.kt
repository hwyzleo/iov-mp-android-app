package net.hwyz.iov.mp.app.module.marketing.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import net.hwyz.iov.mp.app.base.presentation.BaseViewModel
import net.hwyz.iov.mp.app.module.marketing.action.VehicleModelConfigAction
import net.hwyz.iov.mp.app.module.marketing.intent.VehicleModelConfigIntent
import net.hwyz.iov.mp.app.module.marketing.processor.VehicleModelConfigProcessor
import net.hwyz.iov.mp.app.module.marketing.result.VehicleModelConfigResult
import net.hwyz.iov.mp.app.module.marketing.state.VehicleModelConfigState
import javax.inject.Inject

@HiltViewModel
class VehicleModelConfigViewModel @Inject constructor(
    override val actionProcessor: VehicleModelConfigProcessor
) : BaseViewModel<VehicleModelConfigIntent, VehicleModelConfigState, VehicleModelConfigAction, VehicleModelConfigResult>() {
    var viewStates by mutableStateOf(VehicleModelConfigState())
    private val _viewEvents = Channel<VehicleModelConfigViewEvent>(Channel.BUFFERED)
    val viewEvents = _viewEvents.receiveAsFlow()

    override fun actionFromIntent(intent: VehicleModelConfigIntent): List<VehicleModelConfigAction> {
        return when (intent) {
            is VehicleModelConfigIntent.OnLaunched -> listOf(VehicleModelConfigAction.GetSaleModelList)
            is VehicleModelConfigIntent.OnTapModel -> {
                viewStates = viewStates.copy(
                    selectModel = intent.code
                )
                listOf()
            }

            is VehicleModelConfigIntent.OnTapSpareTire -> {
                viewStates = viewStates.copy(
                    selectSpareTire = intent.code
                )
                listOf()
            }

            is VehicleModelConfigIntent.OnTapExterior -> {
                viewStates = viewStates.copy(
                    selectExterior = intent.code
                )
                listOf()
            }

            is VehicleModelConfigIntent.OnTapWheel -> {
                viewStates = viewStates.copy(
                    selectWheel = intent.code
                )
                listOf()
            }

            is VehicleModelConfigIntent.OnTapInterior -> {
                viewStates = viewStates.copy(
                    selectInterior = intent.code
                )
                listOf()
            }

            is VehicleModelConfigIntent.OnTapAdas -> {
                viewStates = viewStates.copy(
                    selectAdas = intent.code
                )
                listOf()
            }

            is VehicleModelConfigIntent.OnTapSaveWishlist -> {
                listOf(
                    VehicleModelConfigAction.SaveWishlist(
                        intent.saleCode,
                        intent.modelCode,
                        intent.modelName,
                        intent.spareTireCode,
                        intent.exteriorCode,
                        intent.wheelCode,
                        intent.interiorCode,
                        intent.adasCode
                    )
                )
            }
        }
    }

    override suspend fun reducer(result: VehicleModelConfigResult) {
        when (result) {
            is VehicleModelConfigResult.UpdateSaleModel -> {
                viewStates.saleCode = result.saleCode
                viewStates.models.clear()
                viewStates.spareTires.clear()
                viewStates.exteriors.clear()
                viewStates.wheels.clear()
                viewStates.interiors.clear()
                viewStates.adases.clear()
                for (saleModel in result.saleModels) {
                    when (saleModel.type) {
                        "MODEL" -> viewStates.models.add(saleModel)
                        "SPARE_TIRE" -> viewStates.spareTires.add(saleModel)
                        "EXTERIOR" -> viewStates.exteriors.add(saleModel)
                        "WHEEL" -> viewStates.wheels.add(saleModel)
                        "INTERIOR" -> viewStates.interiors.add(saleModel)
                        "ADAS" -> viewStates.adases.add(saleModel)
                    }
                }
            }

            VehicleModelConfigResult.BackToIndex -> {
                _viewEvents.send(VehicleModelConfigViewEvent.PopBack)
            }

            is VehicleModelConfigResult.Failure -> {
                _viewEvents.send(
                    VehicleModelConfigViewEvent.ErrorMessage(
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
sealed class VehicleModelConfigViewEvent {
    object PopBack : VehicleModelConfigViewEvent()
    data class ErrorMessage(val message: String) : VehicleModelConfigViewEvent()
}