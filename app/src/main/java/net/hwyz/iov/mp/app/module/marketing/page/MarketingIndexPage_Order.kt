package net.hwyz.iov.mp.app.module.marketing.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.RouteName
import net.hwyz.iov.mp.app.component.button.RoundedCornerButton
import net.hwyz.iov.mp.app.module.marketing.intent.MarketingIndexIntent
import net.hwyz.iov.mp.app.module.marketing.state.MarketingIndexState
import net.hwyz.iov.mp.app.theme.AppTheme
import net.hwyz.iov.mp.app.utils.GlobalStateManager
import net.hwyz.iov.mp.app.utils.OrderState
import net.hwyz.iov.mp.app.utils.RouteUtils
import net.hwyz.iov.mp.app.utils.VehicleType
import timber.log.Timber

/**
 * 购车首页 - 有订单页
 *
 * @author hwyz_leo
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MarketingIndexPageOrder(
    navCtrl: NavHostController,
    intent: (MarketingIndexIntent) -> Unit,
    viewState: MarketingIndexState,
) {
    Box(
        modifier = Modifier
            .background(AppTheme.colors.themeUi)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row {
                Text(viewState.displayName)
                Image(
                    painter = painterResource(id = R.drawable.icon_arrow_up_down),
                    contentDescription = "",
                    modifier = Modifier.height(20.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Column(modifier = Modifier.clickable {
                    GlobalStateManager.parameters = mapOf("orderState" to viewState.orderState)
                    RouteUtils.navTo(
                        navCtrl = navCtrl,
                        destinationName = RouteName.ORDER_DETAIL
                    )
                }) {
                    Row {
                        if (viewState.vehicleType == VehicleType.ORDER) {
                            when (viewState.orderState) {
                                OrderState.EARNEST_MONEY_UNPAID -> Text(
                                    "待支付",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                OrderState.WISHLIST -> Text(
                                    "待支付",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                OrderState.EARNEST_MONEY_PAID -> Text(
                                    "待支付",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                OrderState.DOWN_PAYMENT_UNPAID -> Text(
                                    "待支付",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                OrderState.DOWN_PAYMENT_PAID -> Text(
                                    "待支付",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                OrderState.ARRANGE_PRODUCTION -> Text(
                                    "待支付",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                OrderState.PREPARE_TRANSPORT -> Text(
                                    "待支付",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                OrderState.PREPARE_DELIVER -> Text(
                                    "待支付",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                OrderState.DELIVERED -> Text(
                                    "待支付",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                OrderState.ACTIVATED -> Text(
                                    "待支付",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else {
                            Text("我的心愿单", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    val pagerState = rememberPagerState(initialPage = 0)
                    HorizontalPager(
                        pageCount = viewState.saleModelImages.size,
                        state = pagerState,
                        modifier = Modifier.fillMaxSize()
                    ) { page ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(viewState.saleModelImages[page])
                                .setHeader("User-Agent", "Mozilla/5.0")
                                .build(),
                            contentDescription = "",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Text("已选配置", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.weight(1f))
                        Text("￥188888")
                    }
                    Row {
                        Text(viewState.saleModelDesc, color = AppTheme.colors.fontSecondary)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    if (viewState.vehicleType == VehicleType.ORDER) {

                    } else {
                        RoundedCornerButton(
                            text = "立即订购",
                            bgColor = Color.Black,
                            textColor = Color.White
                        ) {

                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MarketingIndexPageOrderPreview() {
    val state = MarketingIndexState()
    state.displayName = "123"
    state.saleModelImages = listOf(
        "https://pic.imgdb.cn/item/67065b68d29ded1a8c999b62.png",
        "https://pic.imgdb.cn/item/670685e4d29ded1a8cb9c55f.png"
    )
    state.saleModelDesc =
        "寒01七座版 | 有备胎 | 翡翠绿车漆 | 21寸轮毂(四季胎)高亮黑 | 乌木黑内饰 | 高阶智驾"
    MarketingIndexPageOrder(
        navCtrl = rememberNavController(),
        intent = {},
        viewState = state
    )
}