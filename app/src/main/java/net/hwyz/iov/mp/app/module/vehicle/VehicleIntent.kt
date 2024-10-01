package net.hwyz.iov.mp.app.module.vehicle

import net.hwyz.iov.mp.app.base.MviIntent

sealed class VehicleIntent : MviIntent {
    /**
     * 页面加载
     */
    object OnLaunched : VehicleIntent()

    /**
     * 寻车
     */
    data class FindVehicle(val vin: String) : VehicleIntent()
}