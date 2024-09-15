package net.hwyz.iov.mp.app.ui.page.vehicle

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

/**
 * 爱车页面
 */
@Composable
fun VehicleView(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: VehicleViewModel = hiltViewModel()
) {
    val viewStates = viewModel.viewStates
    Box {
        VehicleViewStatus(
            navCtrl = navCtrl,
            intent = { intent: VehicleIntent -> viewModel.intent(intent) },
            viewState = viewStates
        )
    }
}