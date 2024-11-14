package net.hwyz.iov.mp.app.module.marketing.page

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import net.hwyz.iov.mp.app.module.marketing.intent.VehicleOrderDetailIntent
import net.hwyz.iov.mp.app.module.marketing.viewmodel.VehicleOrderDetailViewModel
import net.hwyz.iov.mp.app.utils.GlobalStateManager
import net.hwyz.iov.mp.app.utils.OrderState

/**
 * 车辆订单详情页
 *
 * @author hwyz_leo
 */
@Composable
fun VehicleOrderDetailPage(
    navCtrl: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: VehicleOrderDetailViewModel = hiltViewModel()
) {
    val viewStates = viewModel.viewStates
    LaunchedEffect(Unit) {
        viewModel.intent(VehicleOrderDetailIntent.OnLaunched)
    }
    val orderState: OrderState = GlobalStateManager.parameters["orderState"] as OrderState
    when (orderState) {
        OrderState.WISHLIST -> VehicleOrderDetailPageWishlist(
            navCtrl = navCtrl,
            intent = { intent: VehicleOrderDetailIntent -> viewModel.intent(intent) },
            viewState = viewStates
        )

        OrderState.EARNEST_MONEY_UNPAID -> VehicleOrderDetailPageWishlist(
            navCtrl = navCtrl,
            intent = {},
            viewState = viewStates
        )

        OrderState.EARNEST_MONEY_PAID -> VehicleOrderDetailPageWishlist(
            navCtrl = navCtrl,
            intent = {},
            viewState = viewStates
        )

        OrderState.DOWN_PAYMENT_UNPAID -> VehicleOrderDetailPageWishlist(
            navCtrl = navCtrl,
            intent = {},
            viewState = viewStates
        )

        OrderState.DOWN_PAYMENT_PAID -> VehicleOrderDetailPageWishlist(
            navCtrl = navCtrl,
            intent = {},
            viewState = viewStates
        )

        OrderState.ARRANGE_PRODUCTION -> VehicleOrderDetailPageWishlist(
            navCtrl = navCtrl,
            intent = {},
            viewState = viewStates
        )

        OrderState.PREPARE_TRANSPORT -> VehicleOrderDetailPageWishlist(
            navCtrl = navCtrl,
            intent = {},
            viewState = viewStates
        )

        OrderState.PREPARE_DELIVER -> VehicleOrderDetailPageWishlist(
            navCtrl = navCtrl,
            intent = {},
            viewState = viewStates
        )

        OrderState.DELIVERED -> VehicleOrderDetailPageWishlist(
            navCtrl = navCtrl,
            intent = {},
            viewState = viewStates
        )

        OrderState.ACTIVATED -> VehicleOrderDetailPageWishlist(
            navCtrl = navCtrl,
            intent = {},
            viewState = viewStates
        )
    }
}