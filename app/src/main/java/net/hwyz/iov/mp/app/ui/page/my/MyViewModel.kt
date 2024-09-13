package net.hwyz.iov.mp.app.ui.page.my

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import net.hwyz.iov.mp.app.base.presentation.BaseViewModel
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