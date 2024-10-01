package net.hwyz.iov.mp.app.module.vehicle

import net.hwyz.iov.mp.app.base.MviAction

sealed class VehicleAction : MviAction {
    /**
     * 获取车辆状态
     */
    object GetVehicleStatus : VehicleAction()

    /**
     * 寻车
     */
    data class FindVehicle(val vin: String) : VehicleAction()

    /**
     * 获取指令状态
     */
    data class GetCmdState(val vin: String, val cmdId: String) : VehicleAction()
}