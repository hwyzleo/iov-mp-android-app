package net.hwyz.iov.mp.app.ui.page.vehicle

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import net.hwyz.iov.mp.app.base.presentation.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class VehicleViewModel @Inject constructor(
    override val actionProcessor: VehicleProcessor
) : BaseViewModel<VehicleIntent, VehicleState, VehicleAction, VehicleResult>() {
    var viewStates by mutableStateOf(VehicleState())
        private set

    override fun actionFromIntent(intent: VehicleIntent): List<VehicleAction> {
        return when (intent) {
            is VehicleIntent.OnLaunched -> listOf(VehicleAction.CheckLocalUser)
        }
    }

    override suspend fun reducer(result: VehicleResult) {
        when (result) {
            VehicleResult.DefaultResult -> {}
        }
    }


}