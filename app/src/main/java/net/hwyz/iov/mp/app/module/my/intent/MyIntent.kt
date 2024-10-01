package net.hwyz.iov.mp.app.module.my.intent

import net.hwyz.iov.mp.app.base.MviIntent

sealed class MyIntent : MviIntent {
    object OnLaunched : MyIntent()
}