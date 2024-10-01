package net.hwyz.iov.mp.app.module.my.profile

import net.hwyz.iov.mp.app.base.MviState

data class ProfileState(
    var result: ProfileResult = ProfileResult.DisplayProfile,
    var avatar: String? = null,
    var nickname: String = "",
    var gender: String = "UNKNOWN"
) : MviState