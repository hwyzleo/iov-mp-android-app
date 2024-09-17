package net.hwyz.iov.mp.app.ui.page.vehicle

import net.hwyz.iov.mp.app.base.MviState
import java.time.LocalDateTime

data class VehicleState(
    var result: VehicleResult = VehicleResult.DefaultResult,
    var findVehicleLoading: Boolean = false,
    var findVehicleCmdId: String? = null,
    var findVehicleTime: LocalDateTime? = null,
    var findVehicleExecuteTime: Int = 0,
    var cmdMapping: Map<String, String> = mapOf(),
    val showLogout: Boolean = false
) : MviState