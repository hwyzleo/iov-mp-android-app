package net.hwyz.iov.mp.app.ui.page.vehicle

import net.hwyz.iov.mp.app.base.MviIntent

sealed class VehicleIntent : MviIntent {
    object OnLaunched : VehicleIntent()

    /**
     * 寻车
     */
    data class FindVehicle(val vin: String) : VehicleIntent()
}