package net.hwyz.iov.mp.app.ui.page.vehicle

import net.hwyz.iov.mp.app.base.MviAction

sealed class VehicleAction : MviAction {
    object CheckLocalUser : VehicleAction()
}