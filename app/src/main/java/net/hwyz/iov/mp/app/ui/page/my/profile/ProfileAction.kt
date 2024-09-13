package net.hwyz.iov.mp.app.ui.page.my.profile

import net.hwyz.iov.mp.app.base.MviAction

sealed class ProfileAction : MviAction {
    object DisplayLoading : ProfileAction()
    object LoadProfile : ProfileAction()
    object DisplayProfile : ProfileAction()
    object DisplayNickname : ProfileAction()
    data class UpdateNickname(val nickname: String) : ProfileAction()
    object DisplayGender : ProfileAction()
    data class UpdateGender(val gender: String) : ProfileAction()
}