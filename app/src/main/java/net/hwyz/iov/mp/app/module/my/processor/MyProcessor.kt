package net.hwyz.iov.mp.app.module.my.processor

import net.hwyz.iov.mp.app.base.MviActionProcessor
import net.hwyz.iov.mp.app.data.http.TspApi
import net.hwyz.iov.mp.app.module.my.action.MyAction
import net.hwyz.iov.mp.app.module.my.result.MyResult
import javax.inject.Inject

open class MyProcessor @Inject constructor(
    private var service: TspApi,
) :
    MviActionProcessor<MyAction, MyResult> {

    override suspend fun executeAction(action: MyAction): MyResult {
        return when (action) {
            MyAction.CheckLocalUser -> MyResult.DefaultResult
        }
    }
}