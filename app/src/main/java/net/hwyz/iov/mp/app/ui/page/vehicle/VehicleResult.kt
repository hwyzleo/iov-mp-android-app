package net.hwyz.iov.mp.app.ui.page.vehicle

import net.hwyz.iov.mp.app.base.MviResult

sealed class VehicleResult : MviResult {
    object DefaultResult : VehicleResult()

    object FindVehicle : VehicleResult() {
        data class Success(val vin: String, val cmdId: String) : VehicleResult()
        data class Failure(val error: Throwable) : VehicleResult()
    }

    object GetCmdState : VehicleResult() {
        data class Success(val cmdId: String, val cmdState: String, val failureMsg: String?) :
            VehicleResult()

        data class Failure(val error: Throwable) : VehicleResult()
    }
}