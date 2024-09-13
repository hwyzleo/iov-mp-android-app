package net.hwyz.iov.mp.app.ui.page.my

import net.hwyz.iov.mp.app.base.MviIntent

sealed class MyIntent : MviIntent {
    object OnLaunched : MyIntent()
}