package net.hwyz.iov.mp.app.module.vehicle

import net.hwyz.iov.mp.app.base.MviActionProcessor
import net.hwyz.iov.mp.app.data.bean.ControlRequest
import net.hwyz.iov.mp.app.data.http.TspApi
import javax.inject.Inject

open class VehicleProcessor @Inject constructor(private var tspApi: TspApi) :
    MviActionProcessor<VehicleAction, VehicleResult> {

    override suspend fun executeAction(action: VehicleAction): VehicleResult {
        return when (action) {
            is VehicleAction.GetVehicleStatus -> getVehicleStatus()
            is VehicleAction.FindVehicle -> findVehicle(action.vin)
            is VehicleAction.GetCmdState -> getCmdState(action.vin, action.cmdId)
        }
    }

    private suspend fun getVehicleStatus(): VehicleResult {
        return VehicleResult.FindVehicle
    }

    private suspend fun findVehicle(vin: String): VehicleResult {
        return try {
            val response = tspApi.findVehicle(ControlRequest(vin, emptyMap()))
            if (response.code == 0) {
                VehicleResult.FindVehicle.Success(
                    response.data!!.vin,
                    response.data!!.cmdId,
                    response.data!!.cmdState
                )
            } else {
                throw Exception(response.message)
            }
        } catch (e: Exception) {
            VehicleResult.FindVehicle.Failure(e)
        }
    }

    private suspend fun getCmdState(vin: String, cmdId: String): VehicleResult {
        return try {
            val response = tspApi.getCmdState(vin, cmdId)
            if (response.code == 0) {
                VehicleResult.GetCmdState.Success(
                    response.data!!.cmdId,
                    response.data!!.cmdState,
                    response.data!!.failureMsg
                )
            } else {
                throw Exception(response.message)
            }
        } catch (e: Exception) {
            VehicleResult.GetCmdState.Failure(e)
        }
    }
}