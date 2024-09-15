package net.hwyz.iov.mp.app.ui.page.vehicle

import net.hwyz.iov.mp.app.base.MviActionProcessor
import net.hwyz.iov.mp.app.data.http.TspApi
import javax.inject.Inject

open class VehicleProcessor @Inject constructor(
    private var service: TspApi,
) :
    MviActionProcessor<VehicleAction, VehicleResult> {

    override suspend fun executeAction(action: VehicleAction): VehicleResult {
        return when (action) {
            VehicleAction.CheckLocalUser -> VehicleResult.DefaultResult
        }
    }
}