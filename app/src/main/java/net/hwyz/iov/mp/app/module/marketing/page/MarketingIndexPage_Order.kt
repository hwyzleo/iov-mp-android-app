package net.hwyz.iov.mp.app.module.marketing.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.module.marketing.intent.MarketingIndexIntent
import net.hwyz.iov.mp.app.module.marketing.state.MarketingIndexState
import net.hwyz.iov.mp.app.theme.AppTheme

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
            Column(modifier = Modifier
                .verticalScroll(rememberScrollState())
                .onGloballyPositioned {

                }) {
                Row {
                    Text("我的心愿单")
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
                Row {
                    Text("已选配置")
                    Spacer(modifier = Modifier.weight(1f))
                    Text("￥188888")
                }
                Row {
                    Text(viewState.saleModelDesc, color = AppTheme.colors.fontSecondary)
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
    state.saleModelDesc = "寒01七座版 | 有备胎 | 翡翠绿车漆 | 21寸轮毂(四季胎)高亮黑 | 乌木黑内饰 | 高阶智驾"
    MarketingIndexPageOrder(
        navCtrl = rememberNavController(),
        intent = {},
        viewState = state
    )
}