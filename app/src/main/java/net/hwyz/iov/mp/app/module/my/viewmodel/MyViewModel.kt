package net.hwyz.iov.mp.app.module.my.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import net.hwyz.iov.mp.app.base.presentation.BaseViewModel
import net.hwyz.iov.mp.app.module.my.action.MyAction
import net.hwyz.iov.mp.app.module.my.intent.MyIntent
import net.hwyz.iov.mp.app.module.my.processor.MyProcessor
import net.hwyz.iov.mp.app.module.my.result.MyResult
import net.hwyz.iov.mp.app.module.my.state.MyState
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    override val actionProcessor: MyProcessor
) : BaseViewModel<MyIntent, MyState, MyAction, MyResult>() {
    var viewStates by mutableStateOf(MyState())
        private set

    override fun actionFromIntent(intent: MyIntent): List<MyAction> {
        return when (intent) {
            is MyIntent.OnLaunched -> listOf(MyAction.CheckLocalUser)
        }
    }

    override suspend fun reducer(result: MyResult) {
        when (result) {
            MyResult.DefaultResult -> {}
        }
    }


}