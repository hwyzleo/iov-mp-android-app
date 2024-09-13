package net.hwyz.iov.mp.app.ui.page.my.profile

import net.hwyz.iov.mp.app.base.MviResult
import net.hwyz.iov.mp.app.data.bean.AccountInfo

sealed class ProfileResult : MviResult {
    object DisplayLoading : ProfileResult()
    object LoadProfile : ProfileResult() {
        data class Success(val accountInfo: AccountInfo) : ProfileResult()
        data class Failure(val error: Throwable) : ProfileResult()
    }

    object DisplayProfile : ProfileResult()
    object DisplayNickname : ProfileResult()
    object UpdateNickname : ProfileResult() {
        data class Success(val nickname: String) : ProfileResult()
        data class Failure(val error: Throwable) : ProfileResult()
    }

    object DisplayGender : ProfileResult()
    object UpdateGender : ProfileResult() {
        data class Success(val gender: String) : ProfileResult()
        data class Failure(val error: Throwable) : ProfileResult()
    }
}