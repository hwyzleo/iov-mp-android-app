package net.hwyz.iov.mp.app.module.vehicle

import net.hwyz.iov.mp.app.base.MviState
import java.time.LocalDateTime

data class VehicleState(
    var result: VehicleResult = VehicleResult.DefaultResult,
    var findVehicleLoading: Boolean = false,
    var findVehicleCmdId: String? = null,
    var findVehicleTime: LocalDateTime? = null,
    var findVehicleState: String? = null,
    var findVehicleExecuteTime: Int = 0,
    var findVehicleTimeout: Int = 40,
    var cmdMapping: Map<String, String> = mapOf(),
    val showLogout: Boolean = false
) : MviState