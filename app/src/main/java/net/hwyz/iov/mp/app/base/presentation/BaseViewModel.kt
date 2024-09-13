package net.hwyz.iov.mp.app.base.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import net.hwyz.iov.mp.app.base.MviAction
import net.hwyz.iov.mp.app.base.MviActionProcessor
import net.hwyz.iov.mp.app.base.MviIntent
import net.hwyz.iov.mp.app.base.MviResult
import net.hwyz.iov.mp.app.base.MviState

abstract class BaseViewModel<I : MviIntent, S : MviState, A : MviAction, R : MviResult> :
    ViewModel(),
    MviViewModel<S> {

    fun intent(intent: I) {
        viewModelScope.launch {
            flow {
                actionFromIntent(intent).forEach {
                    var result = actionProcessor.executeAction(it)
                    emit(reducer(result))
                }
            }.collect()
        }
    }

    protected abstract val actionProcessor: MviActionProcessor<A, R>

    abstract fun actionFromIntent(intent: I): List<A>

    abstract suspend fun reducer(result: R)

}