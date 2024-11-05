package net.hwyz.iov.mp.app.module.marketing.page

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment.Companion.Rectangle
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.jpush.android.cache.Sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import net.hwyz.iov.mp.app.R
import net.hwyz.iov.mp.app.component.bar.TopBackTitleBar
import net.hwyz.iov.mp.app.component.button.RoundedCornerButton
import net.hwyz.iov.mp.app.data.bean.SaleModelConfig
import net.hwyz.iov.mp.app.module.marketing.intent.VehicleModelConfigIntent
import net.hwyz.iov.mp.app.module.marketing.viewmodel.VehicleModelConfigViewModel
import net.hwyz.iov.mp.app.theme.AppTheme
import net.hwyz.iov.mp.app.utils.RouteUtils.back
import java.math.BigDecimal

/**
 * 车辆车型配置页 - 外饰
 *
 * @author hwyz_leo
 */
@Composable
fun VehicleModelConfigPageExterior(
    navCtrl: NavHostController,
    exteriors: List<SaleModelConfig>,
    selectExterior: String,
    intent: (VehicleModelConfigIntent) -> Unit
) {
    Box(
        modifier = Modifier
            .background(AppTheme.colors.themeUi)
    ) {
        var selectedTabIndex = remember { mutableStateOf(0) }
        Column {
            exteriors.forEachIndexed { index, exterior ->
                if (selectedTabIndex.value == index) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(exterior.typeImage[0])
                            .setHeader("User-Agent", "Mozilla/5.0")
                            .build(),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(exterior.typeName, fontSize = 16.sp)
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        if (exterior.typePrice.toInt() > 0) {
                            Text(exterior.typePrice.toString())
                        } else {
                            Text(
                                text = "价格已包含",
                                fontSize = 12.sp,
                                color = AppTheme.colors.fontSecondary
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            Spacer(modifier = Modifier.height(120.dp))
            Row(
                modifier = Modifier.padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                exteriors.forEachIndexed { index, exterior ->
                    Box(contentAlignment = Alignment.Center) {
                        if (selectedTabIndex.value == index) {
                            Canvas(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable { }
                            ) {
                                drawCircle(
                                    color = Color(android.graphics.Color.parseColor(exterior.typeParam)),
                                    radius = size.width / 2,
                                )
                            }
                            Canvas(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable { }
                            ) {
                                drawCircle(
                                    color = Color.White,
                                    radius = size.width / 2,
                                )
                            }
                        } else {
                            Canvas(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable { }
                            ) {
                                drawCircle(
                                    color = Color.White,
                                    radius = size.width / 2,
                                )
                            }
                        }
                        Canvas(
                            modifier = Modifier
                                .size(20.dp)
                                .clickable {
                                    selectedTabIndex.value = index
                                }
                        ) {
                            drawCircle(
                                color = Color(android.graphics.Color.parseColor(exterior.typeParam)),
                                radius = size.width / 2
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview
@Composable
fun VehicleModelConfigPageExteriorPreview() {
    VehicleModelConfigPageExterior(
        navCtrl = rememberNavController(),
        exteriors = listOf(
            SaleModelConfig(
                saleCode = "H01",
                type = "EXTERIOR",
                typeCode = "WS06",
                typeName = "冰川白车漆",
                typePrice = BigDecimal(0),
                typeImage = listOf("https://pic.imgdb.cn/item/67064442d29ded1a8c8801fa.png"),
                typeDesc = "",
                typeParam = "#e8e8e7"
            ),
            SaleModelConfig(
                saleCode = "H01",
                type = "EXTERIOR",
                typeCode = "WS05",
                typeName = "银河灰车漆",
                typePrice = BigDecimal(0),
                typeImage = listOf("https://pic.imgdb.cn/item/6706473ad29ded1a8c8aa3a9.png"),
                typeDesc = "",
                typeParam = "#868888"
            ),
            SaleModelConfig(
                saleCode = "H01",
                type = "EXTERIOR",
                typeCode = "WS04",
                typeName = "星尘银车漆",
                typePrice = BigDecimal(0),
                typeImage = listOf("https://pic.imgdb.cn/item/6706487dd29ded1a8c8bb358.png"),
                typeDesc = "",
                typeParam = "#cbcbce"
            ),
            SaleModelConfig(
                saleCode = "H01",
                type = "EXTERIOR",
                typeCode = "WS03",
                typeName = "天际蓝车漆",
                typePrice = BigDecimal(0),
                typeImage = listOf("https://pic.imgdb.cn/item/67064bc8d29ded1a8c8e461b.png"),
                typeDesc = "",
                typeParam = "#4681ad"
            ),
            SaleModelConfig(
                saleCode = "H01",
                type = "EXTERIOR",
                typeCode = "WS02",
                typeName = "翡翠绿车漆",
                typePrice = BigDecimal(0),
                typeImage = listOf("https://pic.imgdb.cn/item/67065b68d29ded1a8c999b62.png"),
                typeDesc = "",
                typeParam = "#3a5337"
            ),
            SaleModelConfig(
                saleCode = "H01",
                type = "EXTERIOR",
                typeCode = "WS01",
                typeName = "墨玉黑车漆",
                typePrice = BigDecimal(0),
                typeImage = listOf("https://pic.imgdb.cn/item/67065c4fd29ded1a8c9a3714.png"),
                typeDesc = "",
                typeParam = "#0f0e11"
            )
        ),
        selectExterior = "",
        intent = {}
    )
}