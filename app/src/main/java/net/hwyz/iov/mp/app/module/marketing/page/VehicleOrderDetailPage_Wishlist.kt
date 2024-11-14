package net.hwyz.iov.mp.app.module.marketing.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Divider
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import net.hwyz.iov.mp.app.component.bar.TopBackTitleBar
import net.hwyz.iov.mp.app.component.button.RoundedCornerButton
import net.hwyz.iov.mp.app.module.marketing.intent.MarketingIndexIntent
import net.hwyz.iov.mp.app.module.marketing.intent.VehicleOrderDetailIntent
import net.hwyz.iov.mp.app.module.marketing.state.MarketingIndexState
import net.hwyz.iov.mp.app.module.marketing.state.VehicleOrderDetailState
import net.hwyz.iov.mp.app.module.marketing.viewmodel.MarketingIndexViewModel
import net.hwyz.iov.mp.app.theme.AppTheme
import net.hwyz.iov.mp.app.utils.GlobalStateManager
import net.hwyz.iov.mp.app.utils.OrderState
import net.hwyz.iov.mp.app.utils.RouteUtils
import net.hwyz.iov.mp.app.utils.RouteUtils.back
import net.hwyz.iov.mp.app.utils.VehicleManager
import java.math.BigDecimal

/**
 * 车辆订单详情页 - 心愿单
 *
 * @author hwyz_leo
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VehicleOrderDetailPageWishlist(
    navCtrl: NavHostController,
    intent: (VehicleOrderDetailIntent) -> Unit,
    viewState: VehicleOrderDetailState
) {
    Box(
        modifier = Modifier
            .background(AppTheme.colors.themeUi)
            .padding(20.dp)
    ) {
        TopBackTitleBar(title = "心愿单", onBack = { navCtrl.back() })
        Column {
            Spacer(modifier = Modifier.height(10.dp))
            val pagerState = rememberPagerState(initialPage = 0)
            HorizontalPager(
                pageCount = viewState.saleModelImages.size,
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(viewState.saleModelImages[page])
                        .setHeader("User-Agent", "Mozilla/5.0")
                        .build(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(text = viewState.saleModelName, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "修改配置")
            }
            Spacer(modifier = Modifier.height(5.dp))
            Divider()
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(text = "统一零售价")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = viewState.saleModelPrice.toString())
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(text = viewState.saleSpareTireName)
                Spacer(modifier = Modifier.weight(1f))
                if(viewState.saleSpareTirePrice.toDouble() > 0) {
                    Text(text = "￥${viewState.saleSpareTirePrice}")
                } else {
                    Text(text = "已包含")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(text = viewState.saleExteriorName)
                Spacer(modifier = Modifier.weight(1f))
                if(viewState.saleExteriorPrice.toDouble() > 0) {
                    Text(text = "￥${viewState.saleExteriorPrice}")
                } else {
                    Text(text = "已包含")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(text = viewState.saleWheelName)
                Spacer(modifier = Modifier.weight(1f))
                if(viewState.saleWheelPrice.toDouble() > 0) {
                    Text(text = "￥${viewState.saleWheelPrice}")
                } else {
                    Text(text = "已包含")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(text = viewState.saleInteriorName)
                Spacer(modifier = Modifier.weight(1f))
                if(viewState.saleInteriorPrice.toDouble() > 0) {
                    Text(text = "￥${viewState.saleInteriorPrice}")
                } else {
                    Text(text = "已包含")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(text = viewState.saleAdasName)
                Spacer(modifier = Modifier.weight(1f))
                if(viewState.saleAdasPrice.toDouble() > 0) {
                    Text(text = "￥${viewState.saleAdasPrice}")
                } else {
                    Text(text = "已包含")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider()
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(text = "购车总价")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "￥${viewState.totalPrice}")
            }
            Spacer(modifier = Modifier.weight(1f))
            RoundedCornerButton(text = "立即订购", bgColor = Color.Black, textColor = Color.White) {
                
            }
        }
    }
}

@Preview
@Composable
fun VehicleOrderDetailPageWishlistPreview() {
    val state = VehicleOrderDetailState()
    state.saleModelImages = listOf(
        "https://pic.imgdb.cn/item/67065b68d29ded1a8c999b62.png",
        "https://pic.imgdb.cn/item/670685e4d29ded1a8cb9c55f.png"
    )
    state.saleModelName = "寒01七座版"
    state.saleModelPrice = BigDecimal.valueOf(188888)
    state.saleSpareTireName = "有备胎"
    state.saleSpareTirePrice = BigDecimal.valueOf(6000)
    state.saleExteriorName = "翡翠绿车漆"
    state.saleExteriorPrice = BigDecimal.valueOf(0)
    state.saleWheelName = "21寸轮毂（四季胎）高亮黑"
    state.saleWheelPrice = BigDecimal.valueOf(0)
    state.saleInteriorName = "乌木黑内饰"
    state.saleInteriorPrice = BigDecimal.valueOf(0)
    state.saleAdasName = "高价智驾"
    state.saleAdasPrice = BigDecimal.valueOf(10000)
    state.totalPrice = BigDecimal.valueOf(205888)
    VehicleOrderDetailPageWishlist(
        navCtrl = rememberNavController(),
        intent = {},
        viewState = state
    )
}