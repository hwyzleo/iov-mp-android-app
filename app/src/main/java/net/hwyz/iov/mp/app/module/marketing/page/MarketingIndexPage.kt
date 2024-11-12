package net.hwyz.iov.mp.app.module.marketing.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import net.hwyz.iov.mp.app.module.marketing.intent.MarketingIndexIntent
import net.hwyz.iov.mp.app.module.marketing.viewmodel.MarketingIndexViewModel
import net.hwyz.iov.mp.app.utils.VehicleManager

/**
 * 购车首页
 *
 * @author hwyz_leo
 */
@Composable
fun MarketingIndexPage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: MarketingIndexViewModel = hiltViewModel()
) {
    val viewStates = viewModel.viewStates
    viewStates.hasOrder = VehicleManager.hasOrder()
    LaunchedEffect(Unit) {
        viewModel.intent(MarketingIndexIntent.OnLaunched)
    }
    val hasOrder = remember { mutableStateOf(viewStates.hasOrder) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        if (!hasOrder.value) {
            MarketingIndexPageNoOrder(
                navCtrl = navCtrl,
                intent = { intent: MarketingIndexIntent -> viewModel.intent(intent) },
                viewState = viewStates
            )
        } else {
            MarketingIndexPageOrder(
                navCtrl = navCtrl,
                intent = { intent: MarketingIndexIntent -> viewModel.intent(intent) },
                viewState = viewStates
            )
        }
    }
}