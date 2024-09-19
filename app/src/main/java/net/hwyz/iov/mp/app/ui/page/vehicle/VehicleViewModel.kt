package net.hwyz.iov.mp.app.ui.page.vehicle

import android.util.Log
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
import net.hwyz.iov.mp.app.utils.CommonUtil
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
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
                startFindVehicleCmdStateCheckTask(intent.vin)
                listOf(VehicleAction.FindVehicle(intent.vin))
            }
        }
    }

    private fun startFindVehicleCmdStateCheckTask(vin: String) {
        startFindVehicleCmdStateCheckJob?.cancel()
        startFindVehicleCmdStateCheckJob = viewModelScope.launch {
            while (isActive && !isFindVehicleTimeout()) {
                delay(1000)
                if (viewStates.findVehicleCmdId != null) {
                    reducer(
                        actionProcessor.executeAction(
                            VehicleAction.GetCmdState(
                                vin,
                                viewStates.findVehicleCmdId!!
                            )
                        )
                    )
                }
            }
        }
    }

    private fun isFindVehicleTimeout(): Boolean {
        if (viewStates.findVehicleTime != null && ChronoUnit.SECONDS.between(
                viewStates.findVehicleTime,
                LocalDateTime.now()
            ) > viewStates.findVehicleTimeout
        ) {
            startFindVehicleCmdStateCheckJob?.cancel()
            viewStates = viewStates.copy(
                findVehicleLoading = false,
                findVehicleExecuteTime = 0,
                findVehicleCmdId = null,
                findVehicleTime = null,
                findVehicleState = null
            )
            return true
        }
        return false
    }

    override suspend fun reducer(result: VehicleResult) {
        when (result) {
            is VehicleResult.DefaultResult -> {}
            is VehicleResult.FindVehicle.Success -> findVehicleSuccess(
                result.vin,
                result.cmdId,
                result.cmdState
            )

            is VehicleResult.FindVehicle.Failure -> findVehicleFailure(result.error)
            is VehicleResult.GetCmdState.Success -> getCmdStateSuccess(
                result.cmdId,
                result.cmdState,
                result.failureMsg
            )

            else -> {}
        }
    }

    private fun findVehicleSuccess(vin: String, cmdId: String, cmdState: String) {
        viewStates = viewStates.copy(
            findVehicleCmdId = cmdId,
            findVehicleTime = LocalDateTime.now(),
            findVehicleState = cmdState,
            cmdMapping = mapOf(cmdId to "FIND_VEHICLE")
        )
    }

    private suspend fun findVehicleFailure(error: Throwable) {
        viewStates = viewStates.copy(
            findVehicleLoading = false,
            findVehicleCmdId = null,
            findVehicleTime = null,
            findVehicleState = null
        )
        startFindVehicleCmdStateCheckJob?.cancel()
        _viewEvents.send(VehicleViewEvent.InfoMessage(CommonUtil.convertErrorMsg(error.message)))
    }

    private suspend fun getCmdStateSuccess(cmdId: String, cmdState: String, failureMsg: String?) {
        viewStates.cmdMapping[cmdId]?.let { cmdType ->
            if (cmdState == "EXECUTING" || cmdState == "SUCCESS" || cmdState == "FAILURE") {
                when (cmdType) {
                    "FIND_VEHICLE" -> getFindVehicleStateSuccess(cmdId, cmdState)
                }
                if (cmdState == "SUCCESS") {
                    _viewEvents.send(VehicleViewEvent.InfoMessage("执行完成"))
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

    private suspend fun getFindVehicleStateSuccess(cmdId: String, cmdState: String) {
        if (viewStates.findVehicleState == "SENT" && cmdState == "EXECUTING") {
            viewStates = viewStates.copy(
                findVehicleLoading = false,
                findVehicleExecuteTime = 10,
                findVehicleState = cmdState
            )
            _viewEvents.send(VehicleViewEvent.InfoMessage("操作成功"))
        } else if (viewStates.findVehicleState == "EXECUTING" && cmdState != "EXECUTING") {
            viewStates = viewStates.copy(
                findVehicleLoading = false,
                findVehicleExecuteTime = 0,
                findVehicleCmdId = null,
                findVehicleTime = null,
                findVehicleState = cmdState
            )
            startFindVehicleCmdStateCheckJob?.cancel()
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