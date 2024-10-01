package net.hwyz.iov.mp.app.module.my.action

import net.hwyz.iov.mp.app.base.MviAction

sealed class MyAction : MviAction {
    object CheckLocalUser : MyAction()
}