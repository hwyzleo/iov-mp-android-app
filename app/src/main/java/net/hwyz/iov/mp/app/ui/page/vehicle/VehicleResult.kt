package net.hwyz.iov.mp.app.ui.page.vehicle

import net.hwyz.iov.mp.app.base.MviResult

sealed class VehicleResult : MviResult {
    object DefaultResult : VehicleResult()
}