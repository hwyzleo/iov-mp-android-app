package net.hwyz.iov.mp.app.ui.page.vehicle

import net.hwyz.iov.mp.app.base.MviIntent

sealed class VehicleIntent : MviIntent {
    object OnLaunched : VehicleIntent()
}