package net.hwyz.iov.mp.app.ui.page.vehicle

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import net.hwyz.iov.mp.app.base.presentation.BaseViewModel
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(override val actionProcessor: VehicleProcessor) :
    BaseViewModel<VehicleIntent, VehicleState, VehicleAction, VehicleResult>() {
    var viewStates by mutableStateOf(VehicleState())
    private val _viewEvents = Channel<VehicleViewEvent>(Channel.BUFFERED)
    val viewEvents = _viewEvents.receiveAsFlow()

    private var startFindVehicleCmdStateCheckJob: Job? = null

    override fun actionFromIntent(intent: VehicleIntent): List<VehicleAction> {
        return when (intent) {
            is VehicleIntent.OnLaunched -> listOf(VehicleAction.CheckLocalUser)
            is VehicleIntent.FindVehicle -> {
                viewStates = viewStates.copy(findVehicleLoading = true)
                listOf(VehicleAction.FindVehicle(intent.vin))
            }
        }
    }

    private fun startFindVehicleCmdStateCheckTask(vin: String, cmdId: String) {
        startFindVehicleCmdStateCheckJob?.cancel()
        startFindVehicleCmdStateCheckJob = viewModelScope.launch {
            while (isActive && viewStates.findVehicleCmdId != null) {
                delay(1000)
                reducer(actionProcessor.executeAction(VehicleAction.GetCmdState(vin, cmdId)))
            }
        }
    }

    override suspend fun reducer(result: VehicleResult) {
        when (result) {
            is VehicleResult.DefaultResult -> {}
            is VehicleResult.FindVehicle.Success -> findVehicleSuccess(result.vin, result.cmdId)
            is VehicleResult.FindVehicle.Failure -> {}
            is VehicleResult.GetCmdState.Success -> getCmdStateSuccess(
                result.cmdId,
                result.cmdState,
                result.failureMsg
            )

            else -> {}
        }
    }

    private fun findVehicleSuccess(vin: String, cmdId: String) {
        viewStates = viewStates.copy(
            findVehicleCmdId = cmdId,
            findVehicleTime = LocalDateTime.now(),
            cmdMapping = mapOf(cmdId to "FIND_VEHICLE")
        )
        startFindVehicleCmdStateCheckTask(vin, cmdId)
    }

    private suspend fun getCmdStateSuccess(cmdId: String, cmdState: String, failureMsg: String?) {
        viewStates.cmdMapping[cmdId]?.let { cmdType ->
            if (cmdState == "EXECUTING" || cmdState == "SUCCESS" || cmdState == "FAILURE") {
                when (cmdType) {
                    "FIND_VEHICLE" -> {
                        if (cmdState == "EXECUTING") {
                            viewStates = viewStates.copy(
                                findVehicleLoading = false,
                                findVehicleExecuteTime = 10
                            )
                            _viewEvents.send(VehicleViewEvent.InfoMessage("操作成功"))
                        } else {
                            viewStates = viewStates.copy(
                                findVehicleLoading = false,
                                findVehicleExecuteTime = 0,
                                findVehicleCmdId = null,
                                findVehicleTime = null
                            )
                            if (cmdState == "SUCCESS") {
                                _viewEvents.send(VehicleViewEvent.InfoMessage("执行完成"))
                            }
                        }
                    }
                }
                if (cmdState == "FAILURE") {
                    if (failureMsg != null) {
                        _viewEvents.send(VehicleViewEvent.InfoMessage(failureMsg))
                    } else {
                        _viewEvents.send(VehicleViewEvent.InfoMessage("操作失败"))
                    }
                }
            }
        }
    }
}

/**
 * 一次性事件
 */
sealed class VehicleViewEvent {
    object PopBack : VehicleViewEvent()
    data class InfoMessage(val message: String) : VehicleViewEvent()
    data class ErrorMessage(val message: String) : VehicleViewEvent()
}