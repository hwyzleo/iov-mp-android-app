package net.hwyz.iov.mp.app.module.my.result

import net.hwyz.iov.mp.app.base.MviResult

sealed class MyResult : MviResult {
    object DefaultResult : MyResult()
}