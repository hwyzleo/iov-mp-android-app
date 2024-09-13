package net.hwyz.iov.mp.app.ui.page.my

import net.hwyz.iov.mp.app.base.MviAction

sealed class MyAction : MviAction {
    object CheckLocalUser : MyAction()
}