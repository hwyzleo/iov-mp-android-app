package net.hwyz.iov.mp.app.ui.page.my

import net.hwyz.iov.mp.app.base.MviResult

sealed class MyResult : MviResult {
    object DefaultResult : MyResult()
}