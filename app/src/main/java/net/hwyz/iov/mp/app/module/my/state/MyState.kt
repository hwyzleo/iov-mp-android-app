package net.hwyz.iov.mp.app.module.my.state

import net.hwyz.iov.mp.app.base.MviState

data class MyState(
    var isLogged: Boolean = false,
    var nickname: String = "",
    val showLogout: Boolean = false
) : MviState