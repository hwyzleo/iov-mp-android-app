package net.hwyz.iov.mp.app.ui.page.vehicle

import net.hwyz.iov.mp.app.base.MviState

data class VehicleState(
    var isLogged: Boolean = false,
    var nickname: String = "",
    val showLogout: Boolean = false
) : MviState