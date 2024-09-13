package net.hwyz.iov.mp.app.ui.page.my

import net.hwyz.iov.mp.app.base.MviState

data class MyState(
    var isLogged: Boolean = false,
    var nickname: String = "",
    val showLogout: Boolean = false
) : MviState