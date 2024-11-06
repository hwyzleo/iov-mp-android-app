package net.hwyz.iov.mp.app.module.marketing.page

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import net.hwyz.iov.mp.app.data.bean.SaleModelConfig
import net.hwyz.iov.mp.app.module.marketing.intent.VehicleModelConfigIntent
import net.hwyz.iov.mp.app.theme.AppTheme
import java.math.BigDecimal

/**
 * 车辆车型配置页 - 内饰
 *
 * @author hwyz_leo
 */
@Composable
fun VehicleModelConfigPageInterior(
    navCtrl: NavHostController,
    interiors: List<SaleModelConfig>,
    selectInterior: String,
    intent: (VehicleModelConfigIntent) -> Unit
) {
    Box(
        modifier = Modifier
            .background(AppTheme.colors.themeUi)
            .onGloballyPositioned {
                if (selectInterior == "" && interiors.isNotEmpty()) {
                    intent(VehicleModelConfigIntent.OnTapInterior(code = interiors.first().typeCode))
                }
            }
    ) {
        val selectedTabIndex = remember { mutableStateOf(0) }
        Column {
            interiors.forEachIndexed { index, interior ->
                if (selectedTabIndex.value == index) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(interior.typeImage[0])
                            .setHeader("User-Agent", "Mozilla/5.0")
                            .build(),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(interior.typeName, fontSize = 16.sp)
                        Spacer(modifier = Modifier.weight(1f))
                    }
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        if (interior.typePrice.toInt() > 0) {
                            Text(interior.typePrice.toString())
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
                interiors.forEachIndexed { index, interior ->
                    Box(contentAlignment = Alignment.Center) {
                        if (selectedTabIndex.value == index) {
                            Canvas(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable { }
                            ) {
                                drawCircle(
                                    color = Color(android.graphics.Color.parseColor(interior.typeParam)),
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
                                    intent(VehicleModelConfigIntent.OnTapInterior(code = interior.typeCode))
                                }
                        ) {
                            drawCircle(
                                color = Color(android.graphics.Color.parseColor(interior.typeParam)),
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
fun VehicleModelConfigPageInteriorPreview() {
    VehicleModelConfigPageInterior(
        navCtrl = rememberNavController(),
        interiors = listOf(
            SaleModelConfig(
                saleCode = "H01",
                type = "INTERIOR",
                typeCode = "NS03",
                typeName = "霜雪白内饰",
                typePrice = BigDecimal(0),
                typeImage = listOf("https://pic.imgdb.cn/item/670685e4d29ded1a8cb9c55f.png"),
                typeDesc = "",
                typeParam = "#dcdcd6"
            ),
            SaleModelConfig(
                saleCode = "H01",
                type = "INTERIOR",
                typeCode = "NS02",
                typeName = "珊瑚橙内饰",
                typePrice = BigDecimal(0),
                typeImage = listOf("https://pic.imgdb.cn/item/670687ecd29ded1a8cbb5280.png"),
                typeDesc = "",
                typeParam = "#a35d31"
            ),
            SaleModelConfig(
                saleCode = "H01",
                type = "INTERIOR",
                typeCode = "NS01",
                typeName = "乌木黑内饰",
                typePrice = BigDecimal(0),
                typeImage = listOf("https://pic.imgdb.cn/item/670688dbd29ded1a8cbc1321.png"),
                typeDesc = "",
                typeParam = "#424141"
            )
        ),
        selectInterior = "",
        intent = {}
    )
}