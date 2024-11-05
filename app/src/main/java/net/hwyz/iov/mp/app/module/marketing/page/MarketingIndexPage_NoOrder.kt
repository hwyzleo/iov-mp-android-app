package net.hwyz.iov.mp.app.module.marketing.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.RouteName
import net.hwyz.iov.mp.app.component.button.RoundedCornerButton
import net.hwyz.iov.mp.app.module.marketing.intent.MarketingIndexIntent
import net.hwyz.iov.mp.app.module.marketing.state.MarketingIndexState
import net.hwyz.iov.mp.app.utils.RouteUtils

/**
 * 购车首页 - 无订单订购页
 *
 * @author hwyz_leo
 */
@Composable
fun MarketingIndexPageNoOrder(
    navCtrl: NavHostController,
    intent: (MarketingIndexIntent) -> Unit,
    viewState: MarketingIndexState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.buy_vehicle_banner),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, end = 20.dp)
                    .statusBarsPadding()
                    .navigationBarsPadding()
            ) {
                Spacer(modifier = Modifier.weight(1f))
                RoundedCornerButton(text = "立即订购") {
                    RouteUtils.navTo(
                        navCtrl = navCtrl,
                        destinationName = RouteName.MODEL_CONFIG
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}

@Preview
@Composable
fun MarketingIndexPageNoOrderPreview() {
    MarketingIndexPageNoOrder(
        navCtrl = rememberNavController(),
        intent = {},
        viewState = MarketingIndexState()
    )
}